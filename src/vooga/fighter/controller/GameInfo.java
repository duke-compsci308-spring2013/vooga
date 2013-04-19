package vooga.fighter.controller;

import java.util.ArrayList;
import java.util.List;

import arcade.games.HighScores;
import arcade.games.UserGameData;

/**
 * Information about the broader game state. includes game mode, selected map and characters.
 * @author matthewparides
 * 
 */
public class GameInfo extends UserGameData{

    private String myGameMode;
    private String myMapName;
    private int myMapCount;
    private List<String> myCharacters;
    private Integer myNumCharacters;
    private List<Integer> myScores;
    private List<String> myMapsPlayed;
    private List<String> myMapNames;
    private HighScores myHighScores;

    /**
     * Constructor
     */
    public GameInfo (List<String> mapNames) {
    	myCharacters = new ArrayList<String>();
    	myMapsPlayed = new ArrayList<String>();
    	myScores = new ArrayList<Integer>();
    	myGameMode = "Fighting Game";
    	myMapNames = mapNames;
    }
    
    /**
     * Test constructor, automatically adds character index and map index to load. 
     */
    public GameInfo (List<String> mapNames, List<String> characters, String map) {
        this(mapNames);
        myCharacters = characters;
        myMapName = map;
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
    public int getScore(int index) {
    	return myScores.get(index);
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
    @Override
    public List<Double> getScores() {
    	List<Double> myArcadeScores = new ArrayList<Double>();
    	for(Integer i : myScores){
    		myArcadeScores.add((double) i);
    	}
    	return myArcadeScores;
    }
    
    /**
     * 
     * @return
     */
    public List<Integer> getIntScores() {
    	return myScores;
    }
   
    
    /**
     * 
     * @return
     */
    public String getGameMode () {
        return myGameMode;
    }

    /**
     * 
     * @param myGameMode
     */
    public void setGameMode (String gameMode) {
        myGameMode = gameMode;
    }

    /**
     * 
     * @return
     */
    public String getMapName () {
        return myMapName;
    }

    /**
     * 
     * @param myMapName
     */
    public void setMapName (String map) {
        myMapName = map;
    }

    /**
     * 
     * @return
     */
    public List<String> getCharacters () {
        return myCharacters;
    }
    
    public void addCharacters(String character) {
        myCharacters.add(character);
    }
    /**
     * 
     * @param index
     * @param characterName
     */
    public void setCharacter (int index, String character) {
        myCharacters.set(index, character);
    }

    /**
     * 
     * @param myCharacters
     */
    public void setCharacters (List<String> characters) {
        myCharacters = characters;
    }

    /**
     * 
     * @return
     */
    public int getNumCharacters () {
        return myNumCharacters;
    }

    /**
     * 
     * @param myNumCharacters
     */
    public void setNumCharacters (int numCharacters) {
        myNumCharacters = numCharacters;
    }
    

    public int getMapCount(){
    	return myMapNames.size();
    }

    public List<String> getMapsPlayed(){
    	return myMapsPlayed;
    }
    
    public List<String> getMapNames(){
    	return myMapNames;
    }
    
    public void setHighScores(HighScores highscores){
    	myHighScores = highscores;
    }
    
    public HighScores getHighScores(){
    	return myHighScores;
    }
}
