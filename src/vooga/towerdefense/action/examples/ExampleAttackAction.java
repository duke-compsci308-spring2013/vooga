package vooga.towerdefense.action.examples;

import java.util.List;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.factories.GameElementFactory;
import vooga.towerdefense.gameElements.GameElement;

public class ExampleAttackAction extends Action{
	
	private GameElement myInitiator;
	private List<Action> myActions;
	
	public ExampleAttackAction(GameElement initiator, List<Action> actionSeries){
		myInitiator = initiator;
		myActions = actionSeries;
		
	}
	
	
	@Override
	public void executeAction(double elapseTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(double elapsedTime) {
		for (Action a: myActions){
			a.update(elapsedTime);
		}
		
	}
	
	

}
