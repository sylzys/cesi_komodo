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
    private int where;
    private String clause;
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

    
      public List<DetailDmd> GetDetaildemande(String clause, int demandeid, Hashtable h) {
          this.where = 0;
         this.where = demandeid;
         this.clause = clause;
        this.h = h;
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
        HibernateConnection connection = HibernateConnection.getInstance();       
        String sql = " from detailsdemande ";
        if (where != 0)
        {
            sql += "where "+clause+"= :"+clause;
        }

        try
        {
              Query query = connection.getSession().createQuery(sql);
              query.setParameter(clause, where);
            if (!h.isEmpty())
            {
                Set<String> set = h.keySet();
                Iterator<String> itr = set.iterator();
                while (itr.hasNext())
                {
                    String str = itr.next();
                    query.setParameter(str, h.get(str));
                }
            }
            this.clients = query.list();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

  
}
