package vooga.towerdefense.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
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

    private static final long serialVersionUID = 1L;
    private static final String RESOURCE = "/vooga/towerdefense/images/";
    private Controller myController;
    private MouseAdapter myMouseAdapter;
    private MouseMotionAdapter myMouseMotionAdapter;
    private Dimension mySize;
    private Point mouseLocation;

    /**
     * Constructor.
     * 
     * @param size
     */
    public MapScreen (Dimension size, Controller controller) {
        mySize = size;
        setPreferredSize(mySize);
        setFocusable(true);
        setVisible(true);
        myController = controller;
        makeMouseAdapters();
        mouseLocation = new Point(0, 0);
        addMouseListener(myMouseAdapter);
        addMouseMotionListener(myMouseMotionAdapter);
        repaint();
    }

    /**
     * updates the mapscreen appropriately.
     */
    public void update () {
        revalidate();
        repaint();
    }

    /**
     * paints the MapScreen component.
     */
    @Override
    public void paintComponent (Graphics pen) {
        super.paintComponent(pen);
        myController.paintMap(pen);
        paintGridLines(pen);
    }
    
    /**
     * paints the ghost image at the mouse location.
     * @param p is the mouse location
     * @param image is the pixmap image to paint
     */
    public void paintGhostImage(Point p, Pixmap image) {
        getGraphics().drawImage(image.getImage(), p.x, p.y,
                                image.getImage().getWidth(null),
                                image.getImage().getWidth(null), null);
    }
    
    /**
     * used for testing to paint a grid.
     * TODO: remove this method
     * @param pen
     */
    public void paintGridLines (Graphics pen) {
        for (int i = 0; i < mySize.width; i += 25) {
            pen.drawLine(i, 0, i, mySize.height);
        }
        for (int j = 0; j < mySize.height; j += 25) {
            pen.drawLine(0, j, mySize.width, j);
        }
    }

    /**
     * helper method to create the listener for mouse input.
     */
    // TODO: integrate this with input team
    private void makeMouseAdapters() {
        myMouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                myController.handleMapClick(e.getPoint());
            }
        };
        myMouseMotionAdapter = new MouseMotionAdapter() {
            @Override
            public void mouseMoved (MouseEvent e) {
                //TODO: remove these comments
                // myController.handleMouseMovement(e.getPoint());
                //mouseLocation = e.getPoint();
                //update();
                myController.handleMapMouseDrag(e.getPoint());
            }
        };
    }
}
