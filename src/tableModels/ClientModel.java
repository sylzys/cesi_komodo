/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModels;

import instances.ClientInstance;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Client;

/**
 *
 * @author sylv
 */
public class ClientModel extends AbstractTableModel {

    private final String[] entetes =
    {
        "Nom", "Créateur", "Date de création"
    };
private ClientInstance noteService;
    private List<Client> clients;

    public ClientModel() {
        super();
        ClientInstance CliInstance = ClientInstance.getInstance();
        clients = CliInstance.GetClients();
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
                System.out.println("Crétaeur:" + clients.get(rowIndex).getUti_utiid());
                return clients.get(rowIndex).getUti_utiid();

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

    public List<Client> getNotes() {
        return clients;
    }
}
