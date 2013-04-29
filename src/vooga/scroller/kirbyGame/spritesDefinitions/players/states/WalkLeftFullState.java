package vooga.scroller.kirbyGame.spritesDefinitions.players.states;

import java.awt.Dimension;
import java.awt.Graphics2D;
import util.Vector;
import vooga.scroller.kirbyGame.spritesDefinitions.KirbyLib;
import vooga.scroller.kirbyGame.spritesDefinitions.players.Kirby;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.animation.movement.MoveLeft;
import vooga.scroller.sprites.animation.state_movement.MoveLeftState;
import vooga.scroller.sprites.state.SpriteState;

public class WalkLeftFullState extends SpriteState<Sprite>{

    private static final String DEFAULT_IMG = "kirbywalkfullL.gif";
    public static int STATE_ID = 8;
    private Kirby myKirby;


    public WalkLeftFullState (Sprite unit) {
        super(unit);
        myKirby = (Kirby) unit;
        // TODO Auto-generated constructor stub
    }

    @Override
    public void update (double elapsedTime, Dimension bounds) {

        
        
    }

    @Override
    public void paint (Graphics2D pen, double angle) {
        getUnit().setView(KirbyLib.makePixmap(DEFAULT_IMG));
        getUnit().getView().paint(pen, getUnit().getCenter(), getUnit().getSize());            
    }

    @Override
    public int getPaintPriority () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void activate () {
        myKirby.setStateID(STATE_ID);        
        Vector component = getUnit().getVelocity().getComponentVector(Sprite.LEFT_DIRECTION);
        component.negate();
        getUnit().addVector(component);
        getUnit().addVector(new Vector(Sprite.LEFT_DIRECTION, 40));
        
    }

    @Override
    public void deactivate () {
        Vector component = getUnit().getVelocity().getComponentVector(Sprite.LEFT_DIRECTION);
        component.negate();
        getUnit().addVector(component);  
    }

}
