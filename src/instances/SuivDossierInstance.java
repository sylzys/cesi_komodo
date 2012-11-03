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
    
    
    
     public void ajouterDansBaseDeDonnées() {
        try
        {
            Transaction tx = HibernateConnection.getSession().beginTransaction();
            System.out.println("Nouvel enregistrement en cours d'insertion ...");

            HibernateConnection.getSession().save(this.suivdoss);
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
