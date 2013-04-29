package vooga.scroller.kirbyGame;


import java.awt.Dimension;
import arcade.games.ArcadeInteraction;
import arcade.games.GameData;
import arcade.games.UserGameData;
import util.Location;
import vooga.scroller.collision_manager.VisitLibrary;
import vooga.scroller.kirbyGame.spritesDefinitions.collisions.KirbyVisitMethods;
import vooga.scroller.kirbyGame.spritesDefinitions.players.Kirby;
import vooga.scroller.level_management.splash_page.SplashPage;
import vooga.scroller.marioGame.splash_page.MarioSplashPage;
import vooga.scroller.marioGame.spritesDefinitions.MarioLib;
import vooga.scroller.marioGame.spritesDefinitions.collisions.MarioVisitMethods;
import vooga.scroller.marioGame.spritesDefinitions.players.Mario;
import vooga.scroller.model.ScrollerGame;
import vooga.scroller.scrollingmanager.OmniScrollingManager;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.scrollingmanager.UniScrollingManager;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.util.Direction;
import vooga.scroller.view.GameView;


/**
 * A sample scroller game. By implementing the methods required by ScrollerGame,
 * the designer defines the components of the game. Most important, is the get LevelFileNames method.
 */

public class KirbyGame extends ScrollerGame {

    // constants
    public static final String TITLE = "Kirby Demo";
    public static final String LEVELS_DIR = "src/vooga/scroller/kirbyGame/sampleLevels/";
    public static final String SPLASH_DIR = "MARIO SPLASH.png";


    /**
     * main --- where the program starts
     * @param args
     */
    public static void main (String args[]) {
        // view of user's content
        ScrollerGame test = new KirbyGame(null);
        test.run();
    }


    public KirbyGame (ArcadeInteraction arcade) {
        super(arcade);
    } 

    @Override
    protected String[] setLevelFileNames () {
        String[] levelsFiles = {"kirbytest.level"};
        return levelsFiles;
    }



    @Override
    protected String setTitle () {
        return TITLE;
    }


    @Override
    protected ScrollingManager setScrollingManager () {
        return new OmniScrollingManager();
    }



    @Override
    protected Player setPlayer (ScrollingManager sm, GameView gameView) {
        return new Kirby(new Location(), new Dimension(32, 32), gameView, sm);
    }


    @Override
    protected String setLevelsDirPath () {
        return LEVELS_DIR;

    }

    @Override
    public UserGameData generateNewProfile () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameData generateNewGameProfile () {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    protected SplashPage setSplashPage () {
        return new MarioSplashPage(MarioLib.makePixmap("MARIO SPLASH.png"), 0, getDisplay(), getScrollingManager());
    }


    @Override
    protected VisitLibrary setVisitLibrary () {
        return new KirbyVisitMethods();
    }

}
