package vooga.towerdefense.model;

import vooga.towerdefense.controller.Controller;


/**
 * This class represents a game loop. It is responsible for starting,
 * stopping, and resuming the animation of the game.
 * 
 * @author Jimmy Longley
 */
public class GameLoop {
    private static final int TICKS_PER_SECOND = 25;
    private static final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
    private static final int MAX_FRAMESKIP = 10;

    private boolean myGameIsRunning = false;
    private Controller myController;


    /**
     * 
     * @param controller a controller
     */
    public GameLoop (Controller controller) {
        // TODO: functions to construct model from file. Probably put that in
        // GameModel constructor.
        myController = controller;
    }

    /**
     * Starts the game loop.
     */
    public void start () {
        setRunning(true);
        run();
    }

    /**
     * 
     */
    public void run () {
        // this game loop will update the game at up to TICKS_PER_SECOND, and
        // repaint the screen as fast as possible.
        long nextGameTick = System.currentTimeMillis();

        while (myGameIsRunning) {
            int loops = 0;
            while (System.currentTimeMillis() > nextGameTick
                   && loops < MAX_FRAMESKIP) {
                // myController.update(System.currentTimeMillis() - nextGameTick);
                myController.update(10);
                nextGameTick += SKIP_TICKS;
                loops++;
            }
            myController.displayMap();
        }
    }

    /**
     * Turns the game loop on or off, depending on the value of isRunning.
     * 
     * @param isRunning true if game loop is running. false otherwise
     */
    public void setRunning (boolean isRunning) {
        myGameIsRunning = isRunning;
        run();
    }
}
