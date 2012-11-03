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
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
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
                listeDevis = new JPanel(),
                detailDmd = new JPanel(),
                left = new JPanel(),
                right = new JPanel(),
                top_right = new JPanel(),
                centertop_right = new JPanel(),
                center_right = new JPanel(),
                bottom_right = new JPanel(),
                bottom_bottom = new JPanel();
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
        detailDmd.setPreferredSize(new Dimension(650, 750));
        detailDmd.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Color.LIGHT_GRAY));
        //  System.out.println("CMD-ID: "+this.demande_id);
        //title.setText("DETAIL COMMANDE "+ this.demande_id);
        TableDispatcher cp = new TableDispatcher();
        TableDispatcher ld = new TableDispatcher();
        /**
         * fixer la largeur de la première colonne à 200 pixels
         */
        
        content.setBackground(Color.white);
        top_right.setBackground(Color.white);
        center_right.setBackground(Color.white);
        listeDmd.setBackground(Color.white);
        listeDevis.setBackground(Color.white);
        right.setBackground(Color.white);
        bottom_right.setBackground(Color.white);
        detailDmd.setBackground(Color.white);
        bottom_bottom.setBackground(Color.white);
        
        listeDevis.setPreferredSize(new Dimension(330, 260));
        bottom_bottom.setPreferredSize(new Dimension(600, 300));
        
        
        
        
        listeDmd.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        listeDevis.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        
        listeDmd.setLayout(new BoxLayout(listeDmd, BoxLayout.LINE_AXIS));
        top_right.setLayout(new FlowLayout());
        right.setLayout(new BoxLayout(right, BoxLayout.LINE_AXIS));
        
        
        detailDmd.setLayout(new BoxLayout(detailDmd, BoxLayout.Y_AXIS));
        listeDevis.setLayout(new BorderLayout());
        bottom_bottom.setLayout(new BoxLayout(bottom_bottom, BoxLayout.LINE_AXIS));
        
        
        //get cmd-detail from DB
        DetailDmdInstance DetailDdeInstance = DetailDmdInstance.getInstance();
        Hashtable h = new Hashtable();
        h.put("demandeid", demande_id);
        dd = DetailDdeInstance.GetDetaildemande("demandeid",demande_id, h);

        jLabel5.setText("<html><h1><b>> Demande n°" + dd.get(0).getDemandeid() + "</b></h1><small>Créee par " + dd.get(0).getUtiprenom() + " " + dd.get(0).getUtinom() + " le " +dd.get(0).getSuivdosdate() + "</small></html>");
        jLabel5.setPreferredSize(new Dimension(450, 100));
        top_right.add(jLabel5);
        JButton retour = new JButton("Retour à la société");
        retour.addActionListener(new DemandeDetail.RetourListenerDmd());
        top_right.add(retour);
        JPanel ab5 = new JPanel();
        ab5.setLayout(new BoxLayout(ab5, BoxLayout.LINE_AXIS));
        ab5.setPreferredSize(new Dimension(600,30));
        ab5.setBackground(Color.white);
        
        newDevisBtn.setText("Créer un devis");
        JPanel ab7 = new JPanel();
        ab7.setLayout(new BoxLayout(ab7, BoxLayout.LINE_AXIS));
        ab7.setPreferredSize(new Dimension(600,60));
        ab7.setBackground(Color.white);
        
        JLabel jLabel14 = new javax.swing.JLabel();
        jLabel14.setPreferredSize(new Dimension(600, 60));
        ab5.add(newDevisBtn);
        ab5.add(jLabel14);
        ab5.add(new JButton("Valider la commande"));
        center_right.add(ab5);
        
        center_right.setPreferredSize(new Dimension(500,150));
        
        JPanel ab6 = new JPanel();
        ab6.setLayout(new BoxLayout(ab6, BoxLayout.LINE_AXIS));
        ab6.setPreferredSize(new Dimension(600,30));
        ab6.setBackground(Color.white);
        ab6.add(new JLabel("Avancement de la demande :"));
        center_right.add(ab6);
        
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(Integer.valueOf(dd.get(0).getDemandeetat()));
        progressBar.setString(dd.get(0).getDemandeetat() + "%");
        progressBar.setStringPainted(true);
        progressBar.setPreferredSize(new Dimension(200,20));
        JPanel ab8 = new JPanel();
        ab8.setLayout(new BoxLayout(ab8, BoxLayout.LINE_AXIS));
        ab8.setPreferredSize(new Dimension(600,40));
        ab8.setBackground(Color.white);
        ab8.add(progressBar);
        JLabel jLabel15 = new javax.swing.JLabel();
        jLabel15.setPreferredSize(new Dimension(400, 20));
        ab8.add(jLabel15);
        center_right.add(ab8);
        
        
        
        
        Font f = new Font("Euphemia", Font.PLAIN, 14);
        JLabel comm = new JLabel("<html><b>Commentaires : </b><br />"+dd.get(0).getSuivdoscom()+"</html>");
        comm.setFont(f); 
        comm.setVerticalTextPosition(0);
        comm.setPreferredSize(new Dimension(600, 200));
        
        centertop_right.setBackground(Color.white);
        ld.setPreferredSize(new Dimension(320, 240));
         listeDevis.setPreferredSize(new Dimension(320, 240));
        listeDevis.add(ld.showtable(ModelesTables.DEVIS,dd.get(0).getDemandeid()), BorderLayout.CENTER);
         listeDmd.add(cp.showtable(ModelesTables.DEMANDELIST,dd.get(0).getCliid()), BorderLayout.CENTER);
        right.setLayout(new BorderLayout());
        
        right.setPreferredSize(new Dimension(600, 250));
        centertop_right.setPreferredSize(new Dimension(320, 240));
        centertop_right.add(listeDevis);
        right.add(centertop_right);
        
        bottom_right.setPreferredSize(new Dimension(600, 200));
        bottom_right.add(comm, BorderLayout.NORTH);
        
        detailDmd.add(top_right);
        detailDmd.add((center_right));
        detailDmd.add(right);
        detailDmd.add((bottom_right));
        detailDmd.add((bottom_bottom));
        content.add(listeDmd);
        content.add(detailDmd);
        this.panel.add(content);
    }

    private static class RetourListenerDmd implements ActionListener {

        public RetourListenerDmd() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            CurrentDatas cur = CurrentDatas.getInstance();
            ClientDetail cd = new ClientDetail(cur.getSoc_id());
        }
    }
}
