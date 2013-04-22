package vooga.towerdefense.gameeditor.mapeditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import vooga.towerdefense.model.tiles.GrassTile;
import vooga.towerdefense.model.tiles.Tile;
import vooga.towerdefense.util.Location;
import vooga.towerdefense.util.Pixmap;


/**
 * This is the screen where the game maker places the tiles in order to make
 * a map. The tiles are placed one at a time on a grid contained in the screen.
 *   
 * @author Leonard K. Ng'eno
 * 
 */
public class MapMakerScreen extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final String DEFAULT_TILE_STRING = "grass_tile.png";
    private static final Pixmap DEFAULT_PIXMAP = new Pixmap(DEFAULT_TILE_STRING);
    private static final Dimension DEFAULT_TILE_SIZE = new Dimension(50, 50);
    private static final Location DEFAULT_LOCATION = new Location (0,0);
    private static final GrassTile DEFAULT_TILE = new GrassTile(1, DEFAULT_PIXMAP, DEFAULT_LOCATION, DEFAULT_TILE_SIZE);
    private static final int MINIMUM_TILE_SIZE = 10;
    private Dimension mySize;
    private int myTileSize;
    private MouseAdapter myMouseListener;
    private List<Grid> myGrids;
    private Tile myTileToBuild;

    public MapMakerScreen (Dimension size) {
        setSize(size);
        setPreferredSize(size);
        mySize = size;
        myTileSize = 50;
        myGrids = new ArrayList<Grid>();
        myTileToBuild = DEFAULT_TILE;
                
        makeListener();
        addMouseListener(myMouseListener);
    }

    @Override
    public void paintComponent (Graphics pen) {
        super.paintComponent(pen);
        setBackground(Color.CYAN);
        paintGridLines(pen);
        paintTilesOnGrid(pen);
    }

    private void paintTilesOnGrid (Graphics pen) {
        for (Grid g : myGrids) {
            g.paint((Graphics2D) pen);
        }
    }

    /**
     * This method resets the sizes of the tiles. This also results in the paths the
     * game maker had build being reset.
     * 
     * @param size      The size of each tile in pixels
     */
    public void setTileSizes (int size) {
        myTileSize = size;
        myTileToBuild = DEFAULT_TILE;              
        repaint();
    }

    private void paintGridLines (Graphics pen) {
        if (myTileSize > MINIMUM_TILE_SIZE) {
            myGrids.removeAll(myGrids);
            for (int i = 0; i < mySize.width; i += myTileSize) {
                for (int j = 0; j < mySize.height; j += myTileSize) {
                    pen.drawLine(i, 0, i, mySize.height);
                    pen.drawLine(0, j, mySize.width, j);
                    Grid rect = new Grid(i, j, myTileSize, myTileSize, myTileToBuild);
                    myGrids.add(rect);
                }
            }
        }

    }

    private void makeListener () {
        myMouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                setTileClicked(e.getPoint());
            }
        };
    }

    private void setTileClicked (Point point) {
        for (Grid tile : myGrids) {
            if (tile.contains(point)) {
                tile.setTile(myTileToBuild);
                paintTilesOnGrid(getGraphics());
            }
        }
    }

    /**
     * Sets the tile to build to be tile t.
     * 
     * @param t  The tile to be fixed on the grid that the game maker clicks on
     */
    public void setTile (Tile t) {
        myTileToBuild = t;
    }
}
