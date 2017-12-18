/**
 * Situation.java
 * This class represents a single situation.location in the game. Can contain items and/or a fight.
 * @author Emma Shumadine (primary), Lily Orth-Smith, Rachel Zhang
 */

import java.util.Vector;
import javafoundations.ArrayStack;

public class Situation {
   private String name;
   private String description;
   private String hint;
   private Vector<Item> requiredItems = new Vector<>();
   private Fight fight = null;
   private Vector<Item> items = new Vector<>();
   private Vector<Item> originalItems = new Vector<>();

   /**
    * Creates a situation with no items, no required items, and no fight
    * @param n the name
    * @param desc the description
    */
   public Situation(String n, String desc) {
      name = n;
      description = desc;
      hint = "";
   }

    /**
     * Creates a situation holding the specified items
     * @param n the name
     * @param desc the description
     * @param i the items
     */
    public Situation(String n, String desc, Vector<Item> i) {
       name = n;
       description = desc;
       items = i;
       for(Item it: items) {
           if(it.getClass() != Weapon.class) {
               originalItems.add(it.clone());
           }
       }
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
        int index = -1;
        for (int i = 0; i < items.size(); i++) {
            if (toRemove.equals(items.get(i)))
                index = i;
        }
        items.removeElementAt(index);
    }

    /**
     * Sets the names of the required items for the situation
     * @param items the vector containing the required items
     */
    public void setRequiredItems(Vector<Item> items) {
        requiredItems = items;
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

        ArrayStack<Item> w = new ArrayStack<>();
        for(int i = 0; i < originalItems.size(); i++) {
            if (i < items.size()) {
                if (items.get(i).getClass() == Weapon.class) {
                    w.push(items.get(i));
                } else {
                    items.setElementAt(originalItems.get(i).clone(), i);
                }
            } else {
                items.add(originalItems.get(i).clone());
            }
        }
        while (!w.isEmpty()) {
            items.add(w.pop());
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
     * Returns a vector of the respawnable items in the situation
     * @return a vector of the respawnable items
     */
    public Vector<Item> getOriginalItems() {
        return originalItems;
    }

    /**
     * Returns a vector of the names of the required items for this situation
     * @return a vector of the names of the required items
     */
    public Vector<Item> getRequiredItems() {
        return requiredItems;
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
     * Sets the items to be respawned in the situation
     * @param i the items
     */
    public void setOriginalItems(Vector<Item> i) {
        originalItems = i;
    }

    /**
     * Returns a string representation of the situation
     * @return a string representation of the situation
     */
    public String toString() {
        return name + ": " + description + "\nItems: " + items;
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
        
        Situation s2 = new Situation("Castle", "a second castle"); 
        
        System.out.println("Testing situation's .equals() method");
        System.out.println("s1 equals s2? (true): " + s1.equals(s2));

        Item apple = new HealItem("Apple", "Test Apple", 5);
        Situation testPickUp = new Situation("Test", "Situation");
        testPickUp.addItem(apple);
        System.out.println(testPickUp);
        testPickUp.removeItem(new HealItem("Apple", "Test Apple", 5));
        System.out.println(testPickUp);
    }
}