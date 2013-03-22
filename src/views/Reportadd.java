/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.UserActif;
import instances.ReportingInstance;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import models.Client;
import models.Interlocuteur;

/**
 *
 * @author lavie
 */
public class Reportadd extends KContainer {
    private UserActif user;
    private Client cli;
    private List<Interlocuteur> inter; 
        public Reportadd(UserActif user, Client cli) {
        super();
        this.user = user;
        this.cli = cli;
        initPanel();
    }
    @Override
    protected void initPanel(){
        ReportingInstance re = ReportingInstance.getInstance();
        inter = re.getinter(cli.getCliid());
        JPanel content = new JPanel();
        JPanel pnltop = new JPanel();
        JPanel pnlcenter = new JPanel();
        JComboBox cbinter = new JComboBox();
        JLabel lblinter = new JLabel("Interlocuteur");
        JLabel lblretour = new JLabel(new ImageIcon("ressources/images/bouton_retour.png"));
        JLabel lbltitle = new JLabel("<html><center><h2 align=center>Ajouter un rapport</h2></center></html>");
        JLabel lblvide = new JLabel();
        pnltop.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnltop.add(lblretour);
        pnltop.add(lblvide);
        pnltop.add(lbltitle);
        
        pnltop.setPreferredSize(new Dimension(1000, 60));
        pnlcenter.setPreferredSize(new Dimension(1000, 640));
        lbltitle.setPreferredSize(new Dimension(200,60));
        lblretour.setPreferredSize(new Dimension(70,60));
        lblvide.setPreferredSize(new Dimension(350,60));
        lblretour.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblretour.setToolTipText("Retour vers la fiche client "+this.cli.getClinom()+"");
        content.setPreferredSize(new Dimension(1000,700));
        content.setOpaque(false);
        pnltop.setOpaque(false);  
        pnlcenter.setOpaque(false);
        cbinter.addItem("");
        for (final Interlocuteur in : inter)
        {
            cbinter.addItem((in.getInterid()+ ". " +in.getInterprenom() + " " + in.getInternom()));        
        
        }
        pnlcenter.add(lblinter);
        pnlcenter.add(cbinter);
        content.add(pnltop);
        content.add(pnlcenter);
        this.panel.add(content);
    }
}
