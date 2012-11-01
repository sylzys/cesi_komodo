/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModels;

import instances.ClientInstance;
import instances.DetailDmdInstance;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Client;
import models.DetailDmd;

/**
 *
 * @author sylv
 */
public class DemandelistModel extends AbstractTableModel {

    private final String[] entetes =
    {
        "Id Demande", "Créateur", "Date",  "Etat"
    };
    private List<DetailDmd> demandes;

    public DemandelistModel() {
        super();
        DetailDmdInstance DmdInstance = DetailDmdInstance.getInstance();
        System.out.println("in model");
        Hashtable h = new Hashtable();
        h.put("cliid", 1);
        demandes = DmdInstance.GetDetaildemande("where cliid = :cliid", h);
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
                return demandes.get(rowIndex).getDemandeid();

            case 1:
                return demandes.get(rowIndex).getUtiprenom() + " " + demandes.get(rowIndex).getUtinom();
                
            case 2:
                return demandes.get(rowIndex).getSuivdosdate();

            case 3:
                return demandes.get(rowIndex).getDemandeetat();

            case 999:
                return demandes.get(rowIndex).getCliid();

            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex)
        {

            case 0:
                return Integer.class;
            
            case 1:
                return String.class;
            case 2:
                return Date.class;
            case 3:
                return Integer.class;
            case 999:
                return Integer.class;
            default:
                return Object.class;
        }
    }

    public List<DetailDmd> getDetailDmd() {
        return demandes;
    }
}
