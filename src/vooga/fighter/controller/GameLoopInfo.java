package vooga.fighter.controller;
import java.awt.Dimension;
import java.util.List;
import java.util.ArrayList;
import java.util.Observable;
import util.Location;
import vooga.fighter.model.mode.LevelMode;
import vooga.fighter.model.mode.Mode;
import vooga.fighter.model.utils.Health;
import vooga.fighter.model.utils.ImageDataObject;
import vooga.fighter.util.HUDVariable;
import vooga.fighter.util.Paintable;
import util.*;


/**     
 * Contains all information required by the view about game objects in a game loop.
 * List indices line up between lists (i.e. index 0 of all lists is player 1 information,
 * index 1 is player 2 info)
 * @author Matt Parides
 * @author Jerry Li 
 * @author Jack Matteucci 
 * @author Wayne You
 *
 */

public class GameLoopInfo extends DisplayLoopInfo implements ViewDataSource{

    private List<String> myCharacterNames;
    private List<Health> myHealthStats;
    private List<Double> myScores;
    private List<PlayerStatus> myPlayerStats;
    private int myNumberPlayers;
    private LevelMode myLevelMode;

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
    	super(mode);
    	myLevelMode = mode;
    	myPlayerStats = new ArrayList<PlayerStatus>();
        myHealthStats = new ArrayList<Health>();
        myScores = new ArrayList<Double>();
        myCharacterNames = new ArrayList<String>();
        myNumberPlayers = mode.getCharacterObjects().size();
        initializePlayers();
    }
    
    @Override
    public Mode getMode() {
        return myLevelMode;
    }
    
    public void initializePlayers() {
          if (myNumberPlayers == 1) {
              Player1Status = new PlayerStatus();
              myPlayerStats.add(Player1Status);
          }
          else if (myNumberPlayers == 2) {
              Player1Status = new PlayerStatus();
              Player2Status = new PlayerStatus();
              myPlayerStats.add(Player1Status);
              myPlayerStats.add(Player2Status);
          }
          else if (myNumberPlayers == 3) {
              Player1Status = new PlayerStatus();
              Player2Status = new PlayerStatus();
              Player3Status = new PlayerStatus();
              myPlayerStats.add(Player1Status);
              myPlayerStats.add(Player2Status);
              myPlayerStats.add(Player3Status);
          }
          else if (myNumberPlayers == 4) {
              Player1Status = new PlayerStatus();
              Player2Status = new PlayerStatus();
              Player3Status = new PlayerStatus();
              Player4Status = new PlayerStatus();
              myPlayerStats.add(Player1Status);
              myPlayerStats.add(Player2Status);
              myPlayerStats.add(Player3Status);
              myPlayerStats.add(Player4Status);
          }
          addHUDElements();
    }
    
    @Override
    public void update() {
        super.update();
        updateStats();
       setChanged();
       notifyObservers();
    }
    
    
    
    public void updateStats() {
        LevelMode currentMode = (LevelMode) getMode();
        myHealthStats = currentMode.getHealthStats();
        for (int i = 0; i < myNumberPlayers; i++) {
            myPlayerStats.get(i).setHealth(myHealthStats.get(i));
        }
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
     * @return the myHealthStats
     */
    public List<Health> getHealthStats() {
        return myHealthStats;
    }

    /**
     * @param myHealthStats the myHealthStats to set
     */
    public void setHealthStats(List<Health> healthStats) {
        myHealthStats = healthStats;
    }

    public void setHealthStat(int index, Health heal) {
        myHealthStats.set(index, heal);
    }

    /**
     * @return the myImageSizes
     */

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
