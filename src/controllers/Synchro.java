/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import instances.HibernateConnection;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import views.Fenetre;

/**
 *
 * @author lavie
 */
public class Synchro {
    private static SessionFactory sessionFactory;
    private String lib;
    private String libreq;
    public boolean InitConnect()
    {
            try {
            //Désactivation du log Warning hibernate
            Logger log = Logger.getLogger("org.hibernate");
            log.setLevel(Level.OFF);
                sessionFactory = new Configuration().configure("config/connect.xml").buildSessionFactory() ;
             } catch (Throwable ex) {
               Logger log = Logger.getLogger("org.hibernate");
               log.setLevel(Level.WARNING);  
               return false;
             }
        Logger log = Logger.getLogger("org.hibernate");
        log.setLevel(Level.WARNING);  
        sessionFactory.close();
        return true;
    }
    public void SaveReq(String req) throws IOException
    {
        try {
           FileWriter fichier = new FileWriter("ressources/requetes.sql", true);
           BufferedWriter bw = new BufferedWriter(fichier);
           libreq = req.substring(0,6);
           if("INSERT".equals(libreq))
           {
               lib = "Ajout|";
           }
           else
           {
               lib = "Mise à jour|" ;
           }
           bw.write(req +";->"+ lib);
           bw.close();
        } catch(IOException e) {
           System.out.println(e);
        }
    }
    public boolean emptyFic()
    {
        try {
            File fich = new File("ressources/requetes.sql");
            if(fich.length() != 0)
            {
                return true;
            }
            else
            {
                return false;   
            }
        }
        catch(Exception e) {
           System.out.println(e);
           return false;
        }
    }
    public void repBdd()
    {
        System.out.println("Réplication de la BDD"); 
//        Runtime runtime = Runtime.getRuntime();
//        try{
//           Process p = runtime.exec("\"C:\\replicationBDD\\pg_dump.exe\" -h 192.168.1.45 -p 5432 -U cesi -Fc -f \"C:\\replicationBDD\\save.bak\" projetcesi");
//           try{
//               p.waitFor();
//           }
//           catch(InterruptedException e)
//           {
//               System.out.println(e);
//           }
//           p = runtime.exec("\"C:\\replicationBDD\\dropdb.exe\" -h localhost -p 5432 -U cesi projetcesi");
//            try{
//               p.waitFor();
//           }
//           catch(InterruptedException e)
//           {
//               System.out.println(e);
//           }
//           
//           p = runtime.exec("\"C:\\replicationBDD\\createdb.exe\" -h localhost -p 5432 -U cesi projetcesi");
//           try{
//               p.waitFor();
//           }
//           catch(InterruptedException e)
//           {
//               System.out.println(e);
//           }
//           p = runtime.exec("\"C:\\replicationBDD\\pg_restore.exe\" -h localhost -p 5432 -U cesi -d \"projetcesi\" -v \"C:\\replicationBDD\\save.bak\"");
//           try{
//               p.waitFor();
//           }
//           catch(InterruptedException e)
//           {
//               System.out.println(e);
//           }
//        }
//        catch(IOException e){
//            System.out.println(e);
//        }
        //Nouvelle connection hors ligne
        HibernateConnection.offline();
        Fenetre fen = Fenetre.getInstance();
        fen.RenewAccueil();
    }
}
