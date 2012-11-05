/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import classes.ButtonData;
import classes.LabelData;
import classes.LinkLabelData;
import controllers.UserActif;
import instances.AlerteInstance;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.IconUIResource;
import models.GetAlerte;

/**
 *
 * @author sylv
 */
public class Alertes extends KContainer{
    
    JLabel title = new JLabel ("PANNEAU ALERTES");
    List<GetAlerte> l_alerts;
    
    public Alertes (UserActif user) {
        super();
        this.user = user;
        initPanel();
    }   

    @Override
    protected
    void initPanel() {
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        
        JScrollPane listScroller = new JScrollPane();
        listScroller.setBorder(javax.swing.BorderFactory.createTitledBorder("Alertes"));
        listScroller.setPreferredSize(new Dimension(800, 600));
        listScroller.setBackground(new java.awt.Color(255, 255, 255));
        listScroller.getVerticalScrollBar().setUnitIncrement(10);
        JPanel PanelListPane = new JPanel();
        PanelListPane.setLayout(new BoxLayout(PanelListPane, BoxLayout.Y_AXIS));
        PanelListPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        PanelListPane.setBackground(new java.awt.Color(255, 255, 255));
        
        // get alertes
        AlerteInstance AlertInstance = AlerteInstance.getInstance();
        l_alerts = AlertInstance.GetAlertes("suividossuppr = 'f'", new Hashtable());
        System.out.println("nb_alerts : " + l_alerts.size());
        for (GetAlerte tmp : l_alerts) {
            JPanel jp = new JPanel();
            jp.setPreferredSize(new Dimension(730, 70));
            jp.setMinimumSize(new Dimension(730, 70));
            jp.setMaximumSize(new Dimension(730, 150));
            jp.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            Box containerAlert = Box.createVerticalBox();
            containerAlert.add(Box.createVerticalStrut(5));
            //jp.setLayout(new BoxLayout(containerAlert, BoxLayout.X_AXIS));
            Box containerSte = Box.createHorizontalBox();
            // creation label societe
            LinkLabelData LblSte = new LinkLabelData("Societe " + tmp.getClinom(), tmp.getCliid());
            LblSte.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    openSte(evt);
                }
            });
            // creation label inter
            LinkLabelData LblInter = new LinkLabelData(tmp.getInterprenom() + " " + tmp.getInternom(), tmp.getInterid());
            LblInter.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    openInter(evt);
                }
            });
            // creation label num commande
            LinkLabelData LblCmd = new LinkLabelData("Commande n. " + tmp.getComid(), tmp.getComid());
            LblCmd.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    openCmd(evt);
                }
            });
            LinkLabelData LblRead = new LinkLabelData("Marquer l'alerte comme lue", tmp.getSuivdosid());
            LblRead.setIcon(new ImageIcon("ressources/images/check.png"));
            LblRead.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    readCmd(evt);
                }
            });
            containerSte.add(LblSte);
            containerSte.add(Box.createHorizontalStrut(5));
            containerSte.add(new JLabel(">"));
            containerSte.add(Box.createHorizontalStrut(5));
            containerSte.add(LblInter);
            containerSte.add(Box.createHorizontalStrut(5));
            containerSte.add(new JLabel(">"));
            containerSte.add(Box.createHorizontalStrut(5));
            containerSte.add(LblCmd);
            containerAlert.add(containerSte);
            containerAlert.add(Box.createVerticalStrut(15));
            // infos commentaire
            Box containerCom = Box.createHorizontalBox();
            containerCom.add(Box.createHorizontalStrut(5));
            containerCom.add(new JLabel(tmp.getSuivdosdate().toString()));
            containerCom.add(Box.createHorizontalStrut(25));
            containerCom.add(new JLabel(tmp.getSuivdoscom()));
            containerAlert.add(containerCom);
            containerAlert.add(Box.createVerticalStrut(15));
            Box containerRead = Box.createHorizontalBox();
            containerRead.add(LblRead);
            containerAlert.add(containerRead);
            jp.add(containerAlert);
            PanelListPane.add(jp);
            PanelListPane.add(Box.createRigidArea(new Dimension(0, 20)));
        }
        
        listScroller.setViewportView(PanelListPane);
        //listPane.add(listScroller, BorderLayout.PAGE_START);
        this.panel.add(listScroller);
    }
    
    private void openSte(MouseEvent evt) {
        LinkLabelData lbl_tmp = (LinkLabelData)evt.getComponent();
        JOptionPane jop = new JOptionPane();
        jop.showMessageDialog(null, "ouverture de la page societe id => " + lbl_tmp.getId(), "Ouverture page societe", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void openInter(MouseEvent evt) {
        LinkLabelData lbl_tmp = (LinkLabelData)evt.getComponent();
        JOptionPane jop = new JOptionPane();
        jop.showMessageDialog(null, "ouverture de l'inter id => " + lbl_tmp.getId(), "Ouverture page inter", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void openCmd(MouseEvent evt) {
        LinkLabelData lbl_tmp = (LinkLabelData)evt.getComponent();
        JOptionPane jop = new JOptionPane();
        jop.showMessageDialog(null, "ouverture de la commande id/num => " + lbl_tmp.getId(), "Ouverture page commande", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void openKikoo(MouseEvent evt) {
        //ButtonData btn_tmp = (ButtonData)evt.getSource();
        //GetAlerte alert_tmp = (GetAlerte)btn_tmp.getDataByKey("alert");
        //JOptionPane jop = new JOptionPane();
        //jop.showMessageDialog(null, "Details de l'alerte : suivdosid=>" + alert_tmp.getCliid().toString(), "Details de l'aterte", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void readCmd(MouseEvent evt) {
        LinkLabelData lbl_tmp = (LinkLabelData)evt.getComponent();
        JOptionPane jop = new JOptionPane();
        jop.showMessageDialog(null, "marquer l'alerte comme lue id => " + lbl_tmp.getId(), "Lecture alerte", JOptionPane.INFORMATION_MESSAGE);
    }
    
}
