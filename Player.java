import java.util.NoSuchElementException;

public class Player extends GameCharacter { 

   //CHANGE THESE DURING GAME DESIGN
   final static private int playerHp = 200; 
   final static private Weapon defaultWeapon = new Weapon("Stick", "A simple stick", 0.9, "3d10"); //change these values!
   final static private int maxInventorySize = 10;
   final static Item firstItem = new HealItem("Apple", "A crunchy apple. Can be eaten.", 5);
   
   private Item[] inventory;
   private int lastItemIndex;
   
   public Player() {
      super(playerHp, defaultWeapon, "Player");
      inventory = new Item[maxInventorySize];
      lastItemIndex = 0;
      inventory[lastItemIndex] = firstItem;
      
   }
   
   public void pickUpItem(Item newItem) throws FullInventoryException {
      try {
         //does make changes even if error is thrown? 
         inventory[lastItemIndex+1] = newItem;
         lastItemIndex++; 
      } catch (IndexOutOfBoundsException e) {
         throw new FullInventoryException();
      }
   }
   
   public Weapon pickUpWeapon(Weapon weaponToPickUp) {
      Weapon savedWeapon = weapon;
      weapon = weaponToPickUp;
      return savedWeapon;
   }
   
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
   
   public boolean inventoryIsEmpty() {
      return (lastItemIndex == -1);
   }
   
   
   public void useHealthItem(Item itemToUse) throws NoSuchElementException {
      dropItem(itemToUse);
      currentHP += itemToUse.getHp();
   }
   
   public Item[] getInventory() {
      return inventory;
   }
   
   public int getMaxInventorySize() {
      return maxInventorySize;
   }
   
   public boolean hasItem(Item item) {
      for (int i = 0; i <= lastItemIndex; i++) {
         if (inventory[i].equals(item)) {
            return true;
         }
      }
      return false;
   }
   
   public Item getItem(int index) {
     return inventory[index];
   }
   
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