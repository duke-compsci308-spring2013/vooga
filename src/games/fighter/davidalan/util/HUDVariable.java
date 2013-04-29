package games.fighter.davidalan.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Use this to annotate CLASS MEMBERS ONLY.
 * This annotation is used with HUDFactory to produce HUDElements that display
 * the information in the annotated member.
 * 
 * @author Wayne You
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface HUDVariable {
    /**
     * A String that can be used in various manners by HUDElements. See specific
     * HUDElements for exact use.
     * 
     * @return name
     */
    public String name();
    
    /**
     * Defines which HUDElement will be used to display the information. e.g.
     * the class for the String "Bar" would be "HUDBar".
     * 
     * @return The HUDElement suffix
     */
    public String HUDElementClass();
}
