package games.scroller.letteradventure;

import arcade.controller.Controller;
import arcade.games.ArcadeInteraction;
import vooga.scroller.collision_manager.VisitLibrary;
import vooga.scroller.level_management.splash_page.SplashPage;
import vooga.scroller.model.ScrollerGame;
import vooga.scroller.scrollingmanager.OmniScrollingManager;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.view.GameView;

/**
 * The LetterAdventure game starts here.
 * 
 * @author Ellango, David Liu
 *
 */
public class LetterAdventure extends ScrollerGame {
    private static final String LEVELS_DIR = "/games/scroller/leveladventure/levels/";
    private static final String TITLE = "DEG";

    public LetterAdventure (ArcadeInteraction arcade) {
        super(arcade);
    }

    @Override
    protected ScrollingManager setScrollingManager () {
        return new OmniScrollingManager();
    }

    @Override
    protected VisitLibrary setVisitLibrary () {
        return new VisitMethods();
    }

    @Override
    protected Player setPlayer (ScrollingManager sm, GameView gameView) {
        return new EPlayer(gameView, sm);
    }

    @Override
    protected String setTitle () {
        return TITLE;
    }

    @Override
    protected String[] setLevelFileNames () {
        String[] levelsFiles = { "level1.level", "level2.level" };
        return levelsFiles;
    }

    @Override
    protected String setLevelsDirPath () {
        return LEVELS_DIR;
    }

    @Override
    protected SplashPage setSplashPage () {
        return new LetterSplashPage(SpriteLibrary.makePixmap("splash.jpg"), 0, getDisplay(),
                                   getScrollingManager());
    }
    
    public static void main (String[] args) {
        new LetterAdventure(new Controller("English")).run();
    }

}
