package vooga.rts.gamedesign.upgrades;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import vooga.rts.gamedesign.sprite.InteractiveEntity;
import vooga.rts.gamedesign.sprite.rtsprite.Resource;
import vooga.rts.player.Player;

/**
 * This class specifies an upgrade and is used to apply the upgrade to the 
 * object that is being upgraded.
 * 
 * @author Ryan Fishel
 * @author Kevin Oh
 * @author Francesco Agosti
 * @author Wenshun Liu 
 *
 */
public class UpgradeNode {

	private UpgradeTree myUpgradeTree;
	private Map<Resource, Integer> myCost;
    private String myUpgradeType;
    private String myUpgradeProperty;
    private int myUpgradeValue;
    private boolean myHasBeenUpgraded;
    private List<UpgradeNode> myChildren; //set to list for the Head.
    private int myID;

    public UpgradeNode(){
        this(null, null, 0);
    }

    public UpgradeNode(String upgradeType, String upgradeObject, int upgradeValue){
        myUpgradeType = upgradeType;
        myChildren = new ArrayList<UpgradeNode>();
        myHasBeenUpgraded = false;
        myUpgradeProperty = upgradeObject;
        myUpgradeValue = upgradeValue;
    }

    /**
     * Applies the effect of this Upgrade type to the given interactive.
     * @param interactive
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     * @throws InstantiationException 
     * @throws NoSuchMethodException 
     * @throws SecurityException 
     */
    public void apply(List<InteractiveEntity> requester)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, InstantiationException,
			SecurityException, NoSuchMethodException {
        for (InteractiveEntity i: requester){
        	Class thisClass = i.getClass();
            //System.out.println(thisClass.getName());
            //Object iClass = thisClass.newInstance();
            Class[] params = new Class[] {int.class};
            //Object[] args = new Object[] {};
            Method thisMethod = thisClass.getDeclaredMethod("upgradeHealth", params);
            thisMethod.invoke(i, getUpgradeValue());
        }
	}
    
    public boolean validUpdate(Player player){
    	//TODO
    	return true;
    }

    public List<UpgradeNode> getChildren(){
        return myChildren;
    }
    
    public void addChild(UpgradeNode upgrade) {
		myChildren.add(upgrade);
	}

    public UpgradeTree getUpgradeTree() {
    	return myUpgradeTree;
    }
    
    public String getUpgradeType(){
        return myUpgradeType;
    }

    public boolean getHasBeenUpgraded(){
        return myHasBeenUpgraded;
    }

    public String getUpgradeObject(){
        return myUpgradeProperty;
    }

    public int getUpgradeValue(){
        return myUpgradeValue;
    }
    
    public int getID(){
    	return myID;
    }
}