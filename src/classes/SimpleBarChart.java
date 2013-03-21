/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SimpleBarChart extends JPanel {

    private double[] value;
    private String[] languages;
    private String title;

    public SimpleBarChart(double[] val, String[] lang, String t) {
        languages = lang;
        value = val;
        title = t;
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (value == null || value.length == 0) {
            return;
        }
        double minValue = 0;
        double maxValue = 0;
        for (int i = 0; i < value.length; i++) {
            if (minValue > value[i]) {
                minValue = 0;
            }
            if (maxValue < value[i]) {
                maxValue = 10;
            }
        }
       // Dimension dim = 200;
        int clientWidth = 450;
        int clientHeight = getHeight();
        int barWidth = clientWidth / value.length;
        Font titleFont = new Font("Arial", Font.BOLD, 15);
        FontMetrics titleFontMetrics = graphics.getFontMetrics(titleFont);
        Font labelFont = new Font("Arial", Font.PLAIN, 10);
        FontMetrics labelFontMetrics = graphics.getFontMetrics(labelFont);
        int titleWidth = titleFontMetrics.stringWidth(title);
        int q = titleFontMetrics.getAscent();
        int p = (clientWidth - titleWidth) / 2;
        graphics.setFont(titleFont);
        graphics.drawString(title, p, q);
        int top = titleFontMetrics.getHeight();
        int bottom = labelFontMetrics.getHeight();
        if (maxValue == minValue) {
            return;
        }
        double scale = (clientHeight - top - bottom) / (maxValue - minValue);
        q = clientHeight - labelFontMetrics.getDescent();
        graphics.setFont(labelFont);
        for (int j = 0; j < value.length; j++) {
            int valueP = j * barWidth + 1;
            int valueQ = top;
            int height = (int) (value[j] * scale);
            if (value[j] >= 0) {
                valueQ += (int) ((maxValue - value[j]) * scale);
            } else {
                valueQ += (int) (maxValue * scale);
                height = -height;
            }
            
            if(value[j] >= 8){
                    graphics.setColor(new Color(50, 176, 93));
            }
            if(value[j] < 8 && value[j] > 5){
                graphics.setColor(new Color(157, 232, 172));
            }if(value[j] == 5) {
                graphics.setColor(new Color(242, 244, 255));
            }
            if(value[j] < 5) {
                graphics.setColor(new Color(255, 107, 84));
            }if(value[j] < 3) {
                graphics.setColor(new Color(232, 53, 34));
            }
            
            
            graphics.fillRect(valueP, valueQ, barWidth - 2, height);
            graphics.setColor(Color.black);
            graphics.drawRect(valueP, valueQ, barWidth - 2, height);
            int labelWidth = labelFontMetrics.stringWidth(languages[j]);
            p = j * barWidth + (barWidth - labelWidth) / 2;
            graphics.drawString(languages[j], p, q);
            
        }
    }

}
