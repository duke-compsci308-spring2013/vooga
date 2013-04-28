package vooga.scroller.extra_resources.inventory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import games.scroller.mr_fish.sprites.player.MrFish;
import util.Location;
import util.input.InputClassTarget;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.statistics.Statistic;


/**
 * Represents a user inventory of items (sprites).
 * 
 * @author Scott Valentine
 * 
 */
@InputClassTarget
public class Inventory<O extends Player ,I extends Item> implements Statistic {

    private static final String NAME = "INVENTORY";

    private static final int COLUMNS = 10;

    private static final int MARGIN = 100;

    private static final int ROWS = 10;

    private static final Dimension BOX_SIZE = new Dimension(64, 64);

    private static final Color SELECTION_COLOR = Color.red;

    private List<I> myItems;
    private int myMoney;

    private int mySelection;

    private O myFish;

    /**
     * Creates a new inventory for the specified player.
     * 
     * @param player who owns this inventory.
     */
    public Inventory (O player) {
        myItems = new ArrayList<I>();
        mySelection = 0;
        myFish = player;
    }

    @Override
    public void addValue (int val) {
        myMoney += val;
    }

    @Override
    public void removeValue (int val) {
        myMoney -= val;

    }

    @Override
    public int getAggregateValue () {
        return myMoney;
    }

    /**
     * Gives the name of this inventory.
     */
    @Override
    public String getName () {
        return NAME;
    }

    /**
     * Adds an item to the end of this inventory.
     * 
     * @param item to be added to the inventory.
     */
    public void addItem (I item) {
        myItems.add(item);
    }

    /**
     * Paints the inventory. This paint it as row of items.
     * 
     * @param pen
     */
    public void paint (Graphics2D pen) {
        int row = 0;
        int column = 0;
        int count = 0;
        for (I item : myItems) {

            Location local =
                    new Location(column * BOX_SIZE.width + MARGIN, row * BOX_SIZE.height + MARGIN);

            item.getView().paint(pen, local, BOX_SIZE);

            if (count == mySelection - 1 || (mySelection == 0 && (count == myItems.size() - 1))) {
                Rectangle r = new Rectangle(BOX_SIZE);
                r.setLocation((int) local.x - BOX_SIZE.width / 2, (int) local.y - BOX_SIZE.height /
                                                                  2);
                pen.setColor(SELECTION_COLOR);
                pen.draw(r);
            }

            if (column < COLUMNS - 1) {
                column = column + 1;
            }
            else {
                column = 0;
                row = row + 1;
            }
            count += 1;
        }

    }

    /**
     * Select the next item in the inventory.
     */
    public void getNextItem () {
        mySelection += 1;
        if (mySelection >= myItems.size()) {
            mySelection = 0;
        }

    }

    /**
     * Select the previous item in the inventory.
     */
    public void getPreviousItem () {
        mySelection -= 1;
        if (mySelection < 0) {
            mySelection = myItems.size() - 1;
        }
    }

    /**
     * Selects and activates the current inventory selection.
     */
    public void selectCurrent () {
        I selection = myItems.get(mySelection);
        myItems.remove(mySelection);
        selection.useItem(myFish);
        if (mySelection == myItems.size() - 1) {
            mySelection = 0;
        }

    }

}
