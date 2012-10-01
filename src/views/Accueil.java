/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.UserActif;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

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
        JLabel icon = new JLabel(new ImageIcon("ressources/images/komodo.gif"));
        String name = this.user.getFullName();
        
        content.setBackground(Color.white);
        title.setText("<html><center>Bienvenue "+name+"<br>"+
                "Votre dernière connexion date du " +
                this.user.getLastLogin()+"</center></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);  
        title.setBorder(new EmptyBorder(30, 0, 30, 0));
        content.setLayout(new BorderLayout());
        content.add(title, BorderLayout.NORTH); 
        content.add(icon, BorderLayout.SOUTH);
        this.panel.add(content);
    }
}
