/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.sun.servicetag.SystemEnvironment;
import instances.HibernateConnection;
import instances.ThreadOnline;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import views.Fenetre;
import views.ReplicView;

/**
 *
 * @author suly
 */
public class Replication extends Thread {

    String PATH_EXE = "";
    String PATH_BAK = "";
    String PATH_DROP_DB = "";
    String PATH_CREATE_DB = "";
    String PATH_PGRESTORE = "";
    String WIN_PREFIX = "C:\\replicationBDD\\";
    String MAC_PREFIX = "/usr/bin/";
    
    public void run() {
        //getting os name
        String os = "";
        if (System.getProperty("os.name").toLowerCase().indexOf("windows") > -1)
        {
            PATH_EXE = WIN_PREFIX + "pg_dump.exe";
            PATH_BAK = WIN_PREFIX + "save.bak";
            PATH_DROP_DB = WIN_PREFIX + "dropdb.exe";
            PATH_CREATE_DB = WIN_PREFIX + "createdb.exe";
            PATH_PGRESTORE = WIN_PREFIX + "pg_restore.exe";
        }
        else if ((System.getProperty("os.name").toLowerCase().indexOf("linux") > -1) || (System.getProperty("os.name").toLowerCase().indexOf("mac") > -1))
        {
            PATH_EXE = MAC_PREFIX + "pg_dump";
            PATH_BAK = "/tmp/save.bak";
            PATH_DROP_DB = MAC_PREFIX + "dropdb";
            PATH_CREATE_DB = MAC_PREFIX + "createdb";
            PATH_PGRESTORE = MAC_PREFIX + "pg_restore";
        }

        Fenetre fen = Fenetre.getInstance();
        fen.progBar();
        fen.rep(0, "Sauvegarde de la base en ligne");
        try
        {
            // pause
            Thread.sleep(1500);
        }
        catch (InterruptedException ex)
        {
            System.out.println(ex.toString());
        }
        List<String> commd = new ArrayList<String>();
        
        commd.add(PATH_EXE);
        commd.add("-i"); // IGNORE la version
        commd.add("-h"); // adresse du serveur de bdd
        commd.add("127.0.0.1");
        commd.add("-p"); // port du serveur de bdd
        commd.add("5432");
        commd.add("-Fc"); // recreer toutes les TABLES pour backup
        commd.add("-U"); // utilisateur
        commd.add("cesi");
        commd.add("-f"); // fichier
        commd.add(PATH_BAK);
        commd.add("projetcesi");//Nom de la base
        System.out.println("sauvegarde :" +commd.toString());
        ProcessBuilder PrcBld = new ProcessBuilder(commd);
        try
        {
            Process process = PrcBld.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line = r.readLine();
            while (line != null)
            {
                System.err.println(line);
                line = r.readLine();
                fen.rep(1, "Erreur lors de la sauvegarde de la base en ligne");
                try
                {
                    // pause
                    Thread.sleep(2000);
                }
                catch (InterruptedException ex)
                {
                    System.out.println(ex.toString());
                }
            }
            r.close();
            try
            {
                final int dcertExitCode = process.waitFor();
            }
            catch (InterruptedException i)
            {
                fen.rep(1, "Erreur lors de la sauvegarde de la base en ligne");
                try
                {
                    // pause
                    Thread.sleep(2000);
                }
                catch (InterruptedException ex)
                {
                    System.out.println(ex.toString());
                }
            }
        }
        catch (IOException e)
        {
            fen.rep(1, "Erreur lors de la sauvegarde de la base en ligne");
            try
            {
                // pause
                Thread.sleep(2000);
            }
            catch (InterruptedException ex)
            {
                System.out.println(ex.toString());
            }
        }
        fen.rep(25, "Suppression de la base locale");
        try
        {
            // pause
            Thread.sleep(1500);
        }
        catch (InterruptedException ex)
        {
            System.out.println(ex.toString());
        }
        commd = new ArrayList<String>();
        commd.add(PATH_DROP_DB);
        commd.add("-h"); // adresse du serveur de bdd
        commd.add("127.0.0.1");
        commd.add("-p"); // port du serveur de bdd
        commd.add("5432");
        commd.add("-U"); // utilisateur
        commd.add("cesi");
        commd.add("projetcesi2");//Nom de la base
        System.out.println("suppression = " +commd.toString());
        PrcBld = new ProcessBuilder(commd);
        try
        {
            Process process = PrcBld.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line = r.readLine();
            while (line != null)
            {
                System.err.println(line);
                line = r.readLine();
                fen.rep(25, "Erreur lors de la suppression de la base locale");
                try
                {
                    // pause
                    Thread.sleep(2000);
                }
                catch (InterruptedException ex)
                {
                    System.out.println(ex.toString());
                }
            }
            r.close();
            try
            {
                final int dcertExitCode = process.waitFor();
            }
            catch (InterruptedException i)
            {
                fen.rep(25, "Erreur lors de la suppression de la base locale");
                try
                {
                    // pause
                    Thread.sleep(2000);
                }
                catch (InterruptedException ex)
                {
                    System.out.println(ex.toString());
                }
            }
        }
        catch (IOException e)
        {
            fen.rep(25, "Erreur lors de la suppression de la base locale");
            try
            {
                // pause
                Thread.sleep(2000);
            }
            catch (InterruptedException ex)
            {
                System.out.println(ex.toString());
            }
        }
        fen.rep(50, "Création de la base locale");
        commd = new ArrayList<String>();
        commd.add(PATH_CREATE_DB);
        commd.add("-T"); // adresse du serveur de bdd
        commd.add("postgres");       
        commd.add("-h"); // adresse du serveur de bdd
        commd.add("127.0.0.1");
        commd.add("-p"); // port du serveur de bdd
        commd.add("5432");
        commd.add("-U"); // utilisateur
        commd.add("cesi");
        commd.add("projetcesi2");//Nom de la base
        System.out.println("creation locale :" +commd.toString());
        PrcBld = new ProcessBuilder(commd);
        try
        {
            Process process = PrcBld.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line = r.readLine();
            while (line != null)
            {
                System.err.println(line);
                line = r.readLine();
                fen.rep(50, "Erreur lors de la création de la base locale");
                try
                {
                    // pause
                    Thread.sleep(2000);
                }
                catch (InterruptedException ex)
                {
                    System.out.println(ex.toString());
                }
            }
            r.close();
            try
            {
                final int dcertExitCode = process.waitFor();
            }
            catch (InterruptedException i)
            {
                fen.rep(50, "Erreur lors de la création de la base locale");
                try
                {
                    // pause
                    Thread.sleep(2000);
                }
                catch (InterruptedException ex)
                {
                    System.out.println(ex.toString());
                }
            }
        }
        catch (IOException e)
        {
            fen.rep(50, "Erreur lors de la création de la base locale");
            try
            {
                // pause
                Thread.sleep(2000);
            }
            catch (InterruptedException ex)
            {
                System.out.println(ex.toString());
            }
        }
        fen.rep(75, "Restauration de la base en local");
        commd = new ArrayList<String>();
        commd.add(PATH_PGRESTORE);
        commd.add("-i"); // IGNORE la version
        commd.add("-h"); // adresse du serveur de bdd
        commd.add("127.0.0.1");
        commd.add("-p"); // port du serveur de bdd
        commd.add("5432");
        commd.add("-d"); // nom de la base de données
        commd.add("projetcesi2");
        commd.add("-U"); // utilisateur
        commd.add("cesi");
        commd.add(PATH_BAK);
        System.out.println("restau locale :" +commd.toString());
        PrcBld = new ProcessBuilder(commd);
        try
        {
            Process process = PrcBld.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line = r.readLine();
            while (line != null)
            {
                System.err.println(line);
                line = r.readLine();
                fen.rep(75, "Erreur lors de la restauration");
                try
                {
                    // pause
                    Thread.sleep(2000);
                }
                catch (InterruptedException ex)
                {
                    System.out.println(ex.toString());
                }
            }
            r.close();
            try
            {
                final int dcertExitCode = process.waitFor();
            }
            catch (InterruptedException i)
            {
                fen.rep(75, "Erreur lors de la restauration");
                try
                {
                    // pause
                    Thread.sleep(2000);
                }
                catch (InterruptedException ex)
                {
                    System.out.println(ex.toString());
                }
            }
        }
        catch (IOException e)
        {
            fen.rep(75, "Erreur lors de la restauration");
            try
            {
                // pause
                Thread.sleep(2000);
            }
            catch (InterruptedException ex)
            {
                System.out.println(ex.toString());
            }
        }
        fen.rep(100, "Fin de la réplication ... Redirection vers l'accueil");
        //Nouvelle connection hors ligne
        HibernateConnection.offline();
        try
        {
            // pause
            Thread.sleep(2500);
        }
        catch (InterruptedException ex)
        {
            System.out.println(ex.toString());
        }
        ThreadOnline.change = true;
        fen.RenewAccueil();
    }
}
