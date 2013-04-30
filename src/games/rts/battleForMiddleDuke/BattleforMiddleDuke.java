package games.rts.battleForMiddleDuke;

import vooga.rts.game.RTSGame;
import arcade.games.ArcadeInteraction;


public class BattleforMiddleDuke extends RTSGame
{    

    public BattleforMiddleDuke (ArcadeInteraction arcade) {
        super(arcade, "/games/rts/battleForMiddleDuke/GameDefinition.xml");    
        setMap("/games/rts/battleForMiddleDuke/resources/maps/peter/peter.xml");
    }

    public static void main (String[] args) {
        BattleforMiddleDuke f = new BattleforMiddleDuke(null);
        f.run();
    }
}