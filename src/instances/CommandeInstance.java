package instances;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import models.Client;
import models.Commande;
import models.Demande;
import org.hibernate.Query;
import org.hibernate.Transaction;

public class CommandeInstance {

    private static CommandeInstance instance;
    private List<Commande> commandes;
    private String where;
    private Hashtable h;
    private Commande commande;

    /**
     * Constructeur prive
     */
    private CommandeInstance() {
        super();
    }

    /**
     * Singleton de Commande
     *
     * @return
     */
    public static synchronized CommandeInstance getInstance() {
        if (instance == null)
        {
            instance = new CommandeInstance();
        }

        return instance;
    }
    
    public synchronized void setCommande(Commande cmd) {
        this.commande = cmd;
    }

    public synchronized List<Commande> GetCommandes(String where, Hashtable h) {
        this.where = where;
        this.h = h;
        //on récupère le "where", et les paramètres
        chargerDepuisBaseDeDonnees();
        //on retourne la liste correspondant à la requete
        return commandes;
    }

    
    private void chargerDepuisBaseDeDonnees() {

        if (commandes == null)
        {
            //si pas déjà fait de requetes, on crée une liste vide
            commandes = new ArrayList<Commande>();
        }
        else //sinon on vide la liste déjà créee
        {
            commandes.clear();
        }

        HibernateConnection connection = HibernateConnection.getInstance();
        //requete de base pour hibernate
        String sql = "from Commande ";
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
                    System.out.println(str + ": " + h.get(str));
                    query.setParameter(str, h.get(str));
                }
            }
            //on met le resultat dans la liste renvoyée
            this.commandes = query.list();
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

            HibernateConnection.getSession().save(this.commande);
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
