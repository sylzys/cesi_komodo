/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controllers.UserActif;


/**
 *
 * @author sylv
 */
public class CurrentDatas {
    int soc_id;
    UserActif user;

    
    
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
    
    public UserActif getUser() {
        return user;
    }

    public void setUser(UserActif user) {
        this.user = user;
    }
}
