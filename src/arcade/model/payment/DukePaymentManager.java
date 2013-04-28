package arcade.model.payment;

import arcade.exceptions.InvalidPaymentException;

/**
 * The DukePaymentManager is a PaymentManager for which the payment is fakely
 * done by Duke FLEX points.
 * 
 * This is just a mock payment manager and no transaction actually occurs.
 * 
 * Used Null Object Design Pattern
 * 
 * 
 * @author Ellango
 *
 */
public class DukePaymentManager implements PaymentManager{

    /**
     * The format for paymentInfo should be:
     * paymentInfo[0] = price 
     * paymentInfo[1] = full name
     * paymentInfo[2] = duke card number
     * 
     */
    @Override
    public void doTransaction (String ... paymentInfo) throws InvalidPaymentException {
        System.out.println("Duke purchase successful!");
        return;
    }

    

}
