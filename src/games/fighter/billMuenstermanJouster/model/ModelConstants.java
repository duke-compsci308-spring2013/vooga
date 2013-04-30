package games.fighter.billMuenstermanJouster.model;

import java.util.ResourceBundle;

/**
 * Stores the constants relevant to the model.
 * 
 * @author James Wei
 *
 */
public class ModelConstants {
    
    // resource bundle setup
    private static final String RESOURCE_PATH = "vooga.fighter.config.objects";
    private static final String RESOURCE_DEFAULT_VALUES_PATH="vooga.fighter.config.defaultvalues";
    private static ResourceBundle myDefaults;  
    private static ResourceBundle myResources;  

    static {
        myResources = ResourceBundle.getBundle(RESOURCE_PATH);
        myDefaults= ResourceBundle.getBundle(RESOURCE_DEFAULT_VALUES_PATH);
    }
    
    // strings used to identify attack object properties
    public static final String ATTACK_PROPERTY_DAMAGE = myResources.getString("Damage");
    public static final String ATTACK_PROPERTY_SPEED = myResources.getString("Movespeed");
    public static final String ATTACK_PROPERTY_DIRECTION = myResources.getString("Direction");
    public static final String ATTACK_PROPERTY_DURATION = myResources.getString("Duration");
    
    // attack object constants
    public static final int ATTACK_COUNTER_ZERO = 0;
    
    // strings used to identify effect properties
    public static final String EFFECT_PROPERTY_DURATION = myResources.getString("Duration");
    
    // effect properties
    public static final String EFFECT_PROPERTY_BURN_DAMAGE = myResources.getString("Damage");
    
    //directions
    public static final int RIGHT=0; 
    public static final int DOWN=90;
    public static final int LEFT=180; 
    public static final int UP=270; 
    
    //Mouse click image tag
    public static final String MOUSE_CLICK_IMAGE_TAG = "mouseclick";
    
    //Mouse image tag
    public static final String MOUSE_IMAGE_TAG = "mouse";    
    public static final String ATTACKLOADER_PATH_TAG = "AttackPath";
    public static final String CHARACTERLOADER_PATH_TAG = "CharacterPath";
    public static final String ENVIRONMENTLOADER_PATH_TAG= "EnvironmentPath";
    public static final String MAPLOADER_PATH_TAG = "MapPath";
    public static final String MENUGRIDLOADER_PATH_TAG = "MenuGridPath";
    public static final String MENULOADER_PATH_TAG = "MenuPath";
    public static final String MOUSECLICKLOADERPATH_TAG = "MouseClickPath";
    public static final String MOUSELOADER_PATH_TAG = "MousePath";
    public static final String EFFECT_PATH_TAG = "EffectPath";
    
    // 4 tick delay
    public static final int FOUR_TICKS = 4;
    

    public static final String ATTACK_PROPERTY="attack";
    public static final String ATTACKNAME_PROPERTY="attackName";
    public static final String BACKGROUND_PROPERTY="background";
    public static final String CHARACTERNAME_PROPERTY="charName";
    public static final String CHARACTER_PROPERTY="character";
    public static final String DURATION_PROPERTY="duration";
    public static final String DEFAULT_PROPERTY="default";
    public static final String DAMAGE_PROPERTY="damage";
    public static final String EFFECTNAME_PROPERTY="effectName";
    public static final String EFFECT_PROPERTY="effect";
    public static final String ENVIRONMENTOBJECT_PROPERTY="environmentObject";
    public static final String ENVIRONMENTOBJECTNAME_PROPERTY="objectName";
    public static final String MAPNAME_PROPERTY="mapName";
    public static final String MAP_PROPERTY="map";
    public static final String STATE_PROPERTY="state";
    public static final String STARTINGPOS_PROPERTY="startingPos";
    public static final String XCOORD_PROPERTY="xCoord";
    public static final String YCOORD_PROPERTY="yCoord";
    public static final String XSIZE_PROPERTY="xSize";
    public static final String YSIZE_PROPERTY="ySize";
    public static final String GRIDNUM_PROPERTY="gridnum";
    public static final String IMAGE_PROPERTY="image";
    public static final String FRAME_PROPERTY="frame";
    public static final String NAME_PROPERTY="name";
    public static final String MENUMODE_PROPERTY = "menumode";
    public static final String MENUNAME_PROPERTY = "menuname";
    public static final String	MENUOBJECT_PROPERTY = "menuobject";
    public static final String MENUOBJECTNAME_PROPERTY = "menuobjectname";
    public static final String WIDTH_PROPERTY="width";
    public static final String HEIGHT_PROPERTY="height";
    public static final String UP_PROPERTY="up";
    public static final String DOWN_PROPERTY="down";
    public static final String RIGHT_PROPERTY="right";
    public static final String STATENAME_PROPERTY="stateName";
    public static final String LEFT_PROPERTY="left";
    public static final String NEXTSTATE_PROPERTY="nextState"; 
    public static final String MOUSE_OBJECT="mouseobject";
    public static final String CORNERX_PROPERTY="cornerX";
    public static final String CORNERY_PROPERTY="cornerY";
    public static final String HITBOX_PROPERTY="hitbox";
    public static final String MOVESPEED_PROPERTY="movespeed";
    public static final String JUMPFACTOR_PROPERTY="jumpfactor";
    public static final String MASS_PROPERTY="mass";
    public static final String MAXHEALTH_PROPERTY="maxHealth";
    
    public static final String BLANK="fighter/images/blank.gif";
    public static final String STAND="stand";
    
    

    public static final String NO_CLASS_EXCEPTION="No Such Class";
    public static final String PARSING_ERROR="Parsing error/file not found for ";
    
    public static final String PROPERTYSET_ERROR1="Property ";

    public static final String PROPERTYSET_ERROR2=" not set.  Default value is ";
    
    public static final String DELIMITER= ",";
    /**
     * The String representation of the location of the resource that will be loaded into myResources.
     */
	public static final String RESOURCE_OBJECT_PATH = "config.objects";
    /**
     * The String representation of the location of the resource that will be loaded into myDefaults.
     */
	public static final String RESOURCE_DEFAULT_VALUE_PATH="config.defaultvalues";
    /**
     * The String representation of the location of the resource that will be loaded into myProperties.
     */
	public static final String RESOURCE_PROPERTIES_PATH="config.propertyfields";
    
}
