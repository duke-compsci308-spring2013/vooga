package vooga.scroller.util;

/**
 * Renderer are able to render a specific type of objects
 * @author Dagbedji Fagnisse
 *
 * @param <T> - Domain descpriptor
 */
public interface Renderer<T> {

    void render(Renderable<T> renderable);
    
    void setRenderable(Renderable<T>renderable);
    
    Renderable<T> getRenderable();
}
