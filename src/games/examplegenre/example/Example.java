
package games.examplegenre.example;

import java.io.IOException;
import arcade.games.ArcadeInteraction;
import arcade.games.Game;
import arcade.games.GameData;
import arcade.games.UserGameData;

public class Example extends Game {

    private static final String GAME_NAME = "example";
    public Example (ArcadeInteraction arcade) {
        super(arcade);
    }

    @Override
    public UserGameData generateNewProfile () {
        return new UserGameData();
    }

    @Override
    public void run () {
        String url = "http://breadfish.de/";
        try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            // win condition is wait 5 sec.
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // update the score before killing it 
        //getArcade().getUserGameData(GAME_NAME).setScore(42);
        getArcade().killGame();

    }

    @Override
    public GameData generateNewGameProfile () {
        // TODO Auto-generated method stub
        return null;
    }

}

