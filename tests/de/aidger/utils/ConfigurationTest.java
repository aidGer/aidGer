package de.aidger.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

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
    }

    @Before
    public void setUp() {
        config = new Configuration();
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
    }

    /**
     * Test of get method, of class Configuration.
     */
    @Test
    public void testGet() {
        System.out.println("get");

        config.set("test", "blub");
        assertEquals(config.get("test"), "blub");
    }

    /**
     * Test of set method, of class Configuration.
     */
    @Test
    public void testSet() {
        System.out.println("set");

        assertEquals(config.get("undefined"), null);

        config.set("test", "test");
        assertEquals(config.get("test"), "test");

        config = new Configuration();
        assertEquals(config.get("test"), "test");
    }

}