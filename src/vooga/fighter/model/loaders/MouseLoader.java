package vooga.fighter.model.loaders;

import java.awt.Dimension;
import java.awt.Rectangle;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import util.Pixmap;
import vooga.fighter.model.objects.MenuObject;
import vooga.fighter.model.objects.MouseClickObject;
import vooga.fighter.model.utils.State;

public class MouseLoader extends ObjectLoader {

	private static final String MOUSE_PATH = "src/vooga/fighter/config/mouse.xml";
	
	MouseClickObject myMouseClick;
	public MouseLoader(MouseClickObject mouseclick) {
		super(MOUSE_PATH);
		myMouseClick = mouseclick;
		load();
	}

	public void load() {
		Document doc = getDocument();
		NodeList menuNodes = doc.getElementsByTagName("menuclickobject");
			Element node = (Element) menuNodes.item(0);
				NodeList states = node.getElementsByTagName("state");
				Element state = (Element) states.item(0);
				String  Statename = getAttributeValue(state, "name");
				NodeList frames = node.getElementsByTagName("frame");
				State newState = new State(myMouseClick, frames.getLength());
					Element node1 = (Element) frames.item(0);
					String imagepathway = getAttributeValue(node1, "image");
					newState.populateImage(new Pixmap(imagepathway), 0);
					int width = Integer.parseInt((getAttributeValue(node1, "width")));
					int height = Integer.parseInt((getAttributeValue(node1, "height")));
					Dimension dim = new Dimension(width,height);
					Rectangle rect = new Rectangle(width,height);
					newState.populateAllSizes(dim);
					newState.populateRectangle(rect, 0);
				myMouseClick.addState(Statename, newState);
				myMouseClick.setCurrentState(Statename);
				}
			
	
	@Deprecated
	public void load(int id) {
	}

	@Deprecated
	public void load(String name) {
	}

}
