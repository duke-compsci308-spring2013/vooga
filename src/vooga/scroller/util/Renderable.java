package vooga.scroller.util;

import vooga.scroller.util.mvc.IView;
import vooga.scroller.view.IPaintable;


/**
 * Interface for renderable objects. Renderables have an object that holds
 * their current state, and a paint method.
 * D is the domain descriptor.
 * 
 * @author mp, df
 * 
 */
public interface Renderable<D> extends IPaintable {

    public Renderer<D> initializeRenderer (IView<?> parent);
}
