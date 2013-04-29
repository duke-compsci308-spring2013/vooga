
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
//    private GameView getView();
    
    public void initGame(Model game) {
        myGame = game;
    }

//    public void initView(GameView view) {
//    }

    public int upperpaintbound() {
        if(myGame != null & getView() != null) {
            int vertical = ((int) myGame.getLowerBoundary() + 
                    getView().getHeight()*1000) % getView().getHeight();
            return 0 - vertical;
        }
        return 0;
    }

    public int lowerpaintbound() { 
        if(myGame != null & getView() != null) {
            int vertical = ((int) myGame.getLowerBoundary() + getView().getHeight()*1000) % getView().getHeight();
            return getView().getHeight() - vertical;
        }
        return 0;

    }

    public int leftpaintbound() {
        if(myGame != null & getView() != null) {
            int horizontal = ((int) myGame.getRightBoundary() + getView().getWidth()*1000) % getView().getWidth();
            return 0 - horizontal;
        }
        return 0;
    }

    public int rightpaintbound() {
        if(myGame != null & getView() != null) {
            int horizontal = ((int) myGame.getRightBoundary() + getView().getWidth()*1000) % getView().getWidth();
            return getView().getWidth() - horizontal;
        }
        return 0;
    }
    
    public double getRightBoundary(Dimension frame, Location center) {
        return (center.getX() + frame.getWidth() / 2);
    }
    
    public double getLeftBoundary(Dimension frame, Location center) {
        return (center.getX() - frame.getWidth() / 2);
    }
    
    public double getUpperBoundary(Dimension frame, Location center) {
        return (center.getY() - frame.getHeight() / 2);
    }
    
    public double getLowerBoundary(Dimension frame, Location center) { 
        return (center.getY() + frame.getHeight() / 2);
    }

    public double levelRightBoundary () {
        return myGame.getLevelBounds().getWidth();
    }

    public double levelLeftBoundary () {
        return 0;
    }

    public double levelUpperBoundary () {
        return 0;
    }

    public double levelLowerBoundary () {        
        return myGame.getLevelBounds().getHeight();
    }
    
    public Image getBackground() {
        return myGame.getBackground();
    }
    
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
            rightpaintbound = getView().getWidth()  - ((int) levelRightBoundary() % myGame.getBackground().getWidth(null));
            
        }
        if(myGame.getLowerBoundary() > levelLowerBoundary()) {
            upperpaintbound = - ((int) levelLowerBoundary() % myGame.getBackground().getHeight(null));
            lowerpaintbound = getView().getHeight()  - ((int) levelLowerBoundary() % myGame.getBackground().getHeight(null));
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

    public Location playerPaintLocation (Player p) {
        double x = getView().getWidth() / 2;
        double y = getView().getHeight() / 2;
        if(p.getX() > (levelRightBoundary() - getView().getWidth() / 2)) {
            x =  (getView().getWidth() / 2) + ((getView().getWidth() / 2) - (levelRightBoundary() - p.getX()));
        }
        if(p.getX() < (levelLeftBoundary() + getView().getWidth() / 2)) {
            x =  (getView().getWidth() / 2) - ((getView().getWidth() / 2) - (levelLeftBoundary() + p.getX()));
        }
        if(p.getY() > (levelLowerBoundary() - getView().getHeight() / 2)) {
            y =  (getView().getHeight() / 2) + ((getView().getHeight() / 2) - (levelLowerBoundary() - p.getY()));
        }
        if(p.getY() < (levelUpperBoundary() + getView().getHeight() / 2)) {
            y =  (getView().getHeight() / 2) - ((getView().getHeight() / 2) - (levelUpperBoundary() + p.getY()));
        }
        
        return new Location(x, y);
        
    }
}
