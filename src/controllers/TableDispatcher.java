package controllers;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import models.CurrentDatas;
import models.Demande;
import models.Devis;
import models.ModelesTables;
import tableModels.AlerteClientModel;
import tableModels.AllnomenclaturelistModel;
import tableModels.ClientCommModel;
import tableModels.CommandeModel;
import tableModels.DemandeModel;
import tableModels.DemandelistModel;
import tableModels.DevisModel;
import tableModels.NomenclatureModel;
import tableModels.NomenclaturelistModel;
import views.ClientDetail;
import views.CommandeDetail;
import views.Createdevis;
import views.DemandeDetail;
import views.DevisDetail;
import views.Fenetre;
import views.Logout;

public class TableDispatcher extends JPanel {

    ModelesTables modeleTable;

    public TableDispatcher() {
    }
    private TableModel model;

    public TableDispatcher(ModelesTables mt, int id) {
        super(new GridLayout(1, 0));
        modeleTable = mt;
        //selon la constante, on prépare le modèle qu'on utilisera pour mapper à la table
        //cf les declarations de constantes dans ModelesTables.java
        switch (mt)
        {
            case CLIENT:
                model = new ClientCommModel(id);
              //  model = new ClientModel(id);
                break;
            case COMMANDE:
                model = new CommandeModel(id);
                break;
            case DEMANDE:
                model = new DemandeModel(id);
                break;
            case DEVIS:
                model = new DevisModel(id);
                break;
            case DEMANDELIST:
                model = new DemandelistModel(id);
                break;
            case NOMENCLATURELIST:
                model = new NomenclaturelistModel(id);
                break;
            case ALLNOMENCLATURELIST:
                model = new AllnomenclaturelistModel();
                break;
            case ALERTE:
                model = new AlerteClientModel(id);
                break;
            case NOMENCLATURE:
                model = new NomenclatureModel(id);
                break;
            
        }

        //on lie notre table à notre modèle générique
        final JTable table = new JTable(model);
        //tri des colonnes
        table.setAutoCreateRowSorter(true);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);
        table.setPreferredScrollableViewportSize(new Dimension(500, 270));
        table.setFillsViewportHeight(true);
        
        switch (mt)
        {
            case CLIENT:
                break;
            case COMMANDE:
                break;
            case DEMANDE:
                break;
            case NOMENCLATURE:
                break;
            case DEVIS:
                // Desactive la taille automatique des colonnes
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                // Fixe la dimension du tableau
                table.setPreferredScrollableViewportSize(new Dimension(300, 150));
                // on definit l'index de la colonne et la largeur sohaitée
                int colIndex = 0;
                int colWidth = 40;
                TableColumn col = table.getColumnModel().getColumn(colIndex);         
                col.setPreferredWidth(colWidth);
                int colIndex2 = 1;
                int colWidth2 = 100;
                TableColumn col2 = table.getColumnModel().getColumn(colIndex2);         
                col2.setPreferredWidth(colWidth2);
                int colIndex3 = 2;
                int colWidth3 = 100;
                TableColumn col3 = table.getColumnModel().getColumn(colIndex3);         
                col3.setPreferredWidth(colWidth3);
                int colIndex4 = 3;
                int colWidth4 = 60;
                TableColumn col4 = table.getColumnModel().getColumn(colIndex4);         
                col4.setPreferredWidth(colWidth4);
                break;
            case DEMANDELIST:
                // Desactive la taille automatique des colonnes
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                // Fixe la dimension du tableau
                table.setPreferredScrollableViewportSize(new Dimension(240, 550));
                // on definit l'index de la colonne et la largeur sohaitée
                 colIndex = 0;
                 colWidth = 80;
                 col = table.getColumnModel().getColumn(colIndex);         
                col.setPreferredWidth(colWidth);
                 colIndex2 = 1;
                 colWidth2 = 120;
                 col2 = table.getColumnModel().getColumn(colIndex2);         
                col2.setPreferredWidth(colWidth2);
                 colIndex3 = 2;
                 colWidth3 = 40;
                 col3 = table.getColumnModel().getColumn(colIndex3);         
                col3.setPreferredWidth(colWidth3);
                 
                break;
                
           case NOMENCLATURELIST:
                // Desactive la taille automatique des colonnes
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                // Fixe la dimension du tableau
                table.setPreferredScrollableViewportSize(new Dimension(280, 140));
                // on definit l'index de la colonne et la largeur sohaitée
                 colIndex = 0;
                 colWidth = 80;
                 col = table.getColumnModel().getColumn(colIndex);         
                col.setPreferredWidth(colWidth);
                 colIndex2 = 1;
                 colWidth2 = 120;
                 col2 = table.getColumnModel().getColumn(colIndex2);         
                col2.setPreferredWidth(colWidth2);
                 colIndex3 = 2;
                 colWidth3 = 40;
                 col3 = table.getColumnModel().getColumn(colIndex3);         
                col3.setPreferredWidth(colWidth3);
                 colIndex4 = 3;
                 colWidth4 = 40;
                 col4 = table.getColumnModel().getColumn(colIndex4);         
                col4.setPreferredWidth(colWidth4);
                 
                break;     
           case ALLNOMENCLATURELIST:
                // Desactive la taille automatique des colonnes
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                // Fixe la dimension du tableau
                table.setPreferredScrollableViewportSize(new Dimension(280, 140));
                // on definit l'index de la colonne et la largeur sohaitée
                 colIndex = 0;
                 colWidth = 80;
                 col = table.getColumnModel().getColumn(colIndex);         
                col.setPreferredWidth(colWidth);
                 colIndex2 = 1;
                 colWidth2 = 120;
                 col2 = table.getColumnModel().getColumn(colIndex2);         
                col2.setPreferredWidth(colWidth2);
                 colIndex3 = 2;
                 colWidth3 = 40;
                 col3 = table.getColumnModel().getColumn(colIndex3);         
                col3.setPreferredWidth(colWidth3);
                 colIndex4 = 3;
                 colWidth4 = 40;
                 col4 = table.getColumnModel().getColumn(colIndex4);         
                col4.setPreferredWidth(colWidth4);
                 
                break;
           case ALERTE:
               // Desactive la taille automatique des colonnes
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                // Fixe la dimension du tableau
                table.setPreferredScrollableViewportSize(new Dimension(800, 110));
                // on definit l'index de la colonne et la largeur sohaitée
                colIndex = 0;
                colWidth = 150;
                col = table.getColumnModel().getColumn(colIndex);         
                col.setPreferredWidth(colWidth);
                colIndex2 = 1;
                colWidth2 = 125;
                col2 = table.getColumnModel().getColumn(colIndex2);         
                col2.setPreferredWidth(colWidth2);
                colIndex3 = 2;
                colWidth3 = 80;
                col3 = table.getColumnModel().getColumn(colIndex3);         
                col3.setPreferredWidth(colWidth3);
                colIndex4 = 3;
                colWidth4 = 445;
                col4 = table.getColumnModel().getColumn(colIndex4);         
                col4.setPreferredWidth(colWidth4);
                break;
            
        }
        
        //gestion du double click
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2)
                {
                    JTable target = (JTable) e.getSource();

                    Point p = e.getPoint();

                    int row = target.rowAtPoint(p);
                    int column = target.convertColumnIndexToModel(target.columnAtPoint(p));
                    TableModel tm = table.getModel();
                    //on renvoie la valeur liée à l'id 999
                    Object value = tm.getValueAt(row, 999); //999 = id dans le model
                    switch (modeleTable)
                    {
                        //on récupère la valeur correspondant au bon modèle
                        case CLIENT:
                            CurrentDatas cur = CurrentDatas.getInstance();
                            cur.setSoc_id((Integer) tm.getValueAt(row, 999));
                            ClientDetail cd = new ClientDetail((Integer) tm.getValueAt(row, 999));
                            break;
                        case COMMANDE:
                            Fenetre.getInstance().RenewCmd((Integer) tm.getValueAt(row, 999));
                            break;
                        case DEMANDE:
                            DemandeDetail dmd = new DemandeDetail((Integer) tm.getValueAt(row, 999));
                            break;
                        case DEMANDELIST:
                            DemandeDetail demande = new DemandeDetail((Integer) tm.getValueAt(row, 999));
                            Fenetre fen = Fenetre.getInstance();
                            fen.RenewContener(demande.getPanel());
                        //    DemandeDetail dmdlist = new DemandeDetail((Integer) tm.getValueAt(row, 999));
                            break;
                        case DEVIS:
                            DevisDetail devislist = new DevisDetail((Integer) tm.getValueAt(row, 999));
                            Fenetre fen2 = Fenetre.getInstance();
                            fen2.RenewContener(devislist.getPanel());
                            break;
                        case NOMENCLATURELIST:
                            break;
                        case NOMENCLATURE:
                            break;
                        case ALERTE:
                            Fenetre.getInstance().RenewCmd((Integer) tm.getValueAt(row, 999));
                            break;
              }
          }
        }
      });
      JScrollPane scrollPane = new JScrollPane(table);

       add(scrollPane);
    }

    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    private JPanel createAndShowGUI(ModelesTables mt, int id) {
        //Create and set up the window.
        JPanel frame = new JPanel();
        //rame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        TableDispatcher cp = new TableDispatcher(mt, id);
        
        cp.setOpaque(true); //content panes must be opaque
        frame.add(cp);

        //on renvoie la frame contenant la table
        return (frame);
    }

    public JPanel showtable(ModelesTables mt, int id) {
       return createAndShowGUI(mt, id);

    }
}
