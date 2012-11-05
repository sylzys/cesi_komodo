package instances;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import models.GetAlerte;
import org.hibernate.Query;

/**
 *
 * @author BoBo
 */
public class AlerteInstance {
    private static AlerteInstance instance;
    private List<GetAlerte> alertes;
    private String where;
    private Hashtable h;
    
    private AlerteInstance() {
        super();
    }
    
    public static synchronized AlerteInstance getInstance() {
        if (instance == null)
        {
            instance = new AlerteInstance();
        }

        return instance;
    }

    public synchronized List<GetAlerte> GetAlertes(String where, Hashtable h) {
        this.where = where;
        this.h = h;
        chargerDepuisBaseDeDonnees();
        return alertes;
    }
    
    private void chargerDepuisBaseDeDonnees() {

        if (alertes == null)
        {
            //return;
            alertes = new ArrayList<GetAlerte>();
        }
        else
        {
            alertes.clear();
        }

        HibernateConnection connection = HibernateConnection.getInstance();
        String sql = "from getalerte ";
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
                    System.out.println(str + ": " + h.get(str));
                    query.setParameter(str, h.get(str));
                }
            }
            
            this.alertes = query.list();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
    
}
