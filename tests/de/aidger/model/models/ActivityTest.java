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

package de.aidger.model.models;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.List;

import de.aidger.model.validators.ValidationException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExpectedException;
import siena.SienaException;

/**
 * Tests the Activity class.
 * 
 * @author aidGer Team
 */
public class ActivityTest {

    private Activity activity = null;

    private static Assistant assistant = null;

    private static Course course = null;

    private static FinancialCategory financial = null;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void beforeClassSetUp() throws SienaException {
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
    }

    /**
     * Sets the Activity class up for tests.
     */
    @Before
    public void setUp() {
        activity = new Activity();
        activity.setAssistantId(assistant.getId());
        activity.setContent("New assistant");
        activity.setCourseId(course.getId());
        activity.setDate(new Date(1000000000));
        activity.setDocumentType("Test Type");
        activity.setProcessor("T");
        activity.setRemark("Remark");
        activity.setSender("Test Sender");
        activity.setType("Test Type");
    }

    /**
     * Test of constructor, of class Activity.
     */
    @Test
    public void testConstructor() throws SienaException {
        System.out.println("Constructor");

        activity.save();

        Activity result = new Activity(activity.getById(activity.getId()));

        assertNotNull(result);
        assertEquals(activity, result);
    }

    /**
     * Test of validation, of class Activity.
     */
    @Test
    public void testValidation() throws SienaException {
        System.out.println("Validation");

        exception.expect(ValidationException.class);

        activity.setAssistantId((long) 0);
        activity.save();
        List<Activity> list = activity.getAll();
        assertNull(list);
        activity.resetErrors();
        activity.setAssistantId(assistant.getId());

        activity.setContent(null);
        activity.save();
        list = activity.getAll();
        assertNull(list);
        activity.resetErrors();
        activity.setContent("New assistant");

        activity.setCourseId((long) 0);
        activity.save();
        list = activity.getAll();
        assertNull(list);
        activity.resetErrors();
        activity.setCourseId(course.getId());

        activity.setDate(null);
        activity.save();
        list = activity.getAll();
        assertNull(list);
        activity.resetErrors();
        activity.setDate(new Date(100));

        activity.setDocumentType(null);
        activity.save();
        list = activity.getAll();
        assertNull(list);
        activity.resetErrors();

        activity
            .setDocumentType("012345678901234567890123456789012345678901234567890");
        activity.save();
        list = activity.getAll();
        assertNull(list);
        activity.resetErrors();
        activity.setDocumentType("Type");

        activity.setProcessor(null);
        activity.save();
        list = activity.getAll();
        assertNull(list);
        activity.resetErrors();

        activity.setProcessor("Tester");
        activity.save();
        list = activity.getAll();
        assertNull(list);
        activity.resetErrors();
        activity.setProcessor("T");

        activity.setType(null);
        activity.save();
        list = activity.getAll();
        assertNull(list);
        activity.resetErrors();

        activity.setType("012345678901234567890");
        activity.save();
        list = activity.getAll();
        assertNull(list);
        activity.resetErrors();

        exception = ExpectedException.none();
    }

    /**
     * Test of clone method, of class Activity.
     */
    @Test
    public void testClone() {
        System.out.println("clone");

        Activity result = activity.clone();

        assertEquals(activity.getId(), result.getId());
        assertEquals(activity.getAssistantId(), result.getAssistantId());
        assertEquals(activity.getContent(), result.getContent());
        assertEquals(activity.getCourseId(), result.getCourseId());
        assertEquals(activity.getDate(), result.getDate());
        assertEquals(activity.getDocumentType(), result.getDocumentType());
        assertEquals(activity.getProcessor(), result.getProcessor());
        assertEquals(activity.getRemark(), result.getRemark());
        assertEquals(activity.getSender(), result.getSender());
        assertEquals(activity.getType(), result.getType());
    }

    /**
     * Test of equals method, of class Activity.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Activity result = activity.clone();

        assertEquals(activity, result);
        assertNotEquals(activity, new Object());        
    }

    /**
     * Test of hashCode method, of class Activity.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Activity result = activity.clone();

        assertEquals(activity.hashCode(), result.hashCode());
    }

    /**
     * Test of getActivities method, of class Activity.
     */
    @Test
    public void testGetActivities_Assistant() throws SienaException {
        System.out.println("getActivities");

        Assistant a = new Assistant();
        a.setEmail("test@example.com");
        a.setFirstName("Test");
        a.setLastName("Tester");
        a.setQualification("g");
        a.save();

        activity.setAssistantId(a.getId());
        activity.save();

        List result = activity.getActivities(a);

        assertNotNull(result);
        assertTrue(result.size() == 1);
    }

    /**
     * Test of getActivities method, of class Activity.
     */
    @Test
    public void testGetActivities_Course() throws SienaException {
        System.out.println("getActivities");

        activity.setCourseId(course.getId());
        activity.save();

        List result = activity.getActivities(course);

        assertNotNull(result);
        assertTrue(result.size() > 0);
    }

    /**
     * Test of getActivities method, of class Activity.
     */
    @Test
    public void testGetActivities_Date_Date() throws SienaException {
        System.out.println("getActivities");

        activity.clearTable();
        activity.save();
        
        Activity a1 = new Activity();
        a1.setDate(new Date(10));
        a1.setContent("New assistant");
        a1.setDocumentType("Test Type");
        a1.setProcessor("T");
        a1.setRemark("Remark");
        a1.setSender("Test Sender");
        a1.setType("Test Type");
        a1.save();
        
        Activity a2 = new Activity();
        a2.setDate(new Date(1000));
        a2.setContent("New assistant");
        a2.setDocumentType("Test Type");
        a2.setProcessor("T");
        a2.setRemark("Remark");
        a2.setSender("Test Sender");
        a2.setType("Test Type");
        a2.save();
        
        Activity a3 = new Activity();
        a3.setDate(new Date(2010000000));
        a3.setContent("New assistant");
        a3.setDocumentType("Test Type");
        a3.setProcessor("T");
        a3.setRemark("Remark");
        a3.setSender("Test Sender");
        a3.setType("Test Type");
        a3.save();
        
        List result = activity.getActivities(new Date(1), new Date(1010000000));

        assertNotNull(result);
        System.out.println(result.size());
        assertEquals(3, result.size());
        
        a1.remove();
        a2.remove();
        a3.remove();
    }
    
    /**
     * Cleans up after the tests.
     */
    @After
    public void cleanUp() throws ValidationException {
        activity.remove();
    }
    
    /** 
     * Cleans up after the whole class is done.
     */
    @AfterClass
    public static void cleanUpClass() throws ValidationException {
        course.remove();
        financial.remove();
        assistant.remove();        
    }

}
