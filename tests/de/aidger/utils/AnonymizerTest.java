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

package de.aidger.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.BeforeClass;
import org.junit.Test;

import siena.SienaException;

import de.aidger.model.Runtime;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.Contract;
import de.aidger.model.models.Course;
import de.aidger.model.models.Employment;
import de.aidger.model.models.FinancialCategory;

/**
 * Tests the Anonymizer class.
 * 
 * @author aidGer Team
 */
public class AnonymizerTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
        Runtime.getInstance().initialize();
    }

    /**
     * Test of anonymizeAssistants method, of class Anonymizer.
     */
    @Test
    public void testAnonymizeAssistants() throws SienaException {
        System.out.println("anonymizeAssistants");

        String old = Runtime.getInstance().getOption("anonymize-time");
        Runtime.getInstance().setOption("anonymize-time", "365");

        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.YEAR, -1);
        Calendar now = new GregorianCalendar();

        Assistant a = new Assistant();
        a.setEmail("test@example.com");
        a.setFirstName("Test");
        a.setLastName("Tester");
        a.setQualification("g");
        a.save();

        Assistant b = new Assistant();
        b.setEmail("test@example.com");
        b.setFirstName("Test2");
        b.setLastName("Tester2");
        b.setQualification("u");
        b.save();

        Assistant c = new Assistant();
        c.setEmail("test@example.com");
        c.setFirstName("Test3");
        c.setLastName("Tester3");
        c.setQualification("u");
        c.save();

        FinancialCategory financial = new FinancialCategory();
        financial.setBudgetCosts(new Integer[] { 100, 200 });
        financial.setCostUnits(new Integer[] { 10001000, 20002000 });
        financial.setName("Tester");
        financial.setYear((short) 2010);
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

        Contract contract = new Contract();
        contract.setAssistantId(a.getId());
        contract.setCompletionDate(new Date(10));
        contract.setConfirmationDate(new Date(100));
        contract.setDelegation(false);
        contract.setEndDate(new Date(1000));
        contract.setStartDate(new Date(20));
        contract.setType("Type");
        contract.save();

        Employment employment = new Employment();
        employment.setAssistantId(a.getId());
        employment.setContractId(contract.getId());
        employment.setCourseId(course.getId());
        employment.setFunds("0711");
        employment.setCostUnit(1);
        employment.setHourCount(40.0);
        employment.setMonth((byte) cal.get(Calendar.MONTH));
        employment.setQualification("g");
        employment.setRemark("Remark");
        employment.setYear((short) cal.get(Calendar.YEAR));
        employment.save();

        Employment e2 = employment.clone();
        e2.setId(null);
        e2.setMonth((byte) now.get(Calendar.MONTH));
        e2.setYear((short) now.get(Calendar.YEAR));
        e2.setAssistantId(b.getId());
        e2.save();

        Anonymizer.anonymizeAssistants();

        // Assistant with old employment => anonymize
        Assistant assi = a.getById(a.getId());
        
        assertNotEquals(assi.getFirstName(), a.getFirstName());
        assertNotEquals(assi.getLastName(), a.getLastName());
        assertNotEquals(assi.getEmail(), a.getEmail());

        // Assistant with new employment => don't anonymize
        assi = a.getById(b.getId());
        assertEquals(assi, b);

        // Assistant with no employment => don't anonymize
        assi = a.getById(c.getId());
        assertEquals(assi, c);

        Runtime.getInstance().setOption("anonymize-time", old);
    }

}