package games.scroller.superMario.sprites;

import util.Vector;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.interfaces.Locatable;
import vooga.scroller.sprites.movement.Movement;


public class SimpleMovement implements Movement {

    private Sprite mySprite;
    private int mySpeed;
    private int myDirection;
    private int myCounter = 20;
    private Locatable myTarget;

    public SimpleMovement (Sprite sprite, Locatable locatable, int speed) {
        mySprite = sprite;
        myTarget = locatable;
        mySpeed = speed;
        myDirection = getDirection();
    }

    private int getDirection () {
        double i = mySprite.getCenter().getX() - myTarget.getCenter().getX();
        if (i > 0) { return Sprite.LEFT_DIRECTION; }
        return Sprite.RIGHT_DIRECTION;
    }

    @Override
    public void execute () {
        if (myCounter == 0) {
            mySprite.setVelocity(new Vector(getDirection(), mySpeed));
        }
        else {
            myDirection = getDirection();
        }
        myCounter--;
    }

}
