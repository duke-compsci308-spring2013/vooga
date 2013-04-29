package games.scroller.stickmansam;

import arcade.games.ArcadeInteraction;
import vooga.scroller.collision_manager.VisitLibrary;
import vooga.scroller.level_management.splash_page.SplashPage;
import vooga.scroller.model.ScrollerGame;
import vooga.scroller.scrollingmanager.OmniScrollingManager;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.view.GameView;

public class StickmanGame extends ScrollerGame {

    public StickmanGame (ArcadeInteraction arcade) {
        super(arcade);
    }

    @Override
    protected ScrollingManager setScrollingManager () {
        return new OmniScrollingManager();
    }

    @Override
    protected VisitLibrary setVisitLibrary () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Player setPlayer (ScrollingManager sm, GameView gameView) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected String setTitle () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected String[] setLevelFileNames () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected String setLevelsDirPath () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected SplashPage setSplashPage () {
        // TODO Auto-generated method stub
        return null;
    }
    
    public static void main (String[] args) {
        //runLevelEditor(null, myPlayer, myTitle, args);
    }

}
