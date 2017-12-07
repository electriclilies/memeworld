/**
 * Situation.java
 * @author Emma Shumadine
 */

import java.util.Vector;

public class Situation {
   private String name;
   private String description;
   private Vector<String> requiredItems = new Vector<>();
   private Fight fight = null;
   private Vector<Item> items = new Vector<>();
   private Vector<Item> pickedUp = new Vector<>();

   /**
    * Creates a situation with no items, no required items, and no fight
    * @param n the name
    * @param desc the description
    */
   public Situation(String n, String desc) {
      name = n;
      description = desc;
   }

    /**
     * Returns a Vector containing the items currently in the situation
     * @return the items in the situation
     */
    public Vector<Item> getItems() {
        return items;
    }

    /**
     * Adds an item to the situation's items
     * @param toAdd the item to add
     */
    public void addItem(Item toAdd) {
        items.add(toAdd);
    }

    /**
     * Adds a fight to the situation. Overwrites the existing fight if the
     * situation already has one.
     * @param f the fight to add
     */
    public void addFight(Fight f) {
        fight = f;
    }

    /**
     * Removes an item from the situation
     * @param toRemove the item to remove
     */
    public void removeItem(Item toRemove) {
        pickedUp.add(toRemove);
        items.remove(toRemove);
    }

    /**
     * Compares this situation to another one and returns true if they have the
     * same name
     * @param s the situation to compare
     * @return true if the situations' names are the same
     */
    public boolean equals(Situation s) {
        return name.equals(s.getName());
    }

    /**
     * Returns true if the situation contains a fight, and false otherwise
     * @return true if the situation contains a fight
     */
    public boolean hasFight() {
        return fight != null;
    }

    /**
     * Resets the situation, respawning enemies and regenerating the healing
     * items
     */
    public void reset() {
        if(hasFight()) {
            fight.respawn();
        }
        for(Item i: pickedUp) {
            if(i.getClass() != Weapon.class) {
                items.add(i);
            }
        }
    }

    /**
     * Returns the name of the situation
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the situation
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the fight in this situation. Null if none.
     * @return the fight
     */
    public Fight getFight() {
        return fight;
    }

    /**
     * Sets the name of the situation
     * @param n the name
     */
    public void setName(String n) {
        name = n;
    }

    /**
     * Sets the description of the situation
     * @param desc the description
     */
    public void setDescription(String desc) {
        description = desc;
    }

    /**
     * Returns a string representation of the situation
     * @return a string representation of the situation
     */
    public String toString() {
        return name + ": " + description + "\n items: " + items;
    }

    public static void main(String[] args) {
        Situation s1 = new Situation("Castle", "the castle of memes");
        System.out.println(s1.hasFight());
        s1.addItem(new Item("dat boi", "dat boiiiiiiiiii"));
        System.out.println(s1);
        Vector<GameCharacter> enemies = new Vector<>();
        enemies.add(new GameCharacter(10, new Weapon("rainbow",
                "a rainbow", 0.90, "1d6"), "Sample"));
        s1.addFight(new Fight(new Player(), enemies));
        System.out.println(s1.hasFight());
    }
}