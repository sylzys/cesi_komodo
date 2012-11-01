package instances;

import controllers.Synchro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import models.Client;
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
    
    public void ajouterDansBaseDeDonnées(){
            	try
        {            
            Transaction tx = HibernateConnection.getSession().beginTransaction();
            System.out.println("Nouvel enregistrement en cours d'insertion ...");
            
            HibernateConnection.getSession().save(this.client);
           // System.out.println(tx.wasCommitted());       
            tx.commit();
            System.out.println("Insertion de l'enregistrement terminé");
            //POUR VERIFIER SI LE CLIENT N'EST PAS EN LIGNE / SI C'EST LE CAS ON ECRIT LA REQUETE DANS UN FICHIER
            if(HibernateConnection.online == false)
            {
                  Synchro writereq = new Synchro();
                  writereq.SaveReq("INSERT INTO client (clinom) VALUES ('"+ this.client.getClinom() +"')", -1);
            }
        }
        catch(HibernateException | IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
