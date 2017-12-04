//mon dec 4, lily orth-smith added .equals methods

public class HealItem extends Item {
  
  private int hp;
  
  public HealItem(String n, String d, int hp) {
    super(n, d);
    this.hp = hp;
  }
  
  public int getHp() {
    return hp;
  }
  
  public void setHp(int hp) {
    this.hp = hp;
  }
  
//  public int use() {
//    int temp = hp;
//    hp = 0;
//    return temp;
//  }
//  we should have the item be dropped from inventory after usage, instead of setting hp to 0.
//  should be implemented in Player class instead.
//  (in Player class)
//  public void useHealItem(HealItem i) {remove i and add i.getHp() to health}
  
  public String toString() {
    return super.toString() + " Heals " + hp + " hp.";
  }
  
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