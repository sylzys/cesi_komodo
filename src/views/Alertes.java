/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import classes.LinkLabelData;
import controllers.UserActif;
import controllers.getInterlocuteurInfos;
import instances.AlerteInstance;
import instances.HibernateConnection;
import instances.SuivDossierInstance;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import models.GetAlerte;
import models.Suivdossier;

/**
 *
 * @author sylv
 */
public class Alertes extends KContainer{
    
    JLabel title = new JLabel ("PANNEAU ALERTES");
    List<GetAlerte> l_alerts;
    Boolean histo = false;
    Integer filter_ste = -1;
    AlerteInstance AlertInstance = AlerteInstance.getInstance();
    SuivDossierInstance AltInstance = SuivDossierInstance.getInstance();


    public Alertes (UserActif user) {
        // pour affichage nouvelles alertes
        super();
        this.user = user;
        initPanel();
    }   
    
    public Alertes (UserActif user, Boolean histo) {
        // pour affichage histo alertes
        super();
        this.user = user;
        this.histo = histo;
        initPanel();
    }
    
    public Alertes (UserActif user, Integer id_ste) {
        // pour affichage alerte d'une societe (nouvelles et histo)
        super();
        this.user = user;
        this.filter_ste = id_ste;
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
        
        l_alerts = this.getAlertes();
        System.out.println("nb_alerts : " + l_alerts.size());
        for (GetAlerte tmp : l_alerts) {
            JPanel jp = new JPanel();
            jp.setPreferredSize(new Dimension(730, 120));
            jp.setMinimumSize(new Dimension(730, 120));
            jp.setMaximumSize(new Dimension(730, 120));
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
            if (!tmp.getSuividossuppr()) {
                Box containerRead = Box.createHorizontalBox();
                containerRead.add(LblRead);
                containerAlert.add(containerRead);
            }
            jp.add(containerAlert);
            PanelListPane.add(jp);
            PanelListPane.add(Box.createRigidArea(new Dimension(0, 20)));
        }
        if (this.filter_ste > -1) {
            JButton btn_return = new JButton("Retour a la fiche societe");
            btn_return.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    ClientDetail cd = new ClientDetail(filter_ste);
                }
            });
            PanelListPane.add(btn_return, Box.CENTER_ALIGNMENT);
        }
        listScroller.setViewportView(PanelListPane);
        //listPane.add(listScroller, BorderLayout.PAGE_START);
        this.panel.add(listScroller);
    }
    
    private List<GetAlerte> getAlertes() {
        // get alertes
        String where = "";
        if (this.filter_ste > -1) {
            where = "cliid=" + this.filter_ste;
        } else if (this.histo) {
            where = "suividossuppr = 't' AND utiid=" + this.user.getId();
        } else {
            where = "suividossuppr = 'f' AND utiid=" + this.user.getId();
        }
        return AlertInstance.GetAlertes(where, new Hashtable());
    }
    
    
    private void openSte(MouseEvent evt) {
        LinkLabelData lbl_tmp = (LinkLabelData)evt.getComponent();
        ClientDetail cd = new ClientDetail(lbl_tmp.getId());
    }
    
    private void openInter(MouseEvent evt) {
        LinkLabelData lbl_tmp = (LinkLabelData)evt.getComponent();
        InterlocuteurDialog interd = new InterlocuteurDialog(null, "Information interlocuteur", true, lbl_tmp.getId());
        getInterlocuteurInfos interInfos = interd.showZDialog(lbl_tmp.getId());
    }
    
    private void openCmd(MouseEvent evt) {
        LinkLabelData lbl_tmp = (LinkLabelData)evt.getComponent();
        Fenetre.getInstance().RenewCmd(lbl_tmp.getId());
    }
    
    private void readCmd(MouseEvent evt) {
        LinkLabelData lbl_tmp = (LinkLabelData)evt.getComponent();
        
        Suivdossier alerte;
        List<Suivdossier> alts = AltInstance.GetSuivDossier("suivdosid = " + lbl_tmp.getId(), new Hashtable());
        alerte = alts.get(0);
        alerte.setSuividossuppr(Boolean.TRUE);
        AltInstance.update(alerte);
        if (HibernateConnection.online == false)
        {
            HibernateConnection.newConnect(false);
        }
        else
        {
            HibernateConnection.newConnect(true);
        }
        Fenetre.getInstance().RenewAlert();
    }
    
}
