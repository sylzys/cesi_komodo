/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModels;

import instances.AllnomenclaturelistInstance;
import instances.ClientInstance;
import instances.DemandeInstance;
import instances.NomenclaturelistInstance;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Client;
import models.Demande;
import models.Nomenclature;
import models.Nomenclature;

/**
 *
 * @author sylv
 */
public class AllnomenclaturelistModel extends AbstractTableModel {

    private final String[] entetes =
    {
        "Libélé",  "Description", "Prix"
    };
    private List<Nomenclature> nomenclatures;

    public AllnomenclaturelistModel() {
        super();
        AllnomenclaturelistInstance AllnomclaturelisteInstance = AllnomenclaturelistInstance.getInstance();
        nomenclatures = AllnomclaturelisteInstance.GetNomenclatures();
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
        return nomenclatures.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex)
        {

            case 0:
                return nomenclatures.get(rowIndex).getNomlib();

            case 1:
                return nomenclatures.get(rowIndex).getNomdes();
                
            case 2:
                return nomenclatures.get(rowIndex).getNomprix();

            case 999:
                return nomenclatures.get(rowIndex).getNomid();

            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex)
        {

            case 0:
                return String.class;
            
            case 1:
                return String.class;
            case 2:
                return Integer.class;
            default:
                return Object.class;
        }
    }

    public List<Nomenclature> getNomenclatureslist() {
        return nomenclatures;
    }
}
