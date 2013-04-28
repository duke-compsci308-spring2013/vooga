package games.scroller.marioGame.spritesDefinitions.players.resources;

import java.awt.Dimension;
import java.awt.Graphics2D;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.state.SpriteState;

public class InvisibleState extends SpriteState<Sprite>{
    
    public InvisibleState (Sprite unit) {
        super(unit);
        // TODO Auto-generated constructor stub
    }

    private static final int PRIORITY = Integer.MIN_VALUE;

    
    @Override
    public void update (double elapsedTime, Dimension bounds) {
        // does nothing extra        
    }

    @Override
    public void paint (Graphics2D pen, double angle) {
        // Invisible, do not paint.
    }

    @Override
    public int getPaintPriority () {
        return PRIORITY;
    }

    @Override
    public void activate () {
        // nothign special
        
    }

    @Override
    public void deactivate () {
        // nothing special
        
    }



}
