
package vooga.scroller.util.mvc.vcFramework;

import java.awt.Dimension;
import vooga.scroller.util.Renderer;
import vooga.scroller.util.mvc.IView;

/**
 * A workspace is an entity with at least a space for a Renderable 
 * and a set of tools for interacting with that Renderable.
 * @param <D> is the Domain Descriptor. A domain descriptor
 * @author Dagbedji Fagnisse
 *
 */
public abstract class WorkspaceView<D extends IDomainDescriptor> 
                                        extends WindowComponent<D> 
                                      implements Renderer<D>{

    /**
     * 
     */
    private static final long serialVersionUID = 2039042992476659779L;
    private static final Double DEF_WIDTH_RATIO = .95;
    private static final Double DEF_HEIGHT_RATIO = .9;
    private int myID;
    private Dimension mySize = ViewConstants.DEFAULT_TAB_SIZE;

    /**
     * Create a new workspace in the specified window
     * @param hostWindow - responsible view
     */
    public WorkspaceView (IView<D> hostWindow) {
        super(hostWindow, DEF_WIDTH_RATIO, DEF_HEIGHT_RATIO);
        setPreferredSize(mySize);
    }

    /**
     * Create a new workspace in the specified window, with the specififed id
     * @param id - integer identifier
     * @param hostWindow - responsible view
     */
    public WorkspaceView (int id, IView<D> hostWindow) {
        this(hostWindow);
        myID = id; 
    }
    
    
    /**
     * Get the ID for this component
     * @return the id of the component
     */
    public int getID () {
        return myID;
    }
    

    /**
     * TODO - unimplemented
     */
    public void undo () {
        
    }

    /**
     * TODO - unimplemented
     */
    public void redo () {
        
    }

    protected void setID (int id) {
        myID = id;
    }
    
    /**
     * Take in a string and send it to Window to process it as a command.
     * @param o - The object to be processed
     */
    @SuppressWarnings("unchecked")
    @Override
    public void process (Object o) {
        ((Window<?, D, ?, ?>) getResponsible()).process(o);
    }
    
}
