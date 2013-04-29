package games.scroller.mr_fish;

import games.scroller.mr_fish.splash.FishSplashPage;
import games.scroller.mr_fish.sprites.FishLib;
import games.scroller.mr_fish.sprites.collisions.VisitMethods;
import games.scroller.mr_fish.sprites.player.MrFish;
import arcade.games.ArcadeInteraction;
import arcade.games.GameData;
import arcade.games.UserGameData;
import util.Location;
import vooga.scroller.collision_manager.VisitLibrary;
import vooga.scroller.level_management.splash_page.SplashPage;
import vooga.scroller.model.ScrollerGame;
import vooga.scroller.scrollingmanager.OmniScrollingManager;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.view.GameView;


public class MrFishGame extends ScrollerGame {
    // constants
    public static final String TITLE = "Mr. Fish";
    public static final String LEVELS_DIR = "src/games/scroller/mr_fish/levels/";
    public static final String SPLASH_DIR = "splash1.png";

    /**
     * main --- where the program starts
     * 
     * @param args
     */
    public static void main (String args[]) {
        // view of user's content
        ScrollerGame test = new MrFishGame(null);
        test.run();
    }

    public MrFishGame (ArcadeInteraction arcade) {
        super(arcade);
    }

    @Override
    protected String[] setLevelFileNames () {
        String[] levelsFiles = { "r.level", "t.level" };
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
        return new MrFish(gameView, sm);
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
        return new FishSplashPage(FishLib.makePixmap(FishLib.IMAGE_LOCATION, SPLASH_DIR), 0,
                                  getDisplay(), getScrollingManager());
    }

    @Override
    protected VisitLibrary setVisitLibrary () {
        // TODO Auto-generated method stub
        return new VisitMethods();
    }

}
