package arcade.exceptions;

import arcade.view.TextKeywords;

/**
 * This exception is thrown if someone tries to register an account and the
 * username is already taken
 * 
 * @author Ellango
 *
 */
@SuppressWarnings("serial")
public class UsernameTakenException extends Exception {
    @Override
    public String getLocalizedMessage () {
        return TextKeywords.USERNAME_TAKEN_ERROR;
    }
}
