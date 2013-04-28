package arcade.view.forms;

import javax.swing.JFileChooser;

/**
 * An interface representing what can be done on a file chooser action.
 * 
 * This interface is a single-function object, likely to be implemented by a 
 * anonymous class.
 * 
 * @author Ellango
 *
 */
public interface FileChooserAction {
    /**
     * The action that should be done on approval of the file chooser.
     */
    abstract void approve(JFileChooser chooser);
}
