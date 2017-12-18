/**
 * InfoTab.java
 * This class provides the GUI for one tab of a tabbed pane. This tab contains the player character's current state.
 * @author Emma Shumadine, Lily Orth-Smith, Rachel Zhang (primary)
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class InfoTab extends JPanel 
{ 
  
  private Game g;
  private JLabel HealthLabel, ItemsLabel, DescriptionLabel, WeaponLabel, HeaderLabel;
  private JComboBox InventoryCombo;
  private JButton UseButton, DropButton;
  private JPanel TopPanel, ListPanel, DescriptionPanel, ActionPanel;
  private Vector<Item> inventory;
  private int selection;

  /**
   * Creates the tab
   * @param g the game associated with this tab
   */
  public InfoTab(Game g) 
  { 
    //setBackground (Color.green); 
    this.g = g;
    setLayout(new BorderLayout());
    
    TopPanel = new JPanel();
    ListPanel = new JPanel();
    DescriptionPanel = new JPanel();
    ActionPanel = new JPanel();
    
    HealthLabel = new JLabel ("<html>" + g.getPlayerHealth()+ "</html>"); 
    inventory = g.getInventory();
    //System.out.println(inventory.get(0) + "**"); //remove
    String strInventory = "<html><br/>";
    for (Item i: inventory)
      strInventory += i.toString() + "<br/>";
    strInventory += "</html>";
    ItemsLabel = new JLabel(strInventory);
    DescriptionLabel = new JLabel("");
    WeaponLabel = new JLabel(g.getWeapon());
    HeaderLabel = new JLabel("Current Items:");
    
    InventoryCombo = new JComboBox(inventory);
    
    UseButton = new JButton("Use");
    DropButton = new JButton("Drop");

    TopPanel.setLayout(new BorderLayout());
    TopPanel.add(HealthLabel, BorderLayout.WEST);
    TopPanel.add(WeaponLabel, BorderLayout.EAST);
    //TopPanel.setBackground(Color.green);
    
    ListPanel.add(HeaderLabel);
    ListPanel.add(ItemsLabel);
    
    DescriptionPanel.add(DescriptionLabel);
    
    ActionPanel.add(InventoryCombo);
    ActionPanel.add(UseButton);
    ActionPanel.add(DropButton);
    
    add(TopPanel, BorderLayout.NORTH);
    add(ListPanel, BorderLayout.WEST);
    add(DescriptionPanel, BorderLayout.SOUTH);
    add(ActionPanel, BorderLayout.CENTER);


    
    InventoryCombo.addActionListener(new ComboListener());
    UseButton.addActionListener(new UseListener());
    addMouseListener (new UpdateListener());
    DropButton.addActionListener(new DropListener());
    
  }

  private class ComboListener implements ActionListener
  {
     private int current;
     public void actionPerformed (ActionEvent event)
     {
       current = InventoryCombo.getSelectedIndex(); 
       selection = current;
      if (current >=0 ) {
       DescriptionLabel.setText(inventory.get(current).getDescription());
       }
       
     }
   }
   private class UseListener implements ActionListener {
     private int current;
     public void actionPerformed (ActionEvent event) {
       
       current = InventoryCombo.getSelectedIndex();
       if (!(inventory.size() == 0)) {
         Item temp = inventory.get(current).clone();
       DescriptionLabel.setText(g.useItem(temp));
       HealthLabel.setText("<html>" + g.getPlayerHealth()+ "</html>");
       //reset inventorylabel
       }
     }
   }
   private class DropListener implements ActionListener {
     private int current;
     public void actionPerformed (ActionEvent event) {
       
       current = InventoryCombo.getSelectedIndex();
       Item item = inventory.get(current);
       g.dropItem(item);
       DescriptionLabel.setText(item.toString() + " dropped.");
       update();
     }
   }

  /**
   * Updates the player's information
   */
  private void update() {
     inventory = g.getInventory();
     InventoryCombo.setModel(new DefaultComboBoxModel(inventory));
     if (inventory.size() > 0 ) {
     if (selection >= inventory.size())
       selection = inventory.size() -1;
     InventoryCombo.setSelectedIndex(selection);
     }
     HealthLabel.setText(g.getPlayerHealth());
     TopPanel.setBackground(g.getHealthColor());
     String strInventory = "<html><br/>";
     for (Item i: inventory)
       strInventory += i.toString() + "<br/>";
     strInventory += "</html>";
     ItemsLabel.setText(strInventory);      
     WeaponLabel.setText(g.getWeapon());
   }

   
   private class UpdateListener implements MouseListener
  {
    public void mouseEntered (MouseEvent event) {
      update();
    }
    public void mouseClicked (MouseEvent event) {
      update();
    }
    public void mouseExited (MouseEvent event) {}
    public void mousePressed (MouseEvent event) {}
    public void mouseReleased (MouseEvent event) {}
    
   }
} 

