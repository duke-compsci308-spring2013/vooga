package vooga.towerdefense.gameeditor;

import java.awt.Dimension;


/**
 * ProjectileEditorScreen is responsible for helping
 * the game developer make projectiles.
 * 
 * @author Angelica Schwartz
 */
public class ProjectileEditorScreen extends GameElementEditorScreen {

    /**
     * title constant.
     */
    private static final String TITLE_NAME = "PROJECTILE ";
    /**
     * next screen constant.
     */
    private static final String NEXT_SCREEN_NAME = "TowerEditorScreen";

    /**
     * constructor.
     * 
     * @param size
     * @param controller
     */
    public ProjectileEditorScreen (Dimension size, GameEditorController controller) {
        super(size, controller, TITLE_NAME, NEXT_SCREEN_NAME);
    }

    /**
     * default serialized id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * adds a projectile to the game.
     */
    @Override
    public void addElementToGame () {
        // TODO Auto-generated method stub
        getController().addProjectileToGame();
    }

}
