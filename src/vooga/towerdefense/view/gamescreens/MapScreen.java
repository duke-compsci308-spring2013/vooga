package vooga.towerdefense.view.gamescreens;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Point2D;
import javax.swing.JPanel;
import vooga.towerdefense.controller.Controller;
import vooga.towerdefense.util.Pixmap;


/**
 * Displays the map and everything on the map.
 * 
 * @author Angelica Schwartz
 * 
 */
public class MapScreen extends JPanel {

    /**
     * default serialized id.
     */
    private static final long serialVersionUID = 1L;
    /**
     * controller to pass information to the back-end.
     */
    private Controller myController;
    /**
     * mouse listener for clicking.
     */
    private MouseAdapter myMouseAdapter;
    /**
     * mouse listener for movement & dragging.
     */
    private MouseMotionAdapter myMouseMotionAdapter;
    /**
     * size of the MapScreen.
     */
    private Dimension mySize;

    /**
     * Constructor.
     * 
     * @param size
     * @param controller
     */
    public MapScreen (Dimension size, Controller controller) {
        mySize = size;
        setPreferredSize(mySize);
        setFocusable(true);
        setVisible(true);
        myController = controller;
        makeMouseAdapters();
        addMouseListener(myMouseAdapter);
        addMouseMotionListener(myMouseMotionAdapter);
        repaint();
    }

    /**
     * updates the MapScreen appropriately.
     */
    public void update () {
        revalidate();
        repaint();
    }

    /**
     * paints the MapScreen component.
     * 
     * @param pen is the graphics object
     */
    @Override
    public void paintComponent (Graphics pen) {
        super.paintComponent(pen);
        // paintGridLines(pen);
        myController.paintMap(pen);
    }

    /**
     * paints the ghost image at the mouse location.
     * 
     * @param p is the mouse location
     * @param image is the pixmap image to paint
     */
    public void paintGhostImage (Point p, Pixmap image) {
        if(image == null) {
            System.out.println("nulll");
        }
        image.paint((Graphics2D) getGraphics(), (Point2D) p,
                    new Dimension(image.getImage().getWidth(null), image.getImage().getHeight(null)));
    }

    /**
     * helper method to create the listener for mouse input.
     */
    // TODO: integrate this with input team
    private void makeMouseAdapters () {
        myMouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                myController.handleMapClick(e.getPoint());
            }
        };
        myMouseMotionAdapter = new MouseMotionAdapter() {
            @Override
            public void mouseMoved (MouseEvent e) {
                myController.handleMapMouseDrag(e.getPoint());

            }
        };
    }
}
