package games.superfightercombatmelee;

import vooga.fighter.controller.ControlProgressionManager;
import vooga.fighter.controller.ControllerFactory;
import vooga.fighter.controller.ControllerManager;
import vooga.fighter.controller.gameinformation.GameInfo;
import vooga.fighter.view.Canvas;

public class SubControllerManager extends ControllerManager {
    
    public SubControllerManager(Canvas frame, GameInfo gameinfo, ControllerFactory factory,
                                ControlProgressionManager progressionmanager) {
        super(frame, gameinfo, factory, progressionmanager);
    }

}
