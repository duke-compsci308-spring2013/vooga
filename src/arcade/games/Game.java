package arcade.games;
import java.awt.Component;


/**
 * All genre writers should extend this class to write their games, so that
 * it can be played in the Arcade.
 * 
 * @author ArcadeTeam
 */
public abstract class Game{
    
    private ArcadeInteraction myArcade;
    
    /**
     * Constructs a game with the arcade interface and the user game data
     * 
     * @param arcade ArcadeInteraction
     */
    public Game(ArcadeInteraction arcade) {
        myArcade = arcade;
    }    
    
    
    /**
     * This method will be called by the arcade if the user has never played
     * the game before, and this data will be associated with the user currently
     * playing.  
     * @return the specific subclass of UserGameData for each game.
     */
    public abstract UserGameData generateNewProfile();
    
    
    /**
     * This method will be called by the arcade if no one has ever played this game 
     * and we need to generate a gamedata object. If you dont plan on using gameData objects
     * then you dont need to implement this method, but if you don't then you cant 
     *  call getGameData from ArcadeInteraction
     * @return the specific subclass of GameData for each game.
     */
    public abstract GameData generateNewGameProfile();
    /**
     * starts the game (should probably start running the game loop)
     *
     */
    public abstract void run ();
    
    /**
     * The ArcadeInteraction is the access the game has  
     * to arcade elements (e.g. user name, user's saved game data)
     *
     */
    protected ArcadeInteraction getArcade() {
        return myArcade;
    }

}
