/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import classes.Uniqid;
import controllers.UserActif;
import instances.ReportingInstance;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import models.Client;
import models.CurrentDatas;
import models.Enquete;
import models.Interlocuteur;

/**
 *
 * @author suly
 */
public class ReportList extends KContainer {
private UserActif user;
private Client cli;
    public ReportList(UserActif user, Client cli) {
        super();
        this.user = user;
        this.cli = cli;
        initPanel();
    }
     @Override
    protected void initPanel(){
        ReportingInstance re = ReportingInstance.getInstance();
        JPanel content = new JPanel();
        JPanel pnltop = new JPanel();
        JPanel pnlcenter = new JPanel();
        JPanel pnltitre = new JPanel();
        JLabel lblretour = new JLabel(new ImageIcon("ressources/images/bouton_retour.png"));
        JLabel lbltitle = new JLabel();
        JLabel lblvide = new JLabel();

        pnltop.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnltop.add(lblretour);
        pnltop.add(lblvide);
        pnltop.add(lbltitle);
        pnltop.setPreferredSize(new Dimension(1000, 60));
        pnlcenter.setPreferredSize(new Dimension(800, 570));
        lbltitle.setPreferredSize(new Dimension(200,60));
        lblretour.setPreferredSize(new Dimension(70,60));
        lblvide.setPreferredSize(new Dimension(350,60));
        lblretour.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblretour.setToolTipText("Retour vers la fiche client "+this.cli.getClinom()+"");
        content.setPreferredSize(new Dimension(1000,700));
        content.setOpaque(false);
        pnltop.setOpaque(false);  
        pnlcenter.setOpaque(false);
        pnltitre.setOpaque(false);
        pnltitre.setPreferredSize(new Dimension(600, 40));
        pnlcenter.add(pnltitre);
        TitledBorder title;
        title = BorderFactory.createTitledBorder("<html><h2>Liste des rapports</h2></html>");
        pnlcenter.setBorder(title);
        content.add(pnltop);
        content.add(pnlcenter);
        lblretour.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent me) {
                    Fenetre fen = Fenetre.getInstance();
                    fen.RenewClientdDetail(cli.getCliid());
                }
            });
        this.panel.add(content);
    }
}
