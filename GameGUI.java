import javax.swing.*; 
import java.awt.*;
 
public class GameGUI 
{ 

   public static void main (String[] args) 
   { 
      JFrame frame = new JFrame ("Layout Manager Demo"); 
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); 

      //new game g; pass g as a parameter for all 3
      JTabbedPane tp = new JTabbedPane(); 
      tp.addTab ("Current Location", new LocationTab()); 
      tp.addTab ("Character Info", new InfoTab()); 
      tp.addTab ("Pick up Items", new ItemsTab()); 
      tp.setPreferredSize(new Dimension(900, 600));

 
      frame.getContentPane().add(tp); 
 
      frame.pack(); 
      frame.setVisible(true); 
   } 
} 
