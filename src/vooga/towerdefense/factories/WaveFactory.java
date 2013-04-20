package vooga.towerdefense.factories;

import java.util.ArrayList;
import java.util.List;

import vooga.towerdefense.gameElements.Unit;
import vooga.towerdefense.gameElements.Wave;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.model.tiles.Tile;
import vooga.towerdefense.util.Location;

public class WaveFactory {

	private final static double DEFAULT_SPAWN_DELAY = 300;
	private final static double DEFAULT_DURATION = 1000 * 90;

	public static Wave createWave(UnitFactory unitFactory, int numUnits,
			GameMap gameMap, Tile spawnTile, double spawnDelay, double duration) {

		Location spawnLocation = new Location(spawnTile.getCenter().getX(),
				spawnTile.getCenter().getY());
		List<Unit> units = new ArrayList<Unit>();
		for (int i = 0; i < numUnits; i++) {
			System.out.println("added another unit to spawn");
			units.add(unitFactory.createUnit(spawnLocation));
		}
		return new Wave(gameMap, units, spawnTile, spawnDelay, duration);

	}

	public static Wave createWave(UnitFactory unitFactory, int numUnits,
			GameMap gameMap, Tile spawnTile) {
		return createWave(unitFactory, numUnits, gameMap, spawnTile,
				DEFAULT_SPAWN_DELAY, DEFAULT_DURATION);
	}
}
