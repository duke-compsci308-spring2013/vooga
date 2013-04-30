package vooga.towerdefense.controller.modes;

import java.awt.Dimension;
import java.awt.Point;

import util.Location;
import vooga.towerdefense.controller.Controller;
import vooga.towerdefense.gameelements.GameElement;

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
	private double myCost;

	/**
	 * sets the item the user is trying to build.
	 * 
	 * @param item
	 *            , gotten from shop
	 */
	public void setItemToBuild(GameElement item) {
		myItemToBuild = item;
	}
	
	/**
	 * sets the cost of the item to be built.
	 * @param cost
	 */
	public void setCost(double cost) {
		myCost = cost;
	}

	/**
	 * handles a click on the map screen in Build mode.
	 * 
	 * @param p
	 * @param controller
	 */
	@Override
	public void handleMapClick(Point p, Controller controller) {
		Dimension tileSize = controller.getTileSize();
		int tilesWide = (int) Math.ceil(myItemToBuild.getSize().getWidth()
				/ tileSize.getWidth());
		int tilesTall = (int) Math.ceil(myItemToBuild.getSize().getHeight()
				/ tileSize.getHeight());

		Location snappedLocation = controller
				.getPointSnappedToGrid(new Location(p.getX(), p.getY()));
		myItemToBuild.setCenter(snappedLocation.getX(), snappedLocation.getY());

		if (controller.canBuildHere(p, tilesWide, tilesTall))
			controller.fixItemOnMap(myItemToBuild, p);
			controller.spend(myCost);
	}

	/**
	 * handles mouse dragging on the map screen in Build mode.
	 * 
	 * @param p
	 * @param controller
	 */
	@Override
	public void handleMapMouseDrag(Point p, Controller controller) {
		Location snappedLocation;
		try {
		snappedLocation = controller
				.getPointSnappedToGrid(new Location(p.getX(), p.getY()));
		}
		catch( NullPointerException e) {
			snappedLocation = new Location();
		}
		Dimension tileSize = controller.getTileSize();
		int tilesWide = (int) Math.ceil(myItemToBuild.getSize().getWidth()
				/ tileSize.getWidth());
		int tilesTall = (int) Math.ceil(myItemToBuild.getSize().getHeight()
				/ tileSize.getHeight());

		if (controller.canBuildHere(p, tilesWide, tilesTall))
			controller.paintGhostImage(myItemToBuild.getPixmap(),
					snappedLocation, myItemToBuild.getSize());
	}
}
