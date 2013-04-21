package vooga.towerdefense.factorytest;

import vooga.towerdefense.factories.GameElementFactory;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.util.Location;

/**
 * Creates a Tower at a specified location
 * @author XuRui
 *
 */
public class TestTowerFactory extends GameElementFactory {

	private TestTowerDefinition myDef;
	public TestTowerFactory(String name, TestTowerDefinition def){
		super(name, def);
		myDef = def;
	}
	
	public GameElement makeTower(Location loc){
		GameElement tower = createElement();
		tower.setCenter(loc.getX(),loc.getY());
		return tower;
	}

}
