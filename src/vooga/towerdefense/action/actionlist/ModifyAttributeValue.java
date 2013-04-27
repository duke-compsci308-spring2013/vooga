package vooga.towerdefense.action.actionlist;

import vooga.towerdefense.action.TargetedAction;
import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.gameelements.GameElement;


/**
 * Modify a specified attribute value. 
 * 
 * @author Matthew Roy
 * @author Zhen Gou
 */
public class ModifyAttributeValue extends TargetedAction {

    private Attribute myAppliedAttribute;
    private String myTargetAttribute;

    public ModifyAttributeValue (Attribute attributeToApply, String targetAttributeName) {
        super();
        myTargetAttribute = targetAttributeName;
        myAppliedAttribute = attributeToApply;
 
    }


    /**
     * Scales the modified value to the elapsed time and applies it to all the given elements
     * Overrides from superclasses
     * @param elapsedTime
     */
    public void executeAction (double elapsedTime) {
        double scaledModifier = myAppliedAttribute.getValue()*elapsedTime/1000.0;
        for (GameElement e : getTargets()) {
            Attribute toChange = e.getAttributeManager().getAttribute(myTargetAttribute);
            if (toChange != null) {
                toChange.modifyValue(scaledModifier);
            }
        }
    }


}
