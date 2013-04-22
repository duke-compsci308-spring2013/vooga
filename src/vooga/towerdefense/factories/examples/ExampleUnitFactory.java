package vooga.towerdefense.factories.examples;

import java.util.ArrayList;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.FollowPath;
import vooga.towerdefense.action.Move;
import vooga.towerdefense.action.OnDeath;
import vooga.towerdefense.action.RemoveGameElement;
import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.attributes.AttributeConstants;
import vooga.towerdefense.attributes.AttributeManager;
import vooga.towerdefense.factories.GameElementDefinition;
import vooga.towerdefense.factories.GameElementFactory;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.model.Path;
import vooga.towerdefense.util.Location;


/**
 * @author Matthew Roy
 * @author Jimmy Longley
 * 
 */
public class ExampleUnitFactory extends GameElementFactory {

    private GameMap myGameMap;

    /**
     * @param elementName
     * @param definition
     */
    public ExampleUnitFactory (String elementName,
                               GameElementDefinition definition, GameMap gameMap) {
        super(elementName, definition);
        myGameMap = gameMap;
    }

    public GameElement createElement (Location putHere) {
        return createElement(putHere, new TrollUnitDefinition());
    }

    public GameElement createElement (Location putHere, TrollUnitDefinition myDef) {
        TrollUnitDefinition myDefinition = myDef;

        AttributeManager AM = new AttributeManager();
        AM.addAttribute(new Attribute(AttributeConstants.MOVE_SPEED, 150.0));
        AM.addAttribute(new Attribute(AttributeConstants.DIRECTION, 50.0));
        AM.addAttribute(new Attribute(AttributeConstants.ATTACK_INTERVAL, 50.0));
        AM.addAttribute(new Attribute(AttributeConstants.HEALTH, 100.0));
        GameElement myGameElement;
        if (putHere != null) {
            myGameElement = new GameElement(myDefinition.getImage(), putHere,
                                            myDefinition.getSize(), AM);
        }
        else {
            myGameElement = new GameElement(myDefinition.getImage(),
                                            myDefinition.getCenter(), myDefinition.getSize(), AM);
        }

        Path path = myGameMap.getShortestPath(putHere,
                                              myGameMap.default_end_location);
        ArrayList<Action> actions = new ArrayList<Action>();
        actions.add(new FollowPath(myGameElement, path));
        actions.add(new Move(myGameElement.getCenter(), myGameElement.getAttributeManager()
                .getAttribute(AttributeConstants.MOVE_SPEED), myGameElement
                .getAttributeManager().getAttribute(
                                                    AttributeConstants.DIRECTION)));

        Action myDeath = new OnDeath(AM.getAttribute(AttributeConstants.HEALTH));
        myDeath.addFollowUpAction(new RemoveGameElement(myGameMap, myGameElement));
        actions.add(myDeath);
        myGameElement.addActions(actions);
        return myGameElement;
    }

}
