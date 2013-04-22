package arcade.exceptions;

/**
 * This runtime exception is thrown if a PaymentView cannot be constructed
 * after selecting a payment option from the PaymentSelection view.  This construction
 * of the PaymentView occurs through reflection, so a failure represents an improperly
 * written PaymentView class.
 * 
 * @author Ellango
 *
 */
@SuppressWarnings("serial")
public class UndefinedPaymentException extends RuntimeException {}
