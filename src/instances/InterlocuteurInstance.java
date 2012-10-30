package instances;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import models.Client;
import models.Commande;
import models.Interlocuteur;
import org.hibernate.Query;

public class InterlocuteurInstance {

    private static InterlocuteurInstance instance;
    private List<Interlocuteur> inter;
    private String where;
    private Hashtable h;

    /**
     * Constructeur prive
     */
    private InterlocuteurInstance() {
        super();
    }

    /**
     * Singleton de Commande
     *
     * @return
     */
    public static synchronized InterlocuteurInstance getInstance() {
        if (instance == null)
        {
            instance = new InterlocuteurInstance();
        }

        return instance;
    }

    public synchronized List<Interlocuteur> GetInterlocuteurs(String where, Hashtable h) {
        this.where = where;
        this.h = h;
        //on récupère le "where", et les paramètres
        chargerDepuisBaseDeDonnees();
        //on retourne la liste correspondant à la requete
        return inter;
    }

    private void chargerDepuisBaseDeDonnees() {

        if (inter == null)
        {
            //si pas déjà fait de requetes, on crée une liste vide
            inter = new ArrayList<Interlocuteur>();
        }
        else //sinon on vide la liste déjà créee
        {
            inter.clear();
        }

        HibernateConnection connection = HibernateConnection.getInstance();
        //requete de base pour hibernate
        String sql = "from Interlocuteur ";
        //ajout du where si non vide
        if (!where.isEmpty())
        {
            sql += where;
        }

        try
        {
            Query query = connection.getSession().createQuery(sql);//"from Client where utiid = :utiid");
            //query.setParameter("utiid", 1);
            //on parse les parametres pour creer le query.setParameters
            if (!h.isEmpty())
            {
                Set<String> set = h.keySet();
                Iterator<String> itr = set.iterator();
                while (itr.hasNext())
                {
                    String str = itr.next();
                    System.out.println(" --> " + str + ": " + h.get(str));
                    query.setParameter(str, h.get(str));
                }
            }
            //on met le resultat dans la liste renvoyée
            this.inter = query.list();
           
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
