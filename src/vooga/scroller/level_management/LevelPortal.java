package vooga.scroller.level_management;

import java.awt.Dimension;
import java.awt.Image;
import util.Location;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.interfaces.IDoor;
import vooga.scroller.util.IGameComponent;
import vooga.scroller.util.ISpriteView;
import vooga.scroller.util.Pixmap;


/**
 * LevelPortal extends Sprite and implements IDoor. Used for advancing to
 * the next Level in LevelManager.
 * 
 * @author Danny Goodman, Scott Valentine
 */
public abstract class LevelPortal extends Sprite implements IDoor {
    private static final String DEFAULT_FILENAME = "portal.png";
    private static final String DEFAULT_PATH = "/vooga/scroller/images/";
    private static final Dimension DEFAULT_SIZE = new Dimension(50, 50);
    private static final Location DEFAULT_LOCATION = new Location(0, 0);
    private static final Pixmap DEFAULT_IMG = new Pixmap(DEFAULT_PATH, DEFAULT_FILENAME);
    private IGameComponent myNextLevel;
    private LevelManager myLevelManager;

    /**
     * Default constructor. Calls with default Location.
     */
    public LevelPortal () {
        this(DEFAULT_LOCATION);
        setView(initView());
        setDefaultImg(initView());
        setSize(initSize().width, initSize().height);
    }

    /**
     * created LevelPortal from Location
     * 
     * @param center - Location of LevelPortal
     */
    private LevelPortal (Location center) {
        super(DEFAULT_IMG, center, DEFAULT_SIZE);
    }

    @Override
    public Image getDefaultImg () {
        return initView().getImage();
    }

    public abstract ISpriteView initView ();

    public abstract Dimension initSize ();

    @Override
    public void goToNextLevel () {
        myLevelManager.setCurrentLevel(myNextLevel);
    }

    @Override
    public void setNextLevel (IGameComponent level) {
        myNextLevel = level;
    }

    @Override
    public IGameComponent getNextLevel () {
        return myNextLevel;
    }

    @Override
    public void setManager (LevelManager lm) {
        myLevelManager = lm;
    }
}
