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
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import models.Client;
import models.GetReporting;
import sun.org.mozilla.javascript.internal.regexp.SubString;

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
    private JLabel lblmsg = new JLabel("<html><center><p color=#561513><b>Derniers rapports</b></p></center></html>");
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
        int nbreport = ri.nbOcc(cli.getCliid(), "GetReporting", "cliid");
        List<GetReporting> lstgp = ri.GetReporting("cliid",cli.getCliid(),5);
        imgdetail.setToolTipText("Visualiser tous les rapports");
        imgicon.setToolTipText("Saisir un rapport");
        pnlinfos.setPreferredSize(new Dimension(200, 185));      
        pnlcenter.setPreferredSize(new Dimension(695, 185));      
        pnltopcenter.setPreferredSize(new Dimension(685, 20));
        pnlmiddlecenter.setPreferredSize(new Dimension(695, 175));       
        pnlgraph.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));       
        lblmsg.setPreferredSize(new Dimension(130, 20));
        lblstat.setPreferredSize(new Dimension(160, 20));
        pnlmsg.setPreferredSize(new Dimension(350, 165));  
        pnlgraph.setPreferredSize(new Dimension(330, 165));
        pnlbutton.setPreferredSize(new Dimension(80, 165));   
        pnltopwest.setPreferredSize(new Dimension(190, 25));
        pnltopwest.setLayout(new FlowLayout(0,60,0));
        pnlmiddlewest.setPreferredSize(new Dimension(190, 175));
        pnltopbutton.setPreferredSize(new Dimension(70, 65));
        pnlcenterbutton.setPreferredSize(new Dimension(70, 70));
        pnlmiddlewest.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
        pnlcontent.setLayout(new BoxLayout(pnlcontent, BoxLayout.LINE_AXIS));
        pnlcontent.setLayout(new BorderLayout());            
        pnlcontent.setPreferredSize(new Dimension(980, 180));
        imgicon.setPreferredSize(new Dimension(60, 60));
        imgdetail.setPreferredSize(new Dimension(60, 60));
        imgicon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        imgdetail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnltopwest.add(lblinfos);
        pnlmiddlewest.add(new JLabel("<html><table><tr><td><p>Nb. Demande(s) : </p></td><td><p color=blue>"+nbdmd+"</p></td></tr>"
                + "<tr><td><p>Nb. Devis(s) : </p></td><td><p color=blue>"+nbdvi+"</p></td></tr>"
                + "<tr><td><p>Nb. Commande(s) : </p></td><td><p color=blue>"+nbcmd+"</p></td></tr>"
                + "<tr><td><p>Nb. Report(s) : </p></td><td><p color=blue>"+nbreport+"</p></td></tr>"
                + "</table></html>"));
        pnlinfos.add(pnltopwest, BorderLayout.NORTH);
        pnlinfos.add(pnlmiddlewest, BorderLayout.CENTER);
        
        pnltopcenter.setLayout(new FlowLayout(0,150,0)); 
        
        pnltopcenter.add(lblstat);
        pnltopcenter.add(lblmsg);
        
        pnlmiddlecenter.add(pnlgraph, BorderLayout.WEST);
        pnlmiddlecenter.add(pnlmsg, BorderLayout.EAST);
        
        pnlcenter.add(pnltopcenter, BorderLayout.NORTH);
        pnlcenter.add(pnlmiddlecenter, BorderLayout.CENTER);
        if(!lstgp.isEmpty())
        {
            for(GetReporting grp : lstgp)
            {
                JLabel imgstt = new JLabel();
                JPanel pnltitle = new JPanel();
                JLabel lbltitle = new JLabel();
                JLabel lbldesc = new JLabel();
                JLabel lbldte = new JLabel();
                pnltitle.setPreferredSize(new Dimension(350, 22));
                boolean statut = grp.isEnqpos();
                if(statut == true)
                {
                   imgstt.setIcon(new ImageIcon("ressources/images/fleche_vert.png"));
                }
                else
                {
                   imgstt.setIcon(new ImageIcon("ressources/images/fleche_rouge.png"));
                }
                String enqdetail = grp.getEnqdesc();
                String enqdesc = grp.getEnqdesc();
                int i = enqdesc.length();
                if(enqdesc.length() > 29)
                {
                    enqdesc = enqdesc.substring(0, 26);
                    lbldesc.setText("<html><p>"+enqdesc+" (...)</p></html>");
                }
                else
                {
                    lbldesc.setText("<html><p>"+enqdesc+"</p></html>");
                }
                String enqtitle = grp.getEnqint();
                SimpleDateFormat  simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
                String enqdte = simpleFormat.format(grp.getEnqdte());
                imgstt.setPreferredSize(new Dimension(20, 20));
                Font fonttitle = new Font("Arial",Font.BOLD,12);
                Font fontdesc = new Font("Arial",Font.HANGING_BASELINE,12);
                Font fontdte = new Font("Arial",Font.BOLD,11);
                lbltitle.setFont(fonttitle);
                lbltitle.setText("<html><p>"+enqtitle+" : </p></html>");
                lbltitle.setPreferredSize(new Dimension(85, 20));
                lbldesc.setFont(fontdesc);
                lbldesc.setToolTipText(enqdetail);
                lbldesc.setPreferredSize(new Dimension(165, 20));
                lbldte.setFont(fontdte);
                lbldte.setText("<html><p>"+enqdte+"</p></html>");
                lbldte.setPreferredSize(new Dimension(55, 20));
                pnltitle.add(imgstt);
                pnltitle.add(lbltitle);
                pnltitle.add(lbldesc);
                pnltitle.add(lbldte);
                pnlmsg.add(pnltitle);
                
            }
           pnlmsg.add(new JLabel("<html><p><b></b></p></html>")); 
        }
        else
        {
           pnlmsg.add(new JLabel("<html><p color=red><b>Aucun report</b></p></html>"), BorderLayout.CENTER);  
        }
        
        pnltopbutton.add(imgicon);
        
        pnlcenterbutton.add(imgdetail);
        
        pnlgraph.add(new JLabel("<html><p color=green><b></b></p></html>"), BorderLayout.NORTH);
        pnlbutton.add(pnltopbutton, BorderLayout.NORTH);
        pnlbutton.add(pnlcenterbutton, BorderLayout.CENTER);
        pnlcontent.add(pnlinfos, BorderLayout.WEST);
        pnlcontent.add(pnlcenter, BorderLayout.CENTER);
        pnlcontent.add(pnlbutton, BorderLayout.EAST);
        
        return pnlcontent;
    }
}
