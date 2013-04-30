package vooga.towerdefense.model.tiles;

import java.awt.Dimension;
import util.Location;
import vooga.towerdefense.util.Pixmap;
import vooga.towerdefense.model.Tile;


/**
 * A tile that can be used to build new tiles from an XMLFile
 * 
 * @author Yoshida
 */
public class VersaTile extends Tile {
    
    private String myName;
    
    /**
     * The constructor of this tile creates a new tile from an unknown <code>Pixmap</code> and
     * walkable/buildable states.
     * Those states are determined by the XMLTileFactory.
     * 
     * @param id a tile id
     * @param image an image
     * @param center the center of the tile
     * @param size the size of the tile
     */
    public VersaTile(Pixmap image, Location center, Dimension size) {
        super(image, center, size);
        setBuildable(false);
        setWalkable(true);
    }
    
    /**
     * Sets the name of the tile.
     * 
     * @param name The name of the tile.
     */
    public void setName (String name) {
        myName = name;
    }
    
    
    /**
     * Sets if this tile is able to be walked on by units.
     * 
     * @param bool A boolean that changes the state of the walkable tile.
     */
    public void setTileWalkable (boolean bool) {
        this.setWalkable(bool);
    }
    
    /**
     * Sets if this tile is able to be walked on by units.
     * 
     * @param bool A boolean that changes the state of the buildable tile.
     */
    public void setTileBuildable (boolean bool) {
        this.setBuildable(bool);
    }
    
    
    /**
     * String name of the image used in the Pixmap of this tile
     */
    @Override
    public String getName () {
        return myName;
    }
}
