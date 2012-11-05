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
import java.io.FileReader;
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
import models.Client;
import models.Commande;
import models.Interlocuteur;
import views.ReplicView;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

/**
 *
 * @author lavie
 */
//Class pour la synchronisation en ligne
public class Synchro {
    private static SessionFactory sessionFactory;
    private String lib;
    private String libreq;
    private String req;
    private String table;
    private String[] name;
    private String[] decTable;
    private String nameclient;
    
    //Test la connection en ligne
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
    //Sauvegarde de la requete dans le fichier
    public void SaveReq(String req, int interid, String nomclient) throws IOException
    {
        try {
           FileWriter fichier = new FileWriter("ressources/requetes.txt", true);
           BufferedWriter bw = new BufferedWriter(fichier);
           if(!nomclient.equals(""))
           {
               bw.write(req + "client:" + nomclient + "||");
           }
           else
           {
               bw.write(req + "interlocuteur:" + interid + "||");
           }
           bw.close();
        } catch(IOException e) {
           System.out.println(e);
        }
    }
    //Test si fichier vide renvoi false si il est vide
    public boolean emptyFic()
    {
        try {
            File fich = new File("ressources/requetes.txt");
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
    //Remise à 0 du fichier de requete
    public void eraseFic()
    {
        try
        {
            File fich = new File("ressources/requetes.txt");
            if(fich.exists() == true)
            {
                fich.delete();
                fich.createNewFile();
            }
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
    public void onlinemod()
    {
        HibernateConnection.online();
        Fenetre fen = Fenetre.getInstance();
        boolean fic = emptyFic();
        if(fic == true)
        {
            fen.RenewSnchro();
        }
        else
        {
            fen.RenewAccueil();
        }
    }
    //Lecture du fichier
    public String readFic()
    {
        req = "";
        String filePath = "ressources/requetes.txt";
        try{
        // Création du flux bufférisé sur un FileReader, immédiatement suivi par un 
        // try/finally, ce qui permet de ne fermer le flux QUE s'il le reader
        // est correctement instancié (évite les NullPointerException)
        BufferedReader buff = new BufferedReader(new FileReader(filePath));
        try {
            String line;
            // Lecture du fichier ligne par ligne. Cette boucle se termine
            // quand la méthode retourne la valeur null.
            while ((line = buff.readLine()) != null) {
                req+= line;
                }
        } finally {
            // dans tous les cas, on ferme nos flux
            buff.close();
        }
        } catch (IOException ioe) {
            // erreur de fermeture des flux
            System.out.println("Erreur --" + ioe.toString());
        }
        return req;
    }
    //Lecture des requete dans le fichier
    public String[] readReq(String req, int interid, String nomclient)
    {
        nameclient = "";
        table = ""; 
        libreq = "";
        name = null;
        decTable = null;
        libreq = req.substring(0,6);
        if("INSERT".equals(libreq))
        {
            libreq = "Ajout"; 
            name = req.split("INSERT INTO");
            table = name[1];
            decTable = table.split("\\(");
            table = decTable[0].replaceAll(" ", "");
        }
        else
        {
            if(req.indexOf("suppr") != -1)
            {
                name = req.split("UPDATE");
                table = name[1];
                decTable = table.split("SET");
                table = decTable[0].replaceAll(" ", "");
                libreq = "Supression";
            }
            else if("DELETE".equals(libreq))
            {
                name = req.split("FROM");
                table = name[1];
                decTable = table.split("WHERE");
                table = decTable[0].replaceAll(" ", "");
                libreq = "Supression";
            }
            else
            {
                name = req.split("UPDATE");
                table = name[1];
                decTable = table.split("SET");
                table = decTable[0].replaceAll(" ", "");
                libreq = "Mise à jour" ;
            }    
        } 
        if(interid != -1 && table.equals("client") == false)
        {
            int clientid = 0;
            try 
            {
                Query query = HibernateConnection.getSession().createQuery("from Interlocuteur where interid = :interid");
                query.setParameter("interid", 1);
                List<Interlocuteur> interlist = query.list();
                for (Interlocuteur inter : interlist) 
                {
                    clientid = inter.getCliid();
                }
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            try 
            {
                Query query = HibernateConnection.getSession().createQuery("from Client where cliid = :cliid");
                query.setParameter("cliid", clientid);
                List<Client> clilist = query.list();
                for (Client client : clilist) 
                {
                    nameclient = client.getClinom();
                }
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }  
        }
        else if(table.equals("client") == true)
        {
            nameclient = nomclient;
        }
        else
        {
            nameclient = "Aucun client concerné"; 
        }
        String action[] = {nameclient, table, libreq};
        return action;
    }
    //Envoi des requete dans la base en ligne
    public void record()
    {
        boolean test = false;
        boolean empty = emptyFic();
        if(empty == true)
        {
            String fic = readFic();
            StringTokenizer strtok = new StringTokenizer(fic, "||");
            String act = "";
            String requete = "";
            while (strtok.hasMoreTokens()) {
               act = strtok.nextToken();
               String[] inter = act.split("interlocuteur:");
               String[] cli = act.split("client:");
                if(inter.length > 1)
                {
                    requete = inter[0];
                }
                else
                {
                    requete = cli[0];
                }
                Transaction tx = null;
                try {
                    tx = HibernateConnection.getSession().beginTransaction();
                    // Ordre Hibernate             
                    Query q = HibernateConnection.getSession().createSQLQuery(requete);
                    q.executeUpdate();
                    tx.commit();
                    eraseFic();   
                    test = true;
                    Fenetre fen = Fenetre.getInstance();
                    fen.RenewAccueil();
                } catch (HibernateException ex) {
                    test = false;
                    System.out.println(ex.toString());                    
                }  
            }
            JOptionPane jop = new JOptionPane();
            if(test == true)
            {
                jop.showMessageDialog(null, "Vous êtes synchronisé avec la base en ligne", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                jop.showMessageDialog(null, "Une erreur est survenue lors de la synchronisation", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
