package views;

import classes.BackgroundPanel;
import controllers.getSteInfos;
import controllers.getLoginInfos;
import instances.InterlocuteurInstance;
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
import models.Interlocuteur;
import classes.WhitePanel;
import com.google.common.base.Strings;
import instances.ClientInstance;
import instances.HibernateConnection;
import java.awt.Font;
import java.awt.Image;
import models.Client;
import org.hibernate.Query;
import sun.java2d.pipe.DrawImage;

public class ModifSteDialog extends JDialog {

    private getSteInfos zInfo = new getSteInfos();
    JLabel title = new JLabel("Modification du client");
    JPanel left = new JPanel(),
            right = new JPanel();
    JTextField cli_nom = new JTextField(),
            cli_add = new JTextField(),
            cli_cp = new JTextField(),
            cli_ville = new JTextField(),
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
            lbl_clicp = new JLabel("CP *"),
            lbl_cliville = new JLabel("Ville *"),
            lbl_clitel = new JLabel("Téléphone *"),
            lbl_clifax = new JLabel("Fax"),
            lbl_climail = new JLabel("Mail"),
            lbl_clisite = new JLabel("Site web"),
            lbl_clidir = new JLabel("Dirigeant"),
            lbl_clinaf = new JLabel("Code NAF"),
            lbl_cliact = new JLabel("Activité *"),
            lbl_clisiren = new JLabel("SIREN *"),
            lbl_clisiret = new JLabel("SIRET"),
            lbl_clica = new JLabel("Chiffre d'Affaire");
    private JButton btn_modif = new JButton("Modifier"),
            btn_cancel = new JButton("Annuler");
    private final int cli_id;
    private List<Client> client;
    private Client cli;

    public ModifSteDialog(JFrame parent, String title, boolean modal, int id) {
        super(parent, title, modal);
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);
        //this.inter_id = id;
        this.cli_id = id;
        this.setResizable(false);
        //this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.initComponent();
    }

    public getSteInfos showZDialog(int id) {
        // this.sendData = false;
        this.setVisible(true);
        return this.zInfo;
    }

    private void initComponent() {
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
        //cp
        JPanel cp = new JPanel();
        // cp.setBackground(Color.WHITE);
        cp.setLayout(new FlowLayout());
        cp.add(lbl_clicp);
        cli_cp.setPreferredSize(new Dimension(200, 30));
        cli_cp.setAlignmentX(Box.RIGHT_ALIGNMENT);
        cp.add(cli_cp);
        left.add(cp);
        //left.add(Box.createVerticalStrut(30));
        //ville
        JPanel ville = new JPanel();
        // cp.setBackground(Color.WHITE);
        ville.setLayout(new FlowLayout());
        ville.add(lbl_cliville);
        cli_ville.setPreferredSize(new Dimension(200, 30));
        cli_ville.setAlignmentX(Box.RIGHT_ALIGNMENT);
        ville.add(cli_ville);
        left.add(ville);
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
        JButton cancel = new JButton("Annuler");
        val.addActionListener(new saveListener());
        cancel.addActionListener(new cancelListener());
        //val.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        buttons.add(val);
        buttons.add(cancel);
        right.add(buttons);
        JLabel icon = new JLabel(new ImageIcon("ressources/images/edit.jpg"));
        icon.setOpaque(false);

        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(new EmptyBorder(0, 0, 10, 0));
        Font f = new Font("Euphemia", Font.PLAIN, 14);
        title.setFont(f);
        title.setPreferredSize(new Dimension(1000, 30));
        //this.panel.add(icon, BorderLayout.PAGE_START);
        content.add(left);
        content.add(right);

        //this.getContentPane() = new BackgroundPanel();
        this.getContentPane().add(icon, BorderLayout.PAGE_START);
        this.getContentPane().add(title, BorderLayout.AFTER_LINE_ENDS);
        this.getContentPane().add(content, BorderLayout.PAGE_END);
        //this.getContentPane().add(content, BorderLayout.CENTER);
        // this.getContentPane().add(control, BorderLayout.SOUTH);
        fill_all();
    }

    private void fill_all() {
        ClientInstance ci = ClientInstance.getInstance();
        Hashtable h = new Hashtable();
        h.put("cliid", cli_id);
        String where = "where cliid = :cliid";
        client = ci.GetClients(where, h);
        for (Client cli : client)
        {
            this.cli = cli;
        }
        cli_nom.setText(cli.getClinom());
        cli_add.setText(cli.getCliadresse());
        cli_cp.setText(cli.getClicp());
        cli_ville.setText(cli.getCliville());
        cli_nom.setText(cli.getClinom());
        cli_tel.setText(cli.getClitel());
        cli_fax.setText(cli.getClifax());
        cli_mail.setText(cli.getClimail());
        cli_site.setText(cli.getClisite());
        cli_dir.setText(cli.getClidg());
        cli_naf.setText(cli.getClinaf());
        cli_act.setText(cli.getCliactivite());
        cli_siren.setText(cli.getClisiren());
        cli_siret.setText(cli.getClisiret());
        cli_ca.setText(String.valueOf(cli.getClica()));
    }

    public class saveListener implements ActionListener {

        public saveListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String str = check_fields();
            if (str == "")
            {
                ClientInstance ci = ClientInstance.getInstance();
            cli.setClinom(cli_nom.getText());
            cli.setCliadresse(cli_add.getText());
            cli.setClicp(cli_cp.getText());
            cli.setCliville(cli.getCliville());
            cli.setClitel(cli_tel.getText());
            cli.setClifax(cli_fax.getText());
            cli.setClimail(cli_mail.getText());
            cli.setClisite(cli_site.getText());
            cli.setClidg(cli_dir.getText());
            cli.setCliactivite(cli_act.getText());
            cli.setClinaf(cli_naf.getText());
            cli.setClisiren(cli_siren.getText());
            cli.setClisiret(cli_siret.getText());
            cli.setClica(Integer.valueOf(cli_ca.getText()));
            Boolean is_ok = ci.updaterBaseDeDonnees(cli);
            String str2 = is_ok ? "La modification du client a été effectué" : "La modification a échoué";
            JOptionPane.showMessageDialog(null, str2, "Modification du client", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                System.out.println("Champs NOK -> ");
                JOptionPane jop3 = new JOptionPane();
                jop3.showMessageDialog(null, str, "Attention", JOptionPane.ERROR_MESSAGE);
            }
            
        }
    }

    public class cancelListener implements ActionListener {

        public cancelListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            fill_all();

        }
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
        if (Strings.isNullOrEmpty(cli_cp.getText()) || cli_cp.getText().trim().isEmpty() || cli_cp.getText().trim().length() > 5)
        {
            str += "<html>Le champ <i>CP</i> ne peut être vide et ne doit pas exceder 5 chiffres<br />";
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
        System.out.println("RETURNING CHECKED : "+str);
        return str;
    }
}
