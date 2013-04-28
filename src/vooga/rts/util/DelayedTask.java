package vooga.rts.util;

/**
 * This allows the game to execute something at a later time.
 * The DelayedTask is updated every cycle of update in order
 * to keep track of how much time has occurred in total. <br />
 * When the elapsed time is greater than the time to run, the
 * Runnable passed in to the constructor will be executed.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class DelayedTask {

    private double myElapsedTime;
    private double myTotalTime;
    private Runnable myTask;
    private boolean myRepeat;

    /**
     * Creates a Delayed Task with the specified time until the
     * task runs as well as a Runnable which defines the task
     * to run.
     * Will not repeat.
     * 
     * @param runIn The number of seconds until the a task is executed.
     * @param toRun The Runnable to run after the specified time.
     */
    public DelayedTask (double runIn, Runnable toRun) {
        this(runIn, toRun, false);
    }
    
    /**
     * Creates a Delayed Task with the specified time until the
     * task runs as well as a Runnable which defines the task
     * to run.
     * Can also specifiy whether the task will repeat or not
     * 
     * @param runIn The number of seconds until the a task is executed.
     * @param toRun The Runnable to run after the specified time.
     * @param repeat Whether the task will repeat.
     */
    public DelayedTask (double runIn, Runnable toRun, boolean repeat) {
        myTotalTime = runIn;
        myTask = toRun;
        myElapsedTime = 0;
        myRepeat = repeat;
    }

    /**
     * Updates the DelayedTask with the time that has elapsed
     * since the last time it was updated.
     * If the total elapsed time is greater than the time required
     * before the task is run, it will run the task.
     * 
     * @param elapsedTime The time that has occurred since the last update.
     */
    public void update (double elapsedTime) {
        if (isActive()) {
            myElapsedTime += elapsedTime;
            if (myTotalTime <= myElapsedTime) {
                myTask.run();
                if (myRepeat) {
                    restart();
                }
            }
        }
    }

    /**
     * @return Whether the DelayedTask is still active. This means
     *         that the elapsed time is stll less than the total time.
     */
    public boolean isActive () {
        return myElapsedTime < myTotalTime;
    }

    /**
     * Stops the DelayedTask from running.
     */
    public void cancel () {
        myElapsedTime = myTotalTime;
    }

    /**
     * Restarts the delayed task.
     */
    public void restart () {
        myElapsedTime = 0;
    }

    /**
     * @return The percentage of completion that the Delayed Task is at.
     *         This is the elapsed time divided by the total time.
     */
    public double getPercentCompleted () {
        if (myTotalTime != 0) {
            return (myElapsedTime / myTotalTime) * 100;
        }
        return 0;
    }
}
