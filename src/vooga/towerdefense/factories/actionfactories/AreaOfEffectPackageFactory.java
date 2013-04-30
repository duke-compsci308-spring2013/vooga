package vooga.towerdefense.factories.actionfactories;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.factories.ActionAnnotation;
import vooga.towerdefense.gameelements.GameElement;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.model.Player;

/**
 * Factory creates an action combo that does an area of effect, for speeding up game creation.
 * 
 * @author Zhen Gou
 * 
 */
public class AreaOfEffectPackageFactory extends ActionFactory {
	private String myRadius;
	private String myNumberOfTargets;
	private String myTargets;
	private String myAttributeToApply;
	private String myTargetAttribute;

	private FindTargetsFactory myFinderFactory;
	private FilterTargetsFactory myFilterFactory;
	private ApplyAttributeBuffFactory myBuffFactory;
/**
 * 
 * @param attackRadius
 * @param numTargets
 * @param targetAffiliation
 * @param attributeToApply
 * @param targetAttribute
 */
	public AreaOfEffectPackageFactory(
			@ActionAnnotation(name = "attack radius", value = "attribute") String attackRadius,
			@ActionAnnotation(name = "num targets", value = "attribute") String numTargets,
			@ActionAnnotation(name = "target affiliation", value = "attribute") String targetAffiliation,
			@ActionAnnotation(name = "attribute to apply", value = "attribute") String attributeToApply,
			@ActionAnnotation(name = "target attribute", value = "attribute") String targetAttribute) {
		super();
		myRadius = attackRadius;
		myNumberOfTargets = numTargets;
		myTargets = targetAffiliation;
		myAttributeToApply = attributeToApply;
		myTargetAttribute = targetAttribute;

	}

	/**
	 * need to be called before creating actions
	 */
	public void initialize(GameMap map, Player player) {
		super.initialize(map, player);
		makeComboFactories();
		myFinderFactory.initialize(map, player);
		myFilterFactory.initialize(map, player);
		myBuffFactory.initialize(map, player);

	}

	private void makeComboFactories() {
		myFinderFactory = new FindTargetsFactory(myRadius);
		myFilterFactory = new FilterTargetsFactory(myTargets, myNumberOfTargets);
		myBuffFactory = new ApplyAttributeBuffFactory(myAttributeToApply,
				myTargetAttribute);
	}

	/**
	 * creates the desired action
	 */

	@Override
	public Action buildAction(GameElement e) {
		Action aoe = myFinderFactory.buildAction(e);
		Action filter = myFilterFactory.createAction(e);
		Action buff = myBuffFactory.createAction(e);
		aoe.addFollowUpAction(filter);
		aoe.addFollowUpAction(buff);
		return aoe;
	}

}
