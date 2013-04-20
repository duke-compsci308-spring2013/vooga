package vooga.towerdefense.gameeditor;

import java.awt.Dimension;

public class LevelEditorScreen extends GameEditorScreen {

    /**
     * default serialized id.
     */
    private static final long serialVersionUID = 1L;
    private static final String NEXT_SCREEN_NAME = "vooga.towerdefense.gameeditor.FinishedScreen";
    private static final String TITLE_NAME = "LEVEL ";
    
    public LevelEditorScreen(Dimension size, GameEditorController controller) {
        super(size, controller, TITLE_NAME, NEXT_SCREEN_NAME);
    }

    @Override
    public void addElementToGame () {
        // TODO Auto-generated method stub
        getController().addLevelToGame();
    }
}
