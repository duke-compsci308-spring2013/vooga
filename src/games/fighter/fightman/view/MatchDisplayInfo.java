package games.fighter.fightman.view;

import vooga.fighter.controller.displayinformation.GameLoopInfo;
import vooga.fighter.model.mode.LevelMode;
import vooga.fighter.util.HUDVariable;

public class MatchDisplayInfo extends GameLoopInfo {
    @HUDVariable (
            name = "P1 HP: ",
            HUDElementClass = "Value"
            )
    public Integer p1Health = 10;
    
    @HUDVariable (
            name = "P2 HP: ",
            HUDElementClass = "Value"
            )
    public Integer p2Health = 10;

    public MatchDisplayInfo(LevelMode mode) {
        super(mode);
    }
}
