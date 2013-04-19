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

public class MouseClickLoader extends ObjectLoader {

	private static final String MOUSE_PATH = "src/vooga/fighter/config/mouseclick.xml";
	
	MouseClickObject myMouseClick;
	public MouseClickLoader(MouseClickObject mouseclick) {
		super(MOUSE_PATH);
		myMouseClick = mouseclick;
		load();
	}

	
	public void load() {
		Document doc = getDocument();
		NodeList menuNodes = doc.getElementsByTagName("mouseclickobject");
		for (int i = 0; i < menuNodes.getLength(); i++) {
			Element node = (Element) menuNodes.item(i);
				NodeList states = node.getElementsByTagName("state");
				for(int j = 0; j < states.getLength(); j++){
				Element state = (Element) states.item(j);
				String  Statename = getAttributeValue(state, "name");
				NodeList frames = state.getElementsByTagName("frame");
				State newState = new State(myMouseClick, frames.getLength());
				for(int k = 0; k < frames.getLength(); k++){
					Element node1 = (Element) frames.item(k);
					String imagepathway = getAttributeValue(node1, "image");
					newState.populateImage(new Pixmap(imagepathway), k);
					int width = Integer.parseInt((getAttributeValue(node1, "width")));
					int height = Integer.parseInt((getAttributeValue(node1, "height")));
					Dimension dim = new Dimension(width,height);
					Rectangle rect = new Rectangle(width,height);
					newState.populateSize(dim, k);
					newState.populateRectangle(rect, k);
					myMouseClick.addState(Statename, newState);
				}
				if(j == 0) myMouseClick.setCurrentState(Statename);
				}
			}
		}

	@Deprecated
	public void load(String Name) {
		// :(  Don't need the string
		
	}

}
