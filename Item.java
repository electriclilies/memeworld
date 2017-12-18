/**
 * Item.java
 * Represents an item with a name and description.
 * @author Emma Shumadine, Lily Orth-Smith, Rachel Zhang (primary)
 * */

public class Item {
  
  protected String name;
  protected String description;

  /**
   * Creates an item
   * @param n the name of the item
   * @param d the description
   */
  public Item(String n, String d) {
    name = n;
    description = d;
  }

  /**
   * Returns the name of the item
   * @return the name of the item
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the item's description
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the name of the item
   * @param n the item's name
   */
  public void setName(String n) {
    name = n;
  }

  /**
   * Sets the item's description
   * @param d the description
   */
  public void setDescription(String d){
    description = d;
  }

  /**
   * Returns true if the items have the same name and same description
   * @param otherItem the item to be compared
   * @return true if the items have the same name and the same description
   */
  public boolean equals(Item otherItem) {
    boolean sameName = name.equals(otherItem.getName());
    boolean sameDescription = description.equals(otherItem.getDescription());
    return (sameName && sameDescription);
  }

  /**
   * Returns a new item with the same name and description
   * @return a cloned item
   */
  public Item clone() {
    return new Item(name, description);
  }

  /**
   * Returns the HP of the item (0 if it is not a HealItem)
   * @return the HP of the item
   */
  public int getHp() {
    return 0;
  }
  
  /**
   * Returns the hit rate of the item (0 if it is not a Weapon)
   * @return the hit rate of the item
   */
  public double getHitRate() {
    return 0;
  }

  /**
   * Uses the item and returns the result (automatically 0 if the item has no use)
   * @return the result of the use
   */
  public int use(){
    return 0;
  }

  /**
   * Returns a string representation of the item
   * @return a string representation of the item
   */
  public String toString() {
    return name;
  }
  

  public static void main(String[] args) {
    Item rock = new Item("Rock", "Does nothing.");
    Item rock2 = new Item("Rock", "Does nothing.");
    System.out.println("rock: " + rock);
    System.out.println("rock2: " + rock2);
    System.out.println("Does rock equal rock2? (true): " + rock.equals(rock2));
    
    System.out.println("Testing rock.getName()-->" + rock.getName());
    System.out.println("Testing rock.getDescription()-->" + rock.getDescription());
    System.out.println("Setting rock name to 'Pebble'");
    rock.setName("Pebble");
    System.out.println("Setting rock description to 'Can be thrown.'");
    rock.setDescription("Can be thrown.");
    System.out.println(rock);
    
    System.out.println("Does rock equal rock2? (false): " + rock.equals(rock2));
  }
}
