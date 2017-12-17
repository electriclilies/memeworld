import java.util.*;
//import java.io.*;
public class Map extends AdjListsGraph<Situation> {
   
   private Situation currentSituation;
   private Situation lastSituation;
   private Situation beginning;
   private Situation bossRoom;

   public Map(Situation initialSituation, Situation boss) {
      super();
      this.addVertex(initialSituation);
      this.addVertex(boss);
      currentSituation = initialSituation;
      beginning = initialSituation;
      bossRoom = boss;
   }

   public boolean isCleared() {
      for(GameCharacter enemy: bossRoom.getFight().getEnemies()) {
         if(enemy.getCurrentHP() != 0) {
            return false;
         }
      }
      return true;
   }
   
   /** Changes lastSituation to currentSituation and currentSitatuion to newSituation, throws IllegalMoveException if newSituation is not an adjacent situation
    *  @param newSituation situation to set currentSituation to
    */
   public void changeSituation (Situation newSituation) throws IllegalMoveException {
      if (isAdjacent(newSituation)) {
         lastSituation = currentSituation;
         currentSituation = newSituation;
         if(!lastSituation.equals(bossRoom)) {
            lastSituation.reset();
         }
      } else {
         throw new IllegalMoveException();
      }
   }

   /**
    * Sets the current situation to the beginning
    */
   public void goToBeginning() {
      currentSituation = beginning;
   }

   /** Gets the situation in the adjacent situations at index adjacencyIndex, and changes to that
    *  @param adjacencyIndex index in the array of ADJACENT situations (NOT the index of the vertex in the graph)
    */
   public void changeSituation(int adjacencyIndex) throws IllegalMoveException {
      Situation[] adjacentSituations = getAdjArray();
      changeSituation(adjacentSituations[adjacencyIndex]);
   }
   
   
   /** Returns true if the given situation is adjacent to current situation
    * @param s situation to check
    * @return whether the situation is adjacent to currentSituation
    */
   public boolean isAdjacent(Situation s) {
      LinkedList<Situation> adj = getAdjacent();
      return adj.contains(s);
   }
   
   
   /** Gets a LinkedList of the adjacent situations
    * @return adjacent situations
    */
   public LinkedList<Situation> getAdjacent() {
      return getPredecessors(currentSituation);
   }
   
   /** Gets an array of the adjacent situations
    * @return adjacent situations
    */
   public Situation[] getAdjArray() {
      LinkedList<Situation> adjacentSituations = getAdjacent(); 
      Situation[] adjArray = new Situation[adjacentSituations.size()];
      adjArray = adjacentSituations.toArray(adjArray);
      return adjArray;
   }
   
   /** Gets an array of the names of the adjacent situations
    * @return names of the adjacent situations
    */
   public String[] getAdjNames() {
      LinkedList<Situation> adjacent = getAdjacent(); 
      String[] names = new String[adjacent.size()];
      
      //if last situation is null, you can't go back, so won't put Go Back as an option
      if (lastSituation == null) {
         for (int i = 0; i < names.length; i++) {
            String name = adjacent.get(i).getName();
            names[i] = adjacent.get(i).getName();
         }
      //if it isn't null, replace lastSituation's name with "Go back" 
      } else {
      
         for (int i = 0; i < names.length; i++) {
            String name = adjacent.get(i).getName();
            if (name.equals(lastSituation.getName())) {
               names[i] = "Go back";
            } else {
               names[i] = adjacent.get(i).getName();
            }
         }
      }
      
      return names; 
   }
   
   /** Gets an array of the descriptions of the adjacent situations
    * @return descriptions of the adjacent situations
    */
   public String[] getAdjDescriptions() {
      LinkedList<Situation> adjacent = getAdjacent(); 
      String[] descriptions = new String[adjacent.size()];
      
      for (int i = 0; i < descriptions.length; i++) {
         String description = adjacent.get(i).getDescription();
         descriptions[i] = description;
      }
      
      return descriptions; 
  }

   /**
    * Returns the situation containing the boss fight
    * @return the situation containing the boss fight
    */
  public Situation getBossRoom() {
      return bossRoom;
  }

   /** Gets the current situation
    * @return the current situation 
    */
   public Situation getCurrentSituation() {
      return currentSituation;
   }
   
   //not sure how to deal w case where lastSituation is null
   /** Gets the last situation
    * @return the last situation 
    */
   public Situation getLastSituation() {
      return lastSituation; 
   }

   /** Provides a String representation of the Map Object
    * @return a String representation of the Map
    */
   public String toString() {
      String mapString = "Current Situation: " + currentSituation;
      
      if (lastSituation != null) {
         mapString += "\nLast Situation: " + lastSituation; 
      }
      
      mapString += "\n" + super.toString(); 
      return mapString; 
   }
   
   //is there a way to do this without making addEdge throw errors too? 
   /* 
    * Graph is undirected so there can never be any arcs. So I am having them throw UnsupportedOperationExceptions
    */ 
   
   /*
   public void addArc(Situation s1, Situation s2) throws UnsupportedOperationException {
      throw new UnsupportedOperationException(); 
   }
   
   public void removeArc(Situation s1, Situation s2) throws UnsupportedOperationException {
      throw new UnsupportedOperationException();
   }
   
   public boolean isArc(Situation s1, Situation s2) throws UnsupportedOperationException {
      throw new UnsupportedOperationException();
   }
   
   */
   
   public static void main (String[] args) {
      Situation s1 = new Situation("s1", "the first situation"); 
      Situation s2 = new Situation("s2", "the 2 situation"); 
      Situation s3 = new Situation("s3", "the 3 situation"); 
      Situation s4 = new Situation("s4", "the 4 situation"); 
      
      Map myMap = new Map(s1, s4);
      System.out.println(myMap); 
      myMap.addVertex(s2);
      myMap.addVertex(s3);
      
      System.out.println(); 
      /*
      System.out.println("Checking that you can't do anything with arcs!"); 
      try {
         myMap.addArc(s1, s2); 
      } catch (UnsupportedOperationException e) {
         System.out.println("You tried to add an arc and couldn't, as expected"); 
      }
      
      try {
         myMap.removeArc(s1, s2); 
      } catch (UnsupportedOperationException e) {
         System.out.println("You tried to remove an arc and couldn't, as expected"); 
      }
      
      try {
         myMap.isArc(s1, s2); 
      } catch (UnsupportedOperationException e) {
         System.out.println("You tried check if there is an arc and couldn't, as expected"); 
      }*/
      
      myMap.addEdge(s1, s2); 
      myMap.addEdge(s2, s3); 
      myMap.addEdge(s1, s4);
      
      System.out.println(myMap);
      
      
      System.out.println("Testing isAdjacent())");
      System.out.println("s2 (true): " + myMap.isAdjacent(s2));
      System.out.println("s3 (false): " + myMap.isAdjacent(s3));
      System.out.println("s4 (true): " + myMap.isAdjacent(s4));
     
      System.out.println("Testing getAdjacent()");
      System.out.println(myMap.getAdjacent());
      
      System.out.println("Testing getAdjArray())");
      System.out.println(myMap.getAdjArray());
      
      System.out.println("Testing getAdjNames())"); 
      String[] adjNames = myMap.getAdjNames();
      for (int i=0; i<adjNames.length; i++) {
         System.out.println(adjNames[i]);
      }
      
      System.out.println("Testing getAdjDescriptions())");
      String[] adjDescriptions = myMap.getAdjDescriptions();
      for (int i=0; i<adjDescriptions.length; i++) {
         System.out.println(adjDescriptions[i]);
      }
      
      System.out.println("Testing getCurrentSituation()");
      System.out.println(myMap.getCurrentSituation());
      System.out.println("Testing getLastSituation()");
      System.out.println(myMap.getLastSituation());
      
      System.out.println("Trying to change situation to s3 (should throw error) ");
      try {
         myMap.changeSituation(s3);
      } catch (IllegalMoveException e) {
         System.out.println("Couldn't move to s3 because of " + e); 
      }
      
      System.out.println("Move to s2: ");
      myMap.changeSituation(s2); 
      System.out.println("Current Situation (s2): " + myMap.getCurrentSituation()); 
      System.out.println("Last Situation (s1): " + myMap.getLastSituation());
      
      System.out.println("Names of adjacent situations (should have a go back: "); 
       
      String[] adjNames2 = myMap.getAdjNames();
      for (int i=0; i<adjNames2.length; i++) {
         System.out.println(adjNames2[i]);
      }
    
      System.out.println("Names of adjacent descriptions (should NOT have a go back): "); 
      String[] adjDescriptions2 = myMap.getAdjDescriptions();
      for (int i=0; i<adjDescriptions2.length; i++) {
         System.out.println(adjDescriptions2[i]);
      }
      
      System.out.println("Testing changeSituation using index (index = 1, current should go to s3) "); 
      myMap.changeSituation(1);
      System.out.println("Current situation: " + myMap.getCurrentSituation());
      System.out.println("Last situation: " + myMap.getLastSituation()); 
   }
   
}