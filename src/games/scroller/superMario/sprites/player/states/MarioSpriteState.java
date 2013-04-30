package games.scroller.superMario.sprites.player.states;

import games.scroller.superMario.sprites.player.Mario;
import java.awt.Dimension;
import java.awt.Graphics2D;
import vooga.scroller.sprites.animation.Animation;
import vooga.scroller.sprites.state.SpriteState;

public class MarioSpriteState extends SpriteState<Mario> {

    private Animation myAnimation;

    public MarioSpriteState (Mario unit) {
        super(unit);
    }
    
    public MarioSpriteState (Mario unit, Animation a) {
        this(unit);
        myAnimation = a;
    }
    
    protected void setAnimation(Animation a) {
        myAnimation = a;
    }

    @Override
    public void update (double elapsedTime, Dimension bounds) {
        
    }

    @Override
    public void paint (Graphics2D pen, double angle) {
        getUnit().getView().paint(pen, getUnit().getCenter(), getUnit().getSize(), angle);
        
    }

    @Override
    public int getPaintPriority () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void activate () {
        
    }

    @Override
    public void deactivate () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getID () {
        // TODO Auto-generated method stub
        return 0;
    }

    public void activateAnimationState (int stateId) {
        myAnimation.activateAnimationState(stateId);
    }

    public void deactivateAnimationState (int stateId) {
        myAnimation.deactivateAnimationState(stateId);
    }

}
