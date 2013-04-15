package vooga.fighter.controller;


import vooga.fighter.model.utils.Health;

public class PlayerStatus {
    
    private String myName;
    private Health myHealth;
    private Double myScore;
    
    public PlayerStatus (String name, Health health, Double score) {
        myName = name;
        myHealth = health;
        myScore = score;
    }
    
    public String getName() {
        return myName;
    }
    
    public Health getHealth() {
        return myHealth;
    }
    
    public Double getScore() {
        return myScore;
    }
    
    public void setName(String name) {
        myName = name;
    }
    
    public void setHealth(Health health) {
        myHealth = health;
    }
    
    public void setScore(double score) {
        myScore = score;
    }
    
}
