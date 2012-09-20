/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cesi_komodo.views;

import cesi_komodo.controllers.Utilisateur;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sylv
 */
public class Accueil extends KContainer {
    JLabel title = new JLabel ();
    public Accueil(Utilisateur user) {
    super();
    this.user = user;
    initPanel();
    }

    @Override
    protected
    void initPanel() {
        JPanel content = new JPanel();
        JLabel icon = new JLabel(new ImageIcon("ressources/images/komodo.jpg"));
        String name = this.user.getFullName();
        title.setText("Bienvenue "+name);
        content.setLayout(new BorderLayout());
        content.add(title, BorderLayout.NORTH);  
        content.add(icon, BorderLayout.CENTER);
        this.panel.add(content);
    }
}
