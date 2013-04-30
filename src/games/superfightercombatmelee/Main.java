package games.superfightercombatmelee;


import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import vooga.fighter.view.Canvas;
import vooga.fighter.controller.*;

/**
 * Creates window that can be moved, resized, and closed by the user.
 *
 * @author Sean Wareham, Jerry Li
 */
public class Main extends JFrame
{
    // constants
    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String TITLE = "FightingGame";


    /**
     * main --- where the program starts
     * @param args
     */
    public static void main (String args[])
    {
        // view of user's content
        
        // start animation
        GameManager control = new SubGameManager();
        control.run();
        // Stopping the game is handled in the MarioTime Class, as it can implement methods that update 
        // as the game updates
        // Full Screen Options are handled in the Canvas Class (as they must be updated with the game).
    }
}

//We spent the majority of our time discussing feedback.  Such that in order to actually implement a new game, it seems as though an inordinate number of
//classes need to be subclassed.  This design is not very extension friendly in that if a new feature is to be added, it seems as though many tertiarily modified classes
//need to be made aware of such additions.  As such, the design is more modifiable than it is extensible.  As in to write a new character for instance, it is
//easy to modify the existing character, but it is not as easy to add a whole new character and alert the simulation to such a change