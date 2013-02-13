/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModels;

import instances.ClientInstance;
import instances.NomenclatureInstance;
import java.util.Hashtable;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Client;
import models.Nomenclature;

/**
 *
 * @author sylv
 */
public class NomenclatureModel extends AbstractTableModel {

    private final String[] entetes =
    {
        "Libellé", "Prix", "Chaines", "Temps"
    };
    private List<Nomenclature> demandes;

    public NomenclatureModel(int id) {
        super();
        NomenclatureInstance DmdInstance = NomenclatureInstance.getInstance();
        Hashtable h = new Hashtable();
        h.put("nomid", id);
        demandes = DmdInstance.GetNomenclatures("where nomid = :nomid", h);
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
                return demandes.get(rowIndex).getNomlib();

            case 1:
                return demandes.get(rowIndex).getNomprix();

            case 2:
                return demandes.get(rowIndex).getNomnbchaine();
            case 3:
                return demandes.get(rowIndex).getNomtemps();

            case 999:
                return demandes.get(rowIndex).getNomid();
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex)
        {

            case 1:
                return String.class;
            case 0:
                return Integer.class;
            case 2:
                return Integer.class;
            case 3:
                return Integer.class;
            case 999:
                return Integer.class;
            default:
                return Object.class;
        }
    }

    public List<Nomenclature> getNomenclatures() {
        return demandes;
    }
}
