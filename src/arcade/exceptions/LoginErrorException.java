package arcade.exceptions;

import arcade.view.TextKeywords;

/**
 * This exception is thrown if the username and password do not match a registered
 * user.
 * 
 * @author Ellango
 *
 */
@SuppressWarnings("serial")
public class LoginErrorException extends Exception{
    @Override
    public String getLocalizedMessage () {
        return TextKeywords.LOGIN_ERROR;
    }
}
