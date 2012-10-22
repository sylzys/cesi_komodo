/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.TableDispatcher;
import controllers.UserActif;
import instances.DemandeInstance;
import instances.DetailDemandeInstance;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Hashtable;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import models.DetailDemande;
import models.ModelesTables;

/**
 *
 * @author sylv
 */
public class DemandeDetail extends KContainer {

    List<DetailDemande> demande;
    int demande_id;
    JLabel title = new JLabel();
    private JProgressBar progressBar;

    public DemandeDetail(int id) {
        super();
        this.demande_id = id;

        initPanel();
    }

    @Override
    protected void initPanel() {
        JPanel content = new JPanel(),
                listeDmd = new JPanel(),
                detailDmd = new JPanel(),
                top_right = new JPanel(),
                center_right = new JPanel(),
                bottom_right = new JPanel();

        content.setLayout(new FlowLayout());
        content.setPreferredSize(new Dimension(1000, 750));
        // listeDmd.setPreferredSize(new Dimension(500, 768));
        detailDmd.setPreferredSize(new Dimension(450, 700));

        //get cmd-detail from DB
        DetailDemandeInstance DemandeInstance = DetailDemandeInstance.getInstance();
        Hashtable h = new Hashtable();
        h.put("demandeid", 1);
        demande = DemandeInstance.GetDetaildemande("where demandeid = :demandeid", h);
        //  System.out.println("CMD-ID: "+this.demande_id);
        //title.setText("DETAIL COMMANDE "+ this.demande_id);
        TableDispatcher cp = new TableDispatcher();
        content.setBackground(Color.white);
        listeDmd.setBackground(Color.white);
        // listeDmd.setBorder(new EmptyBorder(0, 0, 0, 20));
        listeDmd.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
        listeDmd.add(cp.showtable(ModelesTables.DEMANDE));
        content.add(listeDmd);
        //detail commandes, panneau de droite
        detailDmd.setBackground(Color.green);
        detailDmd.setLayout(new BoxLayout(detailDmd, BoxLayout.Y_AXIS));

        top_right.setLayout(new FlowLayout());
        top_right.add(new JLabel("<html>> " + demande.get(0).getCliid() + "<br />Créee le " + demande.get(0).getDemandeetat() + " par " + demande.get(0).getDemandedteadd() + "</html>"));
        top_right.add(new JButton("Retour à la société"));
        center_right.setBackground(Color.white);
        center_right.add(new JLabel("Avancement :"));
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(Integer.valueOf(demande.get(0).getDemandeetat()));
        progressBar.setString(demande.get(0).getDemandeetat() + "%");
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
        detailDmd.add(top_right);
        detailDmd.add((center_right));
        detailDmd.add((bottom_right));
        content.add(detailDmd);
        this.panel.add(content);
    }
}
