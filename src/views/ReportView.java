/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.Replication;
import controllers.Synchro;
import controllers.UserActif;
import instances.HibernateConnection;
import instances.ReportingInstance;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

/**
 *
 * @author suly
 */
public class ReportView extends JPanel{
    private JPanel pnlcontent = new JPanel();
    private JPanel pnlinfos = new JPanel();
    private JPanel pnlmsg = new JPanel();
    private JPanel pnlbutton = new JPanel();
    private JPanel pnltopinfos = new JPanel();
    private JPanel pnltopmsg = new JPanel();
    private JPanel pnltopbutton = new JPanel();
    private JPanel pnlmiddleinfos = new JPanel();
    private JPanel pnlmiddlemsg = new JPanel();
    private JPanel pnlmiddlebutton = new JPanel();
    private JLabel lblinfos = new JLabel("<html><p color=#561513><b>Informations</b></p></html>");   
    private JLabel lblmsg = new JLabel("<html><center><p color=#561513><b>Derniers rapports</b></p></center></html>");
    private JLabel lblicon = new JLabel("");
    private JLabel imgicon = new JLabel(new ImageIcon("ressources/images/iconreport.png"));
    private JLabel imgdetail = new JLabel(new ImageIcon("ressources/images/reportdetail.png"));
    public Client cli;
    public ReportView()
    {
        super();
    }
    public JPanel initPanel(Client client)
    {
        cli = client;
        ReportingInstance ri = ReportingInstance.getInstance(); 
        BigDecimal total = ri.totalcmd(cli.getCliid());
        int nbdevok = ri.nbdevok(cli.getCliid());
        int nbcmdend = ri.nbcmdend(cli.getCliid());
        int nbdmd = ri.nbOcc(cli.getCliid(), "DetailsDemande", "cliid");
        int nbdvi = ri.nbOcc(cli.getCliid(), "DetailDevis", "cliid");
        int nbcmd = ri.nbOcc(cli.getCliid(), "DetailCommande", "cliid");
        int nbreport = ri.nbOcc(cli.getCliid(), "GetReporting", "cliid");
        String img = "";
        Boolean etat = ri.etat(cli.getCliid());
        if(nbreport != 0 && etat == true)
        {
            img = "<img src=\"file:ressources/images/fleche_vert.png\">";
        }
        else if(nbreport != 0 && etat == false)
        {
            img = "<img src=\"file:ressources/images/fleche_rouge.png\">";
        }
        else if(nbreport == 0)
        {
            img = "<p color=red>Aucun rapport</p>";
        }
        List<GetReporting> lstgp = ri.GetReporting("cliid",cli.getCliid(),5);
        pnlcontent.setBackground(Color.white);
        pnlbutton.setBackground(Color.white);
        pnlinfos.setBackground(Color.white);
        pnlmiddlebutton.setBackground(Color.white);
        pnlmiddleinfos.setBackground(Color.white);
        pnlmiddlemsg.setBackground(Color.white);
        pnlmsg.setBackground(Color.white);
        pnltopbutton.setBackground(Color.white);
        pnltopinfos.setBackground(Color.white);
        pnltopmsg.setBackground(Color.white);
        imgdetail.setToolTipText("Visualiser tous les rapports");
        imgicon.setToolTipText("Saisir un rapport");
        pnlinfos.setPreferredSize(new Dimension(350, 185));       
        pnltopinfos.setPreferredSize(new Dimension(350, 25));
        pnlmiddleinfos.setPreferredSize(new Dimension(350, 160));    
        pnlmsg.setPreferredSize(new Dimension(550, 185));       
        pnltopmsg.setPreferredSize(new Dimension(550, 25));
        pnlmiddlemsg.setPreferredSize(new Dimension(550, 160)); 
        pnlbutton.setPreferredSize(new Dimension(80, 185));       
        pnltopbutton.setPreferredSize(new Dimension(80, 25));
        pnlmiddlebutton.setPreferredSize(new Dimension(80, 160)); 
        pnlmiddleinfos.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));   
        pnlcontent.setLayout(new BoxLayout(pnlcontent, BoxLayout.LINE_AXIS));
        pnlcontent.setLayout(new BorderLayout());            
        pnlcontent.setPreferredSize(new Dimension(980, 180));       
        imgicon.setPreferredSize(new Dimension(60, 60));
        imgdetail.setPreferredSize(new Dimension(60, 60));
        imgicon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        imgdetail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnltopinfos.add(lblinfos);
        pnlmiddleinfos.add(new JLabel("<html><table><tr><td><p>Nb. Demande(s) : </p></td><td><p color=blue>"+nbdmd+"</p></td>"
                + "<td><p> --- &nbsp;&nbsp; Total CA ("+Calendar.getInstance().get(Calendar.YEAR)+") : </p></td><td><p color=green> &nbsp;"+total+" €</p></td></tr>"
                + "<tr><td><p>Nb. Devis(s) : </p></td><td><p color=blue>"+nbdvi+"</p></td>"
                + "<td><p> --- &nbsp;&nbsp; Acceptés(s) : </p></td><td><p color=green> &nbsp;"+nbdevok+"</p></td></tr>"
                + "<tr><td><p>Nb. Commande(s) : </p></td><td><p color=blue>"+nbcmd+"</p></td>"
                + "<td><p> --- &nbsp;&nbsp; Livrée(s) : </p></td><td><p color=green> &nbsp;"+nbcmdend+"</p></td></tr>"
                + "<tr><td><p>Nb. Rapport(s) : </p></td><td><p color=blue>"+nbreport+"</p></td>"
                + "<td><p> --- &nbsp;&nbsp; Etat (10 dern.) : </p></td><td>"+img+"</td></tr>"
                + "</table></html>"));
        pnltopmsg.add(lblmsg);
        pnltopbutton.add(lblicon);
        pnlinfos.add(pnltopinfos, BorderLayout.NORTH);
        pnlinfos.add(pnlmiddleinfos, BorderLayout.CENTER);
        pnlmsg.add(pnltopmsg, BorderLayout.NORTH);
        pnlmsg.add(pnlmiddlemsg, BorderLayout.CENTER);
        pnlbutton.add(pnltopbutton, BorderLayout.NORTH);
        pnlbutton.add(pnlmiddlebutton, BorderLayout.CENTER);
        
        if(!lstgp.isEmpty())
        {
            for(GetReporting grp : lstgp)
            {
                JLabel imgstt = new JLabel();
                JPanel pnltitle = new JPanel();
                JLabel lbltitle = new JLabel();
                JLabel lbldesc = new JLabel();
                JLabel lbldte = new JLabel();
                pnltitle.setPreferredSize(new Dimension(500, 22));
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
                if(i > 49)
                {
                enqdesc = enqdesc.substring(0, 46);
                lbldesc.setText("<html><p>"+enqdesc+" (...)</p></html>");
                }
                else
                {
                    lbldesc.setText("<html><p>"+enqdesc+"</p></html>");
                }
                String enqtitle = grp.getEnqint();
                int j = enqtitle.length();
                if(j > 10)
                {
                    enqtitle = enqtitle.substring(0, 7);
                    lbltitle.setText("<html><p>"+enqtitle+" (...)</p></html>");
                }
                else
                {
                    lbltitle.setText("<html><p>"+enqtitle+"</p></html>");
                }
                SimpleDateFormat  simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
                String enqdte = simpleFormat.format(grp.getEnqdte());
                imgstt.setPreferredSize(new Dimension(20, 20));
                Font fonttitle = new Font("Arial",Font.BOLD,12);
                Font fontdesc = new Font("Arial",Font.HANGING_BASELINE,12);
                Font fontdte = new Font("Arial",Font.BOLD,11);
                lbltitle.setFont(fonttitle);
                lbltitle.setPreferredSize(new Dimension(85, 20));
                lbldesc.setFont(fontdesc);
                lbldesc.setToolTipText(enqdetail);
                lbldesc.setPreferredSize(new Dimension(300, 20));
                lbldte.setFont(fontdte);
                lbldte.setText("<html><p>"+enqdte+"</p></html>");
                lbldte.setPreferredSize(new Dimension(55, 20));
                pnltitle.add(imgstt);
                pnltitle.add(lbltitle);
                pnltitle.add(lbldesc);
                pnltitle.add(lbldte);
                pnltitle.setBackground(Color.white);
                pnlmiddlemsg.add(pnltitle);
            }
        }
        else
        {
           pnlmiddlemsg.add(new JLabel("<html><p color=red><b>Aucun rapport</b></p></html>"), BorderLayout.CENTER);  
        }
        pnlmiddlebutton.add(imgicon, BorderLayout.NORTH);     
        pnlmiddlebutton.add(imgdetail, BorderLayout.SOUTH);
        pnlbutton.add(pnltopbutton, BorderLayout.NORTH);
        pnlbutton.add(pnlmiddlebutton, BorderLayout.CENTER);
        pnlcontent.add(pnlinfos, BorderLayout.WEST);
        pnlcontent.add(pnlmsg, BorderLayout.CENTER);
        pnlcontent.add(pnlbutton, BorderLayout.EAST);
        imgicon.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent me) {
                    Fenetre fen = Fenetre.getInstance();
                    Reportadd re = new Reportadd(fen.user, cli);
                    fen.RenewContener(re.getPanel());
                }
            });
        imgdetail.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent me) {
                    Fenetre fen = Fenetre.getInstance();
                    ReportList re = new ReportList(fen.user, cli);
                    fen.RenewContener(re.getPanel());
                }
            });
        return pnlcontent;
    }
}
