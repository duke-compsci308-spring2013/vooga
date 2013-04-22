package vooga.towerdefense.gameeditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import javax.swing.JPanel;


/**
 * The Catalog displays the images that the
 * game developer has already made for this game.
 * 
 * @author Angelica Schwartz
 */
public class Catalog extends JPanel {
    
    /**
     * default serialized id.
     */
    private static final long serialVersionUID = 1L;
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final int SIDE_LENGTH = 50;
    private List<Image> myIconList;
    private Dimension mySize;

    /**
     * constructor.
     * 
     * @param units
     */
    public Catalog (Dimension size, List<Image> images) {
        mySize = size;
        setPreferredSize(size);
        myIconList = images;
        repaint();
    }

    /**
     * paints the unit catalog.
     */
    @Override
    public void paint (Graphics pen) {
        super.paintComponent(pen);
        pen.setColor(BACKGROUND_COLOR);
        pen.fillRect(0, 0, mySize.width, mySize.height);
        displayUnits(pen);
    }

    /**
     * draws the units on the catalog.
     * 
     * @param pen
     */
    // TODO: figure out what to do if there are more units than can show
    private void displayUnits (Graphics pen) {
        int x = 0;
        int y = 0;
        for (Image image : myIconList) {
            if (x + SIDE_LENGTH > mySize.width) {
                y += SIDE_LENGTH;
                x = 0;
            }
            pen.drawImage(image, x, y, SIDE_LENGTH, SIDE_LENGTH, null);
            x += SIDE_LENGTH;
        }
    }
}
