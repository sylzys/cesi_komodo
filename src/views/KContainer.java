/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.UserActif;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author sylv
 */
public abstract class KContainer {

    protected JPanel panel;
    protected UserActif user;

    public KContainer() {
        this.panel = new JPanel();
        //this.user = new UserActif();
        this.panel.setBackground(Color.WHITE);
    }

    public JPanel getPanel() {
        return this.panel;
    }
    protected abstract void initPanel() ;
}
