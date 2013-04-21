package arcade.view;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import arcade.model.Model;
import arcade.util.JPicture;


/**
 * The initial view when the arcade is launched. Allows a user to login
 * or create a new account.
 * 
 * @author Luzhuo, Ellango
 * 
 */
@SuppressWarnings("serial")
public class LoginView extends JFrame {

    private static final String IMAGES_LOCATION = "src/arcade/resources/images/";
    private static final String ERROR_MESSAGE = "Required file is not present.";
    /**
     * 
     */
    private static final String BACKGROUND_FILENAME = "arcade/resources/images/LoginBackGround.jpg";
    private static final String LOGO_FILENAME = "VoogaLogo.png";
    private static final String TITLE_KEYWORD = "title";
    private static final String LOGIN_KEYWORD = "login";
    private static final String REGISTER_KEYWORD = "new_account";
    private static final String USERNAME_KEYWORD = "username";
    private static final String PASSWORD_KEYWORD = "password";
    public static final int WINDOW_WIDTH = 300;
    public static final int WINDOW_HEIGHT = 240;
    private static final int HEADLINE_WIDTH = 170;
    private static final int HEADLINE_HEIGHT = 70;
    private static final int OFFSET = HEADLINE_WIDTH / 2;
    private static final int NO_KEY_PRESSED = -1;

    /**
     * 
     */
    private Model myModel;
    private ResourceBundle myResources;
    private JPanel myContentPanel;
    private JTextField myUserNameTextField;
    private JPasswordField myPasswordTextField;
    private JLabel myWarningMessage;
    private JComponent myBackground;
    private int myLastKeyPressed;

    /**
     * Constructor
     * 
     * @param language
     */
    public LoginView (Model model, ResourceBundle rb) {
        myModel = model;
        myResources = rb;

        setTitle(myResources.getString(TITLE_KEYWORD));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        myContentPanel = (JPanel) getContentPane();
        myContentPanel.setLayout(null);

        createHeadLine();
        createTextLabels();
        createTextFields();
        createLoginButton();
        createRegisterButton();

        createMessageArea();
        createBackground();

        setResizable(false);
        setVisible(true);
    }

    /**
     * create Headline
     */
    private void createHeadLine () {
        ImageIcon headlineIcon = createImageIcon(LOGO_FILENAME);
        JLabel headline = new JLabel(headlineIcon);
        headline.setBounds(WINDOW_WIDTH / 2 - OFFSET, OFFSET / 20, HEADLINE_WIDTH, HEADLINE_HEIGHT);
        myContentPanel.add(headline);
    }

    /**
     * create the username and password labels
     */
    private void createTextLabels () {
        String usernameDescription = myResources.getString(USERNAME_KEYWORD);
        String passwordDescription = myResources.getString(PASSWORD_KEYWORD);
        JLabel username = new JLabel("<html><b>" + usernameDescription + "</b></html>");
        username.setBounds(50, 70, 80, 25);
        JLabel password = new JLabel("<html><b>" + passwordDescription + "</b></html>");
        password.setBounds(50, 100, 80, 25);
        myContentPanel.add(username);
        myContentPanel.add(password);
    }

    /**
     * create the text field. KeyListener added along the way
     */
    private void createTextFields () {
        // UserNameTextField
        myUserNameTextField = new JTextField();
        myUserNameTextField.setBounds(145, 70, 100, 25);
        myUserNameTextField.addKeyListener(createKeyAdapter());

        // PasswordTextField
        myPasswordTextField = new JPasswordField();
        myPasswordTextField.setBounds(145, 100, 100, 25);
        myPasswordTextField.addKeyListener(createKeyAdapter());
        resetTextFields();
        myContentPanel.add(myUserNameTextField);
        myContentPanel.add(myPasswordTextField);
    }

    /**
     * Login button created. ActionListener added along the way
     */
    private void createLoginButton () {
        JButton login = new JButton(myResources.getString(LOGIN_KEYWORD));
        login.setBounds(70, 170, 80, 25);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent arg0) {
                tryLogin();
            }
        });
        myContentPanel.add(login);
    }

    /**
     * Register button created
     */
    // TODO: Add actionlistener
    // Figure out the actual action!!
    private void createRegisterButton () {
        JButton register = new JButton(myResources.getString(REGISTER_KEYWORD));
        register.setBounds(150, 170, 80, 25);
        myContentPanel.add(register);
    }

    /**
         * 
         */
    private void tryLogin () {
        String userNameInput = myUserNameTextField.getText();
        String userPasswordInput = new String(myPasswordTextField.getPassword());
        resetTextFields();
        myModel.authenticate(userNameInput, userPasswordInput);
    }

    private void resetTextFields () {
        myUserNameTextField.setText("");
        myPasswordTextField.setText("");
    }

    /**
     * create KeyListener: listen to "Enter" key
     * Reset the myLastKeyPressed to -1 after the key is released
     * 
     * @return
     */
    private KeyAdapter createKeyAdapter () {
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed (KeyEvent e) {
                myLastKeyPressed = e.getKeyCode();
                if (myLastKeyPressed == KeyEvent.VK_ENTER) {
                    tryLogin();
                }
            }

            @Override
            public void keyReleased (KeyEvent e) {
                myLastKeyPressed = NO_KEY_PRESSED;
            }
        };
        return keyAdapter;
    }

    private void createBackground () {
        myBackground = new JPicture(BACKGROUND_FILENAME, getSize());
        myBackground.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        myContentPanel.add(myBackground);

    }

    private void createMessageArea () {
        myWarningMessage = new JLabel();
        myWarningMessage.setBounds(50, 120, 250, 50);
        myContentPanel.add(myWarningMessage);

    }

    public void sendMessage (String message) {
        myWarningMessage.setText("<html><font color = red>" + message + "</font></html>");
    }

    public void destroy () {
        setVisible(false);
        dispose();
    }

    /**
     * 
     * @param filename
     * @return
     */
    private ImageIcon createImageIcon (String filename) {
        Image myImage;
        try {
            myImage = ImageIO.read(new File(IMAGES_LOCATION + filename));
            return new ImageIcon(myImage, filename);
        }
        catch (IOException e) {
            throw new MissingResourceException(ERROR_MESSAGE, "", "");
        }

    }

    // private void refreshFrame () {
    // this.setBounds(0, 0, WINDOW_WIDTH + 1, WINDOW_HEIGHT);
    // this.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    // setLocationRelativeTo(null);
    // }
}
