package instances;

import controllers.Synchro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import models.Client;
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
            System.out.println("QUERY : " +query.getQueryString());// query.toString());
            this.clients = query.list();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    public Boolean ajouterDansBaseDeDonnées() {
        try
        {
            Transaction tx = HibernateConnection.getSession().beginTransaction();
            System.out.println("Nouvel enregistrement en cours d'insertion ...");

            HibernateConnection.getSession().save(this.client);
            // System.out.println(tx.wasCommitted());       
            tx.commit();
            //POUR VERIFIER SI LE CLIENT N'EST PAS EN LIGNE / SI C'EST LE CAS ON ECRIT LA REQUETE DANS UN FICHIER
            if (HibernateConnection.online == false)
            {
                Synchro writereq = new Synchro();
                writereq.SaveReq("INSERT INTO client (utiid, uti_utiid, clinom, cliadresse, clicp, clitel, clifax, climail,"
                        + "cliactivite, clisiret, clica, clisite, clidg, clietat, cliacces, clinaf, clisiren, clisuppr)"
                        + " VALUES ("+this.client.getUtiid()+","+this.client.getUti_utiid()+","
                        + "'"+this.client.getClinom()+"','"+this.client.getCliadresse()+"','"+this.client.getClicp()+"','"
                        + ""+this.client.getClitel()+"','"+this.client.getClifax()+"','"
                        + ""+this.client.getClimail()+"','"+this.client.getCliactivite()+"','"+this.client.getClisiret()+"',"+this.client.getClica()+",'"
                        + ""+this.client.getClisite()+"','"+this.client.getClidg()+"',"+this.client.getClietat()+","
                        + ""+this.client.isCliacces()+",'"+this.client.getClinaf()+"','"
                        + ""+this.client.getClisiren()+"','f')"
                        + "", -1, this.client.getClinom());
            }
            return true;
        }
        catch (HibernateException | IOException e)
        {
            return false;
        }
    }
}
