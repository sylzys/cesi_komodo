//package classes;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Graphics;
//import javax.swing.ImageIcon;
//import javax.swing.JToolBar;
//
//public class ToolBar extends JToolBar{
//
//  public ToolBar() {
//    super();
//    setBackground(Color.white);
//    setOpaque(true);
//    setPreferredSize(new Dimension(54,54));
//    setMinimumSize(new Dimension(54,54));
//    setMaximumSize(new Dimension(54,54));
//    setSize(new Dimension(54,54));
//  }
//
//  public void paintComponent(Graphics g) {
//    super.paintComponent(g);
//    Dimension size = this.getSize();
//    ImageIcon image = DefaultAction.createImageIcon("/com/aaa/resources/tabback");
//    g.drawImage(image.getImage(), 0,0, size.width, size.height, this);
//  }
//}