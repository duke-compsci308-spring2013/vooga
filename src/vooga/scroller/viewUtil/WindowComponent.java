
package vooga.scroller.viewUtil;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

/**
 * This class is responsible for refining JPanel and 
 * making it convenient to use with a specific Layout Manager
 * (it could have been called as well SLogoViewContainer)
 * It is also responsible for enforcing the chain of responsibility all
 * the way to the controller
 * 
 * @author Ross Cahoon, Dagbedji Fagnisse
 *
 */
@SuppressWarnings("serial")
public abstract class WindowComponent extends JPanel implements IView {

    private GridBagConstraints myConstraints;
    private IView myParent;

    
    
    /**
     * Constructor for WindowView
     * @param parent the parent of the View being created
     */
    private WindowComponent (IView parent) {
        myParent = parent;
    }
    
    private Dimension getDefaultSize (double w, double h) {
        // TODO Auto-generated method stub
        Dimension base = myParent.getSize();
        int width = (int) (base.getWidth()*w);
        int height = (int) (base.getHeight()*h);
        Dimension rel = new Dimension (width, height);
        return rel;
    }


    public WindowComponent (IView parent, double relativeWidth, double relativeHeight) {
        this(parent);
        Dimension rel = getDefaultSize(relativeWidth, relativeHeight);
        setDefaultSize(rel);
        initializeVariables(); 
        addComponents();

        this.setBorder(ViewConstants.DEFAULT_BORDER);
    }
    
    public WindowComponent (IView parent, Dimension size) {
        this(parent);
        setDefaultSize(size);
        initializeVariables(); 
        addComponents();

        this.setBorder(ViewConstants.DEFAULT_BORDER);
    }
    
    private void setDefaultSize(Dimension d) {
        this.setSize(d);
        this.setPreferredSize(d);
        this.setMinimumSize(d);
    }
    
//    /**
//     * Returns the parent component of this component
//     */    
//    public IModel getParent() {
//        return myParent;
//    }
    
    public void processCommand(String cmd) {
        myParent.processCommand(cmd);
    }

    
//    private void setLayoutManager() {
//        this.setLayout(new GridBagLayout());
//        myConstraints = new GridBagConstraints();
//        this.setBorder(ViewConstants.DEFAULT_BORDER);
//    }
    
    /**
     * 
     * @return the constraints for this Layout
     */
    protected GridBagConstraints getConstraints() {
        return myConstraints;
    }

    /**
     * Initialize components variables instances variables 
     * - Template step used in the constructor
     */
    protected abstract void initializeVariables ();

    /**
     * Add the components previously initialized according to layout rules
     * - Template step used in the constructor
     */
    protected abstract void addComponents ();
}


