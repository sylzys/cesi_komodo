/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.UserActif;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;

/**
 *
 * @author sylv
 */
public class Evenement extends KContainer {

    JLabel title = new JLabel("PANNEAU EVENEMENT");
    JButton button = new JButton();
    JTextField jTextField1 = new javax.swing.JTextField();
    JLabel jLabel1 = new javax.swing.JLabel();
    JLabel jLabel2 = new javax.swing.JLabel();
    JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
    JTextArea jTextArea1 = new javax.swing.JTextArea();
    JLabel jLabel3 = new javax.swing.JLabel();
    JFormattedTextField jFormattedTextField1 = new javax.swing.JFormattedTextField();
    JFormattedTextField jFormattedTextField2 = new javax.swing.JFormattedTextField();
    JLabel jLabel4 = new javax.swing.JLabel();
    JFormattedTextField jFormattedTextField3 = new javax.swing.JFormattedTextField();
    JFormattedTextField jFormattedTextField4 = new javax.swing.JFormattedTextField();

    public Evenement(UserActif user) {
        super();
        this.user = user;
        initPanel();
    }

    @Override
    protected void initPanel() {
        JPanel content = new JPanel();

        content.setLayout(new BorderLayout());
        content.add(title, BorderLayout.CENTER);
        
        button.setText("Ajouter");
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });
        content.add(button, BorderLayout.PAGE_END);
        
        jLabel1.setText("Intitulé");
        content.add(jLabel1, BorderLayout.WEST);
        jLabel2.setText("Description");
        content.add(jLabel2, BorderLayout.EAST);
        jLabel3.setText("Début");
        content.add(jLabel3, BorderLayout.SOUTH);
        jLabel4.setText("Fin");
        content.add(jLabel4, BorderLayout.NORTH);
        
        
        this.panel.add(content);
    }
    
    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
}
