package vooga.scroller.sprites.superclasses;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import util.Location;
import vooga.scroller.level_management.IInputListener;
import vooga.scroller.model.Model;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.sprites.interfaces.Locatable;
import vooga.scroller.util.IGameComponent;
import vooga.scroller.util.ISpriteView;
import vooga.scroller.view.GameView;


/**
 * This is the superclass for any player the game designer creates.
 * 
 * @author Jay Wang, Ross Cahoon, Scott Valentine
 * 
 */
public abstract class Player extends GameCharacter implements IInputListener, Locatable {

    private GameView myView;
    private Location myPaintCenter;
    private ScrollingManager myScrollingManager;
    private IGameComponent myLevel;

    public Player (ISpriteView image, Dimension size, GameView gameView,
                   ScrollingManager sm, int health, int damage) {
        this(image, size, health, damage);
        myView = gameView;
        myPaintCenter = new Location(myView.getWidth() / 2, myView.getHeight() / 2);
        myScrollingManager = sm;
    }
    
    public Player (ISpriteView image, Dimension size, int health, int damage, Model model) {
        this(image, size, health, damage);
        setModel(model);
    }
    
    /**
     * This is the minimal constructor. Make sure to set the model at some point when 
     * using this constructor
     * @param image
     * @param size
     * @param health
     * @param damage
     */
    public Player (ISpriteView image, Dimension size, int health, int damage) {
        super(image, size, health, damage);
    }
    
    public void setModel(Model model) {
        myView = model.getView();
        myScrollingManager = model.getScrollingManager();
        myPaintCenter = new Location(myView.getWidth() / 2, myView.getHeight() / 2);
    }

    @Override
    public void update (double elapsedTime, Dimension bounds) {
        super.update(elapsedTime, bounds);
        myPaintCenter = myScrollingManager.playerPaintLocation(this);
    }

    @Override
    public void paint (Graphics2D pen) {

        Point2D currentLocal = new Location(this.getCenter());
        this.setCenter(myPaintCenter.getX(), myPaintCenter.getY());

        super.paint(pen);
        this.setCenter(currentLocal.getX(), currentLocal.getY());
    }

    /**
     * Gives the location at which this sprite will be painted.
     * 
     * @return
     */
    public Location getPaintLocation () {
        return myPaintCenter;
    }

    public void setLevel (IGameComponent level) {
        myLevel = level;
    }

    public IGameComponent getLevel () {
        return myLevel;
    }

    public abstract void incrementScore (int value);
}
