package vooga.fighter.model.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.Sound;
import vooga.fighter.model.ModelConstants;
import vooga.fighter.model.loaders.MapLoader;
import vooga.fighter.model.utils.UpdatableLocation;


/**
 * Map class to contain, update, and manipulate all environmental objects in a
 * particular map. Also contains the background image, music, and player start
 * locations.
 * 
 * @author James Wei, matthewparides, David Le
 * 
 */

public class MapObject extends GameObject {

    private List<EnvironmentObject> myEnviroObjects;
    private List<UpdatableLocation> myStartingPositions;
    private Map<String, Sound> mySounds;
    private Sound myCurrentSound;
    private String myName;

    /**
     * Constructor for a new Map object.
     * @param mapName is the name of the map
     * @param pathHierarchy is the path of the game resources folder
     */
    public MapObject(String mapName, String pathHierarchy) {
        super();
        myEnviroObjects = new ArrayList<EnvironmentObject>();
        myStartingPositions = new ArrayList<UpdatableLocation>();
        mySounds = new HashMap<String, Sound>();
        myCurrentSound = null;
        myName = mapName;
        setLoader(new MapLoader(mapName, this, pathHierarchy));
        setCurrentState(ModelConstants.BACKGROUND_PROPERTY);
        setImageData();
    }

    /**
     * Adds an environment object to the map object.
     * @param object is the environment object to add
     */
    public void addEnviroObject(EnvironmentObject object) {
        myEnviroObjects.add(object);
        object.setImageData();
    }
    
    /**
     * removes an environment object from the map object
     * @param object - object to be removed
     */
    public void removeEnviroObject(EnvironmentObject object) {
        myEnviroObjects.remove(object);
    }
    
    /**
     * returns the name of this map.
     * @return myName - this map's name.
     */
    public String getName() {
        return myName;
    }
    
    /**
     * sets the name of this map. used by map editor
     * @param newName is the name to set
     */
    public void setName(String newName) {
        myName = newName;
    }

    /**
     * Returns the list of environment objects in the map object.
     */
    public List<EnvironmentObject> getEnviroObjects() {
        return myEnviroObjects;
    }

    /**
     * Clears all environment objects from the map object.
     */
    public void clearEnviroObjects() {
        myEnviroObjects.clear();
    }
    
    /**
     * Adds a starting position to the map object.
     * @param position is the start position to add
     */
    public void addStartPosition(UpdatableLocation position) {
        myStartingPositions.add(position);
    }
    
    /**
     * sets a starting position to the map object.
     * @param index is the index to set in the list of starting positions
     * @param position is the starting position to add
     */
    public void setStartPosition(int index, UpdatableLocation position) {
        myStartingPositions.set(index, position);
    }
    
    /**
     * Returns the list of starting positions from the map object.
     */
    public List<UpdatableLocation> getStartPositions() {
        return myStartingPositions;
    }
    
    /**
     * Adds a sound to the map object. Overwrites any existing value.
     * @param key is the name of the sound
     * @param sound is the sound itself
     */
    public void addSound(String key, Sound sound) {
        mySounds.put(key, sound);
    }
    
    /**
     * Clears all sounds from the map object.
     */
    public void clearSounds() {
        mySounds.clear();
    }
    
    /**
     * Sets the current sound of the map object. Does nothing if the given key is
     * not found in the map.
     * @param key is the name of the sound to set as current
     */
    public void setCurrentSound(String key) {
        if (mySounds.containsKey(key)) {
            myCurrentSound = mySounds.get(key);
        }
    }
    
    /**
     * Returns the current sound of the map object.
     */
    public Sound getCurrentSound() {
        return myCurrentSound;
    }
    
    /**
     * Begins playing the current sound.
     */
    public void playCurrentSound() {
        myCurrentSound.play();
    }
    
    /**
     * Updates all environmental objects in the map object.
     */
    @Override
	public void completeUpdate() {                        
        if (getCurrentState().hasCompleted()) {
            getCurrentState().resetState();
        }
        if (myCurrentSound != null) {
            return;
        }
    }
    
    /**
     * Nothing for now, just return false. Never need to remove map.
     */
    @Override
	public boolean shouldBeRemoved() {
        return false;
    }
}
