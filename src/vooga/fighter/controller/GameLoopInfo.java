package vooga.fighter.controller;
import java.awt.Dimension;
import java.util.List;
import java.util.ArrayList;
import java.util.Observable;

import util.Location;
import vooga.fighter.model.LevelMode;
import vooga.fighter.model.utils.Health;
import vooga.fighter.model.utils.ImageDataObject;
import vooga.fighter.util.HUDVariable;
import vooga.fighter.util.Paintable;
import util.*;


/**
 * Contains all information required by the view about game objects in a game loop.
 * List indices line up between lists (i.e. index 0 of all lists is player 1 information,
 * index 1 is player 2 info)
 * @author matthewparides
 *
 */

public class GameLoopInfo extends Observable implements ViewDataSource{

    private LevelMode myMode;
    private Integer myNumObjects;
    private List<ImageDataObject> myImageData;
    private List<String> myCharacterNames;
    private List<Location> mySpriteLocations;
    private List<Pixmap> mySprites;
    private List<Health> myHealthStats;
    private List<Dimension> myImageSizes;
    private List<Double> myScores;
    private List<PlayerStatus> myPlayerStats;

    @HUDVariable(
                 name = "Player1",
                 HUDElementClass = "PlayerScoreAndHealth"
            )
    private PlayerStatus Player1Status;

    @HUDVariable(
                 name = "Player2",
                 HUDElementClass = "PlayerScoreAndHealth"
            )
    private PlayerStatus Player2Status;

    @HUDVariable(
                 name = "Player3",
                 HUDElementClass = "PlayerScoreAndHealth"
            )
    private PlayerStatus Player3Status;

    @HUDVariable(
                 name = "Player4",
                 HUDElementClass = "PlayerScoreAndHealth"
            )
    private PlayerStatus Player4Status;

    public GameLoopInfo(LevelMode mode) {
    	myMode = mode;
    	myImageData = mode.getImageData();
        mySpriteLocations = new ArrayList<Location>();
        mySprites = new ArrayList<Pixmap>();
        myImageSizes = new ArrayList<Dimension>();
        myHealthStats = new ArrayList<Health>();
        myScores = new ArrayList<Double>();
        myCharacterNames = new ArrayList<String>();
        myPlayerStats = new ArrayList<PlayerStatus>();
        myPlayerStats.add(Player1Status);
        myPlayerStats.add(Player2Status);
        myPlayerStats.add(Player3Status);
        myPlayerStats.add(Player4Status);
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
    
    public void updateStats() {
        
    }

    /**
     * Updates the information in the PlayerStatus objects to reflect the 
     * current data in this GameLoopInfo class.
     */
    public void updatePlayerStats() {
        for(int i = 0; i < 4; i++) {
            myPlayerStats.get(i).setName(myCharacterNames.get(i));
            myPlayerStats.get(i).setScore(myScores.get(i));
            myPlayerStats.get(i).setHealth(myHealthStats.get(i));
        }
    }


    /**
     * @return number of objects in this game loop
     */
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
     * @return Health at list index
     */
    public Health getHealth(int index) {
        return myHealthStats.get(index);
    }


    public Double getScore(int index) {
        return myScores.get(index);
    }

    /**
     * @return the mySpriteLocations
     */
    public List<Location> getSpriteLocations() {
        return mySpriteLocations;
    }

    /**
     * @param mySpriteLocations the mySpriteLocations to set
     */
    public void setSpriteLocations(List<Location> mySpriteLocations) {
        mySpriteLocations = mySpriteLocations;
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
    public void setSprites(List<Pixmap> mySprites) {
        mySprites = mySprites;
    }

    public void setSprite(int index, Pixmap pix) {
        mySprites.set(index, pix);
    }

    /**
     * @return the myHealthStats
     */
    public List<Health> getHealthStats() {
        return myHealthStats;
    }

    /**
     * @param myHealthStats the myHealthStats to set
     */
    public void setHealthStats(List<Health> myHealthStats) {
        myHealthStats = myHealthStats;
    }

    public void setHealthStat(int index, Health heal) {
        myHealthStats.set(index, heal);
    }

    /**
     * @return the myImageSizes
     */
    public List<Dimension> getImageSizes() {
        return myImageSizes;
    }

    /**
     * @param myImageSizes the myImageSizes to set
     */
    public void setImageSizes(List<Dimension> myImageSizes) {
        myImageSizes = myImageSizes;
    }

    /**
     * 
     */
    public void setImageSize(int index, Dimension dim) {
        myImageSizes.set(index, dim);
    }

    /**
     * 
     */
    public void setScores(List<Double> scores) {
        myScores = scores;
    }


    /**
     * 
     */
    public void setScore(int index, double score) {
        myScores.set(index, score);
    }

    /**
     * 
     * @return
     */
    public List<Double> getScores() {
        return myScores;
    }


}
