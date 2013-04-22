package vooga.towerdefense.gameeditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import vooga.towerdefense.util.Pixmap;

/**
 * MapEditorScreen is responsible for helping
 *      the game developer make maps.
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
    
    private MapMaker myMapBox;
    private Dimension myMapMakerSize;
    private JTextField myTextField;
    private final int FIELD_SIZE = 10;
    private ActionListener myActionListener;
    private MouseAdapter myTilePainterListener;
    private MouseAdapter myTileEraserListener;
    private int myTileSize;
    private TilesScreen myTilePainter;
    private JPanel myPathEraser;
    private Pixmap myGrassTile;
    private Pixmap myPathTile;
    
    /**
     * Constructor.
     * @param size
     * @param controller
     */
    public MapEditorScreen(Dimension size, GameEditorController controller) {
        super(size, controller, TITLE_NAME, NEXT_SCREEN_NAME);
        makeListeners();
        myMapMakerSize = getController().getMapSize();
        this.add(makeMapBox(myMapMakerSize), BorderLayout.NORTH);
        this.add(makeLabelText("TILE SIZE"), BorderLayout.EAST);
        this.add(makeTextField(), BorderLayout.EAST);
        this.add(makeLabelText("MAP TILES"), BorderLayout.SOUTH);
        this.add(makePathTilePainter(), BorderLayout.SOUTH);
//        this.add(makeLabelText("PATH ERASER"), BorderLayout.EAST);
//        this.add(makePathEraser(), BorderLayout.WEST);

        setVisible(true);
    }

    private JPanel makeMapBox (Dimension size) {
        myMapBox = new MapMaker(size, this);        
        return myMapBox;
    }
    
    private JTextField makeTextField () {

        myTextField = new JTextField(FIELD_SIZE);
        myTextField.addActionListener(myActionListener);
        myTextField.setVisible(true);

        return myTextField;
    }
    
    public void makeListeners () {
        myActionListener = new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                repaintGrids();
            }
        };
        
        myTilePainterListener = new MouseAdapter () {
            @Override
            public void mouseClicked (MouseEvent e) {
                setPainterColor(myTilePainter, Color.GREEN);
                myMapBox.setPaintingMode(true);
            }
        };
        
        myTileEraserListener = new MouseAdapter () {
            @Override
            public void mouseClicked (MouseEvent e) { 
                setPainterColor(myPathEraser, Color.GREEN);
                myMapBox.setEraseMode(true);
            }
        };
    }
    
    private JLabel makeLabelText (String text) {
        return new JLabel(text);
    }
    
    private void repaintGrids(){
        myTileSize = Integer.parseInt(myTextField.getText()); // TODO make sure myTileSize is an int!! or appropriate int!!
        myMapBox.setTileSizes(myTileSize);
    }
    
    @Override
    public void paintComponent(Graphics pen) {
        super.paintComponent(pen);
    }

    /**
     * adds this map to the game.
     */
    public void addElementToGame () {
        getController().addMapToGame();
    }
    
    private JPanel makePathTilePainter () {
        File[] images = getImages(TILE_IMAGES_CLASS_PATH);
        List<Pixmap> myImages = new ArrayList<Pixmap>();
        myImages = makeTileImages(images);

        myTilePainter = new TilesScreen(new Dimension(200,70), myImages);

        return myTilePainter;
    }
    
    private JPanel makePathEraser () {
        myPathEraser = new JPanel ();
        myPathEraser.setPreferredSize(new Dimension(50,50));
        myPathEraser.setBackground(Color.RED);
        myPathEraser.setVisible(true);
        myPathEraser.addMouseListener(myTileEraserListener);
        
        return myPathEraser;
    }
    
    private List<Pixmap> makeTileImages(File[] file)  {
        List<Pixmap> images = new ArrayList<Pixmap>();
        for (File f: file) {
            System.out.println("String: " + f.getName());
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
            for (File file : files) {
                System.out.println("file: " + file);
            }
            return files;
        }
        return null;
    }
    
    public void setPainterColor (JPanel panel, Color color) {
        panel.setBackground(color);
    }
    
    public JPanel getTilePainter() {
        return myTilePainter;
    }
    
    public JPanel getPathEraser () {
        return myPathEraser;
    }

    @Override
    public void addAdditionalMouseBehavior (MouseEvent e) {
        // TODO Auto-generated method stub
    }
    
}
