package models;


import java.util.Date;
import javax.persistence.*;
@Entity
@Table(name="nomenclature")
public class Nomenclature {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int nomid;
    
    @Column(name="nomlib", columnDefinition="VARCHAR(250)", nullable=true)
    private String nomlib ;
    
    @Column(name="nomdate", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date nomdate ;

    @Column(name="nomtemps", columnDefinition="INTEGER", nullable=true)
    private int nomtemps ;

    @Column(name="nombnchaine", columnDefinition="INTEGER", nullable=true)
    private int nombnchaine ;
    
    @Column(name="nomqr", columnDefinition="VARCHAR(250)", nullable=true)
    private String nomqr ;
    
    @Column(name="nomprix", columnDefinition="INTEGER", nullable=true)
    private int nomprix ;

    @Column(name="nomdes", columnDefinition="VARCHAR(250)", nullable=true)
    private String nomdes ;
    
      
    @Column(name="nomsuppr", columnDefinition="BOOLEAN", nullable=true)
    private boolean nomsuppr ;

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

    public Date getNomdate() {
        return nomdate;
    }

    public void setNomdate(Date nomdate) {
        this.nomdate = nomdate;
    }

    public int getNomtemps() {
        return nomtemps;
    }

    public void setNomtemps(int nomtemps) {
        this.nomtemps = nomtemps;
    }

    public int getNombnchaine() {
        return nombnchaine;
    }

    public void setNombnchaine(int nombnchaine) {
        this.nombnchaine = nombnchaine;
    }

    public String getNomqr() {
        return nomqr;
    }

    public void setNomqr(String nomqr) {
        this.nomqr = nomqr;
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

    public boolean isNomsuppr() {
        return nomsuppr;
    }

    public void setNomsuppr(boolean nomsuppr) {
        this.nomsuppr = nomsuppr;
    }
    
    
    
}