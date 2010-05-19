package de.aidger.utils;

import java.io.IOException;
import java.util.logging.*;

import de.aidger.model.Runtime;

/**
 * Provides a set of helper functions to ease the use of the java.util.logging
 * package.
 *
 * @author aidGer Team
 */
public final class Logger {

    /**
     * The only instance of the logger class.
     */
    private static Logger instance = null;

    /**
     * Logger to log all messages
     */
    private java.util.logging.Logger logger;

    /**
     * Initializes the Logger class.
     */
    private Logger() {
        logger = java.util.logging.Logger.getLogger("aidger");
        logger.setUseParentHandlers(false);
        try {
            FileHandler fhandler = new FileHandler(Runtime.getInstance().
                    getConfigPath().concat("/aidger.log"));
            fhandler.setFormatter(new SimpleFormatter());
            fhandler.setLevel(Level.ALL);
            logger.addHandler(fhandler);

            ConsoleHandler chandler = new ConsoleHandler();
            if (Boolean.parseBoolean(Runtime.getInstance().getOption("debug"))) {
                logger.setLevel(Level.ALL);
                chandler.setLevel(Level.ALL);
            }
            logger.addHandler(chandler);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        logger.log(Level.INFO, "Startin Logger");
    }

    /**
     * Get the only instance of the logger.
     *
     * @return The instance
     */
    public synchronized static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }

        return instance;
    }
    
    /**
     * Log a message with the given level to all handlers.
     * 
     * @param lvl
     *            The logging level of the message
     * @param msg
     *            The message to log
     */
    public void logMsg(Level lvl, String msg) {
        logger.log(lvl, msg);
    }

    /**
     * Log a message with debug level.
     *
     * @param msg
     *            The message to log
     */
    public static void debug(String msg) {
        getInstance().logMsg(Level.FINE, msg);
    }

    /**
     * Log a message with info level.
     *
     * @param msg
     *            The message to log
     */
    public static void info(String msg) {
        getInstance().logMsg(Level.INFO, msg);
    }

    /**
     * Log a message with error level.
     *
     * @param msg
     *            The message to log
     */
    public static void error(String msg) {
        getInstance().logMsg(Level.SEVERE, msg);
    }

}
