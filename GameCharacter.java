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
  protected String name;
  
  /**
   * Creates a new game character
   * @param hp the maximum hp of the character
   * @param w the weapon of the character
   * @param n the name
   * */
  public GameCharacter(int hp, Weapon w, String n) {
    maxHP = hp;
    currentHP = maxHP;
    weapon = w;
    name = n;
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
   * Returns the character's name
   * @return the name
   * */
  public String getName() {
    return name;
  }
  
  /**
   * Sets the maximum HP of the character
   * @param hp the maximum hp
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
   * Clones the character
   * @return a cloned version of the character
   */
  public GameCharacter clone() {
    return new GameCharacter(maxHP, weapon, name);
  }
  
  /**
   * Sets the character's weapon
   * @param w the weapon
   * */
  public void setWeapon(Weapon w) {
    weapon = w;
  }
  
  /**
   * Sets the character's name
   * @param n the name
   * */
  public void setName(String n) {
    name = n;
  }
  
  public String toString() {
     return ("HP: " + currentHP + "/" + maxHP + " Weapon: " + weapon);
  }
}