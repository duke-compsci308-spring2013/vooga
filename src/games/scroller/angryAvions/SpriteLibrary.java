package games.scroller.angryAvions;

import java.awt.Dimension;
import vooga.scroller.extra_resources.sprite_interfaces.IPlatform;
import vooga.scroller.level_editor.library.EncapsulatedSpriteLibrary;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.util.ISpriteView;


/**
 * 
 * @author srwareham, Challen
 * 
 */
public class SpriteLibrary extends EncapsulatedSpriteLibrary {
    public static final Dimension DEFAULT_SIZE = new Dimension(42, 42);
    public static final String IMAGES_DIRECTORY = "/games/scroller/angryAvions/images/";

    /**
     * This is the platform that all the characters walk along.
     * 
     */
    public static class Platform extends Sprite implements IPlatform {

        public Platform (ISpriteView image, Dimension size) {
            super(image, size);
            // TODO Auto-generated constructor stub
        }
    }
}
