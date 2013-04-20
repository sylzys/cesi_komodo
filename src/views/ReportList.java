/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import classes.CheckBoxData;
import classes.LabelData;
import classes.Uniqid;
import controllers.UserActif;
import instances.InterlocuteurInstance;
import instances.ReportingInstance;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import models.Client;
import models.CurrentDatas;
import models.Enquete;
import models.GetReporting;
import models.Interlocuteur;

/**
 *
 * @author suly
 */
public class ReportList extends KContainer {
private UserActif user;
private Client cli;
private List<LabelData> listlbl;
private List<LabelData> listlbl2;
private List<GetReporting> reportlist;
private Enquete enq;
    public ReportList(UserActif user, Client cli) {
        super();
        this.user = user;
        this.cli = cli;
        initPanel();
    }
     @Override
    protected void initPanel(){
        ReportingInstance re = ReportingInstance.getInstance();
        reportlist = re.GetReporting("cliid", cli.getCliid(),-1);
        listlbl = new ArrayList<LabelData>();
        JPanel content = new JPanel();
        JPanel pnltop = new JPanel();
        JPanel pnlcenter = new JPanel();
        JPanel pnltitre = new JPanel();
        JLabel lblretour = new JLabel(new ImageIcon("ressources/images/bouton_retour.png"));
        JLabel lbltitle = new JLabel();
        JLabel lblvide = new JLabel();
        JScrollPane scroll = new JScrollPane(pnlcenter);
        pnltop.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlcenter.setLayout(new BorderLayout());
        pnlcenter.scrollRectToVisible(new Rectangle(0, pnlcenter.getHeight(), 10, 10));
        
        FlowLayout flchk1 = new FlowLayout();
        JLabel lbl1 = new JLabel("<html><p color=blue>TITRE</p></html>");
        JLabel lbl2 = new JLabel("<html><p color=blue>INTERLOCUTEUR</p></html>");
        JLabel lbl3 = new JLabel("<html><p color=blue>DATE</p></html>");
        JLabel lbl4 = new JLabel("<html><p color=blue>DESCRIPTION</p></html>");
        JLabel lbl5 = new JLabel("<html><p color=blue>ETAT</p></html>");
        JLabel lbl6 = new JLabel("<html><p color=blue></p></html>");
        lbl1.setBorder(new EmptyBorder(0, 10, 0, 0));
        lbl2.setBorder(new EmptyBorder(0, 10, 0, 0));
        lbl3.setBorder(new EmptyBorder(0, 10, 0, 0));
        lbl4.setBorder(new EmptyBorder(0, 10, 0, 0));
        lbl5.setBorder(new EmptyBorder(0, 10, 0, 0));
        lbl6.setBorder(new EmptyBorder(0, 10, 0, 0));
        lbl1.setOpaque(true);
        lbl1.setBackground(Color.lightGray);
        lbl2.setOpaque(true);
        lbl2.setBackground(Color.lightGray);
        lbl3.setOpaque(true);
        lbl3.setBackground(Color.lightGray);
        lbl4.setOpaque(true);
        lbl4.setBackground(Color.lightGray);
        lbl5.setOpaque(true);
        lbl5.setBackground(Color.lightGray);
        lbl6.setOpaque(true);
        lbl1.setPreferredSize(new Dimension(150, 20));
        lbl2.setPreferredSize(new Dimension(120, 20));
        lbl3.setPreferredSize(new Dimension(100, 20));
        lbl4.setPreferredSize(new Dimension(320, 20));
        lbl5.setPreferredSize(new Dimension(50, 20));
        lbl6.setPreferredSize(new Dimension(70, 20));
        pnlcenter.setLayout(flchk1);
        pnlcenter.add(lbl1);
        pnlcenter.add(lbl2);
        pnlcenter.add(lbl3);
        pnlcenter.add(lbl4);
        pnlcenter.add(lbl5);
        pnlcenter.add(lbl6);
        int i = 0;
        for(GetReporting report : reportlist)
        {
            InterlocuteurInstance inter = InterlocuteurInstance.getInstance();
            Hashtable hh = new Hashtable();
            hh.put("interid", report.getInterid());
            List<Interlocuteur> interdetail = inter.GetInterlocuteurs("where interid = :interid", hh);
            String interinfo = "";
            for (final Interlocuteur in : interdetail)
            {
                interinfo = in.getInterprenom() + " " + in.getInternom();
            }
            String title = report.getEnqint();
            Date date = report.getEnqdte();
            SimpleDateFormat formater = null;
            formater = new SimpleDateFormat("dd/MM/yy");
            String dte = formater.format(date);
            String descr = report.getEnqdesc();
            JLabel lbldesc = new JLabel();
            lbldesc.setToolTipText(descr);
            int j = descr.length();
            if(j > 44)
            {
                descr = descr.substring(0, 41);
                lbldesc.setText("<html><p>"+descr+" (...)</p></html>");
            }
            else
            {
                lbldesc.setText("<html><p>"+descr+"</p></html>");
            }
            JLabel lbltitre = new JLabel();
            lbltitre.setToolTipText(title);
            int k = title.length();
            if(k > 20)
            {
                title = title.substring(0,17);
                lbltitre.setText("<html><p>"+title+" (...)</p></html>");
            }
            else
            {
                lbltitre.setText("<html><p>"+title+"</p></html>");
            }
            boolean etat = report.isEnqpos();
            JLabel lbletat = new JLabel();
            if(etat == true)
            {

               lbletat.setIcon(new ImageIcon("ressources/images/fleche_vert.png"));
            }
            else
            {
               lbletat.setIcon(new ImageIcon("ressources/images/fleche_rouge.png"));
            }
            FlowLayout flchk = new FlowLayout();
            Hashtable ht2 = new Hashtable();
            ht2.put("objet", report);
            LabelData lbldel = new LabelData(i, ht2);
            lbldel.setIcon(new ImageIcon("ressources/images/delete.png"));
            lbldel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            lbldel.setToolTipText("Supprimer le rapport");
            LabelData lbledit = new LabelData(i, ht2);
            lbledit.setIcon(new ImageIcon("ressources/images/edit.png"));
            lbledit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            lbledit.setToolTipText("Modifier le rapport");
            listlbl.add(lbldel);
            listlbl.add(lbledit);
            lbldel.setBorder(new EmptyBorder(0, 10, 0, 0));
            lbldel.setPreferredSize(new Dimension(35, 35));
            lbledit.setBorder(new EmptyBorder(0, 10, 0, 0));
            lbledit.setPreferredSize(new Dimension(35, 35));
            JLabel lblinter = new JLabel(interinfo);
            JLabel lbldate = new JLabel(dte);
            lbltitre.setBorder(new EmptyBorder(0, 10, 0, 0));
            lblinter.setBorder(new EmptyBorder(0, 10, 0, 0));
            lbldate.setBorder(new EmptyBorder(0, 10, 0, 0));
            lbldesc.setBorder(new EmptyBorder(0, 10, 0, 0));
            lbletat.setBorder(new EmptyBorder(0, 10, 0, 0));
            lbltitre.setPreferredSize(new Dimension(150, 35));
            lblinter.setPreferredSize(new Dimension(120, 35));
            lbldate.setPreferredSize(new Dimension(100, 35));
            lbldesc.setPreferredSize(new Dimension(320, 35));
            lbletat.setPreferredSize(new Dimension(50, 35));
            pnlcenter.setLayout(flchk);
            pnlcenter.add(lbltitre);
            pnlcenter.add(lblinter);
            pnlcenter.add(lbldate);
            pnlcenter.add(lbldesc);
            pnlcenter.add(lbletat);
            pnlcenter.add(lbledit);
            pnlcenter.add(lbldel);
            lbldel.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent me) {
                        int option = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce rapport ?", "Suppression du rapport", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (option == JOptionPane.OK_OPTION)
                        {
                            ReportingInstance re = ReportingInstance.getInstance();
                            LabelData lbldata = (LabelData) me.getComponent();
                            Hashtable tmp = lbldata.getData();
                            GetReporting gr = (GetReporting)tmp.get("objet");
                            re.slctenqbdd(gr);
                            Enquete enq = re.getReporting();
                            enq.setEnqsuppr(true);
                            re.updtbdd(enq);
                            Fenetre fen = Fenetre.getInstance();
                            fen.renewRapport(cli);
                        }
                    }
                 });
            lbledit.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent me) {
                    ReportingInstance re = ReportingInstance.getInstance();
                    LabelData lbldata = (LabelData) me.getComponent();
                    Hashtable tmp = lbldata.getData();
                    GetReporting gr = (GetReporting)tmp.get("objet");
                    re.slctenqbdd(gr);
                    Enquete enq = re.getReporting();
                    Fenetre fen = Fenetre.getInstance();
                    Reportedit rep = new Reportedit(fen.user, cli, enq);
                    fen.RenewContener(rep.getPanel());
                }
            });
            i = i + 1;           
        }
        pnltop.add(lblretour);
        pnltop.add(lblvide);
        pnltop.add(lbltitle);
        pnltop.setPreferredSize(new Dimension(1000, 60));
        pnlcenter.setPreferredSize(new Dimension(850, 50 + (40 * i)));
        lbltitle.setPreferredSize(new Dimension(200,60));
        lblretour.setPreferredSize(new Dimension(70,60));
        lblvide.setPreferredSize(new Dimension(830,60));
        scroll.setPreferredSize(new Dimension(900, 600));
        lblvide.setText("<html><center><h2 style=\"padding-left: 250px;\">Liste des rapports</h2></center></html>");
        lblretour.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblretour.setToolTipText("Retour vers la fiche client "+this.cli.getClinom()+"");
        content.setPreferredSize(new Dimension(1000,700));
        content.setOpaque(false);
        pnltop.setOpaque(false);  
        pnlcenter.setOpaque(false);
        pnltitre.setOpaque(false);
        pnltitre.setPreferredSize(new Dimension(600, 40));
        content.add(pnltop);
        content.add(scroll);
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
