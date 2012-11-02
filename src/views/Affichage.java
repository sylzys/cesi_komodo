/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.UserActif;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;
import controllers.TableDispatcher;
import java.lang.reflect.Array;
import java.util.List;
import javax.swing.JFrame;
import models.Client;
import instances.HibernateConnection;
import instances.clientCommInstance;
import models.Client_Comm;
import models.ModelesTables;
import models.Utilisateur;
import org.hibernate.Query;
/**
 *
 * @author sylv
 */
public class Affichage extends KContainer {
    JLabel title = new JLabel ("PANNEAU AFFICHAGE");
    public List<Client_Comm> liste;
    
    public Affichage(UserActif user) {
    super();
    this.user = user;
    initPanel();
    }

    @Override
    protected
    void initPanel() {
        JPanel content = new JPanel();
        
        content.setLayout(new BorderLayout());
        content.add(title, BorderLayout.CENTER);      
        
        getCompanyList();
//        for (Client_Comm cli : this.liste) 
//        {   
//            System.out.println("LISTING CLIENT");
//            System.out.println("Sté : " + cli.getClinom());
//            System.out.println("createur : " + cli.getUti_utiid());
//            System.out.println("createur : " + cli.getClidteadd());
//        }
        
        //show table
        
        TableDispatcher cp = new TableDispatcher();
        //;
        content.add(cp.showtable(ModelesTables.CLIENT), BorderLayout.SOUTH);
        this.panel.add(content);
    }
    private void getCompanyList(){
        clientCommInstance cci = clientCommInstance.getInstance();
        this.liste = cci.GetClients("", null);
//       HibernateConnection connection = HibernateConnection.getInstance();
//        try {
//                Query query = connection.getSession().createQuery("from Client_comm where utiid = :utiid");
//                //query.setParameter("utiid", 1);
//                query.setParameter("utiid", this.user.getId());
//               this.liste = query.list();
//        }catch(Exception e){
           System.out.println("OK, GOT COMPANY LIST");
//        }
   }
       
}

