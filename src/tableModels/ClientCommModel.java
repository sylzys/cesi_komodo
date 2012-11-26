/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModels;

import instances.ClientInstance;
import instances.clientCommInstance;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Client;
import models.Client_Comm;

/**
 *
 * @author sylv
 */
public class ClientCommModel extends AbstractTableModel {

    private final String[] entetes =
    {
        "Nom", "Créateur", "Date de création"
    };
    
    private List<Client_Comm> clients;


    public ClientCommModel(int id) {
        super();
        clientCommInstance CliCommInstance = clientCommInstance.getInstance();
        Hashtable h = new Hashtable();
//        h.put("uti_utiid", id);
//        clients = CliCommInstance.GetClients("where uti_utiid = :uti_utiid", h);
        h.put("utiid", id);
        h.put("clisuppr", false);
        clients = CliCommInstance.GetClients(" where utiid = :utiid and clisuppr = :clisuppr", h);
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
        return clients.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex)
        {

            case 0:
                // Nom
                return clients.get(rowIndex).getClinom();

            case 1:
                return clients.get(rowIndex).getUtiprenom() + " "+ clients.get(rowIndex).getUtinom() ;

            case 2:
                return clients.get(rowIndex).getClidteadd();
                
            case 999:
                return clients.get(rowIndex).getCliid();
                
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex)
        {

            case 0:
            case 1:
                return String.class;
            case 2:
                return Date.class;
            default:
                return Object.class;
        }
    }

    public List<Client_Comm> getClients() {
        return clients;
    }
}
