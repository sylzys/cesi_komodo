/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cesi_komodo.views;

import cesi_komodo.controllers.Utilisateur;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sylv
 */
public class Recherche extends KContainer {
    JLabel title = new JLabel ("PANNEAU RECHERCHE");
    public Recherche(Utilisateur user) {
    super();
    this.user = user;
    initPanel();
    }

    @Override
    protected
    void initPanel() {
        JPanel content = new JPanel();

        content.setLayout(new BorderLayout());
        content.add(title, BorderLayout.CENTER);      
        this.panel.add(content);
    }
}
