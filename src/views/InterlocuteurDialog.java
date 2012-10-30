package views;

import controllers.getInterlocuteurInfos;
import controllers.getLoginInfos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class InterlocuteurDialog extends JDialog {

    private getInterlocuteurInfos zInfo = new getInterlocuteurInfos();
    private boolean sendData;
    private JLabel loginLabel, passLabel, icon;
    private JTextField nom, login, pass;
    private JTextField prenom;
    private JTextField tel;
    private JTextField fax;
    private JTextField mail;

    public InterlocuteurDialog(JFrame parent, String title, boolean modal) {
        super(parent, title, modal);
        this.setSize(550, 270);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.initComponent();
    }

    public getInterlocuteurInfos showZDialog() {
        this.sendData = false;
        this.setVisible(true);
        return this.zInfo;
    }

    private void initComponent() {
    
        JPanel panInfos = new JPanel();
        panInfos.setBackground(Color.white);
        panInfos.setPreferredSize(new Dimension(620, 500));
        panInfos.setLayout(new BoxLayout(panInfos, BoxLayout.Y_AXIS));
        panInfos.setBorder(BorderFactory.createLineBorder(Color.black));
        nom = new JTextField();
        nom.setPreferredSize(new Dimension(450, 25));
        prenom = new JTextField();
        prenom.setPreferredSize(new Dimension(250, 25));
        tel = new JTextField();
        tel.setPreferredSize(new Dimension(450, 25));
        fax = new JTextField();
        fax.setPreferredSize(new Dimension(450, 25));
        mail = new JTextField();
        mail.setPreferredSize(new Dimension(450, 25));
        // panInfos.setBorder(new EmptyBorder(60, 60, 60, 60) );
        panInfos.setBorder(BorderFactory.createTitledBorder("Informations interlocuteur"));
        JPanel name = new JPanel();
        name.setLayout(new FlowLayout());
        name.setBackground(Color.white);
        name.add(new JLabel("Nom :"));
        name.add(nom);
        
        JPanel firstname = new JPanel();
        firstname.setBackground(Color.white);
        firstname.setLayout(new FlowLayout());
        firstname.add(new JLabel("Prénom :"));
        name.add(prenom);
        
        JPanel phone = new JPanel();
        phone.setLayout(new FlowLayout());
        phone.setBackground(Color.white);
        phone.add(new JLabel("Tel :"));
        phone.add(tel);
        
        JPanel Fax = new JPanel();
        Fax.setLayout(new FlowLayout());
        Fax.setBackground(Color.white);
        Fax.add(new JLabel("Fax :"));
        Fax.add(fax);
        
        JPanel email = new JPanel();
        email.setLayout(new FlowLayout());
        email.setBackground(Color.white);
        email.add(new JLabel("Email :"));
        email.add(mail);
        
        panInfos.add(name);
        panInfos.add(firstname);
        panInfos.add(phone);
        panInfos.add(Fax);
        panInfos.add(email);


        JPanel content = new JPanel();
        content.setBackground(Color.white);
        content.setBorder(new EmptyBorder(20, 30, 0, 10));
        content.setLayout(new BorderLayout());
        content.add(panInfos, BorderLayout.WEST);


        JPanel control = new JPanel();
        JButton okBouton = new JButton("OK");

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
}