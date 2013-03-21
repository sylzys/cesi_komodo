/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;


import classes.SimpleBarChart;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import models.Client;

public class SatisfactionView extends JPanel {

    public SatisfactionView() {
        super();
    }

    public JPanel initPanel(Client cli) {
        JPanel suivi_satisfaction = new JPanel();
        suivi_satisfaction.setSize(820, 320);
        
        
        double[] value = new double[5];
        String[] languages = new String[5];
        value[0] = 9;
        languages[0] = "Transports";
            value[1] = 7;
        languages[1] = "Service commercial";
        value[2] = 3;
        languages[2] = "Qualité";
        value[3] = 2;
        languages[3] = "Rapidité";
        value[4] = 5;
        languages[4] = "Satisfaction globale";
        SimpleBarChart test = new SimpleBarChart(value, languages, "Satisfaction client (dernières commandes)");
        
        
        double[] value2 = new double[5];
        String[] languages2 = new String[5];
        value2[0] = 7;
        languages2[0] = "Transports";
            value2[1] = 7;
        languages2[1] = "Service commercial";
        value2[2] = 9;
        languages2[2] = "Qualité";
        value2[3] = 10;
        languages2[3] = "Rapidité";
        value2[4] = 8;
        languages2[4] = "Satisfaction globale";
        SimpleBarChart test2 = new SimpleBarChart(value2, languages2, "Satisfaction client (moyenne)");
        test.setSize(410, 300);
        test2.setSize(410, 300);
        test.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
        test2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
        
        suivi_satisfaction.setLayout(new BoxLayout(suivi_satisfaction, BoxLayout.LINE_AXIS));
        suivi_satisfaction.add(test);
        suivi_satisfaction.add(Box.createRigidArea(new Dimension(10, 0)));
        suivi_satisfaction.add(test2);
        
        return suivi_satisfaction;
    }
}
