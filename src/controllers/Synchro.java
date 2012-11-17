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
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
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
public class Synchro implements Serializable {
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
               System.out.println(ex);
               return false;
             }
            Logger log = Logger.getLogger("org.hibernate");
//            log.setLevel(Level.WARNING);
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
            if(req.indexOf("suppr = true") != -1)
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
                query.setParameter("interid", interid);
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
        else if(!nomclient.equals("") || table.equals("client") == true)
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
    public boolean record(String requete)
    {
        boolean empty = emptyFic();
        if(empty == true)
        {
            Transaction tx = null;
            try {
                tx = HibernateConnection.getSession().beginTransaction();
                // Ordre Hibernate             
                Query q = HibernateConnection.getSession().createSQLQuery(requete);
                q.executeUpdate();
                tx.commit();
                return true;
            } catch (HibernateException ex) {
                System.out.println(ex.toString());  
                return false;
            }
        }
        else
        {
            return false;
        }
    }
    public String cliNom(int cliid)
    {
        String cliNom = "";
        try 
        {
            Query query = HibernateConnection.getSession().createQuery("from Client where cliid = :cliid");
            query.setParameter("cliid", cliid);
            List<Client> clilist = query.list();
            for (Client client : clilist)
            {
                cliNom = client.getClinom();
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }  
        return cliNom;
    }
    public void delReq(String ligne)
    {
        String fic= readFic();
        String newFic="";
        StringTokenizer strtok = new StringTokenizer(fic, "||");
        while (strtok.hasMoreTokens()) {
            String requete = req = strtok.nextToken();
            if(!requete.equals(ligne))
            {
                newFic+=requete+"||";
            }
        }
        eraseFic();
        createFic();
        writeFic(newFic);
    }
    public void createFic(){
        try
        {
            File fichier = new java.io.File("ressources/requetes.txt");
            if(fichier.exists() == false)
            {
                fichier.createNewFile();
            }
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
    public void writeFic(String contenu)
    {
        try {
           FileWriter fichier = new FileWriter("ressources/requetes.txt", true);
           BufferedWriter bw = new BufferedWriter(fichier);
           bw.write(contenu);
           bw.close();
        } catch(IOException e) {
           System.out.println(e);
        }
    }
    public void objSerializable(List<Object> obj)
    {
        try {
        FileOutputStream fichier = new FileOutputStream("ressources/object.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fichier);
        oos.writeObject(obj);
        oos.flush();
        oos.close();
        }
        catch (java.io.IOException e) {
            System.out.println(e);
        }
    }
}
