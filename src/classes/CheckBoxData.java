/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javax.swing.JCheckBox;

/**
 *
 * @author BoBo
 */
public class CheckBoxData extends JCheckBox {
    String Data;
    int Id = -1;
    
    public CheckBoxData() {
        super();
    }
    
    public CheckBoxData(Integer id) {
        super();
        Id = id;
    }
    
    public CheckBoxData(String data) {
        super();
        Data = data;
    }

    public CheckBoxData(Integer id, String data) {
        super();
        Id = id;
        Data = data;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    
    
    
}
