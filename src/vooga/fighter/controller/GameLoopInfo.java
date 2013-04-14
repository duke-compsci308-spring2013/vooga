package vooga.fighter.controller;
import java.awt.Dimension;
import java.util.List;
import java.util.ArrayList;
import java.util.Observable;

import vooga.fighter.objects.utils.Health;
import vooga.fighter.util.HUDVariable;
import vooga.fighter.util.Location;
import vooga.fighter.util.Paintable;
import vooga.fighter.util.Pixmap;


/**
 * Contains all information required by the view about game objects in a game loop.
 * List indices line up between lists (i.e. index 0 of all lists is player 1 information,
 * index 1 is player 2 info)
 * @author matthewparides
 *
 */

public class GameLoopInfo implements Observable, ViewDataSource{
	private Integer myNumObjects;
	private List<String> myCharacterNames;
	private List<Location> mySpriteLocations;
	private List<Pixmap> mySprites;
	private List<Health> myHealthStats;
	private List<Dimension> myImageSizes;
	private List<Integer> myScores;
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
	
	public GameLoopInfo(int numObjects) {
		myNumObjects = numObjects;
		mySpriteLocations = new ArrayList<Location>();
		mySprites = new ArrayList<Pixmap>();
		myHealthStats = new ArrayList<Health>();
		myImageSizes = new ArrayList<Dimension>();
		myScores = new ArrayList<Integer>();
		myCharacterNames = new ArrayList<String>();
		myPlayerStats = new ArrayList<PlayerStatus>();
		myPlayerStats.add(Player1Status);
		myPlayerStats.add(Player2Status);
		myPlayerStats.add(Player3Status);
		myPlayerStats.add(Player4Status);
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
    public Location getLocation (int index) {
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
    
    
    public Integer getScore(int index) {
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
    public void setScores(List<Integer> scores) {
    	myScores = scores;
    }
    
    
    /**
     * 
     */
    public void setScore(int index, int score) {
    	myScores.set(index, score);
    }
    
    /**
     * 
     * @return
     */
    public List<Integer> getScores() {
    	return myScores;
    }
	
	
}
