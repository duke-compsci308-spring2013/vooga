
package vooga.scroller.util;

import vooga.scroller.util.mvc.IView;
import vooga.scroller.view.IPaintable;

/**
 * Interface for renderable objects. Renderables have an object that holds 
 * their current state, and a paint method.
 * T is the type of Renderer to use preferably with this renderable.
 * @author mp, df
 *
 */
public interface Renderable<T> extends IPaintable {

    /**
     * returns the current state of the Renderable object
     * @return state-holding object
     */
    public Object getState();
    
    
    public T initializeRenderer(IView parent);
}

