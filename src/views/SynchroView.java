/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.Synchro;
import controllers.UserActif;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sylv
 */
public class SynchroView extends KContainer {
    JLabel title = new JLabel ();
    public SynchroView(UserActif user) {
    super();
    this.user = user;
    initPanel();
    }

    @Override
    protected
    void initPanel() {
        JPanel content = new JPanel();
        content.setBackground(Color.white);
        content.setLayout(new BorderLayout());
        content.add(title, BorderLayout.NORTH);
        Synchro sync = new Synchro();
        if(sync.emptyFic() == true)
        {
            title.setText("<html><center<h2>Synchronisation de la base de données</h2><br><p color=green>Sélectionnez les actions à sauvegarder dans la base de données en ligne</p><center></html>");
        }
        else
        {
            title.setText("<html><center<h2>Synchronisation de la base de données</h2><br><p color=red>Vous n'avez pas d'action à sauvegarder</p><center></html>");
        }
        
        this.panel.add(content);
    }
}
