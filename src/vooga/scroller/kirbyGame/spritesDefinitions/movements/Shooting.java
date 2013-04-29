package vooga.scroller.kirbyGame.spritesDefinitions.movements;

import util.Vector;
import vooga.scroller.kirbyGame.spritesDefinitions.KirbyLib.KirbyLaser;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.movement.Movement;

public class Shooting implements Movement {

    private int mySpeed;
    private boolean myRight; 
    private Sprite myLaser;
    private Vector myVelocity;
    
    public Shooting(Sprite laser, int speed, boolean right) {
        super();
        mySpeed = speed;
        myRight = right;
        myLaser = laser;
        if (right) {
            myVelocity.setDirection(Sprite.RIGHT_DIRECTION);
        }
        else {
            myVelocity.setDirection(Sprite.LEFT_DIRECTION);
        }
        
        myVelocity.setMagnitude(speed);
        myLaser.setVelocity(myVelocity);
        
    }
    @Override
    public void execute () {
        // TODO Auto-generated method stub
        
    }

}
