package tableModels;

import instances.AlerteInstance;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.GetAlerte;


/**
 *
 * @author BoBo
 */
public class AlerteClientModel extends AbstractTableModel {

    private final String[] entetes =
    {
        "Interlocuteur", "Commande", "Date", "Commentaire"
    };
    
    private List<GetAlerte> alertes;
    private AlerteInstance IAlerte = AlerteInstance.getInstance();


    public AlerteClientModel(int id) {
        super();
        String where = "cliid = :cliid";
        Hashtable h = new Hashtable();
        h.put("cliid", id);
        alertes = IAlerte.GetAlertes(where, h);
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
        return alertes.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex)
        {
            case 0:
                // Interlocuteur
                return (alertes.get(rowIndex).getInterprenom() + " " + alertes.get(rowIndex).getInternom());
            case 1:
                // commandes
                return ("Commande n. " + alertes.get(rowIndex).getComid());
            case 2:
                // date
                return alertes.get(rowIndex).getSuivdosdate().toString();
            case 3:
                // commentaire
                return alertes.get(rowIndex).getSuivdoscom();
            case 999:
                // id commande
                return alertes.get(rowIndex).getComid();
                
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
                return String.class;
            case 3:
                return String.class;
            default:
                return Object.class;
        }
    }

    public List<GetAlerte> getClients() {
        return alertes;
    }
}
