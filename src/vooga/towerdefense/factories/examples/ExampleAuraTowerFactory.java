package vooga.towerdefense.factories.examples;

import java.awt.Dimension;
import java.util.ArrayList;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.FindTargets;
import vooga.towerdefense.action.ModifyAttributeValue;
import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.attributes.AttributeConstants;
import vooga.towerdefense.attributes.AttributeManager;
import vooga.towerdefense.factories.GameElementFactory;
import vooga.towerdefense.factories.ProjectileFactory;
import vooga.towerdefense.factories.TowerDefinition;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.util.Location;
import vooga.towerdefense.util.Pixmap;


/**
 * @author Matthew Roy
 * 
 */
public class ExampleAuraTowerFactory extends GameElementFactory {

    GameMap myMap;

    /**
     * @param name
     * @param def
     */
    public ExampleAuraTowerFactory (GameMap map, String name, TowerDefinition def) {
        super(name, def);
        myMap = map;
    }

    public GameElement createElement (Location putHere) {
        TowerDefinition def = new TowerDefinition();
        AttributeManager AM = getDefaultAM();

        //Pixmap tImage = new Pixmap("palmtree.png");
        Pixmap tImage = new Pixmap("Duvall.jpg");

        GameElement myTower;
        if (putHere != null) {
            myTower = new GameElement(tImage, putHere,
                                      new Dimension(50, 50), AM);
        }
        else {
            myTower = new GameElement(def.getImage(),
                                      def.getCenter(), def.getSize(), AM);
        }

        ArrayList<Action> actions = new ArrayList<Action>();
        FindTargets findTargets =
                new FindTargets(myMap, putHere, AM.getAttribute(AttributeConstants.ATTACK_RADIUS));
        findTargets.addFollowUpAction(new ModifyAttributeValue(AM
                .getAttribute(AttributeConstants.AURA_EFFECT), AttributeConstants.HEALTH));
        actions.add(findTargets);

        myTower.addActions(actions);
        return myTower;
    }

    public AttributeManager getDefaultAM () {
        AttributeManager AM = new AttributeManager();
        AM.addAttribute(new Attribute(AttributeConstants.ATTACK_RADIUS, 500.0));
        AM.addAttribute(new Attribute(AttributeConstants.DIRECTION, 50.0));
        AM.addAttribute(new Attribute(AttributeConstants.ATTACK_INTERVAL, 30.0));
        AM.addAttribute(new Attribute(AttributeConstants.NUM_OF_TARGETS, 1.0));
        AM.addAttribute(new Attribute(AttributeConstants.ATTACK_RADIUS, 200.0));
        AM.addAttribute(new Attribute(AttributeConstants.MOVE_SPEED, 10.0));
        AM.addAttribute(new Attribute(AttributeConstants.AURA_EFFECT, -50.0));
        AM.setProjectileFactory(new ProjectileFactory());
        return AM;
    }

}
