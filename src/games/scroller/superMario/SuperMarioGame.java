package games.scroller.superMario;

import games.scroller.superMario.sprites.Mario;
import games.scroller.superMario.sprites.SuperMarioLib;
import arcade.games.ArcadeInteraction;
import vooga.scroller.collision_manager.VisitLibrary;
import vooga.scroller.level_management.splash_page.SplashPage;
import vooga.scroller.model.ScrollerGame;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.view.GameView;


public class SuperMarioGame extends ScrollerGame {

    /**
     * main --- where the program starts
     * 
     * @param args
     */
    public static void main (String args[]) {
        // ScrollerGame test = new SuperMarioGame(null);
        // test.run();
        String backgroundPath = "/games/scroller/superMario/images/backgrounds/";
        String[] filenames = new String[] { "background.png",
                                           "backgroundhills.png",
                                           "backgroundhillsbig.png" };
        runLevelEditor(new SuperMarioLib(),
                       new Mario(), backgroundPath, filenames);
    }

    public SuperMarioGame (ArcadeInteraction arcade) {
        super(arcade);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected ScrollingManager setScrollingManager () {
        // TODO Auto-generated method stub
        return null;
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

}
