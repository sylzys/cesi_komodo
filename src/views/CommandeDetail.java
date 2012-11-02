/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.TableDispatcher;
import controllers.UserActif;
import instances.CommandeInstance;
import instances.DetailCdeInstance;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import models.CurrentDatas;
import models.DetailCommande;
import models.ModelesTables;

/**
 *
 * @author sylv
 */
public class CommandeDetail extends KContainer {

    List<DetailCommande> cd;
    int cmd_id;
    JLabel title = new JLabel();
    private JProgressBar progressBar;

    public CommandeDetail(int id) {
        super();
        this.cmd_id = id;
        initPanel();
    }

    @Override
    protected void initPanel() {
//        ClientDetail cd_ = ClientDetail.getThis();
//        System.out.println ("GETTIN LAST USED ID : " + cd_.cli_id);
        JPanel content = new JPanel(),
                listeCmd = new JPanel(),
                detailCmd = new JPanel(),
                top_right = new JPanel(),
                center_right = new JPanel(),
                bottom_right = new JPanel();

        content.setLayout(new FlowLayout());
        content.setPreferredSize(new Dimension(1000, 750));
        // listeCmd.setPreferredSize(new Dimension(500, 768));
        detailCmd.setPreferredSize(new Dimension(450, 700));

        //on récupère la liste des commandes détailles dans la DB
        DetailCdeInstance CmdInstance = DetailCdeInstance.getInstance(); //récupère l'instance
        //prépare une hashtable si besoin
        Hashtable h = new Hashtable();
        //le couple valeur/paramètre, à ne plus mettre en dur...
        h.put("comid", this.cmd_id);
        //récupère la liste.
        cd = CmdInstance.GetDetailcde("where comid = :comid", h);

        //demande un tabledispatcher
        TableDispatcher cp = new TableDispatcher();
        content.setBackground(Color.white);
        listeCmd.setBackground(Color.white);
        // listeCmd.setBorder(new EmptyBorder(0, 0, 0, 20));
        listeCmd.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));

        //on affiche dans notre panel, la table renvoyée par le tabledispatcher, correspondant au modèle COMMANDE 
        listeCmd.add(cp.showtable(ModelesTables.COMMANDE));
        content.add(listeCmd);
        //detail commandes, panneau de droite
        detailCmd.setBackground(Color.green);
        detailCmd.setLayout(new BoxLayout(detailCmd, BoxLayout.Y_AXIS));

        top_right.setLayout(new FlowLayout());
        top_right.add(new JLabel("<html>> " + cd.get(0).getClirais() + "<br />Créee le " + cd.get(0).getComdate() + " par " + cd.get(0).getInternom() + "</html>"));
        JButton retour = new JButton("Retour à la société");
        retour.addActionListener(new RetourListener());
        top_right.add(retour);
        center_right.setBackground(Color.white);
        center_right.add(new JLabel("Avancement :"));
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(Integer.valueOf(cd.get(0).getCometat()));
        progressBar.setString(cd.get(0).getCometat() + "%");
        progressBar.setStringPainted(true);
        center_right.add(progressBar);
        bottom_right.setBackground(Color.white);
        bottom_right.setLayout(new GridBagLayout());
        JLabel comm = new JLabel("<html><b>Commentaires</b><br />Powder gingerbread cheesecake wafer. "
                + "I love sugar plum chocolate cake pudding dragée macaroon. Gingerbread wafer dragée pie. "
                + "Fruitcake I love candy canes jelly beans cupcake I love applicake. Dessert I love jelly powder jelly. "
                + "Cookie cookie wypas tootsie roll I love.");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        bottom_right.add(comm, gbc);
        detailCmd.add(top_right);
        detailCmd.add((center_right));
        detailCmd.add((bottom_right));
        content.add(detailCmd);
        this.panel.add(content);
    }

    private static class RetourListener implements ActionListener {

        public RetourListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
           // System.out.println("RETOUR");
            CurrentDatas cur = CurrentDatas.getInstance();
            ClientDetail cd = new ClientDetail(cur.getSoc_id());
        }
    }
}
