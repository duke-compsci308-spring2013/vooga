package vooga.scroller.level_editor.library;

import java.util.HashMap;
import java.util.Map;
import vooga.scroller.util.IBackgroundView;
import vooga.scroller.util.Pixmap;


/**
 * BackgroundLib creates a map of integer ids to Images for the filepath and
 * file names of backgrounds specified upon creation.
 * 
 * @author Danny Goodamn
 * 
 */
public class BackgroundLib implements IBackgroundLibrary {

    private Map<Integer, IBackgroundView> myPixmaps;

    /**
     * Creates the map of Backgrounds to an id from a file path and filenames.
     * 
     * @param backgroundLocation - file path
     * @param fileNames - background image file names
     */
    public BackgroundLib (String backgroundLocation, String[] fileNames) {
        int i = 0;
        myPixmaps = new HashMap<Integer, IBackgroundView>();
        for (String fn : fileNames) {
            Pixmap p = new Pixmap(backgroundLocation, fn);
            myPixmaps.put(i, p);
            i++;
        }
    }

    @Override
    public Map<Integer, IBackgroundView> getBackgrounds () {
        return myPixmaps;
    }

}
