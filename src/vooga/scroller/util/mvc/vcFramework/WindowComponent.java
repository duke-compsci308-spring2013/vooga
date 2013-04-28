
package vooga.scroller.util.mvc.vcFramework;
import java.awt.Dimension;
import javax.swing.JMenu;
import javax.swing.JPanel;
import vooga.scroller.util.mvc.IView;

/**
 * This class is responsible for refining JPanel 
 * It is also responsible for simplifying and 
 * enforcing the chain of responsibility all
 * the way to the higher level window
 * D - the domain descriptor, also enforces chain of responsibility
 * @author Ross Cahoon, Dagbedji Fagnisse
 *
 */
@SuppressWarnings("serial")
public abstract class WindowComponent<D extends IDomainDescriptor> 
                extends JPanel implements IView<D> {

    private IView<D> myResponsible;

    
    
    /**
     * Constructor for WindowView
     * @param parent the parent of the View being created
     */
    private WindowComponent (IView<D> parent) {
        myResponsible = parent;
        this.setBorder(ViewConstants.DEFAULT_BORDER);
    }
    
    /**
     * Construct a WindowComponent with the specified parent and a 
     * size given in pixels.
     * @param parent - container responsible for this WindowComponent
     * @param size - pixels width and height for the component being created
     */
    public WindowComponent (IView<D> parent, Dimension size) {
        this(parent);
        setDefaultSize(size);
    }
    
    /**
     * Construct a WindowComponent with the specified parent and the relative 
     * dimensions specified.
     * @param parent - to be responsible for this component
     * @param relativeWidth - 1 is same value as the parent.
     * @param relativeHeight - 1 is same size as the parent.
     * TODO - if size is greater than 1, automatically enable scrolling (?)
     */
    public WindowComponent (IView<D> parent, double relativeWidth, double relativeHeight) {
        this(parent);
        Dimension rel = getDefaultSize(relativeWidth, relativeHeight);
        setDefaultSize(rel);   
    }

    
    /**
     * Compute the absolute dimension based on the relative width and height 
     * provided.
     * @param w
     * @param h
     * @return
     */
    private Dimension getDefaultSize (double w, double h) {
        Dimension base = myResponsible.getSize();
        int width = (int) (base.getWidth() * w);
        int height = (int) (base.getHeight() * h);
        Dimension rel = new Dimension(width, height);
        return rel;
    }
    
    /**
     * Identify the ViewComponent responsible for this one.
     * @return - responsible ViewComponent.
     */
    protected IView<D> getResponsible() {
        return myResponsible;
    }
    
    @Override
    public void process(Object cmd) {
        getResponsible().process(cmd);
    }
    
    /**
     * Ensure that the size is consistently specified.
     * @param d
     */
    private void setDefaultSize(Dimension d) {
        this.setSize(d);
        this.setPreferredSize(d);
        this.setMinimumSize(d);
    }
    
    public String getLiteral (String string) {
        return myResponsible.getLiteral(string);
    }

    public void registerMenu (JMenu jMenu) {
        myResponsible.registerMenu(jMenu);
        
    }
    
    @Override
    public void showMessageDialog (String message) {
        myResponsible.showMessageDialog (message);
    }
}


