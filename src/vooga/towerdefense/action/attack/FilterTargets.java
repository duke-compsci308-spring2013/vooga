package vooga.towerdefense.action.attack;

import java.util.ArrayList;
import java.util.List;

import vooga.towerdefense.action.TargetedAction;
import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.attributes.AttributeConstantsEnum;
import vooga.towerdefense.gameelements.GameElement;

/**
 * Filters targets using an affiliation system; only retain targets with
 * corresponding targetAffiliationID.
 * 
 * 
 * @author Matthew Roy
 * @author XuRui
 * 
 */
public class FilterTargets extends TargetedAction {

	Attribute myTargetAffiliation;
	Attribute myNumTargets;

	public FilterTargets(Attribute targetAffiliation) {
		super();
		myTargetAffiliation = targetAffiliation;
	}

	/**
	 * Returns a specific number of targets.
	 * 
	 * @param targetAffiliation
	 * @param numTargets
	 */
	public FilterTargets(Attribute targetAffiliation, Attribute numTargets) {
		super();
		myTargetAffiliation = targetAffiliation;
		myNumTargets = numTargets;
	}

	/**
	 * Filters targets and update the target list of all follow up actions.
	 * 
	 * @param elapsedTime
	 */
	@Override
	public void executeAction(double elapsedTime) {
		List<GameElement> filteredTargets = new ArrayList<GameElement>();
		System.out.printf("filter got %d targets\n", getTargets().size());
		for (int i = 0; i < getTargets().size(); i++) {
			GameElement e = getTargets().get(i);
			Attribute affiliation = e.getAttributeManager().getAttribute(
					AttributeConstantsEnum.AFFILIATION.getStatusCode());
			if (affiliation != null
					&& affiliation.getValue() == myTargetAffiliation.getValue()) {
				System.out.printf("my target affiliation is %s\n", myTargetAffiliation.getValue());
				filteredTargets.add(e);
			if (myNumTargets != null
					&& myNumTargets.getValue() == myTargetAffiliation.getValue()) {
				break;
				}
			}
		}
		updateTargetedFollowUpActions(filteredTargets);
		System.out.printf("my filtered targets are %s\n", filteredTargets.size());
	}
}
