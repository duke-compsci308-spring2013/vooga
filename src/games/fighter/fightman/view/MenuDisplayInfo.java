package games.fighter.fightman.view;

import vooga.fighter.controller.displayinformation.DisplayLoopInfo;
import vooga.fighter.model.mode.Mode;
import vooga.fighter.util.HUDVariable;

public class MenuDisplayInfo extends DisplayLoopInfo {
    @HUDVariable (
            name = "Title",
            HUDElementClass = "Text"
            )
    public String titleText;
    @HUDVariable (
            name = "Player 1: ",
            HUDElementClass = "Value"
            )
    public Integer p1Score;
    @HUDVariable (
            name = "Player 2: ",
            HUDElementClass = "Value"
            )
    public Integer p2Score;
    
    /**
     * Passes value to super constructor.
     * @param mode
     */
    public MenuDisplayInfo(Mode mode) {
        super(mode);
    }

    /**
     * Generates the HUDElements for this display
     */
    public void generateHUD () {
        addHUDElements ("games.fighter.fightman.view");
    }
}
