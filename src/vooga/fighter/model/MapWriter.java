package vooga.fighter.model;

import java.util.ArrayList;
import java.util.List;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import util.XMLTool;
import vooga.fighter.model.loaders.MapLoader;
import vooga.fighter.model.objects.EnvironmentObject;
import vooga.fighter.model.objects.MapObject;
import vooga.fighter.model.utils.UpdatableLocation;


/**
 * Class used to write out all essential map information to an xml file
 * @author matthewparides
 *
 */
public class MapWriter extends AbstractWriter{
	private MapObject myWriteSource;
	private Element myRoot;
	private String mySoundFilePath;
	private List<String> myBackgroundFilePaths;
	
	/**
	 * constructor
	 * @param map
	 * @param soundFilePath
	 * @param backgroundFilePaths
	 */
	public MapWriter(MapObject map, String soundFilePath, List<String> backgroundFilePaths, String filepath) {
		super(filepath, new XMLTool());
		myWriteSource = map;
		myBackgroundFilePaths = backgroundFilePaths;
	}
	
	/**
	 * writes the data in the myWriteSource map to an xml file at myFilePath
	 */
	@Override
	public void writeData() {
		writeData(getFilePath());
	}
	
	/**
	 * writes the data in myWriteSource to the xml file at filepath
	 */
	@Override
	public void writeData(String filepath) {
		getXMLWriter().makeDoc();
		List<UpdatableLocation> startingPos = myWriteSource.getStartPositions();
		List<EnvironmentObject> enviroObjects = myWriteSource.getEnviroObjects();
		writeMapHeader();
		writeStartPositions(startingPos);
		writeStates();
		writeSound();
		writeEnvironmentObjects(enviroObjects);
		writeToFile(filepath);
	}
	
	/**
	 * writes the starting positions of the map to xml nodes
	 * includes coordinates.
	 * @param loc
	 */
	private void writeStartPos(UpdatableLocation loc) {
		Element elem = getXMLWriter().makeElement("startingPos");
		elem.setAttribute("yCoord", "" + loc.getLocation().getY());
		elem.setAttribute("xCoord", "" + loc.getLocation().getX());
		getXMLWriter().addChild(myRoot, elem);
		
	}
	
	/**
	 * writes all starting locations to the xmlWriter
	 * @param startLocs
	 */
	private void writeStartPositions(List<UpdatableLocation> startLocs) {
		for(UpdatableLocation loc: startLocs) {
			writeStartPos(loc);
		}
	}
	
	/**
	 * writes the environment objects to xml nodes
	 * environment nodes include coordinate data and the object name
	 * @param enviro - the environmentobject to write
	 */
	private void writeEnvironmentObject(EnvironmentObject enviro) {
		List<String> attribNames = new ArrayList<String>();
		List<String> attribValues = new ArrayList<String>();
		Element elem = getXMLWriter().makeElement("environtmentObject");
		elem.setAttribute("yCoord", "" + enviro.getLocation().getLocation().getY());
		elem.setAttribute("xCoord", "" + enviro.getLocation().getLocation().getX());
		elem.setAttribute("objectName", enviro.getName());
		getXMLWriter().addChild(myRoot, elem);
	}
	
	/**
	 * writes all environmentObjects in enviroObjs to the xmlWriter
	 * @param enviroObjs
	 */
	private void writeEnvironmentObjects(List<EnvironmentObject> enviroObjs) {
		for(EnvironmentObject enviro: enviroObjs) {
			writeEnvironmentObject(enviro);
		}
	}
	
	/**
	 * writes the sound information to xml nodes
	 * currently writes just the filepath
	 */
	private void writeSound() {
		Element soundHead = getXMLWriter().makeElement("sound");
		getXMLWriter().addChild(myRoot, soundHead);
		Element sound = getXMLWriter().makeElement("soundFile");
		sound.setAttribute("sound", mySoundFilePath);
		getXMLWriter().addChild(soundHead, sound);
	}
	
	/**
	 * writes the map header info to an xml node
	 * the map header includes the map name and the dimensions.
	 */
	private void writeMapHeader() {
		myRoot = getXMLWriter().makeRoot("map");
		myRoot.setAttribute("ySize", "871");
		myRoot.setAttribute("xSize", "1024");
		myRoot.setAttribute("mapName", myWriteSource.getName());
	}
	
	/**
	 * writes the image states data to xml nodes
	 */
	private void writeStates() {
		Element stateHead = getXMLWriter().makeElement("state");
		stateHead.setAttribute("stateName", "background");
		getXMLWriter().addChild(myRoot, stateHead);
		for(String str: myBackgroundFilePaths) {
			Element background = getXMLWriter().makeElement("frame");
			background.setAttribute("image", str);
			getXMLWriter().addChild(stateHead, background);
		}
	}
	
	/**
	 * writes the map's generated xml nodes to the file at input filepath.
	 * If the map already exists, overwrites that data, if not, adds a new map.
	 */
	@Override
	protected void writeToFile(String filepath) {
		MapLoader loader = new MapLoader(filepath);
		Document doc = loader.getDocument();
		Element root = doc.getDocumentElement();
		NodeList mapNodes = doc.getElementsByTagName("map");
		for (int i = 0; i < mapNodes.getLength(); i++) {
			Element node = (Element) mapNodes.item(i);
			String name = loader.getAttributeValue(node, "mapName");
			if(name.equals(myWriteSource.getName())) {
				node.getParentNode().removeChild(node);
			}
		}
		root.appendChild(myRoot);
	}
}