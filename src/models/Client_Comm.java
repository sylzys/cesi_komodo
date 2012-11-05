package models;


import java.util.Date;
import javax.persistence.*;
@Entity(name="client_comm")
public class Client_Comm {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="cliid")
    private int cliid;

    @Column(name="utiid", columnDefinition="INTEGER", nullable=true)
    private int utiid ;

    @Column(name="uti_utiid", columnDefinition="INTEGER", nullable=true)
    private int uti_utiid ;
    
    @Column(name="clirais", columnDefinition="CHAR(10)", nullable=true)
    private String clirais ;

    @Column(name="clinom", columnDefinition="VARCHAR(50)", nullable=true)
    private String clinom ;
    
    @Column(name="clidteadd", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date clidteadd ;
    
    @Column(name="cliacces", columnDefinition="BOOLEAN", nullable=true)
    private boolean cliacces ;

    @Column(name="cliadresse", columnDefinition="VARCHAR(250)", nullable=true)
    private String cliadresse ;

    @Column(name="clicp", columnDefinition="CHAR(10)", nullable=true)
    private String clicp ;

    @Column(name="cliville", columnDefinition="VARCHAR(100)", nullable=true)
    private String cliville ;

    @Column(name="clisuppr", columnDefinition="BOOLEAN", nullable=true)
    private boolean clisuppr ;
    
    @Column(name="utinom", columnDefinition="VARCHAR(50)", nullable=true)
    private String utinom ;
    
    @Column(name="utiprenom", columnDefinition="VARCHAR(50)", nullable=true)
    private String utiprenom ;

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

     public boolean isClisuppr() {
        return clisuppr;
    }

    public void setClisuppr(boolean clisuppr) {
        this.clisuppr = clisuppr;
    }
    
    public Date getClidteadd() {
        return clidteadd;
    }

    public void setClidteadd(Date clidteadd) {
        this.clidteadd = clidteadd;
    }

    public boolean isCliacces() {
        return cliacces;
    }

    public void setCliacces(boolean cliacces) {
        this.cliacces = cliacces;
    }

    public int getUti_utiid() {
        return uti_utiid;
    }

    public void setUti_utiid(int uti_utiid) {
        this.uti_utiid = uti_utiid;
    }
    
}