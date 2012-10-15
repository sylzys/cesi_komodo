/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import classes.ButtonData;
import controllers.UserActif;
import instances.AlerteInstance;
import java.awt.Dimension;
import java.util.Hashtable;
import java.util.List;
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
            Box kikoo = Box.createHorizontalBox();
            kikoo.add(new JLabel(tmp.getSuivdosdate().toString()));
            kikoo.add(Box.createHorizontalStrut(25));
            kikoo.add(new JLabel(tmp.getSuivdoscom()));
            kikoo.add(Box.createHorizontalStrut(25));
            ButtonData details = new ButtonData("details");
            details.putData("alert", tmp);
            details.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    ButtonData btn_tmp = (ButtonData)evt.getSource();
                    Suivdossier alert_tmp = (Suivdossier)btn_tmp.getDataByKey("alert");
                    JOptionPane jop = new JOptionPane();
                    jop.showMessageDialog(null, "Details de l'alerte : interid=>" + alert_tmp.getInterid().toString(), "Details de l'aterte", JOptionPane.INFORMATION_MESSAGE);
                }
            });
            kikoo.add(details);
            jp.add(kikoo);        
            PanelListPane.add(jp);
            PanelListPane.add(Box.createRigidArea(new Dimension(0, 20)));
        }
        
        listScroller.setViewportView(PanelListPane);
        //listPane.add(listScroller, BorderLayout.PAGE_START);
        this.panel.add(listScroller);
    }
}
