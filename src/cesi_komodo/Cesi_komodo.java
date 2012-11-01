/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cesi_komodo;
import controllers.Synchro;
import java.io.IOException;
import views.Fenetre;
import instances.HibernateConnection;
import instances.ThreadOnline;
import models.Utilisateur;
import org.hibernate.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Client;
import models.Interlocuteur;
//import org.hibernate.cfg.AnnotationConfiguration;
/**
 *
 * @author sylv
 */
public class Cesi_komodo {
    /**
     * @param args the command line arguments
     */
    public static Fenetre fenetre;
    public static void main(String[] args) {
        HibernateConnection hc = HibernateConnection.getInstance();
        ThreadOnline thread = new ThreadOnline();
        // Activation du Thread
        thread.start();
        fenetre.getInstance();
        
        /****** EXEMPLE SELECT AVEC CLAUSE WHERE *****************/
//        try 
//        {
//            Query query = HibernateConnection.getSession().createQuery("from Interlocuteur where interid = :interid");
//            query.setParameter("interid", 1);
//            List<Interlocuteur> interlist = query.list();
//            for (Interlocuteur inter : interlist) 
//            {
//                System.out.println("Name : " + inter.getInternom());
////                System.out.println("Prénom : " + uti.getUtiprenom());
////                System.out.println("Login : " + uti.getUtilogin());
////                System.out.println("MDP : " + uti.getUtimdp());
////                System.out.println("Mail : " + uti.getUtimail());
////                System.out.println("Tél : " + uti.getUtitel());
////                System.out.println("Supprimé ? : " + uti.getUtisuppr());
////                System.out.println("Date de conenxion : " + uti.getUtidtelog());
//            }
//        }
//        catch(Exception e)
//        {
//            System.out.println(e.getMessage());
//        }
        
        /****** EXEMPLE INSERT *****************/
//    	try
//        {            
//            Transaction tx = HibernateConnection.getSession().beginTransaction();
//            System.out.println("Nouvel enregistrement en cours d'insertion ...");
//            Client cli = new Client();
//            cli.setClinom("testecrirereq");
//            cli.setUtiid(1);
//            cli.setUti_utiid(1);
//            HibernateConnection.getSession().save(cli);
//            System.out.println(tx.wasCommitted());       
//            tx.commit();
//            System.out.println("Insertion de l'enregistrement terminé");
//            //POUR VERIFIER SI LE CLIENT N'EST PAS EN LIGNE / SI C'EST LE CAS ON ECRIT LA REQUETE DANS UN FICHIER
//            if(HibernateConnection.online == false)
//            {
//                  Synchro writereq = new Synchro();
//                  //Requete en sql + id de l'interlocuteur (interid) si client concerné sinon mettre -1
//                  writereq.SaveReq("UPDATE commande SET cometat=80 WHERE utiid = 1", 1);
//            }
//        }
//        catch(HibernateException | IOException e)
//        {
//            System.out.println(e.getMessage());
//        }
		
		
		/******EXEMPLE UPDATE*****************/
//    	Transaction tx = null;
//
//    	try{
//			  SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
//			  session =sessionFactory.openSession();
//			  tx = session.beginTransaction();
//			  System.out.println("Updating Record");
//			  Utilisateur uti = new Utilisateur();
//			  uti.setUtiid(10);
//			  uti.setUtinom("test");
//			  session.update(uti); 		  
//			  tx.commit();
//			  System.out.println("Done");
//			  
//    	}
//    	catch(Exception e){
//			  System.out.println(e.getMessage());
//		}
    }
}
