package vooga.fighter.controller;

import java.util.HashMap;

/**
 * 
 * @author matthewparides
 *
 */
public class CharNameIDMaps {
    public static HashMap<Integer, String> IDtoName;
    public static HashMap<String, Integer> NametoID;

    static{
        IDtoName = new HashMap<Integer, String>();
        NametoID = new HashMap<String, Integer>();
        IDtoName.put(1, "Cpt. Falcon");
        IDtoName.put(2, "Mario");
        IDtoName.put(3, "Link");
        IDtoName.put(4, "Fox");
        NametoID.put(getName(1), 1);
        NametoID.put(getName(2), 2);
        NametoID.put(getName(3), 3);
        NametoID.put(getName(4), 4);
    }

    public static int getID(String name) {
        return NametoID.get(name);
    }

    public static String getName(int ID) {
        return IDtoName.get(ID);
    }

}
