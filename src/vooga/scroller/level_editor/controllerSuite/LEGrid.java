package vooga.scroller.level_editor.controllerSuite;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.Scrollable;
import util.Location;
import vooga.scroller.level_editor.LevelEditing;
import vooga.scroller.level_editor.StartPoint;
import vooga.scroller.level_editor.model.EditableGrid;
import vooga.scroller.level_editor.model.SpriteBox;
import vooga.scroller.level_editor.view.LEGridView;
import vooga.scroller.level_management.LevelPortal;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.util.IBackgroundView;
import vooga.scroller.util.Renderable;
import vooga.scroller.util.Renderer;
import vooga.scroller.util.mvc.IView;


/**
 * LEGrid is the grid that contains all of the SpriteBoxes as well as a StartPoint
 * and a Door. It is rendered by the LEGridView.
 * 
 * @author Danny Goodman, Deo Fagnisse
 * 
 */
public class LEGrid implements EditableGrid, Renderable<LevelEditing>, Scrollable {

    /**
     * Called by LevelEditor
     */
    public static final int DEFAULT_SPRITE_SIZE = 32;
    private static final Location DEFAULT_START_LOC = new Location(0, 0);
    private int mySpriteSize;
    private SpriteBox[][] myGrid;
    private Dimension mySize;
    private Set<SpriteBox> myPaintableBoxes;
    private StartPoint myStartPoint;
    private LevelPortal myDoor;
    private IBackgroundView myBackground;

    /**
     * Creates LEGrid from num of blocks
     * 
     * @param numWidthBlocks - width in blocks
     * @param numHeightBlocks - height in blocks
     */
    public LEGrid (int numWidthBlocks, int numHeightBlocks) {
        mySpriteSize = DEFAULT_SPRITE_SIZE;
        mySize = new Dimension(numWidthBlocks, numHeightBlocks);
        myGrid = new SpriteBox[numWidthBlocks][numHeightBlocks];
        initializeGrid();
        myPaintableBoxes = new HashSet<SpriteBox>();
    }

    @Override
    public void paint (Graphics2D pen) {
        if (myBackground != null) {
            pen.drawImage(myBackground.getImage(), 0, 0, null);
        }
        for (int i = 0; i < mySize.width; i++) {
            for (int j = 0; j < mySize.height; j++) {
                myGrid[i][j].paint(pen);
            }
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Renderer<LevelEditing> initializeRenderer (IView parent) {
        return new LEGridView(parent, this);
    }

    @Override
    public void addSprite (Sprite spr, int x, int y) {
        SpriteBox currentBox = getBox(x, y);
        addToBox(spr, currentBox);
    }

    @Override
    public void deleteSprite (int x, int y) {
        SpriteBox currentBox = getBox(x, y);
        currentBox.deleteSprite();
        myPaintableBoxes.remove(currentBox);
    }

    @Override
    public void changeBackground (IBackgroundView bg) {
        myBackground = bg;
    }

    @Override
    public void changeGridSize (int width, int height) {
        myGrid = createResizedGrid(width, height);
        mySize = new Dimension(width, height);
        checkStartPoint();
        checkDoor();
        checkPaintableBoxes();
    }

    @Override
    public Dimension getPreferredScrollableViewportSize () {
        return null;
    }

    @Override
    public int getScrollableUnitIncrement (Rectangle visibleRect, int orientation, int direction) {
        return mySpriteSize;
    }

    @Override
    public int getScrollableBlockIncrement (Rectangle visibleRect, int orientation, int direction) {
        return mySpriteSize;
    }

    @Override
    public boolean getScrollableTracksViewportWidth () {
        return false;
    }

    @Override
    public boolean getScrollableTracksViewportHeight () {
        return false;
    }

    @Override
    public void addStartPoint (int x, int y) {
        if (myStartPoint == null) {
            myStartPoint = new StartPoint();
        }
        else {
            deleteSprite((int) myStartPoint.getX(), (int) myStartPoint.getY());
        }
        addSprite(myStartPoint, x, y);
    }

    @Override
    public void addDoor (Sprite s, int x, int y) {
        if (myDoor != null) {
            deleteSprite((int) myDoor.getX(), (int) myDoor.getY());
        }
        myDoor = (LevelPortal) s;
        addSprite(myDoor, x, y);
    }

    /**
     * remove startpoint from grid. called by LevelWriter so that StartPoint
     * can be obtained and stored in the setting menu, not in the ascii map.
     * 
     * @return Location of StartPoint
     */
    public Location removeStartPoint () {
        if (myStartPoint == null) { return DEFAULT_START_LOC; }
        Location center = myStartPoint.getCenter();
        deleteSprite((int) center.getX(), (int) center.getY());
        return center;
    }

    /**
     * Save sprite as myDoor instance variable. add Sprite to SpriteBox.
     * 
     * @param xcoor - x coordinate of SpriteBox
     * @param ycoor - y coordinate of SpriteBox
     * @param sprite - Sprite to be added
     */
    public void addDoorWithCoor (int xcoor, int ycoor, Sprite sprite) {
        myDoor = (LevelPortal) sprite;
        addSpriteWithCoor(xcoor, ycoor, sprite);
    }

    /**
     * add sprite to box given the coordinates of the box.
     * 
     * @param xcoor - x coordinate of SpriteBox
     * @param ycoor - y coordinate of SpriteBox
     * @param sprite - Sprit eto be added
     */
    public void addSpriteWithCoor (int xcoor, int ycoor, Sprite sprite) {
        addToBox(sprite, getBoxFromCoor(xcoor, ycoor));
    }

    /**
     * returns set of SpriteBoxes to be painted i.e. those that contain Sprites
     * 
     * @return Set<SpriteBox>
     */
    public Set<SpriteBox> getBoxes () {
        return myPaintableBoxes;
    }

    /**
     * @param xcoor - x coordinate of SpriteBox
     * @param ycoor - y coordinate of SpriteBox
     * @return SpriteBox
     */
    public SpriteBox getBoxFromCoor (int xcoor, int ycoor) {
        return myGrid[xcoor][ycoor];
    }

    /**
     * @param xcoor - x coordinate of SpriteBox
     * @param ycoor - y coordinate of SpriteBox
     * @return Sprite
     */
    public Sprite getSpriteFromCoor (int xcoor, int ycoor) {
        return getBoxFromCoor(xcoor, ycoor).getSprite();
    }

    /**
     * @return myBackground
     */
    public IBackgroundView getBackground () {
        return myBackground;
    }

    /**
     * @return mySpriteSize - size of all SpriteBoxes.
     */
    public int getSpriteSize () {
        return mySpriteSize;
    }

    /**
     * @return mySize
     */
    public Dimension getSize () {
        return mySize;
    }

    /**
     * Get the overall pixel size of this LEGrid.
     * 
     * @return pixel size Dimension
     */
    public Dimension getPixelSize () {
        Dimension res = new Dimension(getWidth(), getHeight());
        return res;
    }

    /**
     * Checks StartPoint and Door for simulation
     * 
     * @return true if valid
     */
    public boolean isValidForSimulation () {
        return myStartPoint != null && myDoor != null;
    }

    /**
     * Checks Door and Background for saving
     * 
     * @return true if valid
     */
    public boolean isValidForSave () {
        return myDoor != null && myBackground != null;
    }

    /**
     * Saves Thumbnail view of level.
     * 
     * @param levelFilePath - String of filePath to save Level.png
     */
    public void saveThumbnail (String levelFilePath) {
        try {
            ImageIO.write(getThumbnail(), "PNG", new File(levelFilePath + ".png"));
        }
        catch (IOException e) {
            // Wont happen
            System.out.println("Image not saved");
        }
    }

    private SpriteBox getBox (double x, double y) {
        int xCoord = (int) Math.floor(x / mySpriteSize);
        int yCoord = (int) Math.floor(y / mySpriteSize);
        return myGrid[xCoord][yCoord];
    }

    private int getHeight () {
        return mySize.height * mySpriteSize;
    }

    private int getWidth () {
        return mySize.width * mySpriteSize;
    }

    /**
     * Recursive call to check if all SpriteBoxes that the Sprite overlaps are empty.
     * 
     * @param current
     * @param width
     * @param height
     * @return
     */
    private boolean checkAvailable (SpriteBox current, double width, double height) {
        if (!current.isAvailable()) { return false; }
        boolean bool1 = true;
        boolean bool2 = true;
        if (width > mySpriteSize) {
            SpriteBox next = getBox(current.getX() + mySpriteSize, current.getY());
            bool1 = checkAvailable(next, width - mySpriteSize, height);
        }
        if (height > mySpriteSize && bool1) {
            SpriteBox nextBox = getBox(current.getX(), current.getY() + mySpriteSize);
            bool2 = checkAvailable(nextBox, width, height - mySpriteSize);
        }
        return bool1 && bool2;
    }

    /**
     * Recursive call that combines all boxes that the Sprite Overlaps.
     * 
     * @param initial
     * @param current
     * @param width
     * @param height
     */
    private void combineBoxes (SpriteBox initial, SpriteBox current, double width, double height) {
        if (width > mySpriteSize) {
            SpriteBox next = getBox(current.getX() + mySpriteSize, current.getY());
            initial.combineWith(next);
            combineBoxes(initial, next, width - mySpriteSize, height);
        }
        if (height > mySpriteSize) {
            SpriteBox next = getBox(current.getX(), current.getY() + mySpriteSize);
            initial.combineWith(next);
            combineBoxes(initial, next, width, height - mySpriteSize);
        }
    }

    private void initializeGrid () {
        for (int x = 0; x < mySize.getWidth(); x++) {
            for (int y = 0; y < mySize.getHeight(); y++) {
                myGrid[x][y] = new SpriteBox(this, x, y);
            }
        }
    }

    /**
     * add Sprite to given Box. Chacks available, then adds Sprite and
     * makes paintable. Then combines boxes as needed.
     * 
     * @param spr
     * @param currentBox
     */
    private void addToBox (Sprite spr, SpriteBox currentBox) {
        if (checkAvailable(currentBox, spr.getWidth(), spr.getHeight())) {
            currentBox.addSprite(spr);
            myPaintableBoxes.add(currentBox);
            combineBoxes(currentBox, currentBox, spr.getWidth(), spr.getHeight());
        }
    }

    /**
     * Helper method for resizing the Grid.
     * 
     * @param width
     * @param height
     * @return
     */
    private SpriteBox[][] createResizedGrid (int width, int height) {
        SpriteBox[][] newGrid = new SpriteBox[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i < mySize.width && j < mySize.height) {
                    newGrid[i][j] = getBoxFromCoor(i, j);
                }
                else {
                    newGrid[i][j] = new SpriteBox(this, i, j);
                }
            }
        }
        return newGrid;
    }

    /**
     * Helper method for resizing the Grid.
     */
    private void checkStartPoint () {
        if (myStartPoint != null) {
            if (myStartPoint.getX() > getWidth() || myStartPoint.getY() > getHeight()) {
                myStartPoint = null;
            }
        }
    }

    /**
     * Helper method for resizing the Grid.
     */
    private void checkDoor () {
        if (myDoor != null) {
            if (myDoor.getX() > getWidth() || myDoor.getY() > getHeight()) {
                myDoor = null;
            }
        }

    }

    /**
     * Helper method for resizing the Grid.
     */
    private void checkPaintableBoxes () {
        for (SpriteBox box : myPaintableBoxes) {
            if (box.getX() > getWidth() || box.getY() > getHeight()) {
                myPaintableBoxes.remove(box);
            }
        }
    }

    private BufferedImage paintThumbnail (BufferedImage img) {
        Graphics2D drawer = img.createGraphics();
        paint(drawer);
        return img;
    }

    private BufferedImage getThumbnail () {
        BufferedImage res = new BufferedImage(getPixelSize().width,
                                              getPixelSize().height,
                                              BufferedImage.TYPE_INT_ARGB);
        return paintThumbnail(res);
    }

}
