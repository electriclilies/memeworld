/**
 * GameGUI.java
 * Runs the full GUI for the game
 * @author Emma Shumadine, Lily Orth-Smith, Rachel Zhang (primary)
 */

import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
 
public class GameGUI 
{ 

   public static void main (String[] args) 
   { 
      JFrame frame = new JFrame ("Layout Manager Demo"); 
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); 

      Game g = new Game();
      JTabbedPane tp = new JTabbedPane(); 
      tp.addTab ("Current Location", new LocationTab(g)); 
      tp.addTab ("Character Info", new InfoTab(g)); 
      tp.addTab ("Pick up Items", new ItemsTab(g)); 
      tp.setPreferredSize(new Dimension(700, 200));


 
      frame.getContentPane().add(tp); 
 
      frame.pack(); 
      frame.setVisible(true); 
   } 
} 
