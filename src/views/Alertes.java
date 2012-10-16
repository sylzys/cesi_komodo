/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import classes.ButtonData;
import classes.LabelData;
import controllers.UserActif;
import instances.AlerteInstance;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import models.Suivdossier;

/**
 *
 * @author sylv
 */
public class Alertes extends KContainer{
    
    JLabel title = new JLabel ("PANNEAU ALERTES");
    List<Suivdossier> l_alerts;
    
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
        l_alerts = AlertInstance.GetAlertes("", new Hashtable());
        System.out.println("nb_alerts : " + l_alerts.size());
        for (Suivdossier tmp : l_alerts) {
            JPanel jp = new JPanel();
            jp.setPreferredSize(new Dimension(730, 70));
            jp.setMinimumSize(new Dimension(730, 70));
            jp.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            //jp.setLayout(new BoxLayout(jp, BoxLayout.X_AXIS));
            Box containerSte = Box.createHorizontalBox();
            LabelData LblSte = new LabelData("Societe foobar");
            LblSte.setId(tmp.getInterid());
            LblSte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            LblSte.setFont(new java.awt.Font("Tahoma", 1, 12));
            //Map attr = LblSte.getFont().getAttributes();
            //attr.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            //LblSte.setFont(new Font(attr));
            LblSte.addMouseListener(new java.awt.event.MouseAdapter() {
                Font original;
                @Override
                public void mouseEntered(MouseEvent e) {
                    original = e.getComponent().getFont();
                    Map attributes = original.getAttributes();
                    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    e.getComponent().setFont(original.deriveFont(attributes));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    e.getComponent().setFont(original);
                }
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    openSte(evt);
                }
            });
            containerSte.add(LblSte);
            containerSte.add(Box.createHorizontalStrut(5));
            containerSte.add(new JLabel(">"));
            containerSte.add(Box.createHorizontalStrut(5));
            containerSte.add(new JLabel(tmp.getSuivdosdate().toString()));
            containerSte.add(Box.createHorizontalStrut(5));
            containerSte.add(new JLabel(tmp.getSuivdosdate().toString()));
            containerSte.add(Box.createHorizontalStrut(25));
            containerSte.add(new JLabel(tmp.getSuivdoscom()));
            containerSte.add(Box.createHorizontalStrut(25));
            ButtonData details = new ButtonData("details");
            details.putData("alert", tmp);
            details.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    openKikoo(evt);
                }
            });
            containerSte.add(details);
            jp.add(containerSte);
            PanelListPane.add(jp);
            PanelListPane.add(Box.createRigidArea(new Dimension(0, 20)));
        }
        
        listScroller.setViewportView(PanelListPane);
        //listPane.add(listScroller, BorderLayout.PAGE_START);
        this.panel.add(listScroller);
    }
    
    private void openSte(MouseEvent evt) {
        LabelData lbl_tmp = (LabelData)evt.getComponent();
        JOptionPane jop = new JOptionPane();
        jop.showMessageDialog(null, "ouverture de la page societe id => " + lbl_tmp.getId(), "Ouverture page societe", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void openKikoo(MouseEvent evt) {
        ButtonData btn_tmp = (ButtonData)evt.getSource();
        Suivdossier alert_tmp = (Suivdossier)btn_tmp.getDataByKey("alert");
        JOptionPane jop = new JOptionPane();
        jop.showMessageDialog(null, "Details de l'alerte : suivdosid=>" + alert_tmp.getSuivdosid().toString(), "Details de l'aterte", JOptionPane.INFORMATION_MESSAGE);
    }
    
}
