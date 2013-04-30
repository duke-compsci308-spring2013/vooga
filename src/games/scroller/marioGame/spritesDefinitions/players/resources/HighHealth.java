package games.scroller.marioGame.spritesDefinitions.players.resources;

import java.awt.Dimension;
import java.awt.Graphics2D;
import vooga.scroller.sprites.state.SpriteState;
import vooga.scroller.sprites.superclasses.Player;

public class HighHealth extends SpriteState<Player>{
    
    private static final int PRIORITY = Integer.MAX_VALUE;
    
    private static final double WIDTH_FACTOR = 1.5;
    private static final double HEIGHT_FACTOR = 2.0;

    private static final int STATE_ID = 200;

    public HighHealth(Player player){
        super(player);
    }
    
    @Override
    public void update (double elapsedTime, Dimension bounds) {
        // does nothing extra
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
        getUnit().scale(WIDTH_FACTOR, HEIGHT_FACTOR);
    }

    @Override
    public void deactivate () {
        getUnit().scale(1/WIDTH_FACTOR, 1/HEIGHT_FACTOR);
    }

    @Override
    public int getID () {
        return STATE_ID;
    }
}
