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
import java.util.Hashtable;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
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
        JPanel content = new JPanel(),
                listeCmd = new JPanel(),
                detailCmd = new JPanel(),
                top_right = new JPanel(),
                center_right = new JPanel(),
                bottom_right = new JPanel();

        content.setLayout(new FlowLayout());
        content.setPreferredSize(new Dimension(1000, 768));
       // listeCmd.setPreferredSize(new Dimension(500, 768));
        detailCmd.setPreferredSize(new Dimension(450, 768));
        
        //get cmd-detail from DB
        DetailCdeInstance CmdInstance = DetailCdeInstance.getInstance();
        Hashtable h = new Hashtable();
        h.put("comid", 1);
        cd = CmdInstance.GetDetailcde("where comid = :comid", h);
        //  System.out.println("CMD-ID: "+this.cmd_id);
        //title.setText("DETAIL COMMANDE "+ this.cmd_id);
        TableDispatcher cp = new TableDispatcher();
        content.setBackground(Color.white);
        listeCmd.setBackground(Color.white);
        listeCmd.setBorder(new EmptyBorder(0, 0, 0, 20));
        listeCmd.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
        listeCmd.add(cp.showtable(ModelesTables.COMMANDE));
        content.add(listeCmd);
        //detail commandes, panneau de droite
        detailCmd.setBackground(Color.white);
        detailCmd.setLayout(new BoxLayout(detailCmd, BoxLayout.Y_AXIS));
        
        top_right.setLayout(new FlowLayout());
        top_right.add(new JLabel("> "+cd.get(0).getClirais()));
        top_right.add(new JButton("Retour à la société"));
        center_right.setBackground(Color.white);
        center_right.add(new JLabel("Avancement :"));
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(Integer.valueOf(cd.get(0).getCometat()));
        progressBar.setString(cd.get(0).getCometat()+ "%");
        progressBar.setStringPainted(true);
        center_right.add(progressBar);
        detailCmd.add(top_right);
        detailCmd.add((center_right));
        content.add(detailCmd);
        this.panel.add(content);
    }
}
