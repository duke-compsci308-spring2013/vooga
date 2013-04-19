package vooga.fighter.controller;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import util.Location;
import util.Pixmap;
import vooga.fighter.model.LevelMode;
import vooga.fighter.model.MenuMode;
import vooga.fighter.model.Mode;
import vooga.fighter.model.utils.Health;
import vooga.fighter.model.utils.ImageDataObject;
import vooga.fighter.util.Paintable;

public class LoopInfo extends Observable implements ViewDataSource{
	private Mode myMode;
    private Integer myNumObjects;
    private List<ImageDataObject> myImageData;
    private List<Location> mySpriteLocations;
    private List<Pixmap> mySprites;
    private List<Dimension> myImageSizes;

	public LoopInfo(Mode mode) {
		myMode = mode;
    	myImageData = mode.getImageData();
        mySpriteLocations = new ArrayList<Location>();
        mySprites = new ArrayList<Pixmap>();
        myImageSizes = new ArrayList<Dimension>();
        update();
	}

    public void update(){
    	mySpriteLocations.clear();
    	mySprites.clear();
    	myImageSizes.clear();
    	myImageData = myMode.getImageData();
    	myNumObjects = myImageData.size();
    	for(ImageDataObject data : myImageData){
    		mySprites.add(data.getMyImage());
    		mySpriteLocations.add(data.getMyLocation());
    		myImageSizes.add(data.getMySize());
    	}
    }
	
    public int ObjectNumber () {
        return myNumObjects;
    }

    /**
     * @return paintable object at list index
     */
    public Paintable getPaintable (int index) {
        return mySprites.get(index);
    }

    /**
     * @return object location at list index
     */
    //    public Location getLocation (int index) {
    //    	return mySpriteLocations.get(index);
    //    }

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
    public List<Pixmap> getSprites() {
        return mySprites;
    }

    /**
     * @param mySprites the mySprites to set
     */
    public void setSprites(List<Pixmap> sprites) {
        mySprites = sprites;
    }

    public void setSprite(int index, Pixmap pix) {
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
    public List<Location> getSpriteLocations() {
        return mySpriteLocations;
    }


}
