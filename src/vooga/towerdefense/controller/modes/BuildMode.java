package vooga.towerdefense.controller.modes;

import java.awt.Point;
import vooga.towerdefense.controller.Controller;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.shop.ShopItem;


/**
 * A build mode used to aid in the placing and building of towers.
 * 
 * @author Jimmy Longley
 * @author Angelica Schwartz
 */
public class BuildMode extends ControlMode {

    /**
     * item that the user wants to build.
     */
    private GameElement myItemToBuild;
    
    /**
     * sets the item the user is trying to build.
     * @param item, gotten from shop
     */
    public void setItemToBuild(GameElement item) {
        myItemToBuild = item;
    }
    
    /**
     * handles a click on the map screen in Build mode.
     * @param p
     * @param controller
     */
    @Override
    public void handleMapClick (Point p, Controller controller) {
       controller.fixItemOnMap(myItemToBuild, p);
    }

    /**
     * handles mouse dragging on the map screen in Build mode.
     * @param p
     * @param controller
     */
    @Override
    public void handleMapMouseDrag (Point p, Controller controller) {
        controller.paintGhostImage(p, myItemToBuild.getPixmap());
        System.out.println("asjdgujsajd");
    }
}
