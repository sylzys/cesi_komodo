/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import instances.HibernateConnection;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author suly
 */
//Class pour les paramètres des actions à synchroniser
public class ParamSync implements Serializable {
    static final long serialVersionUID = 1515L;
    private String clinom;
    private int idWhere;
    private String type;

    public ParamSync() {
        clinom = "";
        idWhere = -1;
        type = "";
    }

    public String getClinom() {
        return clinom;
    }

    public void setClinom(String clinom) {
        this.clinom = clinom;
    }
    public void setClinom(int id, String type) {
        String cliNom = "";
        try 
        {
            if(type.equals("inter"))
            {
               Query query = HibernateConnection.getSession().createQuery("from Interlocuteur where interid = :interid");
               query.setParameter("interid", id);
               List<Interlocuteur> interlist = query.list();
               for (Interlocuteur inter : interlist)
               {
                    id = inter.getCliid();
               } 
            }
            Query query = HibernateConnection.getSession().createQuery("from Client where cliid = :cliid");
            query.setParameter("cliid", id);
            List<Client> clilist = query.list();
            for (Client client : clilist)
            {
                 cliNom = client.getClinom();
            }      
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }  
        this.clinom = cliNom;
    }
    public int getIdWhere() {
        return idWhere;
    }

    public void setIdWhere(int idWhere) {
        this.idWhere = idWhere;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
