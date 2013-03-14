/*
 * This file is part of the aidGer project.
 *
 * Copyright (C) 2010-2011 The aidGer Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.aidger.utils;

import java.io.IOException;
import java.util.logging.*;
import java.text.MessageFormat;

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

        Formatter format = new Formatter() {
            @Override
            public String format(LogRecord record) {
                return MessageFormat.format("[{1, time, medium}] [{0}] {2}\n",
                    new Object[] { record.getLevel().getLocalizedName(),
                            record.getMillis(), record.getMessage() });
            }
        };

        try {
            FileHandler fhandler = new FileHandler(Runtime.getInstance()
                .getConfigPath().concat("/aidger.log"));
            fhandler.setFormatter(format);
            fhandler.setLevel(Level.ALL);
            logger.addHandler(fhandler);

            ConsoleHandler chandler = new ConsoleHandler();
            chandler.setFormatter(format);
            if (Boolean.parseBoolean(Runtime.getInstance().getOption("debug"))) {
                chandler.setLevel(Level.ALL);
            }
            logger.setLevel(Level.ALL);
            logger.addHandler(chandler);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
     * Destroy the current logger instance. Removes access from the log file.
     */
    public void destroy() {
        Handler[] handlers = logger.getHandlers();
        for(int i = 0; i < handlers.length; i++) {
            handlers[i].close();
            logger.removeHandler(handlers[i]);
        }
        instance = null;
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
