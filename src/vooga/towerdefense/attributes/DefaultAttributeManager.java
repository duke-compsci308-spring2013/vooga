package vooga.towerdefense.attributes;

import vooga.towerdefense.factories.ProjectileFactory;

public class DefaultAttributeManager extends AttributeManager {
	
	public DefaultAttributeManager(){
        this.addAttribute(new Attribute(AttributeConstants.ATTACK_RADIUS, 300.0));
        this.addAttribute(new Attribute(AttributeConstants.DIRECTION, 50.0));
        this.addAttribute(new Attribute(AttributeConstants.ATTACK_INTERVAL, 30.0));
        this.addAttribute(new Attribute(AttributeConstants.NUM_OF_TARGETS, 1.0));
        this.addAttribute(new Attribute(AttributeConstants.ATTACK_RADIUS, 200.0));
        this.addAttribute(new Attribute(AttributeConstants.MOVE_SPEED, 10.0));
        this.addAttribute(new Attribute(AttributeConstants.AURA_EFFECT, -10.0));
        this.setProjectileFactory(new ProjectileFactory());
	}
	

}
