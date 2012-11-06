package models;


import java.util.Date;
import javax.persistence.*;
@Entity(name="detailcommande")
public class DetailCommande {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer comid;

    @Column(name="comdate", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date comdate ;

    @Column(name="comdateprev", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date comdateprev ;

    @Column(name="comdesc", columnDefinition="VARCHAR(254)", nullable=true)
    private String comdesc;
    
    @Column(name="comtitre", columnDefinition="VARCHAR(50)", nullable=true)
    private String comtitre;
    
    @Column(name="cometat", columnDefinition="INTEGER", nullable=true)
    private String cometat ;

    @Column(name="comproddeb", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date comproddeb ;

    @Column(name="comprodfin", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date comprodfin ;

    @Column(name="comprix", columnDefinition="INTEGER", nullable=true)
    private Integer comprix ;

    @Column(name="comsuppr", columnDefinition="BOOLEAN", nullable=true)
    private Boolean compsuppr ;

    @Column(name="demandeid", columnDefinition="INTEGER", nullable=true)
    private Integer demandeid;

    @Column(name="cliid", columnDefinition="INTEGER", nullable=true)
    private Integer cliid ;
    
    @Column(name="clirais", columnDefinition="VARCHAR(10)", nullable=true)
    private String clirais ;
    
    @Column(name="clinom", columnDefinition="VARCHAR(50)", nullable=true)
    private String clinom;

    @Column(name="cliadresse", columnDefinition="VARCHAR(250)", nullable=true)
    private String cliadresse ;
    
    @Column(name="clicp", columnDefinition="VARCHAR(10)", nullable=true)
    private String clicp ;
    
    @Column(name="clietat", columnDefinition="INTEGER", nullable=true)
    private Integer clietat ;
    
    @Column(name="clilogin", columnDefinition="CHAR(50)", nullable=true)
    private String clilogin ;
    
    @Column(name="climdp", columnDefinition="VARCHAR(32)", nullable=true)
    private String climdp ;
    
    @Column(name="cliacces", columnDefinition="BOOLEAN", nullable=true)
    private Boolean cliacces ;
    
    @Column(name="clisuppr", columnDefinition="BOOLEAN", nullable=true)
    private Boolean clisuppr ;
    
    @Column(name="internom", columnDefinition="VARCHAR(50)", nullable=true)
    private String internom ;
    
    @Column(name="interprenom", columnDefinition="VARCHAR(50)", nullable=true)
    private String inteprenom ;

    @Column(name="intermail", columnDefinition="VARCHAR(50)", nullable=true)
    private String intermail ;
    
    @Column(name="intertel", columnDefinition="VARCHAR(20)", nullable=true)
    private String intertel ;
    
    @Column(name="intersuppr", columnDefinition="BOOLEAN", nullable=true)
    private Boolean intersuppr ;
    
    @Column(name="suivdosid", columnDefinition="INTEGER", nullable=true)
    private Integer suivdosid ;
    
    @Column(name="suividossuppr", columnDefinition="BOOLEAN", nullable=true)
    private Boolean suividossuppr ;
    
    @Column(name="utiprenom", columnDefinition="VARCHAR(50)", nullable=true)
    private String utiprenom;
    
    @Column(name="utinom", columnDefinition="VARCHAR(50)", nullable=true)
    private String utinom;

    
    public Boolean isCompsuppr() {
        return compsuppr;
    }

    public Boolean isCliacces() {
        return cliacces;
    }

    public Boolean isClisuppr() {
        return clisuppr;
    }

    public Boolean isIntersuppr() {
        return intersuppr;
    }

    public Boolean isSuividossuppr() {
        return suividossuppr;
    }

    public Integer getComid() {
        return comid;
    }

    public void setComid(Integer comid) {
        this.comid = comid;
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

    public String getComdesc() {
        return comdesc;
    }

    public void setComdesc(String comdesc) {
        this.comdesc = comdesc;
    }

    public String getComtitre() {
        return comtitre;
    }

    public void setComtitre(String comtitre) {
        this.comtitre = comtitre;
    }

    public String getCometat() {
        return cometat;
    }

    public void setCometat(String cometat) {
        this.cometat = cometat;
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

    public String getCliadresse() {
        return cliadresse;
    }

    public void setCliadresse(String cliadresse) {
        this.cliadresse = cliadresse;
    }

    public String getClicp() {
        return clicp;
    }

    public void setClicp(String clicp) {
        this.clicp = clicp;
    }

    public Integer getClietat() {
        return clietat;
    }

    public void setClietat(Integer clietat) {
        this.clietat = clietat;
    }

    public String getClilogin() {
        return clilogin;
    }

    public void setClilogin(String clilogin) {
        this.clilogin = clilogin;
    }

    public String getClimdp() {
        return climdp;
    }

    public void setClimdp(String climdp) {
        this.climdp = climdp;
    }

    public Boolean getCliacces() {
        return cliacces;
    }

    public void setCliacces(Boolean cliacces) {
        this.cliacces = cliacces;
    }

    public Boolean getClisuppr() {
        return clisuppr;
    }

    public void setClisuppr(Boolean clisuppr) {
        this.clisuppr = clisuppr;
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

    public String getIntermail() {
        return intermail;
    }

    public void setIntermail(String intermail) {
        this.intermail = intermail;
    }

    public String getIntertel() {
        return intertel;
    }

    public void setIntertel(String intertel) {
        this.intertel = intertel;
    }

    public Boolean getIntersuppr() {
        return intersuppr;
    }

    public void setIntersuppr(Boolean intersuppr) {
        this.intersuppr = intersuppr;
    }

    public Integer getSuivdosid() {
        return suivdosid;
    }

    public void setSuivdosid(Integer suivdosid) {
        this.suivdosid = suivdosid;
    }

    public Boolean getSuividossuppr() {
        return suividossuppr;
    }

    public void setSuividossuppr(Boolean suividossuppr) {
        this.suividossuppr = suividossuppr;
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
    
    
    
}