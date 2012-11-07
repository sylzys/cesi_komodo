/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModels;

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
import models.Nomenclaturelist;

/**
 *
 * @author sylv
 */
public class NomenclaturelistModel extends AbstractTableModel {

    private final String[] entetes =
    {
        "Libélé",  "Description", "Prix"
    };
    private List<Nomenclaturelist> nomenclatures;

    public NomenclaturelistModel(int id) {
        super();
        NomenclaturelistInstance NomclaturelisteInstance = NomenclaturelistInstance.getInstance();
        System.out.println("in model");
        Hashtable h = new Hashtable();
        h.put("devid", id);
        nomenclatures = NomclaturelisteInstance.GetNomenclatureslist("where devid = :devid", h);
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
                System.out.println("Libellé:" + nomenclatures.get(rowIndex).getNomlib());
                return nomenclatures.get(rowIndex).getNomlib();

            case 1:
                System.out.println("Etat:" + nomenclatures.get(rowIndex).getNomdes());
                return nomenclatures.get(rowIndex).getNomdes();
                
            case 2:
                System.out.println("prix:" + nomenclatures.get(rowIndex).getNomprix());
                return nomenclatures.get(rowIndex).getNomprix();

            case 999:
                return nomenclatures.get(rowIndex).getDevid();

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

    public List<Nomenclaturelist> getNomenclatureslist() {
        return nomenclatures;
    }
}
