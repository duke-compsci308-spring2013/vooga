package vooga.scroller.util;

/**
 * Renderer are able to render a specific type of objects
 * @author Dagbedji Fagnisse
 *
 * @param <T> - Type to be rendered
 */
public interface Renderer<T> {

    void render(T renderable);
    
    void setRenderable(T renderable);
    
    T getRenderable();
}
