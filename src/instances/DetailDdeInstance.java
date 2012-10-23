package instances;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import models.Demande;
import org.hibernate.Query;

public class DetailDdeInstance {

    private static DetailDdeInstance instance;
    private List<Demande> clients;
    private String where;
    private Hashtable h;

    /**
     * Constructeur prive
     */
    private DetailDdeInstance() {
        super();
    }

    /**
     * Singleton
     *
     * @return
     */
    public static synchronized DetailDdeInstance getInstance() {
        if (instance == null)
        {
            instance = new DetailDdeInstance();
        }

        return instance;
    }

    
      public List<Demande> GetDetaildemande(String where, Hashtable h) {
         this.where = where;
        this.h = h;
        System.out.println("CHARGER DPS BDD");
        chargerDepuisBaseDeDonnees();
        return clients;
    }

    /**
     * Bouchon.
     *
     * Dans un vrai programme, ces donnees seraient chargees depuis la base.
     */
    private void chargerDepuisBaseDeDonnees() {

        if (clients == null)
        {
            //return;
            clients = new ArrayList<Demande>();
        }
        else
        {
            clients.clear();
        }
System.out.println("INSTANCE");
        HibernateConnection connection = HibernateConnection.getInstance();
        System.out.println("INSTANCE OK");
        String sql = "from demande ";
        if (!where.isEmpty())
        {
            sql += where;
        }

        try
        {
            System.out.println("GETTING SESSION");
            Query query = connection.getSession().createQuery(sql);//"from Detailcde where utiid = :utiid");
            //query.setParameter("utiid", 1);
           System.out.println("GETTING SESSION OK");
            if (!h.isEmpty())
            {
                Set<String> set = h.keySet();
                Iterator<String> itr = set.iterator();
                while (itr.hasNext())
                {
                    String str = itr.next();
                    System.out.println("PARAIMS " + str + ": " + h.get(str));
                    query.setParameter(str, h.get(str));
                }
            }
            System.out.println("GETTING SQL RESULTS");
            this.clients = query.list();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

  
}
