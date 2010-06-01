package de.aidger.utils;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;

/**
 * Tests the Configuration class.
 *
 * @author aidGer Team
 */
public class ConfigurationTest {

    protected static Configuration config = null;

    @Before
    public void setUp() {
        config = new Configuration("./");
    }

    @After
    public void cleanUp() {
        File file = new File("./settings.cfg");
        file.delete();
    }

    /**
     * Test of initialize method, of class Configuration.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");

        File file = new File("./settings.cfg");
        assertTrue(file.exists());
        assertEquals(config.get("debug"), "false");

        config = new Configuration("./");
        assertEquals(config.get("debug"), "false");
    }

    /**
     * Test of get method, of class Configuration.
     */
    @Test
    public void testGet() {
        System.out.println("get");

        assertEquals(config.get("debug"), "false");
    }

    /**
     * Test of set method, of class Configuration.
     */
    @Test
    public void testSet() {
        System.out.println("set");

        assertEquals(config.get("test"), null);

        config.set("test", "test");
        assertEquals(config.get("test"), "test");

        config = new Configuration("./");
        assertEquals(config.get("test"), "test");
    }

}