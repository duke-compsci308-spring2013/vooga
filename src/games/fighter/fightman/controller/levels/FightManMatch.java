package games.fighter.fightman.controller.levels;

import java.util.ArrayList;

import games.fighter.fightman.FightManRunAlone;
import games.fighter.fightman.view.MatchDisplayInfo;
import games.fighter.fightman.view.MatchLayout;
import util.Vector;
import util.input.AlertObject;
import util.input.InputMethodTarget;
import vooga.fighter.controller.Controller;
import vooga.fighter.controller.gameinformation.GameInfo;
import vooga.fighter.controller.interfaces.ControllerDelegate;
import vooga.fighter.controller.interfaces.ModeCondition;
import vooga.fighter.model.mode.LevelMode;
import vooga.fighter.model.mode.Mode;
import vooga.fighter.model.objects.AttackObject;
import vooga.fighter.model.objects.CharacterObject;
import vooga.fighter.util.CollisionManager;
import vooga.fighter.util.Physics;
import vooga.fighter.view.Canvas;

public class FightManMatch extends LevelController {
    //Is player 1 facing right
    private boolean playerFacingRight[] = {true, false};
    
    /**
     * Player 1 wins when Player 2 is at 0 or less health
     */
    private ModeCondition P1WinCon = new ModeCondition() {
        @Override
        public boolean checkCondition(Mode mode) {
            if (getInputObjects().get(1).getHealth().getHealth() <= 0) {
                FightManRunAlone.setScore(FightManRunAlone.getScore() + 1);
                FightManMenu.p1Score++;
                FightManMenu.titleText = "Player 1 Wins!";
                return true;
            }
            return false;
        }
    };
    
    /**
     * Player 2 wins when Player 1 is at 0 or less health
     */
    private ModeCondition P2WinCon = new ModeCondition() {
        @Override
        public boolean checkCondition(Mode mode) {
            if (getInputObjects().get(0).getHealth().getHealth() <= 0) {
                FightManMenu.p2Score++;
                FightManMenu.titleText = "Player 2 Wins!";
                return true;
            }
            return false;
        }
    };
    
    /**
     * Default constructor for reflection purposes.
     */
    public FightManMatch () {}
    
    public FightManMatch(String name, Canvas frame, ControllerDelegate manager,
            GameInfo gameinfo, String filePath) {
        super(name, frame, manager, gameinfo, filePath);
        MatchDisplayInfo gameLoopInfo = new MatchDisplayInfo(getMode());
        setLoopInfo(gameLoopInfo);
        gameinfo.setGameLoopInfo(gameLoopInfo);
        gameinfo.addCharacters("Red");
        gameinfo.addCharacters("Blue");
        gameinfo.setMapName("OnlyMap");
        frame.setLayout(new MatchLayout());
    }

    @Override
    public Controller getController(String name, Canvas frame,
            ControllerDelegate manager, GameInfo gameinfo, String filePath) {
        return new FightManMatch(name, frame, manager, gameinfo, filePath);
    }
    
    @Override
    protected void notifyEndCondition(String choice) {
        removeListener();
        getGameInfo().setCharacters(new ArrayList<String>());
        getManager().notifyEndCondition("FightManMenu");
    }
    
    @InputMethodTarget(name = "player1_backhop")
    public void playerOneBackHop (AlertObject alObj)  {
        if (isStanding(0)) {
            applyForce(0, 25, -40);
            
            getInputObjects().get(0).setCurrentState("backhop");
        }
    }

    @InputMethodTarget(name = "player1_dive")
    public void playerOneDive (AlertObject alObj) {
        if (isStanding(0)) {
            applyForce(0, 50, -40);
            
            AttackObject newAttack = getInputObjects().get(0).attack("dive");
            getMode().addObject(newAttack);
            getInputObjects().get(0).setCurrentState("dive");
        }
    }

    @InputMethodTarget(name = "player1_kick")
    public void playerOneRightInput(AlertObject alObj) {
        if (isStanding(0)) {
            applyForce(0, 60, 0);
            
            AttackObject newAttack = getInputObjects().get(0).attack("dive");
            getMode().addObject(newAttack);
            getInputObjects().get(0).setCurrentState("dive");
        }
    }

    @InputMethodTarget(name = "player2_backhop")
    public void playerTwoJumpInput (AlertObject alObj)  {
        if (isStanding(1)) {
            applyForce(1, 25, -40);
            
            getInputObjects().get(1).setCurrentState("backhop");
        }
    }

    @InputMethodTarget(name = "player2_dive")
    public void playerTwoLeftInput (AlertObject alObj) {
        if (isStanding(1)) {
            applyForce(1, 50, -40);
            
            AttackObject newAttack = getInputObjects().get(1).attack("dive");
            getMode().addObject(newAttack);
            getInputObjects().get(1).setCurrentState("dive");
        }
    }

    @InputMethodTarget(name = "player2_kick")
    public void playerTwoRightInput(AlertObject alObj) {
        if (isStanding(1)) {
            applyForce(1, 60, 0);
            
            AttackObject newAttack = getInputObjects().get(1).attack("dive");
            getMode().addObject(newAttack);
            getInputObjects().get(1).setCurrentState("dive");
        }
    }
    
    /**
     * Applies a force in the direction the player is facing.
     * @param pNum The player index
     * @param dx The x impulse.
     * @param dy The y impulse.
     */
    private void applyForce(int pNum, int dx, int dy) {
        CharacterObject player = getInputObjects().get(pNum);
        int direction = playerFacingRight[pNum] ? 1 : -1;
        Vector force = new Vector();
        force.setVectorByComponent(dx * direction, dy);
        Vector newVelocity = Physics.applyForce(player.getVelocity(), player.getMass(), force);
        
        player.getVelocity().setVectorByComponent(newVelocity.getXChange(), newVelocity.getYChange());
    }
    
    /**
     * Returns if the given player is in the standing state.
     * @param player The player index.
     * @return true if the player is standing.
     */
    private boolean isStanding(int player) {
        return getInputObjects().get(player).getCurrentStateKey().equals("stand");
    }
    
    /**
     * Removes listener
     */
    @Override
    public void removeListener(){
        super.removeListener();
        getInput().removeListener(this);
    }
    
    @Override
    public void setupConditions(){
        addWinCondition(P1WinCon);
        addWinCondition(P2WinCon);
    }
    
    @Override
    public void loadMode() {
        LevelMode temp = new FightManMode(new CollisionManager());
        super.setMode(temp);
        myInputObjects = temp.getCharacterObjects();
    }
}
