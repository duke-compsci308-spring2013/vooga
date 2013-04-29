package vooga.scroller.kirbyGame.spritesDefinitions.players.states;

import java.awt.Dimension;
import java.awt.Graphics2D;
import vooga.scroller.kirbyGame.spritesDefinitions.KirbyLib;
import vooga.scroller.kirbyGame.spritesDefinitions.players.Kirby;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.state.SpriteState;

public class InhaleLeftState extends SpriteState<Sprite>{

    private static final String DEFAULT_IMG = "kirbyinhaleL.gif";
    public static int STATE_ID = 6;
    private Kirby myKirby;


    public InhaleLeftState (Sprite unit) {
        super(unit);
        myKirby.setStateID(STATE_ID);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void update (double elapsedTime, Dimension bounds) {
        // TODO Auto-generated method stub
        
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
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deactivate () {
        // TODO Auto-generated method stub
        
    }

}
