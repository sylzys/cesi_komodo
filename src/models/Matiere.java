/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author overadmin
 */
@Entity
@Table(name="matiere")
public class Matiere {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int matid;
    
    @Column(name="uniteid", columnDefinition="INTEGER", nullable=true)
    private int uniteid;
    
    @Column(name="matlib", columnDefinition="VARCHAR(100)", nullable=true)
    private String matlib;
    
    @Column(name="matsuppr", columnDefinition="BOOLEAN", nullable=true)
    private Boolean matsuppr;  

    public int getMatid() {
        return matid;
    }

    public void setMatid(int matid) {
        this.matid = matid;
    }

    public int getUniteid() {
        return uniteid;
    }

    public void setUniteid(int uniteid) {
        this.uniteid = uniteid;
    }

    public String getMatlib() {
        return matlib;
    }

    public void setMatlib(String matlib) {
        this.matlib = matlib;
    }

    public Boolean getMatsuppr() {
        return matsuppr;
    }

    public void setMatsuppr(Boolean mattsuppr) {
        this.matsuppr = mattsuppr;
    }
}
