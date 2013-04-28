package games.fighter.examplefighter;

import vooga.fighter.controller.GameManager;
import arcade.games.ArcadeInteraction;
import arcade.games.Game;
import arcade.games.GameData;
import arcade.games.UserGameData;

public class ExampleFighter extends GameManager{

    public ExampleFighter (ArcadeInteraction arcade) {
        super(arcade);
    }

    @Override
    public UserGameData generateNewProfile () {
        return getGameInfo();
    }

    @Override
    public GameData generateNewGameProfile () {
        return null; 
    }

}
