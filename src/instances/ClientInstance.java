package instances;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import models.Client;
import org.hibernate.Query;

public class ClientInstance {

    private static ClientInstance instance;
    private List<Client> clients;
    private String where;
    private Hashtable h;

    /**
     * Constructeur prive
     */
    private ClientInstance() {
        super();
    }

    /**
     * Singleton
     *
     * @return
     */
    public static synchronized ClientInstance getInstance() {
        if (instance == null)
        {
            instance = new ClientInstance();
        }

        return instance;
    }

    public synchronized List<Client> GetClients(String where, Hashtable h) {
        this.where = where;
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
            clients = new ArrayList<Client>();
        }
        else
        {
            clients.clear();
        }

        HibernateConnection connection = HibernateConnection.getInstance();
        String sql = "from Client ";
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
            
            this.clients = query.list();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
