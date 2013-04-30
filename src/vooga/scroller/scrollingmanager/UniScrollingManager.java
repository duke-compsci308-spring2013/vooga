
package vooga.scroller.scrollingmanager;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import util.Location;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.util.Direction;

/**
 * This manager prevents players from scrolling in a direction but so far EX like Mario
 * @author Ross Cahoon
 *
 */
public class UniScrollingManager extends ScrollingManager {

    private Direction myRestrictiveDirection;
    private double myMaxDirection;
    private Location myLastPlayerPaintLocation;
    private int myLeftPaintBound;
    private int myUpperPaintBound;
    private int myRightPaintBound;
    private int myLowerPaintBound;
    private boolean myHorizontalScrollActive = false;
    private boolean myVerticalScrollActive = false;

    /**
     * The ScrollingManager for a unidirectional scroller (For example, Mario). 
     * @param restrictedDirection The direction that the game can not scroll in.
     */
    public UniScrollingManager(Direction restrictedDirection) {
        myRestrictiveDirection = restrictedDirection;
        myLeftPaintBound = leftpaintbound();
        myUpperPaintBound = upperpaintbound();
        myRightPaintBound = rightpaintbound();
        myLowerPaintBound = lowerpaintbound();
    }


    protected int upperpaintbound() {
        if (getModel() != null & getView() != null) {
            int vertical = (int) (((int) getModel().getLowerBoundary() 
                    + getView().getHeight() * levelLowerBoundary()) % getBackground().getHeight(null));
            return 0 - vertical;
        }
        return 0;
    }

    protected int lowerpaintbound() { 
        if (getModel() != null & getView() != null) {
            int vertical = (int) (((int) getModel().getLowerBoundary() 
                    + getView().getHeight() * levelLowerBoundary()) % getBackground().getHeight(null));
            return getView().getHeight() - vertical;
        }
        return 0;

    }

    protected int leftpaintbound() {
        if (getModel() != null & getView() != null) {
            int horizontal = (int) (((int) getModel().getRightBoundary() 
                    + getView().getWidth() * levelRightBoundary()) % getBackground().getWidth(null));
            return 0 - horizontal;
        }
        return 0;
    }

    protected int rightpaintbound() {
        if (getModel() != null & getView() != null) {
            int horizontal = (int) (((int) getModel().getRightBoundary() 
                    + getView().getWidth() * levelRightBoundary()) % getBackground().getWidth(null));
            return getView().getWidth() - horizontal;
        }
        return 0;
    }

    private double uniRightBoundary (Player p) {
        if (myRestrictiveDirection == Direction.RIGHT) {
            if (myMaxDirection > levelRightBoundary()) {
                return levelRightBoundary();
            }
            if (myMaxDirection - getView().getWidth() < levelLeftBoundary()) {
                return levelLeftBoundary() + getView().getWidth();
            }
            return myMaxDirection;
        }
        return levelRightBoundary();
    }

    private double uniLeftBoundary (Player p) {
        if (myRestrictiveDirection == Direction.LEFT) {

            if (myMaxDirection < levelLeftBoundary()) {
                return levelLeftBoundary();
            }
            if (myMaxDirection + getView().getWidth() > levelRightBoundary()) {
                return levelRightBoundary() - getView().getWidth();
            }
            return myMaxDirection;
        }
        return levelLeftBoundary();
    }

    private double uniUpperBoundary (Player p) {
        if (myRestrictiveDirection == Direction.TOP) {
            if (myMaxDirection < levelUpperBoundary()) {
                return levelUpperBoundary();
            }
            if (myMaxDirection + getView().getHeight() > levelLowerBoundary()) {
                return levelRightBoundary() - getView().getHeight();
            }
            return myMaxDirection;
        }
        return levelUpperBoundary();
    }

    private double uniLowerBoundary (Player p) { 
        if (myRestrictiveDirection == Direction.BOTTOM) {
            if (myMaxDirection > levelLowerBoundary()) {
                return levelLowerBoundary();
            }
            if (myMaxDirection - getView().getHeight() < levelUpperBoundary()) {
                return levelUpperBoundary() + getView().getHeight();
            }
            return myMaxDirection;
        }
        return levelLowerBoundary();
    }

    /**
     * Paints the View for the game, given the constraints of the ScrollingManger.
     * @param pen The Graphics object that will be doing the painting.
     */
    public void viewPaint(Graphics pen) {
        int leftpaintbound = leftpaintbound();
        int upperpaintbound = upperpaintbound();
        int rightpaintbound = rightpaintbound();
        int lowerpaintbound = lowerpaintbound();

        if (getModel().getLeftBoundary() < levelLeftBoundary()) {
            System.out.println("##1");
            leftpaintbound = (int) levelLeftBoundary();
            rightpaintbound = leftpaintbound + getBackground().getWidth(null);
        }

        if (getModel().getRightBoundary() > levelRightBoundary()) {
            System.out.println("##2");
            rightpaintbound = getView().getWidth() - ((int) levelRightBoundary()
                    % getModel().getBackground().getWidth(null));
            leftpaintbound =  rightpaintbound - getBackground().getWidth(null);

        }
        if (getModel().getLowerBoundary() > levelLowerBoundary()) {
            System.out.println("##3");
            lowerpaintbound = getView().getHeight()  - ((int) levelLowerBoundary() 
                    % getModel().getBackground().getHeight(null));
            upperpaintbound = lowerpaintbound - getBackground().getHeight(null);
        }
        if (getModel().getUpperBoundary() < levelUpperBoundary()) {
            System.out.println("##4");
            upperpaintbound = (int) levelUpperBoundary();
            lowerpaintbound = upperpaintbound + getBackground().getHeight(null);
        }

        checkScrollActivity(rightpaintbound, leftpaintbound, upperpaintbound, lowerpaintbound);
        scrollerDrawImage(pen);

    }

    private void scrollerDrawImage (Graphics pen) {
        Image img = getBackground();
        int imgwidth = img.getWidth(null);
        int imgheight = img.getHeight(null);
        
        System.out.println("Horizontal: " + myHorizontalScrollActive);
        System.out.println("Vertical: " + myVerticalScrollActive);
        pen.drawImage(img, myLeftPaintBound, myUpperPaintBound, imgwidth, imgheight, null);
        pen.drawImage(img, myRightPaintBound, myUpperPaintBound, imgwidth, imgheight, null);
        pen.drawImage(img, myLeftPaintBound, myLowerPaintBound, imgwidth, imgheight, null);
        pen.drawImage(img, myRightPaintBound, myLowerPaintBound, imgwidth, imgheight, null);
        getModel().paint((Graphics2D) pen);
    }


    private void checkScrollActivity (int rightpaintbound,
                                      int leftpaintbound,
                                      int upperpaintbound,
                                      int lowerpaintbound) {
        if (!myHorizontalScrollActive) {
            myLeftPaintBound = leftpaintbound;
            myRightPaintBound = rightpaintbound;
        }
        if (!myVerticalScrollActive) {
            myUpperPaintBound = upperpaintbound;
            myLowerPaintBound = lowerpaintbound;
        }        
    }


    @Override
    public double getHardBoundary (Direction d, double levelBounds) {
        if (d == myRestrictiveDirection) {
            return myMaxDirection;
        }
        return levelBounds;
    }

    @Override
    public Location playerPaintLocation (Player p) {
        updateLocation(p);
        double halfWidth = getView().getWidth() / 2;
        double halfHeight = getView().getHeight() / 2;
        double playerlocx = p.getX();
        double playerlocy = p.getY();
        double x =  playerlocx > (uniRightBoundary(p) - halfWidth)
                ? halfWidth + (halfWidth - (Math.abs(uniRightBoundary(p) - playerlocx))) : halfWidth;
        
        x = playerlocx < (uniLeftBoundary(p) + halfWidth)
                ? halfWidth - (halfWidth - (Math.abs(uniLeftBoundary(p) - playerlocx))) : halfWidth;
                
        double y = playerlocy > (uniLowerBoundary(p) - halfHeight) 
                ? halfHeight + (halfHeight - (Math.abs(uniLowerBoundary(p) - playerlocy))) 
                 : halfHeight;
                 
        y = playerlocy < (uniUpperBoundary(p) + halfHeight) 
                 ? halfHeight - (halfHeight - (Math.abs(uniUpperBoundary(p) - playerlocy)))
                 : halfHeight;
         
        myLastPlayerPaintLocation = new Location(x, y);
        switch (myRestrictiveDirection) {
            case RIGHT:
                myHorizontalScrollActive = (x > halfWidth) ? true : false;    
                break;
            case BOTTOM: 
                myVerticalScrollActive = (x > halfHeight) ? true : false;   
                break;
            case LEFT: 
                myHorizontalScrollActive = (x < halfHeight) ? true : false;  
                break;
            case TOP:  
                myVerticalScrollActive = (x < halfWidth)? true : false; 
                break;
            default:
                break;            
        }
        return myLastPlayerPaintLocation;
    }

    private void updateLocation (Player p) {
        double xCoord = p.getX();
        double yCoord = p.getY();
        int halfWidth = getView().getWidth() / 2;
        int halfHeight = getView().getHeight() / 2;

        switch (myRestrictiveDirection) {
            case RIGHT:
                myMaxDirection = ((xCoord + halfWidth) < myMaxDirection) 
                    ? (xCoord + halfWidth) : myMaxDirection;
                break;
            case BOTTOM: 
                myMaxDirection = ((yCoord + halfHeight)  < myMaxDirection) 
                    ? (yCoord + halfHeight) : myMaxDirection;
                break;
            case LEFT: 
                myMaxDirection = ((xCoord - halfWidth) > myMaxDirection) 
                    ? (xCoord - halfWidth) : myMaxDirection;
                break;
            case TOP: 
                myMaxDirection = ((yCoord - halfHeight) > myMaxDirection) 
                    ? (yCoord - halfHeight) : myMaxDirection;
                break;
            default:
                break;
        }
    }
}
