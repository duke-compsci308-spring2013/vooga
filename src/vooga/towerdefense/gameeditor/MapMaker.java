package vooga.towerdefense.gameeditor;

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
 * This class enables map paths building on a tile by tile basis
 * 
 * @author Leonard K. Ng'eno
 * 
 */
public class MapMaker extends JPanel {

    private static final long serialVersionUID = 1L;
    private Dimension mySize;
    private int myTileSize;
    private MouseAdapter myMouseListener;
    List<Grid> myGrids;
    private boolean myPaintingMode;
    private Tile myTileToBuild;

    public MapMaker (Dimension size) {
        setSize(size);
        setPreferredSize(size);
        mySize = size;
        myTileSize = 50;
        myGrids = new ArrayList<Grid>();
        myTileToBuild =
                new GrassTile(1, new Pixmap("grass_tile.png"), new Location(0, 0),
                              new Dimension(50, 50));
        makeListener();
        addMouseListener(myMouseListener);
        myPaintingMode = false;
    }

    @Override
    public void paintComponent (Graphics pen) {
        super.paintComponent(pen);
        setBackground(Color.CYAN);
        paintGridLines(pen);
        paintGrid(pen);
    }

    private void paintGrid (Graphics pen) {
        for (Grid g : myGrids) {
            g.paint((Graphics2D) pen);
        }
    }

    public void setTileSizes (int size) {
        myTileSize = size;
        myTileToBuild =
                new GrassTile(1, new Pixmap("grass_tile.png"), new Location(0, 0),
                              new Dimension(50, 50));
        repaint();
    }

    private void paintGridLines (Graphics pen) {
        myGrids.removeAll(myGrids);
        if (myTileSize > 1) {
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
            if (tile.contains(point) && myPaintingMode == true) {
                resetPaintingMode();
                tile.setTile(myTileToBuild);
                paintGrid(getGraphics());
            }
        }
    }

    public void setPaintingMode (boolean mode) {
        myPaintingMode = mode;
    }

    private void resetPaintingMode () {
        setPaintingMode(false);
    }

    public void setTile (Tile t) {
        myTileToBuild = t;
    }
}
