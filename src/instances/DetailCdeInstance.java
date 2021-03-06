package instances;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import models.DetailCommande;
import org.hibernate.Query;

public class DetailCdeInstance {

    private static DetailCdeInstance instance;
    private List<DetailCommande> clients;
    private String where;
    private Hashtable h;

    /**
     * Constructeur prive
     */
    private DetailCdeInstance() {
        super();
    }

    /**
     * Singleton
     *
     * @return
     */
    public static synchronized DetailCdeInstance getInstance() {
        if (instance == null)
        {
            instance = new DetailCdeInstance();
        }

        return instance;
    }

    public synchronized List<DetailCommande> GetDetailcde(String where, Hashtable h) {
        this.where = where;
        this.h = h;
        chargerDepuisBaseDeDonnees();
        return clients;
    }

    
    private void chargerDepuisBaseDeDonnees() {

        if (clients == null)
        {
            //return;
            clients = new ArrayList<DetailCommande>();
        }
        else
        {
            clients.clear();
        }

        HibernateConnection connection = HibernateConnection.getInstance();
        String sql = "from detailcommande ";
        if (!where.isEmpty())
        {
            sql += " WHERE " + where;
        }

        try
        {
            Query query = connection.getSession().createQuery(sql);//"from Detailcde where utiid = :utiid");
            //query.setParameter("utiid", 1);
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
