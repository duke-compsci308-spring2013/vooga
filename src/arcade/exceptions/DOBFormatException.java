package arcade.exceptions;

import arcade.view.TextKeywords;

/**
 * This exception is thrown if the date of birth provided at registration of a
 * new user is not correctly formatted.
 * 
 * @author Ellango
 *
 */
@SuppressWarnings("serial")
public class DOBFormatException extends Exception {
    @Override
    public String getLocalizedMessage () {
        return TextKeywords.BIRTHDATE_ERROR;
    }
}
