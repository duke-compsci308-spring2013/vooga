package vooga.scroller.model;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import vooga.scroller.collision_manager.CollisionManager;
import vooga.scroller.collision_manager.VisitLibrary;
import vooga.scroller.level_editor.Level;
import vooga.scroller.level_management.LevelManager;
import vooga.scroller.level_management.splash_page.SplashPage;
import vooga.scroller.marioGame.spritesDefinitions.collisions.MarioVisitMethods;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.util.Renderable;
import vooga.scroller.util.Renderer;
import vooga.scroller.util.mvc.Gaming;
import vooga.scroller.util.mvc.IView;
import vooga.scroller.view.GameView;


/**
 * Represents a scrolling platformer.
 * *
 * 
 * @author Ross Cahoon
 * @author Jay Wang
 * @author Scott Valentine
 */

public class Model implements Renderable<Gaming> {

    private GameView myView;
    private Player myPlayer;
    private LevelManager myLevelManager;
    private ScrollingManager myScrollingManager;
    private CollisionManager myCollisionManager;

    /**
     * Constructs a new Model based on the view and the scrolling manager used by the game.
     * 
     * @param gameView which is used to display/control game.
     * @param myScrollingManager used to control in-game scrolling.
     * @throws IOException
     */

    public Model (GameView gameView,
                  ScrollingManager sm,
                  Player player,
                  SplashPage splashPage,
                  Level ... levels) {
        this(gameView, sm, player);
        myLevelManager = initializeLevelManager(splashPage, player, levels);
    }
    
    public Model (GameView gameView,
                  ScrollingManager sm,
                  SplashPage splashPage,
                  Level ... levels) {
        this(gameView, sm);
        setGameComponents(splashPage, levels);
    }

    /**
     * Define the game flow
     * @param splashPage
     * @param levels
     */
    public void setGameComponents (SplashPage splashPage, Level... levels) {
        myLevelManager = new LevelManager(this, splashPage, levels);
    }

    /**
     * TODO -...
     * 
     * @param gameView
     * @param sm
     * @param player
     * @param splashPage
     * @param levelFileNames
     */
    public Model (GameView gameView,
                  ScrollingManager sm,
                  Player player,
                  SplashPage splashPage,
                  String ... levelFileNames) {
        this(gameView, sm, player);
        myLevelManager = initializeLevelManager(splashPage, player, levelFileNames);
    }

    private Model (GameView gameView, ScrollingManager sm, Player player) {
        this(gameView, sm);
        addPlayer(player);
    }
    
    public void addPlayer (Player player) {
        myPlayer = player;
        player.setModel(this);
        addPlayerToLevel();
    }

    /**
     * Minimal constructor.
     * Binds display, model, and scrolling manager together.
     * Need to use set GameComponents soon after
     * @param gameView
     * @param sm
     */
    public Model (GameView gameView, ScrollingManager sm) {
        this(gameView);
        setScrollingManager(sm);
        myView.setModel(this);
//        myCollisionManager = new CollisionManager(ScrollerGame.getVisitMethods());
    }
    
    /**
     * Ultra-Minimal constructor.
     * @param gameView
     * @param sm
     */
    private Model (GameView gameView) {
        myView = gameView;
        myView.setModel(this);
    }

    public Model (GameView myDisplay,
                  ScrollingManager myScrollingManager2,
                  Player myPlayer2,
                  VisitLibrary myVisitLibrary,
                  SplashPage mySplashPage,
                  String[] myLevelsFilePaths) {
        this(myDisplay, myScrollingManager2, myPlayer2, mySplashPage, myLevelsFilePaths);
        initializeCollisionManager(myVisitLibrary);
    }

    public void initializeCollisionManager (VisitLibrary visitLibrary) {
        myCollisionManager = new CollisionManager(visitLibrary); //TODO
    }

    /**
     * Add player and begin game
     */
    public void start () {
        addPlayerToLevel();
    }

    private void addPlayerToLevel () {
        myLevelManager.getCurrentLevel().addPlayer(myPlayer);
    }

    private LevelManager initializeLevelManager (SplashPage splashPage,
                                                 Player player,
                                                 Level[] levels) {
        return new LevelManager(myScrollingManager, myView, player, splashPage, levels);
    }

    private LevelManager initializeLevelManager (SplashPage splashPage,
                                                 Player player,
                                                 String[] levelFileNames) {
        return new LevelManager(myScrollingManager, myView, player, splashPage, levelFileNames);
    }

    public void setScrollingManager (ScrollingManager sm) {
        sm.initModel(this);
        sm.initView(myView);
        myView.setScrollingManager(sm);
        myScrollingManager = sm;
    }

    /**
     * Draw all elements of the game.
     */
    public void paint (Graphics2D pen) {
        myLevelManager.getCurrentLevel().paint(pen);
    }

    /**
     * Updates all the game elements since the last update.
     * 
     * @param elapsedTime is the elapsed time since the last update.
     */
    public void update (double elapsedTime) {
        myLevelManager.getCurrentLevel().update(elapsedTime, myView.getSize(), myView);
    }

    /**
     * Gives various boundaries for the current level.
     * TODO: can these be consolidated into one function (seems like a lot extra things that aren't
     * really associated with the model.
     * (we could just return the current level or maybe the bounds of the level(this might be
     * dependent on other things) )
     * 
     * @return
     */
    public double getRightBoundary () {
        return myLevelManager.getCurrentLevel().getRightBoundary();
    }

    public double getLeftBoundary () {
        return myLevelManager.getCurrentLevel().getLeftBoundary();
    }

    public double getUpperBoundary () {
        return myLevelManager.getCurrentLevel().getUpperBoundary();
    }

    public double getLowerBoundary () {
        return myLevelManager.getCurrentLevel().getLowerBoundary();
    }

    public Dimension getLevelBounds () {
        return myLevelManager.getCurrentLevel().getLevelBounds();
    }

    public Image getBackground () {
        return myLevelManager.getCurrentLevel().getBackground();
    }

    public Player getPlayer () {
        return myPlayer;
    }

    @Override
    public Renderer<Gaming> initializeRenderer (IView<?> parent) {
        return myView;
    }

    public GameView getView () {
        return myView;
    }

    public ScrollingManager getScrollingManager () {
        return myScrollingManager;
    }

    public CollisionManager getCollisionManager () {
        return myCollisionManager;
    }

    public LevelManager getLevelManager () {
        return myLevelManager;
    }

    public void setGameComponents (SplashPage splashPage, String[] levelsFilePaths) {
        myLevelManager = new LevelManager(this, splashPage, levelsFilePaths);
    }
}
