package vooga.towerdefense.factories;

import java.util.ArrayList;
import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.AttackAction;
import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.attributes.AttributeConstants;
import vooga.towerdefense.attributes.AttributeManager;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.gameElements.Tower;
import vooga.towerdefense.gameElements.Unit;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.model.Path;
import vooga.towerdefense.util.Location;

/**
 * @author Matthew Roy
 *
 */
public class ExampleTowerFactory extends TowerFactory {
	private GameMap myGameMap;

    /**
     * @param name
     * @param def
     */
    public ExampleTowerFactory (String name, GameMap map) {
        super(name, null);
        myGameMap=map;
    }
    
    
    
    public Tower createTower(Location putHere){
        TowerDefinition def=new TowerDefinition();
       
        AttributeManager AM = new AttributeManager();
        AM.addAttribute(new Attribute(AttributeConstants.ATTACK_RADIUS, 300.0));
        AM.addAttribute(new Attribute(AttributeConstants.DIRECTION, 50.0));
        AM.addAttribute(new Attribute(AttributeConstants.ATTACK_INTERVAL, 30.0));
        AM.addAttribute(new Attribute(AttributeConstants.NUM_OF_TARGETS, 1.0));
        AM.addAttribute(new Attribute(AttributeConstants.MOVE_SPEED,10.0));
        AM.setProjectileFactory(new ProjectileFactory());
        Tower myTower;
        if (putHere != null) {
                myTower = new Tower(def.getImage(), def.getCenter(),
                                def.getSize(), AM);
        } else {
                myTower = new Tower(def.getImage(),
                                def.getCenter(), def.getSize(), AM);
        }

        ArrayList<Action> actions = new ArrayList<Action>();
        actions.add(new AttackAction(myGameMap, myTower, myTower.getAttributeManager().getProjectileFactory()));
        
        
       
        //actions.add(new AttackAction(myTower, ));
        myTower.addActions(actions);
        return myTower;

        
        /*
                return new GameElement(def.getImage(), 
                                def.getCenter(), 
                                def.getSize(), 
                                createAttributeFactory().makeAttributeManager(), 
                                createActionFactory().createActions());
                                */
    }

}
