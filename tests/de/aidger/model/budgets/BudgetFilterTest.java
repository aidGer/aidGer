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
package de.aidger.model.budgets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import siena.SienaException;

import de.aidger.model.budgets.BudgetFilter.Comparison;

/**
 * Test the budget filter class.
 * 
 * @author aidGer Team
 */
public class BudgetFilterTest {

    private static BudgetFilter budgetFilter;

    @BeforeClass
    public static void beforeClassSetUp() {
        budgetFilter = new BudgetFilter();
    }

    /**
     * Sets up every test.
     * 
     * @throws AdoHiveException
     */
    @Before
    public void setUp() throws SienaException {
    }

    /**
     * Tests the constructor of the class BudgetFilter.
     */
    @Test
    public void testConstructor() {
        System.out.println("new BudgetFilter()");

        budgetFilter = new BudgetFilter();
        assertNotNull(budgetFilter);
    }

    /**
     * Tests the method addLecturer() of the class BudgetFilter.
     */
    @Test
    public void testAddLecturer() {
        System.out.println("addLecturer()");

        budgetFilter.addLecturer("Test Lecturer");

        assertTrue(budgetFilter.getLecturers().contains("Test Lecturer"));
    }

    /**
     * Tests the method removeLecturer() of the class BudgetFilter.
     */
    @Test
    public void testRemoveLecturer() {
        System.out.println("removeLecturer()");

        budgetFilter.removeLecturer("Test Lecturer");

        assertTrue(!(budgetFilter.getLecturers().contains("Test Lecturer")));
    }

    /**
     * Tests the method addSemester() of the class BudgetFilter.
     */
    @Test
    public void testAddSemester() {
        System.out.println("addSemester()");

        budgetFilter.addSemester("WS1516");

        assertTrue(budgetFilter.getSemesters().contains("WS1516"));
    }

    /**
     * Tests the method removeSemester() of the class BudgetFilter.
     */
    @Test
    public void testRemoveSemester() {
        System.out.println("removeSemester()");

        budgetFilter.removeSemester("WS1516");

        assertTrue(!(budgetFilter.getSemesters().contains("WS1516")));
    }

    /**
     * Tests the comparisons of the class BudgetFilter.
     */
    @Test
    public void testComparison() {
        System.out.println("Comparison");

        assertEquals("=", Comparison.EQUAL.toString());
    }

    /**
     * Tests the setters and getters of the class BudgetFilter.
     */
    @Test
    public void testSettersGetters() {
        System.out.println("Setters and Getters");

        budgetFilter.setAvailableBudget(10);
        assertEquals(10, budgetFilter.getAvailableBudget(), 0);

        budgetFilter.setAvailableComparison(Comparison.NONE);
        assertEquals(Comparison.NONE, budgetFilter.getAvailableComparison());

        budgetFilter.setBookedBudget(10);
        assertEquals(10, budgetFilter.getBookedBudget(), 0);

        budgetFilter.setBookedComparison(Comparison.NONE);
        assertEquals(Comparison.NONE, budgetFilter.getBookedComparison());

        budgetFilter.setTotalBudget(10);
        assertEquals(10, budgetFilter.getTotalBudget(), 0);

        budgetFilter.setTotalComparison(Comparison.NONE);
        assertEquals(Comparison.NONE, budgetFilter.getTotalComparison());

    }

    /**
     * Cleans up after the tests.
     * 
     * @throws AdoHiveException
     */
    @After
    public void cleanUp() throws SienaException {
    }

}
