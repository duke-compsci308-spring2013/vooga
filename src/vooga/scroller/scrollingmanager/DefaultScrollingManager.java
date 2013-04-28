
package vooga.scroller.scrollingmanager;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import vooga.scroller.model.Model;
import vooga.scroller.sprites.superclasses.Player;
import util.Location;
import vooga.scroller.view.GameView;
/**
 * The default scrolling manager
 * @author Ross
 *
 */
public class DefaultScrollingManager extends ScrollingManager {
    private Model myGame;
    private GameView myView;
    
    public void initGame(Model game) {
        myGame = game;
    }

    @Override
	public void initView(GameView view) {
        myView = view;
    }

    @Override
	public int upperpaintbound() {
        if(myGame != null & myView != null) {
            int vertical = ((int) myGame.getLowerBoundary() + myView.getHeight()*1000) % myView.getHeight();
            return 0 - vertical;
        }
        return 0;
    }

    @Override
	public int lowerpaintbound() { 
        if(myGame != null & myView != null) {
            int vertical = ((int) myGame.getLowerBoundary() + myView.getHeight()*1000) % myView.getHeight();
            return myView.getHeight() - vertical;
        }
        return 0;

    }

    @Override
	public int leftpaintbound() {
        if(myGame != null & myView != null) {
            int horizontal = ((int) myGame.getRightBoundary() + myView.getWidth()*1000) % myView.getWidth();
            return 0 - horizontal;
        }
        return 0;
    }

    @Override
	public int rightpaintbound() {
        if(myGame != null & myView != null) {
            int horizontal = ((int) myGame.getRightBoundary() + myView.getWidth()*1000) % myView.getWidth();
            return myView.getWidth() - horizontal;
        }
        return 0;
    }
    
    @Override
	public double getRightBoundary(Dimension frame, Location center) {
        return (center.getX() + frame.getWidth() / 2);
    }
    
    @Override
	public double getLeftBoundary(Dimension frame, Location center) {
        return (center.getX() - frame.getWidth() / 2);
    }
    
    @Override
	public double getUpperBoundary(Dimension frame, Location center) {
        return (center.getY() - frame.getHeight() / 2);
    }
    
    @Override
	public double getLowerBoundary(Dimension frame, Location center) { 
        return (center.getY() + frame.getHeight() / 2);
    }

    @Override
	public double levelRightBoundary () {
        return myGame.getLevelBounds().getWidth();
    }

    @Override
	public double levelLeftBoundary () {
        return 0;
    }

    @Override
	public double levelUpperBoundary () {
        return 0;
    }

    @Override
	public double levelLowerBoundary () {        
        return myGame.getLevelBounds().getHeight();
    }
    
    @Override
	public Image getBackground() {
        return myGame.getBackground();
    }
    
    @Override
	public void viewPaint(Graphics pen) {
        Image img = getBackground();
        int imgwidth = img.getWidth(null);
        int imgheight = img.getHeight(null);
        int leftpaintbound = leftpaintbound();
        int upperpaintbound = upperpaintbound();
        int rightpaintbound = rightpaintbound();
        int lowerpaintbound = lowerpaintbound();
        
        if(myGame.getLeftBoundary() < levelLeftBoundary()) {
            leftpaintbound = (int) levelLeftBoundary();
            rightpaintbound = (int) levelRightBoundary();
        }
        
        if(myGame.getRightBoundary() > levelRightBoundary()) {
            //Messy code
            leftpaintbound =  - ((int) levelRightBoundary() % myGame.getBackground().getWidth(null));
            rightpaintbound = myView.getWidth()  - ((int) levelRightBoundary() % myGame.getBackground().getWidth(null));
            
        }
        if(myGame.getLowerBoundary() > levelLowerBoundary()) {
            upperpaintbound = - ((int) levelLowerBoundary() % myGame.getBackground().getHeight(null));
            lowerpaintbound = myView.getHeight()  - ((int) levelLowerBoundary() % myGame.getBackground().getHeight(null));
        }
        if(myGame.getUpperBoundary() < levelUpperBoundary()) {
            upperpaintbound = (int) levelUpperBoundary();
            lowerpaintbound = (int) levelLowerBoundary();
        }
        pen.drawImage(img, leftpaintbound, upperpaintbound, imgwidth, imgheight, null);
        pen.drawImage(img, rightpaintbound, upperpaintbound, imgwidth, imgheight, null);
        pen.drawImage(img, leftpaintbound, lowerpaintbound, imgwidth, imgheight, null);
        pen.drawImage(img, rightpaintbound, lowerpaintbound, imgwidth, imgheight, null);
        myGame.paint((Graphics2D) pen);
    }

    @Override
	public Location playerPaintLocation (Player p) {
        double x = myView.getWidth() / 2;
        double y = myView.getHeight() / 2;
        if(p.getX() > (levelRightBoundary() - myView.getWidth() / 2)) {
            x =  (myView.getWidth() / 2) + ((myView.getWidth() / 2) - (levelRightBoundary() - p.getX()));
        }
        if(p.getX() < (levelLeftBoundary() + myView.getWidth() / 2)) {
            x =  (myView.getWidth() / 2) - ((myView.getWidth() / 2) - (levelLeftBoundary() + p.getX()));
        }
        if(p.getY() > (levelLowerBoundary() - myView.getHeight() / 2)) {
            y =  (myView.getHeight() / 2) + ((myView.getHeight() / 2) - (levelLowerBoundary() - p.getY()));
        }
        if(p.getY() < (levelUpperBoundary() + myView.getHeight() / 2)) {
            y =  (myView.getHeight() / 2) - ((myView.getHeight() / 2) - (levelUpperBoundary() + p.getY()));
        }
        
        return new Location(x, y);
        
    }
}
