package vooga.towerdefense.gameElements;

import java.util.List;

import vooga.towerdefense.attributes.AttributeManager;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.model.tiles.Tile;


/**
 * A wave of enemy GameElements, allows developer to control the number and types of
 * GameElements as well as the speed at which GameElements are spawned.
 * 
 * @author XuRui
 * @author Matthew Roy
 * @author Jimmy Longley
 * 
 */

public class Wave {

    protected List<GameElement> myGameElements;
    protected AttributeManager myAttributes;
    protected double myDuration;
    protected double mySpawnDelay;
    protected GameMap myMap;
    private double myTimer;
    private double myLastSpawnTime;
    private Tile mySpawnLocation;

    public Wave (GameMap gameMap,
                 List<GameElement> GameElements,
                 Tile spawnLocation,
                 double spawnDelay,
                 double duration) {
        myGameElements = GameElements;
        myDuration = duration;
        mySpawnDelay = spawnDelay;
        myMap = gameMap;
        mySpawnLocation = spawnLocation;
        myTimer = 0;
        myLastSpawnTime = 0;
    }

    public void update (double timeElapsed) {
        if (canSpawn() && hasNextGameElement()) {
            GameElement GameElement = generateGameElement(getNextGameElement());
            myMap.addToMap(GameElement, mySpawnLocation);
            myLastSpawnTime = myTimer;
        }
        myTimer += timeElapsed;
    }

    /**
     * A method called by the model to determine if the wave has completed.
     * 
     * @return whether or not the wave is completed.
     */
    public boolean waveCompleted () {
        return myTimer > myDuration;
        // return !hasNextGameElement();
    }

    /**
     * Creates a GameElement based on given type and location using the wave attributes
     * 
     * @return GameElement created
     */
    private GameElement generateGameElement (GameElement u) {
        // TODO: should really be a GameElement generator class given, not an actual GameElement
        return u;
    }

    public double getDuration () {
        return myDuration;
    }

    private GameElement getNextGameElement () {
        GameElement u = myGameElements.iterator().next();
        myGameElements.remove(0);
        return u;
    }

    private boolean hasNextGameElement () {
        return myGameElements.iterator().hasNext();
    }

    /**
     * 
     * @return true at start of wave or if its been longer than spawn delay since last spawn
     */
    private boolean canSpawn () {
        return myTimer == 0 || (myTimer - myLastSpawnTime) > mySpawnDelay;
    }

    private void addGameElement (GameElement element) {
        myGameElements.add(element);
    }
}
