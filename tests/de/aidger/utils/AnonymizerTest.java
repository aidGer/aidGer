/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.aidger.utils;

import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import de.aidger.model.Runtime;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.Contract;
import de.aidger.model.models.Course;
import de.aidger.model.models.Employment;
import de.aidger.model.models.FinancialCategory;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
    public void testAnonymizeAssistants() throws AdoHiveException {
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

        FinancialCategory financial = new FinancialCategory();
        financial.setBudgetCosts(new Integer[] { 100, 200 });
        financial.setFunds(new Integer[] { 10001000, 20002000 });
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
        employment.setCostUnit("0711");
        employment.setFunds(1);
        employment.setHourCount(40.0);
        employment.setMonth((byte) cal.get(Calendar.MONTH));
        employment.setQualification("g");
        employment.setRemark("Remark");
        employment.setYear((short) cal.get(Calendar.YEAR));
        employment.save();

        employment.setNew(true);
        employment.setMonth((byte) now.get(Calendar.MONTH));
        employment.setYear((short) now.get(Calendar.YEAR));
        employment.setAssistantId(b.getId());
        employment.save();

        Anonymizer.anonymizeAssistants();

        Assistant assi = new Assistant(a.getById(a.getId()));

        assertFalse(assi.getFirstName().equals(a.getFirstName()));
        assertFalse(assi.getLastName().equals(a.getLastName()));
        assertFalse(assi.getEmail().equals(a.getEmail()));

        assi = new Assistant(a.getById(b.getId()));

        assertEquals(assi, b);

        Runtime.getInstance().setOption("anonymize-time", old);
    }

}