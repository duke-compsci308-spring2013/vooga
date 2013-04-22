package vooga.rts.util;

/**
 * This is a basic class that lets you time you long something takes to execute.
 * Create a new instance of it to specify the start time and then call getTime to 
 * get the time in ms.
 * 
 * @author Jonathan Schmidt
 *
 */
public class TimeIt {

    private long myTime;
    
    /**
     * Creates a new TimeIt and marks the current time as the start time.
     */
    public TimeIt () {
        myTime = System.nanoTime();
    }
    
    /**
     * @return The time that has elapsed since this Time It was created.
     */
    public double getTime() {
        double d = System.nanoTime() - myTime;
        return d / 1000000;
    }
    
    /**
     * Prints the time that Time It has measured.
     */
    public void printTime() {
        System.out.println("Time to Calculate: " + getTime());
    }

}
