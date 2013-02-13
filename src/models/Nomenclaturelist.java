package models;


import java.util.Date;
import javax.persistence.*;
@Entity
@Table(name="listnom")
public class Nomenclaturelist {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int nomid;
    
    @Column(name="nomlib", columnDefinition="VARCHAR(250)", nullable=true)
    private String nomlib ;
    
    @Column(name="nomprix", columnDefinition="INTEGER", nullable=true)
    private int nomprix ;

    @Column(name="nomdes", columnDefinition="VARCHAR(250)", nullable=true)
    private String nomdes ;

    @Column(name="devid", columnDefinition="INTEGER", nullable=true)
    private int devid ;
            
    @Column(name="devnomqte", columnDefinition="INTEGER", nullable=true)
    private int devnomqte ;
    
    
    public int getNomid() {
        return nomid;
    }

    public void setNomid(int nomid) {
        this.nomid = nomid;
    }

    public String getNomlib() {
        return nomlib;
    }

    public void setNomlib(String nomlib) {
        this.nomlib = nomlib;
    }

    public int getNomprix() {
        return nomprix;
    }

    public void setNomprix(int nomprix) {
        this.nomprix = nomprix;
    }

    public String getNomdes() {
        return nomdes;
    }

    public void setNomdes(String nomdes) {
        this.nomdes = nomdes;
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