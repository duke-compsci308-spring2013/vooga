package games.scroller.stickmansam;

import arcade.controller.Controller;
import arcade.games.ArcadeInteraction;
import vooga.scroller.collision_manager.VisitLibrary;
import vooga.scroller.level_management.splash_page.SplashPage;
import vooga.scroller.model.ScrollerGame;
import vooga.scroller.scrollingmanager.OmniScrollingManager;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.view.GameView;


public class StickmanGame extends ScrollerGame {

    private static final String SPLASH_IMAGE = "splash.jpg";
    private static final String TITLE = "Stickman Sam";
    private static final String LEVELS = "/games/scroller/stickmansam/levels/";

    public StickmanGame (ArcadeInteraction arcade) {
        super(arcade);
    }

    @Override
    protected ScrollingManager setScrollingManager () {
        return new OmniScrollingManager();
    }

    @Override
    protected VisitLibrary setVisitLibrary () {
        return new StickmanVisitMethods();
    }

    @Override
    protected Player setPlayer (ScrollingManager sm, GameView gameView) {
        return new StickmanPlayer(gameView, sm);
    }

    @Override
    protected String setTitle () {
        return TITLE;
    }

    @Override
    protected String[] setLevelFileNames () {
        return new String[] { /*TODO */ };
    }

    @Override
    protected String setLevelsDirPath () {
        return LEVELS;
    }

    @Override
    protected SplashPage setSplashPage () {
        return new StickmanSplashPage(StickmanSpriteLibrary.makePixmap(SPLASH_IMAGE), 0,
                                      getDisplay(), getScrollingManager());
    }

    public static void main (String[] args) {
        new StickmanGame(new Controller("English")).run();
    }

}
