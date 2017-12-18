/**
 * Weapon.java
 * This class represents a weapon with a hit rate out of 100% and damage determined using D&D-style multi-sided dice.
 * @author Emma Shumadine (primary), Lily Orth-Smith, Rachel Zhang
 * */

import java.util.Random;
import java.util.Vector;

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



  public static void main(String[] args) {
    Vector<Weapon> weapons = new Vector<>();
    Weapon sword1 = new Weapon("Sword", "A sharp sword. Hit Rate: 95%, 4d6 damage", 0.95, "4d6");
    Weapon sword2 = new Weapon("Sword", "A sharp sword. Hit Rate: 95%, 4d6 damage", 0.95, "4d6");
    weapons.add(sword1);
    weapons.add(sword2);
    System.out.println(weapons.remove(sword1));
    System.out.println(weapons);
  }
}