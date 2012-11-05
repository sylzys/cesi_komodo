/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.UserActif;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import models.ModelesTables;

/**
 *
 * @author sylv
 */
public class Accueil extends KContainer {
    JLabel title = new JLabel ();
    public Accueil(UserActif user) {
    super();
    this.user = user;
    initPanel();
    }

    @Override
    protected
    void initPanel() {
        JPanel content = new JPanel();
        content.setOpaque(false);
        JLabel icon = new JLabel(new ImageIcon("ressources/images/komodo.gif"));
        String name = this.user.getFullName();
        
        //content.setBackground(Color.white);
        title.setText("<html><center>Bienvenue "+name+"<br>"+
                "Votre dernière connexion date du " +
                this.user.getLastLogin()+"</center></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);  
        title.setBorder(new EmptyBorder(50, 0, 50, 0));

         title.setHorizontalAlignment(SwingConstants.CENTER);  
        
        Font f = new Font("Euphemia", Font.PLAIN, 14);
        title.setFont(f); 
        title.setPreferredSize(new Dimension(1000, 40));
        
        
        this.panel.add(title, BorderLayout.PAGE_START);
        this.panel.add(icon, BorderLayout.PAGE_END);
       // this.panel.add(content, BorderLayout.PAGE_END);
    }
}
