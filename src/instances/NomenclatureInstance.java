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
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;

public class NomenclatureInstance {

    private static NomenclatureInstance instance;
    private List<Nomenclature> nomenclatures;
    private String where;
    private Hashtable h;
    private Nomenclature nomenclature;

    /**
     * Constructeur prive
     */
    private NomenclatureInstance() {
        super();
    }

    /**
     * Singleton
     *
     * @return
     */
    public static synchronized NomenclatureInstance getInstance() {
        if (instance == null)
        {
            instance = new NomenclatureInstance();
        }

        return instance;
    }

    public synchronized List<Nomenclature> GetNomenclatures(String where, Hashtable h) {
        this.where = where;
        this.h = h;
        chargerDepuisBaseDeDonnees();
        return nomenclatures;
    }
    
    public synchronized void setDemandes(Nomenclature nmc) {
        this.nomenclature = nmc;
    }
    
    

    /**
     * Bouchon.
     *
     * Dans un vrai programme, ces donnees seraient chargees depuis la base.
     */
    private void chargerDepuisBaseDeDonnees() {

        if (nomenclatures == null)
        {
            //return;
            nomenclatures = new ArrayList<Nomenclature>();
        }
        else
        {
            nomenclatures.clear();
        }

        HibernateConnection connection = HibernateConnection.getInstance();
        String sql = "from nomenclature ";
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
            
            this.nomenclatures = query.list();
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

            HibernateConnection.getSession().save(this.nomenclature);   
            tx.commit();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    
    
    
}
