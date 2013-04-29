package games.scroller.stickmansam;

import vooga.scroller.level_editor.library.EncapsulatedSpriteLibrary;
import vooga.scroller.util.Pixmap;

public class StickmanSpriteLibrary extends EncapsulatedSpriteLibrary {

    private static final String IMAGES = "/src/games/scroller/stickmansam/images/";

    
    /**
     * Helper method to create Pixmaps from filepaths.
     * 
     * @author Letter Adventure team
     * @param fileName
     * @return
     */
    public static Pixmap makePixmap (String fileName) {
        return makePixmap(IMAGES, fileName);
    }
}
