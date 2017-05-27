import java.lang.System.*;
import java.time.*;
import java.text.*;
import java.util.*;
/**
 * Counts the amount of seconds between a starting event
 * and an ending event
 * 
 * @author Christian Wang 
 * @version Sept 2016
 */
public class Timer
{
    public long startTime;

    /**
     * Default constructor for timer object
     * Starts the timer 
     */
    public Timer() {
        mark();
    }

    /**
     * Starts the timer and sets the starttime value to current time
     */
    public void mark() {
        startTime = System.currentTimeMillis();
    }

    /**
     * Returns the amount of seconds that passed
     * Since the starting event
     */
    public int secondsElapsed() {
        return (int) (System.currentTimeMillis()-startTime)/1000;
    }

    /**
     * Returns the amount of millisecondss that passed
     * Since the starting event
     */
    public int millisElapsed() {
        return (int) (System.currentTimeMillis()-startTime);
    }

    /**
     * Returns the current time in format year-month-day hour-minute-seconds
     */
    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

}
