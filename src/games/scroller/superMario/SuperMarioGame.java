package games.scroller.superMario;

import games.scroller.superMario.sprites.SuperMarioLib;
import games.scroller.superMario.sprites.player.Mario;
import games.scroller.superMario.sprites.SuperMarioVisitLibrary;
import arcade.games.ArcadeInteraction;
import vooga.scroller.collision_manager.VisitLibrary;
import vooga.scroller.extra_resources.sprite_interfaces.StandardPlayerCollisions;
import vooga.scroller.level_management.splash_page.SplashPage;
import vooga.scroller.marioGame.splash_page.MarioSplashPage;
import vooga.scroller.model.ScrollerGame;
import vooga.scroller.scrollingmanager.OmniScrollingManager;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.view.GameView;


public class SuperMarioGame extends ScrollerGame {

    private static final String TITLE = "Super Mario";

    /**
     * main --- where the program starts
     * 
     * @param args
     */
    public static void main (String args[]) {
        ScrollerGame test = new SuperMarioGame(null);
        test.run();
        String backgroundPath = "/games/scroller/superMario/images/backgrounds/";
        String[] filenames = new String[] { "background.png",
                                           "backgroundhills.png",
                                           "backgroundhillsbig.png" };
        runLevelEditor(new SuperMarioLib(),
                       new Mario(), backgroundPath, filenames);
    }

    public SuperMarioGame (ArcadeInteraction arcade) {
        super(arcade);
    }

    @Override
    protected ScrollingManager setScrollingManager () {
        return new OmniScrollingManager();
    }

    @Override
    protected VisitLibrary setVisitLibrary () {
        return new SuperMarioVisitLibrary();
    }

    @Override
    protected Player setPlayer (ScrollingManager sm, GameView gameView) {
        return new Mario(getDisplay(), setScrollingManager());
    }

    @Override
    protected String setTitle () {
        return TITLE;
    }

    @Override
    protected String[] setLevelFileNames () {
        return new String[] { "level1", "level2" };
    }

    @Override
    protected String setLevelsDirPath () {
        return "src/games/scroller/superMario/levels/";
    }

    @Override
    protected SplashPage setSplashPage () {
        return new MarioSplashPage(SuperMarioLib.makePixmap("MARIO SPLASH.png"), 0, getDisplay(),
                                   getScrollingManager());
    }

}
