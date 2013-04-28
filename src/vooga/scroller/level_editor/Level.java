package vooga.scroller.level_editor;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import util.Location;
import util.input.Input;
import util.input.InputClassTarget;
import util.input.InputMethodTarget;
import vooga.scroller.util.IGameComponent;
import vooga.scroller.level_editor.controllerSuite.LEGrid;
import vooga.scroller.level_editor.model.SpriteBox;
import vooga.scroller.level_management.LevelPortal;
import vooga.scroller.level_management.SpriteManager;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.interfaces.IDoor;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.util.PlatformerConstants;
import vooga.scroller.view.GameView;


@InputClassTarget
public class Level implements IGameComponent {

    private Dimension mySize;
    private Dimension frameOfReferenceSize;
    private SpriteManager mySpriteManager;
    private LevelStateManager myStateManager;
    private ScrollingManager myScrollingManager;
    private Image myBackground;
    private Image CITY_BACKGROUND = new ImageIcon("/vooga/scroller/images/background_small.png")
            .getImage();

    private int myID;
    private IDoor myDoor;
    private Location myStartPoint;

    // public Level (int id, ScrollingManager sm) {
    // this(); // TODO Incomplete. figure out SM constraints...
    // }

    private Level () {
        mySize = PlatformerConstants.DEFAULT_LEVEL_SIZE;
        myBackground = CITY_BACKGROUND;
        frameOfReferenceSize = PlatformerConstants.REFERENCE_FRAME_SIZE;
        // mySprites = new ArrayList<Sprite>();
        myStartPoint = new Location();
        // initFrames();
    }

    public Level (int id) {
        this();
        myID = id;
    }

    // public Level (int id, ScrollingManager sm) {
    // this(); // TODO Incomplete. figure out SM constraints...
    // }

    public Level (int id, ScrollingManager sm) {
        // MIGHT WANT TO INITIALIZE THIS WITH A PLAYER AS WELL
        this();
        mySpriteManager = new SpriteManager(this);
        myStateManager = new LevelStateManager(mySpriteManager);
        myScrollingManager = sm;
        myID = id;
    }

    // public Level (int id, ScrollingManager sm) {
    // this(); // TODO Incomplete. figure out SM constraints...
    // }

    public Level (int id, ScrollingManager sm, LEGrid grid) {
        this(id, sm);
        setSize(grid.getPixelSize());
        for (SpriteBox box : grid.getBoxes()) {
            addSprite(box.getSprite());
        }
        if (grid.getBackground() != null) {
            setBackground(grid.getBackground().getImage());
        }
    }

    // public Level (int id, ScrollingManager sm) {
    // this(); // TODO Incomplete. figure out SM constraints...
    // }

    public void update (double elapsedTime, Dimension bounds, GameView gameView) {
        myStateManager.update(elapsedTime, bounds, gameView);
    }

    @Override
    public void paint (Graphics2D pen) {
        myStateManager.paint(pen);
    }

    public int getID () {
        return myID;
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
        if (StartPoint.class.isAssignableFrom(s.getClass())) {
            myStartPoint = s.getCenter();
            return;
        }
        if (LevelPortal.class.isAssignableFrom(s.getClass())) {
            addDoor((LevelPortal) s);
        }
        mySpriteManager.addSprite(s);
    }

    public void removeSprite (Sprite s) {
        mySpriteManager.removeSprite(s);
    }

    public void addPlayer (Player p) {
        mySpriteManager.addPlayer(p);
    }

    public void setBackground (Image i) {
        myBackground = i;
    }

    public Image getBackground () {
        return myBackground;
    }

    public ScrollingManager getScrollManager () {
        return myScrollingManager;
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
        myInput.addListenerTo(this);

    }

    /**
     * removes listeners from all level elements that are controllable
     * 
     * @param myInput input that controls level elements.
     */
    public void removeInputListeners (Input myInput) {
        // TODO: sprite manager?
        myInput.removeListener(getPlayer());
        myInput.removeListener(this);
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

    /**
     * Pauses the current game.
     */
    @InputMethodTarget(name = "pause")
    public void pauseGame () {
        if (myStateManager.isActive(LevelStateManager.DEFAULT_ID)) {
            myStateManager.removeState(LevelStateManager.DEFAULT_ID);
            myStateManager.addState(LevelStateManager.PAUSED_ID);
            return;
        }
        if (myStateManager.isActive(LevelStateManager.PAUSED_ID)) {
            myStateManager.addState(LevelStateManager.DEFAULT_ID);
            myStateManager.removeState(LevelStateManager.PAUSED_ID);
            return;
        }
    }

}
