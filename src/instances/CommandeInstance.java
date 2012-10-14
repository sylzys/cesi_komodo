package instances;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import models.Client;
import models.Commande;
import models.HibernateConnection;
import org.hibernate.Query;

public class CommandeInstance {

    private static CommandeInstance instance;
    private List<Commande> commandes;
    private String where;
    private Hashtable h;

    /**
     * Constructeur prive
     */
    private CommandeInstance() {
        super();
    }

    /**
     * Singleton
     *
     * @return
     */
    public static synchronized CommandeInstance getInstance() {
        if (instance == null)
        {
            instance = new CommandeInstance();
        }

        return instance;
    }

    public synchronized List<Commande> GetCommandes(String where, Hashtable h) {
        this.where = where;
        this.h = h;
        chargerDepuisBaseDeDonnees();
        return commandes;
    }

    /**
     * Bouchon.
     *
     * Dans un vrai programme, ces donnees seraient chargees depuis la base.
     */
    private void chargerDepuisBaseDeDonnees() {

        if (commandes == null)
        {
            //return;
            commandes = new ArrayList<Commande>();
        }
        else
        {
            commandes.clear();
        }

        HibernateConnection connection = HibernateConnection.getInstance();
        String sql = "from Commande ";
        if (!where.isEmpty())
        {
            sql += where;
        }

        try
        {
            Query query = connection.getSession().createQuery(sql);//"from Client where utiid = :utiid");
            //query.setParameter("utiid", 1);
            if (!h.isEmpty())
            {
                Set<String> set = h.keySet();
                Iterator<String> itr = set.iterator();
                while (itr.hasNext())
                {
                    String str = itr.next();
                    System.out.println(str + ": " + h.get(str));
                    query.setParameter(str, h.get(str));
                }
            }
            
            this.commandes = query.list();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
