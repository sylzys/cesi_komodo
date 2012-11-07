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
@Table(name="nommat")
public class Nommat {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int nomid;   
     
    @Column(name="matid", columnDefinition="INTEGER", nullable=true)
    private int matid;
    
    @Column(name="matqte", columnDefinition="INTEGER", nullable=true)
    private int matqte;

    public int getNomid() {
        return nomid;
    }

    public void setNomid(int nomid) {
        this.nomid = nomid;
    }

    public int getMatid() {
        return matid;
    }

    public void setMatid(int matid) {
        this.matid = matid;
    }

    public int getMatqte() {
        return matqte;
    }

    public void setMatqte(int matqte) {
        this.matqte = matqte;
    }   
}
