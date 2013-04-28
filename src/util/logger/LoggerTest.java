package util.logger;

import java.util.logging.Level;

/**
 * Class used for testing the logger
 * @author Henrique Moraes
 *
 */
public class LoggerTest {
    public static void main (String[] args) {
        LoggerManager log = new LoggerManager();
        log.log(Level.INFO, "Logger Name is " + log.getLogger().getName());
        log.addTxtHandler("NewLoggerTest");
        log.addXMLHandler("NewLoggerTest");
        logEachType(log);

    }

    private static void logEachType (LoggerManager logger) {
        logger.log(Level.FINEST, "Finest Test Message");
        logger.log(Level.FINER, "Finer Test Message");
        logger.log(Level.FINE, "Fine Test Message");
        logger.log(Level.INFO, "Info Test Message");
        logger.log(Level.WARNING, "Warning Test Message");
        logger.log(Level.SEVERE, "Severe Test Message");
    }
}
