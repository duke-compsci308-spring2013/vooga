package vooga.towerdefense.model.shop;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import vooga.towerdefense.factories.GameElementFactory;
import vooga.towerdefense.factories.examples.ExampleAuraTowerFactory;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.util.Location;


public class Shop {
    public static final int SHOP_SEPARATOR_WIDTH = 10;
    public static final int NUM_SHOP_ITEMS = 5;

    private List<ShopItem> myShopItems;

    public Shop (GameMap map) {
        myShopItems = new ArrayList<ShopItem>();
        initShopItems(map);
    }

    private void initShopItems (GameMap map) {
        int xC = 10;
        int yC = 10;
        for (int i = 0; i < NUM_SHOP_ITEMS; ++i) {
        	//TODO: replace this with parsed file input
        	GameElementFactory factory = new ExampleAuraTowerFactory(map, "Tree of Doom", null);
        	factory.initialize(map);
        	Location l = new Location(i * ShopItem.SHOP_ITEM_WIDTH + xC, yC);
        	myShopItems.add(new ShopItem(l,factory));
        	
        	
            xC += SHOP_SEPARATOR_WIDTH;
        }
    }

    public ShopItem getShopItem (Point p) {
        for (ShopItem shopItem : myShopItems) {
            if (shopItem.contains(p)) return shopItem;
        }
        return null;
    }

    public void paint (Graphics2D pen) {
        for (ShopItem shopItem : myShopItems) {
            shopItem.paint(pen);
        }
    }
}
