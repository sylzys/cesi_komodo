package views;

import controllers.Replication;
import controllers.Synchro;
import controllers.UserActif;
import controllers.getLoginInfos;
import instances.HibernateConnection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import models.CurrentDatas;
import models.DetailDevis;
import models.Client;

/**
 *
 * @author sylv
 */
public class Fenetre extends JFrame {

    public static Fenetre instance;
    public JPanel conteneur = new JPanel();
    //menu
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("Menu"),
            alertes = new JMenu("Alertes"),
            societes = new JMenu("Sociétés");
    private JMenuItem evenements = new JMenuItem("Evenements"),
            synchro = new JMenuItem("Synchronisation"),
            recherche = new JMenuItem("Recherche"),
//            sortie = new JMenuItem("Quitter"),
            alertes_new = new JMenuItem("Nouvelles"),
            alertes_histo = new JMenuItem("Historique"),
            afficher = new JMenuItem("Afficher"),
            creer = new JMenuItem("Creer");
    private CurrentDatas cd = CurrentDatas.getInstance();
    //toolbar
    private JToolBar toolbar = new JToolBar() ;

    
    private JLabel logout = new JLabel(new ImageIcon("ressources/images/quitter.png"));
        private JLabel username = new JLabel();
        private JLabel lblAccueil = new JLabel(new ImageIcon("ressources/images/accueil.png"));
        private JLabel lblOnline = new JLabel(new ImageIcon("ressources/images/online.png"));
        private JLabel lblOffline = new JLabel(new ImageIcon("ressources/images/offline.png"));
        private JLabel lblReseau = new JLabel();
        //User
        public UserActif user;

        public Fenetre() {
            this.setTitle("Plast'Prod");
            this.setSize(1024, 800);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setResizable(false);
            boolean login_ok = false;

            while (!login_ok)
            {
                loginDialog zd = new loginDialog(null, "Veuillez vous connecter", true);
                getLoginInfos zInfos = zd.showZDialog();
                
                if (zInfos.getCancelled()) {
                    System.exit(0);
                } else {
                    user = new UserActif(zInfos.getLogin());
                    if (user.Exists() && user.verify(zInfos.getPasswd()) == true) {
                        //user.setLastLogin();
                        login_ok = true;
                        break;
                    } else {
                        JOptionPane jop3 = new JOptionPane();
                        jop3.showMessageDialog(null, "Login ou mot de passe incorrects", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            
            
            //menu
            
            cd.setUser(user);
            afficher.addActionListener(new DisplayListener());
            this.societes.add(afficher);
            creer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            creer.addActionListener(new CreateListener());
            this.societes.add(creer);
            this.menu.add(societes);
            evenements.addActionListener(new EvenementListener());
            this.menu.add(evenements);
            alertes_new.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            alertes_new.addActionListener(new AlertNewListener());
            alertes_histo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            alertes_histo.addActionListener(new AlertHistoListener());
            this.alertes.add(alertes_new);
            this.alertes.add(alertes_histo);
            this.menu.add(alertes);
            synchro.addActionListener(new SynchroListener());
            this.menu.add(synchro);
            recherche.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            recherche.addActionListener(new FindListener());
            this.menu.add(recherche);
//            this.menu.addSeparator();
//            sortie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
//                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
//            sortie.addActionListener(new LogoutListener());
            this.setUndecorated(true);
//            this.menu.add(sortie);
            this.menuBar.add(menu);
            this.setJMenuBar(menuBar);
            this.conteneur.setLayout(new BorderLayout());
            this.conteneur.add(toolbar, BorderLayout.NORTH);

            //toolbar

            logout.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent me) {
                    Logout lo = new Logout(user);
                    RenewContener(lo.getPanel());
                }
            });
            username.setText(user.getFullName());
            username.setBorder(new EmptyBorder(0, 20, 0, 20));
            toolbar.add(Box.createHorizontalGlue());
            lblOnline.setToolTipText("Passer en mode hors ligne");
            lblOnline.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            logout.setToolTipText("Quitter");
            logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            lblReseau.setText("Vous n'êtes pas connecté");
            lblOnline.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent me) {
                    Synchro connect = new Synchro();
                    boolean status = connect.InitConnect();
                    if (HibernateConnection.online == true || status == true)
                    {
                        Replication rep = new Replication();
                        rep.start();
                    }
                    else
                    {
                        lblReseau.setVisible(true);
                        java.util.Timer t = new java.util.Timer();
                        t.schedule(new java.util.TimerTask() {
                            public void run() {
                                lblReseau.setVisible(false);
                            }
                        }, 2500);

                    }
                }
            });
            lblReseau.setVisible(false);
            lblReseau.setBorder(new EmptyBorder(0, 0, 0, 45));
            lblReseau.setForeground(Color.red);
            lblOffline.setToolTipText("Passer en mode en ligne");
            lblOffline.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            lblOffline.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent me) {
                    Synchro connect = new Synchro();
                    boolean status = connect.InitConnect();
                    if (HibernateConnection.online == true || status == true)
                    {
                        //Connection en ligne (renvoi sur la page synchro si des actions sont à sauvegarder)
                        connect.onlinemod();
                    }
                    else
                    {
                        lblReseau.setVisible(true);
                        java.util.Timer t = new java.util.Timer();
                        t.schedule(new java.util.TimerTask() {
                            public void run() {
                                lblReseau.setVisible(false);
                            }
                        }, 2500);
                    }
                }
            });
            toolbar.add(lblReseau);
            toolbar.add(lblOffline);
            toolbar.add(lblOnline);
            if (HibernateConnection.online == false)
            {
                lblOnline.setVisible(false);
                lblOffline.setVisible(true);
            }
            else
            {
                lblOnline.setVisible(true);
                lblOffline.setVisible(false);
            }
            lblAccueil.setBorder(new EmptyBorder(0, 20, 0, 0));
            lblAccueil.setToolTipText("Accueil");
            lblAccueil.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            lblAccueil.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent me) {
                    RenewAccueil();
                }
            });
            toolbar.add(lblAccueil);
            toolbar.add(username);
            toolbar.add(logout);
            toolbar.setBackground(Color.white);

            this.setContentPane(this.conteneur);

            this.setLocationRelativeTo(null);
            this.setVisible(true);
            Synchro sync = new Synchro();
            //Si on est en ligne et qu'il y a des actions à sauvegarder
            if(HibernateConnection.online == true && sync.emptyDir() == false)
            {
                //Redirection vers la page de synchro
                RenewSnchro();
            }
            //Sinon
            else
            {
                //Redirection vers l'accueil
                Accueil home = new Accueil(user);
                RenewContener(home.getPanel());
            }
        }

        //LISTENERS
        private class AlertNewListener implements ActionListener {

            public AlertNewListener() {
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                Alertes al = new Alertes(user);
                RenewContener(al.getPanel());
            }
        }

        private class AlertHistoListener implements ActionListener {

            public AlertHistoListener() {
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                Alertes al = new Alertes(user, true);
                RenewContener(al.getPanel());
            }
        }

        private class CreateListener implements ActionListener {

            public CreateListener() {
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                Creation cr = new Creation(user);
                RenewContener(cr.getPanel());
            }
        }

        private class DisplayListener implements ActionListener {

            public DisplayListener() {
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                Affichage af = new Affichage(user);
                RenewContener(af.getPanel());
            }
        }

        private class EvenementListener implements ActionListener {

            public EvenementListener() {
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                Evenement ev = new Evenement(user);
                RenewContener(ev.getPanel());
            }
        }

        private class FindListener implements ActionListener {

            public FindListener() {
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                Recherche re = new Recherche(user);
                RenewContener(re.getPanel());
            }
        }

        private class SynchroListener implements ActionListener {

            public SynchroListener() {
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                SynchroView sy = new SynchroView(user);
                RenewContener(sy.getPanel());
            }
        }

        private class LogoutListener implements ActionListener {

            public LogoutListener() {
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                Logout lo = new Logout(user);
                RenewContener(lo.getPanel());
            }
        }

        public void RenewContener(JPanel panel) {
            conteneur.removeAll();
            conteneur.add(toolbar, BorderLayout.NORTH);
            conteneur.add(panel, BorderLayout.CENTER);
            conteneur.revalidate();
        }

        public void RenewAlert() {
            Alertes alt = new Alertes(user);
            RenewContener(alt.getPanel());
        }

        public void RenewAlert(Integer cli_id) {
            Alertes alt = new Alertes(user, cli_id);
            RenewContener(alt.getPanel());
        }
        
        public void RenewClientdDetail(Integer cli_id){
            ClientDetail clientDetail = new ClientDetail(cli_id);
            RenewContener(clientDetail.getPanel());                    
        }
        public void RenewDetailsDevis(Integer devid){
            DevisDetail detailDevis = new DevisDetail(devid);
            RenewContener(detailDevis.getPanel());                    
        }
        public void RenewDemandeDetail(Integer demandeid){
            DemandeDetail demande = new DemandeDetail(demandeid);
            RenewContener(demande.getPanel());
        }

        public void RenewCmd(Integer cmd_id) {
            CommandeDetail cmd = new CommandeDetail(cmd_id);
            RenewContener(cmd.getPanel());
        }

        public void RenewSnchro() {
            if (HibernateConnection.online == false)
            {
                lblOnline.setVisible(false);
                lblOffline.setVisible(true);
            }
            else
            {
                lblOnline.setVisible(true);
                lblOffline.setVisible(false);
            }

            username.setText(user.getFullName());
            SynchroView sy = new SynchroView(user);
            RenewContener(sy.getPanel());
        }

        public void RenewAccueil() {
            if (HibernateConnection.online == false)
            {
                lblOnline.setVisible(false);
                lblOffline.setVisible(true);
                HibernateConnection.newConnect(false);
            }
            else
            {
                lblOnline.setVisible(true);
                lblOffline.setVisible(false);
                HibernateConnection.newConnect(true);
            }
            username.setText(user.getFullName());
            Accueil ac = new Accueil(user);
            RenewContener(ac.getPanel());
        }

        public void Erase() {
            conteneur.removeAll();
            conteneur.revalidate();
        }

        public static synchronized Fenetre getInstance() {
            if (instance == null)
            {
                instance = new Fenetre();
            }
            return instance;
        }

        public void progBar() {
            ReplicView pb = new ReplicView();
            RenewContener(pb.getPanel());
        }

        public void rep(int value, String title) {
            ReplicView pb = new ReplicView();
            RenewContener(pb.stateChanged(value, title));
        }
         public void renewRapport(Client cli) {
            ReportList rl = new ReportList(user,cli);
            RenewContener(rl.getPanel());
        }
    }
