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
public class ReplicView extends KContainer {
    public static JProgressBar maBarre;
    public ReplicView()
    {
        super();
        initPanel();
    }
    public JPanel stateChanged(int value, String title)
    {
        JPanel content = new JPanel();
        JPanel center = new JPanel();
        JLabel icon = new JLabel(new ImageIcon("ressources/images/replic.png"));
        JLabel lblTitle = new JLabel("<html><center><h2>Réplication de la base de données</h2><br><br><br><p color=blue>"+title+"</p></center></html>");
        lblTitle.setBorder(new EmptyBorder(0, 200, 0, 200));
        maBarre = new JProgressBar(0,100);
        maBarre.setString("Progression : "+value+"%");
        maBarre.setStringPainted(true);
        icon.setBorder(new EmptyBorder(40, 0, 100, 0));
        center.setPreferredSize(new Dimension(500,50));
        maBarre.setPreferredSize(new Dimension(300,20));
        center.add(maBarre, BorderLayout.CENTER);
        content.setLayout(new BoxLayout(content, BoxLayout.LINE_AXIS));
        content.setLayout(new BorderLayout());
        content.add(lblTitle, BorderLayout.NORTH); 
        content.add(center, BorderLayout.CENTER);
        content.add(icon, BorderLayout.SOUTH); 
        content.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
        maBarre.setValue(value);    
        this.panel.removeAll();
        this.panel.add(content);
        return panel;
    }
    @Override
    protected void initPanel() {
//        JPanel content = new JPanel();
//        JLabel icon = new JLabel(new ImageIcon("ressources/images/replic.png"));
//        JLabel lblTitle = new JLabel("<html><center><h2>Réplication de la base de données</h2><br><br><br><p color=blue>Lancement de la réplication</p></center></html>");
//        maBarre = new JProgressBar(0,100);
//        lblTitle.setBorder(new EmptyBorder(0, 0, 20, 0));
//        maBarre.setString("Progression : 0%");
//        maBarre.setStringPainted(true);
//        content.setBackground(Color.lightGray);
//        icon.setBorder(new EmptyBorder(40, 0, 0, 0));
//        //content.setBackground(Color.white);
//        content.setLayout(new BorderLayout());
//        content.add(lblTitle, BorderLayout.NORTH); 
//        content.add(maBarre, BorderLayout.CENTER);
//        content.add(icon, BorderLayout.SOUTH); 
//        maBarre.setValue(0);    
//        this.panel.removeAll();
//        this.panel.add(content);
    }
}
