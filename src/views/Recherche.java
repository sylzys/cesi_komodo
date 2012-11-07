/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import classes.ButtonData;
import controllers.UserActif;
import instances.HibernateConnection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import models.Client;
import models.Commande;
import models.Nomenclature;
import org.hibernate.Session;

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
        global.setLayout(new BorderLayout());     
        
        JPanel header = new JPanel();
        header.setLayout(new BorderLayout());
        header.setPreferredSize(new Dimension(800,50));
        //content.setLayout(new BorderLayout());
        //content.add(title, BorderLayout.CENTER);
        //content.setPreferredSize(new Dimension(500,100));

        global.add(header,BorderLayout.NORTH);

        //Création du textfield
        searchTextField = new JTextField("Tapez un mot:");
        searchTextField.setPreferredSize(new Dimension(100,20));
        searchTextField.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    // sur l'évènement clique, je vide le champ texte 
                    searchTextField.setText("");
                }
            });
        //ajout au content
       // content.add(searchTextField);
        
        JPanel groupRb = new JPanel();
        groupRb.setPreferredSize(new Dimension(700,50));
        groupRb.add(searchTextField,BorderLayout.CENTER);
        groupRb.add(rbCommande,BorderLayout.CENTER);
        groupRb.add(rbContact,BorderLayout.CENTER);
        groupRb.add(rbDemande,BorderLayout.CENTER);
        groupRb.add(rbSociete,BorderLayout.CENTER);
        groupRb.add(rbNomenclature,BorderLayout.CENTER);        
        groupRb.add(rbDevis);
        

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
        btnSearch.setPreferredSize(new Dimension(120,20));
        //add(btnSearch,BorderLayout.);
        header.add(groupRb,BorderLayout.WEST);
        header.add(btnSearch,BorderLayout.EAST);
        
        //content.add(header,BorderLayout.NORTH);
        
        panelListSearch = new JPanel();        
        panelListSearch.add(new JLabel(""));
        panelListSearch.setPreferredSize(new Dimension(800,500));
        
        global.add(panelListSearch,BorderLayout.CENTER);

        // ajout au panel principal
        this.panel.add(global);      


        btnSearch.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                search(searchTextField.getText());
            }
        });
    }
    private void search(String query)
    {
        panelListSearch.removeAll();
        panelListSearch.revalidate();        
        if(!query.isEmpty())
        {
            Boolean flag = false;
            
            Session session = HibernateConnection.getSession();
            if(rbCommande.isSelected()) 
            {           
                List<Commande>list = session.createSQLQuery("SELECT * From commande WHERE comtitre LIKE '%" +query+ "%'").addEntity(Commande.class).list();                    
                Commande  com = null;
                JPanel panelCom = null;
                for (int i = 0; i < list.size(); i++) {
                    flag = true;
                    com = list.get(i);      
                    panelCom = new JPanel();
                    panelCom.add(new JLabel("Commande n°"+ com.getComid()+""));               
                    panelCom.setBackground(Color.WHITE);
                    panelCom.setPreferredSize(new Dimension(750,100));
                    JLabel comLabel = new JLabel();
                    comLabel.setText("Titre : " + com.getComtitre());
                    panelCom.add(comLabel);
                    ButtonData btnGoToCmd = new ButtonData("Aller");
                    btnGoToCmd.setId(com.getComid());
                    btnGoToCmd.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            ButtonData btn_temp = (ButtonData)arg0.getSource();
                            Fenetre.getInstance().RenewCmd(btn_temp.getId());
                        }
                    });
                    panelCom.add(btnGoToCmd);
                    panelListSearch.add(panelCom,BorderLayout.CENTER);
                }
                Fenetre.getInstance().RenewContener(panel);
            }
            if(rbDevis.isSelected())
            {
                List<models.Devis>list = session.createSQLQuery("SELECT * From devis WHERE devid LIKE '%" +query+ "%'").addEntity(models.Devis.class).list();        
            }
            if(rbContact.isSelected())
            {
                List<models.Client>list = session.createSQLQuery("SELECT * From client WHERE clinom LIKE '%" +query+ "%' OR cliville LIKE '%" +query+ "%' OR climail LIKE '%" +query+ "%';").addEntity(models.Client.class).list();                    
                Client client = null;
                JPanel panelClient = null;
                for (int i = 0; i < list.size(); i++) {
                    flag = true;
                    client = list.get(i);
                    panelClient = new JPanel();
                    panelClient.add(new JLabel("Client n°"+ client.getCliid()+""));               
                    panelClient.setBackground(Color.WHITE);
                    panelClient.setPreferredSize(new Dimension(750,100));
                    JLabel nomLabel = new JLabel();
                    nomLabel.setText("Nom: " + client.getClinom());
                    panelClient.add(nomLabel);
                    ButtonData btnGoToCmd = new ButtonData("Aller");
                    btnGoToCmd.setId(client.getCliid());
                    btnGoToCmd.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            ButtonData btn_temp = (ButtonData)arg0.getSource();
                            Fenetre.getInstance().RenewClientdDetail(btn_temp.getId());
                        }
                    });
                    panelClient.add(btnGoToCmd);
                    panelListSearch.add(panelClient,BorderLayout.CENTER);             
                }
                Fenetre.getInstance().RenewContener(panel);
            }
            if(rbSociete.isSelected())
            {
                //List<models.>list = session.createSQLQuery("SELECT * From devis WHERE comtitre LIKE '%" +query+ "%'").addEntity(models.Devis.class).list();                    
            }
            if(rbNomenclature.isSelected())
            {
                //il faut remplir les nomnbchaine avec des valeurs sinon il y a une erreur
                List<Nomenclature>list = session.createSQLQuery("SELECT * From nomenclature WHERE nomlib LIKE '%" +query+ "%'").addEntity(Nomenclature.class).list();                    
                Nomenclature nomen = null;
                JPanel panelNom = null;
                for (int i = 0; i < list.size(); i++) {
                    flag = true;
                    nomen = list.get(i);
                    panelNom = new JPanel();
                    panelNom.add(new JLabel("Nomclature n°"+ nomen.getNomid()+""));               
                    panelNom.setBackground(Color.WHITE);
                    panelNom.setPreferredSize(new Dimension(750,100));
                    JLabel nomLabel = new JLabel();
                    nomLabel.setText("Description: " + nomen.getNomdes());
                    panelNom.add(nomLabel);
                    ButtonData btnGoToCmd = new ButtonData("Aller");
                    btnGoToCmd.setId(nomen.getNomid());
                    btnGoToCmd.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            ButtonData btn_temp = (ButtonData)arg0.getSource();
                            Fenetre.getInstance().RenewCmd(btn_temp.getId());
                        }
                    });
                    panelNom.add(btnGoToCmd);
                    panelListSearch.add(panelNom,BorderLayout.CENTER);             
                }
                Fenetre.getInstance().RenewContener(panel);
            }
            if(rbDemande.isSelected())
            {
                //List<models.>list = session.createSQLQuery("SELECT * From devis WHERE comtitre LIKE '%" +query+ "%'").addEntity(models.Devis.class).list();                    
            }

            if(flag == false)
            {
                panelListSearch.add(new JLabel("Aucun résultats"),BorderLayout.CENTER);
            }
        }
        else
        {
            panelListSearch.add(new JLabel("Veuillez saisir un mot pour la recherche"),BorderLayout.CENTER);
        }
        Fenetre.getInstance().RenewContener(panel);
      
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
