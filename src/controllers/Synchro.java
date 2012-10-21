/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import instances.HibernateConnection;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
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
import java.util.*;
import javax.swing.JFrame;
import views.ProgressBarAtt;

/**
 *
 * @author lavie
 */
public class Synchro extends Thread {
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
        Fenetre fen = Fenetre.getInstance();
        System.out.println("Réplication de la BDD"); 
        List<String> commd = new ArrayList<String>();
                            commd.add("C:\\replicationBDD\\pg_dump.exe");
                            commd.add("-i"); // IGNORE la version
                            commd.add("-h"); // adresse du serveur de bdd
                            commd.add("192.168.1.45");
                            commd.add("-p"); // port du serveur de bdd
                            commd.add("5432");
                            commd.add("-Fc"); // recreer toutes les TABLES pour backup
                            commd.add("-U"); // utilisateur
                            commd.add("cesi");
                            commd.add("-f"); // fichier
                            commd.add("C:\\replicationBDD\\save.bak");
                            commd.add("projetcesi");//Nom de la base
        ProcessBuilder PrcBld = new ProcessBuilder(commd);
        try {
                Process process = PrcBld.start();
                BufferedReader r = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String line = r.readLine();
                while (line != null) {
                        System.err.println(line);
                        line = r.readLine();
                }
                r.close();
                try {
                    final int dcertExitCode = process.waitFor();
                }catch(InterruptedException i)
                {
                    System.out.println(i);
                }
        }catch(IOException e)
        {
            System.out.println(e);
        }
        commd = new ArrayList<String>();
                commd.add("C:\\replicationBDD\\dropdb.exe");
                commd.add("-h"); // adresse du serveur de bdd
                commd.add("127.0.0.1");
                commd.add("-p"); // port du serveur de bdd
                commd.add("5432");
                commd.add("-U"); // utilisateur
                commd.add("cesi");
                commd.add("projetcesi");//Nom de la base
        PrcBld = new ProcessBuilder(commd);
        try {
                Process process = PrcBld.start();
                BufferedReader r = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String line = r.readLine();
                while (line != null) {
                        System.err.println(line);
                        line = r.readLine();
                }
                r.close();
                try {
                    final int dcertExitCode = process.waitFor();
                }catch(InterruptedException i)
                {
                    System.out.println(i);
                }
        }catch(IOException e)
        {
            System.out.println(e);
        }
        commd = new ArrayList<String>();
                commd.add("C:\\replicationBDD\\createdb.exe");
                commd.add("-h"); // adresse du serveur de bdd
                commd.add("127.0.0.1");
                commd.add("-p"); // port du serveur de bdd
                commd.add("5432");
                commd.add("-U"); // utilisateur
                commd.add("cesi");
                commd.add("projetcesi");//Nom de la base
        PrcBld = new ProcessBuilder(commd);
        try {
                Process process = PrcBld.start();
                BufferedReader r = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String line = r.readLine();
                while (line != null) {
                        System.err.println(line);
                        line = r.readLine();
                }
                r.close();
                try {
                    final int dcertExitCode = process.waitFor();
                }catch(InterruptedException i)
                {
                    System.out.println(i);
                }
        }catch(IOException e)
        {
            System.out.println(e);
        }
        commd = new ArrayList<String>();
                commd.add("C:\\replicationBDD\\pg_restore.exe");
                commd.add("-i"); // IGNORE la version
                commd.add("-h"); // adresse du serveur de bdd
                commd.add("127.0.0.1");
                commd.add("-p"); // port du serveur de bdd
                commd.add("5432");
                commd.add("-d"); // nom de la base de données
                commd.add("projetcesi");
                commd.add("-U"); // utilisateur
                commd.add("cesi");
                commd.add("C:\\replicationBDD\\save.bak");
        PrcBld = new ProcessBuilder(commd);
        try {
                Process process = PrcBld.start();
                BufferedReader r = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String line = r.readLine();
                while (line != null) {
                        System.err.println(line);
                        line = r.readLine();
                }
                r.close();
                try {
                    final int dcertExitCode = process.waitFor();
                }catch(InterruptedException i)
                {
                    System.out.println(i);
                }
        }catch(IOException e)
        {
            System.out.println(e);
        }
        //Nouvelle connection hors ligne
        HibernateConnection.offline();
        fen.RenewAccueil();
    }
    public void onlinemod()
    {
        System.out.println("Passage en mode connecté"); 
        HibernateConnection.online();
        Fenetre fen = Fenetre.getInstance();
        fen.RenewAccueil();
    }
}
