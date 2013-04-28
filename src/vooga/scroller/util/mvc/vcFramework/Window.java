
package vooga.scroller.util.mvc.vcFramework;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ResourceBundle;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import vooga.scroller.util.Renderable;
import vooga.scroller.util.mvc.IController;
import vooga.scroller.util.mvc.IWindow;
import vooga.scroller.viewUtil.EasyGridFactory;

/**
 * This implementation of IWindow is intended to be a manager for all 
 * the other view components of the application. It also has support 
 * (or enforces implementation) for common non-domain specific actions available in most GUIs.
 * Among other things, file-opening, multiple tabs, undoing and redoing...
 * By default, if no tab is specified. Using rendering methods that do not specify a
 * tab is strongly discouraged.
 * 
 * @author Ross Cahoon, Dagbedji Fagnisse
 *
 * @param <W> - The Workspace Type
 * @param <D> - The Domain Descriptor, used in enforcing 
 *      (1) Chain of responsibility and 
 *      (2) facilitating bridge
 * @param <E> - The Editable/Renderable Type
 * @param <T> - The Domain specific tools
 */
@SuppressWarnings("serial")
public abstract class Window<W extends WorkspaceView<D>, 
                             D extends IDomainDescriptor,
                             E extends WindowComponent<D>, 
                             T extends Tools<D>> 
    extends JFrame implements IWindow<W, D, E, T> {

    private static final String DEFAULT_RESOURCE_PACKAGE = "vooga.scroller.resources.";
    private static ResourceBundle ourResources;
    private static final String USER_DIR = "user.dir";
    

    private JFileChooser myChooser;
    private IController<D> myController;
    private MenuBarView myMenuBar;
    private Dimension mySize = ViewConstants.DEFAULT_WINDOW_SIZE;
    
    private JTabbedPane myTabbedPane;    

    private T myTools;

    /**
     * Constructor for Window
     * @param title The title of the View
     * @param language The display language for the window
     * @param controller The Controller responsible for this view
     * @param tools to be used with this workspace
     */
    public Window (String title, String language, IController<D> controller, T tools) {
        super(title);
        this.setResizable(false);
        setPreferredSize(mySize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myController = controller;
        // create and arrange sub-parts of the GUI
        ourResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
        myChooser = new JFileChooser(System.getProperties().getProperty(USER_DIR));
        myMenuBar = new MenuBarView(this);
        //tabs
        getContentPane().setLayout(new GridBagLayout());
        setTools(tools);
        addComponents();
    }
    
    /**
     * Helper Method available to lookup values in the Resources
     * @param s - string literal to be looked up (key)
     * @return the string literal specified in the relevant properties file
     */
    public String getLiteral(String s) {
        return Window.ourResources.getString(s);
    }
    /**
     * Gets the resource bundle
     *
     */
    public static ResourceBundle getResources() {
        return ourResources;
    }
    
    private void addComponents() {
        myTabbedPane = new JTabbedPane();
        setJMenuBar(myMenuBar);
        EasyGridFactory.layoutHorizontal(this, myTabbedPane);
    }

    /**
     * Way to initialize tab creation from the window
     */
    public void addTab() {
        myController.initializeWorkspace();
    }

    /**
     * Adds a tab to the view
     * @param tab The tab to be added
     * @param p The Renderable that it is associated with
     */
    public void addTab (W tab, Renderable<D> p) {
        myTabbedPane.addTab(getLiteral("TabTitle") + " " + (tab.getID() + 1), tab);
        tab.setRenderable(p);
        myTabbedPane.setSelectedComponent(tab);
    }

    @SuppressWarnings("unchecked")
    protected W getActiveTab() {
        return (W) myTabbedPane.getSelectedComponent();
    }

    /**
     * Returns the JFileChooser for this View
     * @return
     */
    public JFileChooser getChooser () {
        return myChooser;
    }
    /**
     * 
     * @return - number of tabs
     */
    public int getTabCount() {
        return myTabbedPane.getTabCount();
    }

    protected T getTools() {
        return myTools;
    }
    
    /**
     * Load the specified file in a new tab
     * @param file2open
     */
    private void loadFile (File file2open) {
        myController.loadFile(file2open);
    }

    /**
     * Initiate the opening of a file
     */
    public void openFile () {
        int response = myChooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            File file2open = myChooser.getSelectedFile();
            loadFile(file2open);
        }
    }

    /**
     * Called by a non TabView child view that will request a string to be processed as a Command
     * Uses the active tab
     * @param o The object to be processed
     */
    public void process (Object o) {
        process(getActiveTab(), o);
    }

    /**
     * Called by a TabView child view that will request a string to be processed as a Command
     * @param tabView The Tabview that is requesting the string to be processed
     * @param o The object to be processed
     */
    public void process (W tabView, Object o) {
        myController.process(tabView, o);
    }
    
    /**
     * Close the window
     */
    public void quit () {
        WindowEvent close = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(close);
    }
    
    /**
     * redo last (undone) action for active workspace
     */
    public void redo () {
        getActiveTab().redo();
    }

    
    /**
     * Save the active workspace
     */
    public void saveFile () {
        int response = myChooser.showSaveDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            File file2save = myChooser.getSelectedFile();
            saveFile(file2save);
        }
        return;
    }


    /**
     * Get a file from the user and delegate actual saving to controller.
     * @param file2save
     */
    private void saveFile (File file2save) {
        myController.saveFile(file2save, getActiveTab());
    }

    
    private void setTools (T tools) {
        myTools = tools;
    }
    
    /**
     * Helper to show a message to the user
     * @param message - to be shown
     */
    @Override
    public void showMessageDialog (String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    
    @Override
    public void showWorkspace (W associatedWorkspaceView, Renderable<D> r) {
        addTab(associatedWorkspaceView, r);
    }
    
    

    
    @Override
    public void start() {
        pack();
        setVisible(true);
    }
    
    
    /**
     * Undo last action for active workspace
     */
    public void undo () {
        getActiveTab().undo();
    }
    

    /**
     * 
     * @return
     */
    public abstract D getDomain();
    
    /**
     * @param jMenu - 
     */
    public void registerMenu (JMenu jMenu) {
        myMenuBar.addCustomMenu(jMenu);
    }

    protected IController<D> getController () {
        return myController;
    }
}
