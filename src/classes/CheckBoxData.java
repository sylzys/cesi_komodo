/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.Hashtable;
import javax.swing.JCheckBox;

/**
 *
 * @author BoBo
 */
public class CheckBoxData extends JCheckBox {
    Hashtable<String, Object> Data;
    int Id = -1;
    
    public CheckBoxData() {
        super();
    }
    
    public CheckBoxData(Integer id) {
        super();
        Id = id;
    }
    
    public CheckBoxData(Hashtable<String, Object> data) {
        super();
        Data = data;
    }

    public Hashtable<String, Object> getData() {
        return Data;
    }

    public void setData(Hashtable<String, Object> Data) {
        this.Data = Data;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    
    
    
}
