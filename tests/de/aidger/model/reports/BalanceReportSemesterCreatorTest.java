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

import static org.junit.Assert.assertNotNull;

import java.sql.Date;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import siena.SienaException;

import de.aidger.model.models.Assistant;
import de.aidger.model.models.Contract;
import de.aidger.model.models.Course;
import de.aidger.model.models.Employment;
import de.aidger.model.models.FinancialCategory;

/**
 * Tests the class BalanceReportSemesterCreator.
 * 
 * @author aidGer Team
 */
public class BalanceReportSemesterCreatorTest {

    private static Course course = null;

    private static Course course2 = null;

    private static Course course3 = null;

    private static Course course4 = null;

    private static Assistant assistant = null;

    private static Employment employment1 = null;

    private static Employment employment2 = null;

    private static Contract contract = null;

    private static FinancialCategory financialCategory = null;

    private static BalanceFilter balanceFilter = null;

    private BalanceReportSemesterCreator balanceReportSemesterCreator = null;

    public BalanceReportSemesterCreatorTest() {

    }

    @After
    public void cleanUp() throws SienaException {

        employment1.remove();

        employment2.remove();

        contract.remove();

        course.remove();

        course2.remove();

        course3.remove();

        course4.remove();

        assistant.remove();

        financialCategory.remove();
    }

    /**
     * Prepares this test.
     * 
     * @throws SienaException
     */
    @BeforeClass
    public static void beforeClassSetUp() throws SienaException {
        de.aidger.model.Runtime.getInstance().initialize();

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
        employment2.setCostUnit(2);
        employment2.setHourCount(10.0);
        employment2.setContractId(contract.getId());
        employment2.setFunds("Test unit");
        employment2.setMonth((byte) 1);
        employment2.setQualification("g");
        employment2.setRemark("Test remark");
        employment2.setYear((short) 1970);
        employment2.save();

        balanceFilter = new BalanceFilter();
    }

    /**
     * Tests the constructor of the class BalanceReportSemesterCreator.
     * 
     * @throws SienaException
     */
    @Test
    public void testConstructor() throws SienaException {
        System.out.println("Constructor");

        balanceReportSemesterCreator = new BalanceReportSemesterCreator(course
            .getSemester(), null, 0);

        assertNotNull(balanceReportSemesterCreator.getGroupCreators());

        balanceReportSemesterCreator = new BalanceReportSemesterCreator(course
            .getSemester(), balanceFilter, 0);

        assertNotNull(balanceReportSemesterCreator.getGroupCreators());

        balanceFilter.addGroup(course.getGroup());
        balanceFilter.addGroup(course2.getGroup());
        balanceFilter.addGroup(course3.getGroup());

        balanceFilter.addLecturer(course.getLecturer());
        balanceFilter.addLecturer(course2.getLecturer());
        balanceFilter.addLecturer(course3.getLecturer());

        balanceFilter.addTargetAudience(course.getTargetAudience());
        balanceFilter.addTargetAudience(course2.getTargetAudience());
        balanceFilter.addTargetAudience(course3.getTargetAudience());

        balanceReportSemesterCreator = new BalanceReportSemesterCreator(course
            .getSemester(), balanceFilter, 0);

        assertNotNull(balanceReportSemesterCreator.getGroupCreators());
    }
}
