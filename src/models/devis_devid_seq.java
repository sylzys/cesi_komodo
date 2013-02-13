package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="devis_devid_seq")
public class devis_devid_seq {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String sequence_name;

    @Column(name="last_value", columnDefinition="INTEGER")
    private int last_value ;
  
    @Column(name="start_value", columnDefinition="INTEGER")
    private int start_value ;

    @Column(name="increment_by", columnDefinition="INTEGER")
    private int increment_by ;
  
    @Column(name="max_value", columnDefinition="INTEGER")
    private int max_value ;

    @Column(name="min_value", columnDefinition="INTEGER")
    private int min_value ;
  
    @Column(name="cache_value", columnDefinition="INTEGER")
    private int cache_value ;
    
    @Column(name="log_cnt", columnDefinition="INTEGER")
    private int log_cnt ;

    @Column(name="is_cycled", columnDefinition="BOOLEAN")
    private boolean is_cycled ;
  
    @Column(name="is_called", columnDefinition="BOOLEAN")
    private boolean is_called ;

    public String getSequence_name() {
        return sequence_name;
    }

    public void setSequence_name(String sequence_name) {
        this.sequence_name = sequence_name;
    }

    public int getLast_value() {
        return last_value;
    }

    public void setLast_value(int last_value) {
        this.last_value = last_value;
    }

    public int getStart_value() {
        return start_value;
    }

    public void setStart_value(int start_value) {
        this.start_value = start_value;
    }

    public int getIncrement_by() {
        return increment_by;
    }

    public void setIncrement_by(int increment_by) {
        this.increment_by = increment_by;
    }

    public int getMax_value() {
        return max_value;
    }

    public void setMax_value(int max_value) {
        this.max_value = max_value;
    }

    public int getMin_value() {
        return min_value;
    }

    public void setMin_value(int min_value) {
        this.min_value = min_value;
    }

    public int getCache_value() {
        return cache_value;
    }

    public void setCache_value(int cache_value) {
        this.cache_value = cache_value;
    }

    public int getLog_cnt() {
        return log_cnt;
    }

    public void setLog_cnt(int log_cnt) {
        this.log_cnt = log_cnt;
    }

    public boolean isIs_cycled() {
        return is_cycled;
    }

    public void setIs_cycled(boolean is_cycled) {
        this.is_cycled = is_cycled;
    }

    public boolean isIs_called() {
        return is_called;
    }

    public void setIs_called(boolean is_called) {
        this.is_called = is_called;
    }

    
    
}

