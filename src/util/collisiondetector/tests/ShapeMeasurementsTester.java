package util.collisiondetector.tests;


import static org.junit.Assert.assertTrue;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

import org.junit.Test;

import util.Location;
import util.collisiondetector.ShapeMeasurements;

public class ShapeMeasurementsTester {
	ShapeMeasurements myShapeMeasurements;

	public ShapeMeasurementsTester() {
	}
	
	public void setup(){
		myShapeMeasurements = new ShapeMeasurements();
		}
	
	@Test
	public void testgetQuickCenter() {
		setup();
		Rectangle rect2 = new Rectangle(100,100);
		Ellipse2D ellipse = new Ellipse2D.Float(0,0, 100, 100);
		assertTrue(myShapeMeasurements.getRectangleCenter(rect2).getX()==50.0);
		assertTrue(myShapeMeasurements.getRectangleCenter(rect2).getY()==50.0);
		assertTrue(myShapeMeasurements.getRectangleCenter(ellipse).getX()==50.0);
		assertTrue(myShapeMeasurements.getRectangleCenter(ellipse).getY()==50.0);
	}
	
	@Test
	public void testgetPrecisionCenter() {
		setup();
		Rectangle rect2 = new Rectangle(100,100);
		Ellipse2D ellipse = new Ellipse2D.Double(0,0, 100, 100);
		assertTrue(Math.abs(myShapeMeasurements.getArbitraryShapeCenter(rect2).getX()-50.0)<.1);
		assertTrue(Math.abs(myShapeMeasurements.getArbitraryShapeCenter(rect2).getY()-50.0)<.1);
		assertTrue(Math.abs(myShapeMeasurements.getArbitraryShapeCenter(ellipse).getX()-50.0)<.1);
		assertTrue(Math.abs(myShapeMeasurements.getArbitraryShapeCenter(ellipse).getY()-50.0)<.1);
	}
	
	@Test
	public void testgetPrecisionCenterWDirection() {
		setup();
		Ellipse2D ellipse = new Ellipse2D.Float(0,0,100,100);
		assertTrue(Math.abs(myShapeMeasurements.getArbitraryShapeCenter(ellipse, 90).getX()+50.0)<.1);
		assertTrue(Math.abs(myShapeMeasurements.getArbitraryShapeCenter(ellipse, 90).getY()-50.0)<.1);
	}
	
	@Test
	public void testgetBottomRightCorner() {
		setup();
		Rectangle rect2 = new Rectangle(100,100);
		assertTrue(myShapeMeasurements.getBottomRightCorner(rect2).getX()==100);
		assertTrue(myShapeMeasurements.getBottomRightCorner(rect2).getY()==100);
	}
	
	@Test
	public void testgetBottomLeftCorner() {
		setup();
		Rectangle rect2 = new Rectangle(100,100);
		assertTrue(myShapeMeasurements.getBottomLeftCorner(rect2).getX()==0);
		assertTrue(myShapeMeasurements.getBottomLeftCorner(rect2).getY()==100);
	}
	
	@Test
	public void testRotatingCorner() {
		setup();
		Rectangle rect2 = new Rectangle(100,100);
		assertTrue(Math.abs(myShapeMeasurements.getBottomLeftCorner(rect2).getX()-
				myShapeMeasurements.getTurnedBottomLeftCorner(rect2, 0).getX())<.001);
		assertTrue(Math.abs(myShapeMeasurements.getBottomRightCorner(rect2).getY()-
				myShapeMeasurements.getTurnedBottomRightCorner(rect2, 0).getY())<.001);
		assertTrue(Math.abs(-100-myShapeMeasurements.getTurnedBottomLeftCorner(rect2, 90).getX())<.001);
		assertTrue(Math.abs(-100 - myShapeMeasurements.getTurnedBottomRightCorner(rect2, 90).getX())<.001);
		assertTrue(Math.abs( myShapeMeasurements.getTurnedTopRightCorner(rect2, 90).getX())<.001);
	}


}
