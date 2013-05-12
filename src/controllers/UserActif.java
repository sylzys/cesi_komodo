/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.BCrypt;
import java.util.Date;
import java.util.List;
import instances.HibernateConnection;
import models.Utilisateur;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author sylv
 */
public class UserActif {

    String login, pass, nom, prenom, mail, tel;
    int id;
    Date lastLogin;
    boolean deleted;
    private Boolean _userExists;
    
    public UserActif(String login) {
        this.login = login;
        this._userExists = false;
        GetActiveUser();
    }

    public UserActif(String login, String pass) {
        this.login = login;
        this.pass = pass;
        this._userExists = false;
        GetActiveUser();
    }

    public boolean verify(String passwd) {
        //String hashed = BCrypt.hashpw(pass, BCrypt.gensalt(8));
        System.out.println(passwd + ":::" + this.pass);
        return BCrypt.checkpw(passwd, this.pass);
    }
     
    private void GetActiveUser() {
        HibernateConnection connection = HibernateConnection.getInstance();
        try {
                Query query = connection.getSession().createQuery("from Utilisateur where utilogin = :utilogin");
                query.setParameter("utilogin", this.login);
                List<Utilisateur> userlist = query.list();
                for (Utilisateur uti : userlist) 
                {
                    this.setNom(uti.getUtinom());
                    this.setPrenom(uti.getUtiprenom());
                    this.setPass(uti.getUtimdp());
                    this.setMail(uti.getUtimail());
                    this.setTel(uti.getUtitel());
                    this.setDeleted(uti.getUtisuppr());
                    this.setLastLogin(uti.getUtidtelog());
                    this.setId(uti.getUtiid());
                    this._userExists = true;
                }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
     public void SetDteActiveUser() {
        HibernateConnection connection = HibernateConnection.getInstance();
            Session mysession = connection.getSession();
                Transaction transaction = null;
                try {
                        String req = "UPDATE Utilisateur SET utidtelog='"+new Date()+"' WHERE utiid='"+this.getId()+"'";
                        mysession.createSQLQuery(req).executeUpdate();
                        mysession.beginTransaction();
                        mysession.getTransaction().commit();
                        mysession.flush();
                    } catch (HibernateException e) {
                    transaction.rollback();
                    e.printStackTrace();
                } finally {
                }
     }
    public Boolean Exists() {
        return this._userExists;
    }
    
    public String getFullName() {
        //recuperer nom et prenom dans la BDD
        return this.prenom+" "+this.nom;
    }
    //GETTERS - SETTERS
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    
}
