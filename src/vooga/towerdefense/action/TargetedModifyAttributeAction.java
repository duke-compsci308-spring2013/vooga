package vooga.towerdefense.action;

import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.attributes.Attribute;
/**
 * 
 * Finds the attribute in the target object and sets it to the attribute given
 * If the target doesn't have the attribute, it places the new attribute in the target
 * @author Matthew Roy
 *
 */
public class TargetedModifyAttributeAction extends TargetedAction {

    GameElement myTarget;
    Attribute myTargetAttribute;
    
    /**
     * @param initiator 
     */
    public TargetedModifyAttributeAction (GameElement initiator, GameElement target, Attribute toModify) {
        super(initiator, target);
        myTarget = target;
        myTargetAttribute = toModify;
    }

   
    @Override
    public void initAction () {
        Attribute targetsAttribute = myTarget.getAttributeManager().getAttribute(myTargetAttribute.getName());
        if(targetsAttribute != null) {
            targetsAttribute.setValue(myTargetAttribute.getValue());
        }
        else {
            myTarget.getAttributeManager().addAttribute(myTargetAttribute);
        }
    }

    
    @Override
    public void execute (double elapsedTime) {
        // TODO Auto-generated method stub

    }

}
