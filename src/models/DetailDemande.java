package models;


import java.util.Date;
import javax.persistence.*;
@Entity(name="demande")
public class DetailDemande {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int demandeid;

    @Column(name="cliid", columnDefinition="INTEGER", nullable=true)
    private int cliid ;
    
    @Column(name="utiid", columnDefinition="INTEGER", nullable=true)
    private int utiid ;
    
            
    @Column(name="demandeetat", columnDefinition="INTEGER", nullable=true)
    private int demandeetat ;       
            
    @Column(name="demandedteadd", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date demandedteadd ;

   
    @Column(name="demandesuppr", columnDefinition="BOOLEAN", nullable=true)
    private boolean demandesuppr ;

    public int getDemandeid() {
        return demandeid;
    }

    public void setDemandeid(int demandeid) {
        this.demandeid = demandeid;
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

    public boolean isDemandesuppr() {
        return demandesuppr;
    }

    public void setDemandesuppr(boolean demandesuppr) {
        this.demandesuppr = demandesuppr;
    }

    
    
}