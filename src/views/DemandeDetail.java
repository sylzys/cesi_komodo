/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.TableDispatcher;
import controllers.UserActif;
import instances.DetailDmdInstance;
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
import models.DetailDmd;
import models.ModelesTables;
import models.CurrentDatas;

/**
 *
 * @author sylv
 */
public class DemandeDetail extends KContainer {

    List<DetailDmd> dd;
    int demande_id;
    JLabel title = new JLabel();
    private JProgressBar progressBar;

    public DemandeDetail(int id) {
        super();
        demande_id = id;
        CurrentDatas cur = CurrentDatas.getInstance();
        cur.setSoc_id(demande_id);
        initPanel();
    }

   // @Override
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
        DetailDmdInstance DetailDdeInstance = DetailDmdInstance.getInstance();
        Hashtable h = new Hashtable();
        h.put("demandeid", 2);
        dd = DetailDdeInstance.GetDetaildemande("where demandeid = 2", h);
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
        top_right.add(new JLabel("<html>> " + dd.get(0).getCliid() + "<br />Créee par " + dd.get(0).getUtiprenom() + " " + dd.get(0).getUtiprenom() + " pour l'entreprise " +dd.get(0).getClinom() + "</html>"));
//top_right.add(new JButton("Retour à la société"));
        center_right.setBackground(Color.white);
        center_right.add(new JLabel("Avancement :"));
        progressBar = new JProgressBar(0, 100);
     //   progressBar.setValue(Integer.valueOf(dd.get(0).getDemandeetat()));
    //    progressBar.setString(dd.get(0).getDemandeetat() + "%");
        progressBar.setStringPainted(true);
        center_right.add(progressBar);
        bottom_right.setBackground(Color.white);
        bottom_right.setLayout(new GridBagLayout());
        System.out.println("DD: "+dd);
     //   JLabel comm = new JLabel("<html><b>"+dd.get(0).getSuivdoscom()+"</b></html>");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
  //      bottom_right.add(comm, gbc);
        detailDmd.add(top_right);
        detailDmd.add((center_right));
        detailDmd.add((bottom_right));
        content.add(detailDmd);
        this.panel.add(content);
    }

    private static class RetourListener implements ActionListener {

        public RetourListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            CurrentDatas cur = CurrentDatas.getInstance();
            ClientDetail cd = new ClientDetail(cur.getSoc_id());
        }
    }
}
