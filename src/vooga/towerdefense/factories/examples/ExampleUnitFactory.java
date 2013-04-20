package vooga.towerdefense.factories.examples;

import java.util.ArrayList;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.FollowPath;
import vooga.towerdefense.action.Move;
import vooga.towerdefense.action.OnDeath;
import vooga.towerdefense.action.RemoveGameElement;
import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.attributes.AttributeConstants;
import vooga.towerdefense.attributes.AttributeManager;
import vooga.towerdefense.factories.GameElementDefinition;
import vooga.towerdefense.factories.UnitFactory;
import vooga.towerdefense.gameElements.Unit;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.model.Path;
import vooga.towerdefense.util.Location;

/**
 * @author Matthew Roy
 * @author Jimmy Longley
 * 
 */
public class ExampleUnitFactory extends UnitFactory {

	private GameMap myGameMap;

	/**
	 * @param elementName
	 * @param definition
	 */
	public ExampleUnitFactory(String elementName,
			GameElementDefinition definition, GameMap gameMap) {
		super(elementName, definition);
		myGameMap = gameMap;
	}

	public Unit createUnit(Location putHere) {
		return createUnit(putHere, new TrollUnitDefinition());
	}

	public Unit createUnit(Location putHere, TrollUnitDefinition myDef) {
		TrollUnitDefinition myDefinition = myDef;

		AttributeManager AM = new AttributeManager();
		AM.addAttribute(new Attribute(AttributeConstants.MOVE_SPEED, 150.0));
		AM.addAttribute(new Attribute(AttributeConstants.DIRECTION, 50.0));
		AM.addAttribute(new Attribute(AttributeConstants.ATTACK_INTERVAL, 50.0));
		Unit myUnit;
		if (putHere != null) {
			myUnit = new Unit(myDefinition.myImage, putHere,
					myDefinition.getSize(), AM);
		} else {
			myUnit = new Unit(myDefinition.getImage(),
					myDefinition.getCenter(), myDefinition.getSize(), AM);
		}

		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new Move(myUnit.getCenter(), myUnit.getAttributeManager()
				.getAttribute(AttributeConstants.MOVE_SPEED), myUnit
				.getAttributeManager().getAttribute(
						AttributeConstants.DIRECTION)));
		Path path = myGameMap.getShortestPath(putHere,
				myGameMap.default_end_location);
		actions.add(new FollowPath(myUnit, path));
		Action myDeath = new OnDeath(AM.getAttribute(AttributeConstants.HEALTH));
		myDeath.addFollowUpAction(new RemoveGameElement(myGameMap, myUnit));
		actions.add(myDeath);
		myUnit.addActions(actions);

		return myUnit;

	}

}
