package vooga.scroller.extra_resources.inventory;

import java.awt.Dimension;
import vooga.scroller.sprites.superclasses.GameCharacter;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.util.ISpriteView;


public abstract class Item extends GameCharacter {

    private static final int DEFAULT_ITEM_DAMAGE = 0;
    private static final int DEFAULT_ITEM_HEALTH = 1;

    // private static final Pixmap EXPLODED_ITEM = FishLib.makePixmap(FishLib.IMAGE_LOCATION,
    // "explosion.gif");

    public Item (ISpriteView image, Dimension size) {
        super(image, size, DEFAULT_ITEM_HEALTH, DEFAULT_ITEM_DAMAGE);
    }

    @Override
    public void handleDeath (vooga.scroller.level_editor.Level level) {
        // TODO Auto-generated method stub

    }

    public abstract int getValue ();

    public abstract void useItem (Player sprite);

}
