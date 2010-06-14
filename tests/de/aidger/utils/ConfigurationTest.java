package de.aidger.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.aidger.model.Runtime;

/**
 * Tests the Configuration class.
 *
 * @author aidGer Team
 */
public class ConfigurationTest {

    protected static Configuration config = null;

    @BeforeClass
    public static void beforeClassSetUp() {
    	Runtime.getInstance().initialize();
    	Runtime.getInstance().setConfigPath("./");
    }

    @Before
    public void setUp() {
        config = new Configuration();
    }

    @After
    public void cleanUp() {
    	(new File(Runtime.getInstance().getConfigPath() + "settings.cfg")).
    			delete();
    }

    /**
     * Test of initialize method, of class Configuration.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");

        File file = new File(Runtime.getInstance().getConfigPath() +
        		"settings.cfg");
        assertTrue(file.exists());
        assertEquals(config.get("debug"), "false");

        config = new Configuration();
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

        config = new Configuration();
        assertEquals(config.get("test"), "test");
    }

}