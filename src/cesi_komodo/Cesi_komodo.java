/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cesi_komodo;
import controllers.Synchro;
import java.io.IOException;
import views.Fenetre;
import instances.HibernateConnection;
import instances.ThreadOnline;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import models.Utilisateur;
import org.hibernate.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import models.Client;
import models.Interlocuteur;
/**
 *
 * @author sylv
 */
public class Cesi_komodo {
    /**
     * @param args the command line arguments
     */
    public static Fenetre fenetre;
    public static void main(String[] args) {
        JFrame frame = new JFrame();	
        JPanel pnl = new JPanel();
        pnl.setPreferredSize(new Dimension(250, 250));
        pnl.setLayout(new BorderLayout());  
        JLabel lbl = new JLabel(new ImageIcon("ressources/images/loader.gif"));
        frame.setBackground(Color.white);
        frame.setResizable(false);
        pnl.setBackground(Color.white);
        pnl.add(lbl, BorderLayout.CENTER);
        frame.getContentPane().add(pnl);
        frame.setPreferredSize(new Dimension(350, 280));
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setTitle("Connection au réseau en cours ...");
        frame.pack();
        frame.setVisible(true);	
        frame.setLocationRelativeTo(null);
        HibernateConnection hc = HibernateConnection.getInstance();
        ThreadOnline thread = new ThreadOnline();
        // Activation du Thread
        thread.start();
        frame.setVisible(false);
        fenetre.getInstance();
    }
}
