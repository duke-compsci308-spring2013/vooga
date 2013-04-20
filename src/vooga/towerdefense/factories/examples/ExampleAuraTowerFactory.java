package vooga.towerdefense.factories.examples;

import java.awt.Dimension;
import java.util.ArrayList;
import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.AttackAction;
import vooga.towerdefense.action.FindTargets;
import vooga.towerdefense.action.ModifyAttributeValue;
import vooga.towerdefense.action.TrackTarget;
import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.attributes.AttributeConstants;
import vooga.towerdefense.attributes.AttributeManager;
import vooga.towerdefense.factories.ProjectileFactory;
import vooga.towerdefense.factories.TowerDefinition;
import vooga.towerdefense.factories.TowerFactory;
import vooga.towerdefense.gameElements.Tower;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.util.Location;
import vooga.towerdefense.util.Pixmap;


/**
 * @author Matthew Roy
 * 
 */
public class ExampleAuraTowerFactory extends TowerFactory {

    GameMap myMap;

    /**
     * @param name
     * @param def
     */
    public ExampleAuraTowerFactory (GameMap map, String name, TowerDefinition def) {
        super(name, def);
        myMap = map;
    }

    public Tower createTower (Location putHere) {
        TowerDefinition def = new TowerDefinition();
        AttributeManager AM = getDefaultAM();
        
        Pixmap tImage = new Pixmap("palmtree.png");


        Tower myTower;
        if (putHere != null) {
            myTower = new Tower(tImage, putHere,
                                new Dimension(100,100), AM);
        }
        else {
            myTower = new Tower(def.getImage(),
                                def.getCenter(), def.getSize(), AM);
        }

        ArrayList<Action> actions = new ArrayList<Action>();
        FindTargets findTargets =
                new FindTargets(myMap, putHere, AM.getAttribute(AttributeConstants.ATTACK_RADIUS));
        findTargets.initAction();
        findTargets.addFollowUpAction(new ModifyAttributeValue(AM
                                                               .getAttribute(AttributeConstants.AURA_EFFECT), AttributeConstants.MOVE_SPEED));
        actions.add(findTargets);

        myTower.addActions(actions);
        return myTower;
    }

}
