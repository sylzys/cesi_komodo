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
import models.Nomenclaturelist;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;

public class NomenclaturelistInstance {

    private static NomenclaturelistInstance instance;
    private List<Nomenclaturelist> nomenclaturelists;
    private String where;
    private Hashtable h;
    private Nomenclaturelist nomenclaturelist;

    /**
     * Constructeur prive
     */
    private NomenclaturelistInstance() {
        super();
    }

    /**
     * Singleton
     *
     * @return
     */
    public static synchronized NomenclaturelistInstance getInstance() {
        if (instance == null)
        {
            instance = new NomenclaturelistInstance();
        }

        return instance;
    }

    public synchronized List<Nomenclaturelist> GetNomenclatureslist(String where, Hashtable h) {
        this.where = where;
        this.h = h;
        chargerDepuisBaseDeDonnees();
        return nomenclaturelists;
    }
    
    public synchronized void setNomenclatureslists(Nomenclaturelist nmc) {
        this.nomenclaturelist = nmc;
    }
    
    

    /**
     * Bouchon.
     *
     * Dans un vrai programme, ces donnees seraient chargees depuis la base.
     */
    private void chargerDepuisBaseDeDonnees() {

        if (nomenclaturelists == null)
        {
            //return;
            nomenclaturelists = new ArrayList<Nomenclaturelist>();
        }
        else
        {
            nomenclaturelists.clear();
        }

        HibernateConnection connection = HibernateConnection.getInstance();
        String sql = "from Nomenclaturelist ";
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
            
            this.nomenclaturelists = query.list();
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

            HibernateConnection.getSession().save(this.nomenclaturelist);    
            tx.commit();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    
    
    
}
