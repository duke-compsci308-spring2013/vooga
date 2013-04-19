package arcade.view;

import java.awt.event.KeyListener;
import java.util.ResourceBundle;
import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import arcade.model.Model;

@SuppressWarnings("serial")
/**
 * Represents a form that a user has to fill out that also contains fields for
 * username and password 
 * 
 * @author Ellango
 *
 */
public abstract class Account extends Form {
    private JTextField myUserNameTextField;
    private JPasswordField myPasswordTextField;
    

    /**
     * Create an AccountView with text fields for username and password.
     * 
     * @param model
     * @param resources
     */
    public Account (Model model, ResourceBundle resources) {
        super(model, resources);
    }

    /**
     * Create the label and text field for the username entry
     * 
     * @return
     */
    protected JComponent createUsernameField () {
        myUserNameTextField = new JTextField();
        return createTextPanel(TextKeywords.USERNAME, myUserNameTextField);
    }

    /**
     * Create the label and text field for the password entry.
     * 
     * @return
     */
    protected JComponent createPasswordField () {
        myPasswordTextField = new JPasswordField();
        return createTextPanel(TextKeywords.PASSWORD, myPasswordTextField);
    }
    
    /**
     * Gets the typed in username.
     * 
     * @return
     */
    protected String getUsername() {
        return myUserNameTextField.getText();

    }
    
    /**
     * Gets the typed in password.
     * @return
     */
    protected String getPassword() {
        return new String(myPasswordTextField.getPassword());
    }

    /**
     * Clears the text in the username field.
     */
    protected void clearUsername() {
        myUserNameTextField.setText("");
    }
    
    /**
     * Clears the text in the password field.
     */
    protected void clearPassword() {
        myPasswordTextField.setText("");
    }
    
    /**
     * Sets a key listener on password field.  For instance, one can add the 
     * ability so that if the user hits enter in the password field, some method
     * can be called.
     */
    protected void setPasswordFieldListener(KeyListener listener) {
        myPasswordTextField.addKeyListener(listener);
    }
}
