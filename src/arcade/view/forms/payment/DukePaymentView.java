package arcade.view.forms.payment;

import java.util.ResourceBundle;
import javax.swing.JComponent;
import javax.swing.JTextField;
import arcade.exceptions.InvalidPaymentException;
import arcade.games.GameInfo;
import arcade.model.Model;
import arcade.view.TextKeywords;


/**
 * The view where a user can enter his/her payment information to buy a game, 
 * tailored for the DukePaymentManager.
 * 
 * @author Ellango
 * 
 */
@SuppressWarnings({"serial", "unused"})
public class DukePaymentView extends PaymentView {
    private static final String PAYMENT_MANAGER_CLASS = "DukePaymentManager";
    private JTextField myNameTextField;
    private JTextField myDukeCardTextField;

    /**
     * Constructs the DukePaymentView with a Model, ResourceBundle, 
     * and a GameInfo for the game to be bought.
     * 
     * @param model
     * @param resources
     * @param info
     */
    public DukePaymentView (Model model, ResourceBundle resources, GameInfo info) {
       super(model, resources, info, PAYMENT_MANAGER_CLASS);
    }
    
    /**
     * Create an instruction for the user for how to pay with Duke card.
     * @return
     */
    private JComponent createDukeInstruction() {
        return createInstruction(TextKeywords.DUKE_CARD_INSTRUCTION);
    }
    
    /**
     * Creates the field where the user enters his/her full name.
     * @return
     */
    private JComponent createNameField() {
        myNameTextField = new JTextField();
        return createTextPanel(TextKeywords.FULLNAME, myNameTextField);
    }
    
    /**
     * Creates the field where the user enters his/her Duke Card number.
     */
    private JComponent createDukeCardField() {
        myDukeCardTextField = new JTextField();
        return createTextPanel(TextKeywords.DUKE_CARD_NUMBER, myDukeCardTextField);
    }

    @Override
    protected String[] getPaymentInfo () {
        String[] paymentInfo = {myNameTextField.getText(),
                                myDukeCardTextField.getText()};
        return paymentInfo;
    }

}
