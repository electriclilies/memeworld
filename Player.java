/**
 * Player.java
 * @author Emma Shumadine, Lily Orth-Smith, Rachel Zhang
 * */

import java.util.NoSuchElementException;

public class Player extends GameCharacter { 

   final static private int playerHp = 100;
   final static private Weapon defaultWeapon = new Weapon("Stick", "A simple stick. Hit Rate: 90%, 1d4 damage", 0.9, "1d4");
   final static private int maxInventorySize = 8;
   final static Item firstItem = new HealItem("Apple", "A crunchy apple. Can be eaten.", 10);

   private Item[] inventory;
   private int lastItemIndex;

   /**
    * Creates a player with an empty inventory
    */
   public Player() {
      super(playerHp, defaultWeapon, "Player");
      inventory = new Item[maxInventorySize];
      lastItemIndex = 0;
      inventory[lastItemIndex] = firstItem;
      
   }

   /**
    * Adds an item to the inventory
    * @param newItem the item to add
    * @throws FullInventoryException if the inventory is full
    */
   public void pickUpItem(Item newItem) throws FullInventoryException {
      try {
         //does make changes even if error is thrown? 
         inventory[lastItemIndex+1] = newItem;
         lastItemIndex++; 
      } catch (IndexOutOfBoundsException e) {
         throw new FullInventoryException();
      }
   }

   /**
    * Switches the player's weapon
    * @param weaponToPickUp the weapon to be switched
    * @return the weapon the player was previously using
    */
   public Weapon pickUpWeapon(Weapon weaponToPickUp) {
      Weapon savedWeapon = weapon;
      weapon = weaponToPickUp;
      return savedWeapon;
   }

   /**
    * Drops the item from the player's inventory
    * @param itemToDrop the item to remove
    * @return the removed item
    * @throws NoSuchElementException if the item doesn't exist in the inventory
    */
   public Item dropItem(Item itemToDrop) throws NoSuchElementException {
     
      int nullIndex = -1;
      for (int i = 0; i <= lastItemIndex; i++) {
         if (itemToDrop.equals(inventory[i])) {
            inventory[i] = null;
            nullIndex = i;
            break;
         }
      }
      if (nullIndex == -1) {
         throw new NoSuchElementException();
      }
      
      for (int i = nullIndex; i < inventory.length-1; i++) {
         inventory[i] = inventory[i+1];
      }
      inventory[inventory.length-1] = null;
      lastItemIndex--;
      
      return itemToDrop;
   }

   /**
    * Returns true if the player's inventory is empty
    * @return true if the player's inventory is empty
    */
   public boolean inventoryIsEmpty() {
      return (lastItemIndex == -1);
   }

   /**
    * Uses a health item to heal the player, dropping the item in the process
    * @param itemToUse the item to use
    * @return the amount healed
    * @throws NoSuchElementException if the item doesn't exist in the player's inventory
    */
   public int useHealthItem(Item itemToUse) throws NoSuchElementException {
      dropItem(itemToUse);
      currentHP += itemToUse.getHp();
      return itemToUse.getHp();
   }

   /**
    * Returns the player's inventory
    * @return the inventory
    */
   public Item[] getInventory() {
      return inventory;
   }

   /**
    * Returns the maximum inventory size
    * @return the maximum inventory size
    */
   public int getMaxInventorySize() {
      return maxInventorySize;
   }

   /**
    * Returns true if the player has the item in their inventory
    * @param item the item
    * @return true if the player has the item in their inventory
    */
   public boolean hasItem(Item item) {
      for (int i = 0; i <= lastItemIndex; i++) {
         if (inventory[i].equals(item)) {
            return true;
         }
      }
      return false;
   }

   /**
    * Returns the item at the index in the player's inventory
    * @param index the index of the item in the inventory
    * @return the item at the index
    */
   public Item getItem(int index) {
     return inventory[index];
   }

   /**
    * Returns a string representation of the player
    * @return a string representation of the player
    */
   public String toString() {
      String inventoryStr;
      if (lastItemIndex == -1) {
         inventoryStr = "[]";
      } else { 
         inventoryStr = "[" + inventory[0];
         for (int i = 1; i <= lastItemIndex; i++) {
            inventoryStr += ", " + inventory[i];
         }
         inventoryStr += "]";
      }
   
      return (super.toString() + " Inventory: " + inventoryStr);
   }
   
   public static void main(String[] args) {
      Player myPlayer = new Player(); 
      HealItem apple = new HealItem("Apple", "A crunchy apple. Can be eaten.", 5);

      System.out.println("myPlayer with empty inventory: " + myPlayer);
      Item rock = new Item("Rock", "Can be thrown.");
      for (int i = 1; i < 10; i++) { //player has default item so can only add 9 items before inventory is full
         myPlayer.pickUpItem(rock);
      }
      System.out.println("myPlayer with full inventory: " + myPlayer);
      try {
         myPlayer.pickUpItem(rock);
      } catch (FullInventoryException e) {
         System.out.println("Player's inventory is full, could not pick up another rock");
      }
      
      System.out.println("Does Player have an apple? (true): " + myPlayer.hasItem(apple));
      System.out.println("Does Player have a rock? (true): " + myPlayer.hasItem(rock));
      
      
      System.out.println("Dropped: " + myPlayer.dropItem(apple));
      System.out.println("Does Player have an apple? (false): " + myPlayer.hasItem(apple));
      
      while (!myPlayer.inventoryIsEmpty()) {
         System.out.println("Removed: " + myPlayer.dropItem(rock));
      }
      
      System.out.println(myPlayer);
   }
   
      
}