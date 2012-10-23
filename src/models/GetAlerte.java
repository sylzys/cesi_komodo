package models;


import java.util.Date;
import javax.persistence.*;
@Entity(name="getalerte")
public class GetAlerte {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idgetalerte;
    
    @Column(name="comid", columnDefinition="INTEGER", nullable=true)
    private String comid;
    
    @Column(name="interid", columnDefinition="INTEGER", nullable=true)
    private String interid;
    
    @Column(name="internom", columnDefinition="VARCHAR(50)", nullable=true)
    private String internom;
    
    @Column(name="interprenom", columnDefinition="VARCHAR(50)", nullable=true)
    private String interprenom;
    
    @Column(name="cliid", columnDefinition="INTEGER", nullable=true)
    private String cliid;
    
    @Column(name="clinom", columnDefinition="VARCHAR(50)", nullable=true)
    private String clinom;
    
    @Column(name="suivdoscom", columnDefinition="VARCHAR(1000)", nullable=true)
    private String suivdoscom;
    
    @Column(name="suivdosdate", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date suivdosdate;    
    
    @Column(name="utiid", columnDefinition="INTEGER", nullable=true)
    private String utiid;

    public int getIdgetalerte() {
        return idgetalerte;
    }

    public void setIdgetalerte(int idgetalerte) {
        this.idgetalerte = idgetalerte;
    }

    public String getComid() {
        return comid;
    }

    public void setComid(String comid) {
        this.comid = comid;
    }

    public String getInterid() {
        return interid;
    }

    public void setInterid(String interid) {
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

    public String getCliid() {
        return cliid;
    }

    public void setCliid(String cliid) {
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

    public String getUtiid() {
        return utiid;
    }

    public void setUtiid(String utiid) {
        this.utiid = utiid;
    }
    
    
}