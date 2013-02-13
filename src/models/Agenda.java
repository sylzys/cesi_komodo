/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author overadmin
 */
@Entity
@Table(name="agenda")
public class Agenda implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int cliid;
    
    @Column(name="utiid", columnDefinition="INTEGER", nullable=true)
    private int utiid ;
    
    @Column(name="ageintitule", columnDefinition="VARCHAR(254)", nullable=true)
    private String ageintitule;
    
    @Column(name="agedeb", columnDefinition="timestamp without time zone", nullable=true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date agedeb;
    
    @Column(name="agefin", columnDefinition="timestamp without time zone", nullable=true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date agefin;
    
    @Column(name="agelieu", columnDefinition="VARCHAR(50)", nullable=true)
    private String agelieu;
    
    @Column(name="agedesc", columnDefinition="VARCHAR(1000)", nullable=true)
    private String agedesc;
    
     @Column(name="ageetat", columnDefinition="BOOLEAN", nullable=true)
    private boolean ageetat;
    
    @Column(name="agesuppr", columnDefinition="BOOLEAN", nullable=true)
    private boolean agesuppr;
     
    @Column(name="ageuniqid", columnDefinition="VARCHAR(500)", nullable=true)
    private String ageuniqid ;

    public int getCliid() {
        return cliid;
    }

    public void setCliid(int cliid) {
        this.cliid = cliid;
    }

    public int getUtiid() {
        return utiid;
    }

    public void setUtiid(int utiid) {
        this.utiid = utiid;
    }

    public String getAgeintitule() {
        return ageintitule;
    }

    public void setAgeintitule(String ageintitule) {
        this.ageintitule = ageintitule;
    }

    public Date getAgedeb() {
        return agedeb;
    }

    public void setAgedeb(Date agedeb) {
        this.agedeb = agedeb;
    }

    public Date getAgefin() {
        return agefin;
    }

    public void setAgefin(Date agefin) {
        this.agefin = agefin;
    }

    public String getAgelieu() {
        return agelieu;
    }

    public void setAgelieu(String agelieu) {
        this.agelieu = agelieu;
    }

    public String getAgedesc() {
        return agedesc;
    }

    public void setAgedesc(String agedesc) {
        this.agedesc = agedesc;
    }

    public boolean isAgeetat() {
        return ageetat;
    }

    public void setAgeetat(boolean ageetat) {
        this.ageetat = ageetat;
    }

    public boolean isAgesuppr() {
        return agesuppr;
    }

    public void setAgesuppr(boolean agesuppr) {
        this.agesuppr = agesuppr;
    }

    public String getAgeuniqid() {
        return ageuniqid;
    }

    public void setAgeuniqid(String ageuniqid) {
        this.ageuniqid = ageuniqid;
    }   
}
