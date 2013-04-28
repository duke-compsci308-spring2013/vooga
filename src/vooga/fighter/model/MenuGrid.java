package vooga.fighter.model;

import java.util.List;
import vooga.fighter.model.loaders.MenuGridLoader;
import vooga.fighter.model.mode.MenuMode;
import vooga.fighter.model.objects.MenuObject;
/**
 * This class has been created to keep 
 * track of all the given instantiated menus.
 * It provides functionality to use the selected menu,
 * or switch to any cardinal direction of it using the given methods.
 * It's loader also loads the locations of the menus.
 * 
 * @author Jack Matteucci
 */

public class MenuGrid {
    private final static String UNSELECTED = "Unselected";
    private final static String SELECTED = "Selected";
    private List<MenuObject> myMenuObjects;
    private MenuObject myCurrentMenuObject;

    public MenuGrid (String Id, MenuMode delegate, String filepath) {
        MenuGridLoader menuGrid = new MenuGridLoader(Id, delegate, filepath);
        myMenuObjects = menuGrid.getMenuObjects();
        myCurrentMenuObject = menuGrid.getFirstMenuObject();
        myCurrentMenuObject.setCurrentState(SELECTED);
    }
    /**
     * returns all menu objects
     */
    public List<MenuObject> getMenuObjects () {
        return myMenuObjects;
    }
    /**
     * returns the current selected menu object
     */
    public MenuObject getCurrentObject () {
        return myCurrentMenuObject;
    }
    /**
     * returns the left menu of the current menu
     */
    public void left () {
        for (MenuObject menu : myMenuObjects) {
            if (myCurrentMenuObject.getLeft() == menu.getNum()) {
                myCurrentMenuObject.setCurrentState(UNSELECTED);
                myCurrentMenuObject = menu;
                myCurrentMenuObject.setCurrentState(SELECTED);
                return;
            }
        }
    }
    /**
     * returns the right menu of the current menu
     */
    public void right () {
        for (MenuObject menu : myMenuObjects) {
            if (myCurrentMenuObject.getRight() == menu.getNum()) {
                myCurrentMenuObject.setCurrentState(UNSELECTED);
                myCurrentMenuObject = menu;
                myCurrentMenuObject.setCurrentState(SELECTED);
                return;
            }
        }
    }
    /**
     * returns the up menu of the current menu
     */
    public void up () {
        for (MenuObject menu : myMenuObjects) {
            if (myCurrentMenuObject.getUp() == menu.getNum()) {
                myCurrentMenuObject.setCurrentState(UNSELECTED);
                myCurrentMenuObject = menu;
                myCurrentMenuObject.setCurrentState(SELECTED);
                return;
            }
        }
    }
    /**
     * returns the down menu of the current menu
     */
    public void down () {
        for (MenuObject menu : myMenuObjects) {
            if (myCurrentMenuObject.getDown() == menu.getNum()) {
                myCurrentMenuObject.setCurrentState(UNSELECTED);
                myCurrentMenuObject = menu;
                myCurrentMenuObject.setCurrentState(SELECTED);
                return;
            }
        }
    }

}
