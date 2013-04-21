package vooga.towerdefense.action;

import java.util.ArrayList;
import java.util.List;
import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.gameElements.GameElement;


/**
 * Modify a specified 
 * @author Matthew Roy
 * @author Zhen Gou
 */
public class ModifyAttributeValue extends Action {

    private Attribute myAppliedAttribute;
    private String myTargetAttribute;

    public ModifyAttributeValue (Attribute attributeToApply, String targetAttributeName) {
        myTargetAttribute = targetAttributeName;
        myAppliedAttribute = attributeToApply;
 
    }
    
    @Override
    public void executeAction (double elapseTime) {
        for (GameElement e : getTargets()) {
            System.out.println(e.getCenter());
            Attribute toChange = e.getAttributeManager().getAttribute(myTargetAttribute);
            System.out.println(toChange);
            if (toChange != null) {
                toChange.modifyValue(myAppliedAttribute.getValue());
            }
        }
    }


}
