/**
 * 
 */
package de.aidger.utils.controlling;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.aidger.model.models.Activity;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.Contract;
import de.aidger.model.models.Course;
import de.aidger.model.models.Employment;
import de.aidger.model.models.FinancialCategory;
import de.aidger.model.models.HourlyWage;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * Tests the class ControllingHelper.
 * 
 * @author aidGer Team
 */
public class ControllingHelperTest {

    protected Course course = null;
    private Employment employment;
    private Assistant assistant;
    private Contract contract;
    private FinancialCategory fc;
    private ControllingHelper controllingHelper;

    @BeforeClass
    public static void beforeClassSetUp() throws AdoHiveException {
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
    public void setUp() throws AdoHiveException {
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
        course.setId(1);
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
        employment.setId(1);
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

        controllingHelper = new ControllingHelper();
    }

    /**
     * Tests the constructor of the class ControllingHelper.
     */
    @Test
    public void testConstructor() {
        System.out.println("Constructor");

        assertNotNull(controllingHelper);
    }

    /**
     * Tests the method getEmploymentYears() of the class ControllingHelper.
     */
    @Test
    public void testGetEmploymentYears() {
        System.out.println("getEmploymentYears()");

        int[] result = controllingHelper.getEmploymentYears();

        assertEquals(1, result.length);
        assertTrue(employment.getYear() == result[0]);
    }

    /**
     * Tests the method getYearMonths() of the class ControllingHelper.
     * 
     * @throws AdoHiveException
     */
    @Test
    public void testGetYearMonths() throws AdoHiveException {
        System.out.println("getYearMonths()");

        int[] result = controllingHelper.getYearMonths(employment.getYear());

        /*
         * The months after the last employment of a year will always be
         * included. Thus the months 10-12 should be included.
         */
        assertEquals(12 - employment.getMonth() + 1, result.length);
        assertTrue(employment.getMonth() == result[0]);
        assertTrue(12 == result[result.length - 1]);

        Employment employment2 = employment.clone();
        employment2.setMonth((byte) (employment.getMonth() + 1));
        employment2.setNew(true);
        employment2.save();

        Employment employment3 = employment.clone();
        employment3.setMonth((byte) (employment2.getMonth() + 1));
        employment3.setNew(true);
        employment3.save();

        controllingHelper = new ControllingHelper();

        result = controllingHelper.getYearMonths(employment.getYear());

        /*
         * The months 10-12 were added. Thus they should be included.
         */
        assertEquals(12 - employment.getMonth() + 1, result.length);
        assertTrue(employment.getMonth() == result[0]);
        assertTrue(employment2.getMonth() == result[1]);
        assertTrue(employment3.getMonth() == result[2]);
    }

    /**
     * Tests the method getFunds() of the class ControllingHelper.
     */
    @Test
    public void testGetFunds() {
        System.out.println("getFunds()");

        int[] result = controllingHelper.getFunds(employment.getYear(),
            employment.getMonth());

        assertEquals(1, result.length);
        assertTrue(employment.getCostUnit() == result[0]);
    }
}
