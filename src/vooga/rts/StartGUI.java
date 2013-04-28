package vooga.rts;

import vooga.rts.game.RTSGame;
import arcade.games.ArcadeInteraction;


public class StartGUI extends RTSGame
{    

    public StartGUI (ArcadeInteraction arcade) {
        super(arcade, "/vooga/rts/gamedesign/factories/Factory.xml");        
    }

    public static void main (String[] args) {
        StartGUI f = new StartGUI(null);
        f.run();
    }
}
