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

public class DisplayLoopInfo extends DisplayInfo implements ViewDataSource{
	private Mode myMode;
    private List<ImageDataObject> myImageData;

	public DisplayLoopInfo(Mode mode) {
		super();
		myMode = mode;
    	myImageData = mode.getImageData();
        updateInfo();
	}

    public void updateInfo(){
    	getSpriteLocations().clear();
    	getSprites().clear();
    	getImageSizes().clear();
    	myImageData = myMode.getImageData();
    	setObjectNumber(myImageData.size());
    	for(ImageDataObject data : myImageData){
    		getSprites().add(data.getMyImage());
    		getSpriteLocations().add(data.getMyLocation());
    		getImageSizes().add(data.getMySize());
    	}
    }
    
    public void update() {
        updateInfo();
    }
    
    public void clear(){
    	super.clear();
        myImageData.clear();
    }

    public Mode getMode() {
        return myMode;
    }

}
