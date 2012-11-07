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
@Table(name="unite")
public class Unite {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int uniteid;
     
    @Column(name="unitelbl", columnDefinition="VARCHAR(100)", nullable=true)
    private String unitelbl;
    
    @Column(name="unitesuppr", columnDefinition="BOOLEAN", nullable=true)
    private Boolean unitesuppr;   

    public int getUniteid() {
        return uniteid;
    }

    public void setUniteid(int uniteid) {
        this.uniteid = uniteid;
    }

    public String getUnitelbl() {
        return unitelbl;
    }

    public void setUnitelbl(String unitelbl) {
        this.unitelbl = unitelbl;
    }

    public Boolean getUnitesuppr() {
        return unitesuppr;
    }

    public void setUnitesuppr(Boolean unitesuppr) {
        this.unitesuppr = unitesuppr;
    }    
}
