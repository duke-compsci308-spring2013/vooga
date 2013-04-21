package vooga.towerdefense.action;

import java.util.ArrayList;
import java.util.List;

import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.attributes.AttributeConstants;
import vooga.towerdefense.factories.ProjectileFactory;
import vooga.towerdefense.factories.examples.ExampleProjectileFactory;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.util.Location;
import vooga.towerdefense.util.Vector;

public class TrackTarget extends Action{
	
	private Attribute myScanningRadius;
	private List<GameElement> myTargets;
	private Location mySource;
	private GameMap myMap;
	

	public TrackTarget(Location source, Attribute attackRadius, GameMap myMap){
		myScanningRadius = new Attribute("a",300.0);
		mySource = source;
		myTargets=new ArrayList<GameElement>();
		this.setEnabled(true);
		//hard coded to add in CreateProjectile action
	}

	public void update(double elapsedTime) {
		
		if (isEnabled()){
			myTargets=myMap.getTargetsWithinRadius(mySource, myScanningRadius.getValue(), 1);
			executeAction(elapsedTime);
		}

	}

	@Override
	public void executeAction(double elapsedTime) {
		System.out.print("track action executed");
		if(!myTargets.isEmpty()){
			for (GameElement t: myTargets){
				addFollowUpAction(new LaunchProjectile(mySource, new ExampleProjectileFactory(), t, myMap));
				for (Action a: getFollowUpActions()){
					a.update(elapsedTime);
				}
			}
		}

	}
	

}
