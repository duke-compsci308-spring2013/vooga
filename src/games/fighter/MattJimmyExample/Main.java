package games.fighter.MattJimmyExample;

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

        SuperFighterRunAlone control = new SuperFighterRunAlone();
        control.run();
    }
}
