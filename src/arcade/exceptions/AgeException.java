package arcade.exceptions;

import arcade.view.TextKeywords;

/**
 * This exception is thrown if an invalid age rating is set when publishing
 * a game.
 * 
 * @author Ellango
 *
 */
@SuppressWarnings("serial")
public class AgeException extends Exception {
    @Override
    public String getLocalizedMessage () {
        return TextKeywords.AGE_ERROR;
    }
}
