package vooga.rts.leveleditor.components;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import vooga.rts.gamedesign.sprite.map.Tile;
import vooga.rts.resourcemanager.ResourceManager;
import vooga.rts.util.Location3D;
import vooga.rts.util.Pixmap;


/**
 * the class of a single editable map node. This node has a linked list
 * to store the information of tiles, terrains and resources.
 * 
 * @author Richard Yang
 *
 */

public class EditableTile extends Tile {
    
    private static final String DEFAULT_IMAGE_PATH = "./vooga/rts/leveleditor/resource/default.gif";
    
    private int myID;
    private String myName;
    private  String myImageName;
    private static Pixmap DEFAULT_IMAGE = new Pixmap(ResourceManager.getInstance().<BufferedImage>getFile(DEFAULT_IMAGE_PATH, BufferedImage.class));
    
    private boolean myOccupied;
    
    
    public EditableTile(Pixmap image, Location3D center, Dimension size, int id , String name, String imageName, boolean isOccupied) {
        super(image,center,size);
        myID = id;
        myName = name;
        myImageName = imageName;
        myOccupied = isOccupied;
    }
    
    
    
    public EditableTile(Pixmap image, int xCount, int yCount, Dimension size, int id, String name, String imageName , boolean isOccupied) {
        this(image, new Location3D(yCount*size.getWidth()+size.getWidth()/2,xCount*size.getHeight()+size.getHeight()/2,0), size,id,name,imageName,isOccupied);
    }
    
    public EditableTile(EditableTile node) {
        this(node.getImage(),node.getWorldLocation(),
                           new Dimension((int)node.getWidth(), (int)node.getHeight()),
                                                                   node.getMyID(),node.getMyName(),node.getMyImageName(),node.getOccupied());
    }
    
    public EditableTile(int xCount, int yCount, Dimension size) {
        
        this(DEFAULT_IMAGE,xCount,yCount,size,0,"","",false);
    }
    
    public EditableTile(int xCount, int yCount, int width, int height) {
        this(xCount,yCount,new Dimension(width,height));
    }
    
    
    
    public void setOccupied(boolean b) {
        myOccupied = b;
    }
   
    public boolean getOccupied() {
        return myOccupied;
    }
        
    public int getMyID() {
        return myID;
    }
    
    public String getMyImageName() {
        return myImageName;
    }
    
    public String getMyName() {
        return myName;
    }
    public void reset() {
        myOccupied = false;
        myID= 0;
        myName = "";
        myImageName = "";
    }

    public void setType(String type) {
        myName = type;        
    }
   
}
