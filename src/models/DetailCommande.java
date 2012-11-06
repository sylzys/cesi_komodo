package models;


import java.util.Date;
import javax.persistence.*;
@Entity(name="detailcommande")
public class DetailCommande {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int comid;

    @Column(name="comdate", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date comdate ;

    @Column(name="comdateprev", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date comdateprev ;

    @Column(name="comdesc", columnDefinition="VARCHAR(254)", nullable=true)
    private String comdesc;

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
    private int comprix ;

    @Column(name="comsuppr", columnDefinition="BOOLEAN", nullable=true)
    private boolean compsuppr ;

    @Column(name="cliid", columnDefinition="INTEGER", nullable=true)
    private int cliid ;

    @Column(name="clirais", columnDefinition="VARCHAR(10)", nullable=true)
    private String clirais ;
    
    @Column(name="clinom", columnDefinition="VARCHAR(50)", nullable=true)
    private String clinom;

    public String getClinom() {
        return clinom;
    }

    public void setClinom(String clinom) {
        this.clinom = clinom;
    }
    
    @Column(name="cliadresse", columnDefinition="VARCHAR(250)", nullable=true)
    private String cliadresse ;
    
    @Column(name="clicp", columnDefinition="VARCHAR(10)", nullable=true)
    private String clicp ;
    
//    @Column(name="cliactivite", columnDefinition="VARCHAR(254)", nullable=true)
//    private String cliactivite ;
    
    @Column(name="clietat", columnDefinition="INTEGER", nullable=true)
    private int clietat ;
    
    @Column(name="clilogin", columnDefinition="CHAR(50)", nullable=true)
    private String clilogin ;
    
    @Column(name="climdp", columnDefinition="VARCHAR(32)", nullable=true)
    private String climdp ;
    
    @Column(name="cliacces", columnDefinition="BOOLEAN", nullable=true)
    private boolean cliacces ;
    
    @Column(name="clisuppr", columnDefinition="BOOLEAN", nullable=true)
    private boolean clisuppr ;
    
    @Column(name="internom", columnDefinition="VARCHAR(50)", nullable=true)
    private String internom ;
    
    @Column(name="interprenom", columnDefinition="VARCHAR(50)", nullable=true)
    private String inteprenom ;

    @Column(name="intermail", columnDefinition="VARCHAR(50)", nullable=true)
    private String intermail ;
    
    @Column(name="intertel", columnDefinition="VARCHAR(20)", nullable=true)
    private String intertel ;
    
    @Column(name="intersuppr", columnDefinition="BOOLEAN", nullable=true)
    private boolean intersuppr ;
    
    @Column(name="suivdosid", columnDefinition="INTEGER", nullable=true)
    private int suivdosid ;
    
    @Column(name="suividossuppr", columnDefinition="BOOLEAN", nullable=true)
    private boolean suividossuppr ;
    
    @Column(name="utiprenom", columnDefinition="VARCHAR(50)", nullable=true)
    private String utiprenom;
    
    @Column(name="utinom", columnDefinition="VARCHAR(50)", nullable=true)
    private String utinom;

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
    

    public int getComid() {
        return comid;
    }

    public void setComid(int comid) {
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

    public int getComprix() {
        return comprix;
    }

    public void setComprix(int comprix) {
        this.comprix = comprix;
    }

    public boolean isCompsuppr() {
        return compsuppr;
    }

    public void setCompsuppr(boolean compsuppr) {
        this.compsuppr = compsuppr;
    }

    public int getCliid() {
        return cliid;
    }

    public void setCliid(int cliid) {
        this.cliid = cliid;
    }

    public String getClirais() {
        return clirais;
    }

    public void setClirais(String clirais) {
        this.clirais = clirais;
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

//    public String getCliactivite() {
//        return cliactivite;
//    }
//
//    public void setCliactivite(String cliactivite) {
//        this.cliactivite = cliactivite;
//    }

    public int getClietat() {
        return clietat;
    }

    public void setClietat(int clietat) {
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

    public boolean isCliacces() {
        return cliacces;
    }

    public void setCliacces(boolean cliacces) {
        this.cliacces = cliacces;
    }

    public boolean isClisuppr() {
        return clisuppr;
    }

    public void setClisuppr(boolean clisuppr) {
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

    public boolean isIntersuppr() {
        return intersuppr;
    }

    public void setIntersuppr(boolean intersuppr) {
        this.intersuppr = intersuppr;
    }

    public int getSuivdosid() {
        return suivdosid;
    }

    public void setSuivdosid(int suivdosid) {
        this.suivdosid = suivdosid;
    }

    public boolean isSuividossuppr() {
        return suividossuppr;
    }

    public void setSuividossuppr(boolean suividossupp) {
        this.suividossuppr = suividossupp;
    }
    
    
}