package views;

import controllers.getInterlocuteurInfos;
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

public class InterlocuteurDialog extends JDialog {

    private getInterlocuteurInfos zInfo = new getInterlocuteurInfos();
    private boolean sendData;
    private JLabel loginLabel, passLabel, icon;
    private JTextField nom, login, pass;
    private JTextField prenom;
    private JTextField tel;
    private JTextField fax;
    private JTextField mail;
    private int inter_id, cli_id;
    private InterlocuteurInstance interInstance;
    private List<Interlocuteur> inter;
    private JButton btn_modif = new JButton("Modifier"),
            btn_cancel = new JButton("Annuler");

    public InterlocuteurDialog(JFrame parent, String title, boolean modal, int id) {
        super(parent, title, modal);
        this.setSize(650, 500);
        this.setLocationRelativeTo(null);
        this.inter_id = id;
        //this.cli_id = cli_id;
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.initComponent();
    }

    public getInterlocuteurInfos showZDialog(int id) {
        this.sendData = false;
        this.setVisible(true);
        return this.zInfo;
    }

    private void initComponent() {

        WhitePanel panInfos = new WhitePanel();
        panInfos.setBackground(Color.white);
        panInfos.setPreferredSize(new Dimension(620, 600));
        panInfos.setLayout(new BoxLayout(panInfos, BoxLayout.Y_AXIS));
        panInfos.setBorder(BorderFactory.createLineBorder(Color.black));
        nom = new JTextField();
        nom.setPreferredSize(new Dimension(250, 25));
        prenom = new JTextField();
        prenom.setPreferredSize(new Dimension(250, 25));
        tel = new JTextField();
        tel.setPreferredSize(new Dimension(250, 25));
        fax = new JTextField();
        fax.setPreferredSize(new Dimension(250, 25));
        mail = new JTextField();
        mail.setPreferredSize(new Dimension(250, 25));
        // panInfos.setBorder(new EmptyBorder(60, 60, 60, 60) );
        panInfos.setBorder(BorderFactory.createTitledBorder("Informations interlocuteur"));
        WhitePanel name = new WhitePanel();
        name.setLayout(new FlowLayout());
        name.setBackground(Color.white);
        name.add(new JLabel("Nom :"));
        name.add(nom);

        WhitePanel firstname = new WhitePanel();
        firstname.setBackground(Color.white);
        firstname.setLayout(new FlowLayout());
        firstname.add(new JLabel("Prénom :"));
        firstname.add(prenom);

        WhitePanel phone = new WhitePanel();
        phone.setLayout(new FlowLayout());
        phone.setBackground(Color.white);
        phone.add(new JLabel("Tel :"));
        phone.add(tel);

        WhitePanel Fax = new WhitePanel();
        Fax.setLayout(new FlowLayout());
        Fax.setBackground(Color.white);
        Fax.add(new JLabel("Fax :"));
        Fax.add(fax);

        WhitePanel email = new WhitePanel();
        email.setLayout(new FlowLayout());
        email.setBackground(Color.white);
        email.add(new JLabel("Email :"));
        email.add(mail);

        WhitePanel modif = new WhitePanel();
        modif.setLayout(new FlowLayout());
        modif.setBackground(Color.white);

        btn_modif.addActionListener(new modifListener());
        btn_cancel.addActionListener(new cancelListener());
        modif.add(btn_modif);
        modif.add(btn_cancel);
        btn_cancel.setEnabled(false);
        panInfos.add(name);
        panInfos.add(firstname);
        panInfos.add(phone);
        panInfos.add(Fax);
        panInfos.add(email);
        panInfos.add(modif);


        WhitePanel content = new WhitePanel();
        content.setBackground(Color.white);
        content.setBorder(new EmptyBorder(20, 30, 0, 10));
        content.setLayout(new BorderLayout());
        content.add(panInfos, BorderLayout.WEST);


        WhitePanel control = new WhitePanel();
        JButton okBouton = new JButton("OK");
        //recupere infos BDD
        System.out.println("DIALOG SHOWING INTER ID " + inter_id);
        interInstance = InterlocuteurInstance.getInstance();
        Hashtable h = new Hashtable();
        h.put("interid", inter_id);
        inter = interInstance.GetInterlocuteurs("where interid = :interid", h);
        for (Interlocuteur in : inter)
        {
            nom.setText(in.getInternom());
            prenom.setText(in.getInterprenom());
            tel.setText(in.getIntertel());
            fax.setText(in.getIntertel());
            mail.setText(in.getIntermail());
            disable_all();
        }
        okBouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // zInfo = new getInterlocuteurInfos(login.getText(), pass.getText());
                setVisible(false);
            }
        });

        JButton cancelBouton = new JButton("Annuler");
        cancelBouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                setVisible(false);
            }
        });

        control.add(okBouton);
        control.add(cancelBouton);

        this.getContentPane().add(content, BorderLayout.CENTER);
        this.getContentPane().add(control, BorderLayout.SOUTH);
    }

    private void disable_all() {
        nom.setDisabledTextColor(Color.black);
        nom.setEnabled(false);
        prenom.setDisabledTextColor(Color.black);
        prenom.setEnabled(false);
        tel.setDisabledTextColor(Color.black);
        tel.setEnabled(false);
        fax.setDisabledTextColor(Color.black);
        fax.setEnabled(false);
        mail.setDisabledTextColor(Color.black);
        mail.setEnabled(false);
        btn_cancel.setEnabled(false);
    }

    private void enable_all() {
        nom.setDisabledTextColor(Color.black);
        nom.setEnabled(true);
        prenom.setDisabledTextColor(Color.black);
        prenom.setEnabled(true);
        tel.setDisabledTextColor(Color.black);
        tel.setEnabled(true);
        fax.setDisabledTextColor(Color.black);
        fax.setEnabled(true);
        mail.setDisabledTextColor(Color.black);
        mail.setEnabled(true);
        btn_cancel.setEnabled(true);
    }

    private class modifListener implements ActionListener {

        public modifListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonText = ((JButton) e.getSource()).getText();
            System.out.println("Button is : " + buttonText);
            if ("Modifier".equals(buttonText))
            {
                enable_all();
                btn_modif.setText("Valider");
            }
            else
            {
                for (Interlocuteur in : inter)
                {
                    in.setInternom(nom.getText());
                    in.setInterprenom(prenom.getText());
                    in.setIntertel(tel.getText());
                    //in.setInter(nom.getText());
                    in.setIntermail(mail.getText());
                    interInstance.updaterBaseDeDonnees(in);
                }
                disable_all();
                btn_modif.setText("Modifier");
            }
        }
    }

    private class cancelListener implements ActionListener {

        public cancelListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            for (Interlocuteur in : inter)
            {
                nom.setText(in.getInternom());
                prenom.setText(in.getInterprenom());
                tel.setText(in.getIntertel());
                fax.setText(in.getIntertel());
                mail.setText(in.getIntermail());
                disable_all();
            }
            btn_modif.setText("Modifier");
        }
    }
}