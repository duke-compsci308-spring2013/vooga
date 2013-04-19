package vooga.fighter.controller;



import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

import util.Location;
import util.input.*;
import vooga.fighter.model.LevelMode;
import vooga.fighter.model.Mode;
import vooga.fighter.model.objects.CharacterObject;
import vooga.fighter.model.LevelMode;
import vooga.fighter.model.Mode;
import vooga.fighter.model.objects.CharacterObject;
import vooga.fighter.util.Paintable;
import vooga.fighter.view.Canvas;


/**
 * 
 * @author Jerry Li
 * 
 * @Modified by Jack Matteucci
 * 
 */
@InputClassTarget
public class OneVOneController extends LevelController {
    private static final String INPUT_PATHWAY = "vooga.fighter.config.leveldefault";
    private List<CharacterObject> myInputObjects;

    public OneVOneController (String name, Canvas frame) {
        super(name, frame);
        
    }
	
    public OneVOneController(String name, Canvas frame, ControllerDelegate manager, 
    		GameInfo gameinfo) {
    	super(name, frame, manager, gameinfo);
    }

    @Override
    public Controller getController (ControllerDelegate delegate, GameInfo gameinfo) {
        return new OneVOneController(super.getName(), super.getView(),
                                   delegate, gameinfo);
    }

    @Override
    public void notifyEndCondition (String endCondition) {
    	removeListener();
    	getManager().notifyEndCondition(NEXT);
    }
    
    @InputMethodTarget(name = "player1_jump")
    public void playerOneJumpInput (AlertObject alObj)  {
        getInputObjects().get(0).move(270);
    }
    
    @InputMethodTarget(name = "player1_left")
    public void playerOneLeftInput (AlertObject alObj) {
        getInputObjects().get(0).move(180);
        
    }
    
    @InputMethodTarget(name = "player1_right")
    public void playerOneRightInput(AlertObject alObj) {
        getInputObjects().get(0).move(0);
        
    }
    
    @InputMethodTarget(name = "player1_down")
    public void playerOneDownInput(AlertObject alObj) {
        getInputObjects().get(0).move(90);
        
    }
    
    @InputMethodTarget(name = "player2_jump")
    public void playerTwoJumpInput (AlertObject alObj)  {
        getInputObjects().get(1).move(270);
    }
    
    @InputMethodTarget(name = "player2_left")
    public void playerTwoLeftInput (AlertObject alObj) {
        getInputObjects().get(1).move(180);
       
    }
    
    @InputMethodTarget(name = "player2_right")
    public void playerTwoRightInput(AlertObject alObj) {
        getInputObjects().get(1).move(0);
        
    }
    
    @InputMethodTarget(name = "player2_down")
    public void playerTwoDownInput(AlertObject alObj) {
        getInputObjects().get(1).move(90);
        
    }
    
    @InputMethodTarget(name = "player1_attack")
    public void playerOneAttackInput(AlertObject alObj) {
        getInputObjects().get(0).attack("weakPunch");
    }
    
    @InputMethodTarget(name = "player2_attack")
    public void playerTwoAttacknput(AlertObject alObj) {
    	getInputObjects().get(1).attack("weakPunch");
    }
    
    public void removeListener(){
    	super.removeListener();
    	getInput().removeListener(this);
    }
    
    
}