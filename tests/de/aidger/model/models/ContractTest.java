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

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import siena.SienaException;

/**
 * Tests the Contract class.
 * 
 * @author aidGer Team
 */
public class ContractTest {

    protected Contract contract = null;

    protected static Assistant assistant = null;

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
        contract.setId((long) 1);
        contract.setAssistantId(assistant.getId());
        contract.setCompletionDate(new Date(10));
        contract.setConfirmationDate(new Date(100));
        contract.setDelegation(false);
        contract.setEndDate(new Date(1000));
        contract.setStartDate(new Date(20));
        contract.setType("Type");
    }

    /**
     * Test of constructor, of class Contract.
     */
    @Test
    public void testConstructor() throws SienaException {
        System.out.println("Constructor");

        contract.save();

        Contract result = new Contract(contract.getById(contract.getId()));

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
        List<Contract> list = contract.getAll();
        assertNotNull(list);
        contract.clearTable();

        contract.setAssistantId((long) 0);
        contract.save();
        list = contract.getAll();
        assertNull(list);
        contract.resetErrors();
        contract.setAssistantId(assistant.getId());

        contract.setCompletionDate(null);
        contract.save();
        list = contract.getAll();
        assertNull(list);
        contract.resetErrors();
        contract.setCompletionDate(new Date(10));

        contract.setEndDate(null);
        contract.save();
        list = contract.getAll();
        assertNull(list);
        contract.resetErrors();
        contract.setEndDate(new Date(1000));

        contract.setStartDate(null);
        contract.save();
        list = contract.getAll();
        assertNull(list);
        contract.resetErrors();
        contract.setStartDate(new Date(20));

        contract.setType(null);
        contract.save();
        list = contract.getAll();
        assertNull(list);
        contract.resetErrors();

        contract.setType("012345678901234567890");
        contract.save();
        list = contract.getAll();
        assertNull(list);
        contract.resetErrors();
        contract.setType("Type");

        contract.setConfirmationDate(new Date(1));
        contract.save();
        list = contract.getAll();
        assertNull(list);
        contract.resetErrors();
        contract.setConfirmationDate(new Date(10));

        contract.setEndDate(new Date(10));
        contract.save();
        list = contract.getAll();
        assertNull(list);
        contract.resetErrors();
        contract.setEndDate(new Date(1000));
    }

    /**
     * Test of validateOnRemove, of class Contract.
     */
    @Test
    public void testValidateOnRemove() throws SienaException {
        System.out.println("validateOnRemove");

        contract.save();
        List<Contract> list = contract.getAll();
        assertNotNull(list);
        contract.remove();
        list = contract.getAll();
        assertNull(list);

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

        contract.remove();
        list = contract.getAll();
        assertNotNull(list);
        contract.resetErrors();
        employment.remove();

        contract.remove();
        list = contract.getAll();
        assertNull(list);
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
        assertFalse(contract.equals(new Object()));

        result.setCompletionDate(null);
        result.setConfirmationDate(null);
        result.setEndDate(null);
        result.setStartDate(null);
        assertFalse(contract.equals(result));
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

        List result = contract.getContracts(new Date(20), new Date(1000));

        assertNotNull(result);
        assertTrue(result.size() > 0);
    }

}