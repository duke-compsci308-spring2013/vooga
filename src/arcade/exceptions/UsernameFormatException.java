package arcade.exceptions;

import arcade.view.TextKeywords;

@SuppressWarnings("serial")
/**
 * This exception is thrown if the username is not in a legal format
 * 
 * @author Ellango
 *
 */
public class UsernameFormatException extends Exception {
    @Override
    public String getLocalizedMessage () {
        return TextKeywords.USERNAME_FORMAT_ERROR;
    }
}
