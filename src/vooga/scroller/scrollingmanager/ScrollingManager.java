package vooga.scroller.scrollingmanager;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import util.Location;
import vooga.scroller.model.Model;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.util.Direction;
import vooga.scroller.view.GameView;

/**
 * Abstract class for all ScrollingManagers to extend
 * @author Ross
 *
 */

public abstract class ScrollingManager {
    private Model myModel;
    private GameView myView;

    /**
     * Used to initialize the a reference to the Model the ScrollingManager will be using.
     * @param model The instance of the Model that the ScrollingManager will be controlling
     */
    public void initModel(Model model) {
        myModel = model;
    }

    /**
     * Used to initialize the a reference to the View the ScrollingManager 
     * will be using.
     * @param gameView The instance of the View that the ScrollingManager will be controlling
     */
    public void initView(GameView gameView) {
        myView = gameView;
    }

    protected abstract int upperpaintbound();

    protected abstract int lowerpaintbound();

    protected abstract int leftpaintbound();

    protected abstract int rightpaintbound();

    /**
     * Given a frame, gives the right boundary around the specified center point
     * @param frame The frame being considered.
     * @param center The center of the frame.
     * @return The right boundary
     */
    public double getRightBoundary(Dimension frame, Location center) {
//        return center.getX() + frame.getWidth() / 2;
        return center.getX() + frame.getWidth();
    }

    /**
     * Given a frame, gives the left boundary around the specified center point
     * @param frame The frame being considered.
     * @param center The center of the frame.
     * @return The left boundary
     */
    public double getLeftBoundary(Dimension frame, Location center) {
//        return center.getX() - frame.getWidth() / 2;
        return center.getX() - frame.getWidth();
    }

    /**
     * Given a frame, gives the upper boundary around the specified center point
     * @param frame The frame being considered.
     * @param center The center of the frame.
     * @return The upper boundary
     */
    public double getUpperBoundary(Dimension frame, Location center) {
        return center.getY() - frame.getHeight() / 2;
    }

    /**
     * Given a frame, gives the lower boundary around the specified center point
     * @param frame The frame being considered.
     * @param center The center of the frame.
     * @return The lower boundary
     */
    public double getLowerBoundary(Dimension frame, Location center) { 
        return center.getY() + frame.getHeight() / 2;
    }

    protected double levelRightBoundary() {
        return getModel().getLevelBounds().getWidth();
    }

    protected double levelLeftBoundary() {
        return 0;
    }

    protected double levelUpperBoundary() {
        return 0;
    }

    protected double levelLowerBoundary() { 
        return getModel().getLevelBounds().getHeight();
    }


    protected  Image getBackground() {
        return getModel().getBackground();
    }

    /**
     * Paints the View for the game, given the constraints of the ScrollingManger.
     * @param pen The Graphics object that will be doing the painting.
     */
    public abstract void viewPaint(Graphics pen);

    /**
     * Given a player, returns the Location given the current state of the Model 
     * that the Player needs to be painted. 
     * @param player The Player for which the paint Location is being updated.
     * @return The Location that the player should be painted.
     */    
    public abstract Location playerPaintLocation(Player player);

    /**
     * Returns the double that corresponds to a hard boundary for the game. 
     * A hard boundary is a line that can't be crossed by the Player
     * @param d The direction for which you are inquiring about a boundary
     * @param levelBounds The bounds for the Level in that direction, 
     * assuming there are no other hard boundaries in that direction.
     * @return The double that corresponds to the hard boundary
     */
    public double getHardBoundary (Direction d, double levelBounds) {
        return levelBounds;
    }

    /**
     * Gets the View that was initialized for this ScrollingManager
     * @return the View 
     */
    protected GameView getView () {
        return myView;
    }

    /**
     * Gets the Model that was initialized for this ScrollingManager
     * @return the Model 
     */
    protected Model getModel () {
        return myModel;
    }


}
