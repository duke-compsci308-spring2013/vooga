package vooga.fighter.model;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import vooga.fighter.controller.ModelDelegate;
import vooga.fighter.model.objects.CharacterObject;
import vooga.fighter.model.objects.EnvironmentObject;
import vooga.fighter.model.objects.GameObject;
import vooga.fighter.model.objects.MapObject;
import vooga.fighter.model.utils.ImageDataObject;
import vooga.fighter.model.utils.State;
import vooga.fighter.model.utils.UpdatableLocation;


/**
 * 
 * @author matthewparides, james
 * 
 */
public class MapEditorMode extends Mode {

    private List<UpdatableLocation> myStartLocations;
    private String myMapName;
    private MapObject myMap;

    public MapEditorMode (ModelDelegate cd, List<Integer> charIds, String mapName) {
        super(cd);
        myStartLocations = new ArrayList<UpdatableLocation>();
        myMapName = mapName;
        myMap = null;
    }

    /**
     * Overrides superclass initialize method by creating all objects in the level.
     */
    public void initializeMode () {
        loadMap(myMapName);
    }

    /**
     * Updates level mode by calling update in all of its objects.
     */
    public void update (double stepTime, Dimension bounds) {
        List<GameObject> myObjects = getMyObjects();
        handleCollisions();
        for (int i = 0; i < myObjects.size(); i++) {
            GameObject object = myObjects.get(i);
            State state = object.getCurrentState();
            // System.out.printf("Updating %s:\n", object.getClass().toString());
            // System.out.printf("Object current state:\ncurrentFrame: %d\nnumFrames: %d\nNull checks:\nImage: %b\nRectangle: %b\nSize: %b\n",
            // state.myCurrentFrame, state.myNumFrames, (state.getCurrentImage()==null),
            // (state.getCurrentRectangle()==null),
            // (state.getCurrentSize()==null));
            object.update();
            if (object.shouldBeRemoved()) {
                myObjects.remove(object);
                i--;
            }
        }
        if (shouldModeEnd()) {
            signalTermination();
        }
    }

    /**
     * Loads the environment objects for a map using the ObjectLoader.
     */
    public void loadMap (String mapName) {
        myMap = new MapObject(mapName);
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
    public void loadCharacters(List<String> characterNames, List<UpdatableLocation> startingPos) {
        for (int i=0; i<characterNames.size(); i++) {
            String charName = characterNames.get(i);
            UpdatableLocation start = startingPos.get(i);
            CharacterObject newCharacter = new CharacterObject(charName, start);
            addObject(newCharacter);
        }
    }

    /**
     * Checks if the level has ended. Does so by checking if any player has no health
     * remaining.
     */
    public boolean shouldModeEnd () {
        for (GameObject object : getMyObjects()) {
            if (object instanceof CharacterObject) {
                CharacterObject currentChar = (CharacterObject) object;
                if (!currentChar.hasHealthRemaining()) { return true; }
            }
        }
        return false;
    }

    /**
     * Creates the list of image data objects and returns it.
     */
    public List<ImageDataObject> getImageData () {
        List<ImageDataObject> result = new ArrayList<ImageDataObject>();
        for (GameObject object : getMyObjects()) {
            result.add(object.getImageData());
        }
        return result;
    }

    public void select (double XCoord, double yCoord) {
        // check for overlap with existing object.
        // overlap = delete object
        // non-overlap = place current selected object.
    }

    public void nextObject () {

    }

    public void prevObject () {

    }

    public MapObject getMap () {
        return myMap;
    }
}
