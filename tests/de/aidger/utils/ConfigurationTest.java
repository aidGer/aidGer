package de.aidger.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

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
     * Test of constructor, of class Configuration.
     */
    @Test
    public void testConstructor() {
        System.out.println("constructor");

        File file = new File(Runtime.getInstance().getConfigPath() +
        		"settings.cfg");
        assertTrue(file.exists());
        assertEquals("false", config.get("debug"));

        config = new Configuration();
        assertEquals("false", config.get("debug"));
    }

    /**
     * Test of get method, of class Configuration.
     */
    @Test
    public void testGet() {
        System.out.println("get");

        assertEquals("false", config.get("debug"));
    }

    /**
     * Test of set method, of class Configuration.
     */
    @Test
    public void testSet() {
        System.out.println("set");

        assertNull(config.get("test"));

        config.set("test", "test");
        assertEquals("test", config.get("test"));

        config = new Configuration();
        assertEquals("test", config.get("test"));
    }

    /**
     * Test of remove method, of class Configuration.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");

        config.set("test", "test");
        assertEquals("test", config.get("test"));

        config.remove("test");
        assertNull(config.get("test"));
    }

}