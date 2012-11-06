package models;


import java.util.Date;
import javax.persistence.*;
@Entity(name="detailcommande")
public class DetailCommande {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer comid;
    
    @Column(name="comtitre", columnDefinition="VARCHAR(254)", nullable=true)
    private String comtitre;
    
    @Column(name="comdesc", columnDefinition="VARCHAR(500)", nullable=true)
    private String comdesc;
    
    @Column(name="comdate", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date comdate ;
    
    @Column(name="cometat", columnDefinition="INTEGER", nullable=true)
    private String cometat ;
    
    @Column(name="comprix", columnDefinition="INTEGER", nullable=true)
    private Integer comprix ;

    @Column(name="comsuppr", columnDefinition="BOOLEAN", nullable=true)
    private Boolean compsuppr ;
    
    
    
    @Column(name="demandeid", columnDefinition="INTEGER", nullable=true)
    private Integer demandeid;
    
    
    
    @Column(name="interid", columnDefinition="INTEGER", nullable=true)
    private Integer interid;
    
    @Column(name="internom", columnDefinition="VARCHAR(50)", nullable=true)
    private String internom ;
    
    @Column(name="interprenom", columnDefinition="VARCHAR(50)", nullable=true)
    private String inteprenom ;
    
    @Column(name="intersuppr", columnDefinition="BOOLEAN", nullable=true)
    private Boolean intersuppr ;
   
    

    @Column(name="cliid", columnDefinition="INTEGER", nullable=true)
    private Integer cliid ;
    
    @Column(name="clirais", columnDefinition="VARCHAR(10)", nullable=true)
    private String clirais ;
    
    @Column(name="clinom", columnDefinition="VARCHAR(50)", nullable=true)
    private String clinom;    
    
    @Column(name="clisuppr", columnDefinition="BOOLEAN", nullable=true)
    private Boolean clisuppr ;
    
    
    
    @Column(name="utiid", columnDefinition="INTEGER", nullable=true)
    private Integer utiid;
    
    @Column(name="utiprenom", columnDefinition="VARCHAR(50)", nullable=true)
    private String utiprenom;
    
    @Column(name="utinom", columnDefinition="VARCHAR(50)", nullable=true)
    private String utinom;
    
    @Column(name="utisuppr", columnDefinition="BOOLEAN", nullable=true)
    private Boolean utisuppr ;

    public Integer getComid() {
        return comid;
    }

    public void setComid(Integer comid) {
        this.comid = comid;
    }

    public String getComtitre() {
        return comtitre;
    }

    public void setComtitre(String comtitre) {
        this.comtitre = comtitre;
    }

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

    public String getCometat() {
        return cometat;
    }

    public void setCometat(String cometat) {
        this.cometat = cometat;
    }

    public Integer getComprix() {
        return comprix;
    }

    public void setComprix(Integer comprix) {
        this.comprix = comprix;
    }

    public Boolean getCompsuppr() {
        return compsuppr;
    }

    public void setCompsuppr(Boolean compsuppr) {
        this.compsuppr = compsuppr;
    }

    public Integer getDemandeid() {
        return demandeid;
    }

    public void setDemandeid(Integer demandeid) {
        this.demandeid = demandeid;
    }

    public Integer getInterid() {
        return interid;
    }

    public void setInterid(Integer interid) {
        this.interid = interid;
    }

    public String getInternom() {
        return internom;
    }

    public void setInternom(String internom) {
        this.internom = internom;
    }

    public String getInteprenom() {
        return inteprenom;
    }

    public void setInteprenom(String inteprenom) {
        this.inteprenom = inteprenom;
    }

    public Boolean getIntersuppr() {
        return intersuppr;
    }

    public void setIntersuppr(Boolean intersuppr) {
        this.intersuppr = intersuppr;
    }

    public Integer getCliid() {
        return cliid;
    }

    public void setCliid(Integer cliid) {
        this.cliid = cliid;
    }

    public String getClirais() {
        return clirais;
    }

    public void setClirais(String clirais) {
        this.clirais = clirais;
    }

    public String getClinom() {
        return clinom;
    }

    public void setClinom(String clinom) {
        this.clinom = clinom;
    }

    public Boolean getClisuppr() {
        return clisuppr;
    }

    public void setClisuppr(Boolean clisuppr) {
        this.clisuppr = clisuppr;
    }

    public Integer getUtiid() {
        return utiid;
    }

    public void setUtiid(Integer utiid) {
        this.utiid = utiid;
    }

    public String getUtiprenom() {
        return utiprenom;
    }

    public void setUtiprenom(String utiprenom) {
        this.utiprenom = utiprenom;
    }

    public String getUtinom() {
        return utinom;
    }

    public void setUtinom(String utinom) {
        this.utinom = utinom;
    }

    public Boolean getUtisuppr() {
        return utisuppr;
    }

    public void setUtisuppr(Boolean utisuppr) {
        this.utisuppr = utisuppr;
    }
    
    
    
}