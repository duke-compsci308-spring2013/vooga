package games.fighter.examplefighter;

import vooga.fighter.controller.GameManager;
import arcade.games.ArcadeInteraction;
import arcade.games.Game;
import arcade.games.GameData;
import arcade.games.UserGameData;

public class ExampleFighter extends Game {

    public ExampleFighter (ArcadeInteraction arcade) {
        super(arcade);
        // TODO Auto-generated constructor stub
    }

    @Override
    public UserGameData generateNewProfile () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameData generateNewGameProfile () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void run () {
        GameManager gm = new GameManager();
        gm.run();
    }

}
