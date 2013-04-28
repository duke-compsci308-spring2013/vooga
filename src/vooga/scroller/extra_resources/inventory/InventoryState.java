package vooga.scroller.extra_resources.inventory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import games.scroller.mr_fish.sprites.player.MrFish;
import util.Location;
import util.Text;
import vooga.scroller.sprites.state.SpriteState;

public class InventoryState extends SpriteState<MrFish> {

    private static final int PRIORITY = -1;
    public static final int STATE_ID = 99;
    private static final Text MENU_TEXT = new Text("INVENTORY (L to exit)");
    private static final Location TEXT_LOCATION = new Location(500, 200);
    private static final Color TEXT_COLOR = Color.GREEN;
    
    
    private Inventory myInventory;
    
    public InventoryState (MrFish unit, Inventory inventory) {
        super(unit);
        MENU_TEXT.setFont(Text.FONT_SERIF, 40);
        myInventory = inventory;
     
    }


    @Override
    public void update (double elapsedTime, Dimension bounds) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void paint (Graphics2D pen, double angle) {
        myInventory.paint(pen);
        getUnit().getView().paint(pen, getUnit().getPaintLocation(), getUnit().getSize());
        MENU_TEXT.paint(pen, TEXT_LOCATION, TEXT_COLOR);
    }

    @Override
    public int getPaintPriority () {
        return PRIORITY;
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
