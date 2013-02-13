package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="devnom")
public class Devnom {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int nomid;

    @Column(name="devid", columnDefinition="INTEGER")
    private int devid ;
  
    @Column(name="devnomqte", columnDefinition="INTEGER")
    private int devnomqte ;

    public int getNomid() {
        return nomid;
    }

    public void setNomid(int nomid) {
        this.nomid = nomid;
    }

    public int getDevid() {
        return devid;
    }

    public void setDevid(int devid) {
        this.devid = devid;
    }

    public int getDevnomqte() {
        return devnomqte;
    }

    public void setDevnomqte(int devnomqte) {
        this.devnomqte = devnomqte;
    }
   
    
    
}

