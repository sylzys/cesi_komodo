package instances;

import controllers.Synchro;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import models.Client;
import models.Demande;
import models.Devis;
import models.ParamSync;
import org.hibernate.Query;
import org.hibernate.Transaction;

public class DevisInstance {

    private static DevisInstance instance;
    private List<Devis> devis;
    private String where;
    private Hashtable h;
    private Devis dvis;

    /**
     * Constructeur prive
     */
    private DevisInstance() {
        super();
    }

    /**
     * Singleton
     *
     * @return
     */
    public static synchronized DevisInstance getInstance() {
        if (instance == null)
        {
            instance = new DevisInstance();
        }

        return instance;
    }

    public synchronized List<Devis> Getdevis(String where, Hashtable h) {
        this.where = where;
        this.h = h;
        chargerDepuisBaseDeDonnees();
        return devis;
    }
    
    public synchronized void setDevis(Devis dvis) {
        this.dvis = dvis;
    }

    /**
     * Bouchon.
     *
     * Dans un vrai programme, ces donnees seraient chargees depuis la base.
     */
    private void chargerDepuisBaseDeDonnees() {

        if (devis == null)
        {
            //return;
            devis = new ArrayList<Devis>();
        }
        else
        {
            devis.clear();
        }

        HibernateConnection connection = HibernateConnection.getInstance();
        String sql = "from Devis ";
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
            
            this.devis = query.list();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        
        
    }
 
    public boolean ajouterDansBaseDeDonnees() {
        if (HibernateConnection.online == false)
        {
            ParamSync param = new ParamSync();
            param.setClinom(this.dvis.getInterid(), "inter");
            param.setType("Ajout");
            Synchro sync = new Synchro();
            sync.objSerializable(this.dvis, param);
        }  
        try
        {
            Transaction tx = HibernateConnection.getSession().beginTransaction();
            HibernateConnection.getSession().save(this.dvis);      
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
