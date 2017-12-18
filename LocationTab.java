/**
 * LocationTab.java
 * This class provides the GUI for one tab of a tabbed pane. This tab contains the player character's current situation
 * and their options of where to go next.
 * @author Emma Shumadine, Lily Orth-Smith, Rachel Zhang (primary)
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class LocationTab extends JPanel 
{ 
  
  private JComboBox MoveCombo;
  private JButton GoButton;
  private JLabel DescriptionLabel, ErrorLabel;
  private JPanel TopPanel, LowerPanel;
  private String[] directions;
  private Game g;

  /**
   * Creates the tab
   * @param g the game associated with this tab
   */
  public LocationTab(Game g) //pass Game g
  { 
    setLayout(new GridLayout(2,0));
    this.g = g;
    
    DescriptionLabel = new JLabel (g.getCurrentDescription());
    ErrorLabel = new JLabel("");
    
    directions = g.getAdjNames();
    MoveCombo = new JComboBox (directions); 
    
    
    GoButton = new JButton("Go");
    
    TopPanel = new JPanel();
    TopPanel.setBackground(new Color(200, 200, 244));
    LowerPanel = new JPanel();
    TopPanel.add(DescriptionLabel); 
    LowerPanel.add (MoveCombo);
    LowerPanel.add (GoButton);
    LowerPanel.add (ErrorLabel);
    
    add(TopPanel);
    add(LowerPanel);
    
    GoButton.addActionListener(new ButtonListener());
  } 
  
  private class ButtonListener implements ActionListener
  {
    private int current; 
    public void actionPerformed (ActionEvent event)
    {
      
      current = MoveCombo.getSelectedIndex();
      
      if (current == 0)
        ErrorLabel.setText("Please select a place to go to.");
      else {
        
        try {
          ErrorLabel.setText("");
          g.movePlayer(current-1);
          if (g.hasFight()) {
            g.startFight();
          }
          DescriptionLabel.setText(g.getCurrentDescription());
          directions = g.getAdjNames();
          MoveCombo.removeAllItems();
          for (int j = 0; j < directions.length; j++)
            MoveCombo.addItem(directions[j]); 
          
        }
        catch (IllegalMoveException e) {
          ErrorLabel.setText("Not enough memes.");
        }
      }
    }
    
  }
  
 
  
 
  
  
} 
