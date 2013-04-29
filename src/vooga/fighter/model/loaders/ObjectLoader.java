package vooga.fighter.model.loaders;

import java.awt.Rectangle;
import java.io.File;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import util.Pixmap;
import vooga.fighter.model.ModelConstants;
import vooga.fighter.model.objects.GameObject;
import vooga.fighter.model.utils.State;

/**
 * Abstract class with shared methods that all ObjectLoader-derived classes may need.
 * 
 * @author David Le, alanni
 *
 */
public abstract class ObjectLoader {



	/**
	 * Raw format of XML document containing object information
	 */
	private Document myDocument;
	/**
	 * Resource bundle containing the information on tags to search for in xml files
	 */
	private ResourceBundle myTags;
	/**
	 * Resource bundle containing the information on default values if certain parameters
	 * are not found in xml files.
	 */
	private ResourceBundle myDefaults;
	/**
	 * Resource bundle containing the information on which tags each class has in xml files
	 */
    private ResourceBundle myProperties;  

	/**
	 * Points to the xml file that the loader will be parsing.
	 * @param objectPath
	 * @param pathHierarchy The path to the folder containing the game's resources
	 */
	public ObjectLoader (String pathName, String pathHierarchy) {
		setResourcePaths(pathHierarchy); 
		String objectPath = myTags.getString(pathName);
		File objectFile = new File(objectPath);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			myDocument = dBuilder.parse(objectFile);
			myDocument.getDocumentElement().normalize();
		} catch (Exception e) {
			myDocument = null;
			System.err.println(ModelConstants.PARSING_ERROR+ getClass().getSimpleName());
		}
	}
	
	/**
	 * Sets the resource paths for the object loader
	 * @param pathHierarchy The path to the folder containing the game's resources
	 */
	private void setResourcePaths(String pathHierarchy){
		myTags = ResourceBundle.getBundle(pathHierarchy+ModelConstants.RESOURCE_OBJECT_PATH);
		myDefaults= ResourceBundle.getBundle(pathHierarchy+ModelConstants.RESOURCE_DEFAULT_VALUE_PATH);
		myProperties= ResourceBundle.getBundle(pathHierarchy+ModelConstants.RESOURCE_PROPERTIES_PATH);
	}

	/**
	 * Loads object based on the name given
	 * @param name The name to be matched in the respective subclass's xml file
	 * @param pathHierarchy The path to the folder containing the game's resources
	 */
	protected abstract void load(String name, String pathHierarchy);
	

	/**
	 * Returns the xml document which the loader points to
	 * @return myDocument
	 */
	public Document getDocument() {
		return myDocument;
	}

	/**
	 * Returns resource bundle which contains what the names of nodes in the xml files should be.
	 * return myResources
	 */
	protected ResourceBundle getResourceBundle() {
		return myTags;
	}

	/**
	 * Returns the string attribute value of the specified tag for the specified mode.
	 * @param node The node to be examined
	 * @param tag The tag which we want to get the value of
	 * @return value The value matching the tag in the specified node
	 */
	public String getAttributeValue(Node node, String tag) {
		try{
			String value= node.getAttributes().getNamedItem(tag).getTextContent();
			return value; 
		}
		catch(NullPointerException e){
				String value= myDefaults.getString(getClass().getSimpleName()+tag);
				System.err.println(ModelConstants.PROPERTYSET_ERROR1+ tag+ ModelConstants.PROPERTYSET_ERROR2+value);
				return value;
		}
	}

	/**
	 * Loads and adds states for the GameObject.
	 * @param stateNodes The list of nodes which contain all the states' information
	 * @param myObject GameObject to load the states' information into
	 */
	protected void addStates(NodeList stateNodes, GameObject myObject) {
		for (int i = 0; i < stateNodes.getLength(); i++) {
			Element state = (Element) stateNodes.item(i);
			String stateName = getAttributeValue(stateNodes.item(i), ModelConstants.STATENAME_PROPERTY);
			NodeList frameNodes = state.getElementsByTagName(ModelConstants.FRAME_PROPERTY);
			State newState = new State(myObject, frameNodes.getLength());
			getFrameProperties(frameNodes, newState);
			myObject.addState(stateName, newState);
		}
	}

	/**
	 * Loads frames and states for the object which calls the method
	 * @param frameNodes The list of nodes which contain all the frames' information
	 * for this state
	 * @param newState The state which all of the frames' information will be loaded into.
	 */
	protected void getFrameProperties(NodeList frameNodes, State newState){
		for (int j = 0; j < frameNodes.getLength(); j++) {
			Element frame = (Element) frameNodes.item(j);
			if (frame.getAttributes().getNamedItem(ModelConstants.IMAGE_PROPERTY) != null){
				newState.populateImage(new Pixmap(getAttributeValue(frame, ModelConstants.IMAGE_PROPERTY)), j);
			} else {
				newState.populateImage(new Pixmap(ModelConstants.BLANK), j);
			}
			NodeList hitboxNodes = frame.getElementsByTagName(ModelConstants.HITBOX_PROPERTY); 
			for (int k=0; k<hitboxNodes.getLength(); k++){
				newState.populateRectangle(new Rectangle(Integer.parseInt(getAttributeValue(hitboxNodes.item(k), ModelConstants.CORNERX_PROPERTY)),
						Integer.parseInt(getAttributeValue(hitboxNodes.item(k), ModelConstants.CORNERY_PROPERTY)), Integer.parseInt(getAttributeValue(hitboxNodes.item(k), ModelConstants.WIDTH_PROPERTY)),
						Integer.parseInt(getAttributeValue(hitboxNodes.item(k), ModelConstants.HEIGHT_PROPERTY))), j);
			}
		}
	}
	
	/**
	 * Return array of desired properties that will be loaded into the character
	 */
	protected String [] getProperties(){
		return myProperties.getString(getClass().getSimpleName()).split(ModelConstants.DELIMITER);
	}
	
	/**
	 * Loops through list of properties and 
	 * @param node The node which contains the property information
	 * @param object The GameObject which will have the property information added into
	 */
	protected void addProperties(Node node, GameObject object){
		for (String property: getProperties()){
			getAndAddProperty(node, property, object);
		}
	}
	
	/**
	 * Adds property values for gameobject
	 */
	protected void getAndAddProperty(Node node, String property, GameObject object){
		int propertyValue= Integer.parseInt(getAttributeValue(node, property));
		object.addProperty(property, propertyValue);
	}
	
}

