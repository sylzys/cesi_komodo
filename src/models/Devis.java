package models;


import java.util.Date;
import javax.persistence.*;
@Entity
@Table(name="devis")
public class Devis {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int deviid;

    @Column(name="devetat", columnDefinition="VARCHAR(50)", nullable=true)
    private String devetat ;
    
    @Column(name="devdate", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date devdate ;
     
    @Column(name="devprix", columnDefinition="INTEGER", nullable=true)
    private int devprix ;
    
    @Column(name="devsuppr", columnDefinition="BOOLEAN", nullable=true)
    private boolean devsuppr ;

    public int getDeviid() {
        return deviid;
    }

    public void setDeviid(int deviid) {
        this.deviid = deviid;
    }

    public String getDevetat() {
        return devetat;
    }

    public void setDevetat(String devetat) {
        this.devetat = devetat;
    }

    public Date getDevdate() {
        return devdate;
    }

    public void setDevdate(Date devdate) {
        this.devdate = devdate;
    }

    public int getDevprix() {
        return devprix;
    }

    public void setDevprix(int devprix) {
        this.devprix = devprix;
    }

    public boolean isDevsuppr() {
        return devsuppr;
    }

    public void setDevsuppr(boolean devsuppr) {
        this.devsuppr = devsuppr;
    }
}

