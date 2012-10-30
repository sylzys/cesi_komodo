/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


/**
 *
 * @author sylv
 */
public class CurrentDatas {
    int soc_id;
    
   public static CurrentDatas instance;
   
   public CurrentDatas() {
       
   }
   
   public int getSoc_id() {
        return soc_id;
    }

    public void setSoc_id(int soc_id) {
        this.soc_id = soc_id;
    }
    
    public static synchronized CurrentDatas getInstance() {
        if (instance == null)
        {
            instance = new CurrentDatas();
        }
        //System.out.println("INSTANCE :"+instance);
        return instance;
    }
}
