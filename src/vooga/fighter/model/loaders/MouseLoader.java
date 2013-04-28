package vooga.fighter.model.loaders;

import java.awt.Dimension;
import java.awt.Rectangle;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import util.Pixmap;
import vooga.fighter.model.ModelConstants;
import vooga.fighter.model.objects.MouseObject;
import vooga.fighter.model.utils.State;

/**
 * Loads the resources necessary for MouseObjects. Reads the data from the file designated
 * in the path ModelConstants.MOUSELOADER_PATH_TAG.
 * @author David Le, alanni
 */
public class MouseLoader extends ObjectLoader {

	/**
	 * MouseObject to be modified by this loader.
	 */
	MouseObject myMouse;
	
	/**
	 * Constructs the MouseLoader and sets the reference to the mouse object and points to
	 * the data xml file.
	 * @param mouse MouseObject to modify
	 * @param pathHierarchy The path to the folder containing the game's resources
	 */
	public MouseLoader(MouseObject mouse, String pathHierarchy) {
		super(ModelConstants.MOUSELOADER_PATH_TAG, pathHierarchy);
		myMouse = mouse;
		load(ModelConstants.MOUSELOADER_PATH_TAG, pathHierarchy);
	}

	/**
	 * Loads resources for the appropriate MouseObject matched by the param mouse
	 * @param mouse Name tag of the mouse to be loaded in the xml file
	 * @param pathHierarchy The path to the folder containing the game's resources
	 */
	@Override
	protected void load(String mouse, String pathHierarchy) {
		Document doc = getDocument();
		NodeList menuNodes = doc.getElementsByTagName(ModelConstants.MOUSE_OBJECT);
		Element node = (Element) menuNodes.item(0);
		NodeList states = node.getElementsByTagName(ModelConstants.STATE_PROPERTY);
		Element state = (Element) states.item(0);
		String Statename = getAttributeValue(state, ModelConstants.NAME_PROPERTY);
		NodeList frames = node.getElementsByTagName(ModelConstants.FRAME_PROPERTY);
		State newState = new State(myMouse, frames.getLength());
		Element node1 = (Element) frames.item(0);
		String imagepathway = getAttributeValue(node1, myMouse.getImageTag()+ModelConstants.IMAGE_PROPERTY);
		newState.populateImage(new Pixmap(imagepathway), 0);
		int width = Integer.parseInt((getAttributeValue(node1, ModelConstants.WIDTH_PROPERTY)));
		int height = Integer.parseInt((getAttributeValue(node1, ModelConstants.HEIGHT_PROPERTY)));
		Dimension dim = new Dimension(width,height);
		Rectangle rect = new Rectangle(width,height);
		newState.populateAllSizes(dim);
		newState.populateRectangle(rect, 0);
		myMouse.addState(Statename, newState);
		myMouse.defineDefaultState(Statename);
		myMouse.setToDefaultState();
	}

}
