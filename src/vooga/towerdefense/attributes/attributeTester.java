package vooga.towerdefense.attributes;

import java.util.HashSet;

public class attributeTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HashSet<Attribute> attributeSet = new HashSet<Attribute>();
		//attributeSet.add(new NumericalAttribute("fuck",2.0));
		AttributeManager AM = new AttributeManager(attributeSet);
		//double test = (Double) AM.getAttribute("fuck").getValue();
	}

}
