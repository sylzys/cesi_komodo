/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.Synchro;
import controllers.TableDispatcher;
import controllers.UserActif;
import instances.HibernateConnection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import models.ModelesTables;
import models.CurrentDatas;
import models.Demande;
import models.Devis;
import models.ParamSync;
import org.hibernate.Query;
import org.hibernate.Session;
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
    JLabel jLabel6 = new javax.swing.JLabel();
    JLabel jLabel7 = new javax.swing.JLabel();
    JLabel jLabel8 = new javax.swing.JLabel();
    JLabel jLabel9 = new javax.swing.JLabel();
    public UserActif user;
    public int idDemande;

    public DevisDetail(int id) {
        super();
        devis_id = id;
        initPanel();
    }

    // @Override
    protected void initPanel() {
        JPanel content = new JPanel(),
                top = new JPanel(),
                middle = new JPanel(),
                bottom = new JPanel(),
                container = new JPanel(),
                sousTitre = new JPanel();
        JLabel statut = new JLabel();
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        container.setPreferredSize(new Dimension(702, 451));

        content.setBackground(Color.white);
        container.setBackground(Color.white);
        top.setBackground(Color.white);
        middle.setBackground(Color.white);
        bottom.setBackground(Color.white);
        sousTitre.setBackground(Color.white);

        Font f = new Font("Euphemia", Font.PLAIN, 22);
        Font g = new Font("Euphemia", Font.PLAIN, 11);
        Font h = new Font("Euphemia", Font.PLAIN, 16);
        title.setFont(f);
        jLabel7.setFont(h);
        jLabel8.setFont(g);
        statut.setFont(h);
                
        top.setPreferredSize(new Dimension(700, 200));
        middle.setPreferredSize(new Dimension(300, 170));
        bottom.setPreferredSize(new Dimension(500, 30));
        sousTitre.setPreferredSize(new Dimension(700, 235));
        title.setPreferredSize(new Dimension(400, 25));
        jLabel7.setPreferredSize(new Dimension(200, 25));
        jLabel8.setPreferredSize(new Dimension(600, 10));
        statut.setPreferredSize(new Dimension(500, 165));
        
        jLabel8.setBorder(new EmptyBorder(0, 0, 0, 100));
        container.setBorder(new EmptyBorder(150, 0, 0, 0));
        statut.setBorder(new EmptyBorder(0, 0, 0, 200));
        bottom.setBorder(new EmptyBorder(0, 0, 0, 200));
                
        top.setLayout(new BoxLayout(top, BoxLayout.LINE_AXIS));
        middle.setLayout(new BoxLayout(middle, BoxLayout.LINE_AXIS));
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.LINE_AXIS));


        Devis dvis = null;
        HibernateConnection connection = HibernateConnection.getInstance();
        Query query = connection.getSession().createQuery("from Devis where devid = :devid");
        query.setParameter("devid", devis_id);
        dvis = (Devis) query.uniqueResult();


        String etat = dvis.getDevetat();
        title.setText("Détails du devis n°" + dvis.getDevid());
        jLabel7.setText("Montant total : " + dvis.getDevprix() + "€");
        jLabel8.setText("Créé le : " + dvis.getDevdate() + " Titre : " + dvis.getDevtitre() + " Description : " + dvis.getDevdesc());
        refuse.setText("Refus du devis");
        validate.setText("Validation du devis");
        Retour.setText("Retour à la demande");
        refuse.addActionListener(new DevisDetail.refuseListener());
        validate.addActionListener(new DevisDetail.validateListener());
        Retour.addActionListener(new DevisDetail.RetourListener());

        sousTitre.add(title, BorderLayout.WEST);
        sousTitre.add(jLabel7, BorderLayout.EAST);
        sousTitre.add(jLabel8, BorderLayout.CENTER);

        if (etat.equals("En cours")) {
            bottom.add(validate, BorderLayout.CENTER);
            bottom.add(refuse, BorderLayout.CENTER);
            statut.setText("Ce devis est en attente de validation");
            sousTitre.add(statut, BorderLayout.CENTER);
        } else {
            statut.setText("Ce devis a été " + dvis.getDevetat());
            sousTitre.add(statut, BorderLayout.CENTER);
        }
        top.add(sousTitre);
        
        
        
        
        
        TableDispatcher ld = new TableDispatcher();
        ld.setBackground(Color.white);
        ld.setPreferredSize(new Dimension(290, 140));
        middle.add(ld.showtable(ModelesTables.NOMENCLATURELIST, dvis.getDevid()), BorderLayout.CENTER);

        bottom.add(Retour, BorderLayout.CENTER);
        idDemande = dvis.getDemandeid();

        container.add(top);
        container.add((middle));
        container.add((bottom));
        container.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
        content.add(container);
        this.panel.add(content);
    }

    private class validateListener implements ActionListener {

        public validateListener() {
        }

        //    @Override
        public void actionPerformed(ActionEvent e) {

            
            Transaction tx3 = HibernateConnection.getSession().beginTransaction();
            Session session = HibernateConnection.getSession();
            Devis dvs = (Devis) session.get(Devis.class, devis_id);
            dvs.setDevid(devis_id);
            dvs.setDevetat("Accepté");
            dvs.setDemandeid(idDemande);
            session.update(dvs);
            tx3.commit();
            if (HibernateConnection.online == false)
            {
                ParamSync param = new ParamSync();
                param.setClinom(dvs.getInterid(),"inter");
                if(dvs.isDevsuppr() == true)
                {
                    param.setType("Suppression");
                }
                else
                {
                    param.setType("Mise à jour");   
                }
                Synchro sync = new Synchro();
                sync.objSerializable(dvs, param);
            } 
            Transaction tx4 = HibernateConnection.getSession().beginTransaction();
            Session session2 = HibernateConnection.getSession();
            Demande dmdd = (Demande) session2.get(Demande.class, idDemande);
            dmdd.setDemandeid(idDemande);
            dmdd.setDemandeetat(80);
            session2.update(dmdd);
            tx4.commit();
            if (HibernateConnection.online == false)
            {
                ParamSync param = new ParamSync();
                param.setClinom(dmdd.getInterid(),"inter");
                if(dmdd.getDemandesuppr() == true)
                {
                    param.setType("Suppression");
                }
                else
                {
                    param.setType("Mise à jour");   
                }
                Synchro sync = new Synchro();
                sync.objSerializable(dmdd, param);
            } 
            DemandeDetail devisForm = new DemandeDetail(idDemande);
            HibernateConnection.newConnect(HibernateConnection.online);
            fen.RenewContener(devisForm.getPanel());
        }
    }

    private class refuseListener implements ActionListener {

        public refuseListener() {
        }

        //    @Override
        public void actionPerformed(ActionEvent e) {
            Transaction tx3 = HibernateConnection.getSession().beginTransaction();
            Session session = HibernateConnection.getSession();
            Devis dvs = (Devis) session.get(Devis.class, devis_id);
            dvs.setDevid(devis_id);
            dvs.setDevetat("Refusé");
            session.update(dvs);
            tx3.commit();
            if (HibernateConnection.online == false)
            {
                ParamSync param = new ParamSync();
                param.setClinom(dvs.getInterid(),"inter");
                if(dvs.isDevsuppr() == true)
                {
                    param.setType("Suppression");
                }
                else
                {
                    param.setType("Mise à jour");   
                }
                Synchro sync = new Synchro();
                sync.objSerializable(dvs, param);
            }
            DemandeDetail devisForm = new DemandeDetail(idDemande);
            fen.RenewContener(devisForm.getPanel());
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
