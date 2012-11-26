/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModels;

import instances.ClientInstance;
import instances.DevisInstance;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Client;
import models.Devis;

/**
 *
 * @author sylv
 */
public class DevisModel extends AbstractTableModel {

    private final String[] entetes =
    {
        "N°", "Etat", "Date",  "Prix"
    };
    private List<Devis> devis;

    public DevisModel(int id) {
        super();
        DevisInstance DvsInstance = DevisInstance.getInstance();
        Hashtable h = new Hashtable();
        h.put("demandeid", id);
        devis = DvsInstance.Getdevis("where demandeid = :demandeid", h);
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
        return devis.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex)
        {

            case 0:
                return devis.get(rowIndex).getDevid();

            case 1:
                return devis.get(rowIndex).getDevetat();
                
            case 2:
                return devis.get(rowIndex).getDevdate();

            case 3:
                return devis.get(rowIndex).getDevprix();

            case 999:
                return devis.get(rowIndex).getDevid();

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

    public List<Devis> getDetaildevis() {
        return devis;
    }
}
