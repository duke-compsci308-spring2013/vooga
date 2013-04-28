package vooga.towerdefense.factories.elementfactories;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import util.Location;
import util.Pixmap;
import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.movement.MoveToTarget;
import vooga.towerdefense.attributes.AttributeConstantsEnum;
import vooga.towerdefense.attributes.AttributeManager;
import vooga.towerdefense.factories.actionfactories.ActionFactory;
import vooga.towerdefense.factories.attributefactories.AttributeFactory;
import vooga.towerdefense.factories.attributefactories.AttributeManagerFactory;
import vooga.towerdefense.gameelements.GameElement;
import vooga.towerdefense.model.GameMap;

/**
 * A factory that creates game elements based on preset data from an XML file.
 * 
 * @author Matthew Roy
 * @author Xu Rui
 * @author Zhen Gou
 */
public class GameElementFactory {

	/**
	 * Name of the element that is defined in this class. For convenience.
	 */
	private String myName;
	private List<ActionFactory> myActionsToMake;
	private List<GameElementFactory> myGameElementFactories;
	private GameMap myMap;

	private Pixmap myImage;
	private String myType;
	private Dimension mySize;
	private AttributeManagerFactory myAttributeManagerFactory;

	/**
	 * complete constructor
	 * 
	 * @param name
	 * @param image
	 * @param location
	 * @param size
	 * @param attrManager
	 */

	public GameElementFactory(String name, String type, Pixmap image,
			Dimension size, AttributeManagerFactory attrManager,
			List<ActionFactory> myActions) {
		myName = name;
		myType = type;
		myImage = image;
		mySize = size;
		myAttributeManagerFactory = attrManager;
		myActionsToMake = myActions;
		myAttributeManagerFactory.addAttributeFactory(makeAffiliation());
	}

	/**
	 * Returns the type of this factory - tower, unit or projectile.
	 * 
	 * @return a string representing the type
	 */
	public String getType() {
		return myType;
	}

	public GameMap getMap() {
		return myMap;
	}

	public String getName() {
		return myName;
	}

	/**
	 * Makes the affiliation of the game element depending on who spawned it.
	 * Add affiliation to attribute factory.
	 * 
	 * @return
	 */
	private AttributeFactory makeAffiliation() {
		AttributeConstantsEnum affliationValue = AttributeConstantsEnum
				.valueOf(myType);
		AttributeFactory affiliation = new AttributeFactory(
				AttributeConstantsEnum.AFFILIATION.getStatusCode(),
				affliationValue.getValue());
		return affiliation;
	}

	/**
	 * Must be called before create element
	 * 
	 * @param map
	 */
	public void initialize(GameMap map,
			List<GameElementFactory> gameElementFactories) {
		myMap = map;
		myGameElementFactories = gameElementFactories;

	}


	/**
	 * Creates a game element at a location.
	 * 
	 * @param spawnLocation
	 * @return
	 */
	public GameElement createElement(Location spawnLocation) {
		GameElement element = new GameElement(myImage, spawnLocation, mySize,
				createAttributeFactory());
		element.addActions(createActions(element));
		return element;
	}

	/**
	 * Create game element at spawn location with inbuilt targe. Mainly for
	 * creating projectiles.
	 * 
	 * @param spawn
	 * @param target
	 * @return
	 */
	public GameElement createElement(Location spawn, GameElement target) {
		GameElement projectile = new GameElement(myImage, spawn, mySize,
				myAttributeManagerFactory.makeAttributeManager());
		projectile.addActions(createActions(projectile));

		List<Action> actions = new ArrayList<Action>();
		actions.add(new MoveToTarget(projectile.getCenter(),
				target.getCenter(), projectile.getAttributeManager()
						.getAttribute(
								AttributeConstantsEnum.MOVE_SPEED
										.getStatusCode())));
		projectile.addActions(actions);
		return projectile;
	}

	public void addGameElementFactory(GameElementFactory GEFactory) {
		myGameElementFactories.add(GEFactory);
	}

	public Pixmap getImage() {
		return myImage;
	}
	
	/**
	 * Adds attributes to attribute factory which will make the element's
	 * attribute manager.
	 * 
	 * @return
	 */
	public AttributeManager createAttributeFactory() {
		for (GameElementFactory f : myGameElementFactories) {
			myAttributeManagerFactory.addGameElementFactory(f);
		}
		return myAttributeManagerFactory.makeAttributeManager();
	}

	public void setActionFactories(List<ActionFactory> actionsToMake) {
		myActionsToMake = actionsToMake;
	}

	/**
	 * Creates a list of actions for a specific element
	 * 
	 * @param e
	 *            element to base actions around
	 * @return list of the actions for that element
	 */
	public List<Action> createActions(GameElement element) {
		List<Action> actions = new ArrayList<Action>();
		for (ActionFactory a : myActionsToMake) {
			actions.add(a.createAction(element));
		}
		return actions;
	}


}
