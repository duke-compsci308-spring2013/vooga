package vooga.towerdefense.factories;

import java.util.ArrayList;
import java.util.List;
import vooga.towerdefense.factories.examples.ExampleUnitFactory;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.gameElements.Wave;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.model.tiles.Tile;
import vooga.towerdefense.util.Location;


public class WaveFactory {

    private final static double DEFAULT_SPAWN_DELAY = 300;
    private final static double DEFAULT_DURATION = 1000 * 90;

    public static Wave createWave (ExampleUnitFactory ElementFactory,
                                   int numUnits,
                                   GameMap gameMap,
                                   Tile spawnTile,
                                   double spawnDelay,
                                   double duration) {
        
        Location spawnLocation = new Location(spawnTile.getCenter().getX(),
                                              spawnTile.getCenter().getY());
        List<GameElement> units = new ArrayList<GameElement>();
        for (int i = 0; i < numUnits; i++) {
            System.out.println("added another unit to spawn");

            units.add(ElementFactory.createElement(spawnLocation));
        }
        return new Wave(gameMap, units, spawnTile, spawnDelay, duration);

    }

    //FIXME: I set these to example unit factories for testing
    public static Wave createWave (ExampleUnitFactory unitFactory, int numUnits,
                                   GameMap gameMap, Tile spawnTile) {
        return createWave(unitFactory, numUnits, gameMap, spawnTile,
                          DEFAULT_SPAWN_DELAY, DEFAULT_DURATION);
    }
}
