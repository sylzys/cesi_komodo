package models;


import java.util.Date;
import javax.persistence.*;
@Entity(name="detaildmd")
public class DetailDmd {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int demandeid;
    
    @Column(name="cliid", columnDefinition="INTEGER", nullable=true)
    private int cliid ;

    @Column(name="clirais", columnDefinition="CHAR(10)", nullable=true)
    private String clirais ;

    @Column(name="demandeetat", columnDefinition="INTEGER", nullable=true)
    private int demandeetat ;

    @Column(name="utinom", columnDefinition="VARCHAR(50)", nullable=true)
    private String utinom ;

    @Column(name="utiprenom", columnDefinition="VARCHAR(50)", nullable=true)
    private String utiprenom ;

    @Column(name="clinom", columnDefinition="VARCHAR(50)", nullable=true)
    private String clinom ;

    @Column(name="cliville", columnDefinition="VARCHAR(100)", nullable=true)
    private String cliville ;

    @Column(name="clitel", columnDefinition="VARCHAR(10)", nullable=true)
    private String clitel ;

    @Column(name="cliactivite", columnDefinition="VARCHAR(254)", nullable=true)
    private String cliactivite ;

    @Column(name="clica", columnDefinition="INTEGER", nullable=true)
    private int clica ;
    
    @Column(name="suivdoscom", columnDefinition="VARCHAR(1000)", nullable=true)
    private String suivdoscom ;
    
    @Column(name="suivdosdate", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date suivdosdate ;
    
    @Column(name="devprix", columnDefinition="INTEGER", nullable=true)
    private int devprix ;
    
    @Column(name="devdate", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date devdate ;
    
    @Column(name="devetat", columnDefinition="VARCHAR(50)", nullable=true)
    private String devetat ;

    
    
    
    
    public int getCliid() {
        return cliid;
    }

    public void setCliid(int cliid) {
        this.cliid = cliid;
    }

    public int getDemandeid() {
        return demandeid;
    }

    public void setDemandeid(int demandeid) {
        this.demandeid = demandeid;
    }

    public String getClirais() {
        return clirais;
    }

    public void setClirais(String clirais) {
        this.clirais = clirais;
    }

    public int getDemandeetat() {
        return demandeetat;
    }

    public void setDemandeetat(int demandeetat) {
        this.demandeetat = demandeetat;
    }

    public String getUtinom() {
        return utinom;
    }

    public void setUtinom(String utinom) {
        this.utinom = utinom;
    }

    public String getUtiprenom() {
        return utiprenom;
    }

    public void setUtiprenom(String utiprenom) {
        this.utiprenom = utiprenom;
    }

    public String getClinom() {
        return clinom;
    }

    public void setClinom(String clinom) {
        this.clinom = clinom;
    }

    public String getCliville() {
        return cliville;
    }

    public void setCliville(String cliville) {
        this.cliville = cliville;
    }

    public String getClitel() {
        return clitel;
    }

    public void setClitel(String clitel) {
        this.clitel = clitel;
    }

    public String getCliactivite() {
        return cliactivite;
    }

    public void setCliactivite(String cliactivite) {
        this.cliactivite = cliactivite;
    }

    public int getClica() {
        return clica;
    }

    public void setClica(int clica) {
        this.clica = clica;
    }

    public String getSuivdoscom() {
        return suivdoscom;
    }

    public void setSuivdoscom(String suivdoscom) {
        this.suivdoscom = suivdoscom;
    }

    public Date getSuivdosdate() {
        return suivdosdate;
    }

    public void setSuivdosdate(Date suivdosdate) {
        this.suivdosdate = suivdosdate;
    }

    public int getDevprix() {
        return devprix;
    }

    public void setDevprix(int devprix) {
        this.devprix = devprix;
    }

    public Date getDevdate() {
        return devdate;
    }

    public void setDevdate(Date devdate) {
        this.devdate = devdate;
    }


    public String getDevetat() {
        return devetat;
    }

    public void setDevetat(String devetat) {
        this.devetat = devetat;
    }
    
   
    
}