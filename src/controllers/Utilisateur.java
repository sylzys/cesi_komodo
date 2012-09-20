/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cesi_komodo.controllers;

/**
 *
 * @author sylv
 */
public class Utilisateur {

    String login, pass;

    public Utilisateur() {
    }

    public Utilisateur(String login, String pass) {
        this.login = login;
        this.pass = pass;
        System.out.println("New user: " + this.login + "/" + this.pass);
    }

    public boolean verify() {
        //admin / admin
        if ("admin".equals(this.login) && "21232f297a57a5a743894a0e4a801fc3".equals(this.pass)) {
            return true;
        } else {
            return false;
        }
    }
    
    public String getFullName() {
        //recuperer nom et prenom dans la BDD
        return "Michel Toto";
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
    
}
