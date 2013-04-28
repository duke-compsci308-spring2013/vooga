package util.logger;

/**
 * Uses reflection to refer to the calling class of a method
 * @author Henrique Moraes
 *
 */
public class LoggerReflection {
    private static int CALLER_INDEX = 4;

    /**
     * private constructor. should not be initialized
     */
    private LoggerReflection () {
    }

    /**
     * 
     * @return The class name of the caller
     */
    public static String getCallerClassName () {
        return getElement().getClassName();
    }

    /**
     * 
     * @return The method name from which the caller calls
     */
    public static String getCallerMethodName () {
        return getElement().getMethodName();
    }

    /**
     * 
     * @return The StackTrace Element associated with the calling
     * method
     */
    private static StackTraceElement getElement () {
        StackTraceElement[] element = Thread.currentThread().getStackTrace();
        return element[CALLER_INDEX];
    }
}
