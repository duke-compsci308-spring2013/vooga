package vooga.towerdefense.model.shop;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import util.Location;
import vooga.towerdefense.factories.elementfactories.GameElementFactory;
import vooga.towerdefense.model.GameMap;

/**
 * The Shop is a container for a list of ShopItems that can be purchased by the
 * player. Shop handles how the ShopItems are placed and displayed, as well as
 * how they are initialized with a GameElementFactory
 * 
 * @author Erick Gonzalez
 * @author Leonard Ng'eno
 * @author JLongley
 * 
 */
public class Shop {
	public static final int SHOP_SEPARATOR_WIDTH = 10;
	//public static final int NUM_SHOP_ITEMS = 5;

	private List<ShopItem> myShopItems;

	public Shop(GameMap map, List<GameElementFactory> factories) {
		myShopItems = new ArrayList<ShopItem>();
		initShopItems(map, factories);
	}

	private void initShopItems(GameMap map, List<GameElementFactory> factories) {
		
		int xC = 10;
		int yC = 10;
		for (int i = 0; i < factories.size(); ++i) {
		    // TODO: replace this with parsed file input
		    //GameElementFactory factory = new GameElementFactory(DefinitionConstants.DEFAULT_TOWER_NAME, 
		    //		DefinitionConstants.DEFAULT_TOWER_IMAGE);
		    //factory.initialize(map);
		    
		    if (factories.get(i).getType().equals("Tower")) {
		        
		        Location l = new Location(i * ShopItem.SHOP_ITEM_WIDTH + xC, yC);
		        myShopItems.add(new ShopItem(l, factories.get(i)));

			xC += SHOP_SEPARATOR_WIDTH;
		    }
		}
	}

	/**
	 * Gets a shop item based on a point clicked
	 * @param p the point clicked
	 * @return the shop item clicked
	 */
	public ShopItem getShopItem(Point p) {
		for (ShopItem shopItem : myShopItems) {
			if (shopItem.contains(p))
				return shopItem;
		}
		return null;
	}

	/**
	 * Paints the items in the shop
	 * @param pen
	 */
	public void paint(Graphics2D pen) {
		for (ShopItem shopItem : myShopItems) {
			shopItem.paint(pen);
		}
	}
}
