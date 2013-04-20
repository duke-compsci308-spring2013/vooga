package vooga.scroller.model;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import javax.swing.JFrame;
import arcade.games.ArcadeInteraction;
import arcade.games.Game;
import arcade.games.GameData;
import arcade.games.UserGameData;
import vooga.scroller.scrollingmanager.OmniScrollingManager;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.scrollingmanager.UniScrollingManager;
import vooga.scroller.util.Direction;
import vooga.scroller.util.PlatformerConstants;
import vooga.scroller.view.GameView;


/**
 * Creates window that can be moved, resized, and closed by the user.
 *
 * @author Ross Cahoon
 */
public class Scroller extends ScrollerGame
{


    // constants
    public static final String TITLE = "Scroller Demo";


    /**
     * main --- where the program starts
     * @param args
     */
    public static void main (String args[]) {
        // view of user's content
        ScrollerGame test = new Scroller();
        test.start();
    }



    @Override
    protected String[] getLevelFileNames () {
        String[] levelsFiles = {"verySimpleLevel.level", "new.level"};
        return levelsFiles;
    }



    @Override
    protected String getTitle () {
        return TITLE;
    }
}
