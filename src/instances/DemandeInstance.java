package instances;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import models.Client;
import models.Demande;
import org.hibernate.Query;

public class DemandeInstance {

    private static DemandeInstance instance;
    private List<Demande> demandes;
    private String where;
    private Hashtable h;

    /**
     * Constructeur prive
     */
    private DemandeInstance() {
        super();
    }

    /**
     * Singleton
     *
     * @return
     */
    public static synchronized DemandeInstance getInstance() {
        if (instance == null)
        {
            instance = new DemandeInstance();
        }

        return instance;
    }

    public synchronized List<Demande> GetDemandes(String where, Hashtable h) {
        this.where = where;
        this.h = h;
        chargerDepuisBaseDeDonnees();
        return demandes;
    }

    /**
     * Bouchon.
     *
     * Dans un vrai programme, ces donnees seraient chargees depuis la base.
     */
    private void chargerDepuisBaseDeDonnees() {

        if (demandes == null)
        {
            //return;
            demandes = new ArrayList<Demande>();
        }
        else
        {
            demandes.clear();
        }

        HibernateConnection connection = HibernateConnection.getInstance();
        String sql = "from demande ";
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
            
            this.demandes = query.list();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
