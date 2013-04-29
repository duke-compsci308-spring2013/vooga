package vooga.fighter.controller;

import javax.swing.JFrame;

import vooga.fighter.controller.GameManagerRunAlone;
import games.fighter.davidalan.*;

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

        StreetFighterGameManager control = new StreetFighterGameManager();
        control.run();
    }
}
