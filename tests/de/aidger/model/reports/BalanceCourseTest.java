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
package de.aidger.model.reports;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.aidger.model.reports.BalanceCourse.BudgetCost;

/**
 * Tests the class BalanceCourse.
 * 
 * @author aidGer Team
 */
public class BalanceCourseTest {

    private static BalanceCourse balanceCourse = null;

    /**
     * Prepares this test.
     */
    @BeforeClass
    public static void beforeClassSetUp() {
    }

    /**
     * Prepares every test.
     */
    @Before
    public void setUp() {
        balanceCourse = new BalanceCourse();
        balanceCourse.setTitle("Test title");
        balanceCourse.setLecturer("Test Lecturer");
        balanceCourse.setBasicAWS(100);
        balanceCourse.setPart('t');
        balanceCourse.setPlannedAWS(100);
        balanceCourse.setTargetAudience("Test Audience");
    }

    /**
     * Tests the constructor of the BalanceCourse class.
     */
    @Test
    public void testConstructor() {
        System.out.println("Constructor");

        BalanceCourse result = new BalanceCourse();

        assertNotNull(result);
        assertEquals(result.getTitle(), "");
        assertEquals(result.getBasicAWS(), 0.0, 0);
        assertEquals(result.getLecturer(), "");
        assertEquals(result.getPart(), '-');
        assertEquals(result.getPlannedAWS(), 0.0, 0);
    }

    /**
     * Tests the getCourseObject() method of the BalanceCourse class.
     */
    @Test
    public void testGetCourseObject() {
        System.out.println("getCourseObject()");

        Object[] values = balanceCourse.getCourseObject();

        assertNotNull(values);
        assertEquals(values[0], balanceCourse.getTitle());
        assertEquals(values[1], balanceCourse.getPart());
        assertEquals(values[2], balanceCourse.getLecturer());
        assertEquals(values[3], balanceCourse.getTargetAudience());
        assertEquals(values[4], balanceCourse.getPlannedAWS());
        assertEquals(values[5], balanceCourse.getBasicAWS());
        assertEquals(values[6], balanceCourse.getBudgetCosts());
    }

    @Test
    public void testAddBudgetCost() {
        System.out.println("addBudgetCost()");

        balanceCourse.addBudgetCost(11111111, "Test cost", 120);

        balanceCourse.addBudgetCost(11111111, "Test cost", 15);

        BudgetCost resultBudgetCost = balanceCourse.getBudgetCosts().get(0);

        assertEquals(11111111, resultBudgetCost.getId());
        assertEquals("Test cost", resultBudgetCost.getName());
        assertEquals(135, resultBudgetCost.getValue(), 0);
    }

}
