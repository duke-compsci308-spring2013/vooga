package vooga.scroller.level_editor.library;

import java.util.Map;
import vooga.scroller.util.IBackgroundView;


/**
 * Interface for all Background Libraries. contains functionality to get
 * a map of Integer to IBackgrounView images.
 * 
 * @author Danny Goodman
 * 
 */
public interface IBackgroundLibrary {

    /**
     * Get the Map of backgrounds for this Background Library.
     * 
     * @return map of backgrounds
     */
    public Map<Integer, IBackgroundView> getBackgrounds ();
}
