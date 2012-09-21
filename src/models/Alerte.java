package models;


import java.util.Date;
import javax.persistence.*;
@Entity
@Table(name="getalerte")
public class Alerte {
    @Id
    private Long id;

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

    public int getCliid() {
        return cliid;
    }

    public void setCliid(int cliid) {
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

    public int getUtiid() {
        return utiid;
    }

    public void setUtiid(int utiid) {
        this.utiid = utiid;
    }
    
    @Column(name="comid", columnDefinition="INTEGER", nullable=true)
    private int comid ;

    @Column(name="interid", columnDefinition="INTEGER", nullable=true)
    private int interid ;

    @Column(name="internom", columnDefinition="VARCHAR(50)", nullable=true)
    private String internom ;

    @Column(name="interprenom", columnDefinition="VARCHAR(50)", nullable=true)
    private String interprenom ;

    @Column(name="cliid", columnDefinition="INTEGER", nullable=true)
    private int cliid ;

    @Column(name="clinom", columnDefinition="VARCHAR(50)", nullable=true)
    private String clinom ;

    @Column(name="suivdoscom", columnDefinition="VARCHAR(1000)", nullable=true)
    private String suivdoscom ;

    @Column(name="suivdosdate", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date suivdosdate ;

    @Column(name="utiid", columnDefinition="INTEGER", nullable=true)
    private int utiid ;
   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

