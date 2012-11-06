/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import classes.CheckBoxData;
import controllers.Synchro;
import controllers.UserActif;
import instances.HibernateConnection;
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
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 *
 * @author slavie
 */
public class SynchroView extends KContainer {
    private String req;
    private String fic;
    private String libreq;
    private JCheckBox chkslct = new JCheckBox();
    private List<CheckBoxData> listchk;
    JLabel title = new JLabel ();
    public SynchroView(UserActif user) {
    super();
    this.user = user;
    initPanel();
    }

    @Override
    protected
    void initPanel() {
        listchk = new ArrayList<CheckBoxData>();
        JPanel content = new JPanel();
        JPanel center = new JPanel();
        JScrollPane scroll = new JScrollPane(center);
        JPanel titre = new JPanel();
        JPanel pnlbtn = new JPanel();
        JButton button = new JButton("Synchroniser"); 
        JLabel lblSynchro = new JLabel(new ImageIcon("ressources/images/synchro.png"));
        Synchro sync = new Synchro();        
        titre.setOpaque(false);
        pnlbtn.setOpaque(false);
        titre.add(title, BorderLayout.CENTER);
        center.setOpaque(false);
        content.setOpaque(false);
        content.setLayout(new BorderLayout());
        center.setLayout(new BorderLayout());
        scroll.setPreferredSize(new Dimension(800,500));
        center.scrollRectToVisible(new Rectangle(0,center.getHeight(),10,10));
        if(HibernateConnection.online == false)
        {
            title.setPreferredSize(new Dimension(350,100));
            title.setText("<html><center<h2>Synchronisation de la base de données</h2><br><p color=red>"
                    + "Vous ne pouvez pas synchroniser hors ligne</p><center></html>");
            content.add(titre, BorderLayout.NORTH);
        }
        else if(sync.emptyFic() == true)
        {      
            FlowLayout flchk1 = new FlowLayout();
            JLabel lbl1 = new JLabel("<html><p color=blue>CLIENT</p></html>");
            JLabel lbl2 = new JLabel("<html><p color=blue>TABLE</p></html>");
            JLabel lbl3 = new JLabel("<html><p color=blue>ACTION</p></html>");
            lbl1.setBorder(new EmptyBorder(0, 10, 0, 0));
            lbl2.setBorder(new EmptyBorder(0, 10, 0, 0));
            lbl3.setBorder(new EmptyBorder(0, 10, 0, 0));
            chkslct.setBorder(new EmptyBorder(0, 10, 0, 0));
            chkslct.setOpaque(false);
            lbl1.setOpaque(true);
            lbl1.setBackground(Color.lightGray);
            lbl2.setOpaque(true);
            lbl2.setBackground(Color.lightGray);
            lbl3.setOpaque(true);
            lbl3.setBackground(Color.lightGray);
            chkslct.setOpaque(true);
            chkslct.setBackground(Color.lightGray);
            chkslct.setPreferredSize(new Dimension(70, 20));
            chkslct.setSelected(true);
            lbl1.setPreferredSize(new Dimension(180, 20));
            lbl2.setPreferredSize(new Dimension(180, 20));
            lbl3.setPreferredSize(new Dimension(300, 20));
            center.setLayout(flchk1);
            center.add(chkslct);
            center.add(lbl1);
            center.add(lbl2);
            center.add(lbl3);
            fic = sync.readFic();
            StringTokenizer strtok = new StringTokenizer(fic, "||");
            int i = 0;
            while (strtok.hasMoreTokens()) {
                i = i + 1;
                req = strtok.nextToken();
                String[] inter = req.split("interlocuteur:");
                int interid = -1;
                if(inter.length > 1)
                {
                    interid = Integer.parseInt(inter[1]);
                }
                String[] cli = req.split("client:");
                String nomclient = "";
                if(cli.length > 1)
                {
                    nomclient = cli[1];
                }
                String[] action = sync.readReq(req, interid, nomclient);
                FlowLayout flchk = new FlowLayout();
                CheckBoxData chkbox = new CheckBoxData(i, req);
                chkbox.setSelected(true);
                listchk.add(chkbox);
                JLabel lblclient = new JLabel(action[0].toUpperCase());
                JLabel lbltable = new JLabel(action[1].toUpperCase());
                JLabel lblaction = new JLabel(action[2].toUpperCase());
                lblclient.setBorder(new EmptyBorder(0, 10, 0, 0));
                lbltable.setBorder(new EmptyBorder(0, 10, 0, 0));
                lblaction.setBorder(new EmptyBorder(0, 10, 0, 0));
                chkbox.setBorder(new EmptyBorder(0, 10, 0, 0));
                chkbox.setPreferredSize(new Dimension(70, 35));
                lblclient.setPreferredSize(new Dimension(180, 35));
                lbltable.setPreferredSize(new Dimension(180, 35));
                lblaction.setPreferredSize(new Dimension(300, 35));
                center.setLayout(flchk);
                center.add(chkbox);
                center.add(lblclient);
                center.add(lbltable);
                center.add(lblaction);
                chkbox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                         CheckBoxData chk = (CheckBoxData)e.getSource();
                        System.out.println(chk.getId());
                    }
                });
            }
            chkslct.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(chkslct.isSelected() == true)
                    {
                         for(CheckBoxData chkdata: listchk)
                         {
                             chkdata.setSelected(true);
                         }
                    }
                    else
                    {
                        for(CheckBoxData chkdata: listchk)
                         {
                             chkdata.setSelected(false);
                         }
                    }
                }
            });
            center.setPreferredSize(new Dimension(750, 50 + (40 * i)));
            title.setPreferredSize(new Dimension(420,100)); 
            title.setText("<html><center><h2>Synchronisation de la base de données</h2><br><p color=green>Sélectionnez "
                    + "les actions à sauvegarder dans la base de données en ligne</p></center></html>");
            lblSynchro.setVisible(false);
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            button.setPreferredSize(new Dimension(120,35));
            button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Synchro sync = new Synchro();
                sync.record();
            }
            });
            pnlbtn.setBackground(Color.white);
            pnlbtn.add(button, BorderLayout.CENTER);
            pnlbtn.add(lblSynchro, BorderLayout.EAST);
            content.add(titre, BorderLayout.NORTH);
            content.add(scroll,BorderLayout.CENTER);  
            content.add(pnlbtn, BorderLayout.SOUTH);
        }
        else
        {
            title.setPreferredSize(new Dimension(350,100));
            title.setText("<html><center<h2>Synchronisation de la base de données</h2><br><p color=red>"
                    + "Vous n'avez aucune action à sauvegarder</p><center></html>");
            content.add(titre, BorderLayout.NORTH);
        } 

        this.panel.add(content);
    }
}
