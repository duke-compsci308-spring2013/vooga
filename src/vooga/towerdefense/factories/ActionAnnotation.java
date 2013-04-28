package vooga.towerdefense.factories;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)

/**
 * Action annotations are used on actions
 *      to determine what parameters the
 *      game developer should enter.
 *
 * @author Angelica Schwartz
 */
public @interface ActionAnnotation {
    public String name();
    public String value();
}