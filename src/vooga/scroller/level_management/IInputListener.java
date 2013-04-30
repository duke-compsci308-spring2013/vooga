package vooga.scroller.level_management;

/**
 * Interface for objects that can listen for inputs.
 * 
 * @author Scott Valentine
 *
 */
public interface IInputListener {
    
    /**
     * Gives the location in file for the .properties file used to map inputs the keywords.
     * 
     * @return The file path of the input mappings .properties file.
     */
    public String getInputFilePath();
    
    
    //public void addInputListener();
}
