package games.scroller.superMario;

import util.Vector;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.util.physics.Force;


public class MarioGravity implements Force {

    private static final int GRAVITY_CONSTANT = -15;
    Sprite mySprite;
    private Vector myVelocityVector;

    public MarioGravity (Sprite sprite) {
        mySprite = sprite;
        myVelocityVector = new Vector(Sprite.UP_DIRECTION, GRAVITY_CONSTANT);
    }

    @Override
    public void apply () {
        mySprite.addVector(myVelocityVector);
    }

}
