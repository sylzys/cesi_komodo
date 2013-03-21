/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;


import javax.swing.JLabel;
import javax.swing.JPanel;
import models.Client;

public class SatisfactionView extends JPanel {

    private JPanel pnlcontent = new JPanel();

    public SatisfactionView() {
        super();
    }

    public JPanel initPanel(Client cli) {
        JLabel suivi_satisfaction = new JLabel();
        suivi_satisfaction.setText("hello world");
        pnlcontent.add(suivi_satisfaction);
        return pnlcontent;
    }
}
