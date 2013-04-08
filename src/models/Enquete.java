package models;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
@Entity
@Table(name="enquete")
public class Enquete implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int enqid;

    @Column(name="utiid", columnDefinition="INTEGER", nullable=true)
    private int utiid ;

    @Column(name="interid", columnDefinition="INTEGER", nullable=true)
    private int interid ;

    @Column(name="enqdte", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date enqdte ;

    @Column(name="enqdesc", columnDefinition="VARCHAR(500)", nullable=true)
    private String enqdesc ;
    
    @Column(name="enqint", columnDefinition="VARCHAR(50", nullable=true)
    private String enqint ;

    @Column(name="enqpos", columnDefinition="BOOLEAN", nullable=true)
    private boolean enqpos ;
    
    @Column(name="enqsuppr", columnDefinition="BOOLEAN", nullable=true)
    private boolean enqsuppr ;
    
    @Column(name="enquniqid", columnDefinition="VARCHAR(500)", nullable=true)
    private String enquniqid ;

    public int getEnqid() {
        return enqid;
    }

    public void setEnqid(int enqid) {
        this.enqid = enqid;
    }

    public int getUtiid() {
        return utiid;
    }

    public void setUtiid(int utiid) {
        this.utiid = utiid;
    }

    public int getInterid() {
        return interid;
    }

    public void setInterid(int interid) {
        this.interid = interid;
    }

    public Date getEnqdte() {
        return enqdte;
    }

    public void setEnqdte(Date enqdte) {
        this.enqdte = enqdte;
    }

    public String getEnqdesc() {
        return enqdesc;
    }

    public void setEnqdesc(String enqdesc) {
        this.enqdesc = enqdesc;
    }
    
    public String getEnqint() {
        return enqint;
    }

    public void setEnqint(String enqint) {
        this.enqint = enqint;
    }
    
    public boolean isEnqpos() {
        return enqpos;
    }

    public void setEnqpos(boolean enqpos) {
        this.enqpos = enqpos;
    }

    public boolean isEnqsuppr() {
        return enqsuppr;
    }

    public void setEnqsuppr(boolean enqsuppr) {
        this.enqsuppr = enqsuppr;
    }

    public String getEnquniqid() {
        return enquniqid;
    }

    public void setEnquniqid(String enquniqid) {
        this.enquniqid = enquniqid;
    }   
}