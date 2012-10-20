/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package instances;

import controllers.Connect;
import controllers.UserActif;
import javax.swing.JPanel;
import sun.applet.Main;
import views.Accueil;
import views.Fenetre;
import views.Synchro;
import cesi_komodo.Cesi_komodo;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author suly
 */
public class ThreadOnline extends Thread 
{
    private boolean status;
    private boolean statusBcl;
    public void run() 
    {
        Connect connect = new Connect();
        status = connect.InitConnect();
        do {
            //Test connection
            statusBcl = connect.InitConnect();
            try {
                // pause
                Thread.sleep(6000);
            }
            catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }
         }while(status == statusBcl);
        Fenetre fen = Fenetre.getInstance();
        fen.setVisible(false);
        if(statusBcl == false)
        {
            System.out.println("Nouvelle connection hors ligne");
            HibernateConnection.newConnect(false);
            fen.RenewAccueil();  
        }
        else
        {
            //Nouvelle connection en ligne
            HibernateConnection.newConnect(true);
            System.out.println("Nouvelle connection en ligne lancement de la page synchro");
            boolean fich = connect.emptyFic();
            if(fich == true)
            {
                fen.RenewSnchro();
            }
            else
            {
                fen.RenewAccueil();
            }
        } 
        fen.setVisible(true);
        //Reload thread
        ThreadOnline th = new ThreadOnline();
        th.run();
     }	
}
