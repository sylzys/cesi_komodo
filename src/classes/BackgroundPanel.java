package classes;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {

    Image image;

    public BackgroundPanel() {
        try
        {
//image = (new ImageIcon(getClass().getResource("ressources/images/komodo.gif"))).getImage();
            //image = (new ImageIcon(getClass().getResource("ressources/images/komodo.gif"))).getImage();
            image = new ImageIcon("ressources/images/skeletal.jpg").getImage();
        }
        catch (Exception e)
        {/*handled in paintComponent()*/

        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null)
        {
            if (image != null)
            {
                g.drawImage(image, (this.getWidth() / 2) - (image.getWidth(this) / 2), (this.getHeight() / 2) - (image.getHeight(this) / 2), image.getWidth(this), image.getHeight(this), this); //(image,location x, location y, size x, size y)
            }
        }
    }
}