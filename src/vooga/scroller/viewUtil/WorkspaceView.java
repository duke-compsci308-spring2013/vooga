
package vooga.scroller.viewUtil;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.util.Stack;
import vooga.scroller.viewUtil.Renderable;
import vooga.scroller.viewUtil.ViewConstants;
import vooga.scroller.viewUtil.Window;

/**
 * A workspace is an entity with at least a space for a Renderable 
 * and a set of tools for interacting with that Renderable.
 * @author Dagbedji Fagnisse
 *
 */
public abstract class WorkspaceView extends WindowComponent {

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
    

    public abstract void setRenderable(Renderable r);
    
    /**
     * Get the ID for this component
     * @return the id of the component
     */
    public int getID () {
        return myID;
    }
    
    
    
//TODO : Maybe not (maybe remove this one)
    public void setBackground (Image img) {
        // TODO Auto-generated method stub
        
    }

    //TODO: think about this one too
    public void toggleGrid () {
        // TODO Auto-generated method stub
        
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

}
