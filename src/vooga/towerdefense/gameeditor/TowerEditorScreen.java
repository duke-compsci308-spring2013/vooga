package vooga.towerdefense.gameeditor;

import java.awt.Dimension;


/**
 * TowerEditorScreen is responsible for helping
 * the game developer make towers.
 * 
 * @author Angelica Schwartz
 */
public class TowerEditorScreen extends GameElementEditorScreen {

    /**
     * title constant.
     */
    private static final String TITLE_NAME = "TOWER ";
    /**
     * next screen constant.
     */
    private static final String NEXT_SCREEN_NAME = "UnitEditorScreen";

    /**
     * constructor.
     * 
     * @param size
     * @param controller
     */
    public TowerEditorScreen (Dimension size, GameEditorController controller) {
        super(size, controller, TITLE_NAME, NEXT_SCREEN_NAME);
    }

    /**
     * default serialized id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * adds a tower to the game.
     */
    @Override
    public void addElementToGame () {
        // TODO Auto-generated method stub
        getController().addTowerToGame();
    }

}
