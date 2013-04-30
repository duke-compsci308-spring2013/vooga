package vooga.towerdefense.factories.waveactionfactories;

import java.util.Map;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.waveactions.WaveAction;
import vooga.towerdefense.factories.ActionAnnotation;
import vooga.towerdefense.factories.actionfactories.ActionFactory;
import vooga.towerdefense.factories.elementfactories.GameElementFactory;
import vooga.towerdefense.gameelements.GameElement;

/**
 * This action factory builds a wave action.
 * 
 * @author Zhen Gou
 *
 */
public class WaveActionFactory extends ActionFactory {

	private int myNumUnits;
	private int myCooldown;
	private GameElementFactory myFactory;
	public static Map<String, GameElementFactory> ourGEFactories; 
	
	public WaveActionFactory(@ActionAnnotation(name = "number of units", value = "int") String numUnits,
	                         @ActionAnnotation(name = "cooldown", value = "int") String cooldown,
	                         @ActionAnnotation(name = "unit to spawn", value = "name of unit") String factory) {
		myNumUnits = Integer.parseInt(numUnits);
		myCooldown = Integer.parseInt(cooldown);
		myFactory = ourGEFactories.get(factory);		
	}
	
	@Override
	protected Action buildAction(GameElement e) {
		return new WaveAction(myNumUnits, myCooldown, myFactory, getMap());
	}
}
