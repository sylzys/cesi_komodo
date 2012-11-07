package models;


import java.util.Date;
import javax.persistence.*;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Entity
@Indexed
@Table(name="commande")
public class Commande {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int comid;
    
    @Column(name="interid", columnDefinition="INTEGER", nullable=true)
    private int interid ;
    
    @Column(name="utiid", columnDefinition="INTEGER", nullable=true)
    private int utiid ; 
    
    @Column(name="demandeid", columnDefinition="INTEGER", nullable=true)
    private int demandeid ; 
    
    @Column(name="comtitre", columnDefinition="VARCHAR(254)", nullable=true)
    private String comtitre ;
    
    @Column(name="comdesc", columnDefinition="VARCHAR(500)", nullable=true)
    private String comdesc ;
   
    @Column(name="comdate", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date comdate ;
    
    @Column(name="comdateprev", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date comdateprev ;
    
    @Column(name="cometat", columnDefinition="INTEGER", nullable=true)
    private int cometat ;
    
    @Column(name="comresume", columnDefinition="VARCHAR(1000)", nullable=true)
    private String comresume ;
    
    @Column(name="comproddeb", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date comproddeb;
    
    @Column(name="comprodfin", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date comprodfin ;
    
    @Column(name="comprix", columnDefinition="INTEGER", nullable=true)
    private int comprix ;
    
    @Column(name="comsuppr", columnDefinition="BOOLEAN", nullable=true)
    private boolean comsuppr ;

    public int getComid() {
        return comid;
    }

    public void setComid(int comid) {
        this.comid = comid;
    }

    public int getInterid() {
        return interid;
    }

    public void setInterid(int interid) {
        this.interid = interid;
    }

    public int getUtiid() {
        return utiid;
    }

    public void setUtiid(int utiid) {
        this.utiid = utiid;
    }

    public int getDemandeid() {
        return demandeid;
    }

    public void setDemandeid(int demandeid) {
        this.demandeid = demandeid;
    }
    
    

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    public String getComtitre() {
        return comtitre;
    }

    public void setComtitre(String comtitre) {
        this.comtitre = comtitre;
    }

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    public String getComdesc() {
        return comdesc;
    }

    public void setComdesc(String comdesc) {
        this.comdesc = comdesc;
    }

    public Date getComdate() {
        return comdate;
    }

    public void setComdate(Date comdate) {
        this.comdate = comdate;
    }

    public Date getComdateprev() {
        return comdateprev;
    }

    public void setComdateprev(Date comdateprev) {
        this.comdateprev = comdateprev;
    }

    public int getCometat() {
        return cometat;
    }

    public void setCometat(int cometat) {
        this.cometat = cometat;
    }

    public String getComresume() {
        return comresume;
    }

    public void setComresume(String comresume) {
        this.comresume = comresume;
    }

    public Date getComproddeb() {
        return comproddeb;
    }

    public void setComproddeb(Date comproddeb) {
        this.comproddeb = comproddeb;
    }

    public Date getComprodfin() {
        return comprodfin;
    }

    public void setComprodfin(Date comprodfin) {
        this.comprodfin = comprodfin;
    }

    public int getComprix() {
        return comprix;
    }

    public void setComprix(int comprix) {
        this.comprix = comprix;
    }

    public boolean isComsuppr() {
        return comsuppr;
    }

    public void setComsuppr(boolean comsuppr) {
        this.comsuppr = comsuppr;
    }
}