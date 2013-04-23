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

package de.aidger.model.models;

import static org.junit.Assert.*;

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
 * Tests the Contract class.
 * 
 * @author aidGer Team
 */
public class ContractTest {

    protected Contract contract = null;

    protected static Assistant assistant = null;

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
    }

    @Before
    public void setUp() {
        contract = new Contract();
        contract.setAssistantId(assistant.getId());
        contract.setCompletionDate(new Date(10));
        contract.setConfirmationDate(new Date(100));
        contract.setDelegation(false);
        contract.setEndDate(new Date(1000));
        contract.setStartDate(new Date(20));
        contract.setType("newContract");
    }

    /**
     * Test of constructor, of class Contract.
     */
    @Test
    public void testConstructor() throws SienaException {
        System.out.println("Constructor");

        contract.save();

        Contract result = contract.getById(contract.getId());

        assertNotNull(result);
        assertEquals(contract, result);
    }

    /**
     * Test of validation, of class Contract.
     */
    @Test
    public void testValidation() throws SienaException {
        System.out.println("Validation");

        contract.save();
        assertFalse(contract.getAll().isEmpty());
        contract.clearTable();

        exception.expect(ValidationException.class);

        contract.setAssistantId((long) 0);
        contract.save();
        assertTrue(contract.getAll().isEmpty());
        contract.resetErrors();
        contract.setAssistantId(assistant.getId());

        contract.setCompletionDate(null);
        contract.save();
        assertTrue(contract.getAll().isEmpty());
        contract.resetErrors();
        contract.setCompletionDate(new Date(10));

        contract.setEndDate(null);
        contract.save();
        assertTrue(contract.getAll().isEmpty());
        contract.resetErrors();
        contract.setEndDate(new Date(1000));

        contract.setStartDate(null);
        contract.save();
        assertTrue(contract.getAll().isEmpty());
        contract.resetErrors();
        contract.setStartDate(new Date(20));

        contract.setType(null);
        contract.save();
        assertTrue(contract.getAll().isEmpty());
        contract.resetErrors();

        contract.setType("012345678901234567890");
        contract.save();
        assertTrue(contract.getAll().isEmpty());
        contract.resetErrors();
        contract.setType("newContract");

        contract.setConfirmationDate(new Date(1));
        contract.save();
        assertTrue(contract.getAll().isEmpty());
        contract.resetErrors();
        contract.setConfirmationDate(new Date(10));

        contract.setEndDate(new Date(10));
        contract.save();
        assertTrue(contract.getAll().isEmpty());
        contract.resetErrors();
        contract.setEndDate(new Date(1000));

        exception = ExpectedException.none();
    }

    /**
     * Test of validateOnRemove, of class Contract.
     */
    @Test
    public void testValidateOnRemove() throws SienaException {
        System.out.println("validateOnRemove");

        contract.save();
        assertFalse(contract.getAll().isEmpty());
        
        contract.remove();
        assertTrue(contract.getAll().isEmpty());

        contract.save();

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
            contract.remove();
            assertTrue(false);
        } catch (ValidationException e) {}
        
        assertFalse(contract.getAll().isEmpty());
        contract.resetErrors();
        
        employment.remove();
        course.remove();
        fc.remove();

        contract.remove();
        assertTrue(contract.getAll().isEmpty());
    }

    /**
     * Test of clone method, of class Contract.
     */
    @Test
    public void testClone() {
        System.out.println("clone");

        Contract result = contract.clone();

        assertEquals(contract.getId(), result.getId());
        assertEquals(contract.getCompletionDate(), result.getCompletionDate());
        assertEquals(contract.getConfirmationDate(), result
            .getConfirmationDate());
        assertEquals(contract.isDelegation(), result.isDelegation());
        assertEquals(contract.getEndDate(), result.getEndDate());
        assertEquals(contract.getStartDate(), result.getStartDate());
        assertEquals(contract.getType(), result.getType());
    }

    /**
     * Test of equals method, of class Contract.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Contract result = contract.clone();

        assertEquals(contract, result);
        assertNotEquals(contract, new Object());
    }

    /**
     * Test of hashCode method, of class Contract.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Contract result = contract.clone();

        assertEquals(contract.hashCode(), result.hashCode());
    }

    /**
     * Test of getContracts method, of class Contract.
     */
    @Test
    public void testGetContracts() throws SienaException {
        System.out.println("getContracts");

        contract.save();

        List<Contract> result = contract.getContracts(new Date(20), new Date(1000));

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
    
    /**
     * Cleans up after the tests.
     */
    @After
    public void cleanUp() throws ValidationException {
        contract.remove();
    }
    
    /**
     * Cleans up after the whole class is done.
     */
    @AfterClass
    public static void cleanUpClass() throws ValidationException {
        assistant.remove();
    }

}
