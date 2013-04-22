package vooga.towerdefense.gameeditor;

import java.awt.Dimension;
import java.awt.event.MouseEvent;


/**
 * LevelEditorScreen is responsible for helping
 * the game developer make levels.
 * 
 * @author Angelica Schwartz
 */
public class LevelEditorScreen extends GameEditorScreen {

    /**
     * default serialized id.
     */
    private static final long serialVersionUID = 1L;
    /**
     * next screen constant.
     */
    private static final String NEXT_SCREEN_NAME = "FinishScreen";
    /**
     * title constant.
     */
    private static final String TITLE_NAME = "LEVEL ";

    /**
     * Constructor.
     * 
     * @param size
     * @param controller
     */
    public LevelEditorScreen (Dimension size, GameEditorController controller) {
        super(size, controller, TITLE_NAME, NEXT_SCREEN_NAME);
    }

    /**
     * adds this level to the game.
     */
    @Override
    public void addElementToGame () {
        // TODO Auto-generated method stub
        getController().addLevelToGame();
    }

    /**
     * adds additional mouse behavior specific
     * to the LevelEditorScreen.
     * 
     * @param e is the MouseEvent
     */
    @Override
    public void addAdditionalMouseBehavior (MouseEvent e) {
        // fill in with appropriate mouse behavior
    }
}
