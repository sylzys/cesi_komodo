/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package instances;

import controllers.Connect;
import java.net.Inet4Address;

/**
 *
 * @author suly
 */
public class ThreadOnline extends Thread 
{
    private boolean testBcl;
    public void run() 
    {
        Connect connect = new Connect();
        testBcl = false;
        do {
           System.out.println("Test de connection (Thread)");
           boolean status = connect.InitConnect();
           if(status == false)
           {
              System.out.println("Hors ligne"); 
           }
           try {
             // pause
             Thread.sleep(6000);
           }
           catch (InterruptedException ex) {
               System.out.println(ex.toString());
           }
          //testBcl = true;
         }while(testBcl != true);
     }	
}
