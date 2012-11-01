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
import java.awt.Font;
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
    JButton newDevisBtn = new JButton();

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
                centertop_right = new JPanel(),
                center_right = new JPanel(),
                bottom_right = new JPanel();

        content.setLayout(new FlowLayout());
        content.setPreferredSize(new Dimension(1000, 750));
        // listeDmd.setPreferredSize(new Dimension(500, 768));
        detailDmd.setPreferredSize(new Dimension(450, 700));

        //  System.out.println("CMD-ID: "+this.demande_id);
        //title.setText("DETAIL COMMANDE "+ this.demande_id);
        TableDispatcher cp = new TableDispatcher();
        content.setBackground(Color.white);
        listeDmd.setBackground(Color.white);
        top_right.setBackground(Color.white);
        centertop_right.setBackground(Color.white);
        listeDmd.setPreferredSize(new Dimension(450, 200));
        listeDmd.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        
        listeDmd.setLayout(new BoxLayout(listeDmd, BoxLayout.LINE_AXIS));
        listeDmd.add(cp.showtable(ModelesTables.DEMANDELIST), BorderLayout.CENTER);
        content.add(listeDmd);
        //detail commandes, panneau de droite
        detailDmd.setBackground(Color.green);
        detailDmd.setLayout(new BoxLayout(detailDmd, BoxLayout.Y_AXIS));
        
        
        //get cmd-detail from DB
        DetailDmdInstance DetailDdeInstance = DetailDmdInstance.getInstance();
        Hashtable h = new Hashtable();
        h.put("demandeid", demande_id);
        dd = DetailDdeInstance.GetDetaildemande("demandeid",demande_id, h);

        top_right.setLayout(new FlowLayout());
        top_right.add(new JLabel("<html><b>> Demande n°" + dd.get(0).getDemandeid() + "</b><br />Créee par " + dd.get(0).getUtiprenom() + " " + dd.get(0).getUtinom() + " le " +dd.get(0).getSuivdosdate() + "</html>"));
        top_right.add(new JButton("Retour à la société"));
        center_right.setBackground(Color.white);
        centertop_right.add(new JButton("Valider la commande"));
        center_right.add(new JLabel("Avancement de la demande :"));
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(Integer.valueOf(dd.get(0).getDemandeetat()));
        progressBar.setString(dd.get(0).getDemandeetat() + "%");
        progressBar.setStringPainted(true);
        center_right.add(progressBar);
        bottom_right.setBackground(Color.white);
        newDevisBtn.setText("Créer un devis");
        center_right.add(newDevisBtn);
        bottom_right.setLayout(new GridBagLayout());
        
        Font f = new Font("Euphemia", Font.PLAIN, 14);
        JLabel comm = new JLabel("<html><b>Commentaires : </b><br />"+dd.get(0).getSuivdoscom()+"</html>");
        comm.setFont(f); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        bottom_right.add(comm, gbc);
        detailDmd.add(top_right);
        detailDmd.add(centertop_right);
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
