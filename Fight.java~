/**
 * Fight.java
 * @author Emma Shumadine
 * */

import java.util.Vector;
import javafoundations.LinkedQueue;

public class Fight {
  LinkedQueue<GameCharacter> turns = new LinkedQueue<GameCharacter>();
  Vector<GameCharacter> enemies;
  Player player;
  
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
   * */
  public void playOneRound(String action, int targetIndex) {
    GameCharacter currentTurn = turns.dequeue();
    GameCharacter target;
    
    if(targetIndex == -1) {
      target = player;
    } else {
      target = enemies.get(targetIndex);
    }
    
    if(enemies.contains(currentTurn) || action.equals("attack")) {
      currentTurn.attack(target);
    } else {
      HealItem toUse = player.getItem(targetIndex);
      toUse.use();
    }
    
    if(currentTurn.getCurrentHP() != 0) {
      turns.enqueue(currentTurn);
    }
  }
  
  /**
   * Runs the fight
   * */
  public void fight() {
    while(player.getCurrentHP() != 0 && turns.size() != 1) {
      plyOneRound(action, targetIndex);
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
}