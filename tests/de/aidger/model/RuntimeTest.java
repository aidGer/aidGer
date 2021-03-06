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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.aidger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import de.aidger.utils.Pair;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests the Runtime class.
 *
 * @author aidGer Team
 */
public class RuntimeTest {

    @BeforeClass
    public static void beforeClassSetUp() {
        Runtime.getInstance().initialize();
    }

    /**
     * Test of getInstance method, of class Runtime.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");

        Runtime rt = Runtime.getInstance();
        assertNotNull(rt);
    }
    
    @Test
    public void testIsTestRun() {
        System.out.println("isTestRun");
        
        assertTrue(Runtime.getInstance().isTestRun());
    }

    /**
     * Test of getJarLocation method, of class Runtime.
     */
    @Test
    public void testGetJarLocation() {
        System.out.println("getJarLocation");

        String location = Runtime.getInstance().getJarLocation();

        assertFalse(location.isEmpty());
        assertFalse(location.startsWith("file:/"));
    }

    /**
     * Test of getLanguages method, of class Runtime.
     */
    @Test
    public void testGetLanguages() {
        System.out.println("getLanguages");

        List<Pair<String, String>> result = Runtime.getInstance().getLanguages();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(result.get(0).fst(), "en");
    }

    /**
     * Test of checkLock method, of class Runtime.
     */
    @Test
    public void testCheckLock() {
    	System.out.println("checkLock");

    	assertTrue(Runtime.getInstance().checkLock());

    	File lockFile = new File(Runtime.getInstance().getConfigPath() + "/aidger.lock");
    	assertTrue(lockFile.exists());

    	assertFalse(Runtime.getInstance().checkLock());
    }

    /**
     * Test of clearOption method, of class Runtime.
     */
    @Test
    public void testClearOption() {
        System.out.println("clearOption");

        Runtime.getInstance().setOption("test", "blub");
        assertEquals("blub", Runtime.getInstance().getOption("test"));

        Runtime.getInstance().clearOption("test");
        assertNull(Runtime.getInstance().getOption("test"));
    }

    /**
     * Test of getOption method, of class Runtime.
     */
    @Test
    public void testGetOption() {
        System.out.println("getOption");

        Runtime.getInstance().setOption("test", "blub");
        assertEquals("blub", Runtime.getInstance().getOption("test"));

        Runtime.getInstance().clearOption("other-test");
        assertNull(Runtime.getInstance().getOption("other-test"));
        assertEquals("bla", Runtime.getInstance().getOption("other-test", "bla"));
        assertEquals("bla", Runtime.getInstance().getOption("other-test"));

        Runtime.getInstance().clearOption("other-test");
        Runtime.getInstance().clearOption("test");
    }

    /**
     * Test of getOptionArray method, of class Runtime.
     */
    @Test
    public void testGetOptionArray() {
        System.out.println("getOptionArray");

        String[] str = new String[] { "test1", "test2" };

        Runtime.getInstance().setOptionArray("test", str);
        String[] result = Runtime.getInstance().getOptionArray("test");

        for (int i = 0; i < result.length; ++i) {
            assertEquals(str[i], result[i]);
        }

        Runtime.getInstance().setOption("test2", "not option array");

        result = Runtime.getInstance().getOptionArray("test2");
        assertNull(result);

        result = Runtime.getInstance().getOptionArray("non-existent");
        assertNull(result);

        Runtime.getInstance().clearOption("test");
        Runtime.getInstance().clearOption("test2");
    }

    /**
     * Test of setOptionArray method, of class Runtime.
     */
    @Test
    public void testSetOptionArray() {
        System.out.println("setOptionArray");

        String[] str = new String[] { "test1", "test2" };

        Runtime.getInstance().setOptionArray("test", str);
        String result = Runtime.getInstance().getOption("test");

        assertEquals(result, "[test1, test2]");

        Runtime.getInstance().clearOption("test");
    }

}
