package vooga.scroller.level_editor.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Set;
import vooga.scroller.level_editor.controllerSuite.LEGrid;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.view.IPaintable;


/**
 * SpriteBox is a container within the Grid that can carry a Sprite, combine
 * with other boxes to hold large sprites, and paint itself.
 * 
 * @author Danny Goodman
 */
public class SpriteBox implements IPaintable {

    private boolean myAvailable;
    private Rectangle myBounds;
    private Set<SpriteBox> myCombinedBoxes;
    private LEGrid myParent;
    private int mySize;
    private Sprite mySprite;

    /**
     * Initialize instance variables.
     * 
     * @param parent - LEGrid that holds this SpriteBox
     * @param x - x Coordinate
     * @param y - y Coordinate
     */
    public SpriteBox (LEGrid parent, int x, int y) {
        myParent = parent;
        mySize = myParent.getSpriteSize();
        setBounds(x, y);
        setAvailable();
        myCombinedBoxes = new HashSet<SpriteBox>();
    }

    /**
     * Adds a Sprite to the Box. Sets the position of the sprite to the
     * center of the box. Sets SpriteBox unavailable.
     * 
     * @param spr - sprite to add
     */
    public void addSprite (Sprite spr) {
        mySprite = spr;
        mySprite.setCenter(myBounds.getX() + mySprite.getWidth() / 2,
                           myBounds.getY() + mySprite.getHeight() / 2);
        setUnavailable();
    }

    /**
     * Combine with another SpriteBox. adds that SpriteBox to the Set.
     * 
     * @param nearestBox - box to combine with
     */
    public void combineWith (SpriteBox nearestBox) {
        myCombinedBoxes.add(nearestBox);
        nearestBox.myCombinedBoxes.add(this);
        nearestBox.setUnavailable();
    }

    /**
     * Delete the sprite within this box. Set Available. Remove Combining boxes.
     */
    public void deleteSprite () {
        mySprite = null;
        setAvailable();
        for (SpriteBox box : myCombinedBoxes) {
            box.myCombinedBoxes.remove(this);
            box.deleteSprite();
        }
        myCombinedBoxes = new HashSet<SpriteBox>();
    }

    /**
     * Gets the Sprite in this Box
     * 
     * @return mySprite
     */
    public Sprite getSprite () {
        return mySprite;
    }

    /**
     * gets the X pixel coordinate of the top left corner.
     * 
     * @return x
     */
    public int getX () {
        return myBounds.x;
    }

    /**
     * gets the y pixel coordinate of the top left corner
     * 
     * @return y
     */
    public int getY () {
        return myBounds.y;
    }

    /**
     * returns whether a sprite can be added to the box.
     * 
     * @return true if available.
     */
    public boolean isAvailable () {
        return myAvailable;
    }

    @Override
    public void paint (Graphics2D pen) {
        if (mySprite != null) {
            mySprite.paint(pen);
        }
        pen.setColor(Color.BLACK);
        pen.drawRect(getX(), getY(), myBounds.width, myBounds.height);
    }

    private void setAvailable () {
        myAvailable = true;
    }

    private void setBounds (int x, int y) {
        myBounds = new Rectangle(x * mySize, y * mySize, mySize, mySize);
    }

    private void setUnavailable () {
        myAvailable = false;
    }

}
