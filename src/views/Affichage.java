/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import classes.BackgroundPanel;
import controllers.TableDispatcher;
import controllers.UserActif;
import instances.clientCommInstance;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Hashtable;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import models.Client_Comm;
import models.CurrentDatas;
import models.ModelesTables;
/**
 *
 * @author sylv
 */
public class Affichage extends KContainer {
    JLabel title = new JLabel ("Liste des sociétés");
    public List<Client_Comm> liste;
    CurrentDatas cd = CurrentDatas.getInstance();
    private clientCommInstance cci = clientCommInstance.getInstance();
    public Affichage(UserActif user) {
    super();
    this.user = user;
    initPanel();
    }

    @Override
    protected
    void initPanel() {
        JPanel content = new BackgroundPanel();
        content.setLayout(new BorderLayout());
        content.add(title, BorderLayout.CENTER);      
        
        getCompanyList();
        
        //show table
        
        TableDispatcher cp = new TableDispatcher();
        //;
        content.add(cp.showtable(ModelesTables.CLIENT, cd.getUser().getId()), BorderLayout.SOUTH);
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
       
        Hashtable h = new Hashtable();
        h.put("utiid", user.getId());
        h.put("clisuppr", false);
        this.liste = cci.GetClients(" where utiid = :utiid and clisuppr = :clisuppr", h);

   }
       
}

