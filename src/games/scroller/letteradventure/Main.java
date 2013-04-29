package games.scroller.letteradventure;

import games.scroller.marioGame.spritesDefinitions.MarioLib;
import games.scroller.mr_fish.sprites.FishLib;
import games.scroller.mr_fish.sprites.player.MrFish;
import vooga.scroller.level_editor.controllerSuite.LEController;
import vooga.scroller.level_editor.library.BackgroundLib;
import vooga.scroller.scrollingmanager.OmniScrollingManager;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.util.PlatformerConstants;
import vooga.scroller.view.GameView;

/**
 * Used for level creation
 * @author Ellango, David Liu
 *
 */
public class Main {

    public static void main (String[] args) {

        String backgroundPath = "src/games/scroller/letteradventure/images/";
        String[] filenames = {"background.png"};
        ScrollingManager sm = new OmniScrollingManager();
        GameView display = new GameView(PlatformerConstants.DEFAULT_WINDOW_SIZE, sm);
        sm.initView(display);
        Player ePlayer = new EPlayer(display, sm);
        LEController.runLevelEditor(new SpriteLibrary(),
                                    new BackgroundLib(backgroundPath, filenames),
                                    ePlayer);

    }
}
