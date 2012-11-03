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
<<<<<<< HEAD
import classes.WhitePanel;
/**
 *
 * @author sylv
 */
public class NouvelleDemande extends KContainer {
=======
import classes.LightPanel;
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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class NouvelleDemande extends JDialog {
>>>>>>> fef236312b5d561770299081268e3f4b431a8223

    private getDemandeInfos zInfo = new getDemandeInfos();
    private boolean sendData;
    
    JLabel title = new JLabel("PANNEAU AJOUT DEMANDE");
<<<<<<< HEAD
    WhitePanel left = new WhitePanel(),
            right = new WhitePanel();
    JTextField cli_nom = new JTextField(),
            cli_add = new JTextField(),
            cli_cp = new JTextField(),
            cli_tel = new JTextField(),
            cli_fax = new JTextField(),
            cli_mail = new JTextField(),
            cli_site = new JTextField(),
            cli_dir = new JTextField(),
            cli_naf = new JTextField(),
            cli_act = new JTextField(),
            cli_siret = new JTextField(),
            cli_siren = new JTextField(),
            cli_ca = new JTextField();
    JLabel lbl_clinom = new JLabel("Nom société *"),
            lbl_cliadd = new JLabel("N° - Rue *"),
            lbl_clicp = new JLabel("CP - Ville *"),
            lbl_clitel = new JLabel("Téléphone *"),
            lbl_clifax = new JLabel("Fax"),
            lbl_climail = new JLabel("Mail"),
            lbl_clisite = new JLabel("Site web"),
            lbl_clidir = new JLabel("Dirigeant"),
            lbl_clinaf = new JLabel("Code NAF"),
            lbl_cliact = new JLabel("Activité *"),
            lbl_clisiret = new JLabel("SIREN *"),
            lbl_clisiren = new JLabel("SIRET"),
            lbl_clica = new JLabel("Chiffre d'Affaire");
=======
    LightPanel left = new LightPanel(),
            right = new LightPanel();
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
        this.setSize(650, 500);
        this.setLocationRelativeTo(null);
        this.dmd_id = id;
        //this.cli_id = cli_id;
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.initComponent();
    }
>>>>>>> fef236312b5d561770299081268e3f4b431a8223

    public getDemandeInfos showZDialog(int id) {
        this.sendData = false;
        this.setVisible(true);
        return this.zInfo;
    }

    private void initComponent() {
        
        JPanel content = new JPanel();
        content.setBackground(Color.white);
        content.setPreferredSize(new Dimension(620, 600));
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createLineBorder(Color.black));

        //LEFT PANEL
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setPreferredSize(new Dimension(400, 450));
        //nom ste
        WhitePanel nom = new WhitePanel();
        nom.setBackground(Color.WHITE);
        nom.setLayout(new FlowLayout());
        nom.add(lbl_titre);
        suivdostitre.setAlignmentX(Box.RIGHT_ALIGNMENT);
        suivdostitre.setPreferredSize(new Dimension(200, 30));
        nom.add(suivdostitre);
        left.add(nom);
        left.add(Box.createVerticalStrut(30));
        //addr
        WhitePanel addr = new WhitePanel();
        addr.setBackground(Color.WHITE);
        addr.setLayout(new FlowLayout());
        addr.add(lbl_interlo);
        interid.setPreferredSize(new Dimension(200, 30));
        interid.setAlignmentX(Box.RIGHT_ALIGNMENT);
        addr.add(interid);
        left.add(addr);
        //cp ville
        WhitePanel cp = new WhitePanel();
        cp.setBackground(Color.WHITE);
        cp.setLayout(new FlowLayout());
        cp.add(lbl_comm);
        suivdoscom.setPreferredSize(new Dimension(200, 30));
        suivdoscom.setAlignmentX(Box.RIGHT_ALIGNMENT);
        cp.add(suivdoscom);
        left.add(cp);
        left.add(Box.createVerticalStrut(30));
<<<<<<< HEAD
        //tel
        WhitePanel tel = new WhitePanel();
        tel.setBackground(Color.WHITE);
        tel.setLayout(new FlowLayout());
        tel.add(lbl_clitel);
        cli_tel.setPreferredSize(new Dimension(200, 30));
        cli_tel.setAlignmentX(Box.RIGHT_ALIGNMENT);
        tel.add(cli_tel);
        left.add(tel);
        //fax
        WhitePanel fax = new WhitePanel();
        fax.setBackground(Color.WHITE);
        fax.setLayout(new FlowLayout());
        fax.add(lbl_clifax);
        cli_fax.setPreferredSize(new Dimension(200, 30));
        cli_fax.setAlignmentX(Box.RIGHT_ALIGNMENT);
        fax.add(cli_fax);
        left.add(fax);
        left.add(Box.createVerticalStrut(30));
        //mail
        WhitePanel mail = new WhitePanel();
        mail.setBackground(Color.WHITE);
        mail.setLayout(new FlowLayout());
        mail.add(lbl_climail);
        cli_mail.setPreferredSize(new Dimension(200, 30));
        cli_mail.setAlignmentX(Box.RIGHT_ALIGNMENT);
        mail.add(cli_mail);
        left.add(mail);
        //fax
        WhitePanel web = new WhitePanel();
        web.setBackground(Color.WHITE);
        web.setLayout(new FlowLayout());
        web.add(lbl_clisite);
        cli_site.setPreferredSize(new Dimension(200, 30));
        cli_site.setAlignmentX(Box.RIGHT_ALIGNMENT);
        web.add(cli_site);
        left.add(web);
=======
      
       // panInfos.add(left);
>>>>>>> fef236312b5d561770299081268e3f4b431a8223

        content.setBackground(Color.white);
        content.setBorder(new EmptyBorder(20, 30, 0, 10));
        content.setLayout(new BorderLayout());
        content.add(left, BorderLayout.WEST);


<<<<<<< HEAD
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setPreferredSize(new Dimension(400, 450));
        //dirigeant
        WhitePanel dir = new WhitePanel();
        dir.setBackground(Color.WHITE);
        dir.setLayout(new FlowLayout());
        dir.add(lbl_clidir);
        cli_dir.setAlignmentX(Box.LEFT_ALIGNMENT);
        cli_dir.setPreferredSize(new Dimension(200, 30));
        dir.add(cli_dir);
        right.add(dir);
        right.add(Box.createVerticalStrut(30));
        //addr
        WhitePanel naf = new WhitePanel();
        naf.setBackground(Color.WHITE);
        naf.setLayout(new FlowLayout());
        naf.add(lbl_clinaf);
        cli_naf.setPreferredSize(new Dimension(200, 30));
        cli_naf.setAlignmentX(Box.LEFT_ALIGNMENT);
        naf.add(cli_naf);
        right.add(naf);
        //activité
        WhitePanel act = new WhitePanel();
        act.setBackground(Color.WHITE);
        act.setLayout(new FlowLayout());
        act.add(lbl_cliact);
        cli_act.setPreferredSize(new Dimension(200, 30));
        cli_act.setAlignmentX(Box.LEFT_ALIGNMENT);
        act.add(cli_act);
        right.add(act);
        right.add(Box.createVerticalStrut(30));
        //siren
        WhitePanel siren = new WhitePanel();
        siren.setBackground(Color.WHITE);
        siren.setLayout(new FlowLayout());
        siren.add(lbl_clisiren);
        cli_siren.setPreferredSize(new Dimension(200, 30));
        cli_siren.setAlignmentX(Box.LEFT_ALIGNMENT);
        siren.add(cli_siren);
        right.add(siren);
        //siret
        WhitePanel siret = new WhitePanel();
        siret.setBackground(Color.WHITE);
        siret.setLayout(new FlowLayout());
        siret.add(lbl_clisiret);
        cli_siret.setPreferredSize(new Dimension(200, 30));
        cli_siret.setAlignmentX(Box.LEFT_ALIGNMENT);
        siret.add(cli_siret);
        right.add(siret);
        right.add(Box.createVerticalStrut(30));
        //CA
        WhitePanel ca = new WhitePanel();
        ca.setBackground(Color.WHITE);
        ca.setLayout(new FlowLayout());
        ca.add(lbl_clica);
        cli_ca.setPreferredSize(new Dimension(200, 30));
        cli_ca.setAlignmentX(Box.LEFT_ALIGNMENT);
        ca.add(cli_ca);
        right.add(ca);
        right.add(new JLabel("Les champs marqués d'une * sont obligatoires"));
        //Enregistrer
        JButton val = new JButton("Enregistrer");
        val.addActionListener(new saveListener());
        val.setHorizontalAlignment(SwingConstants.CENTER);
        right.add(val);
=======
        JPanel control = new JPanel();
        JButton okBouton = new JButton("OK");
        //recupere infos BDD
        System.out.println("DIALOG SHOWING INTER ID " + dmd_id);
        DemandeInstance dmdInstance = DemandeInstance.getInstance();
        Hashtable h = new Hashtable();
        h.put("interid", dmd_id);
        dmd = dmdInstance.GetDemandes("where demandeid = :demandeid", h);
>>>>>>> fef236312b5d561770299081268e3f4b431a8223

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
            dmde.setCliid(1);
            dmde.setUtiid(1);
            dmde.setDemandeetat(20);
            dmde.setDemandesuppr(false);
            
            Suivdossier svidossier = new Suivdossier();
            svidossier.setUtiid(1);
            svidossier.setInterid(1);
            svidossier.setSuivdoscom(suivdoscom.getText());
            svidossier.setSuividossuppr(false);
            
            DemandeInstance dmd_inst = DemandeInstance.getInstance();
            SuivDossierInstance suivd_inst = SuivDossierInstance.getInstance();
            dmd_inst.setDemandes(dmde);
            suivd_inst.setSuivDossier(svidossier);
            //inserer demande et suivi dossier
            dmd_inst.ajouterDansBaseDeDonnées();
            suivd_inst.ajouterDansBaseDeDonnées();
    }
}
