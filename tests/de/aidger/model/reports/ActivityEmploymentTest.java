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

/**
 * 
 */
package de.aidger.model.reports;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import siena.SienaException;

import de.aidger.model.models.Assistant;
import de.aidger.model.models.Contract;
import de.aidger.model.models.Course;
import de.aidger.model.models.Employment;
import de.aidger.model.models.FinancialCategory;

/**
 * Tests the class ActivityEmployment.
 * 
 * @author aidGer Team
 */
public class ActivityEmploymentTest {

    protected Course course = null;
    private Employment employment;
    private Assistant assistant;
    private Contract contract;
    private FinancialCategory fc;
    private ActivityEmployment activityEmployment;

    /**
     * Sets up these tests
     */
    @BeforeClass
    public static void beforeClassSetUp() {
        de.aidger.model.Runtime.getInstance().initialize();
    }

    /**
     * Sets up every test.
     * 
     * @throws SienaException
     */
    @Before
    public void setUp() throws SienaException {
        fc = new FinancialCategory();
        fc.setBudgetCosts(new Integer[] { 100 });
        fc.setCostUnits(new Integer[] { 10001000 });
        fc.setName("name");
        fc.setYear((short) 2010);
        fc.save();

        assistant = new Assistant();
        assistant.setEmail("test@example.com");
        assistant.setFirstName("Test");
        assistant.setLastName("Tester");
        assistant.setQualification("g");
        assistant.save();

        contract = new Contract();
        contract.setAssistantId(assistant.getId());
        contract.setCompletionDate(new Date(10));
        contract.setConfirmationDate(new Date(100));
        contract.setDelegation(false);
        contract.setEndDate(new Date(1000));
        contract.setStartDate(new Date(20));
        contract.setType("Type");
        contract.save();

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
        course.save();

        employment = new Employment();
        employment.setId((long) 1);
        employment.setAssistantId(assistant.getId());
        employment.setContractId(contract.getId());
        employment.setCourseId(course.getId());
        employment.setFunds("0711");
        employment.setCostUnit(1);
        employment.setHourCount(40.0);
        employment.setMonth((byte) 10);
        employment.setQualification("g");
        employment.setRemark("Remark");
        employment.setYear((short) 2012);
        employment.save();

        activityEmployment = new ActivityEmployment();
    }

    /**
     * Tests the constructor of the class ActivityEmployment.
     */
    @Test
    public void testConstructor() {
        System.out.println("Constructor");

        assertNotNull(activityEmployment);
    }

    /**
     * Tests the method toString() of the class ActivityEmployment.
     */
    @Test
    public void testToString() {
        System.out.println("toString()");

        activityEmployment.setCourse(course.getDescription() + "("
                + course.getSemester() + ")");

        assertEquals(
            course.getDescription() + "(" + course.getSemester() + ")",
            activityEmployment.toString());
    }

    /**
     * Tests the method addYear() of the class ActivityEmployment.
     */
    @Test
    public void testAddYear() {
        System.out.println("addYear()");

        activityEmployment.addYear((short) 2009);

        assertTrue(2009 == activityEmployment.getYears().get(0));
    }

    /**
     * Tests the method addMonth() of the class ActivityEmployment.
     */
    @Test
    public void testAddMonth() {
        System.out.println("addMonth()");

        activityEmployment.addMonth((byte) 12);

        assertTrue(12 == activityEmployment.getMonths().get(0));
    }

    /**
     * Tests the method addHours of the class ActivityEmployment.
     */
    @Test
    public void testAddHours() {
        System.out.println("addHours()");

        activityEmployment.addHours(15.2);

        assertEquals(15.2, activityEmployment.getHours(), 0);
    }
}
