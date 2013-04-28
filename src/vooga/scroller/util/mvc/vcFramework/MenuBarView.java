
package vooga.scroller.util.mvc.vcFramework;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JSeparator;
import javax.swing.Timer;
import vooga.scroller.level_editor.view.LEActionLibrary;


/**
 * This class is responsible for setting up the menu bar for a specific window.
 * @author Ross Cahoon, Dagbedji Fagnisse
 *
 */
@SuppressWarnings("serial")
public abstract class MenuBarView extends JMenuBar {
    private static final int DEFAULT_DELAY = 100;
    private Window myWindow;
    private WindowActionLibrary myActionLibrary;
    private LEActionLibrary myWSActionLibrary;
    private JMenu myFileMenu;
    private JMenu myEditMenu;
    private Timer myTimer;
    
    /**
     * Constructor for MenuBarView
     * @param window the parent view that component is inside of
     */
    
    public MenuBarView(Window window) {
        myWindow = window;
        myActionLibrary = new WindowActionLibrary(myWindow);
        setSpecializedWindow(myWindow);
        addComponents();
        ActionListener prefListener =  new ActionListener() {
            @Override
			public void actionPerformed (ActionEvent e) {
                if (myWindow.getTabCount() > 0) {
                    enableWorkspaceDependentsMenus();
                }
            }
        };
        myTimer = new Timer(DEFAULT_DELAY, prefListener);
        myTimer.start();
    }

    /**
     * Any 
     */
    protected void addComponents () {
            this.add(makeFileMenu());
            this.add(makeEditMenu());
            this.addCustomMenus();
    }
    
    protected abstract void setSpecializedWindow(Window w);

    protected abstract void addCustomMenus ();

    /**
     * This menu is a generalized menu that handles all File actions.
     * @return - File Menu
     */
    private JMenu makeFileMenu() {
        JMenu result = new JMenu(Window.getResources().getString("FileMenu"));
        result.setMnemonic(KeyEvent.VK_F);
        result.add(myActionLibrary.new NewTabAction());
        result.add(myActionLibrary.new OpenFileAction());
        result.add(myActionLibrary.new SaveFileAction());
        result.add(new JSeparator());
        result.add(myActionLibrary.new QuitAction());
        myFileMenu = result;
        return myFileMenu;
    }
    
    /**
     * This menu mostly handles actions that apply to the whole active workspace.
     * @return
     */
    private JMenu makeEditMenu() {
        JMenu result = new JMenu(Window.getResources().getString("EditMenu"));
        result.setMnemonic(KeyEvent.VK_E);
        result.add(myActionLibrary.new UndoAction());
        result.add(myActionLibrary.new RedoAction());
        result.setEnabled(false);
        myEditMenu = result;
        return myEditMenu;
    }
    
    /**
     * This menu handles actions that apply primarily to the current domain-specific
     * Renderable. 
     * @return
     */
    protected abstract JMenu makePreferencesMenu();
    
    /**
     * This menu handles actions that provide help resources to the user. 
     * @return
     */
    protected abstract JMenu makeHelpMenu();
    
    /**
     * Make the preferences menu active
     */
    private void enableWorkspaceDependentsMenus() {
        for (JMenu c: getWorkspaceMenus()) {
            c.setEnabled(true);
        }
        myEditMenu.setEnabled(true);
    }

protected abstract List<JMenu> getWorkspaceMenus ();

    
}
