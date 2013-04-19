package vooga.fighter.model.loaders;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import vooga.fighter.model.objects.EnvironmentObject;
import vooga.fighter.model.utils.UpdatableLocation;

/**
 * Loads data associated with a environment object to be passed to EnvironmentObject.
 * 
 * @author alanni
 *
 *
 */
public class EnvironmentObjectLoader extends ObjectLoader {

	private static final String ENVIRONMENT_OBJECT_PATH = "src/vooga/fighter/config/environmentobjects.xml";
	
	private EnvironmentObject myEnvironmentObject;

	/**
	 * Constructs the environment object loader with the id to be loaded and the environment object which the
	 * loader will modify.
	 */
	public EnvironmentObjectLoader (String enviroObjectName, EnvironmentObject enviroObject) {
		super(ENVIRONMENT_OBJECT_PATH);
		myEnvironmentObject = enviroObject;
		load(enviroObjectName); 
	}
	
	/**
	 * Loads the environment object associated with the id
	 */
	public void load(String enviroObjectName) {
		Document doc = getDocument();
		NodeList enviroObjectNodes = doc.getElementsByTagName("enviroObject");
		for (int i = 0; i < enviroObjectNodes.getLength(); i++) {
			Node node = enviroObjectNodes.item(i);
			String name = getAttributeValue(node, "enviroObjectID");
			if (enviroObjectName.equals(name)) {
				int xCoord= Integer.parseInt(getAttributeValue(node, "xCoord"));
				int yCoord= Integer.parseInt(getAttributeValue(node, "yCoord"));
				EnvironmentObject toAdd= new EnvironmentObject();
				toAdd.setLocation(new UpdatableLocation(xCoord, yCoord));
				NodeList stateNodes = ((Element) node).getElementsByTagName("state");
				addStates(stateNodes, toAdd);
				toAdd.setCurrentState("brick");
				toAdd.setImageData();
			}
		}
	}
}
