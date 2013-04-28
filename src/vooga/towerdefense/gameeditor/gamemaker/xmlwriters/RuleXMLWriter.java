package vooga.towerdefense.gameeditor.gamemaker.xmlwriters;

import org.w3c.dom.Element;
import util.XMLTool;

/**
 * RuleXMLWriter writes the rules of the game
 *      to the specified XML doc in the correct format.
 *
 * @author Angelica Schwartz
 */
public class RuleXMLWriter {
    
    private XMLTool myXMLTool;
    
    /**
     * Constructor.
     * @param xmlTool
     */
    public RuleXMLWriter(XMLTool xmlTool) {
        myXMLTool = xmlTool;
    }
    
    /**
     * writes the rules to the file.
     * @param parent the parent for these rules
     * @param rulesText is the string representing the rules
     */
    public void write (Element parent, String rulesText) {
        String[] rules = rulesText.split("\n");
        for (String r : rules) {
            if (!r.equals("")) {
                String[] rule = r.split(" ");
                Element thisRule = myXMLTool.makeElement(rule[0]);
                for (int i = 1; i < rule.length; i++) {
                    myXMLTool.addChild(thisRule, XMLWriter.PARAMETER_TAG, rule[i]);
                }
                myXMLTool.addChild(parent, thisRule);
            }
        }
    }

}
