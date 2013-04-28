package arcade.exceptions;

import arcade.view.TextKeywords;

/**
 * If the payment method that the user tries to enter is invalid and the transaction
 * cannot be made, this exception is thrown
 * 
 * 
 * @author Ellango
 *
 */
@SuppressWarnings("serial")
public class InvalidPaymentException extends Exception {
    @Override
    public String getLocalizedMessage () {
        return TextKeywords.PAYMENT_ERROR;
    }
}
