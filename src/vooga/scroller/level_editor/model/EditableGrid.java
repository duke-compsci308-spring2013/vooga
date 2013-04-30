package vooga.scroller.level_editor.model;

import vooga.scroller.sprites.Sprite;
import vooga.scroller.util.IBackgroundView;


/**
 * Interface specifies an object that can be edited by the LevelEditor. Implemented by Level.
 * LevelEditor will only see those methods pertaining to editing the Level, not those necessary
 * to run the Level.
 * 
 * @author Danny Goodman
 * 
 */
public interface EditableGrid {

    /**
     * Change Background to given background
     * 
     * @param iBackgroundView - background to change to
     */
    public void changeBackground (IBackgroundView iBackgroundView);

    /**
     * Adds a sprite to the specified location if available.
     * 
     * @param sprite - sprite to be added
     * @param x - pixel location, x component to add sprite
     * @param y - pixel location, y component to add sprite
     */
    public void addSprite (Sprite sprite, int x, int y);

    /**
     * Deletes the Sprite at the specified location if there is a sprite there.
     * 
     * @param x - pixel location, x component
     * @param y - pixel location, y component
     */
    public void deleteSprite (int x, int y);

    /**
     * sets the StartPoint Location to the specified Location
     * 
     * @param x - pixel location, x component
     * @param y - pixel location, y component
     */
    public void addStartPoint (int x, int y);

    /**
     * Adds a door sprite to the specified location
     * 
     * @param sprite - door to be added
     * @param x - pixel location, x component
     * @param y - pixel location, y component
     */
    public void addDoor (Sprite sprite, int x, int y);

    /**
     * Changes the size of the grid.
     * 
     * @param width - width in number of Blocks
     * @param height - height in number of Blocks
     */
    public void changeGridSize (int width, int height);

}
