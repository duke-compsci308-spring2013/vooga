
package vooga.towerdefense.attributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import vooga.towerdefense.factories.GameElementFactory;
import vooga.towerdefense.factories.ProjectileFactory;


/**
 * Attributes object that helps to track all game element stats.
 * It also provides info requested by view through controller.
 * Used by Towers, Units, Weapons and any Asset-based object.
 * 
 * @author Matthew Roy
 * @author XuRui
 * @author Zhen Gou
 */
public class AttributeManager {
    private HashMap<String,Attribute> myAttributes;
    private HashMap<String,GameElementFactory> myFactories;
    private ProjectileFactory myProjectileFactory;

    public AttributeManager () {
        myAttributes = new HashMap<String,Attribute>();
        myFactories = new HashMap<String, GameElementFactory>();
//        myProjectileFactory=new ProjectileFactory();
    }
    
    /**
     * Creates an attribute manager based on a set of attributes
     * @param attributes
     */
    public AttributeManager (HashSet<Attribute> attributes) {
    	new AttributeManager();
        for (Attribute a : attributes) {
            myAttributes.put(a.getName(), a);
        }        
    }
    

    /**
     * Returns stats information requested by View components.
     * 
     * @return
     */
    public List<String> getAttributesInfo () {
        List<String> info = new ArrayList<String>();
        for (String statName : myAttributes.keySet()) {
            Attribute stat = myAttributes.get(statName);
            info.add(stat.getDisplayableInfo());
        }
        return info;
    }
    
    public String toString() {
        String returnValue = "";
        for (String s : getAttributesInfo()) {
            returnValue += s + "\n";
        }
        return returnValue;
    }

    
    /**
     * Gets a specific attribute based on name
     * @param name
     * @return attribute if it exists, otherwise returns null
     */
    public Attribute getAttribute(String name) {
        return myAttributes.get(name);
    }

    /**
     * Add stats attribute to game element
     */
    public void addAttribute (Attribute newAttribute) {
        myAttributes.put(newAttribute.getName(), newAttribute);
    }
    
    public void addAttributes(List<Attribute> newAttributes){
    	for (Attribute a: newAttributes){
    		addAttribute(a);
    	}
    }
    
    /**
     * Resets all attributes to default values
     */
    
    public void resetAllAttributes(){
    	for (Attribute attr:myAttributes.values()){
    		attr.reset();
    	}
    }
    

    public GameElementFactory getGameElementFactories(String name){
    	return myFactories.get(name);
    }
    
    public ProjectileFactory getProjectileFactory(){
    	return myProjectileFactory;
    }
    
    public void setProjectileFactory(ProjectileFactory projectileFactory){
    	myProjectileFactory=projectileFactory;
    }

}

