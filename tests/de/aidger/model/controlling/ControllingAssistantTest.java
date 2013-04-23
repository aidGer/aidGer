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
package de.aidger.model.controlling;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;

import de.aidger.model.validators.ValidationException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.aidger.model.models.Activity;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.Contract;
import de.aidger.model.models.CostUnit;
import de.aidger.model.models.Course;
import de.aidger.model.models.Employment;
import de.aidger.model.models.FinancialCategory;
import de.aidger.model.models.HourlyWage;
import de.aidger.utils.reports.BalanceHelper;

/**
 * Tests the class ControllingAssistant.
 * 
 * @author aidGer Team
 */
public class ControllingAssistantTest {

    private Employment employment;
    private Course course;
    private Contract contract;
    private Assistant assistant;
    private FinancialCategory fc;
    private ControllingAssistant controllingAssistant;
    private ControllingAssistant controllingAssistant2;
    private CostUnit costUnit;

    /**
     * Prepares the test set.
     */
    @BeforeClass
    public static void beforeClassSetUp() {
        de.aidger.model.Runtime.getInstance().initialize();
        new HourlyWage().clearTable();
        new FinancialCategory().clearTable();
        new Activity().clearTable();
        new Employment().clearTable();
        new Course().clearTable();
        new Contract().clearTable();
        new Assistant().clearTable();
    }

    /**
     * Prepares the tests.
     */
    @Before
    public void setUp() {
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

        costUnit = new CostUnit();
        costUnit.setFunds("Test Fund");
        costUnit.setCostUnit("11111112");
        costUnit.setTokenDB("A");
        costUnit.save();

        employment = new Employment();
        employment.setId((long) 1);
        employment.setAssistantId(assistant.getId());
        employment.setContractId(contract.getId());
        employment.setCourseId(course.getId());
        employment.setFunds(costUnit.getTokenDB());
        employment.setCostUnit(Integer.parseInt(costUnit.getCostUnit()));
        employment.setHourCount(40.0);
        employment.setMonth((byte) 10);
        employment.setQualification("g");
        employment.setRemark("Remark");
        employment.setYear((short) 2012);
        employment.save();
    }

    @Test
    public void testConstructor() {
        System.out.println("Constructor");

        controllingAssistant = new ControllingAssistant();

        assertNotNull(controllingAssistant);
    }

    @Test
    public void testCompareTo() {
        System.out.println("compareTo()");

        controllingAssistant = new ControllingAssistant();
        controllingAssistant.setFirstName(assistant.getFirstName());
        controllingAssistant.setLastName(assistant.getLastName());
        new BalanceHelper();
        controllingAssistant.setCosts(BalanceHelper
            .calculatePreTaxBudgetCost(employment));

        controllingAssistant2 = new ControllingAssistant();
        controllingAssistant2.setFirstName(assistant.getFirstName());
        controllingAssistant2.setLastName(assistant.getLastName());
        new BalanceHelper();
        controllingAssistant2.setCosts(BalanceHelper
            .calculatePreTaxBudgetCost(employment));

        assertTrue(controllingAssistant.compareTo(controllingAssistant2) == 0);
    }

    /**
     * Cleans up after the tests.
     */
    @After
    public void cleanUp() throws ValidationException {
        employment.remove();
        contract.remove();
        course.remove();
        assistant.remove();
        fc.remove();
        costUnit.remove();
    }
}
