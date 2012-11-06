/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.google.common.base.Strings;
import controllers.UserActif;
import instances.ClientInstance;
import instances.DemandeInstance;
import instances.DevisInstance;
import instances.HibernateConnection;
import instances.SuivDossierInstance;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import models.Client;
import models.CurrentDatas;
import models.Demande;
import models.Devis;
import models.Suivdossier;
import org.hibernate.Hibernate;

/**
 *
 * @author sylv
 */
public class Createdevis extends KContainer {

    JLabel title = new JLabel("Créer un devis");
    JPanel left = new JPanel(),
            right = new JPanel();
        JFormattedTextField jFormattedTextField1 = new javax.swing.JFormattedTextField();
        JFormattedTextField jFormattedTextField2 = new javax.swing.JFormattedTextField();
        int id;
    private Fenetre fen = Fenetre.getInstance();

    public Createdevis(UserActif user, int id) {
        super();
        this.user = user;
        this.id = id;
        initPanel();
    }

    @Override
    protected void initPanel() {
        JPanel ab1 = new JPanel();
        ab1.setLayout(new BoxLayout(ab1, BoxLayout.LINE_AXIS));
        ab1.setPreferredSize(new Dimension(200, 20));

        JButton button = new JButton();
        JTextField jTextField1 = new javax.swing.JTextField();
        JLabel jLabel1 = new javax.swing.JLabel();
        JLabel jLabel2 = new javax.swing.JLabel();
        JLabel jLabel3 = new javax.swing.JLabel();
        JLabel jLabel4 = new javax.swing.JLabel();
        JLabel jLabel5 = new javax.swing.JLabel();
        JLabel jLabel6 = new javax.swing.JLabel();
        JLabel jLabel7 = new javax.swing.JLabel();
        JLabel jLabel8 = new javax.swing.JLabel();

        JPanel b1 = new JPanel();
        b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
        jLabel1.setText("Intitulé");
        jLabel1.setPreferredSize(new Dimension(100, 20));
        jLabel4.setPreferredSize(new Dimension(20, 10));
        jLabel6.setPreferredSize(new Dimension(20, 10));
        b1.add(jLabel4, BorderLayout.CENTER);
        b1.add(jLabel1, BorderLayout.CENTER);
        b1.add(jFormattedTextField1, BorderLayout.CENTER);
        b1.add(jLabel6, BorderLayout.CENTER);
        b1.setPreferredSize(new Dimension(200, 20));

        JPanel ab2 = new JPanel();
        ab2.setLayout(new BoxLayout(ab2, BoxLayout.LINE_AXIS));
        ab2.setPreferredSize(new Dimension(200, 20));


        JPanel b2 = new JPanel();
        b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
        jLabel2.setText("Prix (en euros)");
        jLabel2.setPreferredSize(new Dimension(100, 20));
        jLabel5.setPreferredSize(new Dimension(20, 10));
        jLabel7.setPreferredSize(new Dimension(20, 10));
        b2.add(jLabel5, BorderLayout.CENTER);
        b2.add(jLabel2, BorderLayout.CENTER);
        b2.add(jFormattedTextField2, BorderLayout.CENTER);
        b2.add(jLabel7, BorderLayout.CENTER);
        b2.setPreferredSize(new Dimension(200, 20));

        JPanel ab3 = new JPanel();
        ab3.setLayout(new BoxLayout(ab3, BoxLayout.LINE_AXIS));
        ab3.setPreferredSize(new Dimension(200, 20));

        JPanel ab4 = new JPanel();
        ab4.setLayout(new BoxLayout(ab4, BoxLayout.LINE_AXIS));
        ab4.setPreferredSize(new Dimension(200, 50));


        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        content.setPreferredSize(new Dimension(500, 180));
        content.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));

        JButton retour = new JButton("Annuler");
        retour.addActionListener(new Createdevis.RetourListenerDmd());
        jLabel8.setPreferredSize(new Dimension(20, 50));

        ab4.add(button, BorderLayout.CENTER);
        ab4.add(jLabel8, BorderLayout.CENTER);
        ab4.add(retour, BorderLayout.CENTER);

        button.setText("Ajouter");
        jLabel3.setPreferredSize(new Dimension(200, 20));
        content.add(title);
        content.add(ab1);
        content.add(b1);
        content.add(ab2);
        content.add(b2);
        content.add(ab3);
        content.add(ab4);
        content.add(jLabel3);


        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //  buttonActionPerformed(evt);
                addToDataBase();
            }
        });

        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(new EmptyBorder(0, 0, 10, 0));
        Font f = new Font("Euphemia", Font.PLAIN, 22);
        title.setFont(f);
        title.setPreferredSize(new Dimension(1000, 40));

        JLabel icon = new JLabel(new ImageIcon("ressources/images/newdevis.jpg"));



        this.panel.add(icon, BorderLayout.PAGE_START);
        this.panel.add(title, BorderLayout.AFTER_LINE_ENDS);
        this.panel.add(content, BorderLayout.PAGE_END);

    }

    private void addToDataBase() {
        try
        {
            Devis dvis = new Devis();
            dvis.setDevetat("En cours");
            dvis.setDevdate(new Date());
            dvis.setDevprix(Integer.parseInt(jFormattedTextField2.getText()));
            dvis.setDevsuppr(false);
            dvis.setDeviid(id);
            

            DevisInstance dvis_inst = DevisInstance.getInstance();
        
            dvis_inst.setDevis(dvis);
           if(dvis_inst.ajouterDansBaseDeDonnees()){
                JOptionPane.showMessageDialog(null, "Votre devis a été ajouté avec succès.");
           }
           
            DemandeDetail devisForm = new DemandeDetail(id);
            fen.RenewContener(devisForm.getPanel());
       //     HibernateConnection connection = HibernateConnection.getInstance();
       //     Integer value = (Integer) connection.getSession().createSQLQuery("SELECT last_value FROM demande_demandeid_seq").addScalar("last_value", Hibernate.INTEGER).uniqueResult();
}
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    
    }




    private class RetourListenerDmd implements ActionListener {

        public RetourListenerDmd() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            CurrentDatas cur = CurrentDatas.getInstance();
            
            DemandeDetail devisForm = new DemandeDetail(id);
            fen.RenewContener(devisForm.getPanel());
        }
    }
}
