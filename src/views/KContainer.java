/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import classes.BackgroundPanel;
import controllers.UserActif;
import javax.swing.JPanel;

/**
 *
 * @author sylv
 */
public abstract class KContainer {

    protected JPanel panel;
    protected UserActif user;

    public KContainer() {
        this.panel = new BackgroundPanel();
       
    }

    public JPanel getPanel() {
        return this.panel;
    }
    protected abstract void initPanel() ;
}
