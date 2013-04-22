package vooga.towerdefense.gameeditor.mapeditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import vooga.towerdefense.gameeditor.GameEditorController;
import vooga.towerdefense.gameeditor.GameEditorScreen;
import vooga.towerdefense.model.tiles.GrassTile;
import vooga.towerdefense.model.tiles.PathTile;
import vooga.towerdefense.model.tiles.Tile;
import vooga.towerdefense.util.Location;
import vooga.towerdefense.util.Pixmap;


/**
 * The MapEditorScreen is responsible for helping
 * the game developer make maps. It contains three components: a JTextArea where
 * the game developer specifies the size of each tile; a panel with the different
 * types of tiles that the developer can drag into the map area; and a panel where
 * the developer actually places their map on.
 * 
 * @author Angelica Schwartz
 * @author Leonard K. Ng'eno
 */
public class MapEditorScreen extends GameEditorScreen {

    private static final long serialVersionUID = 1L;
    public static final String CLASS_INDICATOR_STRING = ".png";
    private static final String NEXT_SCREEN_NAME = "ProjectileEditorScreen";
    private static final String TITLE_NAME = "MAP ";
    private static final String TILE_IMAGES_CLASS_PATH = "vooga.towerdefense.images.map";
    private static final String GRASS_TILE_NAME = "grass_tile.png";
    private static final String PATH_TILE_NAME = "path_tile.png";
    private static final Location DEFAULT_LOCATION = new Location(0,0);
    private static final Dimension DEFAULT_SIZE = new Dimension(50,50);
    private static final Dimension TILE_PANEL_SIZE = new Dimension (200, 70);
    private static final Pixmap GRASS_PIXMAP = new Pixmap(GRASS_TILE_NAME);
    private static final Pixmap PATH_PIXMAP = new Pixmap (PATH_TILE_NAME);
    
    private MapMakerScreen myMapMakerBox;
    private Dimension myMapMakerSize;
    private JTextField myTextField;
    private final int FIELD_SIZE = 10;
    private ActionListener myActionListener;
    private MouseAdapter myTileBuilderListener;
    private int myTileSize;
    private TilePanel myTilePainter;
    private Tile myTileToBuild;

    /**
     * Constructor.
     * 
     * @param size
     * @param controller
     */
    public MapEditorScreen (Dimension size, GameEditorController controller) {
        super(size, controller, TITLE_NAME, NEXT_SCREEN_NAME);
        makeListeners();
        myMapMakerSize = getController().getMapSize();
        add(makeMapBox(myMapMakerSize), BorderLayout.NORTH);
        add(makeLabelText("TILE SIZE"), BorderLayout.EAST);
        add(makeTextField(), BorderLayout.EAST);
        add(makeLabelText("MAP TILES"), BorderLayout.SOUTH);
        add(makePathTilePainter(), BorderLayout.SOUTH);
        addMouseListener(myTileBuilderListener);

        setVisible(true);
    }

    private JPanel makeMapBox (Dimension size) {
        myMapMakerBox = new MapMakerScreen(size);
        return myMapMakerBox;
    }

    private JTextField makeTextField () {
        myTextField = new JTextField(FIELD_SIZE);
        myTextField.addActionListener(myActionListener);
        myTextField.setVisible(true);

        return myTextField;
    }

    private void makeListeners () {
        myActionListener = new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                repaintGrids();
            }
        };
    }

    private JLabel makeLabelText (String text) {
        return new JLabel(text);
    }

    private void repaintGrids () {
        myTileSize = Integer.parseInt(myTextField.getText());
        myMapMakerBox.setTileSizes(myTileSize);
        myTextField.setText("");
    }

    @Override
    public void paintComponent (Graphics pen) {
        super.paintComponent(pen);
    }

    /**
     * adds this level to the game.
     */
    @Override
    public void addElementToGame () {
       // getController().addMapToGame(int TileSize, Map<String, String> tileInfo);
       // Element mapParent = makeElement ("Map");
       // myXMLDoc.addChildElement(myRoot, myParent);
       // Element tileInfo = myXMLDoc.addElementFromMap ("TitleInfo", tileInfo)
       // myXMLDoc.addChildElement(mapParent, tileInfo)
        
    }

    private JPanel makePathTilePainter () {
        File[] images = getImages(TILE_IMAGES_CLASS_PATH);
        List<Pixmap> myImages = new ArrayList<Pixmap>();
        myImages = makeTileImages(images);
        myTilePainter = new TilePanel(TILE_PANEL_SIZE, myImages, this);
        return myTilePainter;
    }

    /**
     * Method called by the TilePanel with the type of the tile that the developer has 
     * clicked on.
     * 
     * @param s string representing the tile that the developer clicked on.
     */
    // TODO Fix this so that the tiles are not hard-coded!
    public void makeTileInstances (String s) {
        if (s.equals(GRASS_TILE_NAME)) {
            myTileToBuild = new GrassTile(0, GRASS_PIXMAP, DEFAULT_LOCATION, DEFAULT_SIZE);
        }
        else if (s.equals(PATH_TILE_NAME)) {
            myTileToBuild = new PathTile(1, PATH_PIXMAP, DEFAULT_LOCATION, DEFAULT_SIZE);
        }
        myMapMakerBox.setTile(myTileToBuild);
    }

    private List<Pixmap> makeTileImages (File[] file) {
        List<Pixmap> images = new ArrayList<Pixmap>();
        for (File f : file) {
            images.add(new Pixmap(f.getName()));
        }
        return images;
    }

    private File[] getImages (String packageName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace(".", "/");
        URL resource = classLoader.getResource(path);
        File directory = new File(resource.getFile());
        if (directory.exists()) {
            File[] files = directory.listFiles();
            return files;
        }
        return null;
    }

    @Override
    public void addAdditionalMouseBehavior (MouseEvent e) {
        // TODO Auto-generated method stub
    }

    /**
     * 
     * @return  a mapping of the tile's position and the tile's id as strings
     */
    public Map<String, String> getMapRepresentation () {
        return myMapMakerBox.getMapRepresentation();
    }
    
    /**
     * 
     * @return  the size of each tile in the map
     */
    public int getTileSize () {
        return myTileSize;
    }
}
