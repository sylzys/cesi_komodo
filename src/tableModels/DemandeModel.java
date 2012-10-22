/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModels;

import instances.ClientInstance;
import instances.DemandeInstance;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Client;
import models.Demande;

/**
 *
 * @author sylv
 */
public class DemandeModel extends AbstractTableModel {

    private final String[] entetes =
    {
        "Date",  "Etat"
    };
    private List<Demande> demandes;

    public DemandeModel() {
        super();
        DemandeInstance DmdInstance = DemandeInstance.getInstance();
        System.out.println("in model");
        Hashtable h = new Hashtable();
        h.put("demandeid", 1);
        demandes = DmdInstance.GetDemandes("where demandeid = :demandeid", h);
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
        return demandes.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex)
        {

            case 0:
                System.out.println("Date:" + demandes.get(rowIndex).getDemandedteadd());
                return demandes.get(rowIndex).getDemandedteadd();

            case 1:
                System.out.println("Etat:" + demandes.get(rowIndex).getDemandeetat());
                return demandes.get(rowIndex).getDemandeetat();

            case 999:
                return demandes.get(rowIndex).getDemandeid();

            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex)
        {

            case 0:
                return Date.class;
            
            case 1:
                return Integer.class;
            default:
                return Object.class;
        }
    }

    public List<Demande> getDemandes() {
        return demandes;
    }
}
