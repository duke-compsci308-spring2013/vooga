package vooga.towerdefense.attributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


/**
 * Attributes object that helps to track all game element stats.
 * It also provides info requested by view through controller.
 * Used by Towers, Units, Weapons and any Asset-based object.
 * 
 * @author Matthew Roy
 * @author XuRui
 * 
 */
public abstract class AttributeManager {
    private HashMap<String,Attribute> myAttributes;

    public AttributeManager (HashSet<Attribute> attributes) {
        myAttributes = new HashMap<String, Attribute>();
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

    /**
     * Updates a stat whenever they're changed in the game element.
     * 
     * @param updatedStat
     */
    @Deprecated //This doesn't really serve a purpose
    public abstract void updateAttribute (Attribute updatedStat);
    
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

}