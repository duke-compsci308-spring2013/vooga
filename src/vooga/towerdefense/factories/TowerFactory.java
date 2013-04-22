package vooga.towerdefense.factories;

import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.attributes.AttributeConstants;
import vooga.towerdefense.attributes.AttributeManager;

/**
 * Creates a Tower at a specified location
 * @author XuRui
 *
 */
public class TowerFactory extends GameElementFactory {

	public TowerFactory(String name, TowerDefinition def){
		super(name, def);
	}
	
	public TowerFactory(){
		super();
	}
	
	/**
	 * This is a convenience method to aid in the creation of example tower factories.
	 * @return
	 */
	public AttributeManager getDefaultAM(){
        AttributeManager AM = new AttributeManager();
        AM.addAttribute(new Attribute(AttributeConstants.ATTACK_RADIUS, 300.0));
        AM.addAttribute(new Attribute(AttributeConstants.DIRECTION, 50.0));
        AM.addAttribute(new Attribute(AttributeConstants.ATTACK_INTERVAL, 30.0));
        AM.addAttribute(new Attribute(AttributeConstants.NUM_OF_TARGETS, 1.0));
        AM.addAttribute(new Attribute(AttributeConstants.ATTACK_RADIUS, 200.0));
        AM.addAttribute(new Attribute(AttributeConstants.MOVE_SPEED, 10.0));
        AM.addAttribute(new Attribute(AttributeConstants.AURA_EFFECT, -10.0));
        AM.setProjectileFactory(new ProjectileFactory());
        return AM;
	}

}
