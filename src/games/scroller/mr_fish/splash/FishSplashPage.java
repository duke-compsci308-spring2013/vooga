package games.scroller.mr_fish.splash;

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

@InputClassTarget
public class FishSplashPage extends SplashPage {

    public static final String CONTROLS_FILE_PATH =
            "games/scroller/mr_fish/controls/SplashMapping";
    
    public FishSplashPage (ISpriteView backgroundImage,
                           int splashID,
                           GameView gameView,
                           ScrollingManager sm) {
        super(backgroundImage, splashID, gameView, sm);
        // TODO Auto-generated constructor stub
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
    
    /**
     * Exit the game when on the splash page.
     */
    @InputMethodTarget(name = "exit")
    public void exit() {
        System.exit(-1);
    }
    
    @Override
    public List<Sprite> getSprites () {
        return new ArrayList<Sprite>();
    }

}
