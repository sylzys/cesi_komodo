/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import classes.LinkLabelData;
import controllers.Synchro;
import controllers.UserActif;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.StringTokenizer;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sylv
 */
public class SynchroView extends KContainer {
    private String req;
    private String fic;
    private String libreq;
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
        JPanel center = new JPanel();
        JPanel titre = new JPanel();
        JPanel pnlbtn = new JPanel();
        JCheckBox chkreq = new JCheckBox();
        JButton button = new JButton("Synchroniser"); 
        JLabel lblSynchro = new JLabel(new ImageIcon("ressources/images/synchro.png"));
        Synchro sync = new Synchro();        
        titre.setBackground(Color.white);
        titre.add(title, BorderLayout.CENTER);
        content.setBackground(Color.white);
        content.setLayout(new BorderLayout());
        if(sync.emptyFic() == true)
        {      
            fic = sync.readFic();
            StringTokenizer strtok = new StringTokenizer(fic, "||");
            while (strtok.hasMoreTokens()) {
                req = strtok.nextToken();
                String[] inter = req.split("interlocuteur:");
                int interid = Integer.parseInt(inter[1]);
                String[] action = sync.readReq(req, interid);
                
                System.out.println(action[0]);
                System.out.println(action[1]);
                System.out.println(action[2] + "-----");
            }        
            title.setPreferredSize(new Dimension(420,100)); 
            title.setText("<html><center<h2>Synchronisation de la base de données</h2><br><p color=green>Sélectionnez "
                    + "les actions à sauvegarder dans la base de données en ligne</p><center></html>");
            lblSynchro.setVisible(false);
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            button.setPreferredSize(new Dimension(120,35));
            center.setPreferredSize(new Dimension(800, 500));
            center.setBackground(Color.white);
            center.setLayout(new BorderLayout());
            center.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
            center.add(chkreq,BorderLayout.CENTER);
            pnlbtn.setBackground(Color.white);
            pnlbtn.add(button, BorderLayout.CENTER);
            pnlbtn.add(lblSynchro, BorderLayout.EAST);
            content.add(titre, BorderLayout.NORTH);
            content.add(center,BorderLayout.CENTER);  
            content.add(pnlbtn, BorderLayout.SOUTH);
        }
        else
        {
            title.setPreferredSize(new Dimension(350,100));
            title.setText("<html><center<h2>Synchronisation de la base de données</h2><br><p color=red>"
                    + "Vous n'avez auncune action à sauvegarder</p><center></html>");
            content.add(titre, BorderLayout.NORTH);
        } 

        this.panel.add(content);
    }
}
