
package vooga.scroller.level_editor.view;

import javax.swing.JOptionPane;
import vooga.scroller.level_editor.controllerSuite.LEController;
import vooga.scroller.level_editor.controllerSuite.LETools;
import vooga.scroller.util.Renderable;
import vooga.scroller.util.mvc.vcFramework.Window;

/**
 * This class is a specialized Window (using the vcFramework) for LevelEditing.
 * As such it has the type parameters:
 * <li> LEWorkspaceView as the default workspace supported. </li>
 * <li> LevelEditing as the Domain Specific main resource. </li>
 * <li> LEGridView as the main renderer.</li>
 * <li> LETools as the toolbox that can be used to operate on the workspace items.</li>
 * @author Dagbedji Fagnisse
 */
public class LEView extends Window<LEWorkspaceView, LevelEditing, LEGridView, LETools> {
    

    private static final long serialVersionUID = 1L;
    private static final String TITLE = "Level Editor";
    private static final String SIMULATION_ERROR_MESSAGE = "SimulationError";
    
    /**
     * Default constructor - build a Window with the specified language and controller.
     * @param language
     * @param lEController
     */
    public LEView (String language, LEController lEController) {
        super(TITLE, language, lEController);
    }

    @Override
    public LEWorkspaceView initializeWorkspaceView (int id, Renderable<LEGridView> r) {
        LEWorkspaceView res = new LEWorkspaceView(this, id, r);
        return res;
    }

    @Override
    protected void setMenu () {
        super.setMenu(new LEMenuBar(this));
    }
    
    /**
     * Get the active tab and simulate it if it is valid
     * @param tab
     */
    private void simulate (LEWorkspaceView tab) {
        if (tab.isValidForSimulation()) {
            tab.getRenderable().simulate();
        }
        else 
            JOptionPane.showMessageDialog(this, SIMULATION_ERROR_MESSAGE);
    }

    /**
     * Simulate the existing workspace data
     */
    public void simulate () {
        if (super.getActiveTab() instanceof LEWorkspaceView) {
            simulate(super.getActiveTab());
        }
    }


    @Override
    public void setDefaultWorkspaceTools (LETools t) {
        LEWorkspaceView.setTools(t); //TODO
    }


}
