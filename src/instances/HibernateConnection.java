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
        private static boolean status;
        public static boolean savereseau;
        private static int nbInstances;
	
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
            status = connect.InitConnect();
            //Si en ligne
            if(status == true)
            {
                   //On charge la config online.xml
                   connectOnline();
            }
            else
            {
                //On charge le fichier offline.xml
               connectOffline();
            }
            //Chargement du fichier de configuration de hibernate
            HibernateConnection.sessionFactory = new AnnotationConfiguration().configure(confHib).buildSessionFactory();
            //Ouverture de la session
            HibernateConnection.session = HibernateConnection.sessionFactory.openSession();
            nbInstances = nbInstances + 1;
	}
	public static HibernateConnection getInstance()
	{
                //Si la classe n'est pas instanciée
		if(instance == null)
		{			
			instance = new HibernateConnection();
                        System.out.println("hibernate1");
		}
                //On renvoie l'instance
                
                        System.out.println("hibernate2");
                        System.out.println(instance);
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
            //Si on veut une connection en ligne
            if(bool == true)
            {
                connectOnline();
                online();
            }
            //Sinon
            else
            {
                //Connection hors ligne
                connectOffline();
                offline();
            }
        }
        public static void offline()
        {
            //On ferme la connection
            closeConnection();
            HibernateConnection.online = false;
            //Désactivation du log Infos hibernate
            Logger log = Logger.getLogger("org.hibernate");
            log.setLevel(Level.WARNING);
            connectOffline();
            //Chargement du fichier de configuration de hibernate
            HibernateConnection.sessionFactory = new AnnotationConfiguration().configure(confHib).buildSessionFactory();
            //Ouverture de la session
            HibernateConnection.session = HibernateConnection.sessionFactory.openSession();
        }
        public static void online()
        {
            //On ferme la connection
            closeConnection();
            HibernateConnection.online = true;
            //Désactivation du log Infos hibernate
            Logger log = Logger.getLogger("org.hibernate");
            log.setLevel(Level.WARNING);
            connectOnline();
            //Chargement du fichier de configuration de hibernate
            HibernateConnection.sessionFactory = new AnnotationConfiguration().configure(confHib).buildSessionFactory();
            //Ouverture de la session
            HibernateConnection.session = HibernateConnection.sessionFactory.openSession();
        }
}
