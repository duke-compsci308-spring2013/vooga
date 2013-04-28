package vooga.scroller.util.mvc;

import java.io.File;
import vooga.scroller.util.mvc.vcFramework.IDomainDescriptor;
import vooga.scroller.util.mvc.vcFramework.WorkspaceView;


public interface IController<D extends IDomainDescriptor> {

    /**
     * Initialize the GUI.
     */

    public abstract void start ();

    public abstract void saveFile (File file2save, WorkspaceView<D> t);

    public abstract void loadFile (File file2open);

    /**
     * calls model to process the input string command
     * @param t - 
     * @param cmd - command to process
     * @return ret - return int from command process
     */
    public abstract void process (WorkspaceView<D> t, Object cmd);

    /**
     * Add a new workspace with id based on already existing workspaces.
     */
    public abstract void initializeWorkspace ();
    
    public D getDomainInfo();

}
