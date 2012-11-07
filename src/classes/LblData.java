package classes;

import java.util.Hashtable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author BoBo
 */
public class LblData extends JLabel {
    Hashtable<String, Object> Data;
    int Id = -1;
    
    public LblData() {
        super();
    }
    
    public LblData(Integer id) {
        super();
        Id = id;
    }
    
    public LblData(Hashtable<String, Object> data) {
        super();
        Data = data;
    }

    public LblData(Integer id, Hashtable<String, Object> data) {
        super();
        Id = id;
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
