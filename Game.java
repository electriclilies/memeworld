/**
 * Game.java
 *
 * This class provides methods with which to run the game. The map is predetermined.
 *
 * Known bugs:
 *      The fight goes an extra round after the enemies are defeated
 *      Clicking too fast can break the game (seems to be a problem with the mouse action listener
 *
 * @author Emma Shumadine, Lily Orth-Smith, Rachel Zhang
 * */

import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Game {
   
   private Player player;
   private Map myMap;

   /**
    * Creates a game with the default map
    */
   public Game() {
   player = new Player();
   myMap = makeMap1();
   }

   /**
    * Creates a game with the specified map
    * @param userMap the map to use for the game
    */
   public Game(Map userMap) {
      player = new Player();
      myMap = userMap;
   }

   /**
    * Returns the player to the start of the map
    */
   public void goToBeginning() {
      myMap.goToBeginning();
   }

   /**
    * Moves the player to an adjacent situation given by the index in the adjacency array
    * @param adjIndex the index in the adjacency array
    */
   public void movePlayer(int adjIndex) {
      Situation[] adjacentSituations = myMap.getAdjArray();
      movePlayer(adjacentSituations[adjIndex]);
   }

   /**
    * Moves the player to an adjacent situation
    * @param adjSit the situation
    */
   public void movePlayer(Situation adjSit) {

      //check if the player has the required items to enter the situation
      if(!adjSit.getRequiredItems().isEmpty()) {
         int count = 0;

         for (Item i: adjSit.getRequiredItems()) {
            if (player.hasItem(i)) {
               count++;
            }
         }

         if (count == adjSit.getRequiredItems().size()) {
            myMap.changeSituation(adjSit);
         } else {
            throw new IllegalMoveException();
         }

      } else {
         myMap.changeSituation(adjSit);
      }
   }
   

   /**
    * Picks up an item from the current situation and puts it in the player's inventory
    * @param item the item to pick up
    */
   public void pickUpItem(Item item) throws FullInventoryException{
     if(!player.inventoryIsFull()){
       myMap.getCurrentSituation().removeItem(item);
       double hitRate = item.getHitRate();
       if (hitRate == 0) {
         player.pickUpItem(item);
       }
       else { //item is a weapon
         player.pickUpItem(item);
         Item drop = player.pickUpWeapon(item);
         dropItem(drop);
         
       }
     }
   }


   /**
    * Removes the item from the player's inventory and drops it into the situation
    * @param item the item to drop
    */
  public void dropItem(Item item) {
    if (!item.equals(player.getWeapon()))
      player.dropItem(item);
      myMap.getCurrentSituation().addItem(item);
   }

    /**
     * Uses an item on the player (if it is a health item) and returns a string describing the result
     * @param item the item to use
     * @return a string describing the result
     */
  public String useItem(Item item) {
    int healed = player.useHealthItem(item);
    int HP = item.getHp();
    if (HP != 0)
    return "Used health item. Healed " + Integer.toString(healed) + " HP.";
    else
      return "Can't use this item.";
  }


   /**
    * Returns true if the boss has been defeated, and false otherwise
    * @return true if the boss has been defeated
    */
   public boolean isOver() {
      return myMap.isCleared();
   }


    /**
     * Returns the description of the current situation
     * @return the description
     */
    public String getCurrentDescription() {
     return myMap.getCurrentSituation().getDescription();
   }
   
   public String[] getAdjNames() {
     String[] names = myMap.getAdjNames();
     String[] options = new String[names.length + 1];
     options[0] = "...";
     for (int i = 1; i < options.length; i++) {
       options[i] = names[i-1];
     }
     return options;
   }

    /**
     * Returns the current health of the player as a string status message
     * @return the current health of the player as a string status message
     */
     public String getPlayerHealth() {
     String s =  "Current Meme Points: ";
     s+= Integer.toString(player.getCurrentHP());
     s+= "/";
     s+= Integer.toString(player.getMaxHP());
     return s;
   }

    /**
     * Determines and returns the color to be associated with the player's current hp, based on the current health
     * @return the color to be associated with the player's hp
     */
     public Color getHealthColor() {
       int b = 66;
       int r = 244 - (player.getCurrentHP());
       int g = 74 + (player.getCurrentHP());
       System.out.println(r + " " + g + " " + b);
       return new Color(r, g, b);
     }


    /**
     * Returns a Vector containing the items in the current situation
     * @return a vector containing the items in the current situation
     */
    public Vector<Item> getRoomItems() {
     Vector<Item> temp = myMap.getCurrentSituation().getItems();
     return temp;
     
   }

    /**
     * Returns the player's inventory as a vector
     * @return the player's inventory as a vector
     */
   public Vector<Item> getInventory() {
     Vector<Item> inventory = new Vector<Item>();
     Item[] temp = player.getInventory();
     for (int i = 0; i < player.getLastItemIndex() +1; i++)
       inventory.add(temp[i]);
     return inventory;
   }

    /**
     * Returns the player's current weapon as a string
     * @return the player's current weapon as a string
     */
   public String getWeapon() {
     return "Current Weapon: " + player.getWeapon().toString();
   }

    /**
     * Returns true if the current situation contains a fight
     * @return true if the current situation contains a fight
     */
   public boolean hasFight() {
     return myMap.getCurrentSituation().hasFight();
   }

    /**
     * Returns a vector containing the names of the enemies in the current situation's fight
     * @return a vector containing the names of the enemies
     */
   public Vector<String> getEnemyNames() {
     Vector<String> enemyNames = new Vector<String>();
     Vector<GameCharacter> enemies = myMap.getCurrentSituation().getFight().getEnemies();
     for (int i = 0; i < enemies.size(); i++)
       enemyNames.add(enemies.get(i).getName());

     return enemyNames;
   }

    /**
     * Returns a vector containing the enemies in the current situation's fight
     * @return a vector containing the enemies
     */
   public Vector<GameCharacter> getEnemies() {
     return myMap.getCurrentSituation().getFight().getEnemies();
   }

    /**
     * Returns a string describing the current state of the current situation's fight
     * @return a string describing the current state of the fight
     */
   public String currentFightState() {
     String p = "";
     p += "Player HP: " + Integer.toString(player.getCurrentHP());
     Vector<GameCharacter> enemies = myMap.getCurrentSituation().getFight().getEnemies();
     String e = "Enemies: [";
      for(int i = 0; i < enemies.size(); i++) {
        GameCharacter en = enemies.get(i);
        if(enemies.indexOf(en) != enemies.size() - 1) {
          e += en.getName() + ": " + en.getCurrentHP() + ", ";
        } else {
          e += en.getName() + ": " + en.getCurrentHP() + "]";
        }
      }
      return p + "\n" +e;
   }

    /**
     * Runs one round of the current situation's fight
     * @param action the action to be taken ("heal" or "attack")
     * @param targetIndex the index of the target in its respective data structure (-1 if it's the player)
     * @return A string describing the results of the round
     */
   public String fightOneRound(String action, int targetIndex){
     while (!myMap.getCurrentSituation().getFight().isPlayersTurn()){
       myMap.getCurrentSituation().getFight().playOneRound(action, targetIndex);
     }
     int result = myMap.getCurrentSituation().getFight().playOneRound(action, targetIndex);
     if (action.equals("attack")) {
       if (result == -1)
         return "Miss!";
       else
         return Integer.toString(result) + " damage!";
     }
     else
          return Integer.toString(result) + " healed!";
     
   }

    /**
     * Runs the current situation's fight using user input provided by a GUI
     * @param message the message to be displayed to the player
     * @return a string describing the results of one round of the fight
     */
    public String fight(String message) {
    
     Object[] options = {"Attack","Heal"};
     String title = "Fight!! ";
     for (int i = 0; i < getEnemyNames().size()-1; i++)
        title += getEnemyNames().get(i) + " and ";
     title+= getEnemyNames().get(getEnemyNames().size()-1);
     int choice = JOptionPane.showOptionDialog(null, message, 
    title, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]); 
     if (choice == 0) {
       JComboBox enemyCombo = new JComboBox(getEnemyNames());
       JOptionPane.showMessageDialog(null, enemyCombo, "Select the enemy to attack", JOptionPane.QUESTION_MESSAGE);
       return fightOneRound("attack", enemyCombo.getSelectedIndex()) + "\n" + currentFightState();
     }
     else if (choice == 1) {
       JComboBox healCombo = new JComboBox(getInventory());
       JOptionPane.showMessageDialog(null, healCombo, "Choose an item to heal with", JOptionPane.QUESTION_MESSAGE);
       return fightOneRound("heal", healCombo.getSelectedIndex()) + "\n" + currentFightState();
     }
     else
       return "";
  }

    /**
     * Runs the entirety of the fight using user input provided by a GUI
     */
    public void startFight() {
      String fightString = currentFightState();
      while (!myMap.getCurrentSituation().getFight().fightEnded()){
        fightString = fight(fightString);
      }
      if (player.getCurrentHP() == 0) {
      JOptionPane.showMessageDialog (null, "Game Over.");
      myMap.goToBeginning();
      }
      else {
        JOptionPane.showMessageDialog (null, "You won the fight! Congrats.");
      }
      myMap.getCurrentSituation().getFight().respawn();
    }


   
   /**
    * Makes the map
    * @return the map
    */
   public Map makeMap1() {
      // beginning

      Situation beginning = new Situation("First Room", "You are in a small, dark room.");
      Weapon smashBall = new Weapon("Smash Ball", "Final smaaaaaaash! Hit Rate: 0.1%, 10d100 damage", 0.001, "10d100");
      beginning.addItem(smashBall);

      // nyan cat room

      HealItem rainbowPopTart = new HealItem("A rainbow poptart", "It crosses your mind that this could be nyan cat poop, but as the rainbowy scent wafts "
              + "towards you, you put the thought out of your mind. Heals 5 meme points.", 5);
      Vector<Item> dropNyan = new Vector<>();
      dropNyan.add(rainbowPopTart);
      dropNyan.add(rainbowPopTart.clone());

      Situation nyanCats = new Situation("Nyan Cat Attack!", "Some Nyan Cats seem to have made their home here.", dropNyan);

      Weapon rainbow = new Weapon("Rainbow Power", "Nyanyanyanyanyanyanya!", 0.7, "1d8");
      GameCharacter nyanCat = new GameCharacter(10, rainbow, "A Nyan Cat");
      Vector<GameCharacter> manyNyanCats = new Vector<GameCharacter>();

      manyNyanCats.add(nyanCat);
      GameCharacter nyanCat2 = nyanCat.clone();
      nyanCat2.setName("Another Nyan Cat");
      manyNyanCats.add(nyanCat2);

      Fight nyanCatFight = new Fight(player, manyNyanCats);

      nyanCats.addFight(nyanCatFight);

      // apple room

      Vector<Item> apples = new Vector<>();
      Item apple = new HealItem("Apple", "A crunchy apple. Can be eaten. Heals 10 meme points.", 10);
      apples.add(apple);
      apples.add(apple.clone());

      Situation forest = new Situation("A wild wood", "You find yourself in what appears to be an abandoned apple orchard. Apples are strewn on the "
              + "ground.", apples);

      // sword room

      Situation swordRoom = new Situation("A clearing", "You find yourself in a circular clearing. Someone seems to have left a sword here.");
      Weapon sword = new Weapon("Sword", "A sharp sword. Hit Rate: 95%, 4d6 damage", 0.95, "4d6");
      swordRoom.addItem(sword);

      // dat boi room

      Situation datBoiRoom = new Situation("The dankest of memes", "You can feel the meme energy in the air.");
      Item datBoi = new Item("Dat Boi", "Here comes dat boi!");
      datBoiRoom.addItem(datBoi);

      // sickle room

      Vector<Item> grains = new Vector<>();
      Item wheat = new HealItem("Wheat", "A grain of wheat. Heals 5 meme points", 5);
      grains.add(wheat);
      grains.add(wheat.clone());
      Situation sickleRoom = new Situation("A wheat field", "Outside of the woods lies a large wheat field. It looks like someone was in the "
              + "middle of harvesting the wheat, but only their sickle was left behind.", grains);
      Weapon sickle = new Weapon("Sickle", "A sickle used for harvesting wheat. Hit Rate: 85%, 3d10 damage", 0.85, "3d10");
      sickleRoom.addItem(sickle);

      // the second apple room

      Vector<Item> apples2 = new Vector<>();
      Item apple2 = new HealItem("Apple", "A crunchy apple. Can be eaten.", 10);
      apples.add(apple2);
      apples.add(apple2.clone());

      Situation appleStorage = new Situation("A storehouse", "The storehouse appears not to have been used for some time, but a few apples remain.",
              apples2);

      // doge room

      Situation dogeRoom = new Situation("Shibe", "A doge lives here.");

      Weapon suchDamage = new Weapon("Such Damage", "Much wow", 0.8, "3d8");
      GameCharacter doge = new GameCharacter(50, suchDamage, "Doge");
      Vector<GameCharacter> dogeVector = new Vector<>();
      dogeVector.add(doge);
      Fight dogeFight = new Fight(player, dogeVector);
      dogeRoom.addFight(dogeFight);

      Item dogeCoin = new Item("Dogecoin", "Much money");
      dogeRoom.addItem(dogeCoin);

      // mace room

      Situation maceRoom = new Situation("A cottage", "A run-down cottage. Whoever lived here left behind a mace.");
      Weapon mace = new Weapon("Mace", "A heavy mace. Hit Rate: 80%, 3d12 damage", 0.8, "3d12");
      maceRoom.addItem(mace);

      // boss room

      Situation cena = new Situation("The Finale", "A wild John Cena appears!");
      Weapon sass = new Weapon("Sass", "You call that a weapon?", 0.9, "4d10");
      GameCharacter johnCena = new GameCharacter(150, sass, "John Cena");
      Vector<GameCharacter> cenaFight = new Vector<>();
      cenaFight.add(johnCena);

      Fight bossFight = new Fight(player, cenaFight);

      cena.addFight(bossFight);

      Vector<Item> required = new Vector<>();
      required.add(datBoi);
      required.add(dogeCoin);

      cena.setRequiredItems(required);

      // create the map

      Map map = new Map(beginning, cena);
      map.addVertex(nyanCats);
      map.addVertex(forest);
      map.addVertex(swordRoom);
      map.addVertex(appleStorage);
      map.addVertex(dogeRoom);
      map.addVertex(sickleRoom);
      map.addVertex(datBoiRoom);
      map.addVertex(maceRoom);

      map.addEdge(beginning, nyanCats);
      map.addEdge(beginning, cena);
      map.addEdge(nyanCats, forest);
      map.addEdge(nyanCats, swordRoom);
      map.addEdge(forest, swordRoom);
      map.addEdge(forest, datBoiRoom);
      map.addEdge(swordRoom, sickleRoom);
      map.addEdge(sickleRoom, appleStorage);
      map.addEdge(swordRoom, dogeRoom);
      map.addEdge(dogeRoom, maceRoom);

      return map;
   }

   /**
    * Returns a string representation of the player's current situation, health, and options
    * @return a string representation of the player's current situation, health, and options
    */
   public String toString() {
      LinkedList<Situation> adj = myMap.getAdjacent();
      String s = myMap.getCurrentSituation().toString() + "\nAdjacent rooms: ";
      for(Situation sit: adj) {
         if(adj.indexOf(sit) != adj.size() - 1) {
            s += sit.getName() + ", ";
         } else {
            s += sit.getName();
         }
      }
      s += "\nCurrent Meme Points: " + player.getCurrentHP() + "/" + player.getMaxHP() + "\nInventory: [";
      for(int i = 0; i < player.getInventory().length; i++) {
         if(i != player.getInventory().length - 1) {
            s += player.getInventory()[i] + ", ";
         } else {
            s += player.getInventory()[i] + "]";
         }
      }
      s += "\nWeapon: " + player.getWeapon();
      return s;
   }

   public static void main(String[] args) {
      Game myGame = new Game();
      System.out.println(myGame);
      Weapon smashBall = new Weapon("Smash Ball", "Final smaaaaaaash! Hit Rate: 1%, 10d100 damage", 0.01, "10d100");
      myGame.pickUpItem(smashBall);
      //Item Apple = new HealItem("Apple", "A crunchy apple. Can be eaten. Heals 10 HP.", 10);
      //System.out.println(myGame.useItem(Apple));
      myGame.movePlayer(1);
      myGame.pickUpItem(new HealItem("A rainbow poptart", "It crosses your mind that this could be nyan cat poop, but as the rainbowy scent wafts "
              + "towards you, you put the thought out of your mind. Heals 5 meme points.", 5));
      System.out.println("\n" + myGame);
      myGame.movePlayer(2);
      System.out.println("\n" + myGame);
      Weapon sword = new Weapon("Sword", "A sharp sword. Hit Rate: 95%, 4d6 damage", 0.95, "4d6");
      myGame.pickUpItem(sword);
      System.out.println("\n" + myGame);
   }

}