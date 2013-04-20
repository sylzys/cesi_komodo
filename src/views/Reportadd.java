/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import classes.Uniqid;
import com.google.common.base.Strings;
import controllers.UserActif;
import instances.ReportingInstance;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import models.Client;
import models.CurrentDatas;
import models.Enquete;
import models.Interlocuteur;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author lavie
 */
public class Reportadd extends KContainer {
    private JComboBox cbinter = new JComboBox();
    private JXDatePicker date = new JXDatePicker();
    private JTextArea txtdesc = new JTextArea(7, 40);
    private  JRadioButton upButton   = new JRadioButton("<html><img src=\"file:ressources/images/fleche_vert.png\"></html>"  , true);
    private JRadioButton downButton    = new JRadioButton("<html><img src=\"file:ressources/images/fleche_rouge.png\"></html>"   , false);
    private ButtonGroup bgroup = new ButtonGroup();
    private JTextField txttitre = new JTextField();
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
        JPanel pnllblinter = new JPanel();
        JPanel pnllbltitre = new JPanel();
        JPanel pnlcbinter = new JPanel();
        JPanel pnllbldte = new JPanel();
        JPanel pnldte = new JPanel();
        JPanel pnllbldesc = new JPanel();
        JPanel pnldesc = new JPanel();
        JLabel lbltitre = new JLabel("Titre du rapport");
        JLabel lbldesc = new JLabel("Description");
        JLabel lbletat = new JLabel("Etat");
        JPanel pnllbletat = new JPanel();
        JPanel pnltitre = new JPanel();
        JLabel lblinter = new JLabel("Interlocuteur");
        JLabel lbldate = new JLabel("Date du rapport");
        JLabel lblretour = new JLabel(new ImageIcon("ressources/images/bouton_retour.png"));
        JLabel lbltitle = new JLabel();
        JLabel lblvide = new JLabel();
       
        JButton btnsubmit = new JButton("Enregistrer le rapport");
        JPanel pnlbtnsubmit = new JPanel();
        txttitre.setPreferredSize(new Dimension(250, 25));
        txttitre.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        bgroup.add(upButton);
        bgroup.add(downButton);
        
        JPanel radioPanel = new JPanel();
        radioPanel.add(upButton);
        radioPanel.add(downButton);
        txtdesc.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        txtdesc.setPreferredSize(new Dimension(500, 200));
        txtdesc.setLineWrap(true);
        pnltop.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnltop.add(lblretour);
        pnltop.add(lblvide);
        pnltop.add(lbltitle);
        btnsubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Date dtecur = new Date();
        date.setDate(dtecur);
        pnltop.setPreferredSize(new Dimension(1000, 60));
        pnlcenter.setPreferredSize(new Dimension(800, 570));
        lbltitle.setPreferredSize(new Dimension(200,60));
        lblretour.setPreferredSize(new Dimension(70,60));
        lblvide.setPreferredSize(new Dimension(350,60));
        lblretour.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblretour.setToolTipText("Retour vers la fiche client "+this.cli.getClinom()+"");
        content.setPreferredSize(new Dimension(1000,700));
        content.setOpaque(false);
        pnltop.setOpaque(false);  
        pnlcenter.setOpaque(false);
        pnlcbinter.setOpaque(false);
        pnldte.setOpaque(false);
        pnllbldte.setOpaque(false);
        pnllblinter.setOpaque(false);
        pnldesc.setOpaque(false);
        pnllbldesc.setOpaque(false);
        pnllbletat.setOpaque(false);
        radioPanel.setOpaque(false);
        upButton.setOpaque(false);
        downButton.setOpaque(false);
        pnlbtnsubmit.setOpaque(false);
        pnltitre.setOpaque(false);
        pnllbltitre.setOpaque(false);
        cbinter.addItem("");
        for (final Interlocuteur in : inter)
        {
            cbinter.addItem(in.getInterid()+". "+in.getInterprenom() + " " + in.getInternom());     
        }
        pnllblinter.setPreferredSize(new Dimension(600, 30));
        pnlcbinter.setPreferredSize(new Dimension(600, 30));
        pnllbldte.setPreferredSize(new Dimension(600, 30));
        pnldte.setPreferredSize(new Dimension(600, 30));
        pnllbldesc.setPreferredSize(new Dimension(600,30));
        pnldesc.setPreferredSize(new Dimension(600,130));
        pnllbletat.setPreferredSize(new Dimension(600,25));
        radioPanel.setPreferredSize(new Dimension(600, 40));
        pnlbtnsubmit.setPreferredSize(new Dimension(600, 60));
        pnltitre.setPreferredSize(new Dimension(600, 40));
        pnllbltitre.setPreferredSize(new Dimension(600, 30));
        pnlbtnsubmit.add(btnsubmit);
        pnllbltitre.add(lbltitre);
        pnltitre.add(txttitre);
        pnllbletat.add(lbletat);
        pnllblinter.add(lblinter);
        pnlcbinter.add(cbinter);
        pnllbldte.add(lbldate);
        pnldte.add(date);
        pnllbldesc.add(lbldesc);
        pnldesc.add(txtdesc);
        pnlcenter.add(pnllbltitre);
        pnlcenter.add(pnltitre);
        pnlcenter.add(pnllblinter);
        pnlcenter.add(pnlcbinter);
        pnlcenter.add(pnllbldte);
        pnlcenter.add(pnldte);
        pnlcenter.add(pnllbldesc);
        pnlcenter.add(pnldesc);
        pnlcenter.add(pnllbletat);
        pnlcenter.add(radioPanel);
        pnlcenter.add(pnlbtnsubmit);
        TitledBorder title;
        title = BorderFactory.createTitledBorder("<html><h2>Ajout d'un rapport</h2></html>");
        pnlcenter.setBorder(title);
        content.add(pnltop);
        content.add(pnlcenter);
        lblretour.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent me) {
                    Fenetre fen = Fenetre.getInstance();
                    fen.RenewClientdDetail(cli.getCliid());
                }
            });
        btnsubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                CurrentDatas cd = CurrentDatas.getInstance();
                String titre = txttitre.getText();
                String desc = txtdesc.getText();
                if(txttitre.getText().equals("") || cbinter.getSelectedIndex() == 0|| txtdesc.getText().equals("")||date.getDate() == null)
                {
                    JOptionPane pn = new JOptionPane();
                    pn.showMessageDialog(null, "Tous les champs sont obligatoires");
                }
                else
                {
                    String interlo = (String) cbinter.getSelectedItem();
                    String str[] = interlo.split(". ");
                    Boolean pos = upButton.isSelected();
                    Date dte = date.getDate();
                    Uniqid uniqid = new Uniqid(cd.getUser().getId());
                    Enquete enq = new Enquete();
                    enq.setUtiid(cd.getUser().getId());
                    enq.setEnquniqid(uniqid.getUniqid());
                    enq.setEnqsuppr(false);
                    enq.setEnqint(titre);
                    enq.setInterid((Integer.parseInt(str[0])));
                    enq.setEnqdte(dte);
                    enq.setEnqdesc(desc);
                    enq.setEnqpos(pos);
                    ReportingInstance rapport = ReportingInstance.getInstance();
                    rapport.setReporting(enq);
                    Boolean retour = rapport.addbdd();
                    if(retour == true)
                    {
                        JOptionPane jop = new JOptionPane();
                        jop.showMessageDialog(null, "Rapport enregistré avec succès");
                        Fenetre fen = Fenetre.getInstance();
                        fen.RenewClientdDetail(cli.getCliid());
                    }
                    else
                    {
                        JOptionPane jop = new JOptionPane();
                        jop.showMessageDialog(null, "Une erreur est survenue lors de l'ajout du rapport. Veuillez réessayer.");
                    }
                }   
            }
        });
        this.panel.add(content);
    }
}
