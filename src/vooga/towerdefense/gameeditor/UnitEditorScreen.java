package vooga.towerdefense.gameeditor;

import java.awt.Dimension;
import java.util.Map;


/**
 * UnitEditorScreen is responsible for helping
 * the game developer make units.
 * 
 * @author Angelica Schwartz
 */
public class UnitEditorScreen extends GameElementEditorScreen {

    /**
     * title constant.
     */
    private static final String TITLE_NAME = "UNIT ";
    /**
     * next screen constant.
     */
    private static final String NEXT_SCREEN_NAME = "WaveEditorScreen";

    /**
     * constructor.
     * 
     * @param size
     * @param controller
     */
    public UnitEditorScreen (Dimension size, GameEditorController controller) {
        super(size, controller, TITLE_NAME, NEXT_SCREEN_NAME);
    }

    /**
     * default serialized id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * adds a unit to the game.
     */
    @Override
    public void addElementToGame () {
        getController().addUnitToGame(getName(), getImagePath(), getAttributes(), getActions());
    }

}
