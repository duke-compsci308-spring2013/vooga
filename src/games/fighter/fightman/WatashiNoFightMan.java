package games.fighter.fightman;

import arcade.games.ArcadeInteraction;
import arcade.games.Game;

public class WatashiNoFightMan extends Game {
    
    public WatashiNoFightMan(ArcadeInteraction arcade) {
        super(arcade);
    }

    @Override
    public void run() {
        FightManRunAlone control = new FightManRunAlone();
        control.run();
        
        getArcade().getUserGameData(this).setScore(FightManRunAlone.getScore());
        getArcade().killGame();
    }
    
}
