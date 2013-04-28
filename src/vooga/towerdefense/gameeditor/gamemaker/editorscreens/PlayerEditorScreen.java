package vooga.towerdefense.gameeditor.gamemaker.editorscreens;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import vooga.towerdefense.gameeditor.controller.GameEditorController;
import vooga.towerdefense.gameeditor.gamemaker.editorscreens.subeditorscreens.AttributeSection;

/**
 * PlayerEditorScreen helps the game developer edit
 *      default attributes for the player.
 *
 * @author Angelica Schwartz
 */
public class PlayerEditorScreen extends GameEditorScreen {

    /**
     * default serialized id.
     */
    private static final long serialVersionUID = 1L;
    /**
     * title constant.
     */
    private static final String TITLE_NAME = "PLAYER ";
    /**
     * next screen constant.
     */
    private static final String NEXT_SCREEN_NAME = "GameElementEditorScreen";
    /**
     * package for the attributes.
     */
    private static final String ATTRIBUTES_CLASS_PATH = "vooga.towerdefense.attributes.AttributeConstantsEnum"; 
    /**
     * attribute section for the player.
     */
    private AttributeSection myAttributesSection;
    
    /**
     * constructor.
     * @param size
     * @param controller
     * @throws ClassNotFoundException
     */
    public PlayerEditorScreen (Dimension size,
                               GameEditorController controller) throws ClassNotFoundException {
        super(size, controller, TITLE_NAME, NEXT_SCREEN_NAME );
        myAttributesSection = new AttributeSection("Attributes", getController().getAttributes(ATTRIBUTES_CLASS_PATH), getMouseAdapter());
        add(myAttributesSection, BorderLayout.CENTER);
    }

    /**
     * adds the player to the game.
     */
    @Override
    public void addElementToGame () {
        getController().addPlayerToGame(myAttributesSection.getAttributes());        
    }

    /**
     * does any additional mouse behavior for the PlayerEditorScreen.
     */
    @Override
    public void addAdditionalMouseBehavior (MouseEvent e) {
        myAttributesSection.doAdditionalMouseBehavior(e);        
    }

}
