package util.collisiondetection.tests;

import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.junit.Test;

import util.Location;
import util.collisiondetection.CollisionDetector;

public class CollisionDetectorTester {
	CollisionDetector myDetector;
	
    public void setUp(){
    	myDetector = new CollisionDetector();
    }
	
	@Test
	public void testpreciseDetectCollisionPoint (){
		setUp();
		Rectangle2D rect2 = new Rectangle(100,100);
		Location loc = new Location(5,4);
		Ellipse2D ellipse = new Ellipse2D.Float(0,0, 10, 10);
        assertTrue(myDetector.preciseDetectCollision(rect2, loc));
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
		Location loc = new Location(90,50);
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
		Rectangle rect2 = new Rectangle(50,90,1,1);
		assertTrue(myDetector.hitBottom(rect1,rect2));
	}
	
	@Test
	public void testHitRight() {
		setUp();
		Rectangle rect1 = new Rectangle(100,100);
		Rectangle rect2 = new Rectangle(90, 50,1,1);
		assertTrue(myDetector.hitRight(rect1,rect2));
	}
	@Test
	public void testHitLeft() {
		setUp();
		Rectangle rect1 = new Rectangle(100,100);
		Rectangle rect2 = new Rectangle(10,50,1,5);
		assertTrue(myDetector.hitLeft(rect1,rect2));
	}

}
