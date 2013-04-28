package vooga.scroller.level_editor.commands;

/**
 * Contains names of Commands to be called by the LEView.
 * 
 * @author Danny Goodman
 * 
 */
public class CommandConstants {

    /**
     * keyword for create Sprite command
     */
    public static final String CREATE_SPRITE = "createSprite";

    /**
     * keyword for delete Sprite command
     */
    public static final String DELETE_SPRITE = "deleteSprite";

    /**
     * keyword for create change background command
     */
    public static final String CHANGE_BACKGROUND = "changeBackground";

    /**
     * keyword for change grid size command
     */
    public static final String CHANGE_GRID_SIZE = "changeGridSize";

    /**
     * space constant
     */
    public static final String SPACE = " ";

    private CommandConstants () {
        // not instantiated
    }
}
