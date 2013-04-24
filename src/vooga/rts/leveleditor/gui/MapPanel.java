package vooga.rts.leveleditor.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import util.input.Input;
import util.input.InputClassTarget;
import util.input.InputMethodTarget;
import util.input.PositionObject;
import vooga.rts.leveleditor.components.EditableMap;
import vooga.rts.leveleditor.components.EditableTerrain;
import vooga.rts.leveleditor.components.EditableTile;
import vooga.rts.leveleditor.components.EditableResource;
import vooga.rts.resourcemanager.ResourceManager;
import vooga.rts.util.Camera;
import vooga.rts.util.Location3D;


@InputClassTarget
public class MapPanel extends JComponent {

    public static final String INPUT_DIR = "vooga.rts.resources.properties.Input";
    public static final String PLAYER_IMAGE_PATH = "Player1.png";
    public static final Dimension DEFAULT_MAP_SIZE = new Dimension(600, 600);
    public static final int RESOURCEMODE = 1;
    public static final int PLAYERMODE = 2;
    public static final int TERRAINMODE = 3;
    public static final int TILEMODE = 4;
    public static final int FILLMODE = 5;

    private Canvas myCanvas;
    private EditableMap myMap;
    private Input myInput;
    private int myWidth;
    private int myHeight;
    private int myTileWidth;
    private int myTileHeight;
    private int myCurrentLayer;
    private int myMaxLayer;
    private boolean myRemoveFlag;
    private int myMode;
    private BufferedImage myPlayerImage;
    

    public MapPanel (Canvas canvas) {
        myCanvas = canvas;
        myMap = new EditableMap();
        myInput = new Input(INPUT_DIR, this);
        myInput.addListenerTo(this);
        myWidth = 0;
        myHeight = 0;
        myCurrentLayer = 0;
        myMaxLayer = 0;
        myTileWidth = 0;
        myTileHeight = 0;
        myPlayerImage =
                ResourceManager.getInstance().<BufferedImage> getFile(PLAYER_IMAGE_PATH,
                                                                      BufferedImage.class);
        setPanelSize();
        
    }

    private void setPanelSize () {
        Dimension d = new Dimension(myTileWidth * myWidth, myTileHeight * myHeight);
        setPreferredSize(d);
        Camera.instance().setMapSize(d);
        Camera.instance().setViewSize(d);
        Camera.instance().setWorldLocation(new Location3D((myTileWidth * myWidth)/2, (myTileHeight * myHeight)/2,0));
    }

    @Override
    public void paintComponent (Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 2*myWidth * myTileWidth, myHeight * myTileHeight);
        Camera.instance().paint((Graphics2D)g);
        g.setColor(Color.gray);
        for (int i = 0; i < myWidth; i++) {
            drawLineTest(i * myTileWidth / 2, 0, i * myTileWidth / 2, myHeight * myTileHeight/2,g);
        }

        for (int j = 0; j < myHeight; j++) {
            drawLineTest(0, j * myTileHeight /2  , myWidth * myTileWidth / 2, j * myTileHeight /2,g);
        }

        ((Graphics2D) g).setStroke(new BasicStroke(2));
        g.setColor(Color.black);
        
        drawLineTest(0, 0, myWidth * myTileWidth/2, 0, g);
        drawLineTest(0, 0, 0, myHeight * myTileHeight/2,g);
        drawLineTest(myWidth * myTileWidth/2, 0, myWidth * myTileWidth/2, myHeight * myTileHeight/2,g);
        drawLineTest(0, myHeight * myTileHeight/2, myWidth * myTileWidth/2, myHeight * myTileHeight/2,g);

        // paint map
        myMap.paint((Graphics2D)g);
    }
    
    public void drawLineTest(int x1, int y1, int x2, int y2, Graphics g) {
        Point p1 = new Point(x1,y1);
        Point2D loc1 = Camera.instance().worldToView(new Location3D(p1));
        Point p2 = new Point(x2,y2);
        Point2D loc2 = Camera.instance().worldToView(new Location3D(p2));
        g.drawLine((int)loc1.getX(), (int)loc1.getY(), (int)loc2.getX(), (int)loc2.getY());
    }

    public void setWidth (int w) {
        myWidth = w;
        setPanelSize();
        repaint();
    }

    public void setHeight (int h) {
        myHeight = h;
        setPanelSize();
        repaint();
    }

    public void setTileWidth (int w) {
        myTileWidth = w;
        setPanelSize();
        repaint();
    }

    public void setTileHeight (int h) {
        myTileHeight = h;
        setPanelSize();
        repaint();
    }

    public void initializeMap (String name, String desc, int width, int height, int tileWidth, int tileHeight) {
        myMap = new EditableMap(name,desc,width, height, tileWidth, tileHeight);
    }

    public void placeResource (Location3D loc) {
        EditableResource r = myCanvas.getCurrentSelectResource();
        myMap.addResource(r.getImage(), loc, r.getMyID(), r.getType(), r.getMyImageName(), r.getMyAmount());
        repaint();
    }

    public void placeTerrain (Location3D loc) {
        EditableTerrain t = myCanvas.getCurrentSelectTerrain();
        myMap.addTile(loc, t.getMyID(), t.getMyName(), t.getMyImageName(), t.getImage());
        repaint();
    }


    private void placeTile (Location3D loc) {
        EditableTile t = myCanvas.getCurrentSelectTile();
        myMap.addTile(loc, t.getMyID(), t.getMyName(), t.getMyImageName(), t.getImage());
        repaint();
    }

//    public void placePlayer (int x, int y) {
//        int nodex = x / myTileWidth;
//        int nodey = y / myTileHeight;
//        EditableTile n = myMap.getMapNode(nodex, nodey);
//        if (!myRemoveFlag) {
//            myMap.addPlayer(x, y);
//            n.setPlayerIndex(myMap.getMyPlayerNumber());
//        }
//        else {
//            myMap.removePlayer(n.getPlayerIndex());
//        }
//        repaint();
//    }
    
    public void fill() {
        myMode = FILLMODE;       
    }
    
    public void fillTiles() {
        EditableTile t = myCanvas.getCurrentSelectTile();
        for(int i = 0; i<myMap.getMyXsize(); ++i) {
            for(int j = 0; j<myMap.getMyYsize(); ++j) {
                myMap.addTile(i,j, t.getMyID(), t.getMyName(), t.getMyImageName(), t.getImage());
            }
        }
        repaint();        
    }
    
    public void clear () {
        myCurrentLayer = 0;
        myMaxLayer = 0;
        myMap.clearMap();
        repaint();
    }

    public void setRemoveFlag (boolean b) {
        myRemoveFlag = b;
    }

    public EditableMap getMyMap () {
        return myMap;
    }

    public void setMode (int mode) {
        myMode = mode;
    }

    public void setCurrentLayer (int n) {
        myCurrentLayer = n;

    }

    public int getCurrentLayer () {
        return myCurrentLayer;
    }

    public int getMaxLayer () {
        return myMaxLayer;
    }

//    public void addLayer () {
//        myMaxLayer++;
//        myMap.getLayerMap().put(myMaxLayer, new MapLayer());
//    }
//
//    public void removeLayer () {
//        myMap.getLayerMap().remove(myMaxLayer);
//        myMaxLayer--;
//    }

    @InputMethodTarget(name = "onLeftMouseDown")
    public void testClick (PositionObject p) {
        Location3D loc = Camera.instance().viewtoWorld(p.getPoint2D());
        switch (myMode) {
            case RESOURCEMODE:
                placeResource(loc);
                break;
//            case PLAYERMODE:
//                placePlayer((int) (p.getX()), (int) (p.getY()));
//                break;
            case TERRAINMODE:
                placeTerrain(loc);
                break;
            case TILEMODE:
                placeTile(loc);
                break;
            case FILLMODE:
                fillTiles();
                break;    
            default:
                break;
        }
    }

    @InputMethodTarget(name = "onMouseDrag")
    public void testDrag (PositionObject p) {
        Location3D loc = Camera.instance().viewtoWorld(p.getPoint2D());
        if (myMode == TILEMODE) {
            placeTile(loc);
        }
    }


}
