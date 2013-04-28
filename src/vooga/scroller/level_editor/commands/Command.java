
package vooga.scroller.level_editor.commands;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;


/**
 * Marker Annotation. Marks a method as a Command.
 * Can be obtained through reflection at run time.
 * 
 * @author Danny Goodman
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Command {

}
