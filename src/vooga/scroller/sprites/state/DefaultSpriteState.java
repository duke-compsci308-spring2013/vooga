package vooga.scroller.sprites.state;

import java.awt.Dimension;
import java.awt.Graphics2D;
import util.Vector;
import vooga.scroller.sprites.Sprite;

public class DefaultSpriteState extends SpriteState<Sprite>{

    private static final int PRIORITY = Integer.MAX_VALUE-1;
    public static final int DEFAULT_ID = -1;

    
    public DefaultSpriteState(Sprite sp){
        super(sp);
    }
    
    @Override
    public void update (double elapsedTime, Dimension bounds) {
        getUnit().updateLastLocation();
        
        // do not change original velocity
        Vector v = new Vector(getUnit().getVelocity());
        v.scale(elapsedTime);
        getUnit().translate(v);
        
    }

    @Override
    public void paint (Graphics2D pen, double angle) {
        getUnit().getView().paint(pen, getUnit().getCenter(), getUnit().getSize(), angle);
    }


    @Override
    public int getPaintPriority () {
        return PRIORITY;
    }

    @Override
    public void activate () {
        // does nothing special
        
    }

    @Override
    public void deactivate () {
        // does nothing special
        
    }

}
