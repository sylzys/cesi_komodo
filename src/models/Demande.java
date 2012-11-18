package models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
@Entity(name="demande")
public class Demande implements Serializable {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int demandeid;

    @Column(name="interid", columnDefinition="INTEGER", nullable=true)
    private int interid ;
    
    @Column(name="cliid", columnDefinition="INTEGER", nullable=true)
    private int cliid ;
    
    @Column(name="utiid", columnDefinition="INTEGER", nullable=true)
    private int utiid ;    
    
    @Column(name="demandetitre", columnDefinition="VARCHAR(254)", nullable=true)
    private String demandetitre ;
    
    @Column(name="demandedesc", columnDefinition="VARCHAR(500)", nullable=true)
    private String demandedesc ;
    
    @Column(name="demandeetat", columnDefinition="INTEGER", nullable=true)
    private int demandeetat ;
    
    @Column(name="demandedteadd", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date demandedteadd ; 
    
    @Column(name="demandesuppr", columnDefinition="BOOLEAN", nullable=true)
    private Boolean demandesuppr ;

    public int getDemandeid() {
        return demandeid;
    }

    public void setDemandeid(int demandeid) {
        this.demandeid = demandeid;
    }

    public int getInterid() {
        return interid;
    }

    public void setInterid(int interid) {
        this.interid = interid;
    }

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

    public String getDemandetitre() {
        return demandetitre;
    }

    public void setDemandetitre(String demandetitre) {
        this.demandetitre = demandetitre;
    }

    public String getDemandedesc() {
        return demandedesc;
    }

    public void setDemandedesc(String demandedesc) {
        this.demandedesc = demandedesc;
    }

    public int getDemandeetat() {
        return demandeetat;
    }

    public void setDemandeetat(int demandeetat) {
        this.demandeetat = demandeetat;
    }

    public Date getDemandedteadd() {
        return demandedteadd;
    }

    public void setDemandedteadd(Date demandedteadd) {
        this.demandedteadd = demandedteadd;
    }

    public Boolean getDemandesuppr() {
        return demandesuppr;
    }

    public void setDemandesuppr(Boolean demandesuppr) {
        this.demandesuppr = demandesuppr;
    }

  
}

