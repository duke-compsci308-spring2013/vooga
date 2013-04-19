package vooga.fighter.controller;



import vooga.fighter.model.utils.Health;

public class PlayerStatus {
    
    private static int myPlayerID = 0;
    private String myPlayerName;
    private String myCharacterName;
    private Health myHealth;
    private Double myScore;
    
    public PlayerStatus () {
        myPlayerID++;
        myCharacterName = "";
        myPlayerName = "Player " + myPlayerID;
        myHealth = null;
        myScore = (double) 0;
    }
    
    public PlayerStatus (String name, Health health, Double score) {
        myCharacterName = name;
        myHealth = health;
        myScore = score;
    }
    
    public String getPlayerName() {
        return myPlayerName;
    }
    
    public Health getHealth() {
        return myHealth;
    }
    
    public Double getScore() {
        return myScore;
    }
    
    public void setCharacterName(String name) {
        myCharacterName = name;
    }
    
    public void setHealth(Health health) {
        myHealth = health;
    }
    
    public void setScore(double score) {
        myScore = score;
    }
    
}
