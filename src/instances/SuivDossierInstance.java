package instances;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import models.Suivdossier;
import org.hibernate.Query;

/**
 *
 * @author BoBo
 */
public class SuivDossierInstance {
    private static SuivDossierInstance instance;
    private List<Suivdossier> suivdossier;
    private String where;
    private Hashtable h;
    
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
            
            this.suivdossier = query.list();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
    
}
