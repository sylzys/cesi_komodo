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
    public void run()
    {
        Fenetre fen = Fenetre.getInstance();
        fen.progBar();
        fen.rep(0,"Sauvegarde de la base en ligne");
        try {
                // pause
                Thread.sleep(1500);
            }
            catch (InterruptedException ex) {
                System.out.println(ex.toString());
        } 
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
                        fen.rep(1,"Erreur lors de la sauvegarde de la base en ligne");
                        try {
                            // pause
                            Thread.sleep(2000);
                        }
                        catch (InterruptedException ex) {
                            System.out.println(ex.toString());
                        } 
                }
                r.close();
                try {
                    final int dcertExitCode = process.waitFor();
                }catch(InterruptedException i)
                {
                    fen.rep(1,"Erreur lors de la sauvegarde de la base en ligne");
                    try {
                        // pause
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException ex) {
                        System.out.println(ex.toString());
                    } 
                }
        }catch(IOException e)
        {
            fen.rep(1,"Erreur lors de la sauvegarde de la base en ligne");
            try {
                // pause
                Thread.sleep(2000);
            }
            catch (InterruptedException ex) {
                System.out.println(ex.toString());
            } 
        }
       fen.rep(25,"Suppression de la base locale");
       try {
                // pause
                Thread.sleep(1500);
            }
            catch (InterruptedException ex) {
                System.out.println(ex.toString());
        }
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
                        fen.rep(25,"Erreur lors de la suppression de la base locale");
                        try {
                           // pause
                           Thread.sleep(2000);
                        }
                        catch (InterruptedException ex) {
                            System.out.println(ex.toString());
                        }
                }
                r.close();
                try {
                    final int dcertExitCode = process.waitFor();
                }catch(InterruptedException i)
                {
                    fen.rep(25,"Erreur lors de la suppression de la base locale");
                    try {
                       // pause
                       Thread.sleep(2000);
                    }
                    catch (InterruptedException ex) {
                        System.out.println(ex.toString());
                    }
                }
        }catch(IOException e)
        {
            fen.rep(25,"Erreur lors de la suppression de la base locale");
             try {
                // pause
                Thread.sleep(2000);
            }
            catch (InterruptedException ex) {
                System.out.println(ex.toString());
            } 
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
                        fen.rep(50,"Erreur lors de la création de la base locale");
                        try {
                           // pause
                           Thread.sleep(2000);
                        }
                        catch (InterruptedException ex) {
                            System.out.println(ex.toString());
                        }
                }
                r.close();
                try {
                    final int dcertExitCode = process.waitFor();
                }catch(InterruptedException i)
                {
                    fen.rep(50,"Erreur lors de la création de la base locale");
                    try {
                       // pause
                       Thread.sleep(2000);
                    }
                    catch (InterruptedException ex) {
                        System.out.println(ex.toString());
                    }
                }
        }catch(IOException e)
        {
            fen.rep(50,"Erreur lors de la création de la base locale");
             try {
                // pause
                Thread.sleep(2000);
            }
            catch (InterruptedException ex) {
                System.out.println(ex.toString());
            } 
        }
        fen.rep(75,"Restauration de la base en local");
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
                        fen.rep(75,"Erreur lors de la restauration");
                        try {
                           // pause
                           Thread.sleep(2000);
                        }
                        catch (InterruptedException ex) {
                            System.out.println(ex.toString());
                        }
                }
                r.close();
                try {
                    final int dcertExitCode = process.waitFor();
                }catch(InterruptedException i)
                {
                    fen.rep(75,"Erreur lors de la restauration");
                    try {
                       // pause
                       Thread.sleep(2000);
                    }
                    catch (InterruptedException ex) {
                        System.out.println(ex.toString());
                    }
                }
        }catch(IOException e)
        {
            fen.rep(75,"Erreur lors de la restauration");
             try {
                // pause
                Thread.sleep(2000);
            }
            catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }
        }
        fen.rep(100,"Fin de la réplication ... Redirection vers l'accueil");
        //Nouvelle connection hors ligne
        HibernateConnection.offline();
        try {
                // pause
                Thread.sleep(2500);
            }
            catch (InterruptedException ex) {
                System.out.println(ex.toString());
        }
        ThreadOnline.change = true;
        fen.RenewAccueil();
    }
}
