/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.UserActif;
import instances.HibernateConnection;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.hibernate.Transaction;

/**
 *
 * @author sylv
 */
public class Logout extends KContainer {
    JLabel title = new JLabel ("PANNEAU LOGOUT");
    public Logout(UserActif user) {
    super();
    user.SetDteActiveUser();
    HibernateConnection.closeConnection();
    System.exit(0);
    }

    @Override
    protected
    void initPanel() {
    }
}
