package models;

import java.util.Date;
import javax.persistence.*;
@Entity
@Table(name="suivdossier")
public class Suivdossier {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int suivdosid;
    
    @Column(name="comid", columnDefinition="INTEGER", nullable=true)
    private int comid ;

    @Column(name="interid", columnDefinition="INTEGER", nullable=true)
    private int interid ;

    @Column(name="demandeid", columnDefinition="INTEGER", nullable=true)
    private int demandeid ;

    @Column(name="devid", columnDefinition="INTEGER", nullable=true)
    private int devid ;

    @Column(name="utiid", columnDefinition="INTEGER", nullable=true)
    private int utiid ;

    @Column(name="suivdoscom", columnDefinition="VARCHAR(1000)", nullable=true)
    private String suivdoscom ;

    @Column(name="suivdosdate", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date suivdosdate ;
    
    @Column(name="suivdossuppr", columnDefinition="BOOLEAN", nullable=true)
    private boolean suivdossuppr ;

     
   public int getSuivdosid() {
        return suivdosid;
    }

    public void setSuivdosid(int suivdosid) {
        this.suivdosid = suivdosid;
    }

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

    public int getDemandeid() {
        return demandeid;
    }

    public void setDemandeid(int demandeid) {
        this.demandeid = demandeid;
    }

    public int getDevid() {
        return devid;
    }

    public void setDevid(int devid) {
        this.devid = devid;
    }

    public int getUtiid() {
        return utiid;
    }

    public void setUtiid(int utiid) {
        this.utiid = utiid;
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

    public boolean isSuivdossuppr() {
        return suivdossuppr;
    }

    public void setSuivdossuppr(boolean suivdossuppr) {
        this.suivdossuppr = suivdossuppr;
    }
}

