/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.google.common.base.Strings;
import controllers.UserActif;
import instances.ClientInstance;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import models.Client;
import models.CurrentDatas;

/**
 *
 * @author sylv
 */
public class Createdevis extends KContainer {

    JLabel title = new JLabel("Créer un devis");
    JPanel left = new JPanel(),
            right = new JPanel();
  
    public Createdevis(UserActif user, int id) {
        super();
        this.user = user;
        initPanel();
    }

    @Override
    protected void initPanel() {
        JPanel content = new JPanel();

        content.setLayout(new FlowLayout());
        content.setBorder(BorderFactory.createEmptyBorder(60, 0, 0, 0));
        content.setBackground(Color.WHITE);
        
        JButton retour = new JButton("Retour à la société");
        retour.addActionListener(new Createdevis.RetourListenerDmd());
        right.add(retour);
        left.add(title);
        content.add(left);
        content.add(right);
        this.panel.add(content);
    }

    private class saveListener implements ActionListener {

        public saveListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
//            String str = check_fields();
//            if (str == "")
//            {
//                add_devis();
//                
//            }
//            else
//            {
//                System.out.println("Champs NOK -> ");
//                JOptionPane jop3 = new JOptionPane();
//                jop3.showMessageDialog(null, str, "Attention", JOptionPane.WARNING_MESSAGE);            
//            }
//        }
    }

    private void add_devis() {
           
        }
    
    }
    
    
    private static class RetourListenerDmd implements ActionListener {

        public RetourListenerDmd() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            CurrentDatas cur = CurrentDatas.getInstance();
            ClientDetail cd = new ClientDetail(cur.getSoc_id());
        }
    }
}
