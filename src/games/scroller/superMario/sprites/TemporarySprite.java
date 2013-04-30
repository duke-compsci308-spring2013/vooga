package games.scroller.superMario.sprites;

import java.awt.Dimension;
import vooga.scroller.sprites.superclasses.GameCharacter;
import vooga.scroller.util.ISpriteView;


public abstract class TemporarySprite extends GameCharacter {

    public TemporarySprite (ISpriteView image, Dimension size, double d) {
        super(image, size, (int) d, 0);
    }

    @Override
    public void update (double timeElapsed, Dimension bounds) {
        super.update(timeElapsed, bounds);
        setHealth((int) (getHealth() - timeElapsed));
    }

}
