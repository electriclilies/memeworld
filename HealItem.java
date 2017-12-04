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
  
}