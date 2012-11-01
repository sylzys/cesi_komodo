/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.Date;
import java.util.List;
import instances.HibernateConnection;
import models.Utilisateur;
import org.hibernate.Query;

/**
 *
 * @author sylv
 */
public class UserActif {

    String login, pass, nom, prenom, mail, tel;
    int id;
    Date lastLogin;
    boolean deleted;
    
    public UserActif(String login) {
        this.login = login;
        GetActiveUser();
    }

    public UserActif(String login, String pass) {
        this.login = login;
        this.pass = pass;
        //System.out.println("New user: " + this.login + "/" + this.pass);
    }

    public boolean verify(String pass) {
        //admin / admin
        if (pass.equals(this.pass)) {
            return true;
        } else {
            return false;
        }
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
                }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
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
