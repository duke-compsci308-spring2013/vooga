
package vooga.scroller.util.mvc.vcFramework;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import vooga.scroller.util.Renderable;
import vooga.scroller.util.mvc.IView;
import vooga.scroller.util.mvc.vcFramework.ViewConstants;
import vooga.scroller.util.mvc.vcFramework.Window;

/**
 * A workspace is an entity with at least a space for a Renderable 
 * and a set of tools for interacting with that Renderable.
 * T is the Domain Descriptor. A domain descriptor
 * @author Dagbedji Fagnisse
 *
 */
public abstract class WorkspaceView<D extends IDomainDescriptor> extends WindowComponent {

    /**
     * 
     */
    private static final long serialVersionUID = 2039042992476659779L;
    
    private int myID;
    @SuppressWarnings("unused")
    private GridBagConstraints myConstraints;
    private Dimension mySize = ViewConstants.DEFAULT_TAB_SIZE;
    private static Tools myTools;
    private static Double DEF_WIDTH_RATIO = .95;
    private static Double DEF_HEIGHT_RATIO = .9;

    public WorkspaceView (IView hostWindow) {
        super(hostWindow, DEF_WIDTH_RATIO, DEF_HEIGHT_RATIO);
        setPreferredSize(mySize);
        //TODO : for undo/redo stuff
//        myRenderableHistory = new Stack<Renderable>();
//        myRenderableBrowsingHelper = new Stack<Renderable>();
    }

    public WorkspaceView (int id, IView hostWindow) {
        // TODO Auto-generated constructor stub
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
    


    public void undo () {
        // TODO Auto-generated method stub
        
    }

    public void redo () {
        // TODO Auto-generated method stub
        
    }

    protected void setID (int id) {
        myID = id;
    }
    
    /**
     * Take in a string and send it to Window to process it as a command.
     * @param s The string to be parsed.
     */
    @Override
    public void process (Object o) {
        ((Window) getResponsible()).process(this, o);
    }
    
    //TODO - good design choice?
    public static void setTools(Tools t) {
        myTools = t;
    }
    
    public Tools getTools() {
        return myTools;
    }

    public abstract void setRenderable (Renderable<?> m);
}
