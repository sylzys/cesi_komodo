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
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
        JPanel content = new JPanel();
        Client cli = null;
        content.setLayout(new BorderLayout());
        content.add(title, BorderLayout.CENTER);
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
        JPanel cliInfos = new JPanel();
        cliInfos.setBackground(Color.white);
        cliInfos.setPreferredSize(new Dimension(120, 100));
        JLabel cli_nom = new JLabel(cli.getClinom());
        JLabel cli_crea = new JLabel(Integer.toString(cli.getUti_utiid()));
        JLabel cli_date = new JLabel(cli.getClidteadd().toString());
        cliInfos.add(cli_nom);
        cliInfos.add(cli_crea);
        cliInfos.add(cli_date);
        
        cliInfos.setBorder(BorderFactory.createTitledBorder("Infos Société"));
        content.add(cliInfos, BorderLayout.SOUTH);
//        System.out.println("LISTING CLIENT IN DETAIL");
//        System.out.println("Sté : " + cli.getClinom());
//        System.out.println("createur : " + cli.getUti_utiid());
//        System.out.println("createur : " + cli.getClidteadd());
        // ClientInstance CliInstance = ClientInstance.getInstance();
        Fenetre fen = Fenetre.getInstance();
        fen.conteneur.setVisible(false);
        System.out.println("RECUP INSTANCE " + fen);
        fen.RenewContener(this.getPanel());
        fen.conteneur.setVisible(true);
    }
}
