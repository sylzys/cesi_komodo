package views;

import controllers.getLoginInfos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class loginDialog extends JDialog {

    private getLoginInfos zInfo = new getLoginInfos();
    private boolean sendData;
    private JLabel loginLabel, passLabel, icon;
    private JTextField nom, login, pass;

    public loginDialog(JFrame parent, String title, boolean modal) {
        super(parent, title, modal);
        this.setSize(550, 270);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.initComponent();
    }

    public getLoginInfos showZDialog() {
        this.sendData = false;
        this.setVisible(true);
        return this.zInfo;
    }

    private void initComponent() {
        //Icône
        icon = new JLabel(new ImageIcon("ressources/images/komodo.jpg"));
        JPanel panIcon = new JPanel();
        panIcon.setPreferredSize(new Dimension(120, 200));
        panIcon.setBackground(Color.white);
        panIcon.setLayout(new BorderLayout());
        panIcon.add(icon, BorderLayout.CENTER);

        //Le nom
        JPanel panInfos = new JPanel();
        panInfos.setBackground(Color.white);
        panInfos.setPreferredSize(new Dimension(320, 100));
        panInfos.setBorder(BorderFactory.createLineBorder(Color.black));
        login = new JTextField();
        login.setPreferredSize(new Dimension(150, 25));
        pass = new JPasswordField();
        pass.setPreferredSize(new Dimension(150, 25));
        // panInfos.setBorder(new EmptyBorder(60, 60, 60, 60) );
        panInfos.setBorder(BorderFactory.createTitledBorder("Entrez vos identifiants"));
        loginLabel = new JLabel("Identifiant :");
        panInfos.add(loginLabel);
        panInfos.add(login);
        passLabel = new JLabel("Mot de passe :");
        panInfos.add(passLabel);
        panInfos.add(pass);


        JPanel content = new JPanel();
        content.setBackground(Color.white);
        content.setBorder(new EmptyBorder(20, 30, 0, 10));
        content.setLayout(new BorderLayout());
        content.add(panInfos, BorderLayout.WEST);


        JPanel control = new JPanel();
        JButton okBouton = new JButton("OK");

        okBouton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                zInfo = new getLoginInfos(login.getText(), pass.getText());
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

        this.getContentPane().add(panIcon, BorderLayout.WEST);
        this.getContentPane().add(content, BorderLayout.CENTER);
        this.getContentPane().add(control, BorderLayout.SOUTH);
    }
}