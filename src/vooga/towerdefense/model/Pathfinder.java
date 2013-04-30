package vooga.towerdefense.model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import util.Location;
import vooga.towerdefense.model.AStar.AStar;
import vooga.towerdefense.model.AStar.AStarHeuristic;
import vooga.towerdefense.model.AStar.AreaMap;
import vooga.towerdefense.model.AStar.DiagonalHeuristic;

/**
 * Generates paths based on what tiles are eligible to walk on in the map using
 * graph algorithms.
 * 
 * @author JLongley
 * 
 */
public class Pathfinder {

	private Tile[][] myGrid;
	private Dimension myTileSize;

	public Pathfinder(Tile[][] grid, Dimension tileSize) {
		myGrid = grid;
		myTileSize = tileSize;
	}

	private static void removeLinearPoints(ArrayList<Point> points) {
		try {
			for (int i = 0; i < points.size() - 2; i++) {
				if (points.get(i).getX() == points.get(i + 1).getX()
						&& points.get(i + 1).getX() == points.get(i + 2).getX()
						|| points.get(i).getY() == points.get(i + 1).getY()
						&& points.get(i + 1).getY() == points.get(i + 2).getY()) {
					points.remove(i + 1);
					i--;
				}
			}
		} catch (NullPointerException e) {
			System.out.println("Pathfinder failed to find a valid path!");
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
	public Path getShortestPath(Location start, Location finish) {

		int startX = (int) start.getX();
		int startY = (int) start.getY();
		int goalX = (int) finish.getX();
		int goalY = (int) finish.getY();

		int[][] obstacleMap = convertMap(myGrid);

		AreaMap map = new AreaMap(myGrid[0].length, myGrid.length, obstacleMap);
		AStarHeuristic heuristic = new DiagonalHeuristic();

		AStar aStar = new AStar(map, heuristic);

		// AreaMap uses transposed map, switch x and y
		ArrayList<Point> points = aStar.calcShortestPath(startY, startX, goalY,
				goalX);

		removeLinearPoints(points);

		ArrayList<Location> locations = new ArrayList<Location>();
		for (Point p : points)
			locations.add(new Location((p.getY() + .5) * myTileSize.getWidth(),
					(p.getX() + .5) * myTileSize.getHeight()));

		return new Path(locations);
	}

	private int[][] convertMap(Tile[][] grid) {
		int[][] obstacleMap = new int[grid.length][grid[0].length];

		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				obstacleMap[i][j] = myGrid[i][j].isWalkable() ? 0 : 1;
			}
		}

		return obstacleMap;
	}
}
