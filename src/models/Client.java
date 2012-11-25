package models;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
@Entity
@Table(name="client")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int cliid;

    @Column(name="utiid", columnDefinition="INTEGER", nullable=true)
    private int utiid ;

    @Column(name="uti_utiid", columnDefinition="INTEGER", nullable=true)
    private int uti_utiid ;

    @Column(name="clirais", columnDefinition="CHAR(10)", nullable=true)
    private String clirais ;

    @Column(name="clinom", columnDefinition="VARCHAR(50)", nullable=true)
    private String clinom ;

    @Column(name="cliadresse", columnDefinition="VARCHAR(250)", nullable=true)
    private String cliadresse ;

    @Column(name="clicp", columnDefinition="CHAR(10)", nullable=true)
    private String clicp ;

    @Column(name="cliville", columnDefinition="VARCHAR(100)", nullable=true)
    private String cliville ;

    @Column(name="clipays", columnDefinition="VARCHAR(50)", nullable=true)
    private String clipays ;

    @Column(name="clitel", columnDefinition="VARCHAR(10)", nullable=true)
    private String clitel ;
    
    @Column(name="clifax", columnDefinition="VARCHAR(10)", nullable=true)
    private String clifax ;
    
    @Column(name="climail", columnDefinition="VARCHAR(100)", nullable=true)
    private String climail ;
    
    @Column(name="cliactivite", columnDefinition="VARCHAR(254)", nullable=true)
    private String cliactivite ;
    
    @Column(name="clisiret", columnDefinition="VARCHAR(20)", nullable=true)
    private String clisiret ;
    
    @Column(name="clica", columnDefinition="INTEGER", nullable=true)
    private int clica ;
    
    @Column(name="clisite", columnDefinition="VARCHAR(100)", nullable=true)
    private String clisite ;
    
    @Column(name="clidg", columnDefinition="VARCHAR(100)", nullable=true)
    private String clidg ;
    
    @Column(name="clietat", columnDefinition="INTEGER", nullable=true)
    private int clietat ;
    
    @Column(name="clilogin", columnDefinition="CHAR(50)", nullable=true)
    private String clilogin ;
    
    @Column(name="cliurltmp", columnDefinition="CHAR(500)", nullable=true)
    private String cliurltmp ;
    
    @Column(name="climdp", columnDefinition="VARCHAR(32)", nullable=true)
    private String climdp ;
    
    @Column(name="cliacces", columnDefinition="BOOLEAN", nullable=true)
    private boolean cliacces ;
    
    @Column(name="clisuppr", columnDefinition="BOOLEAN", nullable=true)
    private boolean clisuppr ;
    
    @Column(name="clidtelog", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date clidtelog ;
    
    @Column(name="clidteadd", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date clidteadd ;
    
    @Column(name="clinaf", columnDefinition="VARCHAR(20)", nullable=true)
    private String clinaf ;
    
    @Column(name="clisiren", columnDefinition="VARCHAR(20)", nullable=true)
    private String clisiren ;

    public String getClinaf() {
        return clinaf;
    }

    public void setClinaf(String clinaf) {
        this.clinaf = clinaf;
    }

    public String getClisiren() {
        return clisiren;
    }

    public void setClisiren(String clisiren) {
        this.clisiren = clisiren;
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

    public int getUti_utiid() {
        return uti_utiid;
    }

    public void setUti_utiid(int uti_utiid) {
        this.uti_utiid = uti_utiid;
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

    public String getCliville() {
        return cliville;
    }

    public void setCliville(String cliville) {
        this.cliville = cliville;
    }

    public String getClipays() {
        return clipays;
    }

    public void setClipays(String clipays) {
        this.clipays = clipays;
    }

    public String getClitel() {
        return clitel;
    }

    public void setClitel(String clitel) {
        this.clitel = clitel;
    }

    public String getClifax() {
        return clifax;
    }

    public void setClifax(String clifax) {
        this.clifax = clifax;
    }

    public String getClimail() {
        return climail;
    }

    public void setClimail(String climail) {
        this.climail = climail;
    }

    public String getCliactivite() {
        return cliactivite;
    }

    public void setCliactivite(String cliactivite) {
        this.cliactivite = cliactivite;
    }

    public String getClisiret() {
        return clisiret;
    }

    public void setClisiret(String clisiret) {
        this.clisiret = clisiret;
    }

    public int getClica() {
        return clica;
    }

    public void setClica(int clica) {
        this.clica = clica;
    }

    public String getClisite() {
        return clisite;
    }

    public void setClisite(String clisite) {
        this.clisite = clisite;
    }

    public String getClidg() {
        return clidg;
    }

    public void setClidg(String clidg) {
        this.clidg = clidg;
    }

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

    public Date getClidtelog() {
        return clidtelog;
    }

    public void setClidtelog(Date clidtelog) {
        this.clidtelog = clidtelog;
    }

    public Date getClidteadd() {
        return clidteadd;
    }

    public void setClidteadd(Date clidteadd) {
        this.clidteadd = clidteadd;
    }

    public String getCliurltmp() {
        return cliurltmp;
    }

    public void setCliurltmp(String cliurltmp) {
        this.cliurltmp = cliurltmp;
    }
    
}