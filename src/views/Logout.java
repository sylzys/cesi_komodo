/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.UserActif;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author sylv
 */
public class Logout extends KContainer {
    JLabel title = new JLabel ("PANNEAU LOGOUT");
    public Logout(UserActif user) {
    super();
//    this.user = user;
//    initPanel();
    JOptionPane jop = new JOptionPane();
    jop.showMessageDialog(null, "Fonction de déconnexion", "LOGOUT", JOptionPane.INFORMATION_MESSAGE);
    System.exit(0);
    }

    @Override
    protected
    void initPanel() {
//        JPanel content = new JPanel();
//        
//        content.setLayout(new BorderLayout());
//        content.add(title, BorderLayout.CENTER);      
//        this.panel.add(content);
//        JOptionPane jop = new JOptionPane();
//        jop.showMessageDialog(null, "Fonction de déconnexion", "LOGOUT", JOptionPane.INFORMATION_MESSAGE);
//        System.exit(0);
    }
}
