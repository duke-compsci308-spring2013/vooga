package vooga.fighter.model.loaders;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import util.Pixmap;
import vooga.fighter.controller.ModelDelegate;
import vooga.fighter.model.MenuGrid;
import vooga.fighter.model.objects.MapObject;
import vooga.fighter.model.objects.MenuObject;
import vooga.fighter.model.utils.State;
import vooga.fighter.model.utils.UpdatableLocation;

public class MenuGridLoader extends ObjectLoader {


	private static final String MENUGRID_PATH = "src/vooga/fighter/config/menugrid.xml";

	private MenuGrid myGrid;
	private List<MenuObject> myMenuObjects;
	private ModelDelegate myDelegate;

	public MenuGridLoader (String menuname, MenuGrid grid, ModelDelegate delegate) {
		super(MENUGRID_PATH);
		myDelegate = delegate;
		myMenuObjects = new ArrayList<MenuObject>();
		myGrid = grid;
		System.out.println("<menugridloader.java><menugridloader> "+menuname);
		load(menuname);
		
	}

	/**
	 * Loads map from xml data
	 */
	public void load(String menuname) {
		Document doc = getDocument();
		NodeList menugridNodes = doc.getElementsByTagName("menumode");

		for (int i = 0; i < menugridNodes.getLength(); i++) {
			Element node = (Element) menugridNodes.item(i);
			String name = getAttributeValue(node, "menuname");
			if (name.equals(menuname)) {
				NodeList menuobjects = node.getElementsByTagName("menuobject");
				for(int j = 0; j < menuobjects.getLength(); j++){
					Element node1 = (Element) menuobjects.item(j);
					String MenuObjectName = getAttributeValue(node1, "menuobjectname");
					myMenuObjects.add(new MenuObject(MenuObjectName, myDelegate));
				}

		}
	}
	
	}
	
	public List<MenuObject> getMenuObjects(){
		return myMenuObjects;
	}

	@Deprecated
	public void load(int id) {
		// Using a String	
	}
	
	
}

