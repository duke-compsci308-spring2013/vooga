package vooga.towerdefense.factories.examples;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.FindTargets;
import vooga.towerdefense.action.ModifyAttributeValue;
import vooga.towerdefense.action.Move;
import vooga.towerdefense.action.MoveToDestination;
import vooga.towerdefense.action.RandomChance;
import vooga.towerdefense.action.RemoveGameElement;
import vooga.towerdefense.action.SetAttributeValue;
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
public class ExampleDosProjectileFactory extends GameElementFactory {

    private static final int DEFAULT_WIDTH = 10;
    private static final Pixmap DEFAULT_IMAGE = new Pixmap("fireball.gif");
    private static final Dimension DEFAULT_SIZE = new Dimension(DEFAULT_WIDTH, DEFAULT_WIDTH);
    private static final List<Action> DEFAULT_ACTIONS = new ArrayList<Action>();
    private static final AttributeManager DEFAULT_ATTRIBUTE_MANAGER = new AttributeManager();

    public GameElement createElement (Location putHere) {
        TowerDefinition def = new TowerDefinition();
        AttributeManager AM = getDefaultAM();

        Pixmap tImage = new Pixmap("tower.gif");
        GameElement myTower;
        if (putHere != null) {
            myTower = new GameElement(tImage, putHere,
                                      new Dimension(100, 100), AM);
        }
        else {
            myTower = new GameElement(def.getImage(),
                                      def.getCenter(), def.getSize(), AM);
        }

        ArrayList<Action> actions = new ArrayList<Action>();
        FindTargets findTargets =
                new FindTargets(getMap(), putHere,
                                AM.getAttribute(AttributeConstants.ATTACK_RADIUS));
        findTargets.addFollowUpAction(new SetAttributeValue(AM
                .getAttribute(AttributeConstants.AURA_EFFECT), AttributeConstants.MOVE_SPEED));
        actions.add(findTargets);

        myTower.addActions(actions);
        return myTower;
    }

    public AttributeManager getDefaultAM () {
        AttributeManager AM = new AttributeManager();
        AM.addAttribute(new Attribute(AttributeConstants.ATTACK_RADIUS, 100.0));
        AM.addAttribute(new Attribute(AttributeConstants.DIRECTION, -50.0));
        AM.addAttribute(new Attribute(AttributeConstants.ATTACK_INTERVAL, 30.0));
        AM.addAttribute(new Attribute(AttributeConstants.NUM_OF_TARGETS, 1.0));
        AM.addAttribute(new Attribute(AttributeConstants.ATTACK_RADIUS, 200.0));
        AM.addAttribute(new Attribute(AttributeConstants.MOVE_SPEED, 50.0));
        AM.addAttribute(new Attribute(AttributeConstants.AURA_EFFECT, 5.0));
        AM.setProjectileFactory(new ProjectileFactory());
        return AM;
    }

    /**
     * @param myStart
     * @param center
     * @return
     */
    public GameElement createElement (GameElement myStart, GameElement e, GameMap map) {
        TowerDefinition def = new TowerDefinition();
        AttributeManager AM = getDefaultAM();
        initialize(map);

        Pixmap tImage = new Pixmap("mergeConflict.jpg");
        GameElement myProjectile;
        if (myStart != null) {
            myProjectile = new GameElement(tImage, myStart.getCenter(),
                                           new Dimension(30, 30), AM);
        }
        else {
            myProjectile = new GameElement(def.getImage(),
                                           def.getCenter(), def.getSize(), AM);
        }

        ArrayList<Action> actions = new ArrayList<Action>();
        FindTargets findTargets =
                new FindTargets(map, myStart.getCenter(), AM.getAttribute(AttributeConstants.ATTACK_RADIUS));
        findTargets.addFollowUpAction(new SetAttributeValue(AM
                .getAttribute(AttributeConstants.AURA_EFFECT), AttributeConstants.HEALTH));
        actions.add(findTargets);
        //Action randomChance = new RandomChance(-0.01);
        //randomChance.addFollowUpAction(new RemoveGameElement(map, myProjectile));
        //actions.add(randomChance);
        //actions.add(new Move(myProjectile.getCenter(), AM
        //        .getAttribute(AttributeConstants.MOVE_SPEED), AM
        //        .getAttribute(AttributeConstants.DIRECTION)));
        actions.add(new MoveToDestination(myProjectile.getCenter(), e.getCenter(), AM
                .getAttribute(AttributeConstants.MOVE_SPEED)));

        myProjectile.addActions(actions);
        return myProjectile;
    }

    /**
     * @param myStart
     * @param myTarget
     * @return 
     */
    public GameElement createProjectile (GameElement myStart, GameElement myTarget) {
        return this.createElement(myStart, myTarget, getMap());
    }

}
