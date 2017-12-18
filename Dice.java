/**
 * Dice.java
 * This class offers methods for D&D-style dice.
 * @author Emma Shumadine
 * */

import java.util.*;

public class Dice {
  private int sides;
  private int numDice;
  private static Random rand = new Random();
  
  /**
   * Creates a Dice object holding the number and type of dice
   * @param diceString a string containing the dice information, ie "1d20"
   * */
  public Dice(String diceString) {
    String[] array = diceString.split("d");
    sides = Integer.parseInt(array[1]);
    numDice = Integer.parseInt(array[0]);
  }
  
  /**
   * Rolls the dice and returns the result
   * @return the result of the roll
   * */
  public int roll() {
    int total = 0;
    for (int i = 1; i <= numDice; i++) {
      total += rand.nextInt(sides + 1);
    }
    return total;
  }
  
  /**
   * Returns a string representation of the dice
   * @return a string representation of the dice
   * */
  public String toString() {
    return numDice + "d" + sides;
  }
  
  public static void main(String[] args) {
    Dice d1 = new Dice("1d20");
    System.out.println(d1);
    System.out.println(d1.roll());
    Dice d2 = new Dice("3d10");
    Dice d3 = new Dice("2d6");
    System.out.println(d2);
    System.out.println(d3);
    System.out.println(d2.roll());
  }
}