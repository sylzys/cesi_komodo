package models;


import java.util.Date;
import javax.persistence.*;
@Entity(name="getreporting")
public class GetReporting {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int enqid;

    @Column(name="utiid", columnDefinition="INTEGER", nullable=true)
    private int utiid ;

    @Column(name="interid", columnDefinition="INTEGER", nullable=true)
    private int interid ;
    
    @Column(name="interuniqid", columnDefinition="VARCHAR(500)", nullable=true)
    private String interuniqid ;

    @Column(name="cliid", columnDefinition="INTEGER", nullable=true)
    private int cliid ;
    
    @Column(name="cliuniqid", columnDefinition="VARCHAR(500)", nullable=true)
    private String cliuniqid ;
    
    @Column(name="enqdos", columnDefinition="VARCHAR(50)", nullable=true)
    private String enqdos ;

    @Column(name="enqiddos", columnDefinition="INTEGER", nullable=true)
    private int enqiddos ;

    @Column(name="enqdte", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date enqdte ;

    @Column(name="enqint", columnDefinition="VARCHCHAR(50)", nullable=true)
    private String enqint ;

    @Column(name="enqdesc", columnDefinition="VARCHAR(500)", nullable=true)
    private String enqdesc ;

    @Column(name="enqpos", columnDefinition="BOOLEAN", nullable=true)
    private boolean enqpos ;
    
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

    public String getInteruniqid() {
        return interuniqid;
    }

    public void setInteruniqid(String interuniqid) {
        this.interuniqid = interuniqid;
    }

    public int getCliid() {
        return cliid;
    }

    public void setCliid(int cliid) {
        this.cliid = cliid;
    }

    public String getCliuniqid() {
        return cliuniqid;
    }

    public void setCliuniqid(String cliuniqid) {
        this.cliuniqid = cliuniqid;
    }

    public String getEnqdos() {
        return enqdos;
    }

    public void setEnqdos(String enqdos) {
        this.enqdos = enqdos;
    }

    public int getEnqiddos() {
        return enqiddos;
    }

    public void setEnqiddos(int enqiddos) {
        this.enqiddos = enqiddos;
    }

    public Date getEnqdte() {
        return enqdte;
    }

    public void setEnqdte(Date enqdte) {
        this.enqdte = enqdte;
    }

    public String getEnqint() {
        return enqint;
    }

    public void setEnqint(String enqint) {
        this.enqint = enqint;
    }

    public String getEnqdesc() {
        return enqdesc;
    }

    public void setEnqdesc(String enqdesc) {
        this.enqdesc = enqdesc;
    }

    public boolean isEnqpos() {
        return enqpos;
    }

    public void setEnqpos(boolean enqpos) {
        this.enqpos = enqpos;
    }

    public String getEnquniqid() {
        return enquniqid;
    }

    public void setEnquniqid(String enquniqid) {
        this.enquniqid = enquniqid;
    }
}