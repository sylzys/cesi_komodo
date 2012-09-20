package models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateConnection {
	
	private static HibernateConnection instance;
	private static Session session; 
	private static SessionFactory sessionFactory;
	
	
	public static Session getSession() {
		return HibernateConnection.session;
	}

	public static void setSession(Session session) {
		HibernateConnection.session = session;
	}

	private HibernateConnection()
	{
		HibernateConnection.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
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
