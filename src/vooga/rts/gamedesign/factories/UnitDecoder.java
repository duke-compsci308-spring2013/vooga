package vooga.rts.gamedesign.factories;

import java.awt.Dimension;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import vooga.rts.gamedesign.sprite.gamesprites.interactive.units.Unit;
import vooga.rts.util.Location3D;
import vooga.rts.util.Pixmap;
import vooga.rts.util.ReflectionHelper;
import vooga.rts.util.Sound;


/**
 * This class takes care of parsing the XML file for custom Unit information and 
 * instantiating this custom Unit. 
 * 
 * @author Francesco Agosti
 *
 */
public class UnitDecoder extends Decoder {

	private static String HEAD_TAG = "units";
	private static String TYPE_TAG = "unit";
	
	
	private Factory myFactory;
	public UnitDecoder(Factory factory){
		myFactory = factory;
	}
	
	
	@Override
	public void create(Document doc, String type) {
		String path = doc.getElementsByTagName(type).item(0).getAttributes().getNamedItem(SOURCE_TAG).getTextContent();	
		String subtype = type.substring(0, type.length()-1);
		NodeList nodeLst = doc.getElementsByTagName(subtype);
		
		for(int i = 0 ; i < nodeLst.getLength() ; i++){
			Element nElement = (Element) nodeLst.item(i);
			String name = getElement(nElement, NAME_TAG);
			String img = getElement(nElement, IMAGE_TAG);
			String sound = getElement(nElement, SOUND_TAG);
			int health = Integer.parseInt(getElement(nElement, HEALTH_TAG));
			double buildTime = Double.parseDouble(getElement(nElement, TIME_TAG));
			int speed = Integer.parseInt(getElement(nElement, SPEED_TAG));
			
			Unit unit = (Unit) ReflectionHelper.makeInstance(path, new Pixmap(img), 
																		new Sound(sound),
																		health,
																		buildTime,
																		speed);
			
			myFactory.put(name, unit);
			//Load Production Dependencies now
			String [] nameCanProduce = getElement(nElement, PRODUCE_TAG).split("\\s+");
			if(nameCanProduce[0] != ""){
				myFactory.putProductionDependency(name, nameCanProduce);
			}
			//Load Strategy Dependencies now
			String[] strategies = new String[3];
			strategies[1] = getElement(nElement, OCCUPY_TAG);
			myFactory.putStrategyDependency(name, strategies);
			
		}

	}

}
