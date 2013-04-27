package vooga.fighter.model.loaders;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import vooga.fighter.model.ModelConstants;
import vooga.fighter.model.objects.EnvironmentObject;
import vooga.fighter.model.objects.MapObject;
import vooga.fighter.model.utils.UpdatableLocation;

/**
 * Loads the resources necessary for MapObjects. Reads the data from the file designated
 * in the path ModelConstants.MAPLOADER_PATH_TAG.
 * @author alanni, David Le
 */

public class MapLoader extends ObjectLoader {

	/**
	 * The MapObject to be modified by this MapLoader
	 */
	private MapObject myMap;

	/**
	 * Creates a map loader which will be used to read the map xml file; needed for
	 * level editor and map selection screen.
	 * @param pathHierarchy The path to the folder containing the game's resources
	 */
	public MapLoader(String pathHierarchy) {
		super(ModelConstants.MAPLOADER_PATH_TAG, pathHierarchy);
	}

	/**
	 * Constructs the map loader with the name to be loaded and the map which the
	 * loader will modify.
	 * @param map The name of the map to be matched in the xml file
	 * @param map The MapObject to modify
	 * @param pathHierarchy The path to the folder containing the game's resources
	 */
	public MapLoader(String mapName, MapObject map, String pathHierarchy) {
		super(ModelConstants.MAPLOADER_PATH_TAG, pathHierarchy);
		myMap = map;
		load(mapName, pathHierarchy);
		myMap.setCurrentState("background");
		myMap.defineDefaultState("background");
		myMap.getCurrentState().setLooping(true);
	}

	/**
	 * Loads resources for the appropriate map object matched by the param mapName
	 * @param mapName Name tag of the map to be loaded in the xml file
	 * @param pathHierarchy The path to the folder containing the game's resources
	 */
	protected void load(String mapName, String pathHierarchy) {
		Document doc = getDocument();
		NodeList mapNodes = doc.getElementsByTagName(getResourceBundle().getString("Map"));

		for (int i = 0; i < mapNodes.getLength(); i++) {
			Element node = (Element) mapNodes.item(i);
			String name = getAttributeValue(node, getResourceBundle().getString("MapName"));
			if (mapName.equals(name)) {
				NodeList stateNodes = ((Element) node).getElementsByTagName(getResourceBundle().getString("State"));
				addStates(stateNodes, myMap);
				myMap.setLocation(new UpdatableLocation(Integer.parseInt(getAttributeValue(node, getResourceBundle()
					.getString("XSize"))) / 2, Integer.parseInt(getAttributeValue(node, getResourceBundle()
					.getString("YSize"))) / 2));
				NodeList startingPosNodes = node.getElementsByTagName(getResourceBundle().getString("StartingPosition"));
				addStartingPositions(startingPosNodes);
				NodeList enviroObjectNodes = node.getElementsByTagName(getResourceBundle().getString("EnvironmentObject"));
				addEnviroObjects(enviroObjectNodes, pathHierarchy);
			}
		}
	}
	
	/**
	 * Checks if the map already exists inside the given xml file.
	 * 
	 * @param mapName to check
	 * @return
	 */
	public boolean contains(String mapName) {
		List<String> existingMaps = getMapNames();
		for(String existingMap : existingMaps) {
			if(existingMap.equals(mapName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns a list of map names inside the designated xml file.
	 * 
	 * @return maps List of map names
	 */
	public List<String> getMapNames() {
		List<String> maps = new ArrayList<String>();
		Document doc = getDocument();
		NodeList mapNodes = doc.getElementsByTagName(getResourceBundle().getString("Map"));
		for (int i = 0; i < mapNodes.getLength(); i++) {
			Element node = (Element) mapNodes.item(i);
			maps.add(getAttributeValue(node, getResourceBundle().getString("MapName")));
		}
		return maps;
	}

	/**
	 * Adds starting position for the characters
	 * 
	 * @param startingPosNodes NodeList of all starting positions available on a map
	 */
	private void addStartingPositions(NodeList startingPosNodes) {
		for (int i = 0; i < startingPosNodes.getLength(); i++) {
			Node startingPosition = startingPosNodes.item(i);
			int xCoord = Integer.parseInt(getAttributeValue(startingPosition,
					getResourceBundle().getString("XCoordinate")));
			int yCoord = Integer.parseInt(getAttributeValue(startingPosition,
					getResourceBundle().getString("YCoordinate")));
			myMap.addStartPosition(new UpdatableLocation(xCoord, yCoord));
		}
	}

	/**
	 * Creates environment objects based on XML data
	 * 
	 * @param enviroObjectNodes Nodes containing the information of environment objects
	 * to be added to the map; data for each environment object is handled by their
	 * respective loaders
     * @param pathHierarchy The path to the folder containing the game's resources
	 */
	private void addEnviroObjects(NodeList enviroObjectNodes, String pathHierarchy) {
		for (int i = 0; i < enviroObjectNodes.getLength(); i++) {
			Node enviroObjectNode = enviroObjectNodes.item(i);
			int xCoord = Integer.parseInt(getAttributeValue(enviroObjectNode, getResourceBundle().getString("XCoordinate")));
			int yCoord = Integer.parseInt(getAttributeValue(enviroObjectNode, getResourceBundle().getString("YCoordinate")));
			EnvironmentObject newEnvironmentObject = new EnvironmentObject(getAttributeValue(enviroObjectNode, getResourceBundle()
				.getString("EnvironmentObjectName")), new UpdatableLocation(xCoord, yCoord), pathHierarchy);
			myMap.addEnviroObject(newEnvironmentObject);
		}
	}
}
