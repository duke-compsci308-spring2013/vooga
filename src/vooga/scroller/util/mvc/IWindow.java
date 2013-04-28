
package vooga.scroller.util.mvc;

import vooga.scroller.util.Renderable;
import vooga.scroller.util.mvc.vcFramework.IDomainDescriptor;
import vooga.scroller.util.mvc.vcFramework.Tools;
import vooga.scroller.util.mvc.vcFramework.WindowComponent;
import vooga.scroller.util.mvc.vcFramework.WorkspaceView;

/**
 * This specific kind of view is intended to be the higher level view component
 * on a computer with GUI support. Its implementors are able to
 * <li>instantiate multiple workspaces</li>
 * <li>define a set of Tools to be used by all instances workspaces</li>
 * W is the default "WorkspaceView" for this window
 * D is the domain descriptor
 * 
 * @author Dagbedji Fagnisse
 *
 */
public interface IWindow<W extends WorkspaceView<D>, D extends IDomainDescriptor,
R extends WindowComponent<D>, T extends Tools<D>> extends IView<D> {

    public W initializeWorkspaceView (int id, Renderable<D> m);

    public void showWorkspace (W associatedWorkspaceView, Renderable<D> m);
    
    /**
     * This is most likely to act as an initial "show." This method is intended
     * to start the window by making it visible. It is designed to be primarily used by
     * some kind of controller.
     */
    public void start();

    public void showMessageDialog (String s);

}