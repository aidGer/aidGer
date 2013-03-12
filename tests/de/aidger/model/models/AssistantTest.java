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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.List;

import de.aidger.model.validators.ValidationException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import siena.SienaException;

/**
 * Tests the Assistant class.
 * 
 * @author aidGer Team
 */
public class AssistantTest {

    protected Assistant assistant = null;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void beforeClassSetUp() {
        de.aidger.model.Runtime.getInstance().initialize();
    }

    @Before
    public void setUp() {
        assistant = new Assistant();
        assistant.setId((long) 1);
        assistant.setEmail("test@example.com");
        assistant.setFirstName("Test");
        assistant.setLastName("Tester");
        assistant.setQualification("g");
    }

    /**
     * Test of constructor, of class Assistant.
     */
    @Test
    public void testConstructor() throws SienaException {
        System.out.println("Constructor");

        assistant.save();

        Assistant result = new Assistant(assistant.getById(assistant.getId()));

        assertNotNull(result);
        assertEquals(assistant, result);
    }

    /**
     * Test of validation methods, of class Assistant.
     */
    @Test
    public void testValidation() {
        System.out.println("Validation");

        assistant.save();
        List<Assistant> list = assistant.getAll();
        assertNotNull(list);
        assertTrue(list.size() > 0);
        assistant.clearTable();

        exception.expect(ValidationException.class);
        assistant.setEmail(null);
        assistant.save();
        list = assistant.getAll();
        assertNull(list);
        assistant.resetErrors();

        assistant.setEmail("test");
        assistant.save();
        list = assistant.getAll();
        assertNull(list);
        assistant.resetErrors();
        assistant.setEmail("test@example.com");

        assistant.setFirstName(null);
        assistant.save();
        list = assistant.getAll();
        assertNull(list);
        assistant.resetErrors();

        assistant.setFirstName("");
        assistant.save();
        list = assistant.getAll();
        assertNull(list);
        assistant.resetErrors();
        assistant.setFirstName("Test");

        assistant.setLastName(null);
        assistant.save();
        list = assistant.getAll();
        assertNull(list);
        assistant.resetErrors();

        assistant.setLastName("");
        assistant.save();
        list = assistant.getAll();
        assertNull(list);
        assistant.resetErrors();
        assistant.setLastName("Test");

        assistant.setQualification(null);
        assistant.save();
        list = assistant.getAll();
        assertNull(list);
        assistant.resetErrors();

        assistant.setQualification("Q");
        assistant.save();
        list = assistant.getAll();
        assertNull(list);

        exception = ExpectedException.none();
    }

    /**
     * Test of validateOnRemove, of class Assistant.
     */
    @Test
    public void testValidateOnRemove() throws SienaException {
        System.out.println("validateOnRemove");

        assistant.clearTable();
        assistant.save();
        List<Assistant> list = assistant.getAll();
        assertNotNull(list);
        assistant.remove();
        list = assistant.getAll();
        assertTrue(list.size() == 0);

        assistant.save();
        Activity activity = new Activity();
        activity.setAssistantId(assistant.getId());
        activity.setContent("New assistant");
        activity.setCourseId(null);
        activity.setDate(new java.sql.Date(100));
        activity.setDocumentType("Test Type");
        activity.setProcessor("T");
        activity.setRemark("Remark");
        activity.setSender("Test Sender");
        activity.setType("Test Type");
        activity.save();

        exception.expect(ValidationException.class);
        assistant.remove();
        list = assistant.getAll();
        assertTrue(list.size() > 0);
        assistant.resetErrors();
        activity.remove();

        FinancialCategory fc = new FinancialCategory();
        fc.setBudgetCosts(new Integer[] { 100 });
        fc.setCostUnits(new Integer[] { 10001000 });
        fc.setName("name");
        fc.setYear((short) 2010);
        fc.save();

        Course course = new Course();
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
        course.save();

        Contract contract = new Contract();
        contract.setAssistantId(assistant.getId());
        contract.setCompletionDate(new Date(10));
        contract.setConfirmationDate(new Date(100));
        contract.setDelegation(false);
        contract.setEndDate(new Date(1000));
        contract.setStartDate(new Date(20));
        contract.setType("Type");
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

        assistant.remove();
        list = assistant.getAll();
        assertTrue(list.size() > 0);
        assistant.resetErrors();
        fc.remove();
        course.remove();
        contract.remove();
        employment.remove();

        contract.save();
        assistant.remove();
        list = assistant.getAll();
        assertTrue(list.size() > 0);
        assistant.resetErrors();
        contract.remove();

        exception = ExpectedException.none();

        assistant.remove();
        list = assistant.getAll();
        assertTrue(list.size() == 0);
    }

    /**
     * Test of clone method, of class Assistant.
     */
    @Test
    public void testClone() {
        System.out.println("clone");

        Assistant result = assistant.clone();

        assertEquals(assistant.getId(), result.getId());
        assertEquals(assistant.getEmail(), result.getEmail());
        assertEquals(assistant.getFirstName(), result.getFirstName());
        assertEquals(assistant.getLastName(), result.getLastName());
        assertEquals(assistant.getQualification(), result.getQualification());
    }

    /**
     * Test of equals method, of class Assistant.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Assistant result = assistant.clone();

        assertEquals(assistant, result);
        assertFalse(assistant.equals(new Object()));
    }

    /**
     * Test of hashCode method, of class Assistant.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Assistant result = assistant.clone();

        assertEquals(assistant.hashCode(), result.hashCode());
    }

}