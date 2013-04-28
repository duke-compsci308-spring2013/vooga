package vooga.towerdefense.factories.definitions;

import java.awt.Dimension;

import vooga.towerdefense.factories.attributefactories.AttributeManagerFactory;


/**
 * Default ProjectileDefinition used for testing projectiles; 
 * only GameElementDefinition and GameElement will be made and used at the end.
 * 
 * @author XuRui
 *
 */
public class ProjectileDefinition extends GameElementDefinition {
	
	private Pixmap myImage;
	private Location myCenter;
	private Dimension mySize;

	public ProjectileDefinition() {
		myImage = DefinitionConstants.DEFAULT_PROJECTILE_IMAGE;
		myCenter = DefinitionConstants.DEFAULT_PROJECTILE_LOCATION;
		mySize = DefinitionConstants.DEFAULT_PROJECTILE_SIZE;
	}
	
	@Override
	public Pixmap getImage(){
		return myImage;
	}
	
    @Override
	public Location getCenter () {
        return myCenter;
    }

    @Override
	public Dimension getSize () {
        return mySize;
    }

	@Override
	public AttributeManagerFactory getAttributeManagerFactory() {
		return new AttributeManagerFactory();
	}

	
	
	
	
}
