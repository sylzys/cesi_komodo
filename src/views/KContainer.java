/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cesi_komodo.views;

import cesi_komodo.controllers.Utilisateur;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author sylv
 */
public abstract class KContainer {

    protected JPanel panel;
    protected Utilisateur user;

    public KContainer() {
        this.panel = new JPanel();
        this.user = new Utilisateur();
        this.panel.setBackground(Color.WHITE);
    }

    protected JPanel getPanel() {
        return this.panel;
    }
    protected abstract void initPanel();
}
