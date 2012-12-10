/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import classes.BackgroundPanel;
import classes.LinkLabelData;
import classes.Uniqid;
import controllers.Synchro;
import controllers.TableDispatcher;
import controllers.getDemandeInfos;
import controllers.getInterlocuteurInfos;
import controllers.getSteInfos;
import instances.AlerteInstance;
import instances.ClientInstance;
import instances.DemandeInstance;
import instances.DetailCdeInstance;
import instances.HibernateConnection;
import instances.InterlocuteurInstance;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import models.Client;
import models.CurrentDatas;
import models.Demande;
import models.DetailCommande;
import models.GetAlerte;
import models.Interlocuteur;
import models.ModelesTables;
import org.hibernate.Query;

/**
 *
 * @author sylv
 */
public class ClientDetail extends KContainer {

    //JLabel title = new JLabel("PANNEAU CLIENT DETAIL");
    int cli_id;
    private Fenetre fen = Fenetre.getInstance();
    private List<DetailCommande> detail;
    private List<Demande> demande;
    private JComboBox cb_demande = new JComboBox(),
            cb_commande = new JComboBox();
    private List<Interlocuteur> inter;
    private Client cli = null;
    private InterlocuteurInstance interInstance = InterlocuteurInstance.getInstance();
    private DemandeInstance dd = DemandeInstance.getInstance();
    private ClientInstance ci = ClientInstance.getInstance();
    private AlerteInstance ai = AlerteInstance.getInstance();
    CurrentDatas cd = CurrentDatas.getInstance();
    public ClientDetail(int id) {
        super();
        cli_id = id;
        initPanel();

    }

    //@Override
    protected void initPanel() {
        Synchro sync = new Synchro();
        BackgroundPanel content = new BackgroundPanel(),
                top = new BackgroundPanel(),
                bottom = new BackgroundPanel();

        //JPanelS
        JPanel cliInfos = new JPanel(),
                cliDetail = new JPanel(),
                cliButtons = new JPanel(),
                comboPanel = new JPanel();

        //JBUTTONS
        JButton addContact = new JButton("Ajouter"),
                contact1 = new JButton("+"),
                contact2 = new JButton("+"),
                contact3 = new JButton("+"),
                newDemand = new JButton("Creer une demande"),
                validateDmd = new JButton("Rechercher"),
                validateCmd = new JButton("Rechercher");

        content.setLayout(new BorderLayout());
        content.setPreferredSize(new Dimension(1000, 712));
        this.panel.add(content);

        //DB CONNECTION
        HibernateConnection connection = HibernateConnection.getInstance();
        try
        {
            Query query = connection.getSession().createQuery("from Client where cliid = :cliid");
            query.setParameter("cliid", cli_id);
            cli = (Client) query.uniqueResult();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }//END DB CONNECTION

        top.setLayout(new BorderLayout());

        cliDetail.setPreferredSize(new Dimension(780, 165));

        //ajout des Listeners sur les boutons
        addContact.addActionListener(new addContactListener());
        validateCmd.addActionListener(new validateCmdListener());
        validateDmd.addActionListener(new validateDmdListener());
        newDemand.addActionListener(new newDmdListener());
        //adresse, contacts raison sociale etc


        cliDetail.setLayout(new BorderLayout());
        cliInfos.setLayout(new FlowLayout(0, 50, 0));

        //adresse
        JPanel cliAddr = new JPanel();
        JLabel addr = new JLabel("<html>" + cli.getCliadresse() + "<br />"
                + cli.getClicp() + "<br>"
                + cli.getCliville() + "<br>"
                + cli.getClipays() + "</html>");
        cliAddr.setBorder(new EmptyBorder(0, 0, 0, 20));
        cliAddr.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
        cliAddr.add(addr);
        cliInfos.add(cliAddr);

        //contacts
        JPanel cliContact = new JPanel();
        JLabel contact = new JLabel("<html>Tel: " + cli.getClitel() + "<br>Fax: "
                + cli.getClifax() + "<br>Mail: "
                + cli.getClimail() + "<br>Web Site: "
                + cli.getClisite() + "</html>");
        cliContact.setBorder(new EmptyBorder(0, 0, 0, 20));
        cliContact.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
        cliContact.add(contact);
        cliInfos.add(cliContact);

        //raison sociale
        JPanel cliRS = new JPanel();
        JLabel RS = new JLabel("<html>Dirigeant: " + cli.getClidg() + "<br>Activité :"
                + cli.getCliactivite() + "<br>SIRET: "
                + cli.getClisiret() + "<br>CA: "
                + Integer.toString(cli.getClica()) + "</html>");
        cliRS.add(RS);
        cliInfos.add(cliRS);

        //Boutons contact

        cliButtons.setLayout(new FlowLayout());
        cliButtons.add(new JLabel("Contacts : "));

        Hashtable hh = new Hashtable();
        hh.put("cliid", cli_id);
        hh.put("intersuppr", false);
        inter = interInstance.GetInterlocuteurs("where cliid = :cliid and intersuppr = :intersuppr", hh);
        boolean chkinter = false;
        for (final Interlocuteur in : inter)
        {
            if(HibernateConnection.online == true)
            {
                if(chkinter != true)
                {
                    chkinter = sync.chkobject(in.getInteruniqid(), "models.Interlocuteur");
                }
            }
            LinkLabelData LblInter = new LinkLabelData(in.getInterprenom() + " " + in.getInternom(), in.getInterid());
            LblInter.setIcon(new ImageIcon("ressources/images/eye.gif"));

            LblInter.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    getInterId(evt);
                }

                private void getInterId(MouseEvent evt) {
                    LinkLabelData lbl_tmp = (LinkLabelData) evt.getComponent();
                    showInterlocuteur(lbl_tmp.getId());
                }
            });

            LinkLabelData LblSupprInter = new LinkLabelData("(X)  ", in.getInterid());
            LblSupprInter.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    supprInter(evt);
                }

                private void supprInter(MouseEvent evt) {
                    int userChoice = JOptionPane.showConfirmDialog(null, "Supprimer l'interlocuteur ?", "Supprimer l'interlocuteur ?", JOptionPane.YES_NO_OPTION);
                    if (userChoice == 0)
                    {
                        LinkLabelData lbl_tmp = (LinkLabelData) evt.getComponent();
                        deleteInterlocuteur(lbl_tmp.getId(), in);
                    }

                }
            });
            cliButtons.add(LblInter);
            cliButtons.add(new JLabel(" "));
            cliButtons.add(LblSupprInter);
            cliButtons.add(Box.createHorizontalStrut(5));
        }

        cliButtons.add(addContact);


        cliDetail.setBorder(BorderFactory.createTitledBorder("Infos Société"));

        //Panneau des combos box
        JButton retour = new JButton("Retour");
        retour.addActionListener(new retourListener());
        JPanel r = new JPanel();
        r.setLayout(new FlowLayout());
        //comboPanel.setLayout(new BorderLayout());
        comboPanel.setLayout(new BoxLayout(comboPanel, BoxLayout.Y_AXIS));
        r.add(newDemand);
        r.add(retour);
        comboPanel.add(r);
        //ComboBox demandes
        JPanel comboDmd_panel = new JPanel();
        comboDmd_panel.setLayout(new FlowLayout());

        //get detail demande

        Hashtable hhh = new Hashtable();
        hhh.put("cliid", cli_id);
        demande = dd.GetDemandes("where cliid = :cliid", hhh);

        cb_demande.addItem("Demandes");
        for (Demande dddd : demande)
        {
            cb_demande.addItem((dddd.getDemandeid()));
        }

        comboDmd_panel.add(cb_demande);
        comboDmd_panel.add(validateDmd);

        //Combobox commandes
        JPanel comboCmd_panel = new JPanel();
        // comboCmd_panel.setBackground(Color.J);
        comboCmd_panel.setLayout(new FlowLayout());

        //get detail cde
        DetailCdeInstance dc = DetailCdeInstance.getInstance();
        Hashtable hhhh = new Hashtable();
        hhhh.put("cliid", cli_id);
        detail = dc.GetDetailcde("cliid = :cliid", hhhh);

        cb_commande.addItem("Commandes");
        for (DetailCommande dcc : detail)
        {
            cb_commande.addItem(dcc.getComid());
        }

        comboCmd_panel.add(cb_commande);
        comboCmd_panel.add(validateCmd);

        //ajout des combobox a droite
        comboPanel.add(comboDmd_panel);
        comboPanel.add(comboCmd_panel);

        //ajout des panels au JPanel principal
        top.add(comboPanel, BorderLayout.EAST);
        LinkLabelData LblSte = new LinkLabelData("(Modifier)", cli.getCliid());
        LblSte.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                goModifSte(evt);
            }

            private void goModifSte(MouseEvent evt) {
                LinkLabelData lbl_tmp = (LinkLabelData) evt.getComponent();
                modif_ste(lbl_tmp.getId());
            }
        });

        LinkLabelData LblSupprSte = new LinkLabelData("(Supprimer)", cli.getCliid());
        LblSupprSte.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                goSupprSte(evt);
            }

            private void goSupprSte(MouseEvent evt) {

                //oui = 0
                //non = 1
                int userChoice = JOptionPane.showConfirmDialog(null, "Supprimer le client ?", "Supprimer le client ?", JOptionPane.YES_NO_OPTION);
                if (userChoice == 0)
                {
                    deleteClient();
                }
            }
        });
        JPanel name_ste = new JPanel();
        name_ste.setPreferredSize(new Dimension(100, 20));
        name_ste.setLayout(new FlowLayout((FlowLayout.LEFT)));
        if(HibernateConnection.online == true)
        {
            HibernateConnection.openConnectionBisOff();
            boolean chkcli = sync.chkobject(cli.getCliuniqid(), "models.Client");
            if(chkcli == true || chkinter == true)
            {
                name_ste.setPreferredSize(new Dimension(100, 40));
                JLabel lblsync = new JLabel();
                lblsync.setPreferredSize(new Dimension(780, 15));
                lblsync.setText("<html><p color=red>Attention: ce client est en attente de synchronisation, toutes nouvelles modifications pourraient être perdues.</p></html>");
                name_ste.add(lblsync);
            }
            HibernateConnection.closeConnectionBis();
        }
        name_ste.add(new JLabel("<html><b>" + cli.getClinom() + "</b> - </html>"));
        name_ste.add(LblSte);
        name_ste.add(new JLabel(" - "));
        name_ste.add(LblSupprSte);
        cliDetail.add(name_ste, BorderLayout.PAGE_START);
        cliDetail.add(cliInfos, BorderLayout.CENTER);
        cliDetail.add(cliButtons, BorderLayout.SOUTH);
        top.add(cliDetail, BorderLayout.WEST);
        content.add(top, BorderLayout.NORTH);

        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));

        //JPanel pour accordeons

        //suivi satisfaction
        JPanel suivi_satisfaction = new JPanel();
        suivi_satisfaction.setBorder(BorderFactory.createTitledBorder("Suivi Satisfaction"));
        suivi_satisfaction.setPreferredSize(new Dimension(120, 15));
        bottom.add(suivi_satisfaction);
        //bottom.add(Box.createVerticalStrut(10));

        //alertes
        JPanel alertes = new JPanel();
        alertes.setBorder(BorderFactory.createTitledBorder("Alertes"));
        alertes.setPreferredSize(new Dimension(120, 45));
        // lisye alertes
        TableDispatcher cp = new TableDispatcher();
        alertes.add(cp.showtable(ModelesTables.ALERTE, this.cli_id), BorderLayout.NORTH);
        cp.setPreferredSize(new Dimension(100, 20));
        // bouton voir tout
        JButton btn_view_alertes = new JButton("Voir toutes les alertes");
        btn_view_alertes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Fenetre.getInstance().RenewAlert(cli_id);
            }
        });
        alertes.add(btn_view_alertes, BorderLayout.SOUTH);
        bottom.add(alertes);
        //bottom.add(Box.createVerticalStrut(10));

        //Reporting
        JPanel reporting = new JPanel();
        // reporting.setBackground(Color.J);
        reporting.setBorder(BorderFactory.createTitledBorder("Rapports"));
        reporting.setPreferredSize(new Dimension(120, 80));
        ReportView rv = new ReportView();
        JPanel pnlreport = rv.initPanel(cli);
        reporting.add(pnlreport, BorderLayout.CENTER);
        bottom.add(reporting);
        bottom.add(Box.createVerticalStrut(10));

        content.add(bottom, BorderLayout.CENTER);


        //refresh de la fenetre

        fen.conteneur.setVisible(false);
        fen.RenewContener(this.getPanel());
        fen.conteneur.setVisible(true);

    }

    private class retourListener implements ActionListener {

        public retourListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Affichage af = new Affichage(cd.getUser());
            fen.RenewContener(af.getPanel());
        }
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
            CommandeDetail cd = new CommandeDetail((Integer) (cb_commande.getSelectedItem()));
            fen.RenewContener(cd.getPanel());
        }
    }

    private class validateDmdListener implements ActionListener {

        public validateDmdListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            DemandeDetail demande = new DemandeDetail((Integer) cb_demande.getSelectedItem());
            fen.RenewContener(demande.getPanel());
        }
    }

    private class newDmdListener implements ActionListener {

        public newDmdListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            NouvelleDemande addDmd = new NouvelleDemande(null, "Ajouter une demande", true, cli_id);

            getDemandeInfos interInfos = addDmd.showZDialog(cli_id);


        }
    }

    private void showInterlocuteur(int id) {
        InterlocuteurDialog interd = new InterlocuteurDialog(null, "Information interlocuteur", true, id);
        getInterlocuteurInfos interInfos = interd.showZDialog(id);

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

    public void deleteClient() {

        cli.setClisuppr(true);
        ci.updaterBaseDeDonnees(cli);
        Affichage af = new Affichage(cd.getUser());
        fen.RenewContener(af.getPanel());
    }

    public void deleteInterlocuteur(int inter_id, Interlocuteur in) {
        in.setIntersuppr(true);
        interInstance.updaterBaseDeDonnees(in);
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

    public void addInterlocuteur(int cli_id) {
        //TODO : inserer l'utiid
        addInterlocuteurDialog addInter = new addInterlocuteurDialog(null, "Ajouter un interlocuteur", true, cli_id);

        getInterlocuteurInfos interInfos = addInter.showZDialog(cli_id);
        System.out.println((interInfos.toString()).substring(0, 5));
        if (!"ERROR".equals(interInfos.toString().substring(0, 5)))
        {
            CurrentDatas cd = CurrentDatas.getInstance();
            String[] infos = interInfos.toString().split("#-#");
            Interlocuteur i = new Interlocuteur();
            i.setCliid(cli_id);
            i.setInternom(infos[0]);
            i.setInterprenom(infos[1]);
            i.setIntertel(infos[2]);
            i.setIntermail(infos[3]);
            i.setUtiid(cd.getUser().getId());
            Uniqid uniqid = new Uniqid(cd.getUser().getId());
            i.setInteruniqid(uniqid.getUniqid());
            InterlocuteurInstance is = InterlocuteurInstance.getInstance();
            is.insererEnBaseDeDonnees(i);
            if (HibernateConnection.online == false)
            {
                HibernateConnection.newConnect(false);
            }
            else
            {
                HibernateConnection.newConnect(true);
            }
            ClientDetail cdet = new ClientDetail(cli_id);
        }
    }

    private void modif_ste(int id) {
        ModifSteDialog modif = new ModifSteDialog(null, "Modification client", true, id);
        getSteInfos interInfos = modif.showZDialog(id);

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