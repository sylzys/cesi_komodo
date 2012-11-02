package instances;

import controllers.Synchro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import models.Client;
import models.ClientComm;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;

public class clientCommInstance {

    private static clientCommInstance instance;
    private List<ClientComm> clients;
    private String where;
    private Hashtable h;
    private Client client;
    /**
     * Constructeur prive
     */
    private clientCommInstance() {
        super();
    }

    /**
     * Singleton
     *
     * @return
     */
    public static synchronized clientCommInstance getInstance() {
        if (instance == null)
        {
            instance = new clientCommInstance();
        }

        return instance;
    }

    public synchronized List<ClientComm> GetClients(String where, Hashtable h) {
        this.where = where;
        this.h = h;
        chargerDepuisBaseDeDonnees();
        return clients;
    }
    
    public synchronized void setClient(Client cli) {      
       this.client = cli;
    }

    private void chargerDepuisBaseDeDonnees() {

        if (clients == null)
        {
            //return;
            clients = new ArrayList<ClientComm>();
        }
        else
        {
            clients.clear();
        }

        HibernateConnection connection = HibernateConnection.getInstance();
        String sql = "from client_comm ";
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
