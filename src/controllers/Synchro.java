/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import instances.HibernateConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.ParamSync;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import views.Fenetre;

/**
 *
 * @author lavie
 */
//Class qui gère la synchronisation
public class Synchro {

    private static SessionFactory sessionFactory;
    private String path = "ressources/ser/";
    
    //Test la connection en ligne
    public boolean InitConnect() {
        try {
            //Désactivation du log Warning hibernate
            Logger log = Logger.getLogger("org.hibernate");
            log.setLevel(Level.OFF);
            //On test la connection à la base en ligne
            sessionFactory = new Configuration().configure("config/connect.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.out.println(ex);
            //Si erreur on n'est pas connecté
            return false;
        }
        Logger log = Logger.getLogger("org.hibernate");
        //DECOMMENTER POUR AFFICHER LES ERREUR HIBERNATE
        //log.setLevel(Level.WARNING);
        //On ferme la session
        sessionFactory.close();
        //on renvoie true car on est en ligne
        return true;
    }
    
    //Test si il y a des actions à synchroniser et redirection vers la page synchro
    public void onlinemod() {
        //Connection hibernate en ligne
        HibernateConnection.online();
        Fenetre fen = Fenetre.getInstance();
        //on vérifie qu'il y a des actions à synchroniser
        boolean fic = emptyDir();
        if (fic == false) {
            //Si il y en a on redirige vers la page synchro
            fen.RenewSnchro();
        } else {
            fen.RenewAccueil();
        }
    }
    
    //Serialisation de l'objet et des paramètres
    public void objSerializable(Object obj, Object param) {
        try {
            //Nom de l'objet passé en paramètre
            String objName = obj.getClass().getName();
            //Nb de fichier sérialisé de cet objet
            int nbFic = nbFic(objName);
            //Nouveau fichier
            FileOutputStream fichier = new FileOutputStream(path + objName + "_" + nbFic + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream(fichier);
            //On écrit les objets dans le fichier
            oos.writeObject(obj);
            oos.writeObject(param);
            //Vidage buffer
            oos.flush();
            //Fermeture du fichier
            oos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //Désérialisation des objets (renvoi une liste d'objet)
    public ArrayList<Object> objDeserializable(String ficName) {
        try {
            //Nouvelle liste d'objet
            ArrayList<Object> lsobj = new ArrayList<>();
            //Fichier (nom passé en paramètre)
            FileInputStream fichier = new FileInputStream(path + ficName);
            ObjectInputStream ois = new ObjectInputStream(fichier);
            //On ajoute le premier objet à la liste
            lsobj.add(ois.readObject());
            //On ajoute l'objet de paramètre à la liste
            ParamSync param = (ParamSync) ois.readObject();
            lsobj.add(param);
            //Fermeture du fichier
            ois.close();
            //On renvoi la liste d'objet
            return lsobj;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    //Compte le nombre de fichier de l'objet passé en paramètre
    public int nbFic(String objName) {
        int nbFic = 0;
        //List les fichiers du dossier
        String[] dir = new File(path).list();
        //Parcours le dossier
        for (int i = 0; i < dir.length; i++) {
            //Si le nom correspond au nom de l'objet
            if (dir[i].indexOf(objName) != -1) {
                //Un fichier de plus
                nbFic = nbFic + 1;
            }
        }
        //Renvoi le nombre de fichier
        return nbFic;
    }

    //Test si le dossier est vide (renvoi true si il est vide)
    public boolean emptyDir() {
        File file = new File(path);
        //Si il y a plu de 0 fichiers
        if (file.list().length > 0) {
            //Renvoi false le dossier n'est pas vide
            return false;
        } else {
            return true;
        }
    }

    //Supprime le fichier de l'action passé en paramètre
    public boolean delFic(String fic) {
        try {
            File file = new File(fic);
            //Suppression du fichier
            file.delete();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //Sauvegarde l'action passé en paramètre dans la base en ligne
    public boolean record(String fic) {
        //Liste d'objet de l'action
        ArrayList<Object> lsobj = objDeserializable(fic);
        //Objet à insérer en base
        Object obj = lsobj.get(0);
        //Objet paramètre
        ParamSync param = (ParamSync) lsobj.get(1);
        //Session hibernate
        Transaction tx = HibernateConnection.getSession().beginTransaction();
        try {
                //Switch le type de requete insert ou update
                switch(param.getType()) {
                    case "Ajout" :
                        //Enregistrement de l'objet dans la base
                        HibernateConnection.getSession().save(obj); 
                    break;
                    case "Mise à jour" :
                    case "Suppression" :
                        //Modification de l'objet en base
                        HibernateConnection.getSession().update(obj);
                    break;
                }   
                //Comit la transaction
                tx.commit();
                //Vide la session en cours (persistence des objets)
                HibernateConnection.getSession().flush();
                //Détache l'objet de la session
                HibernateConnection.getSession().evict(obj);
                return true;
        } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
        }
    }

    //Renvoi la table concernée par l'action
    public String table(String fic) {
        //Découpe le nom du fichier
        String[] decoup = fic.split("_");
        String table = decoup[0];
        //Découpe le terme models
        String[] decoup2 = table.split("models.");
        table = decoup2[1];
        if(table.equals("Suivdossier"))
        {
            table = "Alerte";
        }
        //Renvoi le nom de la table
        return table;
    }
}
