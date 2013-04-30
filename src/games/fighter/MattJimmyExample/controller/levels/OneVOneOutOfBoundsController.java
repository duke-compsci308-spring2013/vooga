package games.fighter.MattJimmyExample.controller.levels;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import vooga.fighter.controller.interfaces.ModeCondition;
import vooga.fighter.controller.levels.OneVOneController;
import vooga.fighter.model.mode.LevelMode;
import vooga.fighter.model.mode.Mode;
import vooga.fighter.model.objects.CharacterObject;


public class OneVOneOutOfBoundsController extends OneVOneController {

    private static final String SCORE = "ScoreScreen";

    ModeCondition wincondition = new ModeCondition() {

        @Override
        public boolean checkCondition (Mode mode) {
            LevelMode levelmode = (LevelMode) mode;
            boolean change = false;
            for (int i = 0; i < levelmode.getCharacterObjects().size(); i++) {
                CharacterObject currentChar = levelmode.getCharacterObjects().get(i);
                Double charX = currentChar.getLocation().getX();
                Double charY = currentChar.getLocation().getY();
                Dimension mapSize = levelmode.getMap().getCurrentState().getCurrentSize();
                boolean charOutOfBounds = (charX < 0 || charX > mapSize.getWidth() ||
                                           charY < 0 || charY > mapSize.getHeight());
                if (charOutOfBounds) {
                    change = true;
                    for (int j = 0; j < levelmode.getCharacterObjects().size(); j++) {
                        if (j != i) getGameInfo().addWinners(j);
                        getGameInfo().addScore(levelmode.getCharacterObjects().get(j).getHealth()
                                .getHealth());
                        getGameInfo().addTotalScore(j, getGameInfo().getScore(j));
                    }
                    break;
                }
            }
            return change;
        }
    };

    @Override
    public void checkConditions () {
        if (wincondition.checkCondition(getMode())) {
            getManager().notifyEndCondition(SCORE);
        }
    }

    @Override
    public void setupConditions () {
        getWinConditions().clear();
        addWinCondition(wincondition);
    }

}
