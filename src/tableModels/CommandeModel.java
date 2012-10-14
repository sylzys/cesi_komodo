/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModels;

import instances.ClientInstance;
import instances.CommandeInstance;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Client;
import models.Commande;

/**
 *
 * @author sylv
 */
public class CommandeModel extends AbstractTableModel {

    private final String[] entetes =
    {
        "Commande", "Date", "Créateur", "Etat"
    };
    private List<Commande> commandes;

    public CommandeModel() {
        super();
        CommandeInstance CmdInstance = CommandeInstance.getInstance();
        Hashtable h = new Hashtable();
        h.put("comid", 1);
        commandes = CmdInstance.GetCommandes("where comid = :comid", h);
    }

    @Override
    public int getColumnCount() {
        return entetes.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }

    @Override
    public int getRowCount() {
        return commandes.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex)
        {

            case 0:
                // Nom
                System.out.println("Desc:" + commandes.get(rowIndex).getComdesc());
                return commandes.get(rowIndex).getComdesc();

            case 1:
                System.out.println("Date:" + commandes.get(rowIndex).getComdate());
                return commandes.get(rowIndex).getComdate();

            case 2:
                System.out.println("Créateur:" + commandes.get(rowIndex).getComtitre());
                return commandes.get(rowIndex).getComtitre();

            case 3:
                System.out.println("Etat:" + commandes.get(rowIndex).getCometat());
                return commandes.get(rowIndex).getCometat();

            case 999:
                return commandes.get(rowIndex).getComid();

            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex)
        {

            case 0:
            case 3:
                return String.class;
            case 1:
                return Date.class;
            case 2:
                return Integer.class;
            default:
                return Object.class;
        }
    }

    public List<Commande> getCommandes() {
        return commandes;
    }
}
