/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.UserActif;
import instances.HibernateConnection;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
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
    public Recherche(UserActif user) {
    super();
    this.user = user;
    
      this.user = user;
        try
        {
            Session session = HibernateConnection.getSession();
            FullTextSession  fullTextSession = Search.getFullTextSession(session);
            fullTextSession.createIndexer().startAndWait();

            fullTextSession.close();
        }catch(Exception e)
        {
            System.out.println(e.toString());
        }

        initPanel();
        
    }

    @Override
    protected
    void initPanel() {
        ;
        
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(title, BorderLayout.CENTER);      
        this.panel.add(content);
        //Création du textfield
        JTextField searchTextField = new JTextField("Tapez un mot:");
        //ajout au panel 
        this.panel.add(searchTextField);
        
        // creation des bouton radio 
        JRadioButton rbCommande = new JRadioButton("Commande");
        JRadioButton rbSociete =new JRadioButton("Société");
        JRadioButton rbNomenclature = new JRadioButton("Nomenclature");
        JRadioButton rbDevis = new JRadioButton("Devis");
        JRadioButton rbContact = new JRadioButton("Contact");
        JRadioButton rbDemande = new JRadioButton("Demande");
        //rbDemande.
        
        // ajout au panel 
        this.panel.add(rbCommande);
        this.panel.add(rbSociete);
        this.panel.add(rbNomenclature);
        this.panel.add(rbContact);
        this.panel.add(rbDevis);
        this.panel.add(rbDemande);
        
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
        btnSearch.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                  Session session = HibernateConnection.getSession();
            FullTextSession fullTextSession = Search.getFullTextSession(session);

            QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(models.Commande.class).get();
            org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().onFields("comtitre","comdesc").matching("plop").createQuery();

            // wrap Lucene query in a javax.persistence.Query
            org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery,models.Commande.class);
         
            }            
        });
        this.panel.add(btnSearch);
    }
}
