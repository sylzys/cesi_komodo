package models;


import java.util.Date;
import javax.persistence.*;
@Entity
@Table(name="utilisateur")
public class Utilisateur {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int utiid;

    @Column(name="utinom", columnDefinition="VARCHAR(50)", nullable=true)
    private String utinom ;

    @Column(name="utiprenom", columnDefinition="VARCHAR(50)", nullable=true)
    private String utiprenom ;

    @Column(name="utilogin", columnDefinition="VARCHAR(50)", nullable=true)
    private String utilogin ;

    @Column(name="utimdp", columnDefinition="VARCHAR(32)", nullable=true)
    private String utimdp ;

    @Column(name="utimail", columnDefinition="VARCHAR(50)", nullable=true)
    private String utimail ;

    @Column(name="utitel", columnDefinition="VARCHAR(20)", nullable=true)
    private String utitel ;

    @Column(name="utisuppr", columnDefinition="BOOLEAN", nullable=true)
    private Boolean utisuppr ;

    @Column(name="utidtelog", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date utidtelog ;

    public
    Date getUtidtelog() {
        return utidtelog;
    }

    public
    void setUtidtelog(Date utidtelog) {
        this.utidtelog = utidtelog;
    }
    
    public
    Boolean getUtisuppr() {
        return utisuppr;
    }

    public
    void setUtisuppr(Boolean utisuppr) {
        this.utisuppr = utisuppr;
    }
     
    public
    String getUtitel() {
        return utitel;
    }

    public
    void setUtitel(String utitel) {
        this.utitel = utitel;
    }
        
    public
    String getUtimail() {
        return utimail;
    }

    public
    void setUtimail(String utimail) {
        this.utimail = utimail;
    }
        
    public
    String getUtimdp() {
        return utimdp;
    }

    public
    void setUtimdp(String utimdp) {
        this.utimdp = utimdp;
    }
        
    public
    String getUtilogin() {
        return utilogin;
    }

    public
    void setUtilogin(String utilogin) {
        this.utilogin = utilogin;
    }

    public 
    String getUtinom() {
        return utinom;
    }


    public 
    void setUtinom(String utinom) {
        this.utinom = utinom;
    }

    public 
        String getUtiprenom() {
        return utiprenom;
    }


    public 
    void setUtiprenom(String utiprenom) {
        this.utiprenom = utiprenom;
    }
    
    public 
    int getUtiid() {
        return utiid;
    }

    public 
    void setUtiid(int utiid) {
        this.utiid = utiid;
    }
}

