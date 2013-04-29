package vooga.fighter.model.effects;

import vooga.fighter.model.utils.Effect;

public class KnockdownEffect extends Effect {

	
	public KnockdownEffect(){
		super();
	}
	
	@Override
	public void applyEffect() {
		// TODO Auto-generated method stub
		getOwner().setCurrentState("down");
		getOwner().getVelocity().setMagnitude(0);
	}

	@Override
	public Effect getCloneOfEffect() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
