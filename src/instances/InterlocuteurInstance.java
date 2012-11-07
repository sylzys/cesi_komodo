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
        //on r��cup��re le "where", et les param��tres
        chargerDepuisBaseDeDonnees();
        //on retourne la liste correspondant �� la requete
        return inter;
    }

    private void chargerDepuisBaseDeDonnees() {

        if (inter == null)
        {
            //si pas d��j�� fait de requetes, on cr��e une liste vide
            inter = new ArrayList<Interlocuteur>();
        }
        else //sinon on vide la liste d��j�� cr��ee
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
            //on met le resultat dans la liste renvoy��e
            this.inter = query.list();

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    public synchronized void updaterBaseDeDonnees(Interlocuteur inter) {
        Transaction tx = null;
        if (HibernateConnection.online == false)
        {
            Synchro writereq = new Synchro();
            CurrentDatas cli = CurrentDatas.getInstance();
            int cliid = cli.getSoc_id();
            String cliname = writereq.cliNom(cliid);
       try
            {
                writereq.SaveReq("UPDATE interlocuteur SET utiid = " + cli.getUser().getId() + ", "
                        + "cliid = " + inter.getCliid() + ", internom = '" + inter.getInternom() + "', interprenom = '" + inter.getInterprenom() + "', "
                        + "intermail = '" + inter.getIntermail() + "', intertel = '" + inter.getIntertel() + "',"
                        + "intersuppr = " + inter.isIntersuppr() + " WHERE interid = " + inter.getInterid(), inter.getInterid(), "");
            }
            catch (IOException ex)
            {
                Logger.getLogger(InterlocuteurInstance.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try
        {
            tx = HibernateConnection.getSession().beginTransaction();
            System.out.println("Updating Record");
            HibernateConnection.getSession().update(inter);
            tx.commit();
            System.out.println("Done");

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public synchronized void insererEnBaseDeDonnees(Interlocuteur inter) {
        Transaction tx = null;
        if (HibernateConnection.online == false)
        {
            Synchro writereq = new Synchro();
            CurrentDatas cli = CurrentDatas.getInstance();
            int cliid = cli.getSoc_id();
            String cliname = writereq.cliNom(cliid);
            try
            {
                writereq.SaveReq("INSERT INTO interlocuteur (cliid, internom, interprenom, intermail, intertel, "
                        + " interdteadd, intersuppr)"
                        + " VALUES (" + cliid + ",'" + inter.getInternom() + "','"
                        + inter.getInterprenom() + "','" + inter.getIntermail() + "','" + inter.getIntertel() + "','"
                        + Calendar.getInstance().getTime() + "',"
                        + "'f')"
                        + "", -1, cliname);
            }
            catch (IOException ex)
            {
                Logger.getLogger(InterlocuteurInstance.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try
        {
            tx = HibernateConnection.getSession().beginTransaction();
            HibernateConnection.getSession().save(inter);
            tx.commit();

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
