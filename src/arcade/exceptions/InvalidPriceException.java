package arcade.exceptions;

import arcade.view.TextKeywords;

/**
 * This exception is thrown if an illegal price is entered when publishing a game.
 * 
 * @author Ellango
 *
 */
@SuppressWarnings("serial")
public class InvalidPriceException extends Exception {
    @Override
    public String getLocalizedMessage () {
        return TextKeywords.PRICE_ERROR;
    }
}
