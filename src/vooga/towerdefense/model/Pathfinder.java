package vooga.towerdefense.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import vooga.towerdefense.model.tiles.Tile;
import vooga.towerdefense.util.Location;

/**
 * Generates paths based on what tiles are eligible to walk on in the map using
 * graph algorithms.
 * 
 * @author JLongley
 * 
 */
public class Pathfinder {

	private Tile[][] myGrid;

	public Pathfinder(Tile[][] grid) {
		myGrid = grid;
	}

	class ArrayIndex {
		public ArrayIndex(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		public int x;
		public int y;
		public int dist;
	};

	class DistComparator implements Comparator<ArrayIndex> {

		@Override
		public int compare(ArrayIndex a, ArrayIndex b) {
			if (a.dist < b.dist)
				return -1;
			if (a.dist > b.dist)
				return 1;
			return 0;
		}
	}

	/**
	 * Returns a path object corresponding to the shortest path between points
	 * (x1,y1) and (x2,y2) on the map.
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return the shortest path between (x1,y1) and (x2,y2) 
	 */
	public Path getShortestPath(int x1, int y1, int x2, int y2) {

		// TODO: implement dijkrstra's algoritm to make smart pathfinding
		// int[][] dist = new int[myGrid.length][myGrid[0].length];
		//
		// PriorityQueue<ArrayIndex> Q = new PriorityQueue<ArrayIndex>();
		//
		// for (int i = 0; i < myGrid.length; i++)
		// for (int j = 0; j < myGrid[0].length; j++) {
		// ArrayIndex a = new ArrayIndex(i, j, Integer.MAX_VALUE);
		// Q.add(a);
		// }

		List<Location> locations = new ArrayList<Location>();

		locations.add(new Location(25, 275));
		locations.add(new Location(175, 275));
		if (Math.random() > .5) {
			locations.add(new Location(175, 125));
			locations.add(new Location(375, 125));
		} else {
			locations.add(new Location(175, 425));
			locations.add(new Location(375, 425));
		}
		locations.add(new Location(375, 275));
		locations.add(new Location(575, 275));
		if (Math.random() > .5) {
			locations.add(new Location(575, 175));
			locations.add(new Location(675, 175));
		} else {
			locations.add(new Location(575, 375));
			locations.add(new Location(675, 375));
		}
		locations.add(new Location(675, 275));
		locations.add(new Location(775, 275));

		return new Path(locations);

	}

}
