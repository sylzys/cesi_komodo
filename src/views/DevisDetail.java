/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.TableDispatcher;
import controllers.UserActif;
import instances.DetailDmdInstance;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import models.DetailDmd;
import models.ModelesTables;
import models.CurrentDatas;
import models.Devis;

/**
 *
 * @author sylv
 */
public class DevisDetail extends KContainer {

    List<Devis> devis;
    int devis_id;
    int demandeId;
    private Fenetre fen = Fenetre.getInstance();
    JLabel title = new JLabel();
    JButton validate = new JButton();
    JButton refuse = new JButton();
    public UserActif user;

    public DevisDetail(int id) {
        super();
        devis_id = id;
        //CurrentDatas cur = CurrentDatas.getInstance();
        //cur.setSoc_id(demande_id);
        initPanel();
    }

   // @Override
    protected void initPanel() {
        JPanel content = new JPanel(),
                listeDmd = new JPanel(),
                listeDevis = new JPanel(),
                detailDmd = new JPanel(),
                left = new JPanel(),
                right = new JPanel(),
                top_right = new JPanel(),
                centertop_right = new JPanel(),
                center_right = new JPanel(),
                bottom_right = new JPanel(),
                bottom_bottom = new JPanel();
        JLabel jLabel4 = new javax.swing.JLabel();
    JLabel jLabel5 = new javax.swing.JLabel();
    JLabel jLabel6 = new javax.swing.JLabel();
    JLabel jLabel7 = new javax.swing.JLabel();
    JLabel jLabel8 = new javax.swing.JLabel();
    JLabel jLabel9 = new javax.swing.JLabel();
    JLabel jLabel10 = new javax.swing.JLabel();
    JLabel jLabel11 = new javax.swing.JLabel();
    JLabel jLabel12 = new javax.swing.JLabel();

        content.setLayout(new FlowLayout());
        content.setPreferredSize(new Dimension(1000, 750));
        detailDmd.setPreferredSize(new Dimension(650, 750));
        detailDmd.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Color.LIGHT_GRAY));
        //  System.out.println("CMD-ID: "+this.demande_id);
        //title.setText("DETAIL COMMANDE "+ this.demande_id);
        TableDispatcher cp = new TableDispatcher();
        TableDispatcher ld = new TableDispatcher();
        /**
         * fixer la largeur de la première colonne à 200 pixels
         */
        
        content.setBackground(Color.white);
        top_right.setBackground(Color.white);
        center_right.setBackground(Color.white);
        listeDmd.setBackground(Color.white);
        listeDevis.setBackground(Color.white);
        right.setBackground(Color.white);
        bottom_right.setBackground(Color.white);
        detailDmd.setBackground(Color.white);
        bottom_bottom.setBackground(Color.white);
        
        listeDevis.setPreferredSize(new Dimension(330, 260));
        bottom_bottom.setPreferredSize(new Dimension(600, 300));
        
        
        
        
        listeDmd.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        listeDevis.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        
        listeDmd.setLayout(new BoxLayout(listeDmd, BoxLayout.LINE_AXIS));
        top_right.setLayout(new FlowLayout());
        right.setLayout(new BoxLayout(right, BoxLayout.LINE_AXIS));
        
        
        detailDmd.setLayout(new BoxLayout(detailDmd, BoxLayout.Y_AXIS));
        listeDevis.setLayout(new BorderLayout());
        bottom_bottom.setLayout(new BoxLayout(bottom_bottom, BoxLayout.LINE_AXIS));
        
        
        detailDmd.add(top_right);
        detailDmd.add((center_right));
        detailDmd.add(right);
        detailDmd.add((bottom_right));
        detailDmd.add((bottom_bottom));
        content.add(listeDmd);
        content.add(detailDmd);
        this.panel.add(content);
        user = new UserActif("admin");
    }
    
     private class validateListener implements ActionListener {

        public validateListener() {
        }

    //    @Override
        public void actionPerformed(ActionEvent e) {

//            JOptionPane jop4 = new JOptionPane();
//            jop4.showMessageDialog(null, "Affichage demmande séléctionnée", "ValidateDmd", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("update devis pour devis n° :" + devis_id + "valeur : valider" );
        }
    }
     
     private class refuseListener implements ActionListener {

        public refuseListener() {
        }

    //    @Override
        public void actionPerformed(ActionEvent e) {

//            JOptionPane jop4 = new JOptionPane();
//            jop4.showMessageDialog(null, "Affichage demmande séléctionnée", "ValidateDmd", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("update devis pour devis n° :" + devis_id + " valeur : refuser" );
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
