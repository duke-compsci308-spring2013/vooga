package vooga.scroller.kirbyGame.spritesDefinitions.players.states;

import java.awt.Dimension;
import java.awt.Graphics2D;
import vooga.scroller.kirbyGame.spritesDefinitions.KirbyLib;
import vooga.scroller.kirbyGame.spritesDefinitions.players.Kirby;
import vooga.scroller.sprites.Sprite;

import vooga.scroller.sprites.state.SpriteState;

public class CutterAttackLeftState extends SpriteState<Sprite>{

    private static final String DEFAULT_IMG = "kirbycutterattackL.gif";
    public static int STATE_ID = 13;
    private Kirby myKirby;


    public CutterAttackLeftState (Sprite unit) {
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

        
    }

    @Override
    public void deactivate () {
        myKirby.setStateID(KirbyCutterWalkLeftState.STATE_ID);
    }
}
