package vooga.towerdefense.action;

import java.util.ArrayList;
import java.util.List;
import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.gameElements.GameElement;


/**
 * 
 * @author Matthew Roy
 * 
 */
public class ModifyAttributeValue extends Action {

    private Attribute myAppliedAttribute;
    private String myTargetAttribute;
    List<GameElement> myTargets;

    public ModifyAttributeValue (Attribute attributeToApply, String targetAttributeName) {
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
            System.out.println(e.getCenter());
            Attribute toChange = e.getAttributeManager().getAttribute(myTargetAttribute);
            System.out.println(toChange);
            if (toChange != null) {
                toChange.modifyValue(myAppliedAttribute.getValue());
            }
        }
    }

    @Override
    public void update (double elapsedTime) {
        // TODO Auto-generated method stub

    }

}
