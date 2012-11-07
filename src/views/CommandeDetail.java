/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import classes.ButtonData;
import controllers.TableDispatcher;
import instances.DetailCdeInstance;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import models.DetailCommande;
import models.ModelesTables;

/**
 *
 * @author sylv
 */
public class CommandeDetail extends KContainer {
    Integer cmd_id;
    JLabel title = new JLabel();
    private JProgressBar progressBar;
    DetailCommande details;

    public CommandeDetail(Integer id) {
        super();
        this.cmd_id = id;
        this.recupDetails();
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
        content.setPreferredSize(new Dimension(1000, 750));
        
        detailCmd.setPreferredSize(new Dimension(450, 700));

        //demande un tabledispatcher
        TableDispatcher cp = new TableDispatcher();
        content.setBackground(Color.white);
        listeCmd.setBackground(Color.white);
        listeCmd.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));

        //on affiche dans notre panel, la table renvoyée par le tabledispatcher, correspondant au modèle COMMANDE 
        listeCmd.add(cp.showtable(ModelesTables.COMMANDE,this.details.getCliid()));
        content.add(listeCmd);
        //detail commandes, panneau de droite
        detailCmd.setBackground(Color.green);
        detailCmd.setLayout(new BoxLayout(detailCmd, BoxLayout.Y_AXIS));

        top_right.setLayout(new FlowLayout());
        top_right.add(new JLabel("<html> " + details.getClirais() + " " + details.getClinom() + "<br />Créée le " + details.getComdate() + " par " + details.getUtiprenom() + " " + details.getUtinom() + "</html>"));
        
        ButtonData retour = new ButtonData("Retour à la société", details.getCliid());
        retour.addActionListener(new RetourListener());
        top_right.add(retour);
        center_right.setBackground(Color.white);
        center_right.add(new JLabel("Avancement :"));
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(Integer.valueOf(details.getCometat()));
        progressBar.setString(details.getCometat() + "%");
        progressBar.setStringPainted(true);
        center_right.add(progressBar);
        if (this.details.getDemandeid() != null) {
            center_right.add(cp.showtable(ModelesTables.DEVIS,this.details.getDemandeid()));
        }
        bottom_right.setBackground(Color.white);
        bottom_right.setLayout(new GridBagLayout());
        JLabel comm = new JLabel("<html><b>Description :</b><br />" + this.details.getComdesc());
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
            ButtonData tmp = (ButtonData)e.getSource();
            ClientDetail cd = new ClientDetail(tmp.getId());
           
        }
    }
    
    private void recupDetails() {
        DetailCdeInstance cmdInstance = DetailCdeInstance.getInstance();
        Hashtable h = new Hashtable();
        h.put("comid", this.cmd_id);
        List<DetailCommande> cmds = cmdInstance.GetDetailcde("comid=:comid", h);
        this.details = cmds.get(0);
    }
   
}
