package vooga.towerdefense.util;

import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Element;


public class XMLToolExample {
    private static final String USER_DIR = "user.dir";
    private static XMLTool myDoc;
    
    public static void main (String args[]) {
        // Creates a new XML document
        myDoc = new XMLTool("XMLExample.xml");
        // Creates a new root. Import org.w3c.dom.Element
        Element life = myDoc.makeRoot("Life");
        // Creates a new node.
        Element PrimateElement = myDoc.makeElement("Primates");
        // Creates a new node with a value.
        Element nameElement = myDoc.makeElement("name", "Robert C. Duvall");
        // Creates a HashMap with some stuff in it. More specifically, a map if types of
        Map<String, String> humanMap = new HashMap<String, String>();
        humanMap.put("species", "Homo Sapiens");
        humanMap.put("fingers", "10");
        humanMap.put("eyes", "2");
        humanMap.put("language", "Java");
        humanMap.put("occupation", "Writing good code");
        //Creates a human with certain characteristics in the map
        Element humanElement = myDoc.makeElementsFromMap("Human", humanMap);
        //Add/append nameElement
        myDoc.addChildElement(humanElement, nameElement);
        myDoc.addChildElement(life, humanElement);
        // Gets the value of an element with a tag
        System.out.println(myDoc.getContentFromTag("Life"));        
        // Writes the document
        myDoc.writeFile();
    }
}
