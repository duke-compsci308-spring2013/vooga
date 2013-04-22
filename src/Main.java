import java.util.ResourceBundle;
import arcade.model.Model;
import arcade.view.forms.LoginView;

/**
 * The one class that starts the entire project.
 * 
 * @author The Arcade Team
 */
public class Main {
    
    private static final String RESOURCE_LOCATION = "arcade.resources.";
    private static final String LANGUAGE = "English";
    
    
    private Main () {
        // does not make sense to construct this class
    }

    /**
     * main --- where the program starts
     * 
     * @param args anything passed in from the command-line
     */
    public static void main (String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle(RESOURCE_LOCATION + LANGUAGE);
        Model model = new Model(rb, LANGUAGE);
        LoginView login = new LoginView(model, rb);
        model.setLoginView(login);
        
    }
}
