/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
/**
 *
 * @author suly
 */
public class ProgressBarAtt extends KContainer {
    public static JProgressBar maBarre;
    public ProgressBarAtt()
    {
        super();
        initPanel();
    }
    public JPanel stateChanged(int value, String title)
    {
        JPanel content = new JPanel();
        JLabel icon = new JLabel(new ImageIcon("ressources/images/replic.png"));
        JLabel lbl = new JLabel(title);
        maBarre = new JProgressBar(0,100);
        maBarre.setString("Progression : "+value+"%");
        maBarre.setStringPainted(true);
        content.setBackground(Color.white);
        content.setLayout(new BorderLayout());
        content.add(lbl, BorderLayout.NORTH); 
        content.add(maBarre, BorderLayout.CENTER);
        content.add(icon, BorderLayout.SOUTH); 
        maBarre.setValue(value);
        
        this.panel.removeAll();
        this.panel.add(content);
        return panel;
    }
    @Override
    protected void initPanel() {
        JPanel content = new JPanel();
        maBarre = new JProgressBar(0,99);
        maBarre.setSize(1000, 500);
        maBarre.setString("Progression : ");
        maBarre.setStringPainted(true);
        content.setBackground(Color.white);
        content.setLayout(new BorderLayout());
        content.add(maBarre, BorderLayout.CENTER); 
        maBarre.setValue(0);
        this.panel.add(content);
    }
}
