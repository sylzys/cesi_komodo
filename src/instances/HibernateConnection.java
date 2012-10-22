package instances;

import controllers.Synchro;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

//Singleton class connexion hibernate
public class HibernateConnection {
	private static HibernateConnection instance;
	private static Session session; 
	private static SessionFactory sessionFactory;
        private static String confHib;
        public static boolean online;
	
	public static Session getSession() 
        {
		return HibernateConnection.session;
	}

	public static void setSession(Session session) 
        {
		HibernateConnection.session = session;
	}
        
        public static void connectOffline()
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
        
	public static void connectOnline()
	{
                online = true;
                confHib = "/config/online.xml";
	}
        
        //Constructeur privé
	private HibernateConnection()
	{
            //Désactivation du log Infos hibernate
            Logger log = Logger.getLogger("org.hibernate");
            log.setLevel(Level.WARNING);
            //Test de la connection
            Synchro connect = new Synchro();
            boolean status = connect.InitConnect();
            //Si en ligne
            if(status == true)
            {
                //On charge la config online.xml
               connectOnline();
               System.out.println("En ligne");
            }
            else
            {
                //On charge le fichier offline.xml
               connectOffline();
               System.out.println("Hors ligne");
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
                HibernateConnection.sessionFactory.close();
		HibernateConnection.session.flush();
		HibernateConnection.session.close();
	}
        
        //Nouvelle connection
        public static void newConnect(boolean bool)
        {
            //On ferme la connection
            closeConnection();
            //Si on veut une connection en ligne
            if(bool == true)
            {
                connectOnline();
                HibernateConnection.online = true;
            }
            //Sinon
            else
            {
                //Connection hors ligne
                connectOffline();
                HibernateConnection.online = false;
            }
            //Reload instance
            instance = new HibernateConnection();
        }
        public static void offline()
        {
            HibernateConnection.online = false;
            //On ferme la connection
            closeConnection();
            //Désactivation du log Infos hibernate
            Logger log = Logger.getLogger("org.hibernate");
            log.setLevel(Level.WARNING);
            connectOffline();
            System.out.println("Mode hors ligne connecté");
            //Chargement du fichier de configuration de hibernate
            HibernateConnection.sessionFactory = new AnnotationConfiguration().configure(confHib).buildSessionFactory();
            //Ouverture de la session
            HibernateConnection.session = HibernateConnection.sessionFactory.openSession();
        }
        public static void online()
        {
            HibernateConnection.online = true;
            //On ferme la connection
            closeConnection();
            //Désactivation du log Infos hibernate
            Logger log = Logger.getLogger("org.hibernate");
            log.setLevel(Level.WARNING);
            connectOnline();
            System.out.println("Mode en ligne connecté");
            //Chargement du fichier de configuration de hibernate
            HibernateConnection.sessionFactory = new AnnotationConfiguration().configure(confHib).buildSessionFactory();
            //Ouverture de la session
            HibernateConnection.session = HibernateConnection.sessionFactory.openSession();
        }
}
