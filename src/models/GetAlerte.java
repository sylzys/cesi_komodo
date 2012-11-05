package models;


import java.util.Date;
import javax.persistence.*;
@Entity(name="getalerte")
public class GetAlerte {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer suivdosid;
    
    @Column(name="comid", columnDefinition="INTEGER", nullable=true)
    private Integer comid;
    
    @Column(name="interid", columnDefinition="INTEGER", nullable=true)
    private Integer interid;
    
    @Column(name="internom", columnDefinition="VARCHAR(50)", nullable=true)
    private String internom;
    
    @Column(name="interprenom", columnDefinition="VARCHAR(50)", nullable=true)
    private String interprenom;
    
    @Column(name="cliid", columnDefinition="INTEGER", nullable=true)
    private Integer cliid;
    
    @Column(name="clinom", columnDefinition="VARCHAR(50)", nullable=true)
    private String clinom;
    
    @Column(name="suivdoscom", columnDefinition="VARCHAR(1000)", nullable=true)
    private String suivdoscom;
    
    @Column(name="suivdosdate", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date suivdosdate;    
    
    @Column(name="utiid", columnDefinition="INTEGER", nullable=true)
    private Integer utiid;

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

    public String getInternom() {
        return internom;
    }

    public void setInternom(String internom) {
        this.internom = internom;
    }

    public String getInterprenom() {
        return interprenom;
    }

    public void setInterprenom(String interprenom) {
        this.interprenom = interprenom;
    }

    public Integer getCliid() {
        return cliid;
    }

    public void setCliid(Integer cliid) {
        this.cliid = cliid;
    }

    public String getClinom() {
        return clinom;
    }

    public void setClinom(String clinom) {
        this.clinom = clinom;
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

    public Integer getUtiid() {
        return utiid;
    }

    public void setUtiid(Integer utiid) {
        this.utiid = utiid;
    }
    
}