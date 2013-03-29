/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;


import classes.SimpleBarChart;
import instances.ReportingInstance;
import instances.SatisfactionInstance;
import java.awt.Color;
import java.awt.Dimension;
import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import models.Client;
import models.DetailCommande;
import models.Satisfaction;

public class SatisfactionView extends JPanel {

    public SatisfactionView() {
        super();
    }

    public JPanel initPanel(Client cli) {
        SatisfactionInstance si = SatisfactionInstance.getInstance(); 
        Hashtable h = new Hashtable();
        h.put("cliid", cli.getCliid());
        System.out.println("  ->>"+cli.getCliid());
        List<Satisfaction> satisfactionReturn = si.GetSatisfactions("cliid=:cliid", h);
        if(satisfactionReturn.size() > 0){
            
         System.out.println(satisfactionReturn.get(0).getQ1());
        }
        JPanel suivi_satisfaction = new JPanel();
        suivi_satisfaction.setSize(820, 320);
       // suivi_satisfaction.setOpaque(false);
        
        double[] value = new double[5];
        String[] languages = new String[5];
        value[0] = 9;
        languages[0] = "Transports";
            value[1] = 7;
        languages[1] = "Service commercial";
        value[2] = 3;
        languages[2] = "QualitÃ©";
        value[3] = 2;
        languages[3] = "RapiditÃ©";
        value[4] = 5;
        languages[4] = "Satisfaction globale";
        SimpleBarChart test = new SimpleBarChart(value, languages, "Satisfaction client (derniÃ¨res commandes)");
        
        
        double[] value2 = new double[5];
        String[] languages2 = new String[5];
        value2[0] = 7;
        languages2[0] = "Transports";
            value2[1] = 7;
        languages2[1] = "Service commercial";
        value2[2] = 9;
        languages2[2] = "QualitÃ©";
        value2[3] = 10;
        languages2[3] = "RapiditÃ©";
        value2[4] = 8;
        languages2[4] = "Satisfaction globale";
        SimpleBarChart test2 = new SimpleBarChart(value2, languages2, "Satisfaction client (moyenne globale)");
        test.setSize(410, 300);
        test2.setSize(410, 300);
        test.setBackground(Color.white);
        test2.setBackground(Color.white);
        test.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
        test2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
        
        suivi_satisfaction.setLayout(new BoxLayout(suivi_satisfaction, BoxLayout.LINE_AXIS));
        suivi_satisfaction.add(Box.createRigidArea(new Dimension(2, 0)));
        suivi_satisfaction.add(test);
        suivi_satisfaction.add(Box.createRigidArea(new Dimension(10, 0)));
        suivi_satisfaction.add(test2);
        suivi_satisfaction.add(Box.createRigidArea(new Dimension(2, 0)));
        suivi_satisfaction.setBackground(Color.white);
        
        return suivi_satisfaction;
    }
}
