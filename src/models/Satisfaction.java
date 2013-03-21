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
 * @author Lavie
 */
@Entity
@Table(name="satisfaction")
public class Satisfaction implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int satid;
    
    @Column(name="cliid", columnDefinition="INTEGER", nullable=true)
    private int cliid ;
    
    @Column(name="Q1", columnDefinition="INTEGER", nullable=true)
    private int q1;
    
    @Column(name="Q2", columnDefinition="INTEGER", nullable=true)
    private int q2;
        
    @Column(name="Q3", columnDefinition="INTEGER", nullable=true)
    private int q3;
            
    @Column(name="Q4", columnDefinition="INTEGER", nullable=true)
    private int q4;
    
    @Column(name="Q5", columnDefinition="INTEGER", nullable=true)
    private int q5;
    
    @Column(name="satcom", columnDefinition="VARCHAR(2000)", nullable=true)
    private String satcom ;
    
    @Column(name="satdate", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date satdate ;

    public int getSatid() {
        return satid;
    }

    public void setSatid(int satid) {
        this.satid = satid;
    }

    public int getCliid() {
        return cliid;
    }

    public void setCliid(int cliid) {
        this.cliid = cliid;
    }

    public int getQ1() {
        return q1;
    }

    public void setQ1(int q1) {
        this.q1 = q1;
    }

    public int getQ2() {
        return q2;
    }

    public void setQ2(int q2) {
        this.q2 = q2;
    }

    public int getQ3() {
        return q3;
    }

    public void setQ3(int q3) {
        this.q3 = q3;
    }

    public int getQ4() {
        return q4;
    }

    public void setQ4(int q4) {
        this.q4 = q4;
    }

    public int getQ5() {
        return q5;
    }

    public void setQ5(int q5) {
        this.q5 = q5;
    }

    public String getSatcom() {
        return satcom;
    }

    public void setSatcom(String satcom) {
        this.satcom = satcom;
    }

    public Date getSatdate() {
        return satdate;
    }

    public void setSatdate(Date satdate) {
        this.satdate = satdate;
    }
    
    
  
}
