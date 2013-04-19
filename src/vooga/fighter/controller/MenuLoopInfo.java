package vooga.fighter.controller;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import util.Location;
import util.Pixmap;
import vooga.fighter.model.LevelMode;
import vooga.fighter.model.MenuMode;
import vooga.fighter.model.Mode;
import vooga.fighter.model.utils.Health;
import vooga.fighter.model.utils.ImageDataObject;
import vooga.fighter.util.Paintable;

public class MenuLoopInfo implements ViewDataSource{
	private Mode myMode;
    private Integer myNumObjects;
    private List<ImageDataObject> myImageData;
    private List<Location> mySpriteLocations;
    private List<Pixmap> mySprites;
    private List<Dimension> myImageSizes;

	public MenuLoopInfo(Mode mode) {
    	myImageData = mode.getImageData();
        mySpriteLocations = new ArrayList<Location>();
        mySprites = new ArrayList<Pixmap>();
        myImageSizes = new ArrayList<Dimension>();
        updateImages();
	}

    public void updateImages(){
    	
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

}
