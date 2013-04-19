package vooga.fighter.controller;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import util.Location;
import util.Pixmap;
import vooga.fighter.model.utils.Health;
import vooga.fighter.util.Paintable;

public class DisplayInfo extends Observable implements ViewDataSource{
    
    
    private List<Location> mySpriteLocations;
    private List<Paintable> mySprites;
    private List<Dimension> myImageSizes;
    private Integer myNumObjects;

	public DisplayInfo() {
        mySpriteLocations = new ArrayList<Location>();
        mySprites = new ArrayList<Paintable>();
        myImageSizes = new ArrayList<Dimension>();
	}
	
    /**
     * @return object location at list index
     */

    public Paintable getPaintable (int index) {
        return mySprites.get(index);
    }

    public Location getLocation(int index) {
        return mySpriteLocations.get(index);
    }

    /**
     * @return size of sprite at list index
     */
    public Dimension getSize (int index) {
        return myImageSizes.get(index);
    }
    
    /**
     * @param mySpriteLocations the mySpriteLocations to set
     */
    public void setSpriteLocations(List<Location> spriteLocations) {
        mySpriteLocations = spriteLocations;
    }

    /**
     * 
     * @param index
     */
    public void setSpriteLocation(int index, Location loc) {
        mySpriteLocations.set(index, loc);
    }

    /**
     * @return the mySprites
     */
    public List<Paintable> getSprites() {
        return mySprites;
    }

    /**
     * @param mySprites the mySprites to set
     */
    public void setSprites(List<Paintable> sprites) {
        mySprites = sprites;
    }

    public void setSprite(int index, Paintable pix) {
        mySprites.set(index, pix);
    }
    
    public List<Dimension> getImageSizes() {
        return myImageSizes;
    }

    /**
     * @param myImageSizes the myImageSizes to set
     */
    public void setImageSizes(List<Dimension> imageSizes) {
        myImageSizes = imageSizes;
    }

    /**
     * 
     */
    public void setImageSize(int index, Dimension dim) {
        myImageSizes.set(index, dim);
    }

    /**
     * @return the mySpriteLocations
     */
    protected List<Location> getSpriteLocations() {
        return mySpriteLocations;
    }
    
    public void clear(){
        mySprites.clear();
        myImageSizes.clear();
        mySpriteLocations.clear();
        myNumObjects = 0;
    }
    
    public int ObjectNumber (){
    	return myNumObjects;
    }
    
    public void update(){
    }
    
    protected void setObjectNumber(int number){
    	myNumObjects = number;
    }

}
