package vooga.scroller.util.mvc.vcFramework;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 * Class holding keyword strings for the view package
 * @author Ross
 *
 */
public class ViewConstants {
    
    /**
     * The size for Window objects
     */
    public static final Dimension DEFAULT_WINDOW_SIZE = new Dimension(1100, 700);

    /**
     * The size for TabView objects
     */
    public static final double REL_TAB_WIDTH = 1;
    public static final double REL_TAB_HEIGHT  = .5;
    public static final Dimension DEFAULT_TAB_SIZE = 
            new Dimension(((int)(DEFAULT_WINDOW_SIZE.getWidth()*REL_TAB_WIDTH)), 
                          ((int)(DEFAULT_WINDOW_SIZE.getHeight()*REL_TAB_HEIGHT)));
    
    public static final Border DEFAULT_BORDER = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    

    public static final double DEFAULT_GRIDVIEW_HEIGHT_RATIO = .95;
    public static final double DEFAULT_GRIDVIEW_WIDTH_RATIO = .7;
    public static final double DEFAULT_TOOLSVIEW_HEIGHT_RATIO = .9;
    public static final double DEFAULT_TOOLSVIEW_WIDTH_RATIO = .25;
    
    /**
     * Added to fully prevent instantiation of this utility class.
     */
    private ViewConstants() {
        
    }

}
