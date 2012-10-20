/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author lavie
 */
public class Connect {
    private static SessionFactory sessionFactory;
    private String lib;
    private String libreq;
    public boolean InitConnect()
    {
            try {
                sessionFactory = new Configuration().configure("config/connect.xml").buildSessionFactory() ;
             } catch (Throwable ex) {
                System.err.println("SessionFactory non créée. " + ex.getMessage ()) ;
               return false;
             }
        return true;
    }
    public void SaveReq(String req) throws IOException
    {
        try {
           FileWriter fichier = new FileWriter("ressources/requetes.sql", true);
           BufferedWriter bw = new BufferedWriter(fichier);
           libreq = req.substring(0,6);
           if("INSERT".equals(libreq))
           {
               lib = "Ajout|";
           }
           else
           {
               lib = "Mise à jour|" ;
           }
           bw.write(req +";->"+ lib);
           bw.close();
        } catch(IOException e) {
           System.out.println(e);
        }
    }
}
