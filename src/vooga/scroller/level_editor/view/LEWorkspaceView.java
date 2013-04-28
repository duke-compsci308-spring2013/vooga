package vooga.scroller.level_editor.view;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import vooga.scroller.level_editor.commands.CommandConstants;
import vooga.scroller.level_editor.controllerSuite.LEGrid;
import vooga.scroller.level_editor.controllerSuite.LETools;
import vooga.scroller.util.Renderable;
import vooga.scroller.util.Renderer;
import vooga.scroller.util.mvc.vcFramework.Tools;
import vooga.scroller.util.mvc.vcFramework.WorkspaceView;
import vooga.scroller.viewUtil.EasyGridFactory;


/**
 * This class is a specialized Workspace for a Level Editor.
 * 
 * @author Dagbedji Fagnisse
 * 
 */
public class LEWorkspaceView extends WorkspaceView<LevelEditing> implements Renderer<LEGrid>{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private LEGridView myGridView;
    private LEToolsView myToolsView;
    private JScrollPane myLevelGridScroller;
    private JScrollPane myToolsScroller;
    private static LETools ourTools;

    /**
     * Create a Workspace with the specified host, id, and renderable
     * 
     * @param host - parent containing this workspace, typically a Window
     * @param id - containing tab
     * @param r - Renderable to be loaded in this workspace.
     */
    public LEWorkspaceView (LEView host, int id, 
                            Renderable<LEGridView> grid) {
        super(id, host);
        myGridView = grid.initializeRenderer(this);
        myToolsView = ourTools.initializeRenderer(this);
        myLevelGridScroller = new JScrollPane(myGridView,
                                              ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                                              ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        myToolsScroller = new JScrollPane(myToolsView,
                                              ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                                              ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        EasyGridFactory.layout(this, myLevelGridScroller, myToolsScroller);
    }

    @Override
    public void process (Object isn) {
        if (isn instanceof String) {
            String cmd = (String) isn;
            if (getCommand(cmd).equals(CommandConstants.CREATE_SPRITE)) {
                cmd = cmd + CommandConstants.SPACE + myToolsView.getSelectedSpriteID();
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

    // TODO - Good design choice??
    public static void setTools (Tools t) {
        ourTools = (LETools) t;
    }

    public boolean isValidForSimulation () {
        return myGridView.isValidForSimulation();
    }


    @Override
    public void render (LEGrid grid) {
        myGridView.render(grid);
    }

    @Override
    public void setRenderable (LEGrid grid) {
        myGridView.setRenderable(grid);
        
    }

    @Override
    public LEGrid getRenderable () {
        return myGridView.getRenderable();
    }



    @Override
    public void setRenderable (Renderable<?> m) {
        if (m instanceof LEGrid)
        myGridView.setRenderable((LEGrid) m);
        else {} //TODO
    }

    @Override
    public void render (Renderable r) {
        myGridView.render(r);
    }

}
