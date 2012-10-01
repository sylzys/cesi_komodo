/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.UserActif;
import instances.ClientInstance;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import models.Client;
import models.HibernateConnection;
import org.hibernate.Query;

/**
 *
 * @author sylv
 */
public class ClientDetail extends KContainer {

    JLabel title = new JLabel("PANNEAU CLIENT DETAIL");
    int cli_id;

    public ClientDetail(int id) {
        super();
        cli_id = id;
        System.out.println("IN CLIENT DETAIL");
//    this.user = user;
//         Fenetre fen = Fenetre.getInstance();
//        System.out.println("RECUP INSTANCE " +fen);
//        fen.conteneur.removeAll();
        //      Erase();//RenewContener(this.getPanel());
        initPanel();
//        JOptionPane jop = new JOptionPane();
//        jop.showMessageDialog(null, "Fonction de déconnexion", "LOGOUT", JOptionPane.INFORMATION_MESSAGE);
//        Fenetre fen = Fenetre.getInstance();
//        System.out.println("RECUP INSTANCE " +fen);
//        fen.conteneur.setVisible(false);
//        fen.Erase();//RenewContener(this.getPanel());
//        System.exit(0);
    }

    @Override
    protected void initPanel() {
        JPanel content = new JPanel(),
                cliInfos = new JPanel(),
                top = new JPanel(),
                bottom = new JPanel(),
                cliDetail = new JPanel(),
                cliButtons = new JPanel();
        JButton addContact = new JButton("Ajouter"),
                contact1 = new JButton("+"),
                contact2 = new JButton("+"),
                contact3 = new JButton("+");

        Client cli = null;
        content.setLayout(new BorderLayout());
        // content.add(title);
        content.setPreferredSize(new Dimension(1000, 768));
        this.panel.add(content);
        HibernateConnection connection = HibernateConnection.getInstance();
        try
        {
            Query query = connection.getSession().createQuery("from Client where cliid = :cliid");
            query.setParameter("cliid", cli_id);
            //  query.setParameter("utiid", this.user.getId());
            cli = (Client) query.uniqueResult();
            System.out.println(cli);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        top.setBackground(Color.white);
        top.setLayout(new BorderLayout());

        cliInfos.setBackground(Color.white);
        cliDetail.setPreferredSize(new Dimension(800, 150));
        //adresse, raison sociale etc

        cliDetail.setBackground(Color.white);
        cliDetail.setLayout(new BorderLayout());
        cliInfos.setLayout(new FlowLayout(0, 50, 0));

        //adresse
        JPanel cliAddr = new JPanel();
        cliAddr.setBackground(Color.white);
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
        cliContact.setBackground(Color.white);
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
        cliRS.setBackground(Color.white);
        JLabel RS = new JLabel("<html>Dirigeant: " + cli.getClidg() + "<br>Activité :"
                + cli.getCliactivite() + "<br>SIRET: "
                + cli.getClisiret() + "<br>CA: "
                + Integer.toString(cli.getClica()) + "</html>");
        cliRS.add(RS);
        cliInfos.add(cliRS);

        //Boutons contact
        cliButtons.setBackground(Color.white);
        cliButtons.setLayout(new FlowLayout());
        cliButtons.add(addContact);


        cliDetail.setBorder(BorderFactory.createTitledBorder("Infos Société"));

        //adding panels
        cliDetail.add(new JLabel("<html><b>" + cli.getClinom() + "</b><br/></html>"), BorderLayout.NORTH);
        cliDetail.add(cliInfos, BorderLayout.CENTER);
        cliDetail.add(cliButtons, BorderLayout.SOUTH);
        top.add(cliDetail, BorderLayout.WEST);
        content.add(top, BorderLayout.NORTH);

        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
        //suivi satisfaction
        JPanel suivi_satisfaction = new JPanel();
        suivi_satisfaction.setBorder(BorderFactory.createTitledBorder("Suivi Satisfaction"));
        suivi_satisfaction.setPreferredSize(new Dimension(120, 50));
        bottom.add(suivi_satisfaction);
        bottom.add(Box.createVerticalStrut(10));
//        content.add(suivi_satisfaction, BorderLayout.CENTER);
//        
//        //alertes
        JPanel alertes = new JPanel();
        alertes.setBorder(BorderFactory.createTitledBorder("Alertes"));
        alertes.setPreferredSize(new Dimension(120, 50));
        bottom.add(alertes);
        bottom.add(Box.createVerticalStrut(10));
        //content.add(alertes, BorderLayout.SOUTH);

//        //Reporting
        JPanel reporting = new JPanel();
        reporting.setBorder(BorderFactory.createTitledBorder("Reporting"));
        reporting.setPreferredSize(new Dimension(120, 50));
        bottom.add(reporting);
        bottom.add(Box.createVerticalStrut(10));

        content.add(bottom, BorderLayout.CENTER);

        //refresh de la fenetre
        Fenetre fen = Fenetre.getInstance();
        fen.conteneur.setVisible(false);
        fen.RenewContener(this.getPanel());
        fen.conteneur.setVisible(true);
    }
}
