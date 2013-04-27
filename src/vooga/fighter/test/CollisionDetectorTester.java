package vooga.fighter.test;

import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.junit.Test;

import util.Location;
import util.Vector;
import vooga.fighter.util.CollisionDetector;
import vooga.fighter.util.ShapeMeasurements;

public class CollisionDetectorTester {
	CollisionDetector myDetector;
	
    public void setUp(){
    	myDetector = new CollisionDetector();
    }
	
	@Test
	public void testpreciseDetectCollisionPoint (){
		setUp();
		Location loc = new Location(0,50);
		Ellipse2D ellipse = new Ellipse2D.Float(0,0, 100, 100);
        assertTrue(myDetector.preciseDetectCollision(ellipse,loc));
        }
	


	@Test
	public void testquickDetectCollision (){
		setUp();
		Rectangle rect2 = new Rectangle(100,100);
		Location loc = new Location(0,0);
		assertTrue(myDetector.quickDetectCollision(rect2, loc));
	}
    
	@Test
	public void testHitTopPoint() {
		setUp();
		Rectangle rect1 = new Rectangle(100,100);
		Location loc = new Location(50,10);
		assertTrue(myDetector.hitTop(rect1,loc));
	}
	
	@Test
	public void testHitBottomPoint() {
		setUp();
		Rectangle rect1 = new Rectangle(100,100);
		Location loc = new Location(50,90);
		assertTrue(myDetector.hitBottom(rect1,loc));
	}
	
	@Test
	public void testHitRightPoint() {
		setUp();
		Rectangle rect1 = new Rectangle(100,100);
		Location loc = new Location(95,50);
		assertTrue(myDetector.hitRight(rect1,loc));
	}
	@Test
	public void testHitLeftPoint() {
		setUp();
		Rectangle rect1 = new Rectangle(100,100);
		Location loc = new Location(10,50);
		assertTrue(myDetector.hitLeft(rect1,loc));
	}
	
	
	@Test
	public void testHitTop() {
		setUp();
		Rectangle rect1 = new Rectangle(100,100);
		Rectangle rect2 = new Rectangle(50,10,1,1);
		assertTrue(myDetector.hitTop(rect1,rect2));
	}
	
	@Test
	public void testHitBottom() {
		setUp();
		Rectangle rect1 = new Rectangle(100,100);
		Rectangle rect2 = new Rectangle(50,90,20,200);
		assertTrue(myDetector.hitBottom(rect1,rect2));
	}
	
	@Test
	public void testHitRight() {
		setUp();
		Rectangle rect1 = new Rectangle(100,100);
		Rectangle rect2 = new Rectangle(90, 50,20,200);
		assertTrue(myDetector.hitRight(rect1,rect2));
	}
	@Test
	public void testHitLeft() {
		setUp();
		Rectangle rect1 = new Rectangle(100,100);
		Rectangle rect2 = new Rectangle(10,50,1,1);
		assertTrue(myDetector.hitLeft(rect1,rect2));
	}
	
	@Test
	public void testHitTopwvelocity() {
		setUp();
		Rectangle rect1 = new Rectangle(100,100);
		Location loc = new Location(99, 50);
		Vector speed1 = new Vector(0,0);
		Vector speed2 = new Vector(90, 10);
		assertTrue(myDetector.hitTop(rect1, loc, speed1, speed2));
	}
	
	@Test
	public void testHitBottomwvelocity() {
		setUp();
		Rectangle rect1 = new Rectangle(100,100);
		Location loc = new Location(99, 50);
		Vector speed1 = new Vector(0,0);
		Vector speed2 = new Vector(270, 10);
		assertTrue(myDetector.hitBottom(rect1, loc, speed1, speed2));
	}
	
	@Test
	public void testHitleftwvelocity() {
		setUp();
		Rectangle rect1 = new Rectangle(100,100);
		Location loc = new Location(50, 50);
		Vector speed1 = new Vector(0,0);
		Vector speed2 = new Vector(45, 10);
		assertTrue(myDetector.hitLeft(rect1, loc, speed1, speed2));
	}
	
	@Test
	public void testHitwvelocity() {
		setUp();
		Rectangle rect1 = new Rectangle(100,100);
		Rectangle rect2 = new Rectangle(10, 10, 10, 10);
		Vector speed1 = new Vector(0,0);
		Vector speed2 = new Vector(0, 10);
		assertTrue(myDetector.hitLeft(rect1, rect2, speed1, speed2));
	}
}
