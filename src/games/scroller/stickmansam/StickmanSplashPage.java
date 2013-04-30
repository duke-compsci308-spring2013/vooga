package games.scroller.stickmansam;

import games.scroller.stickmansam.StickmanSpriteLibrary.Door;
import java.util.ArrayList;
import java.util.List;
import util.input.Input;
import util.input.InputClassTarget;
import util.input.InputMethodTarget;
import vooga.scroller.level_management.splash_page.SplashPage;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.util.ISpriteView;
import vooga.scroller.view.GameView;


/**
 * Splash page for the Stickman Sam game
 * 
 * @author David Winegar
 * 
 */
@InputClassTarget
public class StickmanSplashPage extends SplashPage {

    private static final String KEYMAP = "games/scroller/stickmansam/SplashMapping";

    public StickmanSplashPage (ISpriteView backgroundImage,
                               int splashID,
                               GameView gameView,
                               ScrollingManager sm) {
        super(backgroundImage, splashID, gameView, sm);
        addDoor(new Door());
    }

    /**
     * Start the next level.
     */
    @InputMethodTarget(name = "start")
    public void start () {
        getDoor().goToNextLevel();
    }

    @Override
    public List<Sprite> getSprites () {
        return new ArrayList<Sprite>();
    }

    @Override
    public void addInputListeners (Input input) {
        input.replaceMappingResourcePath(getInputFilePath());
        input.addListenerTo(this);
    }

    @Override
    public void removeInputListeners (Input input) {
        input.removeListener(this);
    }

    @Override
    public String getInputFilePath () {
        return KEYMAP;
    }

}
