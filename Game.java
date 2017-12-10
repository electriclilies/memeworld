/**
 * A class to play the game! by no means complete. 
 *
 *
 */

import java.util.*;

public class Game {
   
   Player player;
   Map myMap;
   
   public Game(String whichMap) {
   player = new Player(); 
      switch (whichMap) {
         case "Map1":
            myMap = makeMap1();
            break;
         default: 
            throw new MapNotFoundException();
      } 
   }
   
   public Game(Map userMap) {
      player = new Player();
      myMap = userMap;
   }
   
   public void movePlayer(int adjIndex) {
      myMap.changeSituation(adjIndex);
   }
   
   public void movePlayer(Situation adjSit) {
      myMap.changeSituation(adjSit);
   }
   
   public void pickUpItem(Item item) {
      myMap.getCurrentSituation().removeItem(item);
      player.pickUpItem(item);
   }
   
  public void dropItem(Item item) {
      player.dropItem(item);
      myMap.getCurrentSituation().addItem(item);
   }
   
   
   //my idea is to be able to have several default maps that the player can choose from
   
   /**
    * Makes the first map
    *
    *
    */
   public Map makeMap1() {
      //beginning
      Situation beginning = new Situation("First Room", "You are in a small, dark room", "The room looks dark. You can't quite see what's inside.");
      
      Item pebble = new Item("Smooth pebble.", "A pretty, smooth, blue pebble. Seemingly useless.");
      beginning.addItem(pebble);
      
      //nyancats
      Situation nyanCats = new Situation("Nyan Cat Attack!", "You are in a room flooded with Nyan Cats!", "There's a bright light through " 
                                       + "the doorway, but you can't quite figure out what's going on. You hear faint mewing, and see rainbow flashes."); 
      HealItem rainbowPopTart = new HealItem("A rainbow poptart", "It crosses your mind that this could be nyan cat poop, but as the rainbowy scent wafts "
                                         + "towards you, you put the thought out of your mind.", 12);
      nyanCats.addItem(rainbowPopTart);
      
      Weapon claws = new Weapon("Claws", "Slash slash", 0.2, "5d40");
      GameCharacter nyanCat = new GameCharacter(5, claws, "A Nyan Cat"); 
      Vector<GameCharacter> manyNyanCats = new Vector<GameCharacter>();
      
      manyNyanCats.add(nyanCat);
      manyNyanCats.add(nyanCat);
      manyNyanCats.add(nyanCat);
      manyNyanCats.add(nyanCat);
      manyNyanCats.add(nyanCat);
      manyNyanCats.add(nyanCat);
      
      Fight nyanCatFight = new Fight(player, manyNyanCats);
      
      nyanCats.addFight(nyanCatFight);
                                     
      
      Situation cena = new Situation("Cena", "A wild John Cena appears!", "A dark, mysterious oak door, tightly shut. No light comes out "
                                       + "of any cracks");
                                       
      
      Situation forest = new Situation("A wild wood", "You find yourself in a clearing in a wood. Apples are strewn on the ground.", "A cool wind blows, " 
                                     + "bringing the scent of crisp fall and leaves");
                                     
      Situation sword = new Situation("A sword on the ground", "In the middle of a meadow you see a clint of metal. As you approach, you see a sword " 
                                    + "lying on the ground.", "You see a wide open meadow, with a castle in the distance. Maybe you'll find something "
                                    + "here?"); 
      
        
    
      Map map1 = new Map(beginning);
      map1.addVertex(nyanCats);
      map1.addVertex(cena);
      map1.addVertex(forest);
      map1.addVertex(sword);
      
      map1.addEdge(beginning, nyanCats);
      map1.addEdge(beginning, cena);
      map1.addEdge(nyanCats, forest);
      map1.addEdge(nyanCats, sword);
      map1.addEdge(forest, sword);
      return map1;
           
      }
      
      public static void main(String[] args) {
         Game myGame = new Game("Map1");
         
      }

}