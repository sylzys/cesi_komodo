/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.UserActif;
import instances.HibernateConnection;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author sylv
 */
public class Logout extends KContainer {
    JLabel title = new JLabel ("PANNEAU LOGOUT");
    public Logout(UserActif user) {
    super();

    JOptionPane.showMessageDialog(null, "Fonction de déconnexion", "LOGOUT", JOptionPane.INFORMATION_MESSAGE);
    HibernateConnection.closeConnection();
    System.exit(0);
    }

    @Override
    protected
    void initPanel() {
    }
}
