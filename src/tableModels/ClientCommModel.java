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
import models.ClientComm;

/**
 *
 * @author sylv
 */
public class ClientCommModel extends AbstractTableModel {

    private final String[] entetes =
    {
        "Nom", "Créateur", "Date de création"
    };
    
    private List<ClientComm> clients;

    public ClientCommModel() {
        super();
        clientCommInstance CliCommInstance = clientCommInstance.getInstance();
        Hashtable h = new Hashtable();
        h.put("utiid", 1);
        clients = CliCommInstance.GetClients("where utiid = :utiid", h);
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
                System.out.println("Nom:" + clients.get(rowIndex).getClinom());
                return clients.get(rowIndex).getClinom();

            case 1:
                System.out.println("Créateur:" + clients.get(rowIndex).getUtinom());
                return clients.get(rowIndex).getUtiprenom() + " "+ clients.get(rowIndex).getUtinom() ;

            case 2:
                System.out.println("Date:" + clients.get(rowIndex).getClidteadd());
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

    public List<ClientComm> getClients() {
        return clients;
    }
}
