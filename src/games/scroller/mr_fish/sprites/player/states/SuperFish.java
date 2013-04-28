package games.scroller.mr_fish.sprites.player.states;

import games.scroller.mr_fish.sprites.player.MrFish;
import java.awt.Dimension;
import java.awt.Graphics2D;
import vooga.scroller.sprites.state.SpriteState;

public class SuperFish extends SpriteState<MrFish> {

    public SuperFish (MrFish unit) {
        super(unit);
    }

    private static final int PRIORITY = Integer.MAX_VALUE;
    private static final Dimension SUPER_SIZE = new Dimension(100,100);
    private static final int HEALTH_MULITPLIER = 5;
    
    private Dimension myInitialSize; 
    
    
    @Override
    public void update (double elapsedTime, Dimension bounds) {
        // TODO Auto-generated method stub

    }

    @Override
    public void paint (Graphics2D pen, double angle) {
        // does nothing extra

    }

    @Override
    public int getPaintPriority () {
        return PRIORITY;
    }

    @Override
    public void activate () {
        myInitialSize = new Dimension(getUnit().getSize());
        getUnit().setSize((int)SUPER_SIZE.getWidth(), (int)SUPER_SIZE.getHeight());
        
        MrFish player = getUnit();
        player.setHealth(player.getHealth()*HEALTH_MULITPLIER);

    }

    @Override
    public void deactivate () {
        getUnit().setSize((int)myInitialSize.getWidth(), (int)myInitialSize.getHeight());        
        MrFish player = getUnit();       

        player.setHealth(MrFish.MR_FISH_HEALTH);        
    }

}
