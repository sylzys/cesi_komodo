/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import classes.BackgroundPanel;
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
import classes.WhitePanel;
import controllers.getDemandeInfos;
import instances.DemandeInstance;
import instances.InterlocuteurInstance;
import instances.SuivDossierInstance;
import java.util.Hashtable;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import models.Demande;
import models.Interlocuteur;
import models.Suivdossier;
import controllers.getLoginInfos;
import instances.HibernateConnection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.hibernate.Hibernate;
import org.hibernate.Transaction;

public class NouvelleDemande extends JDialog {

    private getDemandeInfos zInfo = new getDemandeInfos();
    private boolean sendData;
    
    JLabel title = new JLabel("PANNEAU AJOUT DEMANDE");
    WhitePanel left = new WhitePanel(),
            right = new WhitePanel();
    JTextField suivdoscom = new JTextField(),
            suivdostitre = new JTextField(),
            interid = new JTextField();
          
    JLabel lbl_titre = new JLabel("Intitulé de la demande"),
            lbl_comm = new JLabel("Commentaire"),
            lbl_interlo = new JLabel("Interlocuteur");
    private int dmd_id;
    private List<Demande> dmd;

    public NouvelleDemande(JFrame parent, String title, boolean modal, int id) {
        super(parent, title, modal);
        this.setSize(400, 270);
        this.setLocationRelativeTo(null);
        this.dmd_id = id;
        //this.cli_id = cli_id;
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.initComponent();
    }

    public getDemandeInfos showZDialog(int id) {
        this.sendData = false;
        this.setVisible(true);
        return this.zInfo;
    }

    private void initComponent() {
        
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        JLabel label4 = new JLabel();
        JLabel label5 = new JLabel();
        JLabel label6 = new JLabel();
        JPanel content = new JPanel();
        content.setBackground(Color.white);
        content.setPreferredSize(new Dimension(400, 270));
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        content.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        label1.setPreferredSize(new Dimension(30, 20));
        label2.setPreferredSize(new Dimension(30, 20));
        label3.setPreferredSize(new Dimension(30, 20));
        label4.setPreferredSize(new Dimension(30, 20));
        label5.setPreferredSize(new Dimension(30, 20));
        label6.setPreferredSize(new Dimension(30, 20));
                
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.LINE_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.LINE_AXIS));
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.LINE_AXIS));
        panel1.setPreferredSize(new Dimension(450, 20));
        panel2.setPreferredSize(new Dimension(450, 20));
        panel3.setPreferredSize(new Dimension(450, 20));
        
        
        lbl_titre.setPreferredSize(new Dimension(130, 20));
        suivdostitre.setPreferredSize(new Dimension(200, 20));
        
        panel1.add(label1, BorderLayout.WEST);
        panel1.add(lbl_titre, BorderLayout.CENTER);
        panel1.add(suivdostitre, BorderLayout.EAST);
        panel1.add(label4, BorderLayout.WEST);
        panel1.setBorder(new EmptyBorder(20, 5, 20, 5));
        
        
        
        lbl_interlo.setPreferredSize(new Dimension(130, 20));
        interid.setPreferredSize(new Dimension(200, 20));
        
        panel2.add(label2, BorderLayout.WEST);
        panel2.add(lbl_interlo);
        panel2.add(interid);
        panel2.add(label5, BorderLayout.WEST);
        panel2.setBorder(new EmptyBorder(20, 5, 20, 5));
        
        
        
        lbl_comm.setPreferredSize(new Dimension(130, 20));
        suivdoscom.setPreferredSize(new Dimension(200, 20));
        
        panel3.add(label3, BorderLayout.WEST);
        panel3.add(lbl_comm);
        panel3.add(suivdoscom);
        panel3.add(label6, BorderLayout.WEST);
        panel3.setBorder(new EmptyBorder(20, 5, 20, 5));
      
       // panInfos.add(left);

        content.setBackground(Color.white);
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        content.add(panel1);
        content.add(panel2);
        content.add(panel3);


        JPanel control = new JPanel();
        JButton okBouton = new JButton("OK");

        okBouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
               
                    addToDataBase();
                    setVisible(false);
            }
        });

        JButton cancelBouton = new JButton("Annuler");
        cancelBouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                zInfo = new getDemandeInfos();
                setVisible(false);
            }
        });

        control.add(okBouton);
        control.add(cancelBouton);
        control.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        this.getContentPane().add(content, BorderLayout.CENTER);
        this.getContentPane().add(control, BorderLayout.SOUTH);
    }

    private void disable_all() {
//        nom.setDisabledTextColor(Color.black);
//        nom.setEnabled(false);
//        prenom.setDisabledTextColor(Color.black);
//        prenom.setEnabled(false);
//        tel.setDisabledTextColor(Color.black);
//        tel.setEnabled(false);
//        fax.setDisabledTextColor(Color.black);
//        fax.setEnabled(false);
//        mail.setDisabledTextColor(Color.black);
//        mail.setEnabled(false);
//        btn_cancel.setEnabled(false);
    }

    private void enable_all() {
//        nom.setDisabledTextColor(Color.black);
//        nom.setEnabled(true);
//        prenom.setDisabledTextColor(Color.black);
//        prenom.setEnabled(true);
//        tel.setDisabledTextColor(Color.black);
//        tel.setEnabled(true);
//        fax.setDisabledTextColor(Color.black);
//        fax.setEnabled(true);
//        mail.setDisabledTextColor(Color.black);
//        mail.setEnabled(true);
//        btn_cancel.setEnabled(true);
    }
    
    private void addToDataBase() {
            Demande dmde = new Demande();
            dmde.setCliid(dmd_id);
            dmde.setUtiid(1);
            dmde.setDemandeetat(20);
            dmde.setDemandesuppr(false);
            Suivdossier svidossier = new Suivdossier();
            svidossier.setUtiid(1);
            svidossier.setSuivdosdate(new Date());
            svidossier.setInterid(1);
            svidossier.setSuivdoscom(suivdoscom.getText());
            svidossier.setSuividossuppr(false);
            
            DemandeInstance dmd_inst = DemandeInstance.getInstance();
            SuivDossierInstance suivd_inst = SuivDossierInstance.getInstance();
            dmd_inst.setDemandes(dmde);
            //inserer demande et suivi dossier
            dmd_inst.ajouterDansBaseDeDonnées();
            HibernateConnection connection = HibernateConnection.getInstance();
            Integer value = (Integer) connection.getSession().createSQLQuery("SELECT last_value FROM demande_demandeid_seq").addScalar("last_value", Hibernate.INTEGER).uniqueResult();
           
            svidossier.setDemandeid(value);
            suivd_inst.setSuivDossier(svidossier);
            suivd_inst.add();
    }
}
