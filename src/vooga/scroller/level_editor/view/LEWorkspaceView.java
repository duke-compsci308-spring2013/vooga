package vooga.scroller.level_editor.view;

import javax.swing.JScrollPane;
import vooga.scroller.level_editor.LevelEditing;
import vooga.scroller.level_editor.commands.CommandConstants;
import vooga.scroller.util.Renderable;
import vooga.scroller.util.Renderer;
import vooga.scroller.util.mvc.vcFramework.TabbedToolsView;
import vooga.scroller.util.mvc.vcFramework.Tools;
import vooga.scroller.util.mvc.vcFramework.WorkspaceView;
import vooga.scroller.viewUtil.EasyGridFactory;


/**
 * This class is a specialized Workspace for a Level Editor.
 * 
 * @author Dagbedji Fagnisse
 * 
 */
public class LEWorkspaceView extends WorkspaceView<LevelEditing> 
                            implements Renderer<LevelEditing> {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private LEGridView myGridView;
    private TabbedToolsView<LevelEditing> myToolsView;
    private JScrollPane myLevelGridScroller;
    private JScrollPane myToolsScroller;

    /**TODO - This class could use a bit of redesign to reduce the 
     * amount of casts used.
     * Create a Workspace with the specified host, id, and renderable
     * 
     * @param host - parent containing this workspace, typically a Window
     * @param id - containing tab
     * @param grid - renderable to be loaded in this workspace.
     * @param tools - tools to be used in editing
     */
    public LEWorkspaceView (LEView host, int id, 
                            Renderable<LevelEditing> grid, Tools<LevelEditing> tools) {
        super(id, host);
        myGridView = (LEGridView) grid.initializeRenderer(this);
        myToolsView = new TabbedToolsView<LevelEditing>(tools, this);
        myLevelGridScroller = new JScrollPane((LEGridView) myGridView,
                                              JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                              JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        myToolsScroller = new JScrollPane(myToolsView,
                                              JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                              JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        EasyGridFactory.layout(this, myLevelGridScroller, myToolsScroller);
    }

    @Override
    public void process (Object isn) {
        if (isn instanceof String) {
            String cmd = (String) isn;
            if (getCommand(cmd).equals(CommandConstants.CREATE_SPRITE)) {
                cmd = cmd + CommandConstants.SPACE + myToolsView.getSelectedEditableDependent();
            }
            super.process(cmd);
        }
        else {
            super.process(isn);
        }
    }

    private String getCommand (String cmd) {
        return cmd.split(CommandConstants.SPACE)[0];
    }


    /**
     * Check for validity of this workspace for simulation
     * @return - true if the workspace complies with simulation requirements.
     */
    public boolean isValidForSimulation () {
        return ((LEGridView) myGridView).isValidForSimulation();
    }


    @Override
    public void render (Renderable<LevelEditing> grid) {
        myGridView.render(grid);
    }

    @Override
    public void setRenderable (Renderable<LevelEditing> grid) {
        myGridView.setRenderable(grid);
        
    }

    @Override
    public Renderable<LevelEditing> getRenderable () {
        return myGridView.getRenderable();
    }

    /**
     * Returns the renderer
     * @return
     */
    public Renderer<LevelEditing> getEditableRenderer () {
        return myGridView;
    }

}
