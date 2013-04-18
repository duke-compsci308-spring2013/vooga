package vooga.towerdefense.controller;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import vooga.towerdefense.attributes.AttributeManager;
import vooga.towerdefense.controller.modes.BuildMode;
import vooga.towerdefense.controller.modes.ControlMode;
import vooga.towerdefense.controller.modes.SelectMode;
import vooga.towerdefense.factories.ExampleUnitFactory;
import vooga.towerdefense.factories.WaveFactory;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.gameElements.Tower;
import vooga.towerdefense.gameElements.Wave;
import vooga.towerdefense.model.GameController;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.model.GameModel;
import vooga.towerdefense.model.Shop;
import vooga.towerdefense.model.Tile;
import vooga.towerdefense.util.Location;
import vooga.towerdefense.util.Pixmap;
import vooga.towerdefense.view.TDView;


/**
 * Controller is the channel of communication between
 *      the Model and the View.
 *
 * @author Angelica Schwartz
 * @author Erick Gonzalez
 * @author Leonard K. Ng'eno
 */
public class Controller {
    
    /**
     * location of resource bundle.
     */
    private static final String DEFAULT_RESOURCE_PACKAGE = "vooga/towerdefense/resources.";
    /**
     * resource bundle for this controller.
     */
    private ResourceBundle myResourceBundle;
    /**
     * model for this game.
     */
    private GameModel myModel;
    /**
     * view for this game.
     */
    private TDView myView;
    /**
     * control mode for the controller.
     */
    private ControlMode myControlMode;
    
    // TODO: controller constructor should take waves & map in order to initialize GameModel?
    // TODO: fix where the parameters come from
    public Controller (String language) {

        List<Wave> waves = new ArrayList<Wave>();
		
	GameMap map = new GameMap(null, 800, 600, null);
	waves.add(WaveFactory.createWave(new ExampleUnitFactory(),
				1, map, map.getTile(new Point(0, 0))));
	
	setLanguage(language);
        myModel = new GameModel(this, waves, map, new Shop());
        myView = new TDView(this);
        myControlMode = new SelectMode();
    }
    
    /**
     * cancels the purchase and stops painting ghost image.
     */
    public void cancelPurchaseFromShop() {
        myControlMode = new SelectMode();
    }

    /**
     * displays information about the GameElement on the tile.
     * 
     * @param p is the point that was clicked.
     */
    public void displayElementInformation (GameElement e) {
        if (e != null) {
            // TODO: update this to reflect actual properties
            myView.getGameElementInfoScreen().displayInformation("Stuff about my clicked tower");
            // myView.getTowerInfoScreen().displayInformation(e.getAttributes().toString());
            if (e instanceof Tower) {
                myView.getGameElementInfoScreen().displayUpgradesAndButton(((Tower) e).getUpgrades());
            }
        }
        else {
            myView.getGameElementInfoScreen().clearScreen();
        }
    }

    /**
     * updates the display on the MapScreen.
     */
    public void displayMap () {
        myView.getMapScreen().update();
    }

    /**
     * places the new item onto the map & changes the mode
     * back to SelectMode.
     * 
     * @param item
     * @param p
     */
 // TODO The item that should be added should be a new instance of the one in the shop!!!
    public void fixItemOnMap (GameElement item, Point p) {
        GameElement newItem = createNewElement(item);
        Tile myTile = myModel.getTile(p);
        myTile.setTower(newItem);
        myModel.getMap().addToMap(newItem, myTile); 
        displayMap();
        myControlMode = new SelectMode();
    }
    
    /**
     * 
     * @param item      Object to create new instance of
     * @return  new instance of item
     */
    private GameElement createNewElement(GameElement item) {
        try {
            Class<? extends GameElement> myClass = item.getClass();
            System.out.println("I AM: " + myClass.getName());       
            @SuppressWarnings("rawtypes")
            Class[] types = {Pixmap.class, Location.class, Dimension.class, AttributeManager.class, List.class};
            Constructor<? extends GameElement> constructor = myClass.getConstructor(types);
            Object[] parameters = {item.getPixmap(), item.getCenter(), item.getSize(), item.getAttributeManager(), item.getActions()};
            Object myNewItem = constructor.newInstance(parameters); 
            return (GameElement) myNewItem;
        }
        catch(InvocationTargetException e) {
            //??
        }
        
        catch(Exception e) {
            //??
        }
        return null;
    }
    /**
     * gets the associated game element at a point.
     * 
     * @param p
     * @return the game element
     */
    public GameElement getItemAt (Point p) {
        Tile tile = myModel.getTile(p);
        if (tile.containsElement()) { return tile.getElement(); }
        return null;
    }
    
    /**
     * gets the resource bundle for this controller.
     * @return the resource bundle
     */
    public ResourceBundle getResourceBundle() {
        return myResourceBundle;
    }
    
    /**
     * Get the matching string from the resource bundle.
     * @param s is the string to match
     * @return the appropriate string in the selected language
     */
    public String getStringFromResources(String s) {
        return myResourceBundle.getString(s);
    }

    /**
     * 
     * @return a map of the elements in the shop 
     * with String as a key and a Pixmap as the value
     */
    public Map<String, GameElement> getShopItemIcons () {
        return myModel.getShop().getAllShopItemIcons();
    }

    /**
     * handles a click to the map appropriately depending
     * on the mode.
     * 
     * @param p is the location of the click
     */
    public void handleMapMouseDrag (Point p) {
        myControlMode.handleMapMouseDrag(p, this);
    }

    /**
     * handles a mouse drag on the map appropriately depending
     * on the mode.
     * 
     * @param p is the location of the mouse
     */
    public void handleMapClick (Point p) {
        myControlMode.handleMapClick(p, this);
    }

    /**
     * changes the mode to BuildMode and gets the item the user
     * wants to build from the Shop.
     * 
     * @param itemName is the name of the item the user wants to
     *        buy
     */
    public void handleShopClickOnItem (String itemName) {
        GameElement itemToBuy = myModel.getShop().getShopItem(itemName);
        BuildMode myNewMode = new BuildMode();
        myNewMode.setItemToBuild(itemToBuy);    
        myControlMode = myNewMode;
    }
    
    /**
     * starts the next wave in the model.
     */
    public void startNextWave() {
    	myModel.startNextWave();
    }

    /**
     * paints the ghost image of the item on the MapScreen
     * on the mouse's location.
     * 
     * @param p is the mouselocation
     * @param itemImage is the image
     */
    public void paintGhostImage (Point p, Pixmap itemImage) {
        displayMap();
        myView.getMapScreen().paintGhostImage(p, itemImage);
    }

    /**
     * paints the map.
     * 
     * @param pen
     */
    public void paintMap (Graphics pen) {
        myModel.paintMap((Graphics2D) pen);
    }

    /**
     * updates the model.
     * 
     * @param elapsedTime
     */
    public void update (double elapsedTime) {
        myModel.update(elapsedTime);
    }

    /**
     * upgrades the item to the new type.
     * 
     * @param upgradeName
     */
    public void upgradeSelectedItemTo (String upgradeName) {
        Tower t = (Tower) ((SelectMode) myControlMode).getCurrentlySelectedItem();
        t.upgrade(upgradeName);
        // TODO: implement upgrade stuff on backend (ask unit team for tower upgrade info!)
    }
    
    /**
     * Sets the language
     * @param language the language to set the controller to
     */
    public void setLanguage (String language) {
        try {
            myResourceBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE
                                                       + language);
        }
        catch (MissingResourceException e) {
            e.printStackTrace();
        }
    }

    /**
     * Start the game controller.
     */
    public void start () {
        GameController game = new GameController(this);
        game.start();
    }
}
