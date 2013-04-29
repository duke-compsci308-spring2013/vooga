package games.fighter.davidalan.controller.levels;

import java.util.Map;

import games.fighter.davidalan.util.CollisionDetector;


import util.input.*;
import vooga.fighter.controller.Controller;
import vooga.fighter.controller.gameinformation.GameInfo;
import vooga.fighter.controller.interfaces.ControllerDelegate;

import vooga.fighter.model.ModelConstants;
import vooga.fighter.model.objects.AttackObject;
import vooga.fighter.model.objects.CharacterObject;
import vooga.fighter.model.objects.EnvironmentObject;
import vooga.fighter.model.objects.GameObject;
import vooga.fighter.view.Canvas;
import vooga.fighter.view.FourPlayerMatchGameLayout;
import vooga.fighter.controller.levels.OneVOneController;

@InputClassTarget
public class StreetFighterOneVOneController extends OneVOneController{
    
	private CollisionDetector myDetector; 
	
	public StreetFighterOneVOneController(){
		super();
	}
	
	public StreetFighterOneVOneController(String name, Canvas frame, ControllerDelegate manager, 
    		GameInfo gameinfo, String filepath) {
    	super(name, frame, manager, gameinfo, filepath);
    	myDetector= new CollisionDetector(); 
    }


    /**
     * Return concrete controller
     */
    @Override
	public Controller getController(String name, Canvas frame, ControllerDelegate manager, GameInfo gameinfo,
                                    String filepath) {
        Controller controller = new StreetFighterOneVOneController(name, frame, manager, gameinfo, filepath);
        return controller;
    }


    /**
     * Details movement inputs
     * @param alObj
     */
    @InputMethodTarget(name = "player1_jump")
    public void playerOneJumpInput (AlertObject alObj)  {
    	CharacterObject character=getInputObjects().get(0); 
    	for (GameObject object: getMode().getMyObjects()){
    		if (object instanceof EnvironmentObject){
	    		if (myDetector.hitTop(character.getCurrentState().getCurrentRectangle(), object.getCurrentState().getCurrentRectangle())){
	    		       	character.jump();
	    		}
    		}
    	}
    }


    @InputMethodTarget(name = "player2_jump")
    public void playerTwoJumpInput (AlertObject alObj)  {
    	CharacterObject character=getInputObjects().get(1); 
    	for (GameObject object: getMode().getMyObjects()){
    		if (object instanceof EnvironmentObject){
	    		if (myDetector.hitTop(character.getCurrentState().getCurrentRectangle(), object.getCurrentState().getCurrentRectangle())){
	    		        character.jump();
	    		 }
    		}
    	}
    }



    @InputMethodTarget(name = "player1_weakPunch")
    public void playerOneWeakPunchInput(AlertObject alObj) {
        AttackObject newAttack = getInputObjects().get(0).attack("weakPunch");
        getMode().addObject(newAttack);
    }
    
    @InputMethodTarget(name = "player1_medPunch")
    public void playerOneMedPunchInput(AlertObject alObj) {
        AttackObject newAttack = getInputObjects().get(0).attack("medPunch");
        getMode().addObject(newAttack);
    }
    
    @InputMethodTarget(name = "player1_weakKick")
    public void playerOneWeakKickInput(AlertObject alObj) {
        AttackObject newAttack = getInputObjects().get(0).attack("weakKick");
        getMode().addObject(newAttack);
    }
    
    @InputMethodTarget(name = "player1_medKick")
    public void playerOneMedKickInput(AlertObject alObj) {
        AttackObject newAttack = getInputObjects().get(0).attack("medKick");
        getMode().addObject(newAttack);
    }
    
    @InputMethodTarget(name = "player1_hadouken")
    public void playerOneHadoukenInput(AlertObject alObj) {
        AttackObject newAttack = getInputObjects().get(0).attack("hadouken");
        getMode().addObject(newAttack);
    }

    @InputMethodTarget(name = "player2_attack")
    public void playerTwoAttacknput(AlertObject alObj) {
    	   AttackObject newAttack = getInputObjects().get(1).attack("weakPunch");
           getMode().addObject(newAttack);
    }

    @InputMethodTarget(name = "player1_left")
    public void playerOneLeftInput (AlertObject alObj) {
    	CharacterObject character= getInputObjects().get(0);
    	changeAttackDirections(character, 180);
    	character.move(180);
    }

    @InputMethodTarget(name = "player1_right")
    public void playerOneRightInput(AlertObject alObj) {
    	CharacterObject character= getInputObjects().get(0);
    	changeAttackDirections(character, 0);
    	character.move(0);

    }
    
    @InputMethodTarget(name = "player2_left")
    public void playerTwoLeftInput (AlertObject alObj) {
    	CharacterObject character= getInputObjects().get(1);
    	changeAttackDirections(character, 180);
    	character.move(180);
    }

    @InputMethodTarget(name = "player2_right")
    public void playerTwoRightInput (AlertObject alObj) {
    	CharacterObject character= getInputObjects().get(1);
    	changeAttackDirections(character, 0);
    	character.move(0);
    }


    public void changeAttackDirections(CharacterObject character, int direction){
    	for (Map.Entry<String,AttackObject> entry: character.getAttackMapping().entrySet()){
    		entry.getValue().addProperty(ModelConstants.ATTACK_PROPERTY_DIRECTION, direction);
    	}
    }


}
