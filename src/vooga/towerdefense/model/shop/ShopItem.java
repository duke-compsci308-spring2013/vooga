package vooga.towerdefense.model.shop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import util.Location;
import util.Pixmap;
import vooga.towerdefense.factories.elementfactories.GameElementFactory;

/**
 * ShopItem is a class used to display purchasable items in the view. It has a
 * sprite and a factory corresponding to what item it is.
 * 
 * @author Erick Gonzalez
 * @author Leonard Ng'ono
 * @author JLongley
 * 
 */
public class ShopItem extends Rectangle {
	private static final long serialVersionUID = 1L;
	public static final int SHOP_ITEM_HEIGHT = 50;
	public static final int SHOP_ITEM_WIDTH = 50;
	private GameElementFactory myFactory;

	public ShopItem(Location location, GameElementFactory factory) {
		x = (int) location.getX();
		y = (int) location.getY();
		height = SHOP_ITEM_HEIGHT;
		width = SHOP_ITEM_WIDTH;
		myFactory = factory;
	}

	/**
	 * Gets the factory of the game element that the shop item corresponds to
	 * 
	 * @return the factory of the game element that the shop item corresponds to
	 */
	public GameElementFactory getFactory() {
		return myFactory;
	}

	/**
	 * paints the element on the screen
	 * 
	 * @param pen
	 */
	public void paint(Graphics2D pen) {
		pen.setColor(new Color(0, 0, 0));
		pen.fillRect(x, y, width, height);
		// TODO: once factories are fixed, towers will be able to paint their
		// pictures
		Pixmap pic = myFactory.getImage();
		pic.paint(pen, new Location(x+width/2, y+height/2), new Dimension(width, height));
	}
}
