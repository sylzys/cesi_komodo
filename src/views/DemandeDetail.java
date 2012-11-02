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
        JLabel jLabel4 = new javax.swing.JLabel();
    JLabel jLabel5 = new javax.swing.JLabel();
    JLabel jLabel6 = new javax.swing.JLabel();
    JLabel jLabel7 = new javax.swing.JLabel();
    JLabel jLabel8 = new javax.swing.JLabel();
    JLabel jLabel9 = new javax.swing.JLabel();
    JLabel jLabel10 = new javax.swing.JLabel();
    JLabel jLabel11 = new javax.swing.JLabel();
    JLabel jLabel12 = new javax.swing.JLabel();

        content.setLayout(new FlowLayout());
        content.setPreferredSize(new Dimension(1000, 750));
        // listeDmd.setPreferredSize(new Dimension(500, 768));
        detailDmd.setPreferredSize(new Dimension(450, 700));

        //  System.out.println("CMD-ID: "+this.demande_id);
        //title.setText("DETAIL COMMANDE "+ this.demande_id);
        TableDispatcher cp = new TableDispatcher();
        TableDispatcher ld = new TableDispatcher();
        content.setBackground(Color.white);
        listeDmd.setBackground(Color.white);
        top_right.setBackground(Color.white);
        centertop_right.setBackground(Color.white);
        listeDmd.setPreferredSize(new Dimension(450, 200));
        listeDmd.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        
        listeDmd.setLayout(new BoxLayout(listeDmd, BoxLayout.LINE_AXIS));
       
        //detail commandes, panneau de droite
        detailDmd.setBackground(Color.green);
        detailDmd.setLayout(new BoxLayout(detailDmd, BoxLayout.Y_AXIS));
        
        
        //get cmd-detail from DB
        DetailDmdInstance DetailDdeInstance = DetailDmdInstance.getInstance();
        Hashtable h = new Hashtable();
        h.put("demandeid", demande_id);
        dd = DetailDdeInstance.GetDetaildemande("demandeid",demande_id, h);

        top_right.setLayout(new FlowLayout());
        jLabel5.setText("<html><h1><b>> Demande n°" + dd.get(0).getDemandeid() + "</b></h1><small>Créee par " + dd.get(0).getUtiprenom() + " " + dd.get(0).getUtinom() + " le " +dd.get(0).getSuivdosdate() + "</small></html>");
        jLabel5.setPreferredSize(new Dimension(250, 100));
        top_right.add(jLabel5);
        top_right.add(new JButton("Retour à la société"));
        JPanel ab5 = new JPanel();
        ab5.setLayout(new BoxLayout(ab5, BoxLayout.LINE_AXIS));
        ab5.setPreferredSize(new Dimension(400,30));
        ab5.setBackground(Color.white);
        
        newDevisBtn.setText("Créer un devis");
        JPanel ab7 = new JPanel();
        ab7.setLayout(new BoxLayout(ab7, BoxLayout.LINE_AXIS));
        ab7.setPreferredSize(new Dimension(400,60));
        ab7.setBackground(Color.white);
     //   ab7.add(newDevisBtn);
    //    center_right.add(ab7);
        
        JLabel jLabel14 = new javax.swing.JLabel();
        jLabel14.setPreferredSize(new Dimension(400, 60));
        ab5.add(newDevisBtn);
        ab5.add(jLabel14);
        ab5.add(new JButton("Valider la commande"));
        center_right.add(ab5);
        
        center_right.setPreferredSize(new Dimension(300,300));
        
        JPanel ab6 = new JPanel();
        ab6.setLayout(new BoxLayout(ab6, BoxLayout.LINE_AXIS));
        ab6.setPreferredSize(new Dimension(400,30));
        ab6.setBackground(Color.white);
        ab6.add(new JLabel("Avancement de la demande :"));
        center_right.add(ab6);
        center_right.setBackground(Color.white);
        
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(Integer.valueOf(dd.get(0).getDemandeetat()));
        progressBar.setString(dd.get(0).getDemandeetat() + "%");
        progressBar.setStringPainted(true);
        progressBar.setPreferredSize(new Dimension(200,20));
        JPanel ab8 = new JPanel();
        ab8.setLayout(new BoxLayout(ab8, BoxLayout.LINE_AXIS));
        ab8.setPreferredSize(new Dimension(400,40));
        ab8.setBackground(Color.white);
        ab8.add(progressBar);
        JLabel jLabel15 = new javax.swing.JLabel();
        jLabel15.setPreferredSize(new Dimension(200, 20));
        ab8.add(jLabel15);
        center_right.add(ab8);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        bottom_right.setBackground(Color.white);
        bottom_right.setLayout(new BorderLayout());
       bottom_right.add(ld.showtable(ModelesTables.DEVISLIST), BorderLayout.CENTER);
        bottom_right.setLayout(new GridBagLayout());
    //    bottom_right.add(ld.showtable(ModelesTables.DEVISLIST), BorderLayout.CENTER);
        
        Font f = new Font("Euphemia", Font.PLAIN, 14);
        JLabel comm = new JLabel("<html><b>Commentaires : </b><br />"+dd.get(0).getSuivdoscom()+"</html>");
        comm.setFont(f); 
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.weightx = 1.0;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        
         listeDmd.add(cp.showtable(ModelesTables.DEMANDELIST), BorderLayout.CENTER);
        content.add(listeDmd);
        
        
        
        bottom_right.add(comm);
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
