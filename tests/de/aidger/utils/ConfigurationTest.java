/*
 * This file is part of the aidGer project.
 *
 * Copyright (C) 2010-2013 The aidGer Team
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
        config = new Configuration("test-settings.cfg");
    }

    @After
    public void cleanUp() {
    	(new File(Runtime.getInstance().getConfigPath() + "test-settings.cfg")).
    			delete();
    }

    /**
     * Test of constructor, of class Configuration.
     */
    @Test
    public void testConstructor() {
        System.out.println("constructor");

        File file = new File(Runtime.getInstance().getConfigPath() +
        		"test-settings.cfg");
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

        config = new Configuration("test-settings.cfg");
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
