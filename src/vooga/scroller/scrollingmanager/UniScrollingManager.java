
package vooga.scroller.scrollingmanager;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import util.Location;
import vooga.scroller.model.Model;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.view.View;
/**
 * This manager prevents players from scrolling in a directing but so far EX like Mario
 * @author Ross
 *
 */
public class UniScrollingManager extends ScrollingManager {
    private Model myGame;
    private View myView;
    private int myDirection = -1;
    private double myMaxDirection;
    private Location myLastPlayerPaintLocation;
    private int myLeftPaintBound;
    private int myUpperPaintBound;
    private int myRightPaintBound;
    private int myLowerPaintBound;
    private boolean myHorizontalScrollActive = false;
    private boolean myVerticalScrollActive = false;

    public UniScrollingManager(int restrictiondirection){
        if(restrictiondirection != 1 & restrictiondirection != 2 & restrictiondirection != 3 & restrictiondirection != 4) {
            myDirection = 3;
        }
        else {
            myDirection = restrictiondirection;
        }
        myLeftPaintBound = leftpaintbound();
        myUpperPaintBound = upperpaintbound();
        myRightPaintBound = rightpaintbound();
        myLowerPaintBound = lowerpaintbound();
    }

    public void initGame(Model game) {
        myGame = game;
    }

    public void initView(View view) {
        myView = view;
    }

    public int upperpaintbound() {
        if(myGame != null & myView != null) {
            int vertical = ((int) myGame.getLowerBoundary() + myView.getHeight()*1000) % myView.getHeight();
            return 0 - vertical;
        }
        return 0;
    }

    public int lowerpaintbound() { 
        if(myGame != null & myView != null) {
            int vertical = ((int) myGame.getLowerBoundary() + myView.getHeight()*1000) % myView.getHeight();
            return myView.getHeight() - vertical;
        }
        return 0;

    }

    public int leftpaintbound() {
        if(myGame != null & myView != null) {
            int horizontal = ((int) myGame.getRightBoundary() + myView.getWidth()*1000) % myView.getWidth();
            return 0 - horizontal;
        }
        return 0;
    }

    public int rightpaintbound() {
        if(myGame != null & myView != null) {
            int horizontal = ((int) myGame.getRightBoundary() + myView.getWidth()*1000) % myView.getWidth();
            return myView.getWidth() - horizontal;
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

    private double uniRightBoundary (Player p) {
        if(myDirection == 1){
            if(myMaxDirection > levelRightBoundary()) {
                return levelRightBoundary();
            }
            if(myMaxDirection - myView.getWidth() < levelLeftBoundary()) {
                return levelLeftBoundary() + myView.getWidth();
            }
            return myMaxDirection;
        }
        return levelRightBoundary();
    }

    private double uniLeftBoundary (Player p) {
        if(myDirection == 3){

            if(myMaxDirection < levelLeftBoundary()){
                return levelLeftBoundary();
            }
            if(myMaxDirection + myView.getWidth() > levelRightBoundary()) {
                return levelRightBoundary() - myView.getWidth();
            }
            return myMaxDirection;
        }
        return levelLeftBoundary();
    }

    private double uniUpperBoundary (Player p) {
        if(myDirection == 4){
            if(myMaxDirection < levelUpperBoundary()){
                return levelUpperBoundary();
            }
            if(myMaxDirection + myView.getHeight() > levelLowerBoundary()) {
                return levelRightBoundary() - myView.getHeight();
            }
            return myMaxDirection;
        }
        return levelUpperBoundary();
    }

    private double uniLowerBoundary (Player p) { 
        if(myDirection == 2){
            if(myMaxDirection > levelLowerBoundary()){
                return levelLowerBoundary();
            }
            if(myMaxDirection - myView.getHeight() < levelUpperBoundary()) {
                return levelUpperBoundary() + myView.getHeight();
            }
            return myMaxDirection;
        }
        return levelLowerBoundary();
    }

    public Image getBackground() {
        return myGame.getBackground();
    }

    public void viewPaint(Graphics pen) {
        Image img = getBackground();
        int imgwidth = img.getWidth(null);
        int imgheight = img.getHeight(null);

        int leftPaintBound = leftpaintbound();
        int upperPaintBound = upperpaintbound();
        int rightPaintBound = rightpaintbound();
        int lowerPaintBound = lowerpaintbound();

        if(myGame.getLeftBoundary() < levelLeftBoundary()) {
            leftPaintBound = (int) levelLeftBoundary();
            rightPaintBound = (int) levelRightBoundary();
        }

        if(myGame.getRightBoundary() > levelRightBoundary()) {
            leftPaintBound =  - ((int) levelRightBoundary() % myGame.getBackground().getWidth(null));
            rightPaintBound = myView.getWidth()  - ((int) levelRightBoundary() % myGame.getBackground().getWidth(null));

        }
        if(myGame.getLowerBoundary() > levelLowerBoundary()) {
            upperPaintBound = - ((int) levelLowerBoundary() % myGame.getBackground().getHeight(null));
            lowerPaintBound = myView.getHeight()  - ((int) levelLowerBoundary() % myGame.getBackground().getHeight(null));
        }
        if(myGame.getUpperBoundary() < levelUpperBoundary()) {
            upperPaintBound = (int) levelUpperBoundary();
            lowerPaintBound = (int) levelLowerBoundary();
        }

        if(!myHorizontalScrollActive) {
            myLeftPaintBound = leftPaintBound;
            myRightPaintBound = rightPaintBound;
        }
        if(!myVerticalScrollActive) {
            myUpperPaintBound = upperPaintBound;
            myLowerPaintBound = lowerPaintBound;
        }

        pen.drawImage(img, myLeftPaintBound, myUpperPaintBound, imgwidth, imgheight, null);
        pen.drawImage(img, myRightPaintBound, myUpperPaintBound, imgwidth, imgheight, null);
        pen.drawImage(img, myLeftPaintBound, myLowerPaintBound, imgwidth, imgheight, null);
        pen.drawImage(img, myRightPaintBound, myLowerPaintBound, imgwidth, imgheight, null);
        myGame.paint((Graphics2D) pen);
    }

    @Override
    public double getHardBoundary (int i, double levelBounds) {
        if(i == myDirection){
            return myMaxDirection;
        }
        return levelBounds;
    }

    public Location playerPaintLocation (Player p) {
        updateLocation(p);
        double halfWidth = myView.getWidth() / 2;
        double halfHeight = myView.getHeight() / 2;
        double x = halfWidth;
        double y = halfHeight;
        double playerlocx = p.getX();
        double playerlocy = p.getY();
        if(playerlocx > (uniRightBoundary(p) - halfWidth)) {
            x =  halfWidth + (halfWidth - (Math.abs(uniRightBoundary(p) - playerlocx)));
        }
        if(playerlocx < (uniLeftBoundary(p) + halfWidth)) {
            x =  halfWidth - (halfWidth - (Math.abs(uniLeftBoundary(p) - playerlocx)));
        }
        if(playerlocy > (uniLowerBoundary(p) - halfHeight)) {
            y =  halfHeight + (halfHeight - (Math.abs(uniLowerBoundary(p) - playerlocy)));
        }
        if(playerlocy < (uniUpperBoundary(p) + halfHeight)) {
            y =  halfHeight - (halfHeight - (Math.abs(uniUpperBoundary(p) - playerlocy)));
        }
        myLastPlayerPaintLocation = new Location(x, y);
        if(myDirection == 1){
            if(myLastPlayerPaintLocation.getX() > halfWidth){
                myHorizontalScrollActive = false;
            }
            myHorizontalScrollActive = true;            
        }
        if(myDirection == 2 ){
            if(myLastPlayerPaintLocation.getY() > halfHeight){
                myVerticalScrollActive = false;
            }
            myVerticalScrollActive = true;    
        }
        if(myDirection == 3){
            if(myLastPlayerPaintLocation.getX() < halfWidth){
                myHorizontalScrollActive = false;
            }
            myHorizontalScrollActive = true;    
        }
        if(myDirection == 4){
            if(myLastPlayerPaintLocation.getY() < halfHeight){
                myVerticalScrollActive = false;
            }
            myVerticalScrollActive = true;    
        }

        return myLastPlayerPaintLocation;

    }

    private void updateLocation (Player p) {
        double xCoord = p.getX();
        double yCoord = p.getY();
        int halfWidth = myView.getWidth() / 2;
        int halfHeight = myView.getHeight() / 2;



        if(myDirection == -1){
            if(myDirection == 1){
                myMaxDirection = (xCoord + halfWidth);

            }
            if(myDirection == 2 ){
                myMaxDirection = (yCoord + halfHeight);

            }
            if(myDirection == 3){
                myMaxDirection = (xCoord - halfWidth);

            }
            if(myDirection == 4){
                myMaxDirection = (yCoord - halfHeight);
            }
        }
        if(myDirection == 1){
            if((xCoord + halfWidth) < myMaxDirection){
                myMaxDirection = (xCoord + halfWidth);
            }
        }
        if(myDirection == 2){
            if((yCoord + halfHeight)  < myMaxDirection){
                myMaxDirection = (yCoord + halfHeight);
            }
        }
        if(myDirection == 3){
            if((xCoord - halfWidth) > myMaxDirection){
                myMaxDirection = (xCoord - halfWidth);
            }
        }
        if(myDirection == 4){
            if((yCoord - halfHeight) > myMaxDirection){
                myMaxDirection = (yCoord - halfHeight);
            }
        }
    }
}