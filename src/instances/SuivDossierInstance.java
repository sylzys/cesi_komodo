package instances;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import models.Demande;
import models.Suivdossier;
import org.hibernate.Query;
import org.hibernate.Transaction;

/**
 *
 * @author BoBo
 */
public class SuivDossierInstance {
    private static SuivDossierInstance instance;
    private List<Suivdossier> suivdossier;
    private String where;
    private Hashtable h;
    private Suivdossier suivdoss;
    
    private SuivDossierInstance() {
        super();
    }
    
    public static synchronized SuivDossierInstance getInstance() {
        if (instance == null)
        {
            instance = new SuivDossierInstance();
        }

        return instance;
    }

    public synchronized List<Suivdossier> GetSuivDossier(String where, Hashtable h) {
        this.where = where;
        this.h = h;
        chargerDepuisBaseDeDonnees();
        return suivdossier;
    }
    
    
    public synchronized void setSuivDossier(Suivdossier suivdossier) {
        this.suivdoss = suivdossier;
    }
    
    private void chargerDepuisBaseDeDonnees() {

        if (suivdossier == null)
        {
            //return;
            suivdossier = new ArrayList<Suivdossier>();
        }
        else
        {
            suivdossier.clear();
        }

        HibernateConnection connection = HibernateConnection.getInstance();
        String sql = "from Suivdossier ";
        if (!where.isEmpty())
        {
            sql += " WHERE " + where;
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
            
            this.suivdossier = query.list();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
    
    public void add() {
        try
        {
            Transaction tx = HibernateConnection.getSession().beginTransaction();

            HibernateConnection.getSession().save(suivdoss);       
            tx.commit();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void update(Suivdossier alerte) {
        Transaction tx = null;

        try
        {
            tx = HibernateConnection.getSession().beginTransaction();
            HibernateConnection.getSession().update(alerte); 
            tx.commit();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
     
}
