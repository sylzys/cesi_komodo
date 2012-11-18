package models;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
@Entity
@Table(name="agenda")
public class Evenement implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int ageid;

    public int getAgeid() {
        return ageid;
    }

    public void setAgeid(int ageid) {
        this.ageid = ageid;
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
    
    @Column(name="utiid", columnDefinition="INTEGER", nullable=true)
    private int utiid ;

    @Column(name="ageintitule", columnDefinition="VARCHAR(254)", nullable=true)
    private String ageintitule ;
    
    @Column(name="agedeb", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date agedeb ;
    
    @Column(name="agefin", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date agefin ;

    @Column(name="agelieu", columnDefinition="VARCHAR(50)", nullable=true)
    private String agelieu ;
    
    @Column(name="agedesc", columnDefinition="VARCHAR(1000)", nullable=true)
    private String agedesc ;
    
    @Column(name="ageetat", columnDefinition="BOOLEAN", nullable=true)
    private boolean ageetat ;
    
    @Column(name="agesuppr", columnDefinition="BOOLEAN", nullable=true)
    private boolean agesuppr ;
}

