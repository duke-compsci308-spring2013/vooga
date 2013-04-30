package vooga.fighter.model.effects;

import vooga.fighter.model.ModelConstants;
import vooga.fighter.model.objects.CharacterObject;
import vooga.fighter.model.utils.Effect;

public class FreezeEffect extends Effect{
	public FreezeEffect(){
		super();
	}
	
	public FreezeEffect(CharacterObject owner, int duration){
		super (owner, duration);
	}

	@Override
	public void applyEffect() {
		// TODO Auto-generated method stub
		getOwner().setCurrentState("Frozen");
		getOwner().getVelocity().setMagnitude(0);
		
	}

	@Override
	public Effect getCloneOfEffect() {
		// TODO Auto-generated method stub
		return new FreezeEffect(getOwner(), getProperty(ModelConstants.DURATION_PROPERTY));
	}
		
}
