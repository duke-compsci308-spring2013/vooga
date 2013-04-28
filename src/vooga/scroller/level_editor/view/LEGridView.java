package vooga.scroller.level_editor.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.Scrollable;
import vooga.scroller.level_editor.LevelEditing;
import vooga.scroller.level_editor.commands.CommandConstants;
import vooga.scroller.level_editor.controllerSuite.GridSpinner;
import vooga.scroller.level_editor.controllerSuite.LEGrid;
import vooga.scroller.util.Renderable;
import vooga.scroller.util.Renderer;
import vooga.scroller.util.mvc.IView;
import vooga.scroller.util.mvc.vcFramework.ViewConstants;
import vooga.scroller.util.mvc.vcFramework.WindowComponent;


/**
 * View for LEGrid. Contained within LEWorkspaceView
 * 
 * @author Danny Goodman, Deo Fagnisse
 */
public class LEGridView extends WindowComponent<LevelEditing>
        implements Scrollable, Renderer<LevelEditing> {

    private static final long serialVersionUID = 8266835201464623542L;
    private static final String GRID_RENDERING_ERROR = LevelEditing.GRID_RENDERING_ERROR;
    private LEGrid myGrid;
    private GridSpinner myGridSpinner;

    /**
     * Specify a container parent and a width and height ratio.
     * 
     * @param parent - container for this view instance
     * @param r - main renderable for this view instance
     */
    public LEGridView (IView<LevelEditing> parent, Renderable<LevelEditing> r) {
        super(parent, ((LEGrid) r).getPixelSize());
        myGrid = (LEGrid) r;
        this.addMouseListener(new GridPositionListener());

    }

    /**
     * Internal getter for Default Height Ratio
     * 
     * @return - height ratio
     */
    private double getDefaultHeightRatio () {
        return ViewConstants.DEFAULT_GRIDVIEW_HEIGHT_RATIO;
    }

    /**
     * Internal getter for Default width Ratio
     * 
     * @return - width ratio
     */
    private double getDefaultWidthRatio () {
        return ViewConstants.DEFAULT_GRIDVIEW_WIDTH_RATIO;
    }

    private void createSprite (int x, int y) {
        String cmd = CommandConstants.CREATE_SPRITE + CommandConstants.SPACE
                     + x + CommandConstants.SPACE + y;
        process(cmd);
    }

    private void deleteSprite (int x, int y) {
        String cmd = CommandConstants.DELETE_SPRITE + CommandConstants.SPACE
                     + x + CommandConstants.SPACE + y;
        process(cmd);
    }

    @Override
    public Dimension getPreferredScrollableViewportSize () {
        Dimension d = this.getResponsible().getSize();
        Dimension res = new Dimension((int) (d.width * getDefaultWidthRatio()),
                                      (int) (d.height * getDefaultHeightRatio()));
        return res;
    }

    @Override
    public Renderable<LevelEditing> getRenderable () {
        return myGrid;
    }

    @Override
    public int getScrollableBlockIncrement (Rectangle visibleRect, int orientation, int direction) {
        return myGrid.getScrollableBlockIncrement(visibleRect, orientation, direction);
    }

    @Override
    public boolean getScrollableTracksViewportHeight () {
        return false;
    }

    @Override
    public boolean getScrollableTracksViewportWidth () {
        return false;
    }

    @Override
    public int getScrollableUnitIncrement (Rectangle visibleRect, int orientation, int direction) {
        return myGrid.getScrollableUnitIncrement(visibleRect, orientation, direction);
    }

    /**
     * checks if grid is ready for simulation
     * 
     * @return true if valid
     */
    public boolean isValidForSimulation () {
        return myGrid.isValidForSimulation();
    }

    /**
     * Paint the contents of the canvas.
     * 
     * Never called by you directly, instead called by Java runtime
     * when area of screen covered by this container needs to be
     * displayed (i.e., creation, uncovering, change in status)
     * 
     * @param pen used to paint shape on the screen
     */
    @Override
    public void paintComponent (Graphics pen) {
        pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, getSize().width, getSize().height);
        if (myGrid != null) {
            myGrid.paint((Graphics2D) pen);
        }
    }

    @Override
    public void render (Renderable<LevelEditing> r) {
        if (r instanceof LEGrid) {
            render((LEGrid) r);
        }
        else {
            showMessageDialog(GRID_RENDERING_ERROR);
        }
    }

    @Override
    public void setRenderable (Renderable<LevelEditing> r) {
        myGrid = (LEGrid) r;
        setSize(myGrid.getPixelSize());
        repaint();
    }

    /**
     * Update the size of the grid in the active tab
     */
    public void updateGridSize () {

        myGridSpinner = new GridSpinner(myGrid.getSize().width,
                                        myGrid.getSize().height);
        int a = (int) JOptionPane.showConfirmDialog(
                                                    null, myGridSpinner,
                                                    "Update Grid Height and Width",
                                                    JOptionPane.OK_CANCEL_OPTION);
        if (a == 0) {
            process(CommandConstants.CHANGE_GRID_SIZE + " " +
                    myGridSpinner.getGridWidth() + ", " +
                    myGridSpinner.getGridHeight());
        }
    }

    private class GridPositionListener implements MouseListener {
        private static final int LEFT_CLICK = 3;
        private static final int RIGHT_CLICK = 1;

        @Override
        public void mouseClicked (MouseEvent e) {
            if (e.getButton() == LEFT_CLICK) {
                deleteSprite(e.getX(), e.getY());
            }
            else if (e.getButton() == RIGHT_CLICK) {
                createSprite(e.getX(), e.getY());
            }
        }

        @Override
        public void mouseEntered (MouseEvent e) {
        }

        @Override
        public void mouseExited (MouseEvent e) {
        }

        @Override
        public void mousePressed (MouseEvent e) {
        }

        @Override
        public void mouseReleased (MouseEvent e) {
        }

    }

}
