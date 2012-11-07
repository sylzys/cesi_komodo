/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.UserActif;
import instances.HibernateConnection;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import models.Commande;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

/**
 *
 * @author sylv
 */
public class Recherche extends KContainer {

    JLabel title = new JLabel ("PANNEAU RECHERCHE");

    JTextField searchTextField ;

    JPanel panelListSearch;
     // creation des bouton radio
    JRadioButton rbCommande = new JRadioButton("Commande");
    
    JRadioButton rbSociete =new JRadioButton("Société");
    
    JRadioButton rbNomenclature = new JRadioButton("Nomenclature");
    
    JRadioButton rbDevis = new JRadioButton("Devis");
    
    JRadioButton rbContact = new JRadioButton("Contact");
    
    JRadioButton rbDemande = new JRadioButton("Demande");

    /**
     *
     * @param user
     */
    public Recherche(UserActif user) {
        super();
        this.user = user;

        this.user = user;
        try
        {
            Session session = HibernateConnection.getSession();
           //FullTextSession  fullTextSession = Search.getFullTextSession(session);
           //fullTextSession.createIndexer().startAndWait();

            //fullTextSession.close();
        }catch(Exception e)
        {
            System.out.println(e.toString());
        }

        initPanel();

    }

    /**
     *
     */
    @Override
    protected void initPanel() {

        JPanel global = new JPanel();
        global.setLayout(new FlowLayout());
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        //content.add(title, BorderLayout.CENTER);
        content.setBounds(0, 0, 500, 200);

        global.add(content);

        //Création du textfield
        searchTextField = new JTextField("Tapez un mot:");
        searchTextField.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    // sur l'évènement clique, je vide le champ texte 
                    searchTextField.setText("");
                }
            });
        //ajout au content
        content.add(searchTextField);
        
        content.add(rbCommande);
        content.add(rbContact);
        content.add(rbDemande);
        content.add(rbSociete);
        content.add(rbNomenclature);        
        content.add(rbDevis);

        // creation d'un group pour les radio bouton
        ButtonGroup group = new ButtonGroup();
        //ajout de chaque radio bouton dans le groupe
        group.add(rbCommande);
        group.add(rbDevis);
        group.add(rbContact);
        group.add(rbSociete);
        group.add(rbNomenclature);
        group.add(rbDemande);

        JButton btnSearch = new JButton("Rechercher");
        content.add(btnSearch);


        this.panel.add(global);
        this.panel.add(content);
        panelListSearch = new JPanel();
        this.panel.add(panelListSearch);
        // ajout au panel

        btnSearch.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                search(searchTextField.getText());
            }
        });
    }
    private void search(String query)
    {
        Session session = HibernateConnection.getSession();
        if(rbCommande.isSelected()) 
        {
            
            List<Commande>list = session.createSQLQuery("SELECT * From commande WHERE comtitre LIKE '%" +query+ "%'").addEntity(Commande.class).list();        
            
            Commande  com = null;
            for (int i = 0; i < list.size(); i++) {
                com = list.get(i);      
                
            }
        }
        if(rbDevis.isSelected())
        {
            List<models.Devis>list = session.createSQLQuery("SELECT * From devis WHERE devid LIKE '%" +query+ "%'").addEntity(models.Devis.class).list();        
        }
        if(rbContact.isSelected())
        {
            List<models.Client>list = session.createSQLQuery("SELECT * From client WHERE clinom LIKE '%" +query+ "%' OR cliville LIKE '%" +query+ "%' OR climail LIKE '%" +query+ "%'").addEntity(models.Devis.class).list();                    
        }
        if(rbSociete.isSelected())
        {
            //List<models.>list = session.createSQLQuery("SELECT * From devis WHERE comtitre LIKE '%" +query+ "%'").addEntity(models.Devis.class).list();                    
        }
        if(rbNomenclature.isSelected())
        {
            //List<models.>list = session.createSQLQuery("SELECT * From devis WHERE comtitre LIKE '%" +query+ "%'").addEntity(models.Devis.class).list();                    
        }
        if(rbDemande.isSelected())
        {
            //List<models.>list = session.createSQLQuery("SELECT * From devis WHERE comtitre LIKE '%" +query+ "%'").addEntity(models.Devis.class).list();                    
        }
    }

//    private List<Commande> search(String query)
//    {
//
//        Session session = HibernateConnection.getSession();
//        FullTextSession fullTextSession = Search.getFullTextSession(session);
//
//        QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(models.Commande.class).get();
//        org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().onFields("comtitre","comdesc").matching(query).createQuery();
//
//        // wrap Lucene query in a javax.persistence.Query
//        org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery,models.Commande.class);
//        List<Commande> commandes = fullTextQuery.list();
//        //fullTextSession.close();
//        return commandes;
//    }
//    private static void doIndex() throws InterruptedException {
//        Session session = HibernateConnection.getSession();
//
//        FullTextSession fullTextSession = Search.getFullTextSession(session);
//        fullTextSession.createIndexer().startAndWait();
//
//        //fullTextSession.close();
//    }
}
