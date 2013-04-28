package vooga.towerdefense.gameeditor.gamemaker.editorscreens;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import vooga.towerdefense.gameeditor.controller.GameEditorController;


/**
 * FinishScreen helps the game developer save the
 *      game he/she has been editing to a file.
 * 
 * @author Angelica Schwartz
 */
public class FinishScreen extends GameEditorScreen {

    /**
     * default serialized id.
     */
    private static final long serialVersionUID = 1L;
    private static final String FINISH_TEXT = "Finish & save file";
    private static final String TITLE_NAME = "FINISH";
    private static final String NEXT_SCREEN_NAME = null;
    private JButton myFinishButton;

    /**
     * Constructor.
     * @param size
     * @param controller
     */
    public FinishScreen (Dimension size,
                         GameEditorController controller) {
        super(size, controller, TITLE_NAME, NEXT_SCREEN_NAME);
        myFinishButton = new JButton(FINISH_TEXT);
        myFinishButton.addMouseListener(getMouseAdapter());
        add(myFinishButton);
    }

    @Override
    public void addElementToGame () {
        // do nothing
    }

    /**
     * adds additional behavior for a mouse click.
     * @param e is a MouseEvent
     */
    @Override
    public void addAdditionalMouseBehavior (MouseEvent e) {
        if (e.getSource().equals(myFinishButton)) {
            getController().saveFile();
        }
    }

}
