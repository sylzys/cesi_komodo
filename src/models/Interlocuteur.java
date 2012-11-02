package models;


import java.util.Date;
import javax.persistence.*;
@Entity
@Table(name="interlocuteur")
public class Interlocuteur {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer interid;

    @Column(name="fourid", columnDefinition="INTEGER", nullable=true)
    private Integer fourid ;
    
    @Column(name="utiid", columnDefinition="INTEGER", nullable=true)
    private Integer utiid ;
    
    @Column(name="cliid", columnDefinition="INTEGER", nullable=true)
    private Integer cliid ;
    
    @Column(name="internom", columnDefinition="VARCHAR(50)", nullable=true)
    private String internom ;
    
    @Column(name="interprenom", columnDefinition="VARCHAR(50)", nullable=true)
    private String interprenom ;
    
    @Column(name="intermail", columnDefinition="VARCHAR(50)", nullable=true)
    private String intermail ;
    
    @Column(name="intertel", columnDefinition="VARCHAR(20)", nullable=true)
    private String intertel ;
    
    @Column(name="interposte", columnDefinition="VRACHAR(100)", nullable=true)
    private String interposte ;
    
    @Column(name="interdteadd", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date interdteadd ;
    
    @Column(name="intersuppr", columnDefinition="BOOLEAN", nullable=true)
    private boolean intersuppr ;

    public int getInterid() {
        return interid;
    }

    public void setInterid(int interid) {
        this.interid = interid;
    }

    public int getFourid() {
        return fourid;
    }

    public void setFourid(int fourid) {
        this.fourid = fourid;
    }

    public int getUtiid() {
        return utiid;
    }

    public void setUtiid(int utiid) {
        this.utiid = utiid;
    }

    public int getCliid() {
        return cliid;
    }

    public void setCliid(int cliid) {
        this.cliid = cliid;
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

    public String getIntermail() {
        return intermail;
    }

    public void setIntermail(String intermail) {
        this.intermail = intermail;
    }

    public String getIntertel() {
        return intertel;
    }

    public void setIntertel(String intertel) {
        this.intertel = intertel;
    }

    public String getInterposte() {
        return interposte;
    }

    public void setInterposte(String interposte) {
        this.interposte = interposte;
    }

    public Date getInterdteadd() {
        return interdteadd;
    }

    public void setInterdteadd(Date interdteadd) {
        this.interdteadd = interdteadd;
    }

    public boolean isIntersuppr() {
        return intersuppr;
    }

    public void setIntersuppr(boolean intersuppr) {
        this.intersuppr = intersuppr;
    }
    
}

