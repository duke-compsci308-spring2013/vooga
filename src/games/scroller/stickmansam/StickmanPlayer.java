package games.scroller.stickmansam;

import java.awt.Dimension;
import vooga.scroller.level_editor.Level;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.util.ISpriteView;
import vooga.scroller.view.GameView;

public class StickmanPlayer extends Player {

   public StickmanPlayer (GameView gameView,
                           ScrollingManager sm) {
        super(null, null, gameView, sm, 0, 0);
        // TODO Auto-generated constructor stub
    }

    @Override
    public String getInputFilePath () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleDeath (Level level) {
        // TODO Auto-generated method stub

    }
    /**
     * ISpriteView image,
                           Dimension size,
                           GameView gameView,
                           ScrollingManager sm,
                           int health,
                           int damage
     */

}
