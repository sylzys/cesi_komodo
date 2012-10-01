package instances;

import java.util.ArrayList;
import java.util.List;
import models.Client;
import models.HibernateConnection;
import org.hibernate.Query;

public class ClientInstance {

    private static ClientInstance instance;
    private List<Client> clients;

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

    public synchronized List<Client> GetClients() {
        chargerDepuisBaseDeDonnees();
        return clients;
    }

    /**
     * Bouchon.
     *
     * Dans un vrai programme, ces donnees seraient chargees depuis la base.
     */
    private void chargerDepuisBaseDeDonnees() {

        if (clients != null)
        {
            return;
        }

        clients = new ArrayList<Client>();

        HibernateConnection connection = HibernateConnection.getInstance();
        try
        {
            Query query = connection.getSession().createQuery("from Client where utiid = :utiid");
            //query.setParameter("utiid", 1);
            query.setParameter("utiid", 1);
            this.clients = query.list();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
