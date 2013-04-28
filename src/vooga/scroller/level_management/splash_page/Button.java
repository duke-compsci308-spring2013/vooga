package vooga.scroller.level_management.splash_page;

import java.awt.Dimension;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.util.ISpriteView;
import vooga.scroller.util.Pixmap;


public class Button extends Sprite {

    private static final Dimension MOUSE_DIMENSIONS = new Dimension(100, 100);
    private static final ISpriteView DEFAULT_IMAGE = new Pixmap("/vooga/scroller/images/",
                                                                "start_button.png");

    public Button () {
        super(DEFAULT_IMAGE, MOUSE_DIMENSIONS);
    }

}
