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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
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
import de.aidger.model.reports.BalanceCourse;
import de.aidger.model.reports.BalanceFilter;
import de.aidger.model.reports.BalanceCourse.BudgetCost;

/**
 * Tests the class BalanceHelper.
 * 
 * @author aidGer Team
 */
public class BalanceHelperTest {

    private Course course = null;

    private Course course2 = null;

    private Course course3 = null;

    private Course course4 = null;

    private Assistant assistant = null;

    private Employment employment1 = null;

    private Employment employment2 = null;

    private Contract contract = null;

    private FinancialCategory financialCategory = null;

    private BalanceHelper balanceHelper = null;

    private BalanceCourse balanceCourse = null;

    private BalanceFilter balanceFilter = null;

    private HourlyWage hourlyWage = null;

    @BeforeClass
    public static void beforeClassSetUp() throws SienaException {
        de.aidger.model.Runtime.getInstance().initialize();
        new HourlyWage().clearTable();
        new FinancialCategory().clearTable();
        new Employment().clearTable();
        new Course().clearTable();
        new Contract().clearTable();
        new Assistant().clearTable();
        new Activity().clearTable();
    }

    @After
    public void cleanUp() throws SienaException {

        course.remove();

        course2.remove();

        course3.remove();

        course4.remove();

        assistant.remove();

        employment1.remove();

        employment2.remove();

        contract.remove();

        financialCategory.remove();

        hourlyWage.remove();
    }

    /**
     * Sets up the Test of the class BalanceHelper.
     * 
     * @throws AdoHiveException
     */
    @Before
    public void setUp() throws SienaException {
        financialCategory = new FinancialCategory();
        financialCategory.setBudgetCosts(new Integer[] { 1000 });
        financialCategory.setCostUnits(new Integer[] { 10000000 });
        financialCategory.setName("Test Category");
        financialCategory.setYear((short) 2010);
        financialCategory.save();

        course = new Course();
        course.setAdvisor("Tester");
        course.setDescription("Description");
        course.setFinancialCategoryId(financialCategory.getId());
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

        course2 = course.clone();
        course2.setLecturer("Test Tester 2");
        course2.save();

        course3 = course.clone();
        course3.setTargetAudience("Testers 2");
        course3.save();

        course4 = course.clone();
        course4.setGroup("Test group 2");
        course4.save();

        assistant = new Assistant();
        assistant.setEmail("test@example.com");
        assistant.setFirstName("Test");
        assistant.setLastName("Tester");
        assistant.setQualification("g");
        assistant.save();

        contract = new Contract();
        contract.setStartDate(new Date(1970, 1, 1));
        contract.setCompletionDate(new Date(1970, 1, 2));
        contract.setConfirmationDate(new Date(1970, 1, 3));
        contract.setEndDate(new Date(1970, 1, 4));
        contract.setDelegation(true);
        contract.setType("Test type");
        contract.setAssistantId(assistant.getId());
        contract.save();

        employment1 = new Employment();
        employment1.setAssistantId(assistant.getId());
        employment1.setCourseId(course.getId());
        employment1.setCostUnit(1);
        employment1.setHourCount(10.0);
        employment1.setContractId(contract.getId());
        employment1.setFunds("Test unit");
        employment1.setMonth((byte) 1);
        employment1.setQualification("g");
        employment1.setRemark("Test remark");
        employment1.setYear((short) 1970);
        employment1.save();

        employment2 = new Employment();
        employment2.setAssistantId(assistant.getId());
        employment2.setCourseId(course.getId());
        employment2.setCostUnit(1);
        employment2.setHourCount(10.0);
        employment2.setContractId(contract.getId());
        employment2.setFunds("Test unit");
        employment2.setMonth((byte) 1);
        employment2.setQualification("g");
        employment2.setRemark("Test remark");
        employment2.setYear((short) 1970);
        employment2.save();

        hourlyWage = new HourlyWage();
        hourlyWage.setMonth(employment1.getMonth());
        hourlyWage.setYear(employment1.getYear());
        hourlyWage.setQualification(employment1.getQualification());
        hourlyWage.setWage(new BigDecimal(10));
        hourlyWage.save();

        balanceCourse = new BalanceCourse();
        balanceCourse.setTitle("Description");
        balanceCourse.setLecturer("Test Tester");
        balanceCourse.setBasicAWS(course.getNumberOfGroups()
                * course.getUnqualifiedWorkingHours());
        balanceCourse.setPart('a');
        balanceCourse.setPlannedAWS(employment1.getHourCount()
                + employment2.getHourCount());
        balanceCourse.setTargetAudience("Testers");
        balanceCourse.addBudgetCost(employment1.getCostUnit(), employment1
            .getFunds(), 120);
        balanceCourse.addBudgetCost(employment2.getCostUnit(), employment2
            .getFunds(), 120);
    }

    /**
     * Tests the constructor of the class BalanceHelper.
     */
    @Test
    public void testConstructor() {
        System.out.println("Constructor");

        balanceHelper = new BalanceHelper();
    }

    /**
     * Test the method filterCourses() of class BalanceHelper.
     * 
     * @throws AdoHiveException
     */
    @Test
    public void testFilterCourses() throws SienaException {
        System.out.println("filterCourses()");

        balanceHelper = new BalanceHelper();

        List<Course> courses = new Course().getAll();

        /*
         * The course should exist in the course list.
         */
        boolean resultBoolean = false;
        for (Course listCourse : courses) {
            if (listCourse.equals(course)) {
                resultBoolean = true;
            }
        }
        assertTrue(resultBoolean);

        List<Course> filteredCourses = balanceHelper.filterCourses(courses,
            null);

        /*
         * The course should exist, after filtering with a null filter.
         */
        resultBoolean = false;
        for (Course listCourse : filteredCourses) {
            if (listCourse.equals(course)) {
                resultBoolean = true;
            }
        }
        assertTrue(resultBoolean);

        balanceFilter = new BalanceFilter();

        filteredCourses = balanceHelper.filterCourses(courses, balanceFilter);

        /*
         * The course should exist after filtering without any filters.
         */
        resultBoolean = false;
        for (Course listCourse : filteredCourses) {
            if (listCourse.equals(course)) {
                resultBoolean = true;
            }
        }
        assertTrue(resultBoolean);

        balanceFilter = new BalanceFilter();
        balanceFilter.addGroup("Test filter");

        filteredCourses = balanceHelper.filterCourses(courses, balanceFilter);

        /*
         * The course should not exist after filtering with a filter that it
         * doesn't contain.
         */
        resultBoolean = false;
        for (Course listCourse : filteredCourses) {
            if (listCourse.equals(course)) {
                resultBoolean = true;
            }
        }
        assertTrue(!resultBoolean);

        balanceFilter = new BalanceFilter();
        balanceFilter.addLecturer("Test filter");

        filteredCourses = balanceHelper.filterCourses(courses, balanceFilter);

        /*
         * The course should not exist after filtering with a filter that it
         * doesn't contain.
         */
        resultBoolean = false;
        for (Course listCourse : filteredCourses) {
            if (listCourse.equals(course)) {
                resultBoolean = true;
            }
        }
        assertTrue(!resultBoolean);

        balanceFilter = new BalanceFilter();
        balanceFilter.addTargetAudience("Test filter");

        filteredCourses = balanceHelper.filterCourses(courses, balanceFilter);

        /*
         * The course should not exist after filtering with a filter that it
         * doesn't contain.
         */
        resultBoolean = false;
        for (Course listCourse : filteredCourses) {
            if (listCourse.equals(course)) {
                resultBoolean = true;
            }
        }
        assertTrue(!resultBoolean);

        balanceFilter = new BalanceFilter();
        balanceFilter.addGroup(course.getGroup());
        balanceFilter.addGroup(course2.getGroup());
        balanceFilter.addGroup(course3.getGroup());

        balanceFilter.addLecturer(course.getLecturer());
        balanceFilter.addLecturer(course2.getLecturer());
        balanceFilter.addLecturer(course3.getLecturer());

        balanceFilter.addTargetAudience(course.getTargetAudience());
        balanceFilter.addTargetAudience(course2.getTargetAudience());
        balanceFilter.addTargetAudience(course3.getTargetAudience());

        filteredCourses = balanceHelper.filterCourses(courses, balanceFilter);

        /*
         * The first course should exist after filtering with filters from its
         * course data.
         */
        resultBoolean = false;
        for (Course listCourse : filteredCourses) {
            if (listCourse.equals(course)) {
                resultBoolean = true;
            }
        }
        assertTrue(resultBoolean);

        /*
         * The second course should exist after filtering with filters from its
         * course data.
         */
        resultBoolean = false;
        for (Course listCourse : filteredCourses) {
            if (listCourse.equals(course2)) {
                resultBoolean = true;
            }
        }
        assertTrue(resultBoolean);

        /*
         * The third course should exist after filtering with filters from its
         * course data.
         */
        resultBoolean = false;
        for (Course listCourse : filteredCourses) {
            if (listCourse.equals(course3)) {
                resultBoolean = true;
            }
        }
        assertTrue(resultBoolean);
    }

    /**
     * Tests the method getYearSemesters() of class BalanceHelper.
     */
    @Test
    public void testGetYearSemesters() {
        System.out.println("getYearSemesters()");
        balanceHelper = new BalanceHelper();

        ArrayList<String> years = balanceHelper.getYearSemesters(2000);

        assertEquals("2000", years.get(0));
        assertEquals("WS9900", years.get(1));
        assertEquals("SS00", years.get(2));
        assertEquals("WS0001", years.get(3));

        years = balanceHelper.getYearSemesters(2001);

        assertEquals("2001", years.get(0));
        assertEquals("WS0001", years.get(1));
        assertEquals("SS01", years.get(2));
        assertEquals("WS0102", years.get(3));

        years = balanceHelper.getYearSemesters(2009);

        assertEquals("2009", years.get(0));
        assertEquals("WS0809", years.get(1));
        assertEquals("SS09", years.get(2));
        assertEquals("WS0910", years.get(3));

        years = balanceHelper.getYearSemesters(2010);

        assertEquals("2010", years.get(0));
        assertEquals("WS0910", years.get(1));
        assertEquals("SS10", years.get(2));
        assertEquals("WS1011", years.get(3));

        years = balanceHelper.getYearSemesters(2011);

        assertEquals("2011", years.get(0));
        assertEquals("WS1011", years.get(1));
        assertEquals("SS11", years.get(2));
        assertEquals("WS1112", years.get(3));

        years = balanceHelper.getYearSemesters(2099);

        assertEquals("2099", years.get(0));
        assertEquals("WS9899", years.get(1));
        assertEquals("SS99", years.get(2));
        assertEquals("WS9900", years.get(3));
    }

    /**
     * Tests the method getBalanceCourse() of class BalanceHelper.
     */
    @Test
    public void testGetBalanceCourse() {
        System.out.println("getBalanceCourse()");

        balanceHelper = new BalanceHelper();

        BalanceCourse result = balanceHelper.getBalanceCourse(course);

        assertNotNull(result);

        Object[] resultCourseObject = balanceCourse.getCourseObject();
        for (int i = 0; i < resultCourseObject.length - 2; i++) {
            assertEquals(balanceCourse.getCourseObject()[i],
                resultCourseObject[i]);
        }
        ArrayList<BudgetCost> resultBudgetCosts = (ArrayList<BudgetCost>) resultCourseObject[resultCourseObject.length - 1];
        assertEquals(employment1.getCostUnit(), resultBudgetCosts.get(0)
            .getId(), 0.001);
        assertEquals(employment1.getFunds(), resultBudgetCosts.get(0).getName());
        assertEquals(new BigDecimal(240.0).setScale(2).doubleValue(),
            resultBudgetCosts.get(0).getValue(), 0.001);
        assertEquals(employment2.getCostUnit(), resultBudgetCosts.get(0)
            .getId(), 0.001);
        assertEquals(employment2.getFunds(), resultBudgetCosts.get(0).getName());
    }

    /**
     * Tests the method getYears().
     * 
     * @throws AdoHiveException
     */
    @Test
    public void testGetYears() throws SienaException {
        System.out.println("getYears()");

        balanceHelper = new BalanceHelper();

        Course course2 = course.clone();
        course2.setSemester("2000");
        course2.save();

        Course course3 = course.clone();
        course3.setSemester("WS0910");
        course3.save();

        ArrayList years = balanceHelper.getYears();

        /*
         * The years specified above should be available.
         */
        assertNotNull(years);
        assertTrue(years.contains(2009));
        assertTrue(years.contains(2010));
        assertTrue(years.contains(2000));

        employment1.clearTable();
        new Activity().clearTable();

        course.clearTable();

        /*
         * Without any courses, there shouldn't be any years.
         */
        years = balanceHelper.getYears();
        assertTrue(years.isEmpty());
    }

    /**
     * Tests the method getSemesters().
     */
    @Test
    public void testGetSemesters() {
        System.out.println("getSemesters()");

        balanceHelper = new BalanceHelper();

        ArrayList semesters = balanceHelper.getSemesters();

        assertNotNull(semesters);
    }

    /**
     * Tests the method courseExists() of class BalanceHelper.
     */
    @Test
    public void testCourseExists() {
        System.out.println("courseExists()");

        balanceHelper = new BalanceHelper();

        assertTrue(balanceHelper.courseExists(course.getSemester(), null));

        assertTrue(!balanceHelper.courseExists("Test semester", null));
    }

    /**
     * Tests the method calculateBudgetCost() of the class BalanceHelper.
     * 
     * @throws AdoHiveException
     */
    @Test
    public void testCalculateBudgetCost() throws SienaException {
        System.out.println("calculateBudgetCost()");

        de.aidger.model.Runtime.getInstance().setOption("calc-method", "0");
        de.aidger.model.Runtime.getInstance().setOption("historic-factor",
            "1.2");

        new BalanceHelper();

        assertEquals(10 * employment1.getHourCount() * 1.2, BalanceHelper
            .calculateBudgetCost(employment1), 0);

        de.aidger.model.Runtime.getInstance().setOption("calc-method", "1");
        de.aidger.model.Runtime.getInstance().setOption("pessimistic-factor",
            "1.4");

        assertEquals(10 * employment1.getHourCount() * 1.4, BalanceHelper
            .calculateBudgetCost(employment1), 0);

        hourlyWage.remove();

        assertEquals(0, BalanceHelper.calculateBudgetCost(employment1), 0);
    }

    /**
     * Tests the method calculatePreTaxBudgetCost() of the class BalanceHelper.
     * 
     * @throws AdoHiveException
     */
    @Test
    public void testCalculatePreTaxBudgetCost() throws SienaException {
        System.out.println("calculatePreTaxBudgetCost()");

        new BalanceHelper();

        assertEquals(10 * employment1.getHourCount(), BalanceHelper
            .calculatePreTaxBudgetCost(employment1), 0);

        hourlyWage.remove();

        assertEquals(-1, BalanceHelper.calculatePreTaxBudgetCost(employment1),
            0);
    }
}
