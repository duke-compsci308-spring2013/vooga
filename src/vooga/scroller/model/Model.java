package vooga.scroller.model;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import util.Location;
import util.Secretary;
import vooga.scroller.level_editor.Level;
import vooga.scroller.level_management.LevelManager;
import vooga.scroller.marioGame.spritesDefinitions.players.Mario;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.sprites.animation.Animation;
import vooga.scroller.sprites.animation.MovingSpriteAnimationFactory;
import vooga.scroller.sprites.interfaces.IPlayer;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.view.GameView;


/**
 * Represents a scrolling platformer.
 * *
 * 
 * @author Ross Cahoon
 * @author Jay Wang
 * @author Scott Valentine
 */

public class Model {
         
    private GameView myView;
    private Player myPlayer;
    private LevelManager myLevelManager;
    private ScrollingManager myScrollingManager;


    private static final String PLAYER_IMAGES = "walama.gif";
    // "mario.gif"
    //"transparent_wolf.gif" -- not yet added.
    //"walama.gif"
    
    /**
     * Constructs a new Model based on the view and the scrolling manager used by the game.
     * 
     * @param gameView which is used to display/control game.
     * @param myScrollingManager used to control in-game scrolling.
     * @throws IOException 
     */

    public Model (GameView gameView, ScrollingManager sm, Player player, Level ...levels) {
        this(gameView, sm, player);
        myLevelManager = initializeLevelManager(levels);
    }
    
    
    public Model (GameView gameView, ScrollingManager sm, Player player, String... levelFileNames) {
        this(gameView, sm, player);
        myLevelManager = initializeLevelManager(levelFileNames);
    }

    public Model (GameView gameView, ScrollingManager sm, Level level) {
        this(gameView, sm, initTestPlayer(gameView, sm), level);
    }


    private Model (GameView gameView, ScrollingManager sm, Player player) {
        myView = gameView;
        setScrollingManager(sm);
        myPlayer = player;
    }


    private static Player initTestPlayer (GameView gameView, ScrollingManager sm) {
        Player player = new Mario(new Location(), new Dimension(32, 32), gameView, sm);
        MovingSpriteAnimationFactory msaf = new MovingSpriteAnimationFactory(PLAYER_IMAGES);
        Animation playerAnimation = msaf.generateAnimation(player);
        
        player.setView(playerAnimation);
        return player;
    }

    public void addPlayerToLevel () {
        myLevelManager.getCurrentLevel().addPlayer(myPlayer);
    }

    private LevelManager initializeLevelManager (Level[] levels) {
        return new LevelManager(myScrollingManager, myView, levels);
    }


    private LevelManager initializeLevelManager (String[] levelFileNames) {
        return new LevelManager(myScrollingManager, myView, levelFileNames);
    }


    private void setScrollingManager(ScrollingManager sm) {
        myScrollingManager = sm;
        myScrollingManager.initModel(this);
        myScrollingManager.initView(myView); 
    }



//    /**
//     * User defined player initialization.
//     */
//    private Player initPlayer() {
//        // TODO: this is implemented by the developer. 
//        
//        Player player = new Mario(
//                             new Location(100, 140),
//                             new Dimension(138/6, 276/6),
//                             myView, myScrollingManager);
//        
//        MovingSpriteAnimationFactory msaf = new MovingSpriteAnimationFactory(PLAYER_IMAGES);
//        Animation playerAnimation = msaf.generateAnimation(player);
//        
//        player.setView(playerAnimation);
//
//        return player;
//    }

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
    
    public Image getBackground() {
        return myLevelManager.getCurrentLevel().getBackground();
    }
    
    public Player getPlayer(){
        return myPlayer;
    }
    
}
