package vooga.scroller.extra_resources.movements;

import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.movement.Movement;

/**
 * Movement based on time moving in one direction.
 * 
 * @author Scott Valentine
 *
 */
public class TimedMovement implements Movement{

    private Sprite mySprite;
    private double myTimeLimit;
    private double myAngle;
    private double mySpeed;
    private double myTimer;
    
    
    public TimedMovement(Sprite sp, double timeDelay, double angle, double speed){
        mySprite = sp;
        myTimeLimit = timeDelay;
        myAngle = angle;
        mySpeed = speed;
        myTimer= 0;
        
        setInitialiVelocity();
        
    }
    
    
    private void setInitialiVelocity () {
        mySprite.setVelocity(myAngle, mySpeed);
    }


    @Override
    public void execute () {
        myTimer +=1;      
        if(myTimer >= myTimeLimit){
            flipSprite();
            myTimer = 0;
        }        
    }


    private void flipSprite () {
        mySprite.getVelocity().negate();      
    }

}
