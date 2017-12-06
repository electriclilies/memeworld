/**
 * GameCharacter.java
 * @author Emma Shumadine
 * modifications: 
 * Lily Orth-Smith added toString() dec 4
 * */

public class GameCharacter {
  protected int currentHP;
  protected int maxHP;
  protected Weapon weapon;
  
  /**
   * Creates a new game character
   * @param hp the maximum hp of the character
   * @param w the weapon of the character
   * */
  public GameCharacter(int hp, Weapon w) {
    maxHP = hp;
    currentHP = maxHP;
    weapon = w;
  }
  
  
  /**
   * Attacks the target with the weapon. Returns -1 if the attack misses, and the damage of the attack otherwise.
   * @param target the target of the attack
   * @return -1 if the attack misses, and the damage of the attack otherwise
   * */
  public int attack(GameCharacter target) {
    int attackResult = weapon.use();
    if(attackResult != -1) {
      target.changeHP(-attackResult);
    }
    return attackResult;
  }
  
  /**
   * Returns the maximum HP of the character
   * @return the maximum hp
   * */
  public int getMaxHP() {
    return maxHP;
  }
  
  /**
   * Returns the current HP of the character
   * @return the current hp
   * */
  public int getCurrentHP() {
    return currentHP;
  }
  
  /**
   * Returns the character's weapon
   * @return the weapon
   * */
  public Weapon getWeapon() {
    return weapon;
  }
  
  /**
   * Sets the maximum HP of the character
   * @param the maximum hp
   * */
  public void setMaxHP(int hp) {
    maxHP = hp;
  }
  
  /**
   * Changes the current HP of the character
   * @param amount the amount to change the hp by
   * */
  public void changeHP(int amount) {
    int total = currentHP + amount;
    if(total > maxHP) {
      currentHP = maxHP;
    } else if (total < 0) {
      currentHP = 0;
    } else {
      currentHP += amount;
    }
  }
  
  /**
   * Sets the current HP of the character
   * @param hp the hp
   * */
  public void setHP(int hp) {
    if(hp > maxHP) {
      currentHP = maxHP;
    } else {
      currentHP = hp;
    }
  }
  
  /**
   * Sets the character's weapon
   * @param w the weapon
   * */
  public void setWeapon(Weapon w) {
    weapon = w;
  }
  
  public String toString() {
     return ("HP: " + currentHP + "/" + maxHP + " Weapon: " + weapon);
  }
}