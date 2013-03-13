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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.sql.Date;
import java.util.List;

import de.aidger.model.validators.ValidationException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExpectedException;
import siena.SienaException;

/**
 * Tests the Course class.
 * 
 * @author aidGer Team
 */
public class CourseTest {

    protected Course course = null;
    
    protected FinancialCategory fc = null;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void beforeClassSetUp() {
        de.aidger.model.Runtime.getInstance().initialize();
    }

    @Before
    public void setUp() throws SienaException {
    	new Course().clearTable();
    	new FinancialCategory().clearTable();
        
        fc = new FinancialCategory();
        fc.setBudgetCosts(new Integer[] { 100 });
        fc.setCostUnits(new Integer[] { 10001000 });
        fc.setName("name");
        fc.setYear((short) 2010);
        fc.save();

        course = new Course();
        course.setId((long) 1);
        course.setAdvisor("Tester");
        course.setDescription("Description");
        course.setFinancialCategoryId(fc.getId());
        course.setGroup("2");
        course.setLecturer("Test Tester");
        course.setNumberOfGroups(3);
        course.setPart('a');
        course.setRemark("Remark");
        course.setScope("Sniper Scope");
        course.setSemester("SS09");
        course.setTargetAudience("Testers");
        course.setUnqualifiedWorkingHours(100.0);
    }

    /**
     * Test of constructor, of class Course.
     */
    @Test
    public void testConstructor() throws SienaException {
        System.out.println("Constructor");

        course.save();

        Course result = new Course(course.getById(course.getId()));

        assertNotNull(result);
        assertEquals(course, result);
    }

    /**
     * Test of validation, of class Course.
     */
    @Test
    public void testValidation() throws SienaException {
        System.out.println("Validation");

        course.save();
        assertNotNull(course.getAll());
        course.remove();
        assertTrue(course.getAll().isEmpty());

        exception.expect(ValidationException.class);

        course.setDescription(null);
        course.save();
        assertTrue(course.getAll().isEmpty());
        course.resetErrors();
        course.setDescription("Description");

        course.setGroup(null);
        course.save();
        assertTrue(course.getAll().isEmpty());
        course.resetErrors();
        course.setGroup("2");

        course.setLecturer(null);
        course.save();
        assertTrue(course.getAll().isEmpty());
        course.resetErrors();
        course.setLecturer("Test Tester");

        course.setNumberOfGroups(-1);
        course.save();
        assertTrue(course.getAll().isEmpty());
        course.resetErrors();
        course.setNumberOfGroups(3);

        course.setSemester(null);
        course.save();
        assertTrue(course.getAll().isEmpty());
        course.resetErrors();

        course.setSemester("abc 2000");
        course.save();
        assertTrue(course.getAll().isEmpty());
        course.resetErrors();
        course.setSemester("SS09");

        course.setUnqualifiedWorkingHours(0.0);
        course.save();
        assertTrue(course.getAll().isEmpty());
        course.resetErrors();

        exception = ExpectedException.none();
    }

    /**
     * Test of validateOnRemove, of class Contract.
     */
    @Test
    public void testValidateOnRemove() throws SienaException {
        System.out.println("validateOnRemove");

        course.clearTable();
        course.save();
        assertNotNull(course.getAll());
        course.remove();
        assertTrue(course.getAll().isEmpty());

        course.save();
        Activity activity = new Activity();
        activity.setAssistantId(null);
        activity.setContent("New assistant");
        activity.setCourseId(course.getId());
        activity.setDate(new java.sql.Date(100));
        activity.setDocumentType("Test Type");
        activity.setProcessor("T");
        activity.setRemark("Remark");
        activity.setSender("Test Sender");
        activity.setType("Test Type");
        activity.save();

        try {
            course.remove();
            assertTrue(false);
        } catch (ValidationException e) {}
        
        assertFalse(course.getAll().isEmpty());
        course.resetErrors();
        activity.remove();

        Assistant assistant = new Assistant();
        assistant.setEmail("test@example.com");
        assistant.setFirstName("Test");
        assistant.setLastName("Tester");
        assistant.setQualification("g");
        assistant.save();

        Contract contract = new Contract();
        contract.setAssistantId(assistant.getId());
        contract.setCompletionDate(new Date(10));
        contract.setConfirmationDate(new Date(100));
        contract.setDelegation(false);
        contract.setEndDate(new Date(1000));
        contract.setStartDate(new Date(20));
        contract.setType("newContract");
        contract.save();

        Employment employment = new Employment();
        employment.setAssistantId(assistant.getId());
        employment.setContractId(contract.getId());
        employment.setCourseId(course.getId());
        employment.setFunds("0711");
        employment.setCostUnit(1);
        employment.setHourCount(40.0);
        employment.setMonth((byte) 10);
        employment.setQualification("g");
        employment.setRemark("Remark");
        employment.setYear((short) 2010);
        employment.save();

        try {
            course.remove();
            assertTrue(false);
        } catch (ValidationException e) {}
        
        assertFalse(course.getAll().isEmpty());
        course.resetErrors();
        
        employment.remove();
        contract.remove();
        assistant.remove();

        course.remove();
        assertTrue(course.getAll().isEmpty());
    }

    /**
     * Test of clone method, of class Course.
     */
    @Test
    public void testClone() {
        System.out.println("clone");

        Course result = course.clone();

        assertEquals(course.getId(), result.getId());
        assertEquals(course.getAdvisor(), result.getAdvisor());
        assertEquals(course.getDescription(), result.getDescription());
        assertEquals(course.getFinancialCategoryId(), result
            .getFinancialCategoryId());
        assertEquals(course.getGroup(), result.getGroup());
        assertEquals(course.getLecturer(), result.getLecturer());
        assertEquals(course.getNumberOfGroups(), result.getNumberOfGroups());
        assertEquals(course.getPart(), result.getPart());
        assertEquals(course.getRemark(), result.getRemark());
        assertEquals(course.getScope(), result.getScope());
        assertEquals(course.getSemester(), result.getSemester());
        assertEquals(course.getTargetAudience(), result.getTargetAudience());
        assertEquals(course.getUnqualifiedWorkingHours(), result
            .getUnqualifiedWorkingHours(), 0.001);
    }

    /**
     * Test of equals method, of class Course.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Course result = course.clone();

        assertEquals(course, result);
        assertNotEquals(course, new Object());        
    }

    /**
     * Test of hashCode method, of class Course.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Course result = course.clone();

        assertEquals(course.hashCode(), result.hashCode());
    }

    /**
     * Test of getCourses method, of class Course.
     */
    @Test
    public void testGetCourses_FinancialCategory() throws SienaException {
        System.out.println("getCourses");

        FinancialCategory fc = new FinancialCategory();
        fc.setBudgetCosts(new Integer[] { 100, 200 });
        fc.setCostUnits(new Integer[] { 10001000, 20002000 });
        fc.setName("Tester");
        fc.setYear((short) 2010);
        fc.save();

        course.setFinancialCategoryId(fc.getId());
        course.save();

        List result = course.getCourses(fc);

        assertNotNull(result);
        assertTrue(result.size() >= 1);
        assertEquals(course, result.get(0));
    }

    /**
     * Test of getCoursesBySemester method, of class Course.
     */
    @Test
    public void testGetCoursesBySemester() throws SienaException {
        System.out.println("getCoursesBySemester");

        course.save();

        List result = course.getCoursesBySemester(course.getSemester());

        assertNotNull(result);
        assertTrue(result.size() > 0);
    }

    /**
     * Test of getCoursesByGroup method, of class Course.
     */
    @Test
    public void testGetCoursesByGroup() throws SienaException {
        System.out.println("getCoursesByGroup");

        course.save();

        List result = course.getCoursesByGroup(course.getGroup());

        assertNotNull(result);
        assertTrue(result.size() > 0);
    }

    /**
     * Test of getDistinctSemesters method, of class Course.
     */
    @Test
    public void testGetDistinctSemesters() throws SienaException {
        System.out.println("getDistinctSemesters");

        course.setSemester("SS10");
        course.save();

        List result = course.getDistinctSemesters();

        assertNotNull(result);
        assertTrue(result.contains(course.getSemester()));
    }

    /**
     * Test of getDistinctGroups method, of class Course.
     */
    @Test
    public void testGetDistinctGroups() throws SienaException {
        System.out.println("getDistinctGroups");

        course.setGroup("A");
        course.save();

        List result = course.getDistinctGroups();

        assertNotNull(result);
        assertTrue(result.contains(course.getGroup()));
    }
    
    /**
     * Cleans up after the tests.
     */
    @After
    public void cleanUp() throws ValidationException {
        course.remove();
        fc.remove();
    }

}