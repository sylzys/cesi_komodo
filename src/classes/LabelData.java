package classes;

import java.util.Hashtable;
import javax.swing.JLabel;

/**
 *
 * @author BoBo
 */
public class LabelData extends JLabel {
    Hashtable<String, Object> Data;
    Integer Id = -1;

    public LabelData() {
        super();
    }

   
    public LabelData(String label) {
        super(label);
    }
    
    public LabelData(String label, Integer id) {
        super(label);
        Id = id;
    }
    public LabelData(Integer id, Hashtable<String, Object> data) {
        super();
        Id = id;
        Data = data;
    }
    public LabelData(String label, Hashtable<String, Object> data) {
        super(label);
        Data = data;
    }

     public Integer getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    
    
    public Hashtable<String, Object> getData() {
        return Data;
    }

    public Object getDataByKey(String key) {
        if (this.Data == null || !this.Data.containsKey(key)) {
            return null;
        } else {
            return this.Data.get(key);
        }
    }
    
    public void setData(Hashtable<String, Object> Data) {
        if (this.Data == null) {
            this.Data = new Hashtable<>();
        }
        this.Data = Data;
    }
    
    
    
    public void putData(String key, Object val) {
        if (this.Data == null) {
            this.Data = new Hashtable<>();
        }
        this.Data.put(key, val);
    }
    
    
    
}
