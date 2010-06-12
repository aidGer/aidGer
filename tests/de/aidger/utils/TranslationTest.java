/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.aidger.utils;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import de.aidger.model.Runtime;
import de.unistuttgart.iste.se.adohive.util.tuple.Pair;

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

        Translation trans = new Translation("", "");

        List<Pair<String, String>> result = trans.getLanguages();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(result.get(0).fst(), "en");
    }

}