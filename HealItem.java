/**
 * HealItem.java
 * This class represents an item that is capable of healing the player
 * @author Emma Shumadine, Lily Orth-Smith, Rachel Zhang (primary)
 */

public class HealItem extends Item {
  
  private int hp;

  /**
   * Creates a healing item
   * @param n the name
   * @param d the description
   * @param hp the amount that the item heals
   */
  public HealItem(String n, String d, int hp) {
    super(n, d);
    this.hp = hp;
  }

  /**
   * Returns the HP of the item
   * @return the HP of the item
   */
  public int getHp() {
    return hp;
  }

  /**
   * Sets the HP that the item heals
   * @param hp the HP
   */
  public void setHp(int hp) {
    this.hp = hp;
  }

  /**
   * Returns a new HealItem with the same name, description, and hp
   * @return a cloned version of this HealItem
   */
  public HealItem clone() {
    return new HealItem(name, description, hp);
  }

  /**
   * Returns true if the items have the same name, description and hp
   * @param healItem the item to be compared
   * @return true if the items have the same name, description, and hp
   */
  public boolean equals(HealItem healItem) {
     return (super.equals(healItem) && (hp == healItem.getHp()));
  }
  
  public static void main(String[] args) {
    Item rock = new Item("Rock", "Does nothing.");
    HealItem healRock = new HealItem("Rock", "Heals", 5);
    HealItem healRock2 = new HealItem("Rock", "Heals", 5);
    HealItem superHealRock = new HealItem("Rock", "Heals", 20);
    System.out.println("Does rock equal healRock? (false): " + rock.equals(healRock));
    System.out.println("Does healRock equal healRock2? (true): " + healRock.equals(healRock2));
    System.out.println("Does superHealRock equal healRock? (false): " + superHealRock.equals(healRock));
    System.out.println("Does superHealRock equal rock? (false): " + superHealRock.equals(healRock));
  }
}