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

public class AllnomenclaturelistInstance {

    private static AllnomenclaturelistInstance instance;
    private List<Nomenclaturelist> nomenclaturelists;
    private Nomenclaturelist nomenclaturelist;

    /**
     * Constructeur prive
     */
    private AllnomenclaturelistInstance() {
        super();
    }

    /**
     * Singleton
     *
     * @return
     */
    public static synchronized AllnomenclaturelistInstance getInstance() {
        if (instance == null)
        {
            instance = new AllnomenclaturelistInstance();
        }

        return instance;
    }

    public synchronized List<Nomenclaturelist> GetNomenclatureslist() {
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

        try
        {
            Query query = connection.getSession().createQuery(sql);//"from Client where utiid = :utiid");
            //query.setParameter("utiid", 1);
            
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
