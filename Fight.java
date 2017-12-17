/**
 * Fight.java
 * @author Emma Shumadine
 * */

import java.util.Vector;
import javafoundations.LinkedQueue;

public class Fight {
  private LinkedQueue<GameCharacter> turns = new LinkedQueue<GameCharacter>();
  private Vector<GameCharacter> enemies;
  private Player player;
  
  /**
   * Creates a Fight object with the specified player and enemies. Automatically enqueues all turns.
   * @param p the player
   * @param e the enemies
   * */
  public Fight(Player p, Vector<GameCharacter> e) {
    player = p;
    enemies = e;
    
    turns.enqueue(player);
    for(int i = 0; i < enemies.size(); i++) {
      turns.enqueue(enemies.get(i));
    }
  }
  
  /**
   * Plays one turn of the fight
   * @param action the action for the character to take. Can be "attack" or "heal".
   * @param targetIndex the index of the target in its respective data structure. -1 if target is the player.
   * @return the damage/healing done (-1 if the attack missed).
   * */
  public int playOneRound(String action, int targetIndex) {
    GameCharacter currentTurn = turns.dequeue();
    GameCharacter target;
    int result = 0;
    
    if(currentTurn.getCurrentHP() != 0) {
      if(targetIndex == -1 || enemies.contains(currentTurn)) {
        target = player;
      } else {
        target = enemies.get(targetIndex);
      }
      
      if(enemies.contains(currentTurn) || action.equals("attack")) {
        System.out.println("Attacking " + target.getName() + "...");
        result = currentTurn.attack(target);
        if(result != -1) {
          System.out.println(result + " damage!");
        } else {
          System.out.println("Miss!");
        }
      } else {
        System.out.println(target.getName());
        Item toUse = player.getItem(targetIndex);
        result = toUse.getHp();
        player.useHealthItem(toUse);
      }
      
      turns.enqueue(currentTurn);
    }
    return result;
  }
  
  /**
   * Runs the fight
   * */
  public void fight() {
    while(player.getCurrentHP() != 0 && turns.size() != 1) {
      System.out.println("Player: " + player.getCurrentHP());
      String e = "Enemies: [";
      for(int i = 0; i < enemies.size(); i++) {
        GameCharacter en = enemies.get(i);
        if(enemies.indexOf(en) != enemies.size() - 1) {
          e += en.getName() + ": " + en.getCurrentHP() + ", ";
        } else {
          e += en.getName() + ": " + en.getCurrentHP() + "]";
        }
      }
      System.out.println(e);
      playOneRound("attack", 0);
    }
    
    if(player.getCurrentHP() == 0) {
      System.out.println("Player: " + player.getCurrentHP());
      String e = "Enemies: [";
      for(int i = 0; i < enemies.size(); i++) {
        GameCharacter en = enemies.get(i);
        if(enemies.indexOf(en) != enemies.size() - 1) {
          e += en.getName() + ": " + en.getCurrentHP() + ", ";
        } else {
          e += en.getName() + ": " + en.getCurrentHP() + "]";
        }
      }
      System.out.println(e);
      System.out.println("Game over");
    } else {
      System.out.println(player.getName() + " won!");
    }
  }
  
  /**
   * Respawns all enemies and adds all turns to the queue
   * */
  public void respawn() {
    while(!turns.isEmpty()) {
      turns.dequeue();
    }
    turns.enqueue(player);
    for(int i = 0; i < enemies.size(); i++) {
      GameCharacter current = enemies.get(i);
      current.setHP(current.getMaxHP());
      turns.enqueue(current);
    }
  }

  /**
   * Returns a vector containing all the enemies in the fight
   * @return the enemies in the fight
   */
  public Vector<GameCharacter> getEnemies() {
    return enemies;
  }
  
  public static void main(String[] args) {
    Vector<GameCharacter> enemies = new Vector<GameCharacter>();
    enemies.add(new GameCharacter(100, new Weapon("Sickle", "None", 0.65, "5d40"), "Sample"));
    Player player = new Player();
    
    Fight fight = new Fight(player, enemies);
    fight.fight();
  }
}