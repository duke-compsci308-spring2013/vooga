package arcade.view;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import arcade.model.Model;


/**
 * The initial view when the arcade is launched. Allows a user to login
 * or create a new account.
 * 
 * @author Luzhuo, Ellango
 * 
 */
@SuppressWarnings("serial")
public class LoginView extends FormView {
    private static final String LOGO_FILENAME = "../resources/images/VoogaLogo.png";
    private static final int WINDOW_WIDTH = 260;
    private static final int WINDOW_HEIGHT = 240;
    // private static final int NO_KEY_PRESSED = -1;

    private JTextField myUserNameTextField;
    private JPasswordField myPasswordTextField;

    // private int myLastKeyPressed;

    /**
     * Constructs the LoginView with a Model and ResourceBundle
     * 
     * @param model to authenticate the login information
     * @param resources to display text of appropriate language on screen
     */
    public LoginView (Model model, ResourceBundle resources) {
        super(model, resources);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
    }

    @Override
    protected List<JComponent> makeComponents () {
        List<JComponent> components = new ArrayList<JComponent>();
        components.add(createLogo());
        components.add(createUsernameField());
        components.add(createPasswordField());
        components.add(createMessageArea());
        components.add(createButtons());
        return components;
    }

    /**
     * create the main logo and center it.
     */
    private JComponent createLogo () {
        JPanel panel = new JPanel();
        ImageIcon headIcon = new ImageIcon(this.getClass().getResource(LOGO_FILENAME));
        JLabel head = new JLabel(headIcon);
        panel.add(head);
        return panel;
    }

    /**
     * Create the label and text field for the username entry
     * 
     * @return
     */
    private JComponent createUsernameField () {
        myUserNameTextField = new JTextField();
        return createTextPanel(TextKeywords.USERNAME, myUserNameTextField);
    }

    /**
     * Create the label and text field for the password entry.
     * 
     * @return
     */
    private JComponent createPasswordField () {
        myPasswordTextField = new JPasswordField();
        myPasswordTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed (KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    tryLogin();
                }
            }
        });
        return createTextPanel(TextKeywords.PASSWORD, myPasswordTextField);
    }

    /**
     * Create the login and register buttons.
     * 
     * @return
     */
    private JComponent createButtons () {
        JPanel buttonPanel = new JPanel();

        JButton login = new JButton(getResources().getString(TextKeywords.LOGIN));
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent arg0) {
                tryLogin();
            }
        });
        buttonPanel.add(login);

        JButton register = new JButton(getResources().getString(TextKeywords.REGISTER));
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent arg0) {
                // TODO: would prefer to call model here to check if username/password okay.
                RegisterView register = new RegisterView(getModel(), getResources());
                register.send(myUserNameTextField.getText(),
                              new String(myPasswordTextField.getPassword()));
                dispose();
            }
        });
        buttonPanel.add(register);

        resizeComponentWidths(buttonPanel);
        return buttonPanel;
    }

    /**
     * Finds the maximum preferred width for all components in a container,
     * and then resizes all components to match that width.
     * 
     * @param container
     */
    private void resizeComponentWidths (Container container) {
        int max = 0;
        for (Component c : container.getComponents()) {
            int width = c.getPreferredSize().width;
            if (width > max) {
                max = width;
            }
        }

        for (Component c : container.getComponents()) {
            c.setPreferredSize(new Dimension(max, c.getPreferredSize().height));
        }
    }

    /**
     * Sends the inputs to the model to try logging in.
     */
    private void tryLogin () {
        String usernameInput = myUserNameTextField.getText();
        String passwordInput = new String(myPasswordTextField.getPassword());
        resetTextFields();
        getModel().authenticate(usernameInput, passwordInput);
    }

    /**
     * Clears the input text fields.
     */
    private void resetTextFields () {
        myUserNameTextField.setText("");
        myPasswordTextField.setText("");
    }

    // /**
    // * create KeyListener: listen to "Enter" key
    // * Reset the myLastKeyPressed to -1 after the key is released
    // *
    // * @return
    // */
    // private KeyAdapter createKeyAdapter () {
    // KeyAdapter keyAdapter = new KeyAdapter() {
    // @Override
    // public void keyPressed (KeyEvent e) {
    // myLastKeyPressed = e.getKeyCode();
    // if (myLastKeyPressed == KeyEvent.VK_ENTER) {
    // tryLogin();
    // }
    // }
    //
    // @Override
    // public void keyReleased (KeyEvent e) {
    // myLastKeyPressed = NO_KEY_PRESSED;
    // }
    // };
    // return keyAdapter;
    // }
}
