package vooga.towerdefense.model;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;

import vooga.towerdefense.controller.Controller;
import vooga.towerdefense.gameElements.Wave;
import vooga.towerdefense.model.shop.Shop;
import vooga.towerdefense.model.shop.ShopItem;
import vooga.towerdefense.model.tiles.Tile;


/**
 * GameModel holds all of the state and behavior for a particular game of
 * towerdefense
 * 
 * @author Jimmy Longley
 * @author Erick Gonzalez
 */
public class GameModel {
    private Controller myController;
    private List<Wave> myWaves;
    private GameMap myGameMap;
    private Wave myCurrentWave;
    private Shop myShop;

    /**
     * 
     * @param controller a controller
     * @param waves a list of wave objects
     * @param gameMap a game map
     * @param shop a shop
     */
    public GameModel (Controller controller, List<Wave> waves, GameMap gameMap, Shop shop) {
        myController = controller;
        myWaves = waves;
        myGameMap = gameMap;
        myShop = shop;
        myCurrentWave = waves.get(0);
    }

    /**
     * Given a point p, returns the tile object that contains this point.
     * 
     * @param p a point
     * @return a tile at the given point
     */
    public Tile getTile (Point p) {
        return myGameMap.getTile(p);
    }

    /**
     * Updates the game during an iteration of the game loop.
     * 
     * @param elapsedTime time elapsed since last clock tick
     */
    public void update (double elapsedTime) {
        updateWave(elapsedTime);
        myGameMap.update(elapsedTime);
    }

    /**
     * 
     * @param elapsedTime
     */
    public void updateWave (double elapsedTime) {
        myCurrentWave.update(elapsedTime);
//        myCurrentWave.update(elapsedTime);
//        if (myCurrentWave.waveCompleted())
//            startNextWave();
    }

    /**
     * Jumps to the next wave on the list.
     */
    public void startNextWave () {
        if (myWaves.iterator().hasNext()) {
            myCurrentWave = myWaves.iterator().next();
        }
        else {
            // TODO: add win behavior
        }
    }

    /**
     * paints the map
     * 
     * @param pen a pen
     */
    public void paintMap (Graphics2D pen) {
        myGameMap.paint(pen);
    }

    /**
     * 
     * @return a GameMap object
     */
    public GameMap getMap () {
        return myGameMap;
    }

    /**
     * Paints the shop.
     * 
     * @param pen a pen
     */
    public void paintShop (Graphics2D pen) {
        myShop.paint(pen);
    }

    /**
     * Given a point p, retrieves the shop item at this point.
     * 
     * @param p a point
     * @return the ShopItem at the given point p.
     */
    public ShopItem getShopItem (Point p) {
        return myShop.getShopItem(p);
    }
}
