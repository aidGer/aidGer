/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.aidger.utils;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rmbl
 */
public class TranslationTest {

    public TranslationTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
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

        List result = trans.getLanguages();

        assertNotNull(result);
        assertTrue(result.size() > 0);        
    }

}