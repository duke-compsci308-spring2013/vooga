package vooga.rts;

import java.awt.Graphics2D;


/**
 * The GameLoop interface provides the update and paint method to
 * everything that is part of the game. Anything that can be seen
 * or changes in the game will implement the GameLoop.
 * 
 * @author Jonathan Schmidt
 * 
 */
public interface IGameLoop {

    /**
     * Updates the entity every time the game is updated.
     * 
     * @param elapsedTimethe amount of time that has occurred since the last update tick.
     */
    public void update (double elapsedTime);

    /**
     * Paints the entity every time the game is repainted.
     * 
     * @param pen The graphics object that is used to paint on the canvas.
     */
    public void paint (Graphics2D pen);
}
