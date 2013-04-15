package vooga.fighter.model.loaders;

import java.awt.Dimension;
import java.awt.Rectangle;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import vooga.fighter.model.objects.AttackObject;
import vooga.fighter.model.objects.CharacterObject;
import vooga.fighter.model.utils.State;
import util.Pixmap;


/**
 * Loads data associated with a character object to be passed to CharacterObject.
 * 
 * @author David Le, alanni
 *
 */
public class CharacterLoader extends ObjectLoader {
	
	private static final String CHARACTER_PATH = "src/vooga/fighter/config/characters.xml";
	
	private CharacterObject myChar;

	/**
	 * Constructs the character loader with the id to be loaded and the character which the
	 * loader will modify.
	 * @param charId
	 * @param character
	 */
	public CharacterLoader (int charId, CharacterObject character) {
		super(CHARACTER_PATH);
		myChar = character;
		load(charId);
	}

	/**
	 * Loads the character associated with the id
	 * @param charId is the id of the character to be loaded
	 */
	public void load(int charId) {
		Document doc = getDocument();
		NodeList charNodes = doc.getElementsByTagName("character");

		for (int i = 0; i < charNodes.getLength(); i++) {
			Node node = charNodes.item(i);
			int id = Integer.parseInt(getAttributeValue(node, "charID"));
			if (id == charId) {
				int maxHealth = Integer.parseInt(getAttributeValue(node, "maxHealth"));
				int speed= Integer.parseInt(getAttributeValue(node, "movespeed"));
				myChar.addProperty("speed", speed);
				myChar.setHealth(maxHealth);

				NodeList stateNodes = ((Element) node).getElementsByTagName("state");
				addStates(stateNodes, myChar);
				NodeList attackNodes = ((Element) node).getElementsByTagName("attack");
				addAttacks(attackNodes);
			}
		}
	}
	
	/**
	 * Loads and adds attacks to the list of attacks that a character can perform
	 * @param attackNodes
	 */
	private void addAttacks(NodeList attackNodes) {
		for (int i = 0; i < attackNodes.getLength(); i++) {
			Element attack = (Element) attackNodes.item(i);
			String attackName = getAttributeValue(attackNodes.item(i), "attackName");
			int attackDmg = Integer.parseInt(getAttributeValue(attackNodes.item(i), "damage"));
			NodeList frameNodes = attack.getElementsByTagName("frame");
			AttackObject newAttack = new AttackObject();
			State newState = new State(myChar, frameNodes.getLength());
			newAttack.setPower(attackDmg);
			for (int j = 0; j < frameNodes.getLength(); j++) {
				if (frameNodes.item(j).getAttributes().getNamedItem("image") != null) {
					newState.populateImage(new Pixmap(getAttributeValue(frameNodes.item(j), "image")), j);
				}
				Element frame = (Element) frameNodes.item(j);
				Node attackboxNode = frame.getElementsByTagName("attackbox").item(0);
				newState.populateRectangle(new Rectangle(Integer.parseInt(getAttributeValue(attackboxNode, "cornerX")),
						Integer.parseInt(getAttributeValue(attackboxNode, "cornerY")), Integer.parseInt(getAttributeValue(attackboxNode, "rectX")),
						Integer.parseInt(getAttributeValue(attackboxNode, "rectY"))), j);
			}
			newAttack.addState(attackName, newState);
			myChar.addAttack(attackName, newAttack);
		}
	}
}