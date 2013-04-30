package games.scroller.stickmansam;

import vooga.scroller.level_editor.controllerSuite.LEController;
import vooga.scroller.level_editor.library.BackgroundLib;
import vooga.scroller.scrollingmanager.OmniScrollingManager;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.util.PlatformerConstants;
import vooga.scroller.view.GameView;


/**
 * Class for level editor.
 * 
 */
public class LevelEditorMain {

    /**
     * Start game
     * @param args none
     */
    public static void main (String[] args) {

        String backgroundPath = "/games/scroller/stickmansam/images/backgrounds/";
        String[] filenames = { "background.png" };
        ScrollingManager scrollManager = new OmniScrollingManager();
        GameView display = new GameView(PlatformerConstants.DEFAULT_WINDOW_SIZE, scrollManager);
        scrollManager.initView(display);
        Player ePlayer = new StickmanPlayer(display, scrollManager);
        LEController.runLevelEditor(new StickmanSpriteLibrary(),
                                    new BackgroundLib(backgroundPath, filenames),
                                    ePlayer);

    }
}
