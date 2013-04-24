package vooga.rts.leveleditor.components;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import vooga.rts.map.GameMap;
import vooga.rts.state.GameState;
import vooga.rts.util.Location;
import vooga.rts.util.Location3D;
import vooga.rts.util.Pixmap;


/**
 * a class of EditableMap, this editable map is used to generate a
 * map that can be used for game players. This class enables them to
 * generated their own map
 * 
 * @author Richard Yang
 * 
 */

public class EditableMap extends GameMap {


    private static final int DEFAULT_PLAYER_UPPER_LIMIT = 8;
    private static final int DEFAULT_TEAM_UPPER_LIMIT = 4;
    
    private String myMapName = "CIEMAS";
    private String myDescription = " our RTS is the best one !";

    private PlayerSet myPlayerSet;

    private MapSaver mySaver;
    private MapLoader myLoader;
    
    private EditableTileMap myTileMap;

    public EditableMap (String name, String desc, int xSize, int ySize, int tileWidth, int tileHeight) {
        super(new Dimension(tileWidth,tileHeight),xSize,ySize);
        myTileMap = new EditableTileMap(new Dimension(tileWidth,tileHeight),xSize,ySize);
        myMapName = name;
        myDescription =desc;
        GameState.setMap(this);
        initializeMap();
        setTileMap(myTileMap);
    }

    public EditableMap () {
        this("", "", 0, 0, 0, 0);
    }

    public void initializeMap () {
        myTileMap.initialize();
        myPlayerSet = new PlayerSet(DEFAULT_PLAYER_UPPER_LIMIT,DEFAULT_TEAM_UPPER_LIMIT);

        try {
            mySaver = new MapSaver(this);
            //myLoader = new MapLoader(this);
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void resetTileMap(int xSize, int ySize ,int tileWidth, int tileHeight) {
        myTileMap = new EditableTileMap(new Dimension(tileWidth,tileHeight),ySize,xSize);
    }


    public void clearMap () {
        myTileMap.removeAllTiles();
        
    }

    public void printMatrix () {
        System.out.println("printmatrix executed");
        System.out.println("X Size : " + getMyXsize());
        System.out.println("Y Size : " + getMyYsize());
        
        for(int i =0 ; i<getMyXsize() ; i++) {
            for(int j =0 ; j<getMyYsize() ; j++) {
                System.out.print(myTileMap.getTile(i, j).getMyID());
                System.out.print(" ");
            }
            System.out.print("\n");
        }
        for (int i = 0; i < 10; i++) {
            System.out.print("**");
        }
        System.out.print("\n");
    }

    public void load (File resourceFile) {

        try {
            System.out.println("LOAD MAP IN THE FILE");
            myLoader.loadMapFile(resourceFile);

        }
        catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void load (String filePath) {
        File bufferFile = new File(filePath);
        load(bufferFile);
    }

    public void save (File objectiveFile) {
        try {
            mySaver.generateMapFile(objectiveFile);
        }
        catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void save (String filePath) {
        File bufferFile = new File(filePath);
        save(bufferFile);
    }
    
    public void addPlayer(Location location) {
        myPlayerSet.addPlayer(location);
    }
    
    public void addPlayer(int x , int y) {
        myPlayerSet.addPlayer(x, y);
    }
    
    public void removePlayer(Location start, Location end) {
        myPlayerSet.removePlayer(start, end);
    }
    
    public void removePlayer(int startX, int startY, int endX, int endY) {
        myPlayerSet.removePlayer(startX, startY, endX, endY);
    }
    
    public void addTile(Location3D loc, int id, String name, String imageName, Pixmap image) {
        myTileMap.addTile(loc, id, name, imageName, image);
    }
    
    public void addTile(int i, int j, int id, String name , String imageName, Pixmap image) {
        myTileMap.addTile(i, j, id, name, imageName, image);
    }
    
    public void addTerrain(EditableTerrain ter) {
        getTerrain().add(ter);
    } 
    
    public void addTerrain(Pixmap image, Location3D center , int id , String name , String imageName, int walkAbility ) {
        addTerrain(new EditableTerrain(image, center , id , name ,imageName, walkAbility));
    }
    
    public void addTerrain(Pixmap image, int x, int y , int z, int id, String name , String imageName , int walkAbility) {
        addTerrain(image, new Location3D(x, y ,z), id, name , imageName ,walkAbility);
    }
    
    public void addTerrain(Pixmap image, int x, int y, int layerCount, int layerHeight, int id , String name , String imageName, int walkAbility) {
        addTerrain(image,x,y,layerCount*layerHeight,id,name,imageName,walkAbility);
    }
    
    public void addResource(EditableResource res) {
        getResources().add(res);
    }
    
    public void addResource(Pixmap image, Location3D center , int id, String name , String imageName, int amount) {
        addResource(new EditableResource(image,center,id,name,imageName,amount));
    }
    
    public void addResource(Pixmap image, int x, int y , int z, int id, String name , String imageName , int walkAbility) {
        addResource(image, new Location3D(x, y ,z), id, name , imageName ,walkAbility);
    }
    
    public void addResource(Pixmap image, int x, int y, int layerCount, int layerHeight, int id , String name , String imageName, int walkAbility) {
        addResource(image,x,y,layerCount*layerHeight,id,name,imageName,walkAbility);
    }
    
    public int getMyXsize() {
        return myTileMap.getMyHeight();
    }
    
    public int getMyYsize() {
        return myTileMap.getMyWidth();
    }
    
    public int getMyTileWidth() {
        return (int)myTileMap.getMyTileSize().getWidth();
    }
    
    public int getMyTileHeight() {
        return (int)myTileMap.getMyTileSize().getHeight();
    }
    
    public int getTerrainSize() {
        return getTerrain().getSize();
    }
    
    public int getResourceSize() {
        return getResources().getSize();
    }
    
    public String getMyMapName () {
        return myMapName;
    }
    
    public int getMyPlayerNumber() {
        return myPlayerSet.getMyPlayerNumber();
    }
    
    public EditableTile getMyTile(int i , int j) {
        return myTileMap.getTile(i, j);
    }
    
    public EditableTerrain getTerrain(int index) {
        return (EditableTerrain)getTerrain().getMySprites().get(index);
    }
    
    public EditableResource getResource(int index) {
        return (EditableResource)getResources().getMySprites().get(index);
    }

    public void setMyMapName (String myMapName) {
        this.myMapName = myMapName;
    }

    public String getMyDescription () {
        return myDescription;
    }

    public void setMyDescription (String myDescription) {
        this.myDescription = myDescription;
    }
    
    public Map<Integer,Location> getAllPlayers() {
        return myPlayerSet.getAllPlayers();
    }
    
    
    public static void main (String[] args) {
        EditableMap test = new EditableMap("TestMap", "This is a test map", 10, 10, 50, 50);
        
        String imagePath = System.getProperty("user.dir") + "./src/vooga/rts/leveleditor/resource/";
        
        BufferedImage terrain1 = null;
        BufferedImage terrain2 = null;
        
        BufferedImage tile1 = null;
        BufferedImage tile2 = null;
        BufferedImage tile3 = null;
        
        BufferedImage resource1 = null;
        BufferedImage resource2 = null;
    
        
        try {
            terrain1 = ImageIO.read(new File(imagePath+"Terrain1.jpg"));
            terrain2 = ImageIO.read(new File(imagePath+"Terrain2.jpg"));
            
            tile1 = ImageIO.read(new File(imagePath+"Bricks1.jpg"));
            tile2 = ImageIO.read(new File(imagePath+"Brush1.jpg"));
            tile3 = ImageIO.read(new File(imagePath+"Concrete1.jpg"));
            
            resource1 = ImageIO.read(new File(imagePath + "gas1.png"));
            resource2 = ImageIO.read(new File(imagePath + "mineral1.png"));
        }  catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        
        
        test.addPlayer(10, 10);
        test.addPlayer(20, 20);
        
        test.addTile(new Location3D(25,25,0), 1, "bricks", "Bricks1.jpg", new Pixmap(tile1));
        test.addTile(new Location3D(75, 75,0), 1, "bricks", "Bricks1.jpg", new Pixmap(tile1));
        test.addTile(3, 3, 1, "bricks", "Bricks1.jpg", new Pixmap(tile1));
        test.addTile(4, 4, 1, "bricks", "Bricks1.jpg", new Pixmap(tile1));
        
        test.addTile(5, 5, 2, "brush", "Brush1.jpg", new Pixmap(tile2));
        test.addTile(6, 6, 2, "brush", "Brush1.jpg", new Pixmap(tile2));
        test.addTile(7, 7, 2, "brush", "Brush1.jpg", new Pixmap(tile2));
        test.addTile(8, 8, 2, "brush", "Brush1.jpg", new Pixmap(tile2));
        
        test.addTile(5, 7, 3, "concrete", "Concrete1.jpg", new Pixmap(tile3));
        test.addTile(6, 8, 3, "concrete", "Concrete1.jpg", new Pixmap(tile3));
        test.addTile(3, 7, 3, "concrete", "Concrete1.jpg", new Pixmap(tile3));
        test.addTile(2, 8, 3, "concrete", "Concrete1.jpg", new Pixmap(tile3));
        
        
        
        test.addTerrain(new Pixmap(terrain1), 20, 20, 0, 1, "ter1", "Terrain1.jpg", 2);
        test.addTerrain(new Pixmap(terrain1), 40, 40, 0, 1, "ter1", "Terrain1.jpg", 2);
        test.addTerrain(new Pixmap(terrain2), 60, 60, 0, 2, "ter2", "Terrain2.jpg", 4);
        test.addTerrain(new Pixmap(terrain2), 80, 80, 0, 2, "ter2", "Terrain2.jpg", 4);
        
        test.addResource(new Pixmap(resource1), 80, 20,0,1, "gas", "gas1.png", 1000);
        test.addResource(new Pixmap(resource2), 50, 10,0,2, "mineral", "mineral1.png", 1000);
        
        test.printMatrix();
        System.out.println(test.getTerrainSize());
        System.out.println(test.getResourceSize());
        
        System.out.println(test.getMyTile(2, 2).getWorldLocation().getX());
        System.out.println(test.getMyTile(2, 2).getWorldLocation().getY());
                
        MapSaver saver = null;
        try {
            saver = new MapSaver(test);
        }
        catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            saver.generateMapFile(new File(System.getProperty("user.dir") + "./turtleRock"));
        }
        catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
