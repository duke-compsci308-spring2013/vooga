package vooga.fighter.controller.gameinformation;



import vooga.fighter.model.utils.Health;

/**
 * Container for player stats
 * 
 * @author Jerry Li
 * @author Jack Matteucci
 *
 */
public class PlayerStatus {

    private static final String PLAYER = "Player ";
    private static int myPlayerID = 0;
    private String myPlayerName;
    private String myCharacterName;
    private Health myHealth;
    private Double myScore;
    private boolean winStatus;

    /**
     * Constructs player stats. 
     */
    public PlayerStatus () {
        myPlayerID++;
        myCharacterName = "";
        myPlayerName = PLAYER + myPlayerID;
        myHealth = new Health();
        myScore = (double) 0;
    }


    /**
     * Gets player name
     * @return
     */
    public String getPlayerName() {
        return myPlayerName;
    }

    /**
     * gets health
     * @return
     */
    public Health getHealth() {
        return myHealth;
    }

    /**
     * gets score
     * @return
     */
    public Double getScore() {
        return myScore;
    }

    /**
     * sets character name
     * @param name
     */
    public void setCharacterName(String name) {
        myCharacterName = name;
    }

    /**
     * sets health
     * @param health
     */
    public void setHealth(Health health) {
        myHealth = health;
    }

    /**
     * sets score
     * @param score
     */
    public void setScore(double score) {
        myScore = score;
    }

}
