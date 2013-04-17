package vooga.rts.leveleditor.components;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vooga.rts.leveleditor.gui.MapPanel;
import vooga.rts.util.Location;

/**
 * a class of EditableMap, this editable map is used to generate a 
 * map that can be used for game players. This class enables them to
 * generated their own map
 * 
 * @author Richard Yang
 *
 */


public class EditableMap implements Serializable {

    
    private int myXSize;
    private int myYSize; 
    
    private List<Terrain> myTerrains;
    
    private String myMapName = "CIEMAS";
    private String myDescription = " our RTS is the best one !";
    
    EditableNode[][] myNodeMatrix;
    
    private Map<Integer , Location> myPlayerLocations;
    private int myPlayerNumber;
    
    private MapSaver mySaver;
    private MapLoader myLoader;
    
    public EditableMap(int x , int y, int nodeX, int nodeY) {
        
        myXSize = x;
        myYSize = y;
        
        initializeMap(nodeX,nodeY); 
    }
    
    public EditableMap(int x , int y) {
        myXSize = x;
        myYSize = y;
        initializeMap();
    }
    
    public EditableMap() {
        this(0,0);
    }
    
    public void initializeMap(int width, int height) {
        myNodeMatrix = new EditableNode[myXSize][myYSize];
        for(int i =0 ; i<myXSize ; i++) {
            for(int j =0 ; j<myYSize ; j++) {
                myNodeMatrix[i][j] = new EditableNode(i,j,width, height, false);
            }
        }
        myPlayerLocations = new HashMap<Integer , Location>();
        myPlayerNumber = 0;
        mySaver = new MapSaver();
        myLoader = new MapLoader();
    }
    
    public void initializeMap() {
        initializeMap(MapPanel.DEFAULT_TILE_WIDTH,MapPanel.DEFAULT_TILE_HEIGHT);
    }
   
    public void addPlayer(int x, int y) {
        
        myPlayerLocations.put(myPlayerNumber, new Location(x,y));
        myPlayerNumber ++;
        
    }
    
    public void addPlayer(Location loc) {
        myPlayerLocations.put(myPlayerNumber, loc);
        myPlayerNumber ++;
    }
    
    public void removePlayer(int index) {
        myPlayerLocations.remove(index);
        myPlayerNumber --;
    }
    
    
    public void clearMap() {
        for( int i =0 ; i< myXSize ; i++) {
            for( int j =0 ; j< myYSize ; j++) {
                myNodeMatrix[i][j].reset();
            }
        }
    }
    
    
   
    public void printMatrix() {
        System.out.println("printmatrix executed");
        System.out.println("X Size : " + myXSize);
        System.out.println("Y Size : " + myYSize);
        
        for(int i =0 ; i<myXSize ; i++) {
            for(int j =0 ; j<myYSize ; j++) {
                System.out.println(myNodeMatrix[i][j].getTileType());
            }
            System.out.print("\n");
        }
        for(int i = 0 ; i<10 ; i++) {
           System.out.print("**");
        }
        System.out.print("\n");
    }
    
    public void load(File resourceFile) {
        try {
            myLoader.loadMapFile(this, resourceFile);
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void load(String filePath) {
        File bufferFile = new File(filePath);
        load(bufferFile);
    }
    
    public void save(File objectiveFile) { 
        try {
            mySaver.generateMapFile(this, objectiveFile);
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void save(String filePath) {
        File bufferFile = new File(filePath);
        save(bufferFile);
    }
    
    public void addTerrain(Terrain ter) {
        myTerrains.add(ter);
    }
    
    public void addTile(int x , int y , String tileType) {
        myNodeMatrix[x][y].setTileType(tileType);
    }
    
    
    public String getMyMapName () {
        return myMapName;
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

    public EditableNode getMapNode(int x, int y) {
        return myNodeMatrix[x][y];
    }
   
    public EditableNode[][] getMap() {
        return myNodeMatrix;
    }
    
    public int getMyPlayerNumber () {
        return myPlayerNumber;
    }
    
    public int getMyXSize () {
        return myXSize;
    }

    public int getMyYSize () {
        return myYSize;
    }  

    public void setMyXSize (int myXSize) {
        this.myXSize = myXSize;
    }

    public void setMyYSize (int myYSize) {
        this.myYSize = myYSize;
    }

    public Location getPlayer(int index) {
        return myPlayerLocations.get(index); 
    }
    
    

    public Map<Integer, Location> getAllPlayers () {
        return myPlayerLocations;
    }
 
    public int getWidth() {
        return myXSize;
    }

    public int getHeight() {
        return myYSize;
    }

    public void setWidth(int w) {
        myXSize = w;

    }

    public void setHeight(int h) {
        myXSize = h;

    }

    
    public void ZoomIn() {
        for(int i =0 ; i<myXSize ; i++) {
            for(int j =0 ; j<myYSize ; j++) {
                myNodeMatrix[i][j].ZoomIn();
            }
        }
        
    }

    public void ZoomOut() {
        for(int i =0 ; i<myXSize ; i++) {
            for(int j =0 ; j<myYSize ; j++) {
                myNodeMatrix[i][j].ZoomOut();
            }
        }
        
    }
    
    public static void main(String[] args) {
        EditableMap test = new EditableMap(10,10);
        test.addTile(1, 1, "grass");
        test.addTile(2, 2, "sand");
        test.addTile(3, 3, "shit");
        test.save(System.getProperty("user.dir") + "./src/test.xml");
    }
   
}
