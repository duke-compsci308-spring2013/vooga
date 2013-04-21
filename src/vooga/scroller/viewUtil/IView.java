
package vooga.scroller.viewUtil;

import java.awt.Dimension;



public interface IView {

    /**
     * Process a String representing a command.
     * 
     * @param command - unprocessed object, ideally a string.
     */
    public void process(Object command);
    
    
    /**
     * Render the Renderable Object based on State.
     * 
     * @param r
     */
    public void render(Renderable r);
    
    /**
     * Provides the size of this view entity. All views are supposed to occupy
     * some space.
     */
    public Dimension getSize();

}
