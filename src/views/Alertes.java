/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.UserActif;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author sylv
 */
public class Alertes extends KContainer{
    JLabel title = new JLabel ("PANNEAU ALERTES");
    public Alertes (UserActif user) {
        super();
        this.user = user;
        initPanel();
    }   

    @Override
    protected
    void initPanel() {
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        
        JScrollPane listScroller = new JScrollPane();
        listScroller.setBorder(javax.swing.BorderFactory.createTitledBorder("Alertes"));
        listScroller.setPreferredSize(new Dimension(800, 600));
        listScroller.setBackground(new java.awt.Color(255, 255, 255));
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.Y_AXIS));
        buttonPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPane.setBackground(new java.awt.Color(255, 255, 255));
        buttonPane.add(new JLabel("foo"));
        buttonPane.add(new JLabel("bar"));
        buttonPane.add(new JButton("slop"));
        JPanel jp = new JPanel();
        jp.setPreferredSize(new Dimension(730, 70));
        jp.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        JPanel jp2 = new JPanel();
        jp2.setPreferredSize(new Dimension(730, 70));
        jp2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buttonPane.add(jp);
        buttonPane.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPane.add(jp2);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 600)));
        buttonPane.add(new JLabel("kikoo lol"));
        
        listScroller.setViewportView(buttonPane);
        //listPane.add(listScroller, BorderLayout.PAGE_START);
        
        this.panel.add(listScroller);
    }
}
