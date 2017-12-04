public class Item {
  
  protected String name;
  protected String description;
  
  public Item(String n, String d) {
    name = n;
    description = d;
  }
  
  public String getName() {
    return name;
  }
  
  public String getDescription() {
    return description;
  }
  
  public void setName(String n) {
    name = n;
  }
  
  public void setDescription(String d){
    description = d;
  }
  
  public String toString() {
    return name + ": " + description;
  }
  
  public static void main(String[] args) {
    Item rock = new Item("Rock", "Does nothing.");
    System.out.println(rock);
    System.out.println("Testing rock.getName()-->" + rock.getName());
    System.out.println("Testing rock.getDescription()-->" + rock.getDescription());
    System.out.println("Setting rock name to 'Pebble'");
    rock.setName("Pebble");
    System.out.println("Setting rock description to 'Can be thrown.'");
    rock.setDescription("Can be thrown.");
    System.out.println(rock);
    
  }
}
