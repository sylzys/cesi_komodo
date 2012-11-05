/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import classes.BackgroundPanel;
import com.google.common.base.Strings;
import controllers.UserActif;
import instances.ClientInstance;
import instances.HibernateConnection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import models.Client;
import org.hibernate.Hibernate;
import org.hibernate.Query;

/**
 *
 * @author sylv
 */
public class Creation extends KContainer {

    JLabel title = new JLabel("Créer un nouveau client");
    JPanel left = new JPanel(),
            right = new JPanel();
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

    public Creation(UserActif user) {
        super();
        this.user = user;
        initPanel();
    }

    @Override
    protected void initPanel() {
        BackgroundPanel content = new BackgroundPanel();

        content.setLayout(new FlowLayout());
        //content.setBorder(BorderFactory.createEmptyBorder(60, 0, 0, 0));
        //content.setBackground(Color.WHITE);
        //LEFT PANEL
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setPreferredSize(new Dimension(400, 450));
        //nom ste
        JPanel nom = new JPanel();
        //nom.setBackground(Color.WHITE);
        nom.setLayout(new FlowLayout());
        nom.add(lbl_clinom);
        cli_nom.setAlignmentX(Box.RIGHT_ALIGNMENT);
        cli_nom.setPreferredSize(new Dimension(200, 30));
        nom.add(cli_nom);
        left.add(nom);
        left.add(Box.createVerticalStrut(30));
        //addr
        JPanel addr = new JPanel();
        //addr.setBackground(Color.WHITE);
        addr.setLayout(new FlowLayout());
        addr.add(lbl_cliadd);
        cli_add.setPreferredSize(new Dimension(200, 30));
        cli_add.setAlignmentX(Box.RIGHT_ALIGNMENT);
        addr.add(cli_add);
        left.add(addr);
        //cp ville
        JPanel cp = new JPanel();
        // cp.setBackground(Color.WHITE);
        cp.setLayout(new FlowLayout());
        cp.add(lbl_clicp);
        cli_cp.setPreferredSize(new Dimension(200, 30));
        cli_cp.setAlignmentX(Box.RIGHT_ALIGNMENT);
        cp.add(cli_cp);
        left.add(cp);
        left.add(Box.createVerticalStrut(30));
        //tel
        JPanel tel = new JPanel();
        //tel.setBackground(Color.WHITE);
        tel.setLayout(new FlowLayout());
        tel.add(lbl_clitel);
        cli_tel.setPreferredSize(new Dimension(200, 30));
        cli_tel.setAlignmentX(Box.RIGHT_ALIGNMENT);
        tel.add(cli_tel);
        left.add(tel);
        //fax
        JPanel fax = new JPanel();
        // fax.setBackground(Color.WHITE);
        fax.setLayout(new FlowLayout());
        fax.add(lbl_clifax);
        cli_fax.setPreferredSize(new Dimension(200, 30));
        cli_fax.setAlignmentX(Box.RIGHT_ALIGNMENT);
        fax.add(cli_fax);
        left.add(fax);
        left.add(Box.createVerticalStrut(30));
        //mail
        JPanel mail = new JPanel();
        // mail.setBackground(Color.WHITE);
        mail.setLayout(new FlowLayout());
        mail.add(lbl_climail);
        cli_mail.setPreferredSize(new Dimension(200, 30));
        cli_mail.setAlignmentX(Box.RIGHT_ALIGNMENT);
        mail.add(cli_mail);
        left.add(mail);
        //fax
        JPanel web = new JPanel();
        // web.setBackground(Color.WHITE);
        web.setLayout(new FlowLayout());
        web.add(lbl_clisite);
        cli_site.setPreferredSize(new Dimension(200, 30));
        cli_site.setAlignmentX(Box.RIGHT_ALIGNMENT);
        web.add(cli_site);
        left.add(web);


        //RIGHT PANEL

        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setPreferredSize(new Dimension(400, 450));
        //dirigeant
        JPanel dir = new JPanel();
        //   dir.setBackground(Color.WHITE);
        dir.setLayout(new FlowLayout());
        dir.add(lbl_clidir);
        cli_dir.setAlignmentX(Box.LEFT_ALIGNMENT);
        cli_dir.setPreferredSize(new Dimension(200, 30));
        dir.add(cli_dir);
        right.add(dir);
        right.add(Box.createVerticalStrut(30));
        //addr
        JPanel naf = new JPanel();
        //  naf.setBackground(Color.WHITE);
        naf.setLayout(new FlowLayout());
        naf.add(lbl_clinaf);
        cli_naf.setPreferredSize(new Dimension(200, 30));
        cli_naf.setAlignmentX(Box.LEFT_ALIGNMENT);
        naf.add(cli_naf);
        right.add(naf);
        //activité
        JPanel act = new JPanel();
        // act.setBackground(Color.WHITE);
        act.setLayout(new FlowLayout());
        act.add(lbl_cliact);
        cli_act.setPreferredSize(new Dimension(200, 30));
        cli_act.setAlignmentX(Box.LEFT_ALIGNMENT);
        act.add(cli_act);
        right.add(act);
        right.add(Box.createVerticalStrut(30));
        //siren
        JPanel siren = new JPanel();
        // siren.setBackground(Color.WHITE);
        siren.setLayout(new FlowLayout());
        siren.add(lbl_clisiren);
        cli_siren.setPreferredSize(new Dimension(200, 30));
        cli_siren.setAlignmentX(Box.LEFT_ALIGNMENT);
        siren.add(cli_siren);
        right.add(siren);
        //siret
        JPanel siret = new JPanel();
        //   siret.setBackground(Color.WHITE);
        siret.setLayout(new FlowLayout());
        siret.add(lbl_clisiret);
        cli_siret.setPreferredSize(new Dimension(200, 30));
        cli_siret.setAlignmentX(Box.LEFT_ALIGNMENT);
        siret.add(cli_siret);
        right.add(siret);
        right.add(Box.createVerticalStrut(30));
        //CA
        JPanel ca = new JPanel();
        // ca.setBackground(Color.WHITE);
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
        JLabel icon = new JLabel(new ImageIcon("ressources/images/new_client.jpg"));
        icon.setOpaque(false);

        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(new EmptyBorder(0, 0, 10, 0));
        Font f = new Font("Euphemia", Font.PLAIN, 22);
        title.setFont(f);
        title.setPreferredSize(new Dimension(1000, 30));
        this.panel.add(icon, BorderLayout.PAGE_START);
        content.add(left);
        content.add(right);
        this.panel.add(title, BorderLayout.AFTER_LINE_ENDS);
        this.panel.add(content, BorderLayout.PAGE_END);
    }

    private class saveListener implements ActionListener {

        public saveListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String str = check_fields();
            if (str == "")
            {
                add_customer();

            }
            else
            {
                System.out.println("Champs NOK -> ");
                JOptionPane jop3 = new JOptionPane();
                jop3.showMessageDialog(null, str, "Attention", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void add_customer() {
        //remplir client
        Client cli = new Client();
        cli.setClinom(cli_nom.getText());
        cli.setCliadresse(cli_add.getText());
        cli.setClicp(cli_cp.getText().substring(0, 4));
        cli.setCliville(cli_cp.getText().substring(5, cli_cp.getText().length()));
        cli.setClitel(cli_tel.getText());
        cli.setClifax(cli_fax.getText());
        cli.setClimail(cli_mail.getText());
        cli.setClisite(cli_site.getText());
        cli.setClidg(cli_dir.getText());
        cli.setClinaf(cli_naf.getText());
        cli.setClisiren(cli_siren.getText());
        cli.setClisiret(cli_siret.getText());
        cli.setCliactivite(cli_act.getText());
        cli.setUtiid(this.user.getId());
        cli.setUti_utiid(this.user.getId());
        cli.setClidteadd(Calendar.getInstance().getTime());
        if (!Strings.isNullOrEmpty(cli_ca.getText()) && !cli_ca.getText().trim().isEmpty())
        {
            cli.setClica(Integer.parseInt(cli_ca.getText()));
        }

        ClientInstance cli_inst = ClientInstance.getInstance();
        cli_inst.setClient(cli);
        //inserer client
        Boolean is_ok = cli_inst.ajouterDansBaseDeDonnées();
        String str = is_ok ? "L'ajout du client a été effectué" : "L'ajout du client a échoué";
        JOptionPane.showMessageDialog(null, str, "Ajout du client", JOptionPane.ERROR_MESSAGE);
        HibernateConnection connection = HibernateConnection.getInstance();
        Query query = connection.getSession().createSQLQuery("SELECT last_value FROM client_cliid_seq");
        // System.out.println ("Recorded client ID :" + query.uniqueResult());
        int last_value = ((BigInteger) query.uniqueResult()).intValue();

        int go_to = last_value - 1;
        if (HibernateConnection.online == false)
        {
            HibernateConnection.newConnect(false);
        }
        else
        {
            HibernateConnection.newConnect(true);
        }
        ClientDetail cd = new ClientDetail(go_to);
    }

    private String check_fields() {
        String str = "";
        System.out.println("NOM -> " + cli_nom.getText());
        if (Strings.isNullOrEmpty(cli_nom.getText()) || cli_nom.getText().trim().isEmpty())
        {
            // System.out.println("EMPTY STRING");
            str += "<html>Le champ <i>Nom Société</i> ne peut être vide<br />";
        }
        if (Strings.isNullOrEmpty(cli_add.getText()) || cli_add.getText().trim().isEmpty())
        {
            str += "<html>Le champ <i>N° - Rue</i> ne peut être vide<br />";
        }
        if (Strings.isNullOrEmpty(cli_cp.getText()) || cli_cp.getText().trim().isEmpty())
        {
            str += "<html>Le champ <i>CP - Ville</i> ne peut être vide<br />";
        }
        if (Strings.isNullOrEmpty(cli_tel.getText()) || cli_tel.getText().trim().isEmpty())
        {
            str += "<html>Le champ <i>Téléphone</i> ne peut être vide<br />";
        }
        if (Strings.isNullOrEmpty(cli_act.getText()) || cli_act.getText().trim().isEmpty())
        {
            str += "<html>Le champ <i>Activité</i> ne peut être vide<br />";
        }
        if (Strings.isNullOrEmpty(cli_siren.getText()) || cli_siren.getText().trim().isEmpty())
        {
            str += "<html>Le champ <i>SIREN</i> ne peut être vide<br />";
        }
        if (!Strings.isNullOrEmpty(cli_ca.getText()) && !cli_ca.getText().trim().isEmpty())
        {
            try
            {
                double d = Double.parseDouble(cli_ca.getText());
            }
            catch (NumberFormatException nfe)
            {
                str += "<html>Le champ <i>Chiffre d'Affaire</i> doit être une valeur numérique<br />";
            }
        }
        if (!Strings.isNullOrEmpty(str))
        {
            str += "</html>";
        }
        return str;
    }
}
