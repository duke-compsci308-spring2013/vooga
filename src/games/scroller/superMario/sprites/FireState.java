package games.scroller.superMario.sprites;

import java.awt.Dimension;
import java.awt.Graphics2D;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.state.SpriteState;

public class FireState extends SpriteState<Sprite> {
    
    public static final int STATE_ID = 3;

    public FireState (Sprite unit) {
        super(unit);
    }

    @Override
    public void update (double elapsedTime, Dimension bounds) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void paint (Graphics2D pen, double angle) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getPaintPriority () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void activate () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deactivate () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getID () {
        // TODO Auto-generated method stub
        return STATE_ID;
    }

}
