package vooga.fighter.util;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.List;

import util.Location;
import util.Vector;
import vooga.fighter.util.ShapeMeasurements;

/**
 * This class is dedicated to collision detection for 
 * shapes and provides useful methods to:
 * -confirm detections for shapes, either quickly or precisely
 * -check the side of a shape that another shape/point has with 
 *    (Treating all shapes as Rectangles in this case), choice 
 *    in parameters as velocity can be taken into account for 
 *    extra precision.
 *     
 * Please read through the methods, but this class can be both
 * extended to include specific functionality for specific shapes (i.e ellipses)
 * or one can simply use the precise methods and insert their specific shape
 * (note this will not work for checking the sides, as it will always treat the
 * shape as a rectangle!)
 *
 * @author Jack Matteucci 
 * 
 */

public class CollisionDetector {
	
	static final int INTERSECT_LINE_PRECISION = 1000;
	ShapeMeasurements myMeasurements;
	
	public CollisionDetector(){
		myMeasurements = new ShapeMeasurements();
	}
	 /**
	  * Uses rectangle bounds of each shape to check if any point of shape2
	  * is within the rectangle bounds of point 1
	 */
	public boolean quickDetectCollision(Shape shape1, Shape shape2){
		return shape1.getBounds().intersects(shape2.getBounds());
	}

	 /**
	  * Uses rectangle bounds of each shape to check if point2
	  * is within the rectangle bounds of point 1
	 */
	public boolean quickDetectCollision(Shape shape1, Point2D point2){
		return shape1.contains(point2);
	}
	
	 /** 
	  * Precisely tracks the bounds of a shape, checking if for any 
	  * point on the bounds, whether point2 is closer than the 
	  * center than that bound point. 
	  * @param precision is the allowed angle between the vector created
	  * from the center to bound point and the vector created from the 
	  * center to point2
	  * @param rotationAngle is the angle at which shape1 is rotated- degrees
	 */
	public boolean preciseDetectCollision(Shape shape1, Location point2, 
			double rotationAngle, double precision){
		List<Location> boundary = myMeasurements.makeBoundary(shape1, Vector.SanitizeAngle(rotationAngle), 
				ShapeMeasurements.BOUNDARY_PRECISION);
		Location center1 = myMeasurements.getArbitraryShapeCenter(shape1,Vector.SanitizeAngle(rotationAngle));
		return pointWithinBoundary(boundary,center1, point2,precision);
	}
	
	 /** 
	  * Precisely tracks the bounds of a shape, checking if for any 
	  * point on the bounds, whether point2 is closer than the 
	  * center than that bound point. Precision taken to be a degree of 1;
	  * No rotation considered.
	 */		
		public boolean preciseDetectCollision(Shape shape1, Location point2){
			return preciseDetectCollision(shape1,point2, ShapeMeasurements.NO_ROTATION, 
					ShapeMeasurements.ANGLE_PRECISION); 
	}
	
		 /** 
		  * Precisely tracks the bounds of a shape1, checking if for any 
		  * point on the bounds, whether a point on shape2 is closer than shape1's 
		  * center than that shape1 bound point.
		  * @param precision is the allowed angle between the vector created
		  * from the center to bound point and the vector created from the 
		  * center to the current point of shape2 being looked at 
		  * @param rotationAngle1 is the angle at which shape1 is rotated about top left corner- degrees
		  * @param rotationAngle1 is the angle at which shape2 is rotated about top left corner- degrees
		 */
	public boolean preciseDetectCollision(Shape shape1, Shape shape2, double rotationAngle1,
			double rotationAngle2, double precision){
		List<Location> boundary2 = myMeasurements.makeBoundary(shape2, rotationAngle2, 
				ShapeMeasurements.BOUNDARY_PRECISION);
			for(Location boundaryLoc : boundary2){
				if(preciseDetectCollision(shape1, boundaryLoc, rotationAngle1, precision)) return true;
			}		
		return false;
	}
	 /** 
	  * Precisely tracks the bounds of a shape1, checking if for any 
	  * point on the bounds, whether a point on shape2 is closer than shape1's 
	  * center than that shape1 bound point. 
	  * @param precision is the allowed angle between the vector created
	  * from the center to bound point and the vector created from the 
	  * center to the current point of shape2 being looked at.  Precision
	  * set automatically to 1 degree. No rotation considered.
	  */
	public boolean preciseDetectCollsion(Shape shape1, Shape shape2){
		return preciseDetectCollision(shape1,shape2,ShapeMeasurements.NO_ROTATION, 
				ShapeMeasurements.NO_ROTATION, ShapeMeasurements.ANGLE_PRECISION);
	}
	
    /**
     * Convenience method: Treating the Shape as a Rectangle
     * returns whether shape1's top has been collided with by 
     * point2
     */
	public boolean hitTop(Shape shape1, Point2D point2){
		return checkSide(shape1,point2,myMeasurements.getTopLeftCorner(shape1),
				myMeasurements.getTopRightCorner(shape1));
	}


    /**
     * Convenience method: Treating the Shapes as a Rectangles
     * returns whether shape1's top side has been collided with by 
     * shape2
     */
	public boolean hitTop(Shape shape1, Shape shape2){
		return hitTop(shape1, myMeasurements.getBottomRightCorner(shape2))||
				hitTop(shape1, myMeasurements.getBottomLeftCorner(shape2))||
				bothCornersIntersecting(shape1,shape2,shape2.getBounds2D().getMaxY()
						,shape1.getBounds2D().getMinY());
	}
    /**
     * Convenience method: Treating the Shape as a Rectangle
     * returns whether shape1's Top side has been collided with by 
     * the specified point, taking Velocity into account.
     */
	public boolean hitTop(Shape shape1, Location point2, Vector speed1, Vector speed2){
		double vNormalization = getVelocityNormalization(speed1, speed2);
		if(vNormalization == 0) return hitTop(shape1, point2); // Not quite sure how to get rid of this check from directional checks
		return  intersectsLine(shape1, myMeasurements.getTopLeftCorner(shape1), 
				myMeasurements.getTopRightCorner(shape1), 
				point2, speed1, speed2, vNormalization, INTERSECT_LINE_PRECISION);
	}
    /**
     * Convenience method: Treating the Shapes as Rectangles
     * returns whether shape1's Top side has been collided with by 
     * shape2, taking velocity of each shape into account.
     */
	public boolean hitTop(Shape shape1, Shape shape2, Vector speed1, Vector speed2){
		return hitTop(shape1, myMeasurements.getBottomLeftCorner(shape2), speed1, speed2)||
		hitTop(shape1, myMeasurements.getBottomRightCorner(shape2), speed1, speed2);
	}
	
    /**
     * Convenience method: Treating the Shape as a Rectangle
     * returns whether shape1's bottom side has been collided with by 
     * point2
     */
	public boolean hitBottom(Shape shape1, Point2D point2){
		return checkSide(shape1,point2,myMeasurements.getBottomRightCorner(shape1),
				myMeasurements.getBottomLeftCorner(shape1));	
	}
	
    /**
    * Convenience method: Treating the Shapes as a Rectangles
    * returns whether shape1's bottom side has been collided with by 
    * shape2
    */
	public boolean hitBottom(Shape shape1, Shape shape2){
		return(hitBottom(shape1, myMeasurements.getTopRightCorner(shape2))||	
		hitBottom(shape1, myMeasurements.getTopLeftCorner(shape2))||	
		bothCornersIntersecting(shape1,shape2,shape1.getBounds2D().getMaxY()
				,shape2.getBounds2D().getMinY()));
	}
	
    /**
     * Convenience method: Treating the Shape as a Rectangle
     * returns whether shape1's Bottom side has been collided with by 
     * the specified point, taking Velocity into account.
     */
	public boolean hitBottom(Shape shape1, Location point2, Vector speed1, Vector speed2){
		double vNormalization = getVelocityNormalization(speed1, speed2);
		if(vNormalization == 0) return hitBottom(shape1, point2);
		return intersectsLine(shape1, myMeasurements.getBottomLeftCorner(shape1), 
				myMeasurements.getBottomRightCorner(shape1), 
				point2, speed1, speed2, vNormalization, INTERSECT_LINE_PRECISION);
	}
    /**
     * Convenience method: Treating the Shapes as Rectangles
     * returns whether shape1's Bottom side has been collided with by 
     * shape2, taking velocity of each shape into account.
     */
	public boolean hitBottom(Shape shape1, Shape shape2, Vector speed1, Vector speed2){
		return (hitBottom(shape1, myMeasurements.getTopLeftCorner(shape2), speed1, speed2)||
		hitBottom(shape1, myMeasurements.getTopRightCorner(shape2), speed1, speed2));
	}
	

    /**
     * Convenience method: Treating the Shape as a Rectangle
     * returns whether shape1's right side has been collided with by 
     * point2
     */
	public boolean hitRight(Shape shape1, Point2D point2){
		return checkRightSide(shape1,point2, myMeasurements.getTopRightCorner(shape1),
				myMeasurements.getBottomRightCorner(shape1));
	}

    /**
     * Convenience method: Treating the Shapes as a Rectangles
     * returns whether shape1's right side has been collided with by 
     * shape2
     */
	public boolean hitRight(Shape shape1, Shape shape2){
		return hitRight(shape1, myMeasurements.getBottomLeftCorner(shape2))||
				hitRight(shape1, myMeasurements.getTopLeftCorner(shape2))||
				bothCornersIntersecting(shape1,shape2,shape2.getBounds2D().getMaxX()
				,shape1.getBounds2D().getMinX());
		
	}
    /**
     * Convenience method: Treating the Shape as a Rectangle
     * returns whether shape1's Right side has been collided with by 
     * the specified point, taking Velocity into account.
     */
	
	public boolean hitRight(Shape shape1, Location point2, Vector speed1, Vector speed2){
		double vNormalization = getVelocityNormalization(speed1, speed2);
		if(vNormalization == 0) return hitRight(shape1, point2);
		return  intersectsLine(shape1, myMeasurements.getBottomRightCorner(shape1), 
				myMeasurements.getTopRightCorner(shape1), 
				point2, speed1, speed2, vNormalization, INTERSECT_LINE_PRECISION);
	}
    /**
     * Convenience method: Treating the Shapes as Rectangles
     * returns whether shape1's Right side has been collided with by 
     * shape2, taking velocity of each shape into account.
     */
	public boolean hitRight(Shape shape1, Shape shape2, Vector speed1, Vector speed2){
		return hitRight(shape1, myMeasurements.getBottomLeftCorner(shape2), speed1, speed2)||
				hitRight(shape1, myMeasurements.getTopLeftCorner(shape2), speed1, speed2);
	}
	
	
    /**
     * Convenience method: Treating the Shape as a Rectangle
     * returns whether shape1's left side has been collided with by 
     * point2
     */
	public boolean hitLeft(Shape shape1, Point2D point2){
		return checkSide(shape1,point2,myMeasurements.getBottomLeftCorner(shape1),
				myMeasurements.getTopLeftCorner(shape1));	
	}
	

    /**
     * Convenience method: Treating the Shapes as a Rectangles
     * returns whether shape1's left side has been collided with by 
     * shape2
     */
	public boolean hitLeft(Shape shape1, Shape shape2){
		return(hitLeft(shape1, myMeasurements.getBottomRightCorner(shape2))||	
		hitLeft(shape1, myMeasurements.getTopRightCorner(shape2))||
		bothCornersIntersecting(shape1,shape2,shape1.getBounds2D().getMaxX()
				,shape2.getBounds2D().getMinX()));
	}
    /**
     * Convenience method: Treating the Shape as a Rectangle
     * returns whether shape1's Left side has been collided with by 
     * the specified point, taking Velocity into account.
     */
	public boolean hitLeft(Shape shape1, Location point2, Vector speed1, Vector speed2){
		double vNormalization = getVelocityNormalization(speed1, speed2);
		if(vNormalization == 0) return hitLeft(shape1, point2);
		return  intersectsLine(shape1, myMeasurements.getTopLeftCorner(shape1), 
				myMeasurements.getBottomLeftCorner(shape1), 
				point2, speed1, speed2, vNormalization, INTERSECT_LINE_PRECISION);
	}
    /**
     * Convenience method: Treating the Shapes as Rectangles
     * returns whether shape1's Left side has been collided with by 
     * shape2, taking velocity of each shape into account.
     */
	public boolean hitLeft(Shape shape1, Shape shape2, Vector speed1, Vector speed2){
		return hitLeft(shape1, myMeasurements.getTopRightCorner(shape2), speed1, speed2)||
		hitLeft(shape1, myMeasurements.getBottomRightCorner(shape2), speed1, speed2);
	}
   
	/**
     * Convenience method:
     * Returns direction from center of Shape to a Point.
     */
	protected double getQuickDirection (Shape shape1, Point2D point2){
			Location center = (Location) myMeasurements.getRectangleCenter(shape1);
			return Vector.SanitizeAngle(Vector.angleBetween(point2, center));
	}
	 /**
	  * Convenience method to find if a point is within a path (closer to center than
	  * the outlining path is)
	 */
	protected boolean pointWithinBoundary(List<Location> boundary, Location center1, Location point2, double precision){
		Vector vector2 = center1.difference(point2);
		for(Location boundaryLoc : boundary){
			Vector vector1 = center1.difference(boundaryLoc);
			if(Math.abs(Vector.SanitizeAngle(vector1.getAngleBetween(vector2)))<precision&&
					vector1.getMagnitude()>= vector2.getMagnitude()){
				return true;
			}
		}
		return false;
	}
	 /**
	  * Convenience method to get a non-infinity Noramlization constant for
	  * velocities (used so that backtracking's precision does not depend on velocity 
	  * magnitude
	 */
	protected double getVelocityNormalization(Vector velocity1, Vector velocity2){
		if(velocity1.getMagnitude() == 0 && velocity2.getMagnitude() == 0) return 0;
		if(velocity1.getMagnitude() >=velocity2.getMagnitude()) return 1/velocity1.getMagnitude();
		else return 1/velocity2.getMagnitude();
	}
	 /**
	  * Translates Shape back one fram time depending on given velocity and normalization constant
	  * @param vNormalization = 1 uses velocity as is.
	 */
	protected Shape backTranslate(Shape shape1, Vector velocity, double vNormalization){
		Location newTopLeft = backTranslate(myMeasurements.getTopLeftCorner(shape1), velocity, vNormalization);
		return new Rectangle((int) newTopLeft.getX(), (int) newTopLeft.getY() , (int) shape1.getBounds2D().getWidth(),
				(int) shape1.getBounds2D().getHeight());		
	}
	 /**
	  * Translates point back one frame time depending on given velocity and normalization constant
	  * @param vNormalization = 1 uses velocity as is.
	 */
	protected Location backTranslate(Location loc, Vector velocity, double vNormalization){
		return new Location(loc.getX()-(velocity.getXChange()*vNormalization),
				loc.getY()-(velocity.getYChange()*vNormalization));	
	}
	
	 /**
	  * Convenience Method
	  * Determines whether a particular point has at some point in the past 
	  * crossed the boundary line of a Rectangle, given it is currently within 
	  * the rectangle. 
	  * @param corner 1 and corner 2 are the two corners one would like to connect a line between
	  * @param speed1 and speed2 are the speeds of Shape1 and point2 respectively
	  * @param numOfPoint is the number of points on desires the boundary line to be described by
	 */
	protected boolean intersectsLine(Shape shape1, Location corner1, Location corner2, 
			Location point2, Vector speed1, Vector speed2, double vNormalization, int numOfPoints){
	while(quickDetectCollision(shape1,point2)){
		if(myMeasurements.pointOnBoundary(myMeasurements.makeBoundaryLine
				(corner1,corner2,numOfPoints),point2, 2)) return true;
		else{
			shape1 = backTranslate(shape1, speed1, vNormalization);
			point2 = backTranslate(point2, speed2, vNormalization);
		}
	}
	return false;
	}
	 /**
	  * Convenience Method
	  * Checks a side of a Rectangle, and depending on inputs, will return a boolean
	  * as to whether the given point hit that side NOTE: only works for RIGHT SIDE
	  */
	private boolean checkRightSide(Shape shape1, Point2D point2, Location corner1, Location corner2){
		if(!quickDetectCollision(shape1,point2)) return false; //a line of repeated code :( don't really know how to refactor this out
		double hitdirection = getQuickDirection(shape1,point2);
		return (hitdirection-getQuickDirection(shape1, corner1 ) > 0 ||
			(hitdirection-getQuickDirection(shape1,corner2)) <= 0);
	}
	 /**
	  * Convenience Method
	  * Checks a side of a Rectangle, and depending on inputs, will return a boolean
	  * as to whether the given point hit that side  NOTE: DOES NOT WORK FOR RIGHT SIDE
	  */
	private boolean checkSide(Shape shape1, Point2D point2, Location corner1, Location corner2){
		if(!quickDetectCollision(shape1,point2)) return false;
		return withinCorners(shape1, getQuickDirection(shape1,point2), corner1, corner2);
	}
	 /**
	  * Convenience Method to get rid of duplicated code:
	  * checks if the direction is within the direction of the two corners
	  */
	private boolean withinCorners(Shape shape1, double hitdirection, 
			Location corner1,Location corner2){
		return (hitdirection-getQuickDirection(shape1, corner1 ) > 0 &&
				(hitdirection-getQuickDirection(shape1,corner2)) <= 0);
	}
	 /**
	  * Convenience Method to get rid of duplicated code:
	  * checks if both corners are within the shape
	  */
	private boolean bothCornersIntersecting(Shape shape1, Shape shape2, double lowbound, double highbound){
		return (quickDetectCollision(shape1,shape2)&&
		(lowbound >=highbound));
	}
	
}
