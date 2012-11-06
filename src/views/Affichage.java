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
import javax.swing.ImageIcon;
import models.Client_Comm;
import classes.BackgroundPanel;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Hashtable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import models.ModelesTables;
import models.Utilisateur;
import org.hibernate.Query;
/**
 *
 * @author sylv
 */
public class Affichage extends KContainer {
    JLabel title = new JLabel ("Liste des sociétés");
    public List<Client_Comm> liste;
    
    public Affichage(UserActif user) {
    super();
    this.user = user;
    initPanel();
    }

    @Override
    protected
    void initPanel() {
        JPanel content = new BackgroundPanel();
        //content.setBackground(new ImageIcon("ressources/images/komodo.gif"));
        content.setLayout(new BorderLayout());
        content.add(title, BorderLayout.CENTER);      
        
        getCompanyList();

        
        //show table
        
        TableDispatcher cp = new TableDispatcher();
        //;
        content.add(cp.showtable(ModelesTables.CLIENT, 1), BorderLayout.SOUTH);
         title.setHorizontalAlignment(SwingConstants.CENTER);  
        title.setBorder(new EmptyBorder(0, 0, 10, 0));
        Font f = new Font("Euphemia", Font.PLAIN, 22);
        title.setFont(f); 
        title.setPreferredSize(new Dimension(1000, 40));
        
        JLabel icon = new JLabel(new ImageIcon("ressources/images/liste_client.jpg"));
        
        
        this.panel.add(icon, BorderLayout.PAGE_START);
        this.panel.add(title, BorderLayout.AFTER_LINE_ENDS);
        this.panel.add(content, BorderLayout.PAGE_END);
       
    }
    private void getCompanyList(){
        clientCommInstance cci = clientCommInstance.getInstance();
        Hashtable h = new Hashtable();
        h.put("utiid", user.getId());
        h.put("clisuppr", false);
        this.liste = cci.GetClients(" where utiid = :utiid and clisuppr = :clisuppr", h);

   }
       
}

