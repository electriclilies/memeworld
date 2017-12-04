/**
 * Weapon.java
 * @author Emma Shumadine
 * */

import java.util.Random;

public class Weapon extends Item {
  private int hitRate;
  private Dice damageDice;
  private static Random toRoll = new Random();
  
  /**
   * Creates a weapon
   * @param n the name
   * @param desc the description
   * @param hR the hit rate
   * @param dD the damage string
   * */
  public Weapon(String n, String desc, double hR, String dD) {
    super(n, desc);
    hitRate = (int)(100*hR);
    damageDice = new Dice(dD);
  }
  
  /**
   * Uses the weapon. Returns -1 if the weapon misses and the weapon's damage otherwise.
   * @return -1 if the weapon misses and the weapon's damage otherwise
   * */
  public int use() {
    int hit = toRoll.nextInt();
    if(hit < hitRate) {
      return damageDice.roll();
    } else {
      return -1;
    }
  }
  
  /**
   * Returns the weapon's hit rate
   * @return the hit rate
   * */
  public double getHitRate() {
    return Integer.valueOf(hitRate).doubleValue()/100;
  }
  
  /**
   * Returns the weapon's damage dice
   * @return the damage dice
   * */
  public Dice getDamage() {
    return damageDice;
  }
  
  /**
   * Sets the hit rate of the weapon
   * @param hR the hit rate
   * */
  public void setHitRate(double hR) {
    hitRate = (int)(100*hR);
  }
  
  /**
   * Sets the damage of the weapon
   * @param dD the damage string
   * */
  public void setDamage(String dD) {
    damageDice = new Dice(dD);
  }
  
  public String toString() {
     return super.toString();
  }
}