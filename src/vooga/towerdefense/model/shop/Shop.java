package vooga.towerdefense.model.shop;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import vooga.towerdefense.util.Location;


public class Shop {
    public static final int SHOP_SEPARATOR_WIDTH = 10;
    public static final int NUM_SHOP_ITEMS = 5;

    private List<ShopItem> myShopItems;

    public Shop () {
        myShopItems = new ArrayList<ShopItem>();
        initShopItems();
    }

    private void initShopItems () {
        int xC = 10;
        int yC = 10;
        for (int i = 0; i < NUM_SHOP_ITEMS; ++i) {
            myShopItems.add(new ShopItem(new Location(i * ShopItem.SHOP_ITEM_DIMENSIONS.getWidth() +
                                                      xC, yC)));
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
