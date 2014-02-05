package games.fighter.fightman;

import javax.swing.JFrame;

import vooga.fighter.controller.GameManagerRunAlone;

public class Main extends JFrame
{
    /**
     * main --- where the program starts
     * 
     * @param args
     */
    public static void main (String args[])
    {

        FightManRunAlone control = new FightManRunAlone();
        control.run();
    }
}
