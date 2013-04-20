package vooga.towerdefense.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import vooga.towerdefense.controller.Controller;


/**
 * This class represents a game loop. It is responsible for starting,
 * stopping, and resuming the animation of the game.
 * 
 * @author Jimmy Longley
 */
public class GameLoop {
    // private static final int TICKS_PER_SECOND = 25;
    // private static final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
    // private static final int MAX_FRAMESKIP = 10;

    // animate 25 times per second if possible
    private static final int FRAMES_PER_SECOND = 25;
    // better way to think about timed events (in milliseconds)
    private static final int ONE_SECOND = 1000;
    private static final int DEFAULT_DELAY = ONE_SECOND / FRAMES_PER_SECOND;

    private Controller myController;
    private Timer myTimer;
    private boolean myGameIsRunning;


    /**
     * 
     * @param controller a controller
     */
    public GameLoop (Controller controller) {
        // TODO: functions to construct model from file. Probably put that in
        // GameModel constructor.
        myController = controller;
        initTimer();
    }

    /**
     * Starts the game loop.
     */
    public void start () {
        myTimer.start();
    }

    public void stop () {
        myTimer.stop();
    }

    // public void run () {
    // // this game loop will update the game at up to TICKS_PER_SECOND, and
    // // repaint the screen as fast as possible.
    // long nextGameTick = System.currentTimeMillis();
    //
    // while (myGameIsRunning) {
    // int loops = 0;
    // while (System.currentTimeMillis() > nextGameTick
    // && loops < MAX_FRAMESKIP) {
    // // myController.update(System.currentTimeMillis() - nextGameTick);
    // myController.update(10);
    // nextGameTick += SKIP_TICKS;
    // loops++;
    // }
    // myController.displayMap();
    // }
    // }

    private void initTimer () {
        myTimer = new Timer(DEFAULT_DELAY,
                            new ActionListener() {
                                public void actionPerformed (ActionEvent e) {
//                                    myController.update(DEFAULT_DELAY / ONE_SECOND);
                                    myController.update(10);
                                    myController.displayMap();
                                }
                            });
    }

}
