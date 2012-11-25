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
import models.Utilisateur;
import org.hibernate.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        HibernateConnection hc = HibernateConnection.getInstance();
        ThreadOnline thread = new ThreadOnline();
        // Activation du Thread
        thread.start();
        fenetre.getInstance();
    }
}
