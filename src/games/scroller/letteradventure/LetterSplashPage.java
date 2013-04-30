package games.scroller.letteradventure;

import java.util.ArrayList;
import java.util.List;
import util.input.Input;
import util.input.InputMethodTarget;
import vooga.scroller.level_management.splash_page.SplashPage;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.util.ISpriteView;
import vooga.scroller.view.GameView;

/**
 * The splash screen for LetterAdventure
 * 
 * @author Ellango, David Liu
 *
 */
public class LetterSplashPage extends SplashPage {
    
    public static final String CONTROLS_FILE_PATH =
            "games/scroller/letteradventure/SplashMapping";

    public LetterSplashPage (ISpriteView backgroundImage,
                             int splashID,
                             GameView gameView,
                             ScrollingManager sm) {
        super(backgroundImage, splashID, gameView, sm);
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
        return CONTROLS_FILE_PATH;
    }
    
    /**
     * Start the next level.
     */
    @InputMethodTarget(name = "start")
    public void nextLevel () {
        getDoor().goToNextLevel();
    }

}
