/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.ConnectException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author lavie
 */
public class Connect {
    private static SessionFactory sessionFactory;
    public boolean InitConnect()
    {
           // Connect.sessionFactory = new AnnotationConfiguration().configure("config/online.xml").buildSessionFactory();
            try {
                sessionFactory = new Configuration().configure("config/connect.xml").buildSessionFactory() ;
             } catch (Throwable ex) {
                System.err.println("SessionFactory non créée. " + ex.getMessage ()) ;
               return false;
             }
        return true;
    }
}
