package games.scroller.stickmansam;

import java.util.List;
import util.input.Input;
import vooga.scroller.level_management.splash_page.SplashPage;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.util.ISpriteView;
import vooga.scroller.view.GameView;

public class StickmanSplashPage extends SplashPage {

    public StickmanSplashPage (ISpriteView backgroundImage,
                               int splashID,
                               GameView gameView,
                               ScrollingManager sm) {
        super(backgroundImage, splashID, gameView, sm);
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<Sprite> getSprites () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addInputListeners (Input input) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeInputListeners (Input input) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getInputFilePath () {
        // TODO Auto-generated method stub
        return null;
    }

}
