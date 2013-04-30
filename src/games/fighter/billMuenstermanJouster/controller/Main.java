package games.fighter.billMuenstermanJouster.controller;

import games.fighter.billMuenstermanJouster.JousterGameManager;
import games.fighter.billMuenstermanJouster.JousterGameRunAlone;

import javax.swing.JFrame;



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
