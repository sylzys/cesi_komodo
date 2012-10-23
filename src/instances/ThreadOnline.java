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
    public static  boolean change;
    private boolean statusBcl;
    public void run()
    {
        Synchro connect = new Synchro();
        status = connect.InitConnect();
        do {
            if(change == true)
            {
                try {
                // pause
                Thread.sleep(900000);
                }
                catch (InterruptedException ex) {
                    System.out.println(ex.toString());
                }
                change = false;
            }
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
        if(statusBcl == false)
        {
            HibernateConnection.newConnect(false);
            fen.RenewAccueil();
        }
        else
        {
            //Nouvelle connection en ligne
            HibernateConnection.newConnect(true);
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
