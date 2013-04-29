package games.scroller.mr_fish;

import games.scroller.mr_fish.sprites.FishLib;
import games.scroller.mr_fish.sprites.player.MrFish;
import java.awt.Dimension;
import vooga.scroller.level_editor.controllerSuite.LEController;
import vooga.scroller.level_editor.library.BackgroundLib;
import vooga.scroller.scrollingmanager.OmniScrollingManager;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.util.PlatformerConstants;
import vooga.scroller.view.GameView;


public class LevelEditor {

    /**
     * @param args
     */
    public static void main (String[] args) {

        String backgroundPath = "/games/scroller/mr_fish/images/";
        String[] filenames = new String[] { "underwater1.jpg",
                                           "underwater2.jpg",
                                           "underwater3.jpg",
                                           "bikini_bottom.jpg" };
        ScrollingManager sm = new OmniScrollingManager();
        GameView display = new GameView(PlatformerConstants.DEFAULT_WINDOW_SIZE, sm);
        sm.initView(display);
        Player fish = new MrFish(display, sm);
        LEController.runLevelEditor(new FishLib(),
                                    new BackgroundLib(backgroundPath, filenames),
                                    fish);

    }
}
