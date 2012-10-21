/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package instances;

import controllers.Synchro;
import views.Fenetre;
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
        
        Synchro connect = new Synchro();
        status = connect.InitConnect();
        do {
            System.out.println("Thread test connection");
            //Test connection
            statusBcl = connect.InitConnect();
            try {
                // pause
                Thread.sleep(3000);
            }
            catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }
         }while(status == statusBcl);
        Fenetre fen = Fenetre.getInstance();
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
        //Reload thread
        ThreadOnline th = new ThreadOnline();
        th.run();
     }
}
