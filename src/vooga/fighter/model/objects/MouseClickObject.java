package vooga.fighter.model.objects;

import java.awt.geom.Point2D;

import vooga.fighter.model.ModelConstants;

/**
 * Loads the resources necessary for MouseClickObjects.Reads the data from the file designated
 * in the path ModelConstants.MOUSE_CLICK_IMAGE_TAG.
 * @author Jack Matteucci
 */
public class MouseClickObject extends MouseObject {

	/**
	 * Constructs the MouseClickLoader and designates the location of the MouseObject
	 * @param loc
	 * @param pathHierarchy
	 */
    public MouseClickObject (Point2D loc, String pathHierarchy) {
    	super(loc, pathHierarchy);
    }
    
    /**
     * Returns the image tag that the MouseClickObject uses
     */
    @Override
    public String getImageTag(){
    	return ModelConstants.MOUSE_CLICK_IMAGE_TAG;
    }
}
