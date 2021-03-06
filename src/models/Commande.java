package models;


import java.io.Serializable;
import java.math.BigDecimal;
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
public class Commande implements Serializable {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer comid;
    
    @Column(name="interid", columnDefinition="INTEGER", nullable=true)
    private Integer interid ; 
    
    @Column(name="demandeid", columnDefinition="INTEGER", nullable=true)
    private Integer demandeid ; 
    
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
    private Integer cometat ;
    
    @Column(name="comresume", columnDefinition="VARCHAR(1000)", nullable=true)
    private String comresume ;
    
    @Column(name="comproddeb", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date comproddeb;
    
    @Column(name="comprodfin", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date comprodfin ;
    
    @Column(name="comprix", columnDefinition="NUMERIC(20,2)", nullable=true)
    private BigDecimal comprix ;
    
    @Column(name="comsuppr", columnDefinition="BOOLEAN", nullable=true)
    private boolean comsuppr ;
    
    @Column(name="comuniqid", columnDefinition="VARCHAR(500)", nullable=true)
    private String comuniqid ;
    
    @Column(name="enqid", columnDefinition="INTEGER", nullable=true)
    private Integer enqid ;

    public Integer getComid() {
        return comid;
    }

    public void setComid(Integer comid) {
        this.comid = comid;
    }

    public Integer getInterid() {
        return interid;
    }

    public void setInterid(Integer interid) {
        this.interid = interid;
    }

    public Integer getDemandeid() {
        return demandeid;
    }

    public void setDemandeid(Integer demandeid) {
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

    public Integer getCometat() {
        return cometat;
    }

    public void setCometat(Integer cometat) {
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

    public BigDecimal getComprix() {
        return comprix;
    }

    public void setComprix(BigDecimal comprix) {
        this.comprix = comprix;
    }

    public boolean isComsuppr() {
        return comsuppr;
    }

    public void setComsuppr(boolean comsuppr) {
        this.comsuppr = comsuppr;
    }

    public String getComuniqid() {
        return comuniqid;
    }

    public void setComuniqid(String comuniqid) {
        this.comuniqid = comuniqid;
    }

    public Integer getEnqid() {
        return enqid;
    }

    public void setEnqid(Integer enqid) {
        this.enqid = enqid;
    }
}