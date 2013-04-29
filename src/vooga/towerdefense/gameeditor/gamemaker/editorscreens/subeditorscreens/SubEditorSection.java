package vooga.towerdefense.gameeditor.gamemaker.editorscreens.subeditorscreens;

import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 * Superclass to make a subcomponent of the editor
 *      screens. 
 *
 * @author Angelica Schwartz
 */
public abstract class SubEditorSection extends JPanel {
    
    /**
     * default serialized id.
     */
    private static final long serialVersionUID = 1L;
    /**
     * constant for text area height.
     */
    public static final int TEXT_AREA_HEIGHT = 25;
    /**
     * constant for text area width.
     */
    public static final int TEXT_AREA_WIDTH = 10;
    /**
     * title for this screen.
     */
    private String myTitle;
    
    /**
     * constructor.
     * @param title
     */
    public SubEditorSection(String title) {
        myTitle = title;
    }
    
    /**
     * gets the title for this section.
     * @return the title as a string
     */
    public String getTitle() {
        return myTitle;
    }
    
    /**
     * clears the section.
     */
    public abstract void clear();
    
    /**
     * does any additional mouse behavior for this section.
     * @param e is the mouseevent
     */
    public abstract void doAdditionalMouseBehavior(MouseEvent e);

}
