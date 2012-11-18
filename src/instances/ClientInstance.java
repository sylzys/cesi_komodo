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
import models.Interlocuteur;
import models.ParamSync;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;

public class ClientInstance {

    private static ClientInstance instance;
    private List<Client> clients;
    private String where;
    private Hashtable h;
    private Client client;
    private Synchro writereq = new Synchro();

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
        System.out.println("Loading from DB");
        chargerDepuisBaseDeDonnees();
        return clients;
    }

    public synchronized void setClient(Client cli) {
        this.client = cli;
    }

    public void chargerDepuisBaseDeDonnees() {

        if (clients == null)
        {
            //return;
            System.out.println("new client");
            clients = new ArrayList<Client>();
        }
        else
        {
            System.out.println("clearing client");
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
            if (!h.isEmpty())
            {
                System.out.println("got parameters");
                Set<String> set = h.keySet();
                Iterator<String> itr = set.iterator();
                while (itr.hasNext())
                {
                    String str = itr.next();
                    System.out.println(str + ": " + h.get(str));
                    query.setParameter(str, h.get(str));
                    System.out.println("setting params");
                }
            }
            System.out.println("QUERY : " + query.getQueryString());// query.toString());
            this.clients = query.list();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    public synchronized Boolean updaterBaseDeDonnees(Client cli) {
        if (HibernateConnection.online == false)
        {
            ParamSync param = new ParamSync();
            param.setClinom(cli.getClinom());
            if(cli.isClisuppr() == true)
            {
                param.setType("Suppression");
            }
            else
            {
                param.setType("Mise à jour");   
            }
            Synchro sync = new Synchro();
            sync.objSerializable(cli, param);
        }       
        try
        {
            Transaction tx = HibernateConnection.getSession().beginTransaction();
            HibernateConnection.getSession().update(cli);
            tx.commit();
            return true;
        }
        catch (Exception e)
        {
            System.out.println("Update failed");
            return false;
        }       
    }

    public Boolean ajouterDansBaseDeDonnees() {
        if (HibernateConnection.online == false)
        {
            ParamSync param = new ParamSync();
            param.setClinom(this.client.getClinom());
            param.setType("Ajout");
            Synchro sync = new Synchro();
            sync.objSerializable(this.client, param);
        }        
        try
        {
            Transaction tx = HibernateConnection.getSession().beginTransaction();
            HibernateConnection.getSession().save(this.client);
            tx.commit();
            return true;
        }
        catch (HibernateException e)
        {
            System.out.println(e);
            return false;
        }
    }
}
