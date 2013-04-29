package games.fighter.billMuenstermanJouster.util;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;

import util.Location;
import util.Vector;

public class ShapeMeasurements {
	
	 /**
	  * A class that returns basic measurements of 
	  * Shapes
	 */
	public static final double ANGLE_PRECISION = 1;
	public static final double BOUNDARY_PRECISION = 1000;
	public static final int INTERSECT_LINE_PRECISION = 1000;
	public static final double NO_ROTATION = 0;
	public ShapeMeasurements() {
	}
	
	 /**
	  * Uses rectange bounds of each shape to get a quick 
	  * Point2D for the center.
	 */
	public Point2D getRectangleCenter(Shape shape1){
		Rectangle2D rect = shape1.getBounds2D();
		return new Location(rect.getCenterX(), rect.getCenterY());
	}
	 /**
	  * Uses path of bounds to precisely calculate the centroid of 
	  * the shape.  DISCLAMER: Does not work for RECTANGLES.  Just
	  * use getRectangleCenter()
	 */
	public Location getArbitraryShapeCenter(Shape shape1, double rotationAngle){
		List<Location> boundary = makeBoundary(shape1,rotationAngle, BOUNDARY_PRECISION);
		if(shape1 instanceof Rectangle) boundary = makeRectangleBoundary(shape1, rotationAngle, INTERSECT_LINE_PRECISION);
		float sumX = 0;
		float sumY = 0;
		int count = 0;
;		for(Location loc : boundary){
				sumX += loc.getX();
				sumY += loc.getY();
				count++;
		}
		return new Location(sumX/count, sumY/count);
	}
	
	
	
	 /**
	  * Uses path of bounds to precisely calculate the centroid of 
	  * the shape.  DISCLAMER: if the shape has holes (like a donut
	  * this method will find the center to be inside the donut, 
	  * so use wisely! no rotation considered
	 */
	public Location getArbitraryShapeCenter(Shape shape1){
		return getArbitraryShapeCenter(shape1,0);
	}
	
	 /**
	  * Treating the shape as a rectangle, returns the location of
	  * the new Location of the past Bottom RightCorner.  NOTE: this 
	  * does not still mean it will be "Bottom" or "Right" anymore.
	  * 
	  * @param angle is the angle ther shape is rotated around the top left corner
	 */
	public Location getTurnedBottomRightCorner(Shape shape1,double angle){
		return getTurnedPoint(shape1, getBottomRightCorner(shape1), angle);
	}
	
	 /**
	  * Treating the shape as a rectangle, returns 
	  *  the location of the Bottom Right Corner
	 */
	public Location getBottomRightCorner(Shape shape1){
		Rectangle2D rect = shape1.getBounds2D();
		return new Location(rect.getMaxX(),rect.getMaxY());
	}
	
	 /**
	  * Treating the shape as a rectangle, returns the location of
	  * the new Location of the past BottomLeftCorner.  NOTE: this 
	  * does not still mean it will be "Bottom" or "Left" anymore.
	  * 
	  * @param angle is the angle ther shape is rotated around the top left corner
	 */
	public Location getTurnedBottomLeftCorner(Shape shape1,double angle){
		return getTurnedPoint(shape1, getBottomLeftCorner(shape1), angle);
	}
	
	
	 /**
	  * Treating the shape as a rectangle, returns the location of
	  * the bottom left corner
	 */
	public Location getBottomLeftCorner(Shape shape1){
		Rectangle2D rect = shape1.getBounds2D();
		return new Location(rect.getMinX(),rect.getMaxY());
	}
	
	
	 /**
	  * Treating the shape as a rectangle, returns the location of
	  * the new Location of the past TopRightCorner.  NOTE: this 
	  * does not still mean it will be "Top" or "Right" anymore.
	  * 
	  * @param angle is the angle ther shape is rotated around the top left corner
	 */
	public Location getTurnedTopRightCorner(Shape shape1,double angle){
		return getTurnedPoint(shape1, getTopRightCorner(shape1), angle);
	}
	
	 /**
	  * Treating the shape as a rectangle, returns the location of
	  * the top right Corner
	 */
	public Location getTopRightCorner(Shape shape1){
		Rectangle2D rect = shape1.getBounds2D();
		return new Location(rect.getMaxX(),rect.getMinY());
	}
	
	 /**
	  * Treating the shape as a rectangle, returns the location of
	  * the top left corner
	  * 
	  * @param angle is the angle ther shape is rotated around the top left corner
	 */
	public Location getTopLeftCorner(Shape shape1,double angle){
		Rectangle2D rect = shape1.getBounds2D();
		return new Location(rect.getMinX(),rect.getMinY());
	}
	
	 /**
	  * Treating the shape as a rectangle, returns the location of
	  * the top left corner
	 */
	public Location getTopLeftCorner(Shape shape1){
		Rectangle2D rect = shape1.getBounds2D();
		return new Location(rect.getMinX(),rect.getMinY());
	}
	
	 /**
	  * Creates an ordered list of locations, describing the boundary of a shape
	  * 
	  * @param precision represents the definition with which the Boundary is described.
	  * The higher the precision, the better the definition.  p = 1000 typically a 
	  * good value to precisely describe a shape
	 */
	public List<Location> makeBoundary(Shape shape1, Double rotationAngle, double precision){
		if(precision == 0) precision = BOUNDARY_PRECISION;
		AffineTransform transform1 = AffineTransform.getRotateInstance(Math.toRadians(
				Vector.SanitizeAngle(rotationAngle)));
		List<Location> boundary = new LinkedList<Location>();
		float[] coord1 = new float[6];
		PathIterator path1 = shape1.getPathIterator(transform1, 1/precision);
		while(!path1.isDone()){
			path1.currentSegment(coord1);
			Location loc = new Location(coord1[0], coord1[1]);
			boundary.add(loc);
			path1.next();
		}
		return boundary;
	}
	
	 /**
	  * Creates an ordered list of locations, describing the boundary of a shape
	  * 
	  * @param precision represents the definition with which the Boundary is described.
	  * The higher the precision, the better the definition.  p = 1000 typically a 
	  * good value to precisely describe a shape
	 */
	public List<Location> makeBoundary(Shape shape1){
		return makeBoundary(shape1, NO_ROTATION, BOUNDARY_PRECISION);
	}
	
	 /**
	  * Convenience method to create a List of locations that describe a particular line
	  * from loc1 to loc2
	  * 
	  * @param NumberOfPoints is the number of points that will be used to describe the line.
	 */
	public List<Location> makeBoundaryLine(Location loc1, Location loc2, int NumberOfPoints){
		List<Location> line  = new LinkedList<Location>();
		Vector vec = new Vector(loc1,loc2);
		double x = loc1.getX();
		double y = loc1.getY();
		for(int i = 0; i<NumberOfPoints; i++){
			line.add(new Location(x,y));
			x += (vec.getMagnitude()*Math.cos(Math.toRadians(vec.getDirection()))/NumberOfPoints);
			y -= (vec.getMagnitude()*Math.sin(Math.toRadians(vec.getDirection()))/ NumberOfPoints);
		}
		return line;
	}
	
	
	 /**
	  * Returns a list of Locations that describe a rectangular boundary.
	  * 
	  * @param is the angle the rectangle is rotated about its top left corner
	 */
	public List<Location> makeRectangleBoundary(Shape shape1, double rotationAngle, int numOfPoints){
		List<List<Location>> rectSideList = new LinkedList<List<Location>>();
		List<Location> rectBoundary = new LinkedList<Location>();
		rectSideList.add(makeBoundaryLine(getTopLeftCorner(shape1), getTurnedTopRightCorner(shape1, rotationAngle), numOfPoints));
		rectSideList.add(makeBoundaryLine(getTurnedTopRightCorner(shape1, rotationAngle), getTurnedBottomRightCorner(shape1, rotationAngle), numOfPoints));
		rectSideList.add(makeBoundaryLine(getTurnedBottomRightCorner(shape1, rotationAngle), getTurnedBottomLeftCorner(shape1, rotationAngle), numOfPoints));
		rectSideList.add(makeBoundaryLine(getTurnedBottomLeftCorner(shape1, rotationAngle), getTopLeftCorner(shape1), numOfPoints));
		for(List<Location> side: rectSideList){
			for(Location loc: side){
				rectBoundary.add(loc);
			}
		}
		return rectBoundary;
	}
	
	public List<Location> makeRectangularBoundary(Shape shape1){
		return makeRectangleBoundary(shape1, NO_ROTATION, INTERSECT_LINE_PRECISION);
	}
	
	 /**
	  * Returns whether a Locations is within a certain distance of a list of locations
	  * 
	  * @param precision is how close in pixels the point has to be to the boundary
	 */
	public boolean pointOnBoundary(List<Location> line, Location point, double precision){
		for(Location point2: line){
			if(Math.abs(point2.distance(point))< precision) return true;
		}
		return false;
	}
	
	 /**
	  * Returns based on tha angle turned, the new Location of a point on an original rectangle
	  * All rotation is about the top left corner
	 */
	private Location getTurnedPoint(Shape shape1, Location point, double angle){
		Vector v = new Vector(getTopLeftCorner(shape1),point);
		v.turn(-angle);	
		return new Location(getTopLeftCorner(shape1).getX() + v.getXChange(),
				getTopLeftCorner(shape1).getX() - v.getYChange());  // For some reason the translate function
		//of the Vector class way not working properly here. 
	}


}
