package vooga.fighter.model.loaders;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import util.Pixmap;
import vooga.fighter.model.ModelConstants;
import vooga.fighter.model.objects.MenuObject;
import vooga.fighter.model.utils.State;

/**
 * Loads the resources necessary for selectable Menu objects. Reads the data from the file designated
 * in the path ModelConstants.MENULOADER_PATH_TAG.
 * @author Jack Matteucci, David Le
 */
public class MenuLoader extends ObjectLoader {
	
	/**
	 * Menu object to be modified by this loader.
	 */
	MenuObject myMenuObject;
	
	/**
	 * Constructs the MenuLoader and designates what menu item to load, which object to point
	 * it to, and the path leading to the game's resources.
	 * @param menuObjectName Name of the menu object to loaded from the xml file
	 * @param menuObject Sets the instance variable of which MenuObject to load the resources into
	 * @param pathHierarchy The path to the folder containing the game's resources
	 */
	public MenuLoader(String menuObjectName, MenuObject menuObject, String pathHierarchy) {
		super(ModelConstants.MENULOADER_PATH_TAG, pathHierarchy);
		myMenuObject = menuObject;
		load(menuObjectName, pathHierarchy);
	}

	/**
	 * Loads resources for the appropriate menu object matched by the param menuObjectName
	 * @param menuObjectName Name tag of the menu object to be loaded in the xml file
	 * @param pathHierarchy The path to the folder containing the game's resources
	 */
    @Override
	protected void load (String menuObjectName, String pathHiearchy) {
        Document doc = getDocument();
        NodeList menuNodes = doc.getElementsByTagName(ModelConstants.MENUOBJECT_PROPERTY);
        for (int i = 0; i < menuNodes.getLength(); i++) {
            Element node = (Element) menuNodes.item(i);
            String name = getAttributeValue(node, ModelConstants.MENUOBJECTNAME_PROPERTY);
            if (name.equals(menuObjectName)) {
                myMenuObject.setValue(name);
                NodeList states = node.getElementsByTagName(ModelConstants.STATE_PROPERTY);
                for (int j = 0; j < states.getLength(); j++) {
                    Element state = (Element) states.item(j);
                    String stateName = getAttributeValue(state, ModelConstants.NAME_PROPERTY);
                    NodeList frames = state.getElementsByTagName(ModelConstants.FRAME_PROPERTY);
                    State newState = new State(myMenuObject, frames.getLength());
                    for (int k = 0; k < frames.getLength(); k++) {
                        Element node1 = (Element) frames.item(k);
                        String imagepathway = getAttributeValue(node1, ModelConstants.IMAGE_PROPERTY);
                        Pixmap image = new Pixmap(imagepathway);
                        newState.populateImage(image, k);
                    }
                    myMenuObject.addState(stateName, newState);
                    newState.setLooping(true);
                    if (j == 0) {
                        myMenuObject.defineDefaultState(stateName);
                        myMenuObject.setToDefaultState();
                    }
                }

            }
        }
    }
}
