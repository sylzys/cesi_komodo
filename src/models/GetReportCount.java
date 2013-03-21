package models;


import java.util.Date;
import javax.persistence.*;
@Entity(name="getreportcount")
public class GetReportCount {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int enqid;

    @Column(name="cliid", columnDefinition="INTEGER", nullable=true)
    private int cliid ;

    @Column(name="enqdte", columnDefinition="DATE", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date enqdte ;

    @Column(name="enqpos", columnDefinition="BOOLEAN", nullable=true)
    private boolean enqpos ;
    

    public int getEnqid() {
        return enqid;
    }

    public void setEnqid(int enqid) {
        this.enqid = enqid;
    }

    public int getCliid() {
        return cliid;
    }

    public void setCliid(int cliid) {
        this.cliid = cliid;
    }

    public Date getEnqdte() {
        return enqdte;
    }

    public void setEnqdte(Date enqdte) {
        this.enqdte = enqdte;
    }

    public boolean isEnqpos() {
        return enqpos;
    }

    public void setEnqpos(boolean enqpos) {
        this.enqpos = enqpos;
    }
}