package models;

import controllers.Connect;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class HibernateConnection {
	private static HibernateConnection instance;
	private static Session session; 
	private static SessionFactory sessionFactory;
        private static String confHib;
	
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
                confHib = "/config/online.xml";
            }
            else
            {
                confHib = "/config/offline.xml";
            }
            HibernateConnection.sessionFactory = new AnnotationConfiguration().configure(confHib).buildSessionFactory();
            HibernateConnection.session = HibernateConnection.sessionFactory.openSession();
	}
	
	public static HibernateConnection getInstance()
	{
		if(instance == null)
		{			
			instance = new HibernateConnection();
		}
		return instance;
	}
	
	public static void closeConnection()
	{
		
		HibernateConnection.session.flush();
		HibernateConnection.session.close();
	}

}
