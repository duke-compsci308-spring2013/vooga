package vooga.towerdefense.attributes;

/**
 * Contains constants for use in defining attribute names
 * 
 * 
 * @author Matthew Roy
 * @author Zhen Gou
 * @author Jimmy Longley
 * @author Yoshida
 * 
 */
public enum AttributeConstantsEnum {
    NAME("Name"),
    HEALTH("Health"),
    MONEY("Money"),
    SCORE("Score"),
    SHAPE("Shape"),
    AFFILIATION("Affiliation"),
    
    /**
     * Affiliation value constants
     */
    Player("Player", 0.0),
    Enemy("Enemy", 1.0),
    Neutral("Neutral", 2.0),
    
    Tower("Player", 0.1),
    Unit("Player", 0.2),
    Projectile("Player", 0.3),
    
    ATTACK_DAMAGE("Attack_Damage"),
    ATTACK_RADIUS("Attack_Range"),
    NUM_OF_TARGETS("Number_of_Targets"),
    NUM_OF_PROJECTILES("Number_of_Projectiles"),
    COOL_DOWN("Cool_down"),
    /**
     * Seconds between attacks
     */
    ATTACK_INTERVAL("Attack_Speed"),
    MOVE_SPEED("Move_Speed"),
    FLY_SPEED("Fly_Speed"),
    DIRECTION("Direction"),
    UPGRADES("Upgrades"),
    COST("Cost"),
    
    PROJECTILE_FACTORY("projectile_factory"),
    AURA_EFFECT("Aura");
    
    private String attributeConstant;
    private double attributeValue;
    
    AttributeConstantsEnum(String s) {
        attributeConstant = s;
    }
    
    AttributeConstantsEnum(String s, double v) {
        attributeConstant = s;
        attributeValue = v;
    }
    
    public String getStatusCode () {
        return attributeConstant;
    }
    
    public double getValue () {
        return attributeValue;
    }
}
