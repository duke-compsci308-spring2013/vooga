package vooga.towerdefense.gameeditor.gameloader.xmlloaders;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Element;
import util.XMLTool;
import vooga.towerdefense.action.Action;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.model.GameModel;
import vooga.towerdefense.model.levels.Level;
import vooga.towerdefense.model.rules.Rule;

public class LevelsXMLLoader {
    
    private static final String LEVELS_TAG = "levels";
    private static final String ACTIONS_TAG = "actions";
    private static final String RULES_TAG = "rules";
    
    private XMLTool myXMLTool;
    
    public LevelsXMLLoader(XMLTool xmlTool) {
        myXMLTool = xmlTool;
    }
    
    public List<Level> getLevels(GameModel model, GameMap gameMap) {
        // TODO: Levels element needs to be passed in here
        Element levelsElement = myXMLTool.getElement(LEVELS_TAG);

        List<Level> levels = new ArrayList<Level>();
        
        List<Element> subElements = myXMLTool.getChildrenList(levelsElement);
        for (Element subElement : subElements) {
            levels.add(getLevel(subElement, model, gameMap));
        }
        return levels;
    }
        
    private Level getLevel(Element levelElement, GameModel model, GameMap gameMap) {
        RulesXMLLoader rulesLoader = new RulesXMLLoader(myXMLTool);
        ActionXMLLoader actionLoader = new ActionXMLLoader(myXMLTool);
        Map<String, Element> subElements = myXMLTool.getChildrenElementMap(levelElement);

        List<Rule> rules = rulesLoader.getRules(model, subElements.get(RULES_TAG));
        List<Action> actions = actionLoader.loadActions(subElements.get(ACTIONS_TAG), gameMap);
        return new Level(actions, rules);
    }
}