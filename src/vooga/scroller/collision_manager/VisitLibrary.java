package vooga.scroller.collision_manager;

import java.lang.reflect.Method;

/**
 * This is an abstract class that all VisitMethods a game designer make must extend. The one main method here 
 * is getVisitMethod which will look into the subclass for a visit method that matches a specific signature. 
 * <br>
 * <br>
 * Collision Manager takes in a type VisitLibrary in its constructor. This is part of our redesign so that the 
 * player does not need to touch the framework. 
 * 
 * 
 * @author Jay Wang
 *
 */
public abstract class VisitLibrary {

    public Method getVisitMethod(@SuppressWarnings("rawtypes") Class[] classArray) throws SecurityException, NoSuchMethodException {
        return this.getClass().getMethod("visit", classArray);
    }
}
