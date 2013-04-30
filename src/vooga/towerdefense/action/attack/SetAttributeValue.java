package vooga.towerdefense.action.attack;

import java.util.ArrayList;
import java.util.List;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.gameelements.GameElement;


/**
 * This action sets the attribute value to a new attribute value.
 * Not applicable for FactoryAttributes (see ModifyFactoryAttribute)
 * 
 * @author Matthew Roy
 * 
 */
public class SetAttributeValue extends Action {

    private Attribute myAppliedAttribute;
    private String myTargetAttribute;
    List<GameElement> myTargets;

    public SetAttributeValue (Attribute attributeToApply, String targetAttributeName) {
        myTargetAttribute = targetAttributeName;
        myAppliedAttribute = attributeToApply;
        myTargets = new ArrayList<GameElement>();
    }
    
    
    public void setTargets(List<GameElement> elements) {
        myTargets = elements;
    }

    @Override
    public void executeAction (double elapseTime) {
        for (GameElement e : myTargets) {
            Attribute toChange = e.getAttributeManager().getAttribute(myTargetAttribute);
            if (toChange != null) {
                toChange.setValue(myAppliedAttribute.getValue());
            }
        }
    }
}

