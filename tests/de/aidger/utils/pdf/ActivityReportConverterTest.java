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

/**
 * 
 */
package de.aidger.utils.pdf;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import siena.SienaException;

import de.aidger.model.Runtime;
import de.aidger.model.models.Assistant;

/**
 * Tests the class ActivityReportConverter.
 * 
 * @author aidGer Team
 */
public class ActivityReportConverterTest {

    private Assistant assistant;

    private static boolean openSetting = false;

    /**
     * Sets up these tests.
     */
    @BeforeClass
    public static void beforeClassSetUp() {
        Runtime.getInstance().initialize();
        String autoOpen = Runtime.getInstance().getOption("auto-open");
        if (autoOpen.equals("true")) {
            openSetting = true;
        }
        Runtime.getInstance().setOption("auto-open", "false");
    }

    /**
     * Sets up every test.
     * 
     * @throws AdoHiveException
     */
    @Before
    public void setUp() throws SienaException {
        assistant = new Assistant();
        assistant.setFirstName("Test");
        assistant.setLastName("Tester");
        assistant.setEmail("test@tester.com");
        assistant.setQualification("u");
        assistant.save();
    }

    /**
     * Tests the Constructor of the class ActivityReportConverter.
     */
    @Test
    public void testConstructor() {
        System.out.println("Constructor");

        File testFile = new File("Test_Report");

        ArrayList<String[]> testRow = new ArrayList<String[]>();
        testRow
            .add(new String[] { "01.2010 - 01.2010", "Test Course", "20.0" });

        ActivityReportConverter converter = new ActivityReportConverter(
            testFile, testRow, "blibber");

        File file = new File("Test_Report.pdf");

        assertTrue(file.exists());

        file.delete();

        testFile = new File("Test_Report.pdf");

        converter = new ActivityReportConverter(testFile, testRow, "blibber");

        file = new File("Test_Report.pdf");

        assertTrue(file.exists());
    }

    /**
     * Cleans up after every test.
     * 
     * @throws AdoHiveException
     */
    @After
    public void cleanUp() throws SienaException {
        assistant.remove();
    }

    /**
     * Cleans up after all tests have completed.
     */
    @AfterClass
    public static void afterClassCleanUp() {
        Runtime.getInstance().setOption("auto-open", "" + openSetting);
    }
}
