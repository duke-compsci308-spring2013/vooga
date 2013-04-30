package vooga.scroller.sprites.animation.movement;

import java.awt.Dimension;
import java.awt.Graphics2D;
import util.Vector;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.state.SpriteState;
import vooga.scroller.util.ISpriteView;

public class SpriteMovement extends SpriteState<Sprite> {
    
    private static final int PRIORITY = 0;

    private static final int ID = 19;

    private ISpriteView myView;
    private double myDirection;
    private double mySpeed;
    
    public SpriteMovement(Sprite sp, ISpriteView view, double direction, double speed){
        super(sp);
        myView = view;
        myDirection = direction;
        mySpeed = speed;
    }
    

    @Override
    public void update (double elapsedTime, Dimension bounds) {
        // does nothing special
    }

    @Override
    public void paint (Graphics2D pen, double angle) {
        myView.paint(pen, getUnit().getCenter(), getUnit().getSize(), angle);
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


    @Override
    public int getID () {
        return ID;
    }

}
