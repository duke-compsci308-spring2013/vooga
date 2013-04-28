package vooga.towerdefense.gameeditor.gameloader.xmlloaders;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;

import util.XMLTool;
import vooga.towerdefense.controller.Controller;
import vooga.towerdefense.factories.elementfactories.GameElementFactory;
import vooga.towerdefense.factories.waveactionfactories.WaveActionFactory;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.model.GameModel;
import vooga.towerdefense.model.Player;
import vooga.towerdefense.model.levels.Level;
import vooga.towerdefense.model.rules.Rule;
import vooga.towerdefense.view.TDView;

/**
* GameLoader loads in the information from the XML file and
* starts the game.
*
* @author Angelica Schwartz
*/
public class GameLoader {
    
    private XMLTool myXMLTool;
    private Map<String, GameElementFactory> gameElements;
    
    public GameLoader(String xmlFilePath) {
        myXMLTool = new XMLTool(xmlFilePath);
        gameElements = new HashMap<String, GameElementFactory>();
    }
    
    public List<GameMap> loadMaps() {
        MapXMLLoader mapLoader = new MapXMLLoader(myXMLTool);
        List<GameMap> maps = mapLoader.loadMaps();
        return maps;
    }
    
    public TDView loadView(TDView view, Controller controller) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ViewXMLLoader loader = new ViewXMLLoader(myXMLTool);
        return loader.makeView(view, controller);
    }
    
    public Player loadPlayer(Controller controller) {
        PlayerXMLLoader loader = new PlayerXMLLoader(myXMLTool);
        return loader.loadPlayer(controller);
    }
    
    public List<GameElementFactory> loadElements(GameMap map, Player player) {
        GameElementXMLLoader loader = new GameElementXMLLoader(myXMLTool);
        //TODO: Fix this
        WaveActionFactory.ourGEFactories = loader.loadGameElementFactories(map, player);
        
        List<GameElementFactory> gameElementFactoryList = new ArrayList<GameElementFactory>(WaveActionFactory.ourGEFactories.values());
        
        for (GameElementFactory factory : WaveActionFactory.ourGEFactories.values()) {
            factory.initialize(map, gameElementFactoryList);
        }
        return new ArrayList<GameElementFactory>(WaveActionFactory.ourGEFactories.values());
    }
    
    public List<Level> loadLevels(GameModel model, GameMap gameMap, Player player) {
        LevelsXMLLoader loader = new LevelsXMLLoader(myXMLTool);
        return loader.loadLevels(model, gameMap, player);
    }
    
    public List<Rule> loadRules(GameModel model) {
        Element rulesElement = myXMLTool.getElement("rules");
        RulesXMLLoader loader = new RulesXMLLoader(myXMLTool);
        return loader.getRules(model, rulesElement);
    }
    
    public void startGame(Controller controller) {
        controller.start();
    }
    
    public Map<String, GameElementFactory> getGEFactoryMap() {
        return gameElements;
    }

}
