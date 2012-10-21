/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import instances.HibernateConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import views.Fenetre;
import views.ProgressBarAtt;

/**
 *
 * @author suly
 */
public class Replication extends Thread {
    public void run()
    {
        Fenetre fen = Fenetre.getInstance();
        fen.progBar();
        fen.rep(1,"Sauvegarde de la base en ligne");
        System.out.println("Réplication de la BDD"); 
        List<String> commd = new ArrayList<String>();
                            commd.add("C:\\replicationBDD\\pg_dump.exe");
                            commd.add("-i"); // IGNORE la version
                            commd.add("-h"); // adresse du serveur de bdd
                            commd.add("127.0.0.1");
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
            fen.rep(1,"Erreur lors de la sauvegarde de la base en ligne");
        }
       fen.rep(25,"Suppression de la base locale");
        commd = new ArrayList<String>();
                commd.add("C:\\replicationBDD\\dropdb.exe");
                commd.add("-h"); // adresse du serveur de bdd
                commd.add("127.0.0.1");
                commd.add("-p"); // port du serveur de bdd
                commd.add("5432");
                commd.add("-U"); // utilisateur
                commd.add("cesi");
                commd.add("projetcesi2");//Nom de la base
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
            fen.rep(25,"Erreur lors de la suppression de la base locale");
        }
        fen.rep(50,"Création de la base local");
        commd = new ArrayList<String>();
                commd.add("C:\\replicationBDD\\createdb.exe");
                commd.add("-h"); // adresse du serveur de bdd
                commd.add("127.0.0.1");
                commd.add("-p"); // port du serveur de bdd
                commd.add("5432");
                commd.add("-U"); // utilisateur
                commd.add("cesi");
                commd.add("projetcesi2");//Nom de la base
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
            fen.rep(50,"Erreur lors de la création de la base locale");
        }
        fen.rep(75,"Restauration de la base");
        commd = new ArrayList<String>();
                commd.add("C:\\replicationBDD\\pg_restore.exe");
                commd.add("-i"); // IGNORE la version
                commd.add("-h"); // adresse du serveur de bdd
                commd.add("127.0.0.1");
                commd.add("-p"); // port du serveur de bdd
                commd.add("5432");
                commd.add("-d"); // nom de la base de données
                commd.add("projetcesi2");
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
            fen.rep(75,"Erreur lors de la restauration");
        }
        fen.rep(1,"Fin de la réplication");
        //Nouvelle connection hors ligne
        HibernateConnection.offline();
        fen.RenewAccueil();
    }
}
