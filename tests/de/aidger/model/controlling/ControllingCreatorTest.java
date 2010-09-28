/**
 * 
 */
package de.aidger.model.controlling;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;

import org.junit.After;
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
import de.aidger.utils.reports.BalanceHelper;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * Tests the class ControllingCreator.
 * 
 * @author aidGer Team
 */
public class ControllingCreatorTest {

    private Employment employment;
    private Course course;
    private Contract contract;
    private Assistant assistant;
    private FinancialCategory fc;
    private ControllingCreator controllingCreator;

    /**
     * Prepares the test set.
     * 
     * @throws AdoHiveException
     */
    @BeforeClass
    public static void beforeClassSetUp() throws AdoHiveException {
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
     * 
     * @throws AdoHiveException
     */
    @Before
    public void setUp() throws AdoHiveException {
        fc = new FinancialCategory();
        fc.setBudgetCosts(new Integer[] { 100 });
        fc.setFunds(new Integer[] { 10001000 });
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
        employment.setCostUnit("0711");
        employment.setFunds(1);
        employment.setHourCount(40.0);
        employment.setMonth((byte) 10);
        employment.setQualification("g");
        employment.setRemark("Remark");
        employment.setYear((short) 2012);
        employment.save();
    }

    /**
     * Tests the constructor of the class ControllingCreator.
     */
    @Test
    public void testConstructor() {
        System.out.println("Constructor");

        controllingCreator = new ControllingCreator(employment.getYear(),
            employment.getMonth(), employment.getFunds());

        assertNotNull(controllingCreator);
    }

    /**
     * Tests the method getAssistants of the class ControllingCreator.
     */
    @Test
    public void testGetAssistants() {
        System.out.println("getAssistants()");

        controllingCreator = new ControllingCreator(employment.getYear(),
            employment.getMonth(), employment.getFunds());

        ControllingAssistant expectedAssistant = new ControllingAssistant();
        expectedAssistant.setName(assistant.getFirstName() + " "
                + assistant.getLastName());
        new BalanceHelper();
        expectedAssistant.setCosts(BalanceHelper
            .calculatePreTaxBudgetCost(employment));

        assertArrayEquals(controllingCreator.getAssistants(false).get(0)
            .getObjectArray(), expectedAssistant.getObjectArray());
    }

    /**
     * Cleans up after the tests.
     * 
     * @throws AdoHiveException
     */
    @After
    public void cleanUp() throws AdoHiveException {
        fc.remove();
        assistant.remove();
        contract.remove();
        course.remove();
        employment.remove();
    }
}
