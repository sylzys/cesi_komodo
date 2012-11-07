package instances;

import controllers.Synchro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import models.Client;
import models.Demande;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;

public class DemandeInstance {

    private static DemandeInstance instance;
    private List<Demande> demandes;
    private String where;
    private Hashtable h;
    private Demande demande;

    /**
     * Constructeur prive
     */
    private DemandeInstance() {
        super();
    }

    /**
     * Singleton
     *
     * @return
     */
    public static synchronized DemandeInstance getInstance() {
        if (instance == null)
        {
            instance = new DemandeInstance();
        }

        return instance;
    }

    public synchronized List<Demande> GetDemandes(String where, Hashtable h) {
        this.where = where;
        this.h = h;
        chargerDepuisBaseDeDonnees();
        return demandes;
    }
    
    public synchronized void setDemandes(Demande dmd) {
        this.demande = dmd;
    }
    
    

    /**
     * Bouchon.
     *
     * Dans un vrai programme, ces donnees seraient chargees depuis la base.
     */
    private void chargerDepuisBaseDeDonnees() {

        if (demandes == null)
        {
            //return;
            demandes = new ArrayList<Demande>();
        }
        else
        {
            demandes.clear();
        }

        HibernateConnection connection = HibernateConnection.getInstance();
        String sql = "from demande ";
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
            
            this.demandes = query.list();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
    
    
    public void ajouterDansBaseDeDonnees() {
        try
        {
            Transaction tx = HibernateConnection.getSession().beginTransaction();
            System.out.println("Nouvel enregistrement en cours d'insertion ...");

            HibernateConnection.getSession().save(this.demande);
            // System.out.println(tx.wasCommitted());       
            tx.commit();
            System.out.println("Insertion de l'enregistrement terminé");
            //POUR VERIFIER SI LE CLIENT N'EST PAS EN LIGNE / SI C'EST LE CAS ON ECRIT LA REQUETE DANS UN FICHIER
            if (HibernateConnection.online == false)
            {
//                Synchro writereq = new Synchro();
//                writereq.SaveReq("INSERT INTO demande (clinom) VALUES ('" + this.demande.getCliid() + "')", -1, this.demande.getUtiid());
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    
    
    
}
