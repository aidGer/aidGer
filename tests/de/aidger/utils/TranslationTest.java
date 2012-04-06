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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.aidger.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import de.aidger.model.Runtime;

/**
 * Tests the Translation class.
 *
 * @author aidGer Team
 */
public class TranslationTest {

    @BeforeClass
    public static void beforeClassSetUp() {
        Runtime.getInstance().initialize();
    }


    /**
     * Test of _ method, of class Translation.
     */
    @Test
    public void test_() {
        System.out.println("_");

        assertEquals(Translation._("undefined"), "undefined");
    }

    /**
     * Test of getLanguages method, of class Translation.
     */
    @Test
    public void testGetLanguages() {
        System.out.println("getLanguages");

        Translation trans = new Translation("");

        List<Pair<String, String>> result = trans.getLanguages();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(result.get(0).fst(), "en");
    }

}