package vooga.towerdefense.factories.examples;

import java.awt.Dimension;
import java.util.ArrayList;
import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.FindTargets;
import vooga.towerdefense.action.LaunchProjectile;
import vooga.towerdefense.action.ModifyAttributeValue;
import vooga.towerdefense.attributes.AttributeConstants;
import vooga.towerdefense.attributes.AttributeManager;
import vooga.towerdefense.factories.TowerDefinition;
import vooga.towerdefense.factories.TowerFactory;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.util.Location;
import vooga.towerdefense.util.Pixmap;


public class ExampleShootingTowerFactory extends TowerFactory {

    GameMap myMap;

    /**
     * @param name
     * @param def
     */
    public ExampleShootingTowerFactory (GameMap map, String name, TowerDefinition def) {
        super(name, def);
        myMap = map;
    }

    public GameElement createTower (Location putHere) {
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
                new FindTargets(myMap, putHere, AM.getAttribute(AttributeConstants.ATTACK_RADIUS));
        // findTargets.addFollowUpAction(LaunchProjectile(putHere, new ExampleProjectileFactory(), ,
        // myMap)));
        actions.add(findTargets);

        myTower.addActions(actions);
        return myTower;
    }
}
