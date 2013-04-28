package arcade.model.payment;

import arcade.exceptions.InvalidPaymentException;

/**
 * A payment manager represents a system for taking in payment info 
 * (e.g. credit card, PayPal, Bitcoin)
 * 
 * The payment manager will take these info and perform the transaction, which
 * behavior is determined by implementations.  For example, a 
 * CreditCardPaymentManager would use take these info, and connect through the
 * network to the Visa payment system.
 * 
 * 
 * @author Ellango
 *
 */
public interface PaymentManager {
    /**
     * Takes the paymentInfo and tries to do the transaction.
     * 
     * If the paymentInfo is not valid and the transaction cannot be made, then
     * a InvalidPaymentException is thrown.
     */
    void doTransaction(String ... paymentInfo) throws InvalidPaymentException;
}
