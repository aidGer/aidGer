package de.aidger.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.logging.Level;

import org.junit.BeforeClass;
import org.junit.Test;

import de.aidger.model.Runtime;

/**
 * Tests the Logger class.
 *
 * @author aidGer Team
 */
public class LoggerTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
        Runtime.getInstance().initialize();
    }

    /**
     * Test of getInstance method, of class Logger.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");

        Logger result = Logger.getInstance();
        assertNotNull(result);

        File file = new File(Runtime.getInstance().getConfigPath().concat(
                "/aidger.log"));
        assertTrue(file.exists());
    }

    /**
     * Test of logMsg method, of class Logger.
     */
    @Test
    public void testLogMsg() {
        System.out.println("logMsg");

        File file = new File(Runtime.getInstance().getConfigPath().concat(
                "/aidger.log"));
        long before = file.length();
        Logger.getInstance().logMsg(Level.SEVERE, "Some logging message");

        assertTrue(before < file.length());
    }

    /**
     * Test of debug method, of class Logger.
     */
    @Test
    public void testDebug() {
        System.out.println("debug");

        File file = new File(Runtime.getInstance().getConfigPath().concat(
                "/aidger.log"));
        long before = file.length();
        Logger.debug("Debug logging message");

        assertTrue(before < file.length());
    }

    /**
     * Test of info method, of class Logger.
     */
    @Test
    public void testInfo() {
        System.out.println("info");

        File file = new File(Runtime.getInstance().getConfigPath().concat(
                "/aidger.log"));
        long before = file.length();
        Logger.info("Info logging message");

        assertTrue(before < file.length());
    }

    /**
     * Test of error method, of class Logger.
     */
    @Test
    public void testError() {
        System.out.println("error");

        File file = new File(Runtime.getInstance().getConfigPath().concat(
                "/aidger.log"));
        long before = file.length();
        Logger.error("Error logging message");

        assertTrue(before < file.length());
    }

}