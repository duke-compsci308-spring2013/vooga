package vooga.fighter.model;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import vooga.fighter.controller.ModelDelegate;
import vooga.fighter.model.loaders.MapLoader;
import vooga.fighter.model.objects.CharacterObject;
import vooga.fighter.model.objects.EnvironmentObject;
import vooga.fighter.model.objects.GameObject;
import vooga.fighter.model.objects.MapObject;
import vooga.fighter.model.utils.ImageDataObject;
import vooga.fighter.model.utils.State;
import vooga.fighter.model.utils.UpdatableLocation;


/**
 * Represents one level in the game, i.e. one matchup of two or more characters.
 * 
 * @author james
 * 
 */
public class LevelMode extends Mode {

    private List<UpdatableLocation> myStartLocations;
    private List<Integer> myCharacterIds;
    private int myMapId;
    private MapObject myMap;

    public LevelMode(ModelDelegate cd, List<Integer> charIds, int mapId) {
        super(cd);
        myStartLocations = new ArrayList<UpdatableLocation>();
        myCharacterIds = charIds;
        myMapId = mapId;
        myMap = null;
    }

    /**
     * Overrides superclass initialize method by creating all objects in the level.
     */
    public void initializeMode() {
        loadMap(myMapId);
        loadCharacters(myCharacterIds, myStartLocations);
    }

    /**
     * Updates level mode by calling update in all of its objects.
     */
    public void update(double stepTime, Dimension bounds) {
        List<GameObject> myObjects = getMyObjects();
        handleCollisions();
        for (int i=0; i<myObjects.size(); i++) {
            GameObject object = myObjects.get(i);
            State state = object.getCurrentState();
//            System.out.printf("Updating %s:\n", object.getClass().toString());
//            System.out.printf("Object current state:\ncurrentFrame: %d\nnumFrames: %d\nNull checks:\nImage: %b\nRectangle: %b\nSize: %b\n",
//                              state.myCurrentFrame, state.myNumFrames, (state.getCurrentImage()==null), (state.getCurrentRectangle()==null),
//                              (state.getCurrentSize()==null));
            object.update();
            if (object.shouldBeRemoved()) {
                myObjects.remove(object);
                i--;
            }
        }
        if (shouldModeEnd()) {
            super.signalTermination();
        }
    }

    /**
     * Loads the environment objects for a map using the ObjectLoader.
     */
    public void loadMap (int mapId) {
        myMap = new MapObject(mapId);
    	myStartLocations = myMap.getStartPositions();
    	addObject(myMap);
    	List<EnvironmentObject> mapObjects = myMap.getEnviroObjects();
    	for (EnvironmentObject object : mapObjects) {
    	    addObject(object);
    	}    	
    }

    /**
     * Loads the character objects for the selected characters using the ObjectLoader.
     */
    public void loadCharacters(List<Integer> characterIds, List<UpdatableLocation> startingPos) {
        for (int i=0; i<characterIds.size(); i++) {
            int charId = characterIds.get(i);
            UpdatableLocation start = startingPos.get(i);
            addObject(new CharacterObject(charId, start));
        }
    }

    /**
     * Detects and handles collisions between game objects.
     */
    public void handleCollisions () {
        CollisionManager.checkCollisions(getMyObjects());
    }
    
    /**
     * Checks if the level has ended. Does so by checking if any player has no health
     * remaining.
     */
    public boolean shouldModeEnd() {
        for (GameObject object : getMyObjects()) {
            if (object instanceof CharacterObject) {
                CharacterObject currentChar = (CharacterObject) object;
                if (!currentChar.hasHealthRemaining()) {
                    return true;
                }
            }
        }
        return false;
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
    
}
