package vooga.towerdefense.model;

import java.awt.Dimension;
import java.awt.Point;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.util.Location;
import vooga.towerdefense.util.Pixmap;
import vooga.towerdefense.util.Sprite;

public class Tile extends Sprite {
    public static final int TILE_SIZE = 50;
    public static final Pixmap GRASS_TILE_IMAGE = new Pixmap("map/grass_tile.png");
    public static final Pixmap PATH_TILE_IMAGE = new Pixmap("map/path_tile.png");
    public static final Dimension TILE_DIMENSIONS = new Dimension(TILE_SIZE, TILE_SIZE);
    
    private Point myCenter;
    private GameElement myElement;

    
    public Tile (Pixmap image, Location center, Dimension size) {
        super(image, center, size);
    }
    
    public static Pixmap getTileImage(int tileId) {
        if (tileId == 0) {
            return GRASS_TILE_IMAGE;
        } else if (tileId == 1) {
            return PATH_TILE_IMAGE;
        }
        return null;
    }
    
    public boolean containsElement() {
        return myElement != null;
    }
    
    public GameElement getElement() {
        return myElement;
    }

    public void setTower (GameElement t) {
        myElement = t;
    }

    public void deleteTower () {
        myElement = null;
    }
    
    public String toString() {
        return "(" + myCenter.getX() + ", " + myCenter.getY() + ")";
    }

}
