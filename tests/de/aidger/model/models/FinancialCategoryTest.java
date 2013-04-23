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

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.aidger.model.validators.ValidationException;
import org.junit.After;

import siena.SienaException;

/**
 * Tests the FinancialCategory class.
 * 
 * @author aidGer Team
 */
public class FinancialCategoryTest {

    protected FinancialCategory financial = null;
    
    @BeforeClass
    public static void beforeClassSetUp() {
        de.aidger.model.Runtime.getInstance().initialize();
    }

    @Before
    public void setUp() {
        new FinancialCategory().clearTable();
        financial = new FinancialCategory();
        financial.setId((long) 1);
        financial.setCostUnits(new Integer[] { 10001000, 20002000 });
        financial.setBudgetCosts(new Integer[] { 100, 200 });
        financial.setName("Tester");
        financial.setYear((short) 2010);
    }

    /**
     * Test of constructor, of class FinancialCategory.
     */
    @Test
    public void testConstructor() throws SienaException {
        System.out.println("Constructor");

        financial.save();

        FinancialCategory result = new FinancialCategory(financial
            .getById(financial.getId()));

        assertNotNull(result);
        assertEquals(financial, result);
    }

    /**
     * Test of validation methods, of class FinancialCategory.
     */
    @Test
    public void testValidation() throws SienaException {
        System.out.println("Validation");

        financial.save();
        List<FinancialCategory> list = financial.getAll();
        assertTrue(!list.isEmpty());
        financial.remove();
        list = financial.getAll();
        assertTrue(list.isEmpty());
        
        financial.setName(null);
        try {
            financial.save();
        	fail("No validation exception!");
        } catch(ValidationException e) {}
        list = financial.getAll();
        assertTrue(list.isEmpty());
        financial.setName("Tester");

        financial.setYear((short) 999);
        try {
            financial.save();
        	fail("No validation exception!");
        } catch(ValidationException e) {}
        list = financial.getAll();
        assertTrue(list.isEmpty());

        financial.setYear((short) 10101);
        try {
            financial.save();
        	fail("No validation exception!");
        } catch(ValidationException e) {}
        list = financial.getAll();
        assertTrue(list.isEmpty());
        financial.setYear((short) 2010);

        financial.setBudgetCosts(new Integer[] { 0, -1 });
        try {
            financial.save();
        	fail("No validation exception!");
        } catch(ValidationException e) {}
        list = financial.getAll();
        assertTrue(list.isEmpty());
        financial.setBudgetCosts(new Integer[] { 100, 200 });

        financial.setCostUnits(new Integer[] { 1234567 });
        try {
            financial.save();
        	fail("No validation exception!");
        } catch(ValidationException e) {}
        list = financial.getAll();
        assertTrue(list.isEmpty());

        financial.setCostUnits(new Integer[] { 123456789 });
        try {
            financial.save();
        	fail("No validation exception!");
        } catch(ValidationException e) {}
        list = financial.getAll();
        assertTrue(list.isEmpty());
    }

    /**
     * Test of validateOnRemove, of class Contract.
     */
    @Test
    public void testValidateOnRemove() throws SienaException {
        System.out.println("validateOnRemove");

        financial.clearTable();
        financial.save();
        List<FinancialCategory> list = financial.getAll();
        assertTrue(!list.isEmpty());
        financial.remove();
        list = financial.getAll();
        assertTrue(list.isEmpty());

        financial.save();
        Course course = new Course();
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

        try {
        	financial.remove();
        	fail("No validation exception!");
        } catch(ValidationException e) {}
        list = financial.getAll();
        assertTrue(!list.isEmpty());
        financial.resetErrors();
        course.remove();

        financial.remove();
        list = financial.getAll();
        assertTrue(list.isEmpty());
    }

    /**
     * Test of clone method, of class FinancialCategory.
     */
    @Test
    public void testClone() {
        System.out.println("clone");

        FinancialCategory result = financial.clone();

        assertEquals(financial.getId(), result.getId());
        assertEquals(financial.getBudgetCosts(), result.getBudgetCosts());
        assertEquals(financial.getCostUnits(), result.getCostUnits());
        assertEquals(financial.getName(), result.getName());
        assertEquals(financial.getYear(), result.getYear());
    }

    /**
     * Test of equals method, of class FinancialCategory.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        FinancialCategory result = financial.clone();

        assertEquals(financial, result);
        assertNotEquals(financial, new Object());        
    }

    /**
     * Test of hashCode method, of class Assistant.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        FinancialCategory result = financial.clone();

        assertEquals(financial.hashCode(), result.hashCode());
    }
    
        /**
     * Cleans up after the tests.
     */
    @After
    public void cleanUp() throws ValidationException {
        financial.remove();
    }
    
}
