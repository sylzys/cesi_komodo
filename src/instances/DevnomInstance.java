package instances;

import controllers.Synchro;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import models.Devnom;
import models.ParamSync;
import org.hibernate.Query;
import org.hibernate.Transaction;

public class DevnomInstance {

    private static DevnomInstance instance;
    private List<Devnom> devnom;
    private String where;
    private Hashtable h;
    private Devnom dvinom;

    /**
     * Constructeur prive
     */
    private DevnomInstance() {
        super();
    }

    /**
     * Singleton
     *
     * @return
     */
    public static synchronized DevnomInstance getInstance() {
        if (instance == null)
        {
            instance = new DevnomInstance();
        }

        return instance;
    }

    public synchronized List<Devnom> Getdevnom(String where, Hashtable h) {
        this.where = where;
        this.h = h;
        chargerDepuisBaseDeDonnees();
        return devnom;
    }
    
    public synchronized void setDevnom(Devnom dvinom) {
        this.dvinom = dvinom;
    }

    /**
     * Bouchon.
     *
     * Dans un vrai programme, ces donnees seraient chargees depuis la base.
     */
    private void chargerDepuisBaseDeDonnees() {

        if (devnom == null)
        {
            //return;
            devnom = new ArrayList<Devnom>();
        }
        else
        {
            devnom.clear();
        }

        HibernateConnection connection = HibernateConnection.getInstance();
        String sql = "from Devnom ";
        if (!where.isEmpty())
        {
            sql += where;
        }

        try
        {
            Query query = connection.getSession().createQuery(sql);//"from devis where utiid = :utiid");
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
            
            this.devnom = query.list();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        
        
    }
 
    public boolean ajouterDansBaseDeDonnees() {
        if (HibernateConnection.online == false)
        {
            
        }  
        try
        {
            Transaction tx = HibernateConnection.getSession().beginTransaction();
            HibernateConnection.getSession().save(this.dvinom);      
            tx.commit();
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    
    }
}
