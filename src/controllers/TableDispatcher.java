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
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import models.ModelesTables;
import tableModels.ClientModel;
import tableModels.CommandeModel;
import views.ClientDetail;
import views.CommandeDetail;
import views.Logout;

public class TableDispatcher extends JPanel {

    ModelesTables modeleTable;

    public TableDispatcher() {
    }
    private TableModel model;

    public TableDispatcher(ModelesTables mt) {
        super(new GridLayout(1, 0));
        modeleTable = mt;
        //get table model
        System.out.println("MODELE UTILISE = " + mt);
        switch (mt)
        {
            case CLIENT:
                model = new ClientModel();
                break;
            case COMMANDE:
                model = new CommandeModel();
                break;
        }

        final JTable table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);
        table.setPreferredScrollableViewportSize(new Dimension(500, 270));
        table.setFillsViewportHeight(true);
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2)
                {
                    JTable target = (JTable) e.getSource();

                    Point p = e.getPoint();

                    int row = target.rowAtPoint(p);
                    int column = target.convertColumnIndexToModel(target.columnAtPoint(p));
                    TableModel tm = table.getModel();
                    Object value = tm.getValueAt(row, 999); //999 = id dans le model
                    System.out.println("Selection : " + value);
                    System.out.println("DOUBLE CLICKED " + row + "/" + column + "->" + value);
                    switch (modeleTable)
                    {
                        case CLIENT:
                            ClientDetail cd = new ClientDetail((Integer) tm.getValueAt(row, 999));
                            break;
                        case COMMANDE:
                            CommandeDetail cmd = new CommandeDetail((Integer) tm.getValueAt(row, 999));
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
    private JPanel createAndShowGUI(ModelesTables mt) {
        //Create and set up the window.
        JPanel frame = new JPanel();
        //rame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        TableDispatcher cp = new TableDispatcher(mt);
        cp.setOpaque(true); //content panes must be opaque
        frame.add(cp);

        //Display the window.
        // frame.pack();
//        frame.setVisible(true);
        return (frame);
    }

    public JPanel showtable(ModelesTables mt) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                this.modelesTables = mt;
//                System.out.println("====>"+this.modelesTables);
        return createAndShowGUI(mt);
//            }
//        });
    }
}