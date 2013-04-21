package arcade.games;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the names and high scores a game can ask for from the arcade.
 * These high scores will be aggregated from all of the UserGameData's statistic
 * fields for this game
 * 
 * This class is currently a wrapper around a Map, but could be changed at a
 * later date.
 *
 */
public class HighScores {
    private Map<User, Double> myScores;

    public HighScores() {
        myScores = new HashMap<User, Double>();
    }
    
    /**
     * Returns the scores in descending order.
     */
    public Map<User, Double> getScores() {
        return myScores;
    }
}
