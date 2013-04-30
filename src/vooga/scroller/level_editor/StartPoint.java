package vooga.scroller.level_editor;

import java.awt.Dimension;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.util.Pixmap;


/**
 * StartPoint is a subclass of Sprite that is not seen in an actual game.
 * It is basically just a carrier of the StartPoint Location while also
 * containing a Pixmap Image so that it can be displayed on screen while
 * creating a level. When stored, the Location is extracted and the sprite itself
 * left behind. For this reason, it does not have any functionality beyond that
 * of a sprite.
 * 
 * @author Danny Goodman
 *
 */
public class StartPoint extends Sprite {

    private static final String DEFAULT_FILENAME = "startPoint.png";
    private static final String DEFAULT_PATH = "/vooga/scroller/images/";
    private static final Pixmap DEFAULT_IMAGE = new Pixmap(DEFAULT_PATH, DEFAULT_FILENAME);
    private static final Dimension DEFAULT_SIZE = new Dimension(32, 32);

    /**
     * Creates a StartPoint Sprite with the default image.
     */
    public StartPoint () {
        super(DEFAULT_IMAGE, DEFAULT_SIZE);
    }
}
