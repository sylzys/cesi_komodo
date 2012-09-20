/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cesi_komodo;
import views.Fenetre;
import models.*;
import org.hibernate.*;
import java.util.List;
import org.hibernate.cfg.AnnotationConfiguration;
/**
 *
 * @author sylv
 */
public class Cesi_komodo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Fenetre fen = new Fenetre();
        
        //SELECT AVEC CLAUSE WHERE
        HibernateConnection connection = HibernateConnection.getInstance();
        try {
                Query query = connection.getSession().createQuery("from Utilisateur where utiid = :utiid");
                query.setParameter("utiid", 1);
                List<Utilisateur> userlist = query.list();
                for (Utilisateur uti : userlist) 
                {
                    System.out.println("Name : " + uti.getUtinom());
                    System.out.println("Prénom : " + uti.getUtiprenom());
                    System.out.println("Login : " + uti.getUtilogin());
                    System.out.println("MDP : " + uti.getUtimdp());
                    System.out.println("Mail : " + uti.getUtimail());
                    System.out.println("Tél : " + uti.getUtitel());
                    System.out.println("Supprimé ? : " + uti.getUtisuppr());
                    System.out.println("Date de conenxion : " + uti.getUtidtelog());
                }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            connection.closeConnection();
        }
        
        /******EXEMPLE INSERT*****************/
        Session session = null;
    	Transaction tx = null;
    	try{
            SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            session =sessionFactory.openSession();
            tx = session.beginTransaction();
            System.out.println("Nouvel enregistrement en cours d'insertion ...");
            Utilisateur uti = new Utilisateur();
            uti.setUtinom("newuser");
            session.save(uti); 		  
            tx.commit();
            System.out.println("Insertion de l'enregistrement terminé");		  
    	}
    	catch(Exception e){
            System.out.println(e.getMessage());
		}
    	finally{
            session.flush();
            session.close();
    	}
		
		
		/******EXEMPLE UPDATE*****************/
//        Session session = null;
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
//    	finally{
//			// Actual contact insertion will happen at this step
//			session.flush();
//			session.close();
//    	}
    }
}
