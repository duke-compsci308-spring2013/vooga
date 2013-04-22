package vooga.towerdefense.model.shop;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import vooga.towerdefense.factories.GameElementFactory;
import vooga.towerdefense.util.Location;


public class ShopItem extends Rectangle {
    private static final long serialVersionUID = 1L;
    public static final int SHOP_ITEM_HEIGHT = 50;
    public static final int SHOP_ITEM_WIDTH = 50;
    private GameElementFactory myFactory;
    
    public ShopItem (Location location, GameElementFactory factory) {
        x = (int) location.getX();
        y = (int) location.getY();
        height = SHOP_ITEM_HEIGHT;
        width = SHOP_ITEM_WIDTH;
        myFactory = factory;
    }
    
    public GameElementFactory getFactory() {
    	return myFactory;
    }
    
    public void paint (Graphics2D pen) {
        pen.setColor(new Color(0, 0, 0));
        pen.fillRect(x, y, width, height);
        //TODO: once factories are fixed, towers will be able to paint their pictures
        //myFactory.createElement(null).getPixmap().paint(pen, new Location(x,y),new Dimension(width,height));
    }
}
