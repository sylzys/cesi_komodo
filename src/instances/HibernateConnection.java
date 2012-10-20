package instances;

import controllers.Connect;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class HibernateConnection {
	private static HibernateConnection instance;
	private static Session session; 
	private static SessionFactory sessionFactory;
        private static String confHib;
        public static boolean online;
	
	public static Session getSession() {
		return HibernateConnection.session;
	}

	public static void setSession(Session session) {
		HibernateConnection.session = session;
	}

	private HibernateConnection()
	{
            Logger log = Logger.getLogger("org.hibernate");
            log.setLevel(Level.WARNING);
            Connect connect = new Connect();
            boolean status = connect.InitConnect();
            if(status == true)
            {
                online = true;
                confHib = "/config/online.xml";
                if(HibernateConnection.online == true)
                {
                    ThreadOnline thread = new ThreadOnline();
                    // Activation du Thread
                    thread.start();
                    // tant que le thread est en vie...
                   if(thread.isAlive() == false) 
                    {
                      // faire un traitement...
                      System.out.println("Fin du thread");
//                      try {
//                        // et faire une pause
//                        Thread.sleep(1000);
//                      }
//                      catch (InterruptedException ex) { 
//                          System.out.println(ex.toString());
//                      }
                    }
                }
            }
            else
            {
                online = false;
                confHib = "/config/offline.xml";
                //Création du fichier pour enregistrer les requetes
                try
                {
                    File fichier = new java.io.File("ressources/requetes.sql");
                    if(fichier.exists() == false)
                    {
                        fichier.createNewFile();
                    }
                }
                catch (IOException e)
                {
                    System.out.println(e);
                } 
            }
            //Chargement du fichier de configuration de hibernate
            HibernateConnection.sessionFactory = new AnnotationConfiguration().configure(confHib).buildSessionFactory();
            //Ouverture de la session
            HibernateConnection.session = HibernateConnection.sessionFactory.openSession();
	}
	
	public static HibernateConnection getInstance()
	{
                //Si la classe n'est pas instanciée
		if(instance == null)
		{			
			instance = new HibernateConnection();
		}
                //On renvoie l'instance
		return instance;
	}
	
	public static void closeConnection()
	{
		//Vidage mémoire
		HibernateConnection.session.flush();
		HibernateConnection.session.close();
	}
}
