package vooga.fighter.model;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import vooga.fighter.controller.ModelDelegate;
import vooga.fighter.model.objects.CharacterObject;
import vooga.fighter.model.objects.GameObject;
import vooga.fighter.model.utils.ImageDataObject;

/**
 * Represents a mode in the game. Holds a list of all game objects in the mode,
 * and updates those every update cycle.
 * 
 * @author James Wei, alanni
 *
 */
public abstract class Mode {

	private static final String NEXT = "Next";
    private List<GameObject> myObjects;
    private long myId;
    private ModelDelegate myModelDelegate;
    private CollisionManager myCollisionManager;

    /**
     * Constructs a new Mode.
     */
    public Mode(ModelDelegate md) {
        myObjects = new ArrayList<GameObject>();
        myCollisionManager = new CollisionManager();
        setModelDelegate(md);
    }    
    /**
     * Sets the controller delegate for this mode.
     */
    public void setModelDelegate(ModelDelegate md) {
        myModelDelegate = md;
    }
    
    /**
    * Returns the id of the mode.
    */
    public long getMyId() {
        return myId;
    }

    /**
    * Returns the list of objects for this mode.
    */
    public List<GameObject> getMyObjects() {
        return myObjects;
    }    

    /**
    * Add an object to the list of game objects.
    */
    public void addObject(GameObject object) {
        myObjects.add(object);
    }

    /**
    * Remove an object from the list of game objects.
    */
    public void removeObject(GameObject object) {
        myObjects.remove(object);
    }

    /**
    * Notifies the subcontroller that the mode should terminate. Specific rules
    * for when the mode should be terminated are implemented in subclasses.
   */
   public void signalTermination(String Winner) {
       myModelDelegate.notifyEndCondition(Winner);
    }
   
    /**
     * Handles collisions between objects in this mode. Collision checking is
     * delegated to the CollisionManager, and the handling of individual collisions
     * is achieved by delegating to the objects themselves through double dispatch
     * in the visitor design pattern.
     */
    public void handleCollisions() {
        myCollisionManager.checkCollisions(myObjects);
    }

    /**
     * Creates the list of image data objects and returns it.
     */
    public List<ImageDataObject> getImageData() {
        List<ImageDataObject> result = new ArrayList<ImageDataObject>();
        for (GameObject object : getMyObjects()) {
            result.add(object.getImageData());
        }
        return result;
    }
    
    
    
    /**
     *  Removes objects that have been destroyed or have timed out
     */
    public void removeAppropriateObjects(){
    	ArrayList<GameObject> objectsCopy= new ArrayList<GameObject>(myObjects);
    	for (GameObject o: objectsCopy){
    		if (o.shouldBeRemoved()){
    			myObjects.remove(o);
    		}
    	}
    }
    /**
     * Updates the mode for one game loop. Implemented by subclasses.
     */
    public abstract void update(double stepTime, Dimension bounds);
    
    /**
    * Handles all initialization details when the mode is loaded by the appropriate
    * subcontroller. This method should be called first by the subcontroller.
    */
    public abstract void initializeMode();
    

}
