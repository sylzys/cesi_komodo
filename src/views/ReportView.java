/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import instances.ReportingInstance;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import models.Client;

/**
 *
 * @author suly
 */
public class ReportView extends JPanel{
    private JPanel pnlcontent = new JPanel();
    private JPanel pnlinfos = new JPanel();
    private JPanel pnlcenter = new JPanel();
    private JPanel pnltopcenter = new JPanel();
    private JPanel pnlmiddlecenter = new JPanel();
    private JPanel pnltopwest = new JPanel();
    private JPanel pnlmiddlewest = new JPanel();
    private JLabel lblinfos = new JLabel("<html><p color=#561513><b>Informations</b></p></html>");   
    private JLabel lblmsg = new JLabel("<html><center><p color=#561513><b>Derniers reports</b></p></center></html>");
    private JLabel lblstat = new JLabel("<html><center><p color=#561513><b>Statut</b></p></center></html>");
    private JPanel pnlmsg = new JPanel();
    private JPanel pnlgraph = new JPanel();
    private JPanel pnlbutton = new JPanel();
    private JPanel pnltopbutton = new JPanel();
    private JPanel pnlcenterbutton = new JPanel();
    private JLabel imgicon = new JLabel(new ImageIcon("ressources/images/iconreport.png"));
    private JLabel imgdetail = new JLabel(new ImageIcon("ressources/images/reportdetail.png"));
    public ReportView()
    {
        super();
    }
    public JPanel initPanel(Client cli)
    {
        ReportingInstance ri = ReportingInstance.getInstance(); 
        int nbdmd = ri.nbOcc(cli.getCliid(), "DetailsDemande", "cliid");
        int nbdvi = ri.nbOcc(cli.getCliid(), "DetailDevis", "cliid");
        int nbcmd = ri.nbOcc(cli.getCliid(), "DetailCommande", "cliid");
        pnlinfos.setPreferredSize(new Dimension(200, 150));      
        pnlcenter.setPreferredSize(new Dimension(680, 150));      
        pnltopcenter.setPreferredSize(new Dimension(675, 20));
        pnlmiddlecenter.setPreferredSize(new Dimension(675, 115));       
        pnlgraph.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));       
        lblmsg.setPreferredSize(new Dimension(130, 20));
        lblstat.setPreferredSize(new Dimension(160, 20));
        pnlmsg.setPreferredSize(new Dimension(330, 100));
        pnlgraph.setPreferredSize(new Dimension(330, 115));
        pnlbutton.setPreferredSize(new Dimension(100, 150));
        pnltopwest.setPreferredSize(new Dimension(190, 25));
        pnltopwest.setLayout(new FlowLayout(0,60,0));
        pnlmiddlewest.setPreferredSize(new Dimension(190, 110));
        pnltopbutton.setPreferredSize(new Dimension(95, 65));
        pnlcenterbutton.setPreferredSize(new Dimension(95, 70));
        pnlmiddlewest.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
        pnlcontent.setLayout(new BoxLayout(pnlcontent, BoxLayout.LINE_AXIS));
        pnlcontent.setLayout(new BorderLayout());            
        pnlcontent.setPreferredSize(new Dimension(980, 150));
        imgicon.setPreferredSize(new Dimension(60, 60));
        imgdetail.setPreferredSize(new Dimension(60, 60));
        imgicon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        imgdetail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnltopwest.add(lblinfos);
        pnlmiddlewest.add(new JLabel("<html><p color=green><b>Nb. Demande(s) : "+nbdmd+"</b></p></html>"));
        pnlmiddlewest.add(new JLabel("<html><p color=green><b>Nb. Devis(s) : "+nbdvi+"</b></p></html>"));
        pnlmiddlewest.add(new JLabel("<html><p color=green><b>Nb. Commande(s) : "+nbcmd+"</b></p></html>"));
        pnlinfos.add(pnltopwest, BorderLayout.NORTH);
        pnlinfos.add(pnlmiddlewest, BorderLayout.CENTER);
        
        pnltopcenter.setLayout(new FlowLayout(0,150,0)); 
        
        pnltopcenter.add(lblstat);
        pnltopcenter.add(lblmsg);
        
        pnlmiddlecenter.add(pnlgraph, BorderLayout.WEST);
        pnlmiddlecenter.add(pnlmsg, BorderLayout.EAST);
        
        pnlcenter.add(pnltopcenter, BorderLayout.NORTH);
        pnlcenter.add(pnlmiddlecenter, BorderLayout.CENTER);
              
        pnlmsg.add(new JLabel("<html><p color=green><b>Message :</b></p></html>"), BorderLayout.NORTH);
        
        pnltopbutton.add(imgicon);
        
        pnlcenterbutton.add(imgdetail);
        
        pnlgraph.add(new JLabel("<html><p color=green><b>Graphique :</b></p></html>"), BorderLayout.NORTH);
        pnlbutton.add(pnltopbutton, BorderLayout.NORTH);
        pnlbutton.add(pnlcenterbutton, BorderLayout.CENTER);
        pnlcontent.add(pnlinfos, BorderLayout.WEST);
        pnlcontent.add(pnlcenter, BorderLayout.CENTER);
        pnlcontent.add(pnlbutton, BorderLayout.EAST);
        
        return pnlcontent;
    }
}
