package vooga.towerdefense.factories;

import java.awt.Dimension;

import vooga.towerdefense.util.Pixmap;
import vooga.towerdefense.util.Location;

public class TowerDefinition extends GameElementDefinition {
	
	private static final Pixmap TOWER_IMAGE = new Pixmap("tower.gif");
	private static final Location DEFAULT_TOWER_LOCATION = new Location(200, 100);
	private static final Dimension DEFAULT_TOWER_SIZE = new Dimension(50, 50);
	
	Pixmap myImage;
	Location myCenter;
	Dimension mySize;

	public TowerDefinition() {
		myImage = TOWER_IMAGE;
		myCenter = DEFAULT_TOWER_LOCATION;
		mySize = DEFAULT_TOWER_SIZE;
	}
	
	
	

	
	
}
