package vooga.towerdefense.factories.examples;

import java.awt.Dimension;
import java.util.ArrayList;
import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.FindTargets;
import vooga.towerdefense.action.LaunchProjectile;
import vooga.towerdefense.action.MakeElement;
import vooga.towerdefense.action.Move;
import vooga.towerdefense.action.RandomChance;
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
public class MergeConflictTower extends GameElementFactory {



    /**
     * @param name
     * @param def
     */
    public MergeConflictTower (GameMap map, String name, TowerDefinition def) {
        super(name, def);
        initialize(map);
    }

    @Override
    public GameElement createElement (Location putHere) {
        TowerDefinition def = new TowerDefinition();
        AttributeManager AM = getDefaultAM();

        Pixmap tImage = new Pixmap("github.png");
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
                new FindTargets(getMap(), myTower.getCenter(), AM.getAttribute(AttributeConstants.ATTACK_RADIUS));
        Action randomFiring = new RandomChance(0.01);
        ExampleDosProjectileFactory coolStoryBro = new ExampleDosProjectileFactory();
        coolStoryBro.initialize(getMap());
        //Action launchProjectile = new MakeElement(getMap(), myTower.getCenter(), new ExampleDosProjectileFactory());
        Action launchProjectile = new LaunchProjectile(getMap(), myTower, new ExampleDosProjectileFactory(), myTower);
        findTargets.addFollowUpAction(launchProjectile);
        randomFiring.addFollowUpAction(findTargets);
        actions.add(randomFiring);
        //actions.add(launchProjectile);

        myTower.addActions(actions);
        return myTower;
    }
    


    public AttributeManager getDefaultAM () {
        AttributeManager AM = new AttributeManager();
        AM.addAttribute(new Attribute(AttributeConstants.ATTACK_RADIUS, 200.0));
        AM.addAttribute(new Attribute(AttributeConstants.DIRECTION, 50.0));
        AM.addAttribute(new Attribute(AttributeConstants.ATTACK_INTERVAL, 30.0));
        AM.addAttribute(new Attribute(AttributeConstants.NUM_OF_TARGETS, 1.0));
        AM.addAttribute(new Attribute(AttributeConstants.ATTACK_RADIUS, 200.0));
        AM.addAttribute(new Attribute(AttributeConstants.MOVE_SPEED, 10.0));
        AM.addAttribute(new Attribute(AttributeConstants.AURA_EFFECT, 5.0));
        AM.setProjectileFactory(new ProjectileFactory());
        return AM;
    }

    
    
}
