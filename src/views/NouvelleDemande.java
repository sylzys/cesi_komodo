/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import classes.Uniqid;
import classes.WhitePanel;
import com.google.common.base.Strings;
import controllers.getDemandeInfos;
import instances.DemandeInstance;
import instances.InterlocuteurInstance;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import models.CurrentDatas;
import models.Demande;
import models.Interlocuteur;

public class NouvelleDemande extends JDialog {

    private getDemandeInfos zInfo = new getDemandeInfos();
    private boolean sendData;
    private Fenetre fen = Fenetre.getInstance();
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
    private List<Interlocuteur> inter;
    private JComboBox cb_interlo = new JComboBox();

    public NouvelleDemande(JFrame parent, String title, boolean modal, int id) {
        super(parent, title, modal);

        this.setSize(400, 270);
        this.setLocationRelativeTo(null);
        this.dmd_id = id;
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
        content.setPreferredSize(new Dimension(400, 200));
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        content.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        label1.setPreferredSize(new Dimension(30, 20));
        label2.setPreferredSize(new Dimension(30, 30));
        label3.setPreferredSize(new Dimension(30, 20));
        label4.setPreferredSize(new Dimension(30, 20));
        label5.setPreferredSize(new Dimension(30, 30));
        label6.setPreferredSize(new Dimension(30, 20));

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.LINE_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.LINE_AXIS));
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.LINE_AXIS));
        panel1.setPreferredSize(new Dimension(450, 20));
        panel2.setPreferredSize(new Dimension(450, 30));
        panel3.setPreferredSize(new Dimension(450, 20));


        lbl_titre.setPreferredSize(new Dimension(130, 20));
        suivdostitre.setPreferredSize(new Dimension(200, 20));

        panel1.add(label1, BorderLayout.WEST);
        panel1.add(lbl_titre, BorderLayout.CENTER);
        panel1.add(suivdostitre, BorderLayout.EAST);
        panel1.add(label4, BorderLayout.WEST);
        panel1.setBorder(new EmptyBorder(30, 5, 30, 5));


        InterlocuteurInstance interInstance = InterlocuteurInstance.getInstance();
        Hashtable hh = new Hashtable();
        hh.put("cliid", dmd_id);
        hh.put("intersuppr", false);
        inter = interInstance.GetInterlocuteurs("where cliid = :cliid and intersuppr = :intersuppr", hh);
        cb_interlo.addItem("Interlocuteurs");
        for (final Interlocuteur in : inter)
        {
            cb_interlo.addItem((in.getInterid() + ". " + in.getInterprenom() + " " + in.getInternom()));

        }
        lbl_interlo.setPreferredSize(new Dimension(130, 30));
        cb_interlo.setPreferredSize(new Dimension(120, 30));

        panel2.add(label2, BorderLayout.WEST);
        panel2.add(lbl_interlo);
        panel2.add(cb_interlo, BorderLayout.CENTER);
        panel2.add(label5, BorderLayout.EAST);
        panel2.setBorder(new EmptyBorder(0, 5, 0, 5));



        lbl_comm.setPreferredSize(new Dimension(130, 20));
        suivdoscom.setPreferredSize(new Dimension(200, 20));

        panel3.add(label3, BorderLayout.WEST);
        panel3.add(lbl_comm);
        panel3.add(suivdoscom);
        panel3.add(label6, BorderLayout.WEST);
        panel3.setBorder(new EmptyBorder(30, 5, 30, 5));


        content.setBackground(Color.white);
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        content.add(panel1);
        content.add(panel2);
        content.add(panel3);


        JPanel control = new JPanel();
        JButton okBouton = new JButton("OK");

        okBouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String str = check_fields();
                if (str == "")
                {
                    addToDataBase();
                    new ClientDetail(dmd_id);
                    setVisible(false);

                }
                else
                {
                    JOptionPane.showMessageDialog(null, str, "Erreur", JOptionPane.ERROR_MESSAGE);

                }

                //setVisible(false);
            }
        });

        JButton cancelBouton = new JButton("Annuler");
        cancelBouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                CurrentDatas cur = CurrentDatas.getInstance();
                new ClientDetail(cur.getSoc_id());
                setVisible(false);
            }
        });

        control.add(okBouton);
        control.add(cancelBouton);
        control.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        this.getContentPane().add(content, BorderLayout.CENTER);
        this.getContentPane().add(control, BorderLayout.SOUTH);
    }

    private String check_fields() {
        String str = "";
        if (Strings.isNullOrEmpty(suivdoscom.getText()))
        {
            str += "<html>Le champ <i>Commentaire</i> ne peut être vide<br />";
        }
        if (Strings.isNullOrEmpty(suivdostitre.getText()))
        {
            str += "<html>Le champ <i>Intitulé de la demande</i> ne peut être vide<br />";
        }
        if ("Interlocuteurs".equals(cb_interlo.getSelectedItem()))
        {
            str += "<html>Vous devez choisir un interlocuteur<br />";
        }
        return str;
    }

    private void addToDataBase() {
        CurrentDatas cd = CurrentDatas.getInstance();
        Demande dmde = new Demande();
        dmde.setCliid(dmd_id);
        //dmde.setUtiid(1);
        dmde.setUtiid(cd.getUser().getId());
        Uniqid uniqid = new Uniqid(cd.getUser().getId());
        dmde.setDemandeuniqid(uniqid.getUniqid());
        String interlo = (String) cb_interlo.getSelectedItem();
        String str[] = interlo.split(". ");

        dmde.setInterid((Integer.parseInt(str[0])));
        dmde.setDemandeetat(20);
        dmde.setDemandetitre(suivdostitre.getText());
        dmde.setDemandedesc(suivdoscom.getText());
        dmde.setDemandesuppr(false);
        dmde.setDemandedteadd(new Date());

        DemandeInstance dmd_inst = DemandeInstance.getInstance();
        dmd_inst.setDemandes(dmde);
        dmd_inst.ajouterDansBaseDeDonnees();

    }
}
