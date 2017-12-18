/**
 * ItemsTab.java
 * This class provides the GUI for one tab of a tabbed pane. This tab contains the current items available in the situation.
 * @author Emma Shumadine, Lily Orth-Smith, Rachel Zhang (primary)
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class ItemsTab extends JPanel 
{ 
  private Game g;
  private JLabel ContainsLabel, ItemListLabel, DescriptionLabel;
  private JComboBox ItemCombo;
  private JButton PickUpButton;
  private JPanel TopPanel, LowerPanel;
  private Vector<Item> roomItems;
  private int selection;


  /**
   * Creates the tab
   * @param g the game associated with this tab
   */
  public ItemsTab(Game g) 
  { 
    this.g = g;
    setLayout(new GridLayout(2,0)); 
    
    ContainsLabel = new JLabel ("This room contains: "); 
    
    DescriptionLabel = new JLabel("");
    roomItems = g.getRoomItems();
    
    PickUpButton = new JButton("Pick Up");
    
    ItemCombo = new JComboBox(roomItems);
    ItemCombo.setModel(new DefaultComboBoxModel(roomItems));
    
    TopPanel = new JPanel();
    TopPanel.setBackground(new Color(200, 100, 0));
    LowerPanel = new JPanel();
    
    TopPanel.add(ContainsLabel); 
    TopPanel.add(ItemCombo);
    TopPanel.add(PickUpButton);
    LowerPanel.add(DescriptionLabel);
    
    add(TopPanel);
    add(LowerPanel);
    
    ItemCombo.addActionListener(new ComboListener());
    PickUpButton.addActionListener(new ButtonListener());
    addMouseListener(new UpdateListener());

  }
  
  private class ComboListener implements ActionListener
  {
    private int current;
    public void actionPerformed (ActionEvent event)
    {
      current = ItemCombo.getSelectedIndex();
      selection = current;
      if (current >= 0) {
      DescriptionLabel.setText(roomItems.get(current).getDescription());
      }
      
    }
  }
  
  private class ButtonListener implements ActionListener
  {
    private int current;
    public void actionPerformed (ActionEvent event)
    {
      
      current = ItemCombo.getSelectedIndex();
      if (roomItems.size() <= 0) {
        DescriptionLabel.setText("No items in this room.");
      }
      else {
        try {
          ItemCombo.setSelectedIndex(0);
          g.pickUpItem(roomItems.get(current));
          update();
        } catch (FullInventoryException e) {
          DescriptionLabel.setText("Inventory full.");
        }
        
      }
    }
  }

  /**
   * Updates the available items
   */
  private void update() {

    roomItems = g.getRoomItems();
    ItemCombo.setModel(new DefaultComboBoxModel(roomItems));
    if (roomItems.size() > 0) {
      if (selection >= roomItems.size())
       selection = roomItems.size() -1;
      ItemCombo.setSelectedIndex(selection);
    }
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
