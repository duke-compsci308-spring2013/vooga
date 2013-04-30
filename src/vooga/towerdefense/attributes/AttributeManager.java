package vooga.towerdefense.attributes;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import util.Location;
import vooga.towerdefense.factories.elementfactories.GameElementFactory;

/**
 * Attributes object that helps to track all game element attributes. It also
 * provides info requested by view through controller. Used by game elements and
 * also any other class that needs attribute management.
 * 
 * @author Matthew Roy
 * @author XuRui
 * @author Zhen Gou
 */
public class AttributeManager {
	private HashMap<String, Attribute> myAttributes;
	private HashMap<String, GameElementFactory> myGameElementFactories;
	private HashMap<String, GameElementFactory> myUpgrades;

	public AttributeManager() {
		myAttributes = new HashMap<String, Attribute>();
		myGameElementFactories = new HashMap<String, GameElementFactory>();
		myUpgrades = new HashMap<String, GameElementFactory>();

	}

	/**
	 * Creates an attribute manager based on a set of attributes
	 * 
	 * @param attributes
	 */
	public AttributeManager(HashSet<Attribute> attributes) {
		new AttributeManager();
		for (Attribute a : attributes) {
			myAttributes.put(a.getName(), a);
		}
	}

	/**
	 * currently the update will reset all attribute's temporary buff. So this
	 * method should be called before executing actions.
	 */
	public void update() {
		resetAllAttributesTemporaryBuff();
	}

	/**
	 * Returns stats information requested by View components.
	 * 
	 * @return
	 */
	public List<String> getAttributesInfo() {
		List<String> info = new ArrayList<String>();
		for (String statName : myAttributes.keySet()) {
			Attribute stat = myAttributes.get(statName);
			info.add(stat.getDisplayableInfo());
		}
		return info;
	}

	/**
	 * returns a string representing info about the attributeManager
	 */

	public String toString() {
		String returnValue = "";
		for (String s : getAttributesInfo()) {
			returnValue += s + "\n";
		}
		return returnValue;
	}

	/**
	 * Gets a specific attribute based on name
	 * 
	 * @param name
	 * @return attribute if it exists, otherwise returns null
	 */
	public Attribute getAttribute(String name) {
		return myAttributes.get(name);
	}

	/**
	 * Add stats attribute to game element
	 */
	public void addAttribute(Attribute newAttribute) {
		myAttributes.put(newAttribute.getName(), newAttribute);
	}

	/**
	 * add a list of attributes to the manager
	 * 
	 * @param newAttributes
	 */
	public void addAttributes(List<Attribute> newAttributes) {
		for (Attribute a : newAttributes) {
			addAttribute(a);
		}
	}

	/**
	 * replaces the desired attribute's old value with new value
	 * 
	 * @param type
	 * @param newValue
	 */
	public void replaceAttributeValue(String type, double newValue) {
		myAttributes.get(type).setValue(newValue);
	}
	
	public void paintHealth(Graphics2D pen, Location where, Dimension size) {
	    Attribute Health = getAttribute(AttributeConstantsEnum.HEALTH.getStatusCode());
	    if (Health != null) {
	        Health.paint(pen, where, size);
	    }
	}

	/**
	 * Resets all attributes to default values
	 */
	public void resetAllAttributes() {
		for (Attribute attr : myAttributes.values()) {
			attr.reset();
		}
	}

	/**
	 * reset buff value of all attributes
	 */
	public void resetAllAttributesTemporaryBuff() {
		for (Attribute attr : myAttributes.values()) {
			attr.resetBuffValue();
		}
	}

	/**
	 * add a gameelementfactory to the upgrade map
	 * 
	 * @param upgradeName
	 * @param upgrade
	 */
	public void addUpgrade(String upgradeName, GameElementFactory upgrade) {
		myUpgrades.put(upgradeName, upgrade);
	}

	/**
	 * checks whether this element has upgrades
	 * 
	 * @return
	 */
	public boolean hasUpgrades() {
		return !myUpgrades.isEmpty();
	}

	/**
	 * return all the upgrades this element has
	 * 
	 * @return
	 */
	public HashMap<String, GameElementFactory> getUpgrades() {
		return myUpgrades;
	}

	/**
	 * add a gameelementfactory to the factory list
	 * 
	 * @param name
	 * @param factory
	 */
	public void addGameElementFactory(String name, GameElementFactory factory) {
		myGameElementFactories.put(name, factory);
	}

	/**
	 * return the gameelement factory related to that name
	 * 
	 * @param name
	 * @return
	 */

	public GameElementFactory getGameElementFactory(String name) {
		return myGameElementFactories.get(name);
	}
}
