package games.scroller.mario;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import vooga.scroller.scrollingmanager.OmniScrollingManager;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.util.PlatformerConstants;
import vooga.scroller.view.GameView;
import arcade.games.ArcadeInteraction;
import arcade.games.Game;
import arcade.games.GameData;
import arcade.games.UserGameData;

public class Mario extends Game {
    
    // constants
    public static final String TITLE = "Mario";

    public Mario (ArcadeInteraction arcade) {
        super(arcade);
    }

    @Override
    public UserGameData generateNewProfile () {
        return new UserGameData();
    }

    @Override
    public GameData generateNewGameProfile () {
        return null;
    }

    @Override
    public void run () {
     // view of user's content
        ScrollingManager scrollManager = new OmniScrollingManager();
        GameView display = new GameView(PlatformerConstants.DEFAULT_WINDOW_SIZE, scrollManager);
        scrollManager.initView(display);
        
        // container that will work with user's OS
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // add our user interface components
        frame.getContentPane().add(display, BorderLayout.CENTER);
        // display them
        frame.pack();
        frame.setVisible(true);
        // start animation
        display.start();
    }
    
    public static void main(String[] args){
        Mario m = new Mario(null);
        m.run();
    }

}
