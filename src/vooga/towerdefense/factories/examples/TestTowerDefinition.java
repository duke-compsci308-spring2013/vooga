package vooga.towerdefense.factories.examples;

import java.awt.Dimension;
import java.util.List;

import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.factories.TowerDefinition;
import vooga.towerdefense.util.Location;
import vooga.towerdefense.util.Pixmap;

public class TestTowerDefinition extends TowerDefinition {
	
	private static final Pixmap TOWER_IMAGE = new Pixmap("tower.gif");
	private static final Dimension DEFAULT_TOWER_SIZE = new Dimension(50, 50);
	private static final Location DEFAULT_TOWER_LOCATION = new Location(100,200);
	private static final double DEFAULT_HEALTH = 50;
	
	private Pixmap myImage;
	private Dimension mySize;
	private Location myCenter;

	public TestTowerDefinition() {
		myImage = TOWER_IMAGE;
		mySize = DEFAULT_TOWER_SIZE;
		myCenter = DEFAULT_TOWER_LOCATION;
	}
	
	/**
	 * Temporary method for translating health into an attribute
	 */
	
	public Attribute makeHealthAttribute(){
		return new Attribute("health", DEFAULT_HEALTH);
	}
	
	public Pixmap getImage(){
		return myImage;
	}
	
	public Location getCenter(){
		return myCenter;
	}
	
	public Dimension getSize(){
		return mySize;
	}
	
	
	

	
	
}
