package views;

import controllers.Replication;
import controllers.UserActif;
import controllers.Synchro;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.*;
import controllers.getLoginInfos;
import instances.ClientInstance;
import instances.HibernateConnection;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

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
            societes = new JMenu("Sociétés");
    private JMenuItem evenements = new JMenuItem("Evenements"),
            alertes = new JMenuItem("Alertes"),
            synchro = new JMenuItem("Synchronisation"),
            recherche = new JMenuItem("Recherche"),
            sortie = new JMenuItem("Quitter"),
            afficher = new JMenuItem("Afficher"),
            creer = new JMenuItem("Creer");
    //toolbar
    private JToolBar toolbar = new JToolBar();
    private JButton logout = new JButton("Deconnexion");
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

        boolean login_ok = false;

//        while (!login_ok)
//        {
//            loginDialog zd = new loginDialog(null, "Veuillez vous connecter", true);
//            getLoginInfos zInfos = zd.showZDialog();
//            JOptionPane jop = new JOptionPane();
//            System.out.println(zInfos.toString());
//            if ("-NA-".equals(zInfos.toString()))
//            {
//                System.exit(0);
//            }
//            String[] login = zInfos.toString().split("#");
//            if (!login[0].isEmpty() && !login[1].isEmpty())
//            {
//                user = new UserActif(login[0]);
//                if (user.verify(login[1]))
//                {
//                    break;
//                }
//                else
//                {
//                    JOptionPane jop3 = new JOptionPane();
//                    jop3.showMessageDialog(null, "Votre login est incorrect", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
//                }
//            }
//            else
//            {
//                JOptionPane jop4 = new JOptionPane();
//                jop4.showMessageDialog(null, "Les champs 'identifiant' et 'mot de passe' ne peuvent pas être vides", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
//            }
//        }
        //menu
        user = new UserActif("admin");
        afficher.addActionListener(new DisplayListener());
        this.societes.add(afficher);
        creer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        creer.addActionListener(new CreateListener());
        this.societes.add(creer);
        this.menu.add(societes);
        evenements.addActionListener(new EvenementListener());
        this.menu.add(evenements);
        alertes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        alertes.addActionListener(new AlertListener());
        this.menu.add(alertes);
        synchro.addActionListener(new SynchroListener());
        this.menu.add(synchro);
        recherche.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        recherche.addActionListener(new FindListener());
        this.menu.add(recherche);
        this.menu.addSeparator();
        sortie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        sortie.addActionListener(new LogoutListener());
        this.menu.add(sortie);
        this.menuBar.add(menu);
        this.setJMenuBar(menuBar);
        this.conteneur.setLayout(new BorderLayout());
        this.conteneur.add(toolbar, BorderLayout.NORTH);

        //toolbar

        logout.addActionListener(new LogoutListener());
        username.setText(user.getFullName());
        username.setBorder(new EmptyBorder(0, 20, 0, 20));
        toolbar.add(Box.createHorizontalGlue());
        lblOnline.setToolTipText("Passer en mode hors ligne");
        lblOnline.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblReseau.setText("Vous n'êtes pas connecté");
        lblOnline.addMouseListener(new MouseAdapter() {  
           public void mousePressed(MouseEvent me){
               Synchro connect = new Synchro();
               boolean status = connect.InitConnect();
               if(HibernateConnection.online == true|| status== true)
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
           public void mousePressed(MouseEvent me){
               Synchro connect = new Synchro();
               boolean status = connect.InitConnect();
               if(HibernateConnection.online == true|| status== true)
               {
                    Synchro sync = new Synchro();   
                    sync.onlinemod();
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
        if(HibernateConnection.online == false)
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
           public void mousePressed(MouseEvent me){    
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

        //Affiachage accueil
        Accueil home = new Accueil(user);
        RenewContener(home.getPanel());
    }

    //LISTENERS
    private class AlertListener implements ActionListener {

        public AlertListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Alertes al = new Alertes(user);
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
    public void RenewSnchro() {            
            user = new UserActif("admin");
            if(HibernateConnection.online == false)
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
            user = new UserActif("admin");
            if(HibernateConnection.online == false)
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
    public void Erase(){
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
    public void progBar()
    {
        ProgressBarAtt pb = new ProgressBarAtt();
        RenewContener(pb.getPanel());
    }
    public void rep(int value, String title)
    {
        ProgressBarAtt pb = new ProgressBarAtt();
        RenewContener(pb.stateChanged(value, title));
    }
}
