/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.Hashtable;
import javax.swing.JButton;

/**
 *
 * @author BoBo
 */
public class ButtonData extends JButton {
    Hashtable<String, Object> Data;
    int Id = -1;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public ButtonData() {
        super();
    }
    
    public ButtonData(String label) {
        super(label);
    }
    
    public ButtonData(String label, Integer id) {
        super(label);
        Id = id;
    }
    
    public ButtonData(String label, Hashtable<String, Object> data) {
        super(label);
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
