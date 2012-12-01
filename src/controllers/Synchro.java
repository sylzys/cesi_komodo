/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gdata.data.ExtensionDescription;
import instances.ClientInstance;
import instances.HibernateConnection;
import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Interlocuteur;
import models.Client;
import models.Commande;
import models.Demande;
import models.Devis;
import models.ParamSync;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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
        if(file.exists())
        {
            //Si il y a plus de 0 fichiers
            if (file.list().length > 0) {
                //Renvoi false le dossier n'est pas vide
                return false;
            } else {
                return true;
            }
        }
        else
        {  
            file.mkdir();
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
    
    //Sauvegarde l'action passé en paramètre dans la base en ligne
    public boolean record(String fic) {
        //Liste d'objet de l'action
        ArrayList<Object> lsobj = objDeserializable(fic);
        //Objet à insérer en base
        Object obj = lsobj.get(0);
        //Objet paramètre
        ParamSync param = (ParamSync) lsobj.get(1);
        obj = conflitid(obj, param);
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
                        HibernateConnection.getSession().merge(obj);
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
    //Switch le modele et set les id en conflit
    public Object conflitid(Object obj, ParamSync param)
    {
        String nameobj = obj.getClass().getName();
        String uniqid;
        int id;
        switch(nameobj)
        {
            case "models.Interlocuteur" :
                Interlocuteur inter = (Interlocuteur)obj;
                if(!param.getType().equals("Ajout"))
                {
                    uniqid = uniqueid(inter.getInterid(), "Interlocuteur", "interid");
                    id = idonline(uniqid, "Interlocuteur", "interuniqid");
                    inter.setInterid(id);
                }
                uniqid = uniqueid(inter.getCliid(), "Client", "cliid");
                id = idonline(uniqid, "Client", "cliuniqid");
                inter.setCliid(id);
                return inter;
            case "models.Client" :
                if(!param.getType().equals("Ajout"))
                {
                    Client cli = (Client)obj;
                    uniqid = uniqueid(cli.getCliid(), "Client", "cliid");
                    id = idonline(uniqid, "Client", "cliuniqid");
                    cli.setCliid(id);
                    return cli;
                }
                else
                {
                   return obj; 
                }
            case "models.Demande" :
                Demande dmd = (Demande)obj;
                if(!param.getType().equals("Ajout"))
                {                  
                    uniqid = uniqueid(dmd.getDemandeid(), "demande", "demandeid");
                    id = idonline(uniqid, "demande", "demandeuniqid");
                    dmd.setDemandeid(id);
                }
                uniqid = uniqueid(dmd.getCliid(), "Client", "cliid");
                id = idonline(uniqid, "Client", "cliuniqid");
                dmd.setCliid(id);
                uniqid = uniqueid(dmd.getInterid(), "Interlocuteur", "interid");
                id = idonline(uniqid, "Interlocuteur", "interuniqid");
                dmd.setInterid(id);
                return dmd;
            case "models.Devis" :
                Devis dvi = (Devis)obj;
                if(!param.getType().equals("Ajout"))
                {                  
                    uniqid = uniqueid(dvi.getDevid(), "Devis", "devid");
                    id = idonline(uniqid, "Devis", "devuniqid");
                    dvi.setDevid(id);
                }
                uniqid = uniqueid(dvi.getDemandeid(), "demande", "demandeid");
                id = idonline(uniqid, "demande", "demandeuniqid");
                dvi.setDemandeid(id);
                uniqid = uniqueid(dvi.getInterid(), "Interlocuteur", "interid");
                id = idonline(uniqid, "Interlocuteur", "interuniqid");
                dvi.setInterid(id);
                return dvi;
            case "models.Commande" :
                Commande cmd = (Commande)obj;
                if(!param.getType().equals("Ajout"))
                {                  
                    uniqid = uniqueid(cmd.getComid(), "commande", "comid");
                    id = idonline(uniqid, "commande", "comuniqid");
                    cmd.setComid(id);
                }
//                uniqid = uniqueid(cmd.getEnqid(), "Enquete", "enqid");
//                id = idonline(uniqid, "Enquete", "enquniqid");
//                cmd.setEnqid(id);
                uniqid = uniqueid(cmd.getInterid(), "Interlocuteur", "interid");
                id = idonline(uniqid, "Interlocuteur", "interuniqid");
                cmd.setInterid(id);
                uniqid = uniqueid(cmd.getDemandeid(), "demande", "demandeid");
                id = idonline(uniqid, "demande", "demandeuniqid");
                cmd.setDemandeid(id);
                return cmd;
            default:
                return obj;
        }      
    }
   
    public String uniqueid(int id, String table, String champs)
    {
       String uniqid = "";
       try 
       {
           HibernateConnection connection = HibernateConnection.getInstance();
           Query query = connection.getSessionBis().createQuery("from "+table+ " where "+champs+" = :"+champs+"");
           query.setParameter(champs, id);
           switch(table)
           {
               case "Interlocuteur" :
               Interlocuteur inter = (Interlocuteur) query.uniqueResult();
               uniqid = inter.getInteruniqid();
                   break;
               case "Client" :
               Client client = (Client) query.uniqueResult();
               uniqid = client.getCliuniqid(); 
                   break;
               case "demande" :
               Demande demande = (Demande) query.uniqueResult();
               uniqid = demande.getDemandeuniqid();
                   break;
               case "Devis" :
               Devis devis = (Devis) query.uniqueResult();
               uniqid = devis.getDevuniqid();
                   break;
               case "commande" :
               Commande commande = (Commande) query.uniqueResult();
               uniqid = commande.getComuniqid();
                   break;
           }
           return uniqid;
       }
       catch(Exception e)
       {
           System.out.println(e.getMessage());
           return uniqid;
       }
    }
    public int idonline(String uniqid, String table, String champs)
    {
        int id = 0;
        try 
        {
            HibernateConnection connection = HibernateConnection.getInstance();
            Query query = connection.getSession().createQuery("from "+table+" where "+champs+" = :"+champs+"");
            query.setParameter(champs,uniqid);
            switch(table)
            {
                case "Interlocuteur" :
                Interlocuteur inter = (Interlocuteur) query.uniqueResult();
                id = inter.getInterid();
                    break;
                case "Client" :
                Client client = (Client) query.uniqueResult();
                id = client.getCliid(); 
                    break;
                case "demande" :
                Demande demande = (Demande) query.uniqueResult();
                id = demande.getDemandeid();
                    break;
                case "Devis" :
                Devis devis = (Devis) query.uniqueResult();
                id = devis.getDevid();
                    break;
                case "commande" :
                Commande commande = (Commande) query.uniqueResult();
                id = commande.getComid();
                    break;
            }
            return id;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    public boolean chkobject(String uniqid, String objName)
    {
         //List les fichiers du dossier
        File file = new File(path);
        File[] files = file.listFiles();
        //Parcours le dossier
        for (int i = 0; i < files.length; i++) {
             String[] decoup = files[i].getName().split("_");
             String model = decoup[0];
            //Si le nom correspond au nom de l'objet
            if (model.equals(objName)) {
                ArrayList<Object> obj = objDeserializable(files[i].getName());
                String nameobj = obj.get(0).getClass().getName();
                switch(nameobj)
                {
                    case "models.Interlocuteur":
                        Interlocuteur inter = (Interlocuteur)obj.get(0);
                        String interuniqid = inter.getInteruniqid();
                        if(uniqid.equals(interuniqid))
                        {
                            return true;
                        }
                        else
                        {
                            break;
                        }
                   case "models.Client":
                   Client cli = (Client)obj.get(0);
                   String cliuniqid = cli.getCliuniqid();
                   if(uniqid.equals(cliuniqid))
                   {
                       return true;
                   }
                   else
                   {
                       break;
                   }
                   default:
                        return false;
                }
            }
        }
        if(objName.equals("models.Client"))
        {
            boolean chk = chkother(uniqid);
            return chk;
        }
        else
        {
            return false;
        }
    }
    public boolean chkother(String uniqid)
    {
         //List les fichiers du dossier
        File file = new File(path);
        File[] files = file.listFiles();
        //Parcours le dossier
        for (int i = 0; i < files.length; i++) {
            ArrayList<Object> obj = objDeserializable(files[i].getName());
            String nameobj = obj.get(0).getClass().getName();
            int cliid = 0;
            switch(nameobj)
            {
                case "models.Demande":
                    Demande dmd = (Demande)obj.get(0);
                    cliid = dmd.getCliid();
                    break;   
                default:
                if(nameobj.equals("models.Devis")||nameobj.equals("models.Commande"))
                {
                    int interid = 0;
                    switch(nameobj)
                    {
                        case "models.Devis":
                            Devis devis = (Devis)obj.get(0);
                            interid = devis.getInterid();
                            break;
                        case "models.Commande": 
                            Commande cmd = (Commande)obj.get(0);
                            interid = cmd.getInterid();
                            break;
                    }
                    HibernateConnection connection = HibernateConnection.getInstance();
                    Query query = connection.getSession().createQuery("from Interlocuteur where interid = :interid");
                    query.setParameter("interid",interid);
                    Interlocuteur inter = (Interlocuteur)query.uniqueResult();
                    cliid = inter.getCliid();
                }

                String cliuniqid = uniqueid(cliid, "Client", "cliid");
                if(cliuniqid.equals(uniqid))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
