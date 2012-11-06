/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModels;

import instances.ClientInstance;
import instances.CommandeInstance;
import instances.DetailCdeInstance;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Client;
import models.Commande;
import models.DetailCommande;
import views.CommandeDetail;

/**
 *
 * @author sylv
 */
public class CommandeModel extends AbstractTableModel {
    
    List<DetailCommande> commands;
    
    //intitulé des colonnes
    private final String[] entetes =
    {
        "N°", "Titre", "Date", "Créateur", "Etat"
    };

    public CommandeModel() {
        super();
        System.out.println("default constructor");
    }
    
    public CommandeModel(Integer cli_id) {
        super();
        System.out.println("id_cli constructor");
        System.out.println("Recherche des commandes pour le client id : " + cli_id);
        DetailCdeInstance cmdInstance = DetailCdeInstance.getInstance();
        Hashtable h = new Hashtable();
        h.put("cliid", cli_id);
        this.commands = cmdInstance.GetDetailcde("cliid=:cliid", h);
    }
    
    public CommandeModel(List<DetailCommande> commands) {
        super();
        System.out.println("list constructor");
        this.commands = commands;
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
        return commands.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex)
        {
            //définit quel attribut de l'objet est affiché à la colonne d'index X 
            case 0:
                // Id / numero
                System.out.println("Id:" + commands.get(rowIndex).getComid());
                return commands.get(rowIndex).getComid();

            case 1:
                System.out.println("Titre:" + commands.get(rowIndex).getComtitre());
                return commands.get(rowIndex).getComtitre();

            case 2:
                System.out.println("Date:" + commands.get(rowIndex).getComdate());
                return commands.get(rowIndex).getComdate();

            case 3:
                System.out.println("Créateur:" + commands.get(rowIndex).getUtiprenom() + " " + commands.get(rowIndex).getUtinom());
                return commands.get(rowIndex).getUtiprenom() + " " + commands.get(rowIndex).getUtinom();
            case 4:
                System.out.println("Etat:" + commands.get(rowIndex).getCometat());
                return commands.get(rowIndex).getCometat();
                    
            //spécificité de l'index 999: sert à définir ce qui est envoyé lors du double click sur une ligne du tableau
            case 999:
                return commands.get(rowIndex).getComid();

            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    //indique le type d'objet selon chaque index de colonne défini au dessus
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
                return String.class;
            case 4:
                return Integer.class;
            default:
                return Object.class;
        }
    }

    public List<DetailCommande> getCommandes() {
        return commands;
    }
}
