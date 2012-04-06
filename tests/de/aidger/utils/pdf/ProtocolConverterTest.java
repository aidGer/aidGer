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
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import siena.SienaException;

import de.aidger.model.Runtime;
import de.aidger.model.models.Activity;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.Course;
import de.aidger.model.models.FinancialCategory;

/**
 * Tests the class ProtocolConverter.
 * 
 * @author aidGer Team
 */
public class ProtocolConverterTest {

    private static Activity activity = null;

    private static Assistant assistant = null;

    private static Course course = null;

    private static FinancialCategory financial = null;

    private ProtocolConverter protocolConverter = null;

    private static boolean openSetting = false;

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
     * Prepares this test.
     * 
     * @throws AdoHiveException
     */
    @Before
    public void setUp() throws SienaException {
        de.aidger.model.Runtime.getInstance().initialize();

        assistant = new Assistant();
        assistant.setEmail("test@example.com");
        assistant.setFirstName("Test");
        assistant.setLastName("Tester");
        assistant.setQualification("g");
        assistant.save();

        financial = new FinancialCategory();
        financial.setBudgetCosts(new Integer[] { 100, 200 });
        financial.setCostUnits(new Integer[] { 10001000, 20002000 });
        financial.setName("Tester");
        financial.setYear((short) 2010);
        financial.save();

        course = new Course();
        course.setAdvisor("Tester");
        course.setDescription("Description");
        course.setFinancialCategoryId(financial.getId());
        course.setGroup("2");
        course.setLecturer("Test Tester");
        course.setNumberOfGroups(3);
        course.setPart('a');
        course.setRemark("Remark");
        course.setScope("Sniper Scope");
        course.setSemester("SS09");
        course.setTargetAudience("Testers");
        course.setUnqualifiedWorkingHours(100.0);
        course.save();

        activity = new Activity();
        activity.clearTable();
        activity.setId(1);
        activity.setAssistantId(assistant.getId());
        activity.setContent("New assistant");
        activity.setCourseId(course.getId());
        activity.setDate(new Date(100));
        activity.setDocumentType("Test Type");
        activity.setProcessor("T");
        activity.setRemark("Remark");
        activity.setSender("Test Sender");
        activity.setType("Test Type");
        activity.save();
    }

    /**
     * Tests the constructor of the class ProtocolConverter.
     * 
     * @throws AdoHiveException
     */
    @Test
    public void testConstructorRange() throws SienaException {
        System.out.println("Constructor for activities in time range");

        File testFile = new File("Test_Report");
        protocolConverter = new ProtocolConverter(testFile, -1);

        File file = new File("Test_Report.pdf");
        assertTrue(file.exists());

        testFile = new File("Test_Report.test");
        protocolConverter = new ProtocolConverter(testFile, -1);

        file = new File("Test_Report.test.pdf");
        assertTrue(file.exists());
    }

    @Test
    public void testConstructorOne() {
        System.out.println("Constructor for a list of activities");

        List<Activity> activities = new ArrayList<Activity>();
        activities.add(activity);

        File testFile = new File("Test_Report.pdf");
        protocolConverter = new ProtocolConverter(testFile, activities);

        assertTrue(testFile.exists());
    }

    @After
    public void cleanUp() throws SienaException {

        course.remove();

        assistant.remove();

        activity.remove();

        financial.remove();

        File file = new File("Test_Report.pdf");
        file.delete();
        file = new File("Test_Report.test.pdf");
        file.delete();
    }

    /**
     * Cleans up after all tests have completed.
     */
    @AfterClass
    public static void afterClassCleanUp() {
        Runtime.getInstance().setOption("auto-open", "" + openSetting);
    }
}
