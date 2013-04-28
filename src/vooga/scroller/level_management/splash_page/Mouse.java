package vooga.scroller.level_management.splash_page;

import java.awt.Dimension;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.util.ISpriteView;
import vooga.scroller.util.Pixmap;


public class Mouse extends Sprite {

    private static final Pixmap DEFAULT_IMAGE = new Pixmap("/vooga/scroller/images/sun.png");
    private static final Dimension MOUSE_DIMENSIONS = new Dimension(40, 40);

    public Mouse (ISpriteView image) {
        super(image, MOUSE_DIMENSIONS);
    }

    public Mouse () {
        this(DEFAULT_IMAGE);
    }

}
