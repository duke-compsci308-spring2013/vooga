package vooga.scroller.sprites.test_sprites.pikachu.animation_states;

import vooga.scroller.sprites.animation.AnimationState;
import vooga.scroller.util.Pixmap;
import vooga.scroller.util.Sprite;

public class Stand extends AnimationState {

    private static final Pixmap STAND = new Pixmap("standleft.png");
    
    public Stand () {
        super(STAND);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean validAnimation (Sprite unit) {
        return unit.getCenter().equals(unit.lastLocation());
    }

}
