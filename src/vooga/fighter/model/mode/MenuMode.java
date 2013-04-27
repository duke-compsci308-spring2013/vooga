package vooga.fighter.model.mode;

import java.awt.Dimension;
import java.util.List;
import vooga.fighter.model.MenuGrid;
import vooga.fighter.model.objects.GameObject;
import vooga.fighter.model.objects.MenuObject;
import vooga.fighter.util.CollisionManager;


/**
 * Mode implementation for a menu. It serves to hold all the menus,
 * but delegates handling their organization the menu grid.  It also
 * keeps track of the current choice, giving the controller the 
 * appropriate methods to get the choice, and reset the choice.
 * 
 * @author Jack Matteucci
 * @modified Jerry Li
 * 
 */
public class MenuMode extends Mode {
	private static final int THREE_TICKS = 3;
    private String myMenuName;
    private List<MenuObject> myMenuObjects;
    private MenuGrid myMenuGrid;
    private String myChoice;
    private int myInputTicks;

    public MenuMode (CollisionManager manager, String menuName) {
        super(manager);
        myMenuName = menuName;
        myChoice = "";
        myInputTicks = 0;
    }

    @Override
    public void update () {
        removeAppropriateObjects();
        List<GameObject> myObjects = getMyObjects();
        for (GameObject object : myObjects) {
            object.update();
        }
        for (GameObject object : myObjects) {
            object.updateState();
        }
        handleCollisions();
        myInputTicks++;
    }
    /**
     * Sets the menu Grid for menu, used by controller
     */
    public void setMenuGrid (MenuGrid grid) {
        myMenuGrid = grid;
    }
/**
 * Returns the Name of the Menu
 */
    public String getName () {
        return myMenuName;
    }
    /**
     * Sets the menu objects, used by controller
     */
    public void setMenuObjects (List<MenuObject> menus) {
        myMenuObjects = menus;
        for (MenuObject menu : menus) {
            addObject(menu);
        }
    }
    /**
     * Returns the "Next" variable of the menu with the 
     * value matching the parameter
     */
    public String getMenusNext (String value) {
        for (MenuObject menu : myMenuObjects) {
            if (menu.getValue().equals(value)) return menu.getNext();
        }
        return "";
    }
    /**
     * Sets the Menu Choice selected
     */
    public void setChoice (String choice) {
        myChoice = choice;
    }
    /**
     * switches to the left menu of the current menu
     */
    public void left () {
        myMenuGrid.left();
    }
    /**
     * returns the up menu of the current menu
     */
    public void up () {
        myMenuGrid.up();
    }
    /**
     * returns the down menu of the current menu
     */
    public void down () {
        myMenuGrid.down();
    }
    /**
     * returns the right menu of the current menu
     */
    public void right () {
        myMenuGrid.right();
    }
    /**
     * returns choice
     */
    public void resetChoice () {
        myChoice = "";
    }
    /**
     * return choice, with no effects to the choice
     */
    public String peekChoice () {
        return myChoice;
    }
    /**
     * returns the current menu selected
     */
    public MenuObject getCurrentMenu () {
        return myMenuGrid.getCurrentObject();
    }
    /**
     * A method to make the switching using 
     * arrow keys work!
     */
    public boolean inputReady () {
        if (myInputTicks > THREE_TICKS) {
            myInputTicks = 0;
            return true;
        }
        return false;
    }

}
