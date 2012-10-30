package instances;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import models.DetailDmd;
import org.hibernate.Query;

public class DetailDmdInstance {

    private static DetailDmdInstance instance;
    private List<DetailDmd> clients;
    private String where;
    private Hashtable h;

    /**
     * Constructeur prive
     */
    private DetailDmdInstance() {
        super();
    }

    /**
     * Singleton
     *
     * @return
     */
    public static synchronized DetailDmdInstance getInstance() {
        if (instance == null)
        {
            instance = new DetailDmdInstance();
        }

        return instance;
    }

    
      public List<DetailDmd> GetDetaildemande(String where, Hashtable h) {
         this.where = where;
        this.h = h;
        System.out.println("CHARGER DPS BDD"+h+" --- "+where);
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
            clients = new ArrayList<DetailDmd>();
        }
        else
        {
            clients.clear();
        }
        System.out.println("INSTANCE");
        HibernateConnection connection = HibernateConnection.getInstance();
        System.out.println("INSTANCE OK");
        String sql = " from detailsdemande ";
        if (!where.isEmpty())
        {
            sql += where;
        }
        where = "where demandeid = 2";
        try
        {
            System.out.println("GETTING SESSION"+sql+" =>>> "+where);
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