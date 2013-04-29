package vooga.fighter.model.loaders;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import vooga.fighter.model.ModelConstants;
import vooga.fighter.model.objects.AttackObject;
import vooga.fighter.model.objects.CharacterObject;

/**
 * Loads the resources necessary for CharacterObjects. Reads the data from the file designated
 * in the path ModelConstants.CHARACTERLOADER_PATH_TAG.
 * 
 * @author David Le, alanni
 *
 */
public class CharacterLoader extends ObjectLoader {
	
	/**
	 * The CharacterObject which will be modified.
	 */
	private CharacterObject myChar;

	/**
	 * Constructs the character loader with the name to be loaded and the character which the
	 * loader will modify.
	 * @param charName The name of the character to be matched in the xml file
	 * @param character The CharacterObject to modify
	 * @param pathHierarchy The path to the folder containing the game's resources
	 */
	public CharacterLoader (String charName, CharacterObject character, String pathHierarchy) {
		super(ModelConstants.CHARACTERLOADER_PATH_TAG, pathHierarchy);
		myChar = character;
		load(charName, pathHierarchy);
	}

	/**
	 * Loads the character associated with the name.
	 * @param charName The name of the character to be matched in the xml file
	 * @param pathHierarchy The path to the folder containing the game's resources
	 */
	@Override
	protected void load(String charName, String pathHierarchy) {
		Document doc = getDocument();
		NodeList charNodes = doc.getElementsByTagName(ModelConstants.CHARACTER_PROPERTY);

		for (int i = 0; i < charNodes.getLength(); i++) {
			Element node = (Element) charNodes.item(i);
			String name = getAttributeValue(node, ModelConstants.CHARACTERNAME_PROPERTY);
			if (charName.equals(name)) {
				addProperties(node, myChar);
				NodeList stateNodes = node.getElementsByTagName(ModelConstants.STATE_PROPERTY);
				addStates(stateNodes, myChar);
				myChar.defineDefaultState(getAttributeValue(node, ModelConstants.DEFAULT_PROPERTY));
				NodeList attackNodes = node.getElementsByTagName(ModelConstants.ATTACK_PROPERTY);
				addAttacks(attackNodes, pathHierarchy);
			}
		}
	}
	
	
	/**
	 * Loads and adds attacks to the list of attacks that a character can perform by instantiating
	 * the new AttackObjects (the data for AttackObjects is handled via AttackLoader inside
	 * the AttackObject class).
	 * @param attackNodes Nodes representing available attacks
	 * @param pathHierarchy The path to the folder containing the game's resources
	 */
	private void addAttacks(NodeList attackNodes, String pathHierarchy) {
		for (int i = 0; i < attackNodes.getLength(); i++) {
			String attackName = getAttributeValue(attackNodes.item(i), ModelConstants.ATTACKNAME_PROPERTY);
			AttackObject newAttack = new AttackObject(attackName, pathHierarchy);
			newAttack.setOwner(myChar);
			newAttack.setOwnerForEffects(myChar);
			myChar.addAttack(attackName, newAttack);
		}
	}
}