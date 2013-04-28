package vooga.fighter.model.loaders;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import vooga.fighter.model.ModelConstants;
import vooga.fighter.model.objects.EnvironmentObject;

/**
 * Loads the resources necessary for EnvironmentObjects. Reads the data from the file designated
 * in the path ModelConstants.ENVIRONMENTLOADER_PATH_TAG.
 * @author alanni, David Le
 */
public class EnvironmentObjectLoader extends ObjectLoader {
	
	/**
	 * The EnvironmentObject which will be modified
	 */
	private EnvironmentObject myEnvironmentObject;

	/**
	 * Constructs the environment object loader to be used for reading the designated environment
	 * object file; used by level editor to find all available environment objects.
	 * @param pathHierarchy The path to the folder containing the game's resources
	 */
	public EnvironmentObjectLoader (String pathHierarchy) {
		super(ModelConstants.ENVIRONMENTLOADER_PATH_TAG, pathHierarchy);
	}
	
	/**
	 * Constructs the environment object loader with the name to be loaded and the environment object
	 * which the loader will modify.
	 * @param enviroObjectName The name of the environment object to be matched in the xml file
	 * @param enviroObject The EnvironmentObject to modify
	 * @param pathHierarchy The path to the folder containing the game's resources
	 */
	public EnvironmentObjectLoader (String enviroObjectName, EnvironmentObject enviroObject, String pathHierarchy) {
		super(ModelConstants.ENVIRONMENTLOADER_PATH_TAG, pathHierarchy);
		myEnvironmentObject = enviroObject;
		load(enviroObjectName, pathHierarchy); 
	}
	
	/**
	 * Retrieves information of all environment objects; used for level editor.
     * @param pathHierarchy The path to the folder containing the game's resources
	 */
	public List<EnvironmentObject> getEnvironmentObjects(String pathHierarchy) {
		Document doc = getDocument();
		List<EnvironmentObject> allEnviroObjects = new ArrayList<EnvironmentObject>();
		NodeList enviroObjectNodes = doc.getElementsByTagName(ModelConstants.ENVIRONMENTOBJECT_PROPERTY);
		for (int i = 0; i < enviroObjectNodes.getLength(); i++) {
			Node enviroObjectNode = enviroObjectNodes.item(i);
			String name = getAttributeValue(enviroObjectNode, ModelConstants.ENVIRONMENTOBJECTNAME_PROPERTY);
			EnvironmentObject newEnvironmentObject= new EnvironmentObject(name, pathHierarchy);
			allEnviroObjects.add(newEnvironmentObject);
		}
		return allEnviroObjects;
	}
	
	/**
	 * Loads the environment object associated with the id
	 * @param enviroObjectName The environment object name tag to be matched in the xml
 	 * @param pathHierarchy The path to the folder containing the game's resources
	 */
	@Override
	protected void load(String enviroObjectName, String pathHierarchy) {
		Document doc = getDocument();
		NodeList enviroObjectNodes = doc.getElementsByTagName(ModelConstants.ENVIRONMENTOBJECT_PROPERTY);
		for (int i = 0; i < enviroObjectNodes.getLength(); i++) {
			Node enviroObjectNode = enviroObjectNodes.item(i);
			String name = getAttributeValue(enviroObjectNode, ModelConstants.ENVIRONMENTOBJECTNAME_PROPERTY);
			if (enviroObjectName.equals(name)) {
				NodeList stateNodes = ((Element) enviroObjectNode).getElementsByTagName(getResourceBundle().getString("State"));
				addStates(stateNodes, myEnvironmentObject);
				myEnvironmentObject.defineDefaultState(getAttributeValue(enviroObjectNode, getResourceBundle().getString("Default")));
			}
		}
	}
}
