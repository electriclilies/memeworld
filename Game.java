/**
 * A class to play the game! by no means complete. 
 *
 *
 */

import java.util.*;

public class Game {
   
   private Player player;
   private Map myMap;
   
   public Game() {
   player = new Player();
   myMap = makeMap1();
   }
   
   public Game(Map userMap) {
      player = new Player();
      myMap = userMap;
   }

   public void goToBeginning() {
      myMap.goToBeginning();
   }
   
   public void movePlayer(int adjIndex) {
      Situation[] adjacentSituations = myMap.getAdjArray();
      movePlayer(adjacentSituations[adjIndex]);
   }
   
   public void movePlayer(Situation adjSit) {
      if(!adjSit.getRequiredItems().isEmpty()) {
         int count = 0;
         for (int i = 0; i < player.getInventory().length; i++) {
            if (adjSit.getRequiredItems().contains(player.getInventory()[i])) {
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
   
   public void pickUpItem(Item item) {
      myMap.getCurrentSituation().removeItem(item);
      player.pickUpItem(item);
   }
   
  public void dropItem(Item item) {
      player.dropItem(item);
      myMap.getCurrentSituation().addItem(item);
   }

   public boolean isOver() {
      return myMap.isCleared();
   }
   
   /**
    * Makes the map
    */
   public Map makeMap1() {
      // beginning

      Situation beginning = new Situation("First Room", "You are in a small, dark room.");
      Weapon smashBall = new Weapon("Smash Ball", "Final smaaaaaaash! Hit Rate: 1%, 10d100 damage", 0.01, "10d100");
      beginning.addItem(smashBall);

      // nyan cat room

      HealItem rainbowPopTart = new HealItem("A rainbow poptart", "It crosses your mind that this could be nyan cat poop, but as the rainbowy scent wafts "
              + "towards you, you put the thought out of your mind.", 5);
      Vector<Item> dropNyan = new Vector<>();
      dropNyan.add(rainbowPopTart);
      dropNyan.add(rainbowPopTart.clone());

      Situation nyanCats = new Situation("Nyan Cat Attack!", "Some Nyan Cats seem to have made their home here.", dropNyan);

      Weapon rainbow = new Weapon("Rainbow Power", "Nyanyanyanyanyanyanya!", 0.7, "1d8");
      GameCharacter nyanCat = new GameCharacter(10, rainbow, "A Nyan Cat");
      Vector<GameCharacter> manyNyanCats = new Vector<GameCharacter>();

      manyNyanCats.add(nyanCat);
      manyNyanCats.add(nyanCat.clone());

      Fight nyanCatFight = new Fight(player, manyNyanCats);

      nyanCats.addFight(nyanCatFight);

      // apple room

      Vector<Item> apples = new Vector<>();
      Item apple = new HealItem("Apple", "A crunchy apple. Can be eaten.", 10);
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
      Item wheat = new HealItem("Wheat", "A grain of wheat.", 5);
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
      return s;
   }

   public static void main(String[] args) {
      Game myGame = new Game();
      System.out.println(myGame);
      myGame.movePlayer(0);
      System.out.println(myGame);
   }

}