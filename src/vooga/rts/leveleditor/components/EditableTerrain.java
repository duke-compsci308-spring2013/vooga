package vooga.rts.leveleditor.components;


import java.awt.Image;
import vooga.rts.gamedesign.sprite.map.Terrain;
import vooga.rts.util.Location3D;
import vooga.rts.util.Pixmap;


/**
 * all class of terrains, which is used as a part of map
 * 
 * @author Richard Yang
 * 
 */

public class EditableTerrain extends Terrain {

    private int myID;
    
    private String myName;
    private String myImageName;
    
    private int myWalkAbility; 

    public EditableTerrain (Pixmap image, Location3D center , int id , String name , String imageName, int walkAbility) {
        super(image, center, image.getMyDimension());
        myID = id;
        myName = name;
        myImageName = imageName;
        myWalkAbility = walkAbility;
    }
   
    public EditableTerrain (Pixmap image, int x, int y , int z, int id, String name , String imageName , int walkAbility) {
        this(image,new Location3D(x,y,z),id,name,imageName,walkAbility);
    }

    public EditableTerrain (Pixmap image, int x, int y, int layerCount, int layerHeight, int id , String name , String imageName, int walkAbility) {
        this(image,x,y,layerCount*layerHeight,id,name,imageName,walkAbility);
    }

    public int getMyID () {
        return myID;
    }

    public String getMyName () {
        return myName;
    }

    public String getMyImageName () {
        return myImageName;
    }

    public int getMyWalkAbility () {
        return myWalkAbility;
    }
    
    public Image getMyImage() {
        return super.getImage().getMyImage();
    }


    public void setWalkability(int w) {
        myWalkAbility = w;
        
    }

    public void setType(String type) {
        myName = type;
        
    } 
 
}
