package games.fighter.billMuenstermanJouster;

import javax.swing.JFrame;

import vooga.fighter.controller.GameManagerRunAlone;


/**
 * Creates window that can be moved, resized, and closed by the user.
 * 
 * @author Jack Matteucci
 */
public class Main extends JFrame
{
    /**
     * main --- where the program starts
     * 
     * @param args
     */
    public static void main (String args[])
    {
        JousterGameRunAlone control = new JousterGameRunAlone();
        control.run();
    }
}
