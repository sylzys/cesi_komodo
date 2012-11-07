/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import classes.BackgroundPanel;
import classes.LinkLabelData;
import controllers.getDemandeInfos;
import controllers.getInterlocuteurInfos;
import controllers.getSteInfos;
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
import models.Interlocuteur;
import org.dom4j.CDATA;
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
    private InterlocuteurInstance interInstance;
    private List<Interlocuteur> inter;
    private Client cli = null;

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
//        //JComboBox
//        JComboBox cb_demande = new JComboBox(),
//                cb_commande = new JComboBox();



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
        // top.setBackground(Color.J);
        top.setLayout(new BorderLayout());

        //cliInfos.setBackground(Color.J);
        cliDetail.setPreferredSize(new Dimension(780, 150));

        //ajout des Listeners sur les boutons
        addContact.addActionListener(new addContactListener());
        validateCmd.addActionListener(new validateCmdListener());
        validateDmd.addActionListener(new validateDmdListener());
        newDemand.addActionListener(new newDmdListener());
        //adresse, contacts raison sociale etc

        //cliDetail.setBackground(Color.J);
        cliDetail.setLayout(new BorderLayout());
        cliInfos.setLayout(new FlowLayout(0, 50, 0));

        //adresse
        JPanel cliAddr = new JPanel();
        //cliAddr.setBackground(Color.J);
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
        //cliContact.setBackground(Color.J);
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
        //  cliRS.setBackground(Color.J);
        JLabel RS = new JLabel("<html>Dirigeant: " + cli.getClidg() + "<br>Activité :"
                + cli.getCliactivite() + "<br>SIRET: "
                + cli.getClisiret() + "<br>CA: "
                + Integer.toString(cli.getClica()) + "</html>");
        cliRS.add(RS);
        cliInfos.add(cliRS);

        //Boutons contact

        //cliButtons.setBackground(Color.J);
        cliButtons.setLayout(new FlowLayout());
        cliButtons.add(new JLabel("Contacts : "));
        interInstance = InterlocuteurInstance.getInstance();
        Hashtable hh = new Hashtable();
        hh.put("cliid", cli_id);
        hh.put("intersuppr", false);
        inter = interInstance.GetInterlocuteurs("where cliid = :cliid and intersuppr = :intersuppr", hh);
        for (final Interlocuteur in : inter)
        {
            System.out.println("INTER : " + in.getInternom() + in.getInterprenom());
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
                    //JOptionPane.showMessageDialog(null, userChoice, "ValidateCmd", JOptionPane.INFORMATION_MESSAGE);
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
//        LinkLabelData LblCmd = new LinkLabelData("Commande n. 10", 10);
//        LblCmd.setIcon(new ImageIcon("ressources/images/eye.gif"));


        cliButtons.add(addContact);


        cliDetail.setBorder(BorderFactory.createTitledBorder("Infos Société"));

        //Panneau des combos box
        // comboPanel.setBackground(Color.J);
        comboPanel.setLayout(new BorderLayout());
        comboPanel.add(newDemand, BorderLayout.NORTH);

        //ComboBox demandes
        JPanel comboDmd_panel = new JPanel();
        // comboDmd_panel.setBackground(Color.J);
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

        //ajout des panels au JPanel principal
        top.add(comboPanel, BorderLayout.EAST);
        LinkLabelData LblSte = new LinkLabelData("(Modifier)", cli.getCliid());
        LblSte.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("CLICKED");
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
                System.out.println("CLICKED");
                goSupprSte(evt);
            }

            private void goSupprSte(MouseEvent evt) {
//                LinkLabelData lbl_tmp = (LinkLabelData) evt.getComponent();
//                modif_ste(lbl_tmp.getId());
                //oui = 0
                //non = 1
                int userChoice = JOptionPane.showConfirmDialog(null, "upprimer le client ?", "Supprimer le client ?", JOptionPane.YES_NO_OPTION);
                //JOptionPane.showMessageDialog(null, userChoice, "ValidateCmd", JOptionPane.INFORMATION_MESSAGE);
                if (userChoice == 0)
                {
                    deleteClient();
                }
            }
        });

        JPanel name_ste = new JPanel();
        name_ste.setPreferredSize(new Dimension(100, 20));
        name_ste.setLayout(new FlowLayout((FlowLayout.LEFT)));
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
        //   suivi_satisfaction.setBackground(Color.J);
        suivi_satisfaction.setBorder(BorderFactory.createTitledBorder("Suivi Satisfaction"));
        suivi_satisfaction.setPreferredSize(new Dimension(120, 50));
        bottom.add(suivi_satisfaction);
        bottom.add(Box.createVerticalStrut(10));

        //alertes
        JPanel alertes = new JPanel();
        // alertes.setBackground(Color.J);
        alertes.setBorder(BorderFactory.createTitledBorder("Alertes"));
        alertes.setPreferredSize(new Dimension(120, 50));
        JButton btn_view_alertes = new JButton("Voir les alertes");
        btn_view_alertes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Fenetre.getInstance().RenewAlert(cli_id);
            }
        });
        alertes.add(btn_view_alertes);
        bottom.add(alertes);
        bottom.add(Box.createVerticalStrut(10));

        //Reporting
        JPanel reporting = new JPanel();
        // reporting.setBackground(Color.J);
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

            NouvelleDemande addDmd = new NouvelleDemande(null, "Ajouter une demande", true, cli_id);

            getDemandeInfos interInfos = addDmd.showZDialog(cli_id);


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

    public void deleteClient() {
        ClientInstance ci = ClientInstance.getInstance();
        cli.setClisuppr(true);
        ci.updaterBaseDeDonnees(cli);
        Fenetre fen = Fenetre.getInstance();
        CurrentDatas cd = CurrentDatas.getInstance();
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
        // JOptionPane jop = new JOptionPane();
        System.out.println(interInfos.toString());

        if ("N/A" != interInfos.toString())
        {
            CurrentDatas cd = CurrentDatas.getInstance();
            String[] infos = interInfos.toString().split("#-#");
            System.out.println("add inter with cli_id : " + cli_id);
            Interlocuteur i = new Interlocuteur();
            i.setCliid(cli_id);
            i.setInternom(infos[0]);
            i.setInterprenom(infos[1]);
            i.setIntertel(infos[2]);
            i.setIntermail(infos[3]);
            i.setUtiid(cd.getUser().getId());
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
            new ClientDetail(cli_id);
        }
    }

    private void modif_ste(int id) {
        ModifSteDialog modif = new ModifSteDialog(null, "Modification client", true, id);
        System.out.println("MODIFYING CLIENT ID " + id);
        getSteInfos interInfos = modif.showZDialog(id);
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
}