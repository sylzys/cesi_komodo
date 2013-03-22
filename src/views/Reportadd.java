/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.UserActif;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import models.Client;

/**
 *
 * @author lavie
 */
public class Reportadd extends JPanel {
    private UserActif user;
    private Client cli;
    public Reportadd(UserActif user, Client client) {
        super();
        initPanel();
        this.user = user;
        this.cli = client;
    }
    
    public JPanel initPanel(){
        JPanel content = new JPanel();
        JPanel pnltop = new JPanel();
        
        JLabel lblretour = new JLabel(new ImageIcon("ressources/images/bouton_retour.png"));
        JLabel lbltitle = new JLabel("<html><center><h2 align=center>Ajouter un rapport</h2></center></html>");
        JLabel lblvide = new JLabel();
        pnltop.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnltop.add(lblretour);
        pnltop.add(lblvide);
        pnltop.add(lbltitle);
        
        pnltop.setPreferredSize(new Dimension(1000, 60));
        lbltitle.setPreferredSize(new Dimension(200,60));
        lblretour.setPreferredSize(new Dimension(70,60));
        lblvide.setPreferredSize(new Dimension(350,60));
        lblretour.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblretour.setToolTipText("Retour vers la fiche client"+this.cli.getClinom()+"");
        content.setPreferredSize(new Dimension(1000,700));
        content.setOpaque(false);
        pnltop.setOpaque(false);
        content.add(pnltop);
        return content;
    }
}
