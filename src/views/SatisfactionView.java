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
        System.out.println("  ->>" + cli.getCliid());

        List<Satisfaction> satisfactionReturn = si.slctbdd(cli.getCliid());
        JPanel suivi_satisfaction = new JPanel();
        suivi_satisfaction.setSize(820, 320);
        // suivi_satisfaction.setOpaque(false);

        double[] value = new double[5];
        String[] languages = new String[5];
        if (satisfactionReturn.size() > 0) {
            System.out.println(satisfactionReturn.get(0).getQ1());
            value[0] = satisfactionReturn.get(0).getQ1();
            languages[0] = "Transports";
            value[1] = satisfactionReturn.get(0).getQ2();
            languages[1] = "Service commercial";
            value[2] = satisfactionReturn.get(0).getQ3();
            languages[2] = "Qualité";
            value[3] = satisfactionReturn.get(0).getQ4();
            languages[3] = "Rapidité";
            value[4] = satisfactionReturn.get(0).getQ1();
            languages[4] = "Satisfaction globale";
        }

        SimpleBarChart test = new SimpleBarChart(value, languages, "Satisfaction client (dernière commande)");


        double[] value2 = new double[5];
        String[] languages2 = new String[5];
        if (satisfactionReturn.size() > 0) {
            double q1 = 0;
            double q2 = 0;
            double q3 = 0;
            double q4 = 0;
            double q5 = 0;
            for (int i = 0; i < satisfactionReturn.size(); i++) {
                q1 = q1 + satisfactionReturn.get(i).getQ1();
                q2 = q2 + satisfactionReturn.get(i).getQ2();
                q3 = q3 + satisfactionReturn.get(i).getQ3();
                q4 = q4 + satisfactionReturn.get(i).getQ4();
                q5 = q5 + satisfactionReturn.get(i).getQ5();
            }
             
            value2[0] = q1 / satisfactionReturn.size();
            languages2[0] = "Transports";
            value2[1] = q2 / satisfactionReturn.size();
            languages2[1] = "Service commercial";
            value2[2] = q3 / satisfactionReturn.size();
            languages2[2] = "Qualité";
            value2[3] = q4 / satisfactionReturn.size();
            languages2[3] = "Rapidité";
            value2[4] = q5 / satisfactionReturn.size();
            languages2[4] = "Satisfaction globale";
        }
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
