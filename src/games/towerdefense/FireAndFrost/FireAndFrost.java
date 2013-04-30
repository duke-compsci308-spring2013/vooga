package games.towerdefense.FireAndFrost;
import java.lang.reflect.InvocationTargetException;
import vooga.towerdefense.arcadeinteraction.TowerDefenseUserGameData;
import vooga.towerdefense.controller.Controller;
import arcade.games.ArcadeInteraction;
import arcade.games.Game;
import arcade.games.GameData;
import arcade.games.UserGameData;

/**
 * Example game for arcade
 *
 * @author Zhen Gou
 */
public class FireAndFrost extends Game {

    /**
     * constructor.
     */
    public FireAndFrost (ArcadeInteraction arcade) {
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
            Controller controller = new Controller("English", "/src/vooga/towerdefense/resources/ZhensGame.xml");
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
 
        return null;
    }

}