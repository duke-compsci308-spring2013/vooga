package vooga.towerdefense.gameeditor.gamemaker.editorscreens.mapeditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import vooga.towerdefense.gameeditor.controller.GameEditorController;
import vooga.towerdefense.gameeditor.gamemaker.editorscreens.GameEditorScreen;
import vooga.towerdefense.model.tiles.factories.TileFactory;
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
    private static final String NEXT_SCREEN_NAME = "PlayerEditorScreen";
    private static final String TITLE_NAME = "MAP ";
    private static final String TILE_IMAGES_CLASS_PATH = "vooga/towerdefense/images/map";
    private static final String TILE_PACKAGE_PATH = "vooga.towerdefense.model.tiles.factories";
    private static final Dimension TILE_PANEL_SIZE = new Dimension(500, 100);
    private static final String USER_DIR = "user.dir";
    private static final String DEFAULT_TILE_SIZE = "50";

    private MapMakerScreen myMapMakerBox;
    private Dimension myMapMakerSize;
    private JTextField myTileSizeTextField;
    private JTextField myMapNameTextField;
    private final int FIELD_SIZE = 10;
    private ActionListener myActionListener;
    private MouseAdapter myMouseListener;
    private int myTileSize;
    private TilePanel myTilePainter;
    private TileFactory myTileFactoryToBuild;
    private List<String> myBackgroundImages;
    private List<TileFactory> myTileFactories;
    private String myBackgroundImageName;
    private String myMapName;
    private JButton myBackgroundImageButton;
    private JFileChooser myChooser;
    private JButton myMapNameButton;
    private TileFactoryLoader myLoader;

    /**
     * Constructor.
     * 
     * @param size size of the map editor screen
     * @param controller the game editor controller
     */
    public MapEditorScreen (Dimension size, GameEditorController controller) {
        super(size, controller, TITLE_NAME, NEXT_SCREEN_NAME);
        makeListeners();
        initVariables();
        addComponentsToScreen();
        addMouseListener(myMouseListener);
        myBackgroundImageButton.addMouseListener(myMouseListener);
        myMapNameButton.addMouseListener(myMouseListener);
        setVisible(true);
    }

    /**
     * initialize variables used
     */
    private void initVariables () {
        myTileSize = 50;
        myMapMakerSize = getController().getMapSize();
        myBackgroundImages = new ArrayList<String>();
        myBackgroundImageButton = new JButton("BACKGROUND IMAGE");
        myMapNameButton = new JButton("ENTER NAME");
        myChooser = new JFileChooser(System.getProperties().getProperty(USER_DIR));
        myLoader = new TileFactoryLoader(getController());
    }

    /**
     * adds various components to screen and aligns them
     */
    private void addComponentsToScreen () {
        add(makeMapBox(myMapMakerSize), BorderLayout.CENTER);
        add(makeLabelText("TILE SIZE"), BorderLayout.WEST);
        add(makeTileTextField(), BorderLayout.WEST);
        add(myMapNameButton, BorderLayout.EAST);
        add(makeMapNameTextField(), BorderLayout.WEST);
        add(makePathTilePainter(), BorderLayout.SOUTH);
        add(myBackgroundImageButton, BorderLayout.SOUTH);
    }

    /**
     * Creates a text field for inputting the name of the game
     * 
     * @return JTextField
     */
    private JComponent makeMapNameTextField () {
        myMapNameTextField = new JTextField(FIELD_SIZE);
        myMapNameTextField.addActionListener(myActionListener);
        myMapNameTextField.setVisible(true);

        return myMapNameTextField;
    }

    /**
     * Creates a JPanel where the map is built
     * 
     * @param size size of the map
     * @return JPanel that the map is built on
     */
    private JPanel makeMapBox (Dimension size) {
        myMapMakerBox = new MapMakerScreen(size);
        return myMapMakerBox;
    }

    /**
     * Creates a JTextField for changing the tile size
     * 
     * @return text field area
     */
    private JTextField makeTileTextField () {
        myTileSizeTextField = new JTextField(FIELD_SIZE);
        myTileSizeTextField.setText(DEFAULT_TILE_SIZE);
        myTileSizeTextField.addActionListener(myActionListener);
        myTileSizeTextField.setVisible(true);

        return myTileSizeTextField;
    }

    private void makeListeners () {
        myActionListener = new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                if (e.getSource().equals(myMapNameTextField)) {
                    setMapName();
                }
                else if (e.getSource().equals(myTileSizeTextField)) {
                    resetTileSize();
                }
            }
        };
        myMouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                if (e.getSource().equals(myBackgroundImageButton)) {
                    int response = myChooser.showOpenDialog(null);
                    if (response == JFileChooser.APPROVE_OPTION) {
                        File file = myChooser.getSelectedFile();
                        String path = file.getName();
                        myBackgroundImageName = path;
                        myMapMakerBox.setBackgroundImage(myBackgroundImageName);
                    }
                }
                else if (e.getSource().equals(myMapNameButton)) {
                    setMapName();
                }
            }
        };
    }

    private void setMapName () {
        myMapName = myMapNameTextField.getText();
        if (myMapName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "NAME MUST BE AT LEAST ONE CHARACTER!");
        }
        else {
            myMapNameTextField.setText("");
        }
    }

    private JLabel makeLabelText (String text) {
        return new JLabel(text);
    }

    private void resetTileSize () {
        myTileSize = Integer.parseInt(myTileSizeTextField.getText());
        myMapMakerBox.setTileSizes(myTileSize);
        myTileSizeTextField.setText("");
    }

    /**
     * override the default Java Graphics paintComponent method
     */
    @Override
    public void paintComponent (Graphics pen) {
        super.paintComponent(pen);
    }

    /**
     * adds this map to the game.
     */
    @Override
    public void addElementToGame () {
        getController().addMapToGame(getMapName(), getBackgroundImagePath(),
                                     myMapMakerBox.getMapWidth(), myMapMakerBox.getMapHeight(),
                                     getTileSize(), myMapMakerBox.getMapString());
    }

    private JPanel makePathTilePainter () {
        File[] images = myLoader.getImages(TILE_IMAGES_CLASS_PATH);
        List<Pixmap> myImages = new ArrayList<Pixmap>();
        myImages = makeTileImages(images);
        myTileFactories = myLoader.initTileFactories(TILE_PACKAGE_PATH);
        myTilePainter = new TilePanel(TILE_PANEL_SIZE, myImages, this);
        return myTilePainter;
    }

    /**
     * Method called by the TilePanel with the type of the tile that the developer has
     * clicked on.
     * 
     * @param s string representing the tile that the developer clicked on.
     */
    public void makeTileInstances (String s) {

        for (TileFactory tile : myTileFactories) {
            if (tile.getName().equals(s)) {
                myTileFactoryToBuild = tile;
            }
        }
        myMapMakerBox.setTile(myTileFactoryToBuild);
    }

    private List<Pixmap> makeTileImages (File[] file) {
        List<Pixmap> images = new ArrayList<Pixmap>();
        for (File f : file) {
            images.add(new Pixmap("/" + TILE_IMAGES_CLASS_PATH + "/" + f.getName()));
            myBackgroundImages.add("/" + TILE_IMAGES_CLASS_PATH + "/" + f.getName());
        }
        return images;
    }

    @Override
    public void addAdditionalMouseBehavior (MouseEvent e) {
    }

    /**
     * get the name of the map
     * 
     * @return name of the map
     */
    public String getMapName () {
        return myMapName;
    }

    /**
     * get the path of the background image
     * 
     * @return string representing the path of the background image
     */
    public String getBackgroundImagePath () {
        return myMapMakerBox.getBackgroundImageName();
    }

    /**
     * 
     * @return the size of each tile in the map
     */
    public String getTileSize () {
        return Integer.toString(myTileSize);
    }
}
