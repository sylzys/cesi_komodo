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
import models.Nomenclature;
import models.Satisfaction;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;

public class SatisfactionInstance {

    private static SatisfactionInstance instance;
    private List<Satisfaction> satisfactions;
    private String where;
    private Hashtable h;
    private Satisfaction satisfaction;

    /**
     * Constructeur prive
     */
    private SatisfactionInstance() {
        super();
    }

    /**
     * Singleton
     *
     * @return
     */
    public static synchronized SatisfactionInstance getInstance() {
        if (instance == null)
        {
            instance = new SatisfactionInstance();
        }

        return instance;
    }

    public synchronized List<Satisfaction> GetSatisfactions(String where, Hashtable h) {
        this.where = where;
        this.h = h;
        chargerDepuisBaseDeDonnees();
        return satisfactions;
    }
    
    public synchronized void setSatisfaction(Satisfaction satis) {
        this.satisfaction = satis;
    }
    
    

    /**
     * Bouchon.
     *
     * Dans un vrai programme, ces donnees seraient chargees depuis la base.
     */
    private void chargerDepuisBaseDeDonnees() {

        if (satisfactions == null)
        {
            //return;
            satisfactions = new ArrayList<Satisfaction>();
        }
        else
        {
            satisfactions.clear();
        }

        HibernateConnection connection = HibernateConnection.getInstance();
        String sql = "from satisfaction ";
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
                    query.setParameter(str, h.get(str));
                }
            }
            
            this.satisfactions = query.list();
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

            HibernateConnection.getSession().save(this.satisfaction);   
            tx.commit();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    
    
    
}
