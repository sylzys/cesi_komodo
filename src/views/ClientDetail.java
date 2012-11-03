/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import classes.BackgroundPanel;
import classes.LinkLabelData;
import controllers.UserActif;
import controllers.getInterlocuteurInfos;
import instances.ClientInstance;
import instances.DemandeInstance;
import instances.DetailCdeInstance;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import models.Client;
import models.DetailCommande;
import models.Demande;
import instances.HibernateConnection;
import instances.InterlocuteurInstance;
import java.awt.Button;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import models.CurrentDatas;
import models.DetailDmd;
import models.Interlocuteur;
import org.hibernate.Query;
import classes.WhitePanel;

/**
 *
 * @author sylv
 */
public class ClientDetail extends KContainer {

    JLabel title = new JLabel("PANNEAU CLIENT DETAIL");
    int cli_id;
    private Fenetre fen = Fenetre.getInstance();
    private List<DetailCommande> detail;
    private List<Demande> demande;
    private JComboBox cb_demande = new JComboBox(),
            cb_commande = new JComboBox();
    private InterlocuteurInstance interInstance;
    private List<Interlocuteur> inter;

    public ClientDetail(int id) {
        super();
        cli_id = id;
        System.out.println("IN client detail");
//        CurrentDatas cur = CurrentDatas.getInstance();
//        cur.setSoc_id(cli_id);
        initPanel();

    }

    //@Override
    protected void initPanel() {
        BackgroundPanel content = new BackgroundPanel();

        //LightPanelS
        WhitePanel cliInfos = new WhitePanel(),
                top = new WhitePanel(),
                bottom = new WhitePanel(),
                cliDetail = new WhitePanel(),
                cliButtons = new WhitePanel(),
                comboPanel = new WhitePanel();

        //JBUTTONS
        JButton addContact = new JButton("Ajouter"),
                contact1 = new JButton("+"),
                contact2 = new JButton("+"),
                contact3 = new JButton("+"),
                newDemand = new JButton("Creer une demande"),
                validateDmd = new JButton("Valider D"),
                validateCmd = new JButton("Valider C");
//        //JComboBox
//        JComboBox cb_demande = new JComboBox(),
//                cb_commande = new JComboBox();


        Client cli = null;
        content.setLayout(new BorderLayout());
        content.setPreferredSize(new Dimension(1000, 768));
        this.panel.add(content);

        //DB CONNECTION
        HibernateConnection connection = HibernateConnection.getInstance();
        try
        {
            Query query = connection.getSession().createQuery("from Client where cliid = :cliid");
            query.setParameter("cliid", cli_id);
            //  query.setParameter("utiid", this.user.getId());
            cli = (Client) query.uniqueResult();

            System.out.println("CLIENT USED : " + cli);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }//END DB CONNECTION
//            ClientInstance ci = ClientInstance.getInstance();
//            Hashtable h = new Hashtable();
//            h.put("cliid", cli_id);
//            //récupère la liste.
//            cli.GetClients("where cliid = :cliid", h);
           // top.setBackground(Color.white);
            top.setLayout(new BorderLayout());

            //cliInfos.setBackground(Color.white);
            cliDetail.setPreferredSize(new Dimension(780, 150));

            //ajout des Listeners sur les boutons
            addContact.addActionListener(new addContactListener());
            validateCmd.addActionListener(new validateCmdListener());
            validateDmd.addActionListener(new validateDmdListener());
            newDemand.addActionListener(new newDmdListener());
            //adresse, contacts raison sociale etc

            //cliDetail.setBackground(Color.white);
            cliDetail.setLayout(new BorderLayout());
            cliInfos.setLayout(new FlowLayout(0, 50, 0));

            //adresse
            WhitePanel cliAddr = new WhitePanel();
            //cliAddr.setBackground(Color.white);
            JLabel addr = new JLabel("<html>" + cli.getCliadresse() + "<br />"
                    + cli.getClicp() + "<br>"
                    + cli.getCliville() + "<br>"
                    + cli.getClipays() + "</html>");
            cliAddr.setBorder(new EmptyBorder(0, 0, 0, 20));
            cliAddr.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
            cliAddr.add(addr);
            cliInfos.add(cliAddr);

            //contacts
            WhitePanel cliContact = new WhitePanel();
            //cliContact.setBackground(Color.white);
            JLabel contact = new JLabel("<html>Tel: " + cli.getClitel() + "<br>Fax: "
                    + cli.getClifax() + "<br>Mail: "
                    + cli.getClimail() + "<br>Web Site: "
                    + cli.getClisite() + "</html>");
            cliContact.setBorder(new EmptyBorder(0, 0, 0, 20));
            cliContact.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
            cliContact.add(contact);
            cliInfos.add(cliContact);

            //raison sociale
            WhitePanel cliRS = new WhitePanel();
          //  cliRS.setBackground(Color.white);
            JLabel RS = new JLabel("<html>Dirigeant: " + cli.getClidg() + "<br>Activité :"
                    + cli.getCliactivite() + "<br>SIRET: "
                    + cli.getClisiret() + "<br>CA: "
                    + Integer.toString(cli.getClica()) + "</html>");
            cliRS.add(RS);
            cliInfos.add(cliRS);

            //Boutons contact

            //cliButtons.setBackground(Color.white);
            cliButtons.setLayout(new FlowLayout());
            cliButtons.add(new JLabel("Contacts : "));
            interInstance = InterlocuteurInstance.getInstance();
            Hashtable hh = new Hashtable();
            hh.put("cliid", cli_id);
            inter = interInstance.GetInterlocuteurs("where cliid = :cliid", hh);
            for (Interlocuteur in : inter)
            {
                System.out.println("INTER : " + in.getInternom() + in.getInterprenom());
                LinkLabelData LblCmd = new LinkLabelData(in.getInterprenom() + " " + in.getInternom(), in.getInterid());
                LblCmd.setIcon(new ImageIcon("ressources/images/eye.gif"));

                LblCmd.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        getInterId(evt);
                    }

                    private void getInterId(MouseEvent evt) {
                        LinkLabelData lbl_tmp = (LinkLabelData) evt.getComponent();
                        showInterlocuteur(lbl_tmp.getId());
                    }
                });
                cliButtons.add(LblCmd);
                cliButtons.add(Box.createHorizontalStrut(5));
            }
//        LinkLabelData LblCmd = new LinkLabelData("Commande n. 10", 10);
//        LblCmd.setIcon(new ImageIcon("ressources/images/eye.gif"));


            cliButtons.add(addContact);


            cliDetail.setBorder(BorderFactory.createTitledBorder("Infos Société"));

            //Panneau des combos box
           // comboPanel.setBackground(Color.white);
            comboPanel.setLayout(new BorderLayout());
            comboPanel.add(newDemand, BorderLayout.NORTH);

            //ComboBox demandes
            WhitePanel comboDmd_panel = new WhitePanel();
           // comboDmd_panel.setBackground(Color.white);
            comboDmd_panel.setLayout(new FlowLayout());

            //get detail demande
            DemandeInstance dd = DemandeInstance.getInstance();
            Hashtable hhh = new Hashtable();
            hhh.put("cliid", cli_id);
            demande = dd.GetDemandes("where cliid = :cliid", hhh);

            cb_demande.addItem("Demandes");
            for (Demande dddd : demande)
            {
                System.out.println("Etat : " + dddd.getDemandeetat());
                cb_demande.addItem((dddd.getDemandeid()));
            }

            comboDmd_panel.add(cb_demande);
            comboDmd_panel.add(validateDmd);

            //Combobox commandes
            WhitePanel comboCmd_panel = new WhitePanel();
           // comboCmd_panel.setBackground(Color.white);
            comboCmd_panel.setLayout(new FlowLayout());

            //get detail cde
            DetailCdeInstance dc = DetailCdeInstance.getInstance();
            Hashtable hhhh = new Hashtable();
            hhhh.put("cliid", cli_id);
            detail = dc.GetDetailcde("where cliid = :cliid", hhhh);

            cb_commande.addItem("Commandes");
            for (DetailCommande dcc : detail)
            {
                System.out.println("Name : " + dcc.getInternom());
                System.out.println("Prénom : " + dcc.getInteprenom());
                System.out.println("Prix : " + dcc.getComprix());
                System.out.println("Etat : " + dcc.getCometat());
                System.out.println("date ?  : " + dcc.getComdate());
                cb_commande.addItem(dcc.getComid());
            }

            comboCmd_panel.add(cb_commande);
            comboCmd_panel.add(validateCmd);

            //ajout des combobox a droite
            comboPanel.add(comboDmd_panel, BorderLayout.CENTER);
            comboPanel.add(comboCmd_panel, BorderLayout.SOUTH);

            //ajout des panels au WhitePanel principal
            top.add(comboPanel, BorderLayout.EAST);
            cliDetail.add(new JLabel("<html><b>" + cli.getClinom() + "</b><br/></html>"), BorderLayout.NORTH);
            cliDetail.add(cliInfos, BorderLayout.CENTER);
            cliDetail.add(cliButtons, BorderLayout.SOUTH);
            top.add(cliDetail, BorderLayout.WEST);
            content.add(top, BorderLayout.NORTH);

            bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));

            //LightPanel pour accordeons

            //suivi satisfaction
            WhitePanel suivi_satisfaction = new WhitePanel();
         //   suivi_satisfaction.setBackground(Color.white);
            suivi_satisfaction.setBorder(BorderFactory.createTitledBorder("Suivi Satisfaction"));
            suivi_satisfaction.setPreferredSize(new Dimension(120, 50));
            bottom.add(suivi_satisfaction);
            bottom.add(Box.createVerticalStrut(10));

            //alertes
            WhitePanel alertes = new WhitePanel();
           // alertes.setBackground(Color.white);
            alertes.setBorder(BorderFactory.createTitledBorder("Alertes"));
            alertes.setPreferredSize(new Dimension(120, 50));
            bottom.add(alertes);
            bottom.add(Box.createVerticalStrut(10));

            //Reporting
            WhitePanel reporting = new WhitePanel();
           // reporting.setBackground(Color.white);
            reporting.setBorder(BorderFactory.createTitledBorder("Reporting"));
            reporting.setPreferredSize(new Dimension(120, 50));
            bottom.add(reporting);
            bottom.add(Box.createVerticalStrut(10));

            content.add(bottom, BorderLayout.CENTER);


            //refresh de la fenetre
          //JOptionPane.showMessageDialog(null, "Affichage commande séléctionnée\n" + cb_commande.getSelectedItem().toString(), "ValidateCmd", JOptionPane.INFORMATION_MESSAGE);

            fen.conteneur.setVisible(false);
            fen.RenewContener(this.getPanel());
            fen.conteneur.setVisible(true);
       // }
    }

    private class addContactListener implements ActionListener {

        public addContactListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            addInterlocuteur(cli_id);

        }
    }

    private class validateCmdListener implements ActionListener {

        public validateCmdListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //Fenetre fen = Fenetre.getInstance();
            //JOptionPane jop4 = new JOptionPane();
            //jop4.showMessageDialog(null, "Affichage commande séléctionnée\n" + cb_commande.getSelectedItem().toString(), "ValidateCmd", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("type :" + (cb_commande.getSelectedItem()).getClass());
            // CommandeDetail cd = new CommandeDetail(user, cli_id, (Integer)(cb_commande.getSelectedItem()));
            CommandeDetail cd = new CommandeDetail((Integer) (cb_commande.getSelectedItem()));
            fen.RenewContener(cd.getPanel());
        }
    }

    private class validateDmdListener implements ActionListener {

        public validateDmdListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {

//            JOptionPane jop4 = new JOptionPane();
//            jop4.showMessageDialog(null, "Affichage demmande séléctionnée", "ValidateDmd", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("type :" + (cb_demande.getSelectedItem()).getClass());
            DemandeDetail demande = new DemandeDetail((Integer) cb_demande.getSelectedItem());
            fen.RenewContener(demande.getPanel());
        }
    }

    private class newDmdListener implements ActionListener {

        public newDmdListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            NouvelleDemande nd = new NouvelleDemande(user);
            fen.RenewContener(nd.getPanel());
        }
    }

    private void showInterlocuteur(int id) {
        InterlocuteurDialog interd = new InterlocuteurDialog(null, "Information interlocuteur", true, id);
        System.out.println("SHOWING INTER ID " + id);
        getInterlocuteurInfos interInfos = interd.showZDialog(id);
        //JOptionPane jop = new JOptionPane();

        if (HibernateConnection.online == false)
        {
            HibernateConnection.newConnect(false);
        }
        else
        {
            HibernateConnection.newConnect(true);
        }
        ClientDetail cd = new ClientDetail(cli_id);
    }

    private void addInterlocuteur(int cli_id) {

        addInterlocuteurDialog addInter = new addInterlocuteurDialog(null, "Ajouter un interlocuteur", true, cli_id);

        getInterlocuteurInfos interInfos = addInter.showZDialog(cli_id);
        // JOptionPane jop = new JOptionPane();
        System.out.println(interInfos.toString());

        if ("N/A" != interInfos.toString())
        {
            String[] infos = interInfos.toString().split("#-#");
            System.out.println("add inter with cli_id : " + cli_id);
            Interlocuteur i = new Interlocuteur();
            i.setCliid(cli_id);
            i.setInternom(infos[0]);
            i.setInterprenom(infos[1]);
            i.setIntertel(infos[2]);
            i.setIntermail(infos[3]);
            InterlocuteurInstance is = InterlocuteurInstance.getInstance();
            is.insererEnBaseDeDonnées(i);
            if (HibernateConnection.online == false)
            {
                HibernateConnection.newConnect(false);
            }
            else
            {
                HibernateConnection.newConnect(true);
            }
            ClientDetail cd = new ClientDetail(cli_id);
        }
    }
}
