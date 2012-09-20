package views;

import controllers.UserActif;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.*;
import controllers.getLoginInfos;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author sylv
 */
public class Fenetre extends JFrame {

   
  public Fenetre fen = this;
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
     private JButton logout = new JButton ("Deconnexion");
     private JLabel username = new JLabel();
     
     //User
      UserActif user = new UserActif();
     
      
    public Fenetre() {
        this.setTitle("Plast'Prod");
        this.setSize(1024, 768);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        boolean login_ok = false;

        while (!login_ok) {
            loginDialog zd = new loginDialog(null, "Veuillez vous connecter", true);
            getLoginInfos zInfos = zd.showZDialog();
            JOptionPane jop = new JOptionPane();
            System.out.println(zInfos.toString());
            if("-NA-".equals(zInfos.toString())){
                System.exit(0);
            }
            user.setLogin(zInfos.toString().split("#")[0]);
            user.setPass(zInfos.toString().split("#")[1]);
            if (user.verify()) {
                break;
            } else {
                JOptionPane jop3 = new JOptionPane();
                jop3.showMessageDialog(null, "Votre login est incorrect", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
            }
        }
        //menu
        afficher.addActionListener(new DisplayListener());
        this.societes.add(afficher);
        creer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        creer.addActionListener(new CreateListener());
        this.societes.add(creer);
        this.menu.add(societes);
        evenements.addActionListener(new EvenementListener());
        this.menu.add(evenements);
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
        this.menu.add(sortie);
        this.menuBar.add(menu);
        this.setJMenuBar(menuBar);
        this.conteneur.setLayout(new BorderLayout());
        this.conteneur.add(toolbar, BorderLayout.NORTH);
        
        //toolbar
        logout.addActionListener(new LogoutListener());
        username.setText(user.getFullName());
        username.setBorder(new EmptyBorder(0, 0, 0, 20) );
        toolbar.add(Box.createHorizontalGlue());
        toolbar.add(username);
        toolbar.add(logout);
        
        this.setContentPane(this.conteneur);
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        //Affiachage accueil
        Accueil home = new Accueil(user);
        conteneur.removeAll();
        conteneur.add(toolbar, BorderLayout.NORTH);
        conteneur.add(home.getPanel(), BorderLayout.CENTER);
        conteneur.revalidate();
    }
    
    
    
   //LISTENERS
    
     private class AlertListener implements ActionListener {

        public
        AlertListener() {
        }

        @Override
        public
        void actionPerformed(ActionEvent e) {
            Alertes al = new Alertes(user);
            conteneur.removeAll();
            conteneur.add(toolbar, BorderLayout.NORTH);
            conteneur.add(al.getPanel(), BorderLayout.CENTER);
            conteneur.revalidate();
        }
    }

    private class CreateListener implements ActionListener {

        public
        CreateListener() {
        }

        @Override
        public
        void actionPerformed(ActionEvent e) {
            Creation cr = new Creation(user);
            conteneur.removeAll();
            conteneur.add(toolbar, BorderLayout.NORTH);
            conteneur.add(cr.getPanel(), BorderLayout.CENTER);
            conteneur.revalidate();
        }
    }

    private class DisplayListener implements ActionListener {

        public
        DisplayListener() {
        }

        @Override
        public
        void actionPerformed(ActionEvent e) {
            Affichage af = new Affichage(user);
            conteneur.removeAll();
            conteneur.add(toolbar, BorderLayout.NORTH);
            conteneur.add(af.getPanel(), BorderLayout.CENTER);
            conteneur.revalidate();
        }
    }

    private class EvenementListener implements ActionListener {

        public
        EvenementListener() {
            
        }

        @Override
        public
        void actionPerformed(ActionEvent e) {
            Evenement ev = new Evenement(user);
            conteneur.removeAll();
            conteneur.add(toolbar, BorderLayout.NORTH);
            conteneur.add(ev.getPanel(), BorderLayout.CENTER);
            conteneur.revalidate();
        }
    }

    private class FindListener implements ActionListener {

        public
        FindListener() {
        }

        @Override
        public
        void actionPerformed(ActionEvent e) {
            Recherche re = new Recherche(user);
            conteneur.removeAll();
            conteneur.add(toolbar, BorderLayout.NORTH);
            conteneur.add(re.getPanel(), BorderLayout.CENTER);
            conteneur.revalidate();
        }
    }

    private class SynchroListener implements ActionListener {

        public
        SynchroListener() {
        }

        @Override
        public
        void actionPerformed(ActionEvent e) {
           Synchro sy = new Synchro(user);
           conteneur.removeAll();
           conteneur.add(toolbar, BorderLayout.NORTH);
            conteneur.add(sy.getPanel(), BorderLayout.CENTER);
            conteneur.revalidate();
        }
    }
    private class LogoutListener implements ActionListener {

        public LogoutListener() {
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Logout lo = new Logout(user);
            conteneur.removeAll();
            conteneur.add(toolbar, BorderLayout.NORTH);
            conteneur.add(lo.getPanel(), BorderLayout.CENTER);
            conteneur.revalidate();
        }
    }
}
