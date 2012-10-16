/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.Hashtable;
import javax.swing.JLabel;

/**
 *
 * @author BoBo
 */
public class LabelData extends JLabel {
    Hashtable<String, Object> Data;
    int Id = -1;

    public LabelData() {
        super();
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    
    public LabelData(String text) {
        super(text);
    }
    
    public LabelData(String text, Hashtable<String, Object> data) {
        super(text);
        Data = data;
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
