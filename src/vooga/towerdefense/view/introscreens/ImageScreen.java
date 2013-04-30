package vooga.towerdefense.view.introscreens;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Loads the splash screen.
 * @author Leonard K. Ng'eno
 *
 */
public class ImageScreen extends JPanel {

    private static final String RESOURCE = "/vooga/towerdefense/images/";
    private static final long serialVersionUID = 1L;
    private java.awt.Image myBackgroundImage;

    /**
     * Constructor
     * @param size      Screen size
     * @param imagePath         Image location
     */
    public ImageScreen (Dimension size, String imagePath) {
        setPreferredSize(size);
        myBackgroundImage =
                new ImageIcon(getClass().getResource(RESOURCE + imagePath)).getImage();
    }

    /**
     * Graphics painting
     */
    @Override
    public void paintComponent (Graphics pen) {
        super.paintComponent(pen);
        if (myBackgroundImage != null) {
            pen.drawImage(myBackgroundImage, 0, 0, this.getSize().width, this.getSize().height,
                          null);
        }

    }

}
