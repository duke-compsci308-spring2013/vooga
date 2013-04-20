package vooga.towerdefense.factories.examples;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.attributes.AttributeManager;
import vooga.towerdefense.factories.ProjectileFactory;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.gameElements.Projectile;
import vooga.towerdefense.util.Location;
import vooga.towerdefense.util.Pixmap;

public class ExampleProjectileFactory extends ProjectileFactory{
    private static final int DEFAULT_WIDTH=10;
    private static final Pixmap DEFAULT_IMAGE = new Pixmap("fireball.gif");
    private static final Dimension DEFAULT_SIZE = new Dimension(DEFAULT_WIDTH, DEFAULT_WIDTH);
    private static final List<Action> DEFAULT_ACTIONS = new ArrayList<Action>();
    private static final AttributeManager DEFAULT_ATTRIBUTE_MANAGER=new AttributeManager();
    

	public Projectile createProjectile(GameElement target, Location source){
		return new Projectile(DEFAULT_IMAGE,DEFAULT_SIZE, source, target.getCenter(),DEFAULT_ACTIONS,DEFAULT_ATTRIBUTE_MANAGER);
	}

}
