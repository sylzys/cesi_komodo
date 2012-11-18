package instances;

import controllers.Synchro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Client;
import models.Commande;
import models.CurrentDatas;
import models.Interlocuteur;
import models.ParamSync;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;

public class InterlocuteurInstance {

    private static InterlocuteurInstance instance;
    private List<Interlocuteur> inter;
    private String where;
    private Hashtable h;
    private Session session;

    /**
     * Constructeur prive
     */
    private InterlocuteurInstance() {
        super();
    }

    /**
     * Singleton de Commande
     *
     * @return
     */
    public static synchronized InterlocuteurInstance getInstance() {
        if (instance == null)
        {
            instance = new InterlocuteurInstance();
        }

        return instance;
    }

    public synchronized List<Interlocuteur> GetInterlocuteurs(String where, Hashtable h) {
        this.where = where;
        this.h = h;
        //on récupère le "where", et les paramètres
        chargerDepuisBaseDeDonnees();
        //on retourne la liste correspondant à la requete
        return inter;
    }

    private void chargerDepuisBaseDeDonnees() {

        if (inter == null)
        {
            //si pas déjà fait de requetes, on crée une liste vide
            inter = new ArrayList<Interlocuteur>();
        }
        else //sinon on vide la liste déjà crée
        {
            inter.clear();
        }

        HibernateConnection connection = HibernateConnection.getInstance();
        //requete de base pour hibernate
        String sql = "from Interlocuteur ";
        //ajout du where si non vide
        if (!where.isEmpty())
        {
            sql += where;
        }
        try
        {
            Query query = connection.getSession().createQuery(sql);//"from Client where utiid = :utiid");
            //query.setParameter("utiid", 1);
            //on parse les parametres pour creer le query.setParameters
            if (!h.isEmpty())
            {
                Set<String> set = h.keySet();
                Iterator<String> itr = set.iterator();
                while (itr.hasNext())
                {
                    String str = itr.next();
                    System.out.println(" --> " + str + ": " + h.get(str));
                    query.setParameter(str, h.get(str));
                }
            }
            //on met le resultat dans la liste renvoyée
            this.inter = query.list();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public synchronized void updaterBaseDeDonnees(Interlocuteur inter) {
        if (HibernateConnection.online == false)
        {
            ParamSync param = new ParamSync();
            param.setClinom(inter.getInterid(), "inter");
            if(inter.isIntersuppr() == true)
            {
                param.setType("Suppression");
            }
            else
            {
                param.setType("Mise à jour");   
            }
            Synchro sync = new Synchro();
            sync.objSerializable(inter, param);
        }
        try
        {
            Transaction tx = HibernateConnection.getSession().beginTransaction();
            HibernateConnection.getSession().update(inter);
            tx.commit();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public synchronized void insererEnBaseDeDonnees(Interlocuteur inter) {
        //Test si on est hors ligne
        if (HibernateConnection.online == false)
        {
            CurrentDatas cli = CurrentDatas.getInstance();
            int cliid = cli.getSoc_id();
            //Nouvel objet de paramètre
            ParamSync param = new ParamSync();
            //Nom du client
            param.setClinom(cliid, "client");
            //Type d'action
            param.setType("Ajout");
            //Id du client concerné
            param.setIdWhere(cliid);
            Synchro sync = new Synchro();
            //sérialisation des objets
            sync.objSerializable(inter, param);
        }
        try
        {
            //Transaction hibernate
            Transaction tx = HibernateConnection.getSession().beginTransaction();
            HibernateConnection.getSession().save(inter);
            tx.commit();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
