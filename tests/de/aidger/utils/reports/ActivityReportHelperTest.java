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
package de.aidger.utils.reports;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import siena.SienaException;

import de.aidger.model.models.Activity;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.Contract;
import de.aidger.model.models.Course;
import de.aidger.model.models.Employment;
import de.aidger.model.models.FinancialCategory;
import de.aidger.model.models.HourlyWage;
import de.aidger.model.reports.ActivityEmployment;

/**
 * Tests the class ActivityReportHelper.
 * 
 * @author aidGer Team
 */
public class ActivityReportHelperTest {

    protected Course course = null;
    private Employment employment;
    private Assistant assistant;
    private Contract contract;
    private FinancialCategory fc;
    private ActivityReportHelper activityReportHelper;

    @BeforeClass
    public static void beforeClassSetUp() throws SienaException {
        de.aidger.model.Runtime.getInstance().initialize();
        new HourlyWage().clearTable();
        new FinancialCategory().clearTable();
        new Employment().clearTable();
        new Activity().clearTable();
        new Course().clearTable();
        new Contract().clearTable();
        new Assistant().clearTable();
    }

    /**
     * Sets up every test.
     * 
     * @throws AdoHiveException
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

        activityReportHelper = new ActivityReportHelper();
    }

    @Test
    public void testConstructor() {
        System.out.println("Constructor");

        assertNotNull(activityReportHelper);
    }

    @Test
    public void testGetEmployments() throws SienaException {
        System.out.println("addCourseEmployment()");

        List<ActivityEmployment> result = activityReportHelper
            .getEmployments(assistant);

        assertEquals(1, result.size());

        ActivityEmployment activityEmployment = result.get(0);

        assertEquals(
            course.getDescription() + "(" + course.getSemester() + ")",
            activityEmployment.getCourse());
        assertEquals(employment.getHourCount(), activityEmployment.getHours(),
            0);
        assertEquals(1, activityEmployment.getMonths().size());
        assertEquals(employment.getMonth(), activityEmployment.getMonths().get(
            0));
        assertEquals(1, activityEmployment.getYears().size());
        assertEquals(employment.getYear(), activityEmployment.getYears().get(0));

        activityReportHelper = new ActivityReportHelper();

        Employment employment2 = employment.clone();
        employment2.setMonth((byte) (employment.getMonth() + 1));
        employment2.setId(null);
        employment2.save();

        result = activityReportHelper.getEmployments(assistant);

        assertEquals(1, result.size());

        activityEmployment = result.get(0);

        assertEquals(
            course.getDescription() + "(" + course.getSemester() + ")",
            activityEmployment.getCourse());
        assertEquals(employment.getHourCount() + employment2.getHourCount(),
            activityEmployment.getHours(), 0);
        assertEquals(2, activityEmployment.getMonths().size());
        assertEquals(employment.getMonth(), activityEmployment.getMonths().get(
            0));
        assertEquals(employment2.getMonth(), activityEmployment.getMonths()
            .get(1));
        assertEquals(1, activityEmployment.getYears().size());
        assertEquals(employment.getYear(), activityEmployment.getYears().get(0));

        activityReportHelper = new ActivityReportHelper();

        Employment employment3 = employment2.clone();
        employment3.setMonth((byte) (employment2.getMonth() + 1));
        employment3.setId(null);
        employment3.save();

        result = activityReportHelper.getEmployments(assistant);

        assertEquals(1, result.size());

        activityEmployment = result.get(0);

        assertEquals(
            course.getDescription() + "(" + course.getSemester() + ")",
            activityEmployment.getCourse());
        assertEquals(employment.getHourCount() + employment2.getHourCount()
                + employment3.getHourCount(), activityEmployment.getHours(), 0);
        assertEquals(2, activityEmployment.getMonths().size());
        assertEquals(employment.getMonth(), activityEmployment.getMonths().get(
            0));
        assertEquals(employment3.getMonth(), activityEmployment.getMonths()
            .get(1));
        assertEquals(1, activityEmployment.getYears().size());
        assertEquals(employment.getYear(), activityEmployment.getYears().get(0));

        employment2.remove();
        employment3.remove();

        activityReportHelper = new ActivityReportHelper();

        Employment employment4 = employment.clone();
        employment4.setMonth((byte) (employment.getMonth() + 2));
        employment4.setId(null);
        employment4.save();

        result = activityReportHelper.getEmployments(assistant);

        assertEquals(2, result.size());

        activityEmployment = result.get(0);

        assertEquals(
            course.getDescription() + "(" + course.getSemester() + ")",
            activityEmployment.getCourse());
        assertEquals(employment.getHourCount(), activityEmployment.getHours(),
            0);
        assertEquals(1, activityEmployment.getMonths().size());
        assertEquals(employment.getMonth(), activityEmployment.getMonths().get(
            0));
        assertEquals(1, activityEmployment.getYears().size());
        assertEquals(employment.getYear(), activityEmployment.getYears().get(0));

        activityEmployment = result.get(1);

        assertEquals(
            course.getDescription() + "(" + course.getSemester() + ")",
            activityEmployment.getCourse());
        assertEquals(employment4.getHourCount(), activityEmployment.getHours(),
            0);
        assertEquals(1, activityEmployment.getMonths().size());
        assertEquals(employment4.getMonth(), activityEmployment.getMonths()
            .get(0));
        assertEquals(1, activityEmployment.getYears().size());
        assertEquals(employment4.getYear(), activityEmployment.getYears()
            .get(0));

        activityReportHelper = new ActivityReportHelper();

        Employment employment5 = employment.clone();
        employment5.setMonth((byte) (1));
        employment5.setYear((short) (employment4.getYear() + 1));
        employment5.setId(null);
        employment5.save();

        result = activityReportHelper.getEmployments(assistant);

        assertEquals(2, result.size());

        activityEmployment = result.get(1);

        assertEquals(
            course.getDescription() + "(" + course.getSemester() + ")",
            activityEmployment.getCourse());
        assertEquals(employment4.getHourCount() + employment5.getHourCount(),
            activityEmployment.getHours(), 0);
        assertEquals(2, activityEmployment.getMonths().size());
        assertEquals(employment4.getMonth(), activityEmployment.getMonths()
            .get(0));
        assertEquals(employment5.getMonth(), activityEmployment.getMonths()
            .get(1));
        assertEquals(2, activityEmployment.getYears().size());
        assertEquals(employment4.getYear(), activityEmployment.getYears()
            .get(0));
        assertEquals(employment5.getYear(), activityEmployment.getYears()
            .get(1));

        employment4.remove();
        employment5.remove();

        activityReportHelper = new ActivityReportHelper();

        Employment employment6 = employment.clone();
        employment6.setMonth((byte) (employment.getMonth() + 2));
        employment6.setId(null);
        employment6.save();

        Employment employment7 = employment.clone();
        employment7.setMonth((byte) (employment.getMonth() + 1));
        employment7.setId(null);
        employment7.save();

        result = activityReportHelper.getEmployments(assistant);

        assertEquals(1, result.size());

        activityEmployment = result.get(0);

        assertEquals(
            course.getDescription() + "(" + course.getSemester() + ")",
            activityEmployment.getCourse());
        assertEquals(employment.getHourCount() + employment6.getHourCount()
                + employment7.getHourCount(), activityEmployment.getHours(), 0);
        assertEquals(2, activityEmployment.getMonths().size());
        assertEquals(employment.getMonth(), activityEmployment.getMonths().get(
            0));
        assertEquals(employment6.getMonth(), activityEmployment.getMonths()
            .get(1));
        assertEquals(1, activityEmployment.getYears().size());
        assertEquals(employment.getYear(), activityEmployment.getYears().get(0));

        employment6.remove();
        employment7.remove();

        activityReportHelper = new ActivityReportHelper();

        Course course2 = course.clone();
        course2.setDescription("Test Description");
        course2.setId(null);
        course2.save();

        Employment employment8 = employment.clone();
        employment8.setCourseId(course2.getId());
        employment8.setId(null);
        employment8.save();

        result = activityReportHelper.getEmployments(assistant);

        assertEquals(2, result.size());

        activityEmployment = result.get(1);

        assertEquals(course2.getDescription() + "(" + course2.getSemester()
                + ")", activityEmployment.getCourse());
        assertEquals(employment8.getHourCount(), activityEmployment.getHours(),
            0);
        assertEquals(1, activityEmployment.getMonths().size());
        assertEquals(employment8.getMonth(), activityEmployment.getMonths()
            .get(0));
        assertEquals(1, activityEmployment.getYears().size());
        assertEquals(employment8.getYear(), activityEmployment.getYears()
            .get(0));
    }

    @Test
    public void testGetEmploymentArray() throws SienaException {
        System.out.println("getEmploymentArray()");
        Employment employment2 = employment.clone();
        employment2.setMonth((byte) (employment.getMonth() + 1));
        employment2.setId(null);
        employment2.save();

        List<ActivityEmployment> result = activityReportHelper
            .getEmployments(assistant);

        assertEquals(1, result.size());

        ActivityEmployment activityEmployment = result.get(0);

        Object[] resultArray = activityReportHelper
            .getEmploymentArray(activityEmployment);
        Object[] expected = { "10.2012 - 11.2012",
                course.getDescription() + "(" + course.getSemester() + ")",
                (employment.getHourCount() + employment2.getHourCount()) + "h" };

        assertArrayEquals(expected, resultArray);

        activityReportHelper = new ActivityReportHelper();

        Employment employment3 = employment.clone();
        employment3.setMonth((byte) (1));
        employment3.setId(null);
        employment3.save();

        result = activityReportHelper.getEmployments(assistant);

        assertEquals(2, result.size());

        activityEmployment = result.get(0);

        resultArray = activityReportHelper
            .getEmploymentArray(activityEmployment);
        expected = new Object[] { "01.2012 - 01.2012",
                course.getDescription() + "(" + course.getSemester() + ")",
                employment3.getHourCount() + "h" };

        assertArrayEquals(expected, resultArray);

        employment2.remove();
        employment3.remove();
    }

    @After
    public void cleanUp() throws SienaException {
        employment.clearTable();
        contract.clearTable();
        course.clearTable();
        assistant.clearTable();
        fc.clearTable();
    }
}
