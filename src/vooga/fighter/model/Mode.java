package vooga.fighter.model;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import vooga.fighter.controller.ModelDelegate;
import vooga.fighter.model.objects.CharacterObject;
import vooga.fighter.model.objects.GameObject;

/**
 * Represents a mode in the game. Holds a list of all game objects in the mode,
 * and updates those every update cycle.
 * 
 * @author james
 *
 */
public abstract class Mode {

    private List<GameObject> myObjects;
    private long myId;
    private ModelDelegate myModelDelegate;
    private List<CharacterObject> myCharacterObjects; 

    /**
     * Constructs a new Mode.
     */
    public Mode(ModelDelegate cd) {
        myObjects = new ArrayList<GameObject>();
        myCharacterObjects= new ArrayList<CharacterObject>();
        setModelDelegate(cd);
    }
    
    /**
     * Sets the controller delegate for this mode.
     */
    public void setModelDelegate(ModelDelegate cd) {
        myModelDelegate = cd;
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
     * Returns the list of character objects for the mode
     */
    public List<CharacterObject>getMyCharacterObjects(){
    	return myCharacterObjects; 
    }

    /**
    * Add an object to the list of game objects.
    */
    public void addObject(GameObject object) {
        myObjects.add(object);
        if (object instanceof CharacterObject){
        	myCharacterObjects.add((CharacterObject)object);
        }
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
    public void signalTermination() {
        myModelDelegate.notifyEndCondition();
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
    
    /**
     * Returns true if the mode should end. Implemented by subclasses.
     */
    public abstract boolean shouldModeEnd();

}
