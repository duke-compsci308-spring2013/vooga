package games.scroller.mr_fish.sprites.player.states;

import java.awt.Dimension;
import java.awt.Graphics2D;
import util.Vector;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.state.SpriteState;

public abstract class Tilt extends SpriteState<Sprite> {

    private static final int PRIORITY = Integer.MAX_VALUE;

    private static final double ROTATION_BUFFER =20;
    
    private double myRateOfRotation;
   
    private double myDirection;
    private double mySpeed;

    
    public Tilt (Sprite unit, double rateOfRotation, double direction, double speed) {
        super(unit);
        myRateOfRotation = rateOfRotation;
        myDirection = direction;
        mySpeed = speed;
    }


    @Override
    public void update (double elapsedTime, Dimension bounds) {
        
        Sprite unit = getUnit();
        
        double movingDirection = unit.lastLocation().getX() - unit.getCenter().getX();
        double rotation = elapsedTime*myRateOfRotation;

        
        if(movingDirection > 0){
            rotation = -rotation;
        }

        
    }

    @Override
    public void paint (Graphics2D pen, double angle) {
        // nothing different
        
    }

    @Override
    public int getPaintPriority () {
        return PRIORITY;
    }

    @Override
    public void activate () {
        Vector component = getUnit().getVelocity().getComponentVector(myDirection);
        component.negate();
        getUnit().addVector(component);
        getUnit().addVector(new Vector(myDirection, mySpeed));
        
    }

    @Override
    public void deactivate () {
        Vector component = getUnit().getVelocity().getComponentVector(myDirection);
        component.negate();
        getUnit().addVector(component);
        
    }

}
