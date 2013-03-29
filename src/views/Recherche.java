/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import classes.ButtonData;
import classes.MyPdf;
import controllers.UserActif;
import instances.HibernateConnection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import models.Client;
import models.Commande;
import models.Demande;
import models.DetailDevis;
import models.Devis;
import models.Matiere;
import models.Nomenclature;
import models.Nommat;
import org.hibernate.Session;

/**
 *
 * @author sylv
 */
public class Recherche extends KContainer {

    JLabel title = new JLabel ("PANNEAU RECHERCHE");

    JTextField searchTextField ;

    JPanel panelListSearch;
    
    JScrollPane scroller;
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
        global.setBackground(new java.awt.Color(255, 255, 255));
        global.setBorder(javax.swing.BorderFactory.createTitledBorder("Recherche"));
        
        JPanel header = new JPanel();
        header.setBackground(new java.awt.Color(255, 255, 255));
        header.setLayout(new BorderLayout());
        header.setPreferredSize(new Dimension(800,25));
       
        
        scroller = new JScrollPane();
        //scroller.setBorder(javax.swing.BorderFactory.createTitledBorder("Alertes"));
        scroller.setPreferredSize(new Dimension(750, 550));
        scroller.setBackground(new java.awt.Color(255, 255, 255));
        scroller.getVerticalScrollBar().setUnitIncrement(10);
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
        groupRb.setBackground(new java.awt.Color(255, 255, 255));
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

        
        JPanel panelInter = new JPanel();
        panelInter.setBackground(new java.awt.Color(255, 255, 255));
        panelInter.setPreferredSize(new Dimension(120,20));
        
        JButton btnSearch = new JButton("Rechercher");
        btnSearch.setPreferredSize(new Dimension(115,20));
        //add(btnSearch,BorderLayout.);
        panelInter.add(btnSearch);
        header.add(panelInter,BorderLayout.EAST);
        header.add(groupRb,BorderLayout.WEST);
        header.setBackground(new java.awt.Color(255, 255, 255));
        //header.add(btnSearch,BorderLayout.EAST);
        
        //content.add(header,BorderLayout.NORTH);
        
        panelListSearch = new JPanel();         
        panelListSearch.setLayout(new BoxLayout(panelListSearch, BoxLayout.Y_AXIS));
        panelListSearch.setPreferredSize(new Dimension(750,540));
        panelListSearch.setBackground(new java.awt.Color(255, 255, 255));
        panelListSearch.add(new JLabel(""));
        
        //scroller.add(panelListSearch);
        scroller.setPreferredSize(new Dimension(750,540));
        scroller.setViewportView(panelListSearch);
        global.add(scroller,BorderLayout.CENTER);

        // ajout au panel principal
        this.panel.setBackground(new java.awt.Color(255, 255, 255));
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
            query = query.toUpperCase();
            Session session = HibernateConnection.getSession();
            if(rbCommande.isSelected()) 
            {           
                List<Commande>list = session.createSQLQuery("SELECT * From commande WHERE upper(comtitre) LIKE '%" +query+ "%'").addEntity(Commande.class).list();                    
                Commande  com = null;
                JPanel panelCom = null;
                for (int i = 0; i < list.size(); i++) {
                    flag = true;
                    com = list.get(i);      
                    panelCom = new JPanel();
                    panelCom.add(new JLabel("Commande n°"+ com.getComid()+""));               
                    //panelCom.setBackground(Color.WHITE);
                    panelCom.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
                    panelCom.setPreferredSize(new Dimension(700,100));
                    JLabel comLabel = new JLabel();
                    comLabel.setText("Titre : " + com.getComtitre());
                    panelCom.add(comLabel);
                    ButtonData btnGoToCmd = new ButtonData("Voir");
                    btnGoToCmd.setId(com.getComid());
                    btnGoToCmd.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            ButtonData btn_temp = (ButtonData)arg0.getSource();
                            Fenetre.getInstance().RenewCmd(btn_temp.getId());
                        }
                    });
                    panelCom.add(btnGoToCmd);
                    JPanel box = new JPanel();
                    box.setPreferredSize(new Dimension(750,10));
                    box.setBackground(new java.awt.Color(255, 255, 255));
                    panelListSearch.add(panelCom,BorderLayout.SOUTH);
                    panelListSearch.add(box,BorderLayout.SOUTH);
                }
                Fenetre.getInstance().RenewContener(panel);
            }
            if(rbDevis.isSelected())
            {
                List<models.Devis>list = session.createSQLQuery("SELECT * From devis WHERE upper(devtitre) LIKE '%" +query+ "%'").addEntity(models.Devis.class).list();        
                models.Devis devis = null;
                JPanel panelDevis = null;
                for (int i = 0; i < list.size(); i++) {
                    flag = true;
                    devis = list.get(i);
                    panelDevis = new JPanel();
                    panelDevis.add(new JLabel("Devis n°"+ devis.getDevid()+""));               
                   // panelDevis.setBackground(Color.WHITE);
                    panelDevis.setBorder(javax.swing.BorderFactory.createEtchedBorder());
                    panelDevis.setPreferredSize(new Dimension(750,100));
                    JLabel nomLabel = new JLabel();
                    nomLabel.setText("Prix: " + devis.getDevprix());
                    panelDevis.add(nomLabel);
                    ButtonData btnGoToCmd = new ButtonData("Voir");
                    btnGoToCmd.setId(devis.getDevid());
                    btnGoToCmd.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            ButtonData btn_temp = (ButtonData)arg0.getSource();
                            //Fenetre.getInstance().RenewDetailsDevis(btn_temp.getId());
                            makePdfDevis(Integer.toString(btn_temp.getId()));
                        }
                    });
                    panelDevis.add(btnGoToCmd);
                    JPanel box = new JPanel();
                    box.setPreferredSize(new Dimension(750,10));
                    box.setBackground(new java.awt.Color(255, 255, 255));
                    panelListSearch.add(panelDevis,BorderLayout.SOUTH);
                    panelListSearch.add(box,BorderLayout.SOUTH);                   
                }
                Fenetre.getInstance().RenewContener(panel);
            }
            if(rbContact.isSelected())
            {
                List<models.Client>list = session.createSQLQuery("SELECT * From client WHERE upper(clinom) LIKE '%" +query+ "%' OR upper(cliville) LIKE '%" +query+ "%' OR upper(climail) LIKE '%" +query+ "%';").addEntity(models.Client.class).list();                    
                Client client = null;
                JPanel panelClient = null;
                for (int i = 0; i < list.size(); i++) {
                    flag = true;
                    client = list.get(i);
                    panelClient = new JPanel();
                    panelClient.add(new JLabel("Client n°"+ client.getCliid()+""));               
                   // panelClient.setBackground(Color.WHITE);
                    panelClient.setPreferredSize(new Dimension(750,100));
                    JLabel nomLabel = new JLabel();
                    nomLabel.setText("Nom: " + client.getClinom());
                    panelClient.add(nomLabel);
                    ButtonData btnGoToCmd = new ButtonData("Voir");
                    btnGoToCmd.setId(client.getCliid());
                    btnGoToCmd.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            ButtonData btn_temp = (ButtonData)arg0.getSource();
                            Fenetre.getInstance().RenewClientdDetail(btn_temp.getId());
                        }
                    });
                    panelClient.add(btnGoToCmd);
                    JPanel box = new JPanel();
                    box.setPreferredSize(new Dimension(750,10));
                    box.setBackground(new java.awt.Color(255, 255, 255));
                    panelListSearch.add(panelClient,BorderLayout.SOUTH);
                    panelListSearch.add(box,BorderLayout.SOUTH);             
                }
                Fenetre.getInstance().RenewContener(panel);
            }
            if(rbSociete.isSelected())
            {
                List<models.Client>list = session.createSQLQuery("SELECT * From client WHERE upper(clinom) LIKE '%" +query+ "%' OR upper(cliville) LIKE '%" +query+ "%' OR upper(climail) LIKE '%" +query+ "%';").addEntity(models.Client.class).list();                    
                 Client client = null;
                JPanel panelClient = null;
                for (int i = 0; i < list.size(); i++) {
                    flag = true;
                    client = list.get(i);
                    panelClient = new JPanel();
                    panelClient.add(new JLabel("Client n°"+ client.getCliid()+""));               
                    //panelClient.setBackground(Color.WHITE);
                    panelClient.setPreferredSize(new Dimension(750,100));
                    JLabel nomLabel = new JLabel();
                    nomLabel.setText("Nom: " + client.getClinom());
                    panelClient.add(nomLabel);
                    ButtonData btnGoToCmd = new ButtonData("Voir");
                    btnGoToCmd.setId(client.getCliid());
                    btnGoToCmd.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            ButtonData btn_temp = (ButtonData)arg0.getSource();
                            Fenetre.getInstance().RenewClientdDetail(btn_temp.getId());
                        }
                    });                    
                    panelClient.add(btnGoToCmd);
                    JPanel box = new JPanel();
                    box.setPreferredSize(new Dimension(750,10));
                    box.setBackground(new java.awt.Color(255, 255, 255));
                    panelListSearch.add(panelClient,BorderLayout.SOUTH);
                    panelListSearch.add(box,BorderLayout.SOUTH);       
                }
            }
            if(rbNomenclature.isSelected())
            {
                //il faut remplir les nomnbchaine avec des valeurs sinon il y a une erreur
                List<Nomenclature>list = session.createSQLQuery("SELECT * From nomenclature WHERE upper(nomlib) LIKE '%" +query+ "%'").addEntity(Nomenclature.class).list();                    
                Nomenclature nomen = null;
                JPanel panelNom = null;
                for (int i = 0; i < list.size(); i++) {
                    flag = true;
                    nomen = list.get(i);
                    panelNom = new JPanel();
                    panelNom.add(new JLabel("Nomclature n°"+ nomen.getNomid()+""));               
                    //panelNom.setBackground(Color.WHITE);
                    panelNom.setPreferredSize(new Dimension(750,100));
                    JLabel nomLabel = new JLabel();
                    nomLabel.setText("Description: " + nomen.getNomdes());
                    panelNom.add(nomLabel);
                    ButtonData btnGoToCmd = new ButtonData("Voir");
                    btnGoToCmd.setId(nomen.getNomid());
                    btnGoToCmd.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            ButtonData btn_temp = (ButtonData)arg0.getSource();
                            btn_temp.getId();
                            //Fenetre.getInstance().RenewCmd(btn_temp.getId());
                            makePdfNomenclature(Integer.toString(btn_temp.getId()));
                        }
                    });
                    panelNom.add(btnGoToCmd);
                    JPanel box = new JPanel();
                    box.setPreferredSize(new Dimension(750,10));
                    box.setBackground(new java.awt.Color(255, 255, 255));
                    panelListSearch.add(panelNom,BorderLayout.SOUTH);
                    panelListSearch.add(box,BorderLayout.SOUTH);            
                }
                Fenetre.getInstance().RenewContener(panel);
            }
            if(rbDemande.isSelected())
            {
                List<models.Demande>list = session.createSQLQuery("SELECT * From demande WHERE upper(demandetitre) LIKE '%" +query+ "%'").addEntity(models.Demande.class).list();                    
                Demande demande = null;
                JPanel panelDem = null;
                for (int i = 0; i < list.size(); i++) {
                    flag = true;
                    demande = list.get(i);
                    panelDem = new JPanel();
                    panelDem.add(new JLabel("Demande n°"+ demande.getDemandeid()+""));               
                    //panelDem.setBackground(Color.WHITE);
                    panelDem.setPreferredSize(new Dimension(750,100));
                    JLabel nomLabel = new JLabel();
                    nomLabel.setText("Titre" + demande.getDemandetitre());
                    panelDem.add(nomLabel);
                    ButtonData btnGoToCmd = new ButtonData("Voir");
                    btnGoToCmd.setId(demande.getDemandeid());
                    btnGoToCmd.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            ButtonData btn_temp = (ButtonData)arg0.getSource();
                           // Fenetre.getInstance().RenewAlert(btn_temp.getId());
                           Fenetre.getInstance().RenewDemandeDetail(btn_temp.getId());
                        }
                    });
                    panelDem.add(btnGoToCmd);
                     JPanel box = new JPanel();
                    box.setPreferredSize(new Dimension(750,10));
                    box.setBackground(new java.awt.Color(255, 255, 255));
                    panelListSearch.add(panelDem,BorderLayout.SOUTH);
                    panelListSearch.add(box,BorderLayout.SOUTH);            
                }
                Fenetre.getInstance().RenewContener(panel);
            }

            if(flag == false)
            {
                panelListSearch.add(new JLabel("Aucun résultat"),BorderLayout.CENTER);
            }
        }
        else
        {
            panelListSearch.add(new JLabel("Veuillez saisir un mot pour la recherche"),BorderLayout.CENTER);
        }
        scroller.setViewportView(panelListSearch);
        Fenetre.getInstance().RenewContener(panel);      
    }
    
    
    private void makePdfDevis(String id)
    {
        Session session = HibernateConnection.getSession();        
        List<Devis> list = session.createSQLQuery("SELECT * FROM devis WHERE devid = " +id + ";" ).addEntity(Devis.class).list();
        
        Devis devis = (Devis)list.get(0);
        MyPdf mypdf = new MyPdf(Integer.toString(devis.getDevid()));
        try
        {
            mypdf.addTitlePage(devis.getDevdate(),Integer.toString(devis.getDevid()),devis.getDevdesc());	
            mypdf.createQrcode(Integer.toString(devis.getDevid()));            
            mypdf.addRow("Prix : " + devis.getDevid());
            mypdf.document.close();
            
        }
        catch(Exception e)
        {
            e.getStackTrace();
        }
        
        savePdf(mypdf.getFILE());
        
    }
    private void makePdfNomenclature(String id)
    {
    
        Session session = HibernateConnection.getSession();        
        List<Nomenclature>list = session.createSQLQuery("SELECT * From nomenclature WHERE nomid ="+id+ ";").addEntity(Nomenclature.class).list();               
        Nomenclature nomen = list.get(0);
        List<Nommat>listNomMat = session.createSQLQuery("SELECT * From nommat WHERE nomid ="+id+ " ").addEntity(Nommat.class).list();
        
         
        int i = 0;
        Nommat nommat;
        List<Matiere> listMatiere;
        Matiere mat;
        String[][]data = new String[listNomMat.size()][3];
        for(i =0;i< listNomMat.size();i++)
        {
            nommat = listNomMat.get(i);
            listMatiere =  session.createSQLQuery("SELECT * From matiere WHERE matid ="+id+ " ").addEntity(Matiere.class).list();
            mat = listMatiere.get(0);
            data[i][0] = Integer.toString(mat.getMatid());
            data[i][1] = mat.getMatlib();
            data[i][2] = Integer.toString(nommat.getMatqte());                   
        }
        
        MyPdf mypdf = new MyPdf(Integer.toString(nomen.getNomid()));
        try
        {
            mypdf.addTitlePage(nomen.getNomdate(),Integer.toString(nomen.getNomid()),nomen.getNomdes());	
            mypdf.createQrcode(Integer.toString(nomen.getNomid()));
            mypdf.addTable(data);
            mypdf.addRow("Prix : " + nomen.getNomprix());
            mypdf.document.close();
            
        }
        catch(Exception e)
        {
            e.getStackTrace();
        }
        
        savePdf(mypdf.getFILE());
       
        
        
        
    }
    
    private void savePdf(String path)
    {
         File file = new File(path); 
        if (Desktop.isDesktopSupported())
        {
            Desktop desktop = Desktop.getDesktop();
             try
                {
                    desktop.open(file);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

        }
        else
            System.err.println("Desktop not supported");
    }
}
