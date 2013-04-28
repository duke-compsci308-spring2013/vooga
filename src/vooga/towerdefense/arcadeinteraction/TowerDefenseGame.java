package vooga.towerdefense.arcadeinteraction;
import java.lang.reflect.InvocationTargetException;
import vooga.towerdefense.controller.Controller;
import vooga.towerdefense.model.GameLoop;
import arcade.games.ArcadeInteraction;
import arcade.games.Game;
import arcade.games.GameData;
import arcade.games.UserGameData;

/**
 * Implements abstract methods from Game class in arcade.
 *
 * @author Angelica Schwartz
 */
public class TowerDefenseGame extends Game {

    /**
     * constructor.
     */
    public TowerDefenseGame (ArcadeInteraction arcade) {
        super(arcade);
    }

    /**
     * implements abstract method specifically for TowerDefense.
     */
    @Override
    public UserGameData generateNewProfile () {
        return new TowerDefenseUserGameData();
    }

    /**
     * runs the TowerDefense game.
     */
    @Override
    public void run () {
        try {
            Controller controller = new Controller("English", "/src/vooga/towerdefense/resources/JimmysTestGame.xml");
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public GameData generateNewGameProfile () {
        // TODO Auto-generated method stub
        return null;
    }

}
