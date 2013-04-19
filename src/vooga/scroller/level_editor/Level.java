package vooga.scroller.level_editor;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import util.Location;
import util.input.Input;
import vooga.scroller.util.Sprite;
import vooga.scroller.viewUtil.Renderable;
import vooga.scroller.level_management.IDoor;
import vooga.scroller.level_management.LevelPortal;
import vooga.scroller.level_management.SpriteManager;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.util.PlatformerConstants;
import vooga.scroller.view.View;

public class Level implements Renderable {

    private Dimension mySize;
    private Dimension frameOfReferenceSize;
    private SpriteManager mySpriteManager;
    private View myView;
    private ScrollingManager myScrollingManager;
    private Image myBackground;
    private Image DEFAULT_BACKGROUND = new ImageIcon(getClass()
            .getResource("/vooga/scroller/images/default_background.png")).getImage();
    private Image CITY_BACKGROUND = new ImageIcon("/vooga/scroller/images/background_small.png")
            .getImage();

    private int myID;
    private IDoor myDoor;
    private Location myStartPoint;

    public int getID () {
        return myID;
    }

    private Level () {
        mySize = PlatformerConstants.DEFAULT_LEVEL_SIZE;
        myBackground = CITY_BACKGROUND;
        frameOfReferenceSize = PlatformerConstants.REFERENCE_FRAME_SIZE;
        myStartPoint = new Location();
    }

    public Level (int id, ScrollingManager sm) {
        this(); // TODO Incomplete. figure out SM constraints...
    }

    public Level (int id) {
        this();
        myID = id;
    }

    public Level (int id, ScrollingManager sm, View view) {
        // MIGHT WANT TO INITIALIZE THIS WITH A PLAYER AS WELL
        this();
        myView = view;
        mySpriteManager = new SpriteManager(this, myView);
        myScrollingManager = sm;
        myID = id;
    }

    public Level (int id, ScrollingManager sm, View view, LEGrid grid) {
        this(id, sm, view);
        setSize(grid.getPixelSize());
        for (SpriteBox box : grid.getBoxes()) {
            addSprite(box.getSprite());
        }
    }

    public void setSize (Dimension size) {
        mySize = size;
    }

    /**
     * Adds a sprite to the level.
     * 
     * @param s the Sprite to be added
     */

    public void addSprite (Sprite s) {
        if (s instanceof StartPoint) {
            myStartPoint = s.getCenter();
            return;
        }
        if (s instanceof LevelPortal) {
            addDoor((LevelPortal) s);
        }
        mySpriteManager.addSprite(s);
    }

    public void removeSprite (Sprite s) {
        mySpriteManager.removeSprite(s);
    }

    public void addPlayer (Player p ) {
        mySpriteManager.addPlayer(p);
    }

    public void setBackground (Image i) {
        myBackground = i;
    }

    public Image getBackground () {
        return myBackground;
    }

    public ScrollingManager getScrollManager(){
        return myScrollingManager;
    }
    
    // Methods from Renderable Interface. To be called by View components.

    @Override
    public Object getState () {
        // TODO auto-generated.
        return null;
    }

    public void update (double elapsedTime, Dimension bounds, View view) {
        mySpriteManager.updateSprites(elapsedTime, bounds, view);
    }

    @Override
    public void paint (Graphics2D pen) {
        mySpriteManager.paint(pen);
    }

    public double getRightBoundary (Dimension frame) {
        return myScrollingManager.getRightBoundary(frame, getPlayer().getCenter());
    }

    public double getLeftBoundary (Dimension frame) {
        return myScrollingManager.getLeftBoundary(frame, getPlayer().getCenter());
    }

    public double getUpperBoundary (Dimension frame) {
        return myScrollingManager.getUpperBoundary(frame, getPlayer().getCenter());
    }

    public double getLowerBoundary (Dimension frame) {
        return myScrollingManager.getLowerBoundary(frame, getPlayer().getCenter());
    }

    public double getRightBoundary () {
        return myScrollingManager.getRightBoundary(frameOfReferenceSize, getPlayer().getCenter());
    }

    public double getLeftBoundary () {
        return myScrollingManager.getLeftBoundary(frameOfReferenceSize, getPlayer().getCenter());
    }

    public double getUpperBoundary () {
        return myScrollingManager.getUpperBoundary(frameOfReferenceSize, getPlayer().getCenter());
    }

    public double getLowerBoundary () {
        return myScrollingManager.getLowerBoundary(frameOfReferenceSize, getPlayer().getCenter());
    }

    public Dimension getLevelBounds () {
        return mySize;
    }


    /**
     * Gives the view used by this Level
     * 
     * @return This level's view.
     */
    public View getView () {
        return myView;
    }

    /**
     * Gives the player currently in the level. Returns null if
     * player has never been added to the level.
     * 
     * @return This level's player.
     */
    public Player getPlayer () {
        return mySpriteManager.getPlayer();
    }

    /**
     * adds listeners to all level elements that are controllable
     * 
     * @param myInput input that controls level elements.
     */
    public void addInputListeners (Input myInput) {       
        // TODO: sprite manager?
        myInput.replaceMappingResourcePath(getPlayer().getInputFilePath());
        myInput.addListenerTo(getPlayer());

    }

    /**
     * removes listeners from all level elements that are controllable
     * 
     * @param myInput input that controls level elements.
     */
    public void removeInputListeners (Input myInput) {
        // TODO: sprite manager?
        myInput.removeListener(getPlayer());
    }

    /**
     * TODO - define default door or make it clear that door needs to be set.
     * 
     * @return
     */
    public IDoor getDoor () {
        return myDoor;
    }

    public void addDoor (IDoor door) {
        myDoor = door;
    }

    public void addStartPoint (Location start) {
        myStartPoint = start;
    }

    public Location getStartPoint () {
        return myStartPoint;
    }

}
