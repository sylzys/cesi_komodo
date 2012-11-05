/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.TableDispatcher;
import controllers.UserActif;
import instances.DetailDmdInstance;
import instances.DevisInstance;
import instances.HibernateConnection;
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
import models.Client;
import models.DetailDmd;
import models.ModelesTables;
import models.CurrentDatas;
import models.Devis;
import org.hibernate.Query;
import org.hibernate.Transaction;

/**
 *
 * @author sylv
 */
public class DevisDetail extends KContainer {

    List<Devis> devis;
    int devis_id;
    int demandeId;
    private Fenetre fen = Fenetre.getInstance();
    JLabel title = new JLabel(), statut_lbl, prix_lbl, date_lbl;
    JButton validate = new JButton();
    JButton refuse = new JButton();
    JButton Retour = new JButton();
    public UserActif user;
    public int idDemande;

    public DevisDetail(int id) {
        super();
        devis_id = id;
        //CurrentDatas cur = CurrentDatas.getInstance();
        //cur.setSoc_id(demande_id);
        initPanel();
    }

    // @Override
    protected void initPanel() {
        JPanel content = new JPanel(),
                listeDmd = new JPanel(),
                listeDevis = new JPanel(),
                detailDmd = new JPanel(),
                top = new JPanel(),
                middle = new JPanel(),
                bottom = new JPanel(),
                container = new JPanel(),
                center_right = new JPanel(),
                bottom_right = new JPanel(),
                bottom_bottom = new JPanel();

        content.setLayout(new FlowLayout());
        content.setPreferredSize(new Dimension(1000, 750));
        container.setPreferredSize(new Dimension(700, 500));

        //  System.out.println("CMD-ID: "+this.demande_id);
        //title.setText("DETAIL COMMANDE "+ this.demande_id);
        TableDispatcher nom_tblDis = new TableDispatcher();
        /**
         * fixer la largeur de la première colonne à 200 pixels
         */
        content.setBackground(Color.white);

        top.setPreferredSize(new Dimension(700, 100));
        middle.setPreferredSize(new Dimension(700, 350));
        bottom.setPreferredSize(new Dimension(700, 50));

        listeDmd.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        listeDevis.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

        top.setLayout(new BoxLayout(top, BoxLayout.LINE_AXIS));
        middle.setLayout(new BoxLayout(middle, BoxLayout.LINE_AXIS));
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.LINE_AXIS));


        Devis dvis = null;
        HibernateConnection connection = HibernateConnection.getInstance();
        try {
            Query query = connection.getSession().createQuery("from Devis where devid = :devid");
            query.setParameter("devid", devis_id);
            //  query.setParameter("utiid", this.user.getId());
            dvis = (Devis) query.uniqueResult();

            System.out.println("Devis USED : " + devis_id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



        String etat = dvis.getDevetat();
        title.setText("<html><h1>Détails du devis n°" + dvis.getDevid() + " <br /></h1>Statut du devis : " + etat + " <br />Montant total : " + dvis.getDevprix() + "€<br /> Créé le : " + dvis.getDevdate() + "</html>");

        refuse.setText("Refus du devis");
        validate.setText("Validation du devis");
        Retour.setText("Retour à la demande");
        refuse.addActionListener(new DevisDetail.refuseListener());
        validate.addActionListener(new DevisDetail.validateListener());
        Retour.addActionListener(new DevisDetail.RetourListener());

        top.add(title, BorderLayout.CENTER);

        if (etat.equals("En cours")) {
            bottom.add(validate, BorderLayout.CENTER);
            bottom.add(refuse, BorderLayout.CENTER);
        } else {
            bottom.add(new JLabel("Ce devis a été " + dvis.getDevetat()), BorderLayout.CENTER);
        }
        bottom.add(Retour, BorderLayout.CENTER);
        idDemande = dvis.getDeviid();

        container.add(top);
        container.add((middle));
        container.add((bottom));
        content.add(container);
        this.panel.add(content);
    }

    private class validateListener implements ActionListener {

        public validateListener() {
        }

        //    @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("update devis pour devis n° :" + devis_id + "valeur : valider");
            Transaction tx = HibernateConnection.getSession().beginTransaction();
            System.out.println("Updating Record");
            Devis dvs = new Devis();
            dvs.setDevid(devis_id);
            dvs.setDevetat("Accepté");
            HibernateConnection.getSession().update(dvs);
            tx.commit();
            System.out.println("Done");
        }
    }

    private class refuseListener implements ActionListener {

        public refuseListener() {
        }

        //    @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("update devis pour devis n° :" + devis_id + " valeur : refuser");
            Transaction tx = HibernateConnection.getSession().beginTransaction();
            System.out.println("Updating Record");
            Devis dvs = new Devis();
            dvs.setDevetat("Refusé");
            HibernateConnection.getSession().update(dvs);
            tx.commit();
            System.out.println("Done");
        }
    }

    private class RetourListener implements ActionListener {

        public RetourListener() {
        }

        //       @Override
        public void actionPerformed(ActionEvent e) {
            CurrentDatas cur = CurrentDatas.getInstance();
            ClientDetail cd = new ClientDetail(cur.getSoc_id());
            DemandeDetail devisForm = new DemandeDetail(idDemande);
            fen.RenewContener(devisForm.getPanel());
        }
    }
}
