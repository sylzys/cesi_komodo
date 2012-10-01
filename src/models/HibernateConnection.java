package models;

import controllers.Connect;
import java.io.File;
import java.io.IOException;
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
            Connect connect = new Connect();
            boolean status = connect.InitConnect();
            if(status == true)
            {
                online = true;
                confHib = "/config/online.xml";
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
