package models;

import java.util.Date;
import javax.persistence.*;
@Entity
@Table(name="suivdossier")
public class Suivdossier {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer suivdosid;
    
    @Column(name="comid", columnDefinition="INTEGER", nullable=true)
    private Integer comid ;

    @Column(name="interid", columnDefinition="INTEGER", nullable=true)
    private Integer interid ;

    @Column(name="demandeid", columnDefinition="INTEGER", nullable=true)
    private Integer demandeid ;

    @Column(name="devid", columnDefinition="INTEGER", nullable=true)
    private Integer devid ;

    @Column(name="utiid", columnDefinition="INTEGER", nullable=true)
    private Integer utiid ;

    @Column(name="suivdoscom", columnDefinition="VARCHAR(1000)", nullable=true)
    private String suivdoscom ;

    @Column(name="suivdosdate", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date suivdosdate ;
    
    @Column(name="suivdossuppr", columnDefinition="BOOLEAN", nullable=true)
    private Boolean suivdossuppr ;

     
   public Integer getSuivdosid() {
        return suivdosid;
    }

    public void setSuivdosid(Integer suivdosid) {
        this.suivdosid = suivdosid;
    }

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

    public Integer getDevid() {
        return devid;
    }

    public void setDevid(Integer devid) {
        this.devid = devid;
    }

    public Integer getUtiid() {
        return utiid;
    }

    public void setUtiid(Integer utiid) {
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

    public Boolean isSuivdossuppr() {
        return suivdossuppr;
    }

    public void setSuivdossuppr(Boolean suivdossuppr) {
        this.suivdossuppr = suivdossuppr;
    }
}

