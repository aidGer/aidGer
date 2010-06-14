/**
 * 
 */
package de.aidger.utils.pdf;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.sql.Date;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.aidger.model.models.Assistant;
import de.aidger.model.models.Contract;
import de.aidger.model.models.Course;
import de.aidger.model.models.Employment;
import de.aidger.model.models.FinancialCategory;
import de.aidger.utils.reports.BalanceHelper;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * @author Phil
 * 
 */
public class BalanceReportConverterTest {

    private Course course = null;

    private Assistant assistant = null;

    private Employment employment1 = null;

    private Employment employment2 = null;

    private Contract contract = null;

    private FinancialCategory financialCategory = null;

    private BalanceReportConverter balanceReportConverter = null;

    public BalanceReportConverterTest() {

    }

    /**
     * Prepares this test.
     * 
     * @throws AdoHiveException
     */
    @Before
    public void setUp() throws AdoHiveException {
        de.aidger.model.Runtime.getInstance().initialize();

        financialCategory = new FinancialCategory();
        financialCategory.setBudgetCosts(new int[] { 1000 });
        financialCategory.setFunds(new int[] { 10000000 });
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
        course.setUnqualifiedWorkingHours(100);
        course.save();

        assistant = new Assistant();
        assistant.setEmail("test@example.com");
        assistant.setFirstName("Test");
        assistant.setLastName("Tester");
        assistant.setQualification("g");
        assistant.save();

        contract = new Contract();
        contract.setNew(true);
        contract.setStartDate(new Date(1970, 1, 1));
        contract.setCompletionDate(new Date(1970, 1, 3));
        contract.setConfirmationDate(new Date(1970, 1, 2));
        contract.setEndDate(new Date(1970, 1, 4));
        contract.setDelegation(true);
        contract.setType("Test type");
        contract.save();

        employment1 = new Employment();
        employment1.setAssistantId(assistant.getId());
        employment1.setCourseId(course.getId());
        employment1.setFunds(1);
        employment1.setHourCount(10.0);
        employment1.setContractId(contract.getId());
        employment1.setCostUnit("Test unit");
        employment1.setMonth((byte) 1);
        employment1.setQualification("g");
        employment1.setRemark("Test remark");
        employment1.setYear((short) 1970);
        employment1.setNew(true);
        employment1.save();

        employment2 = new Employment();
        employment2.setAssistantId(assistant.getId());
        employment2.setCourseId(course.getId());
        employment2.setFunds(2);
        employment2.setHourCount(10.0);
        employment2.setContractId(contract.getId());
        employment2.setCostUnit("Test unit");
        employment2.setMonth((byte) 1);
        employment2.setQualification("g");
        employment2.setRemark("Test remark");
        employment2.setYear((short) 1970);
        employment2.setNew(true);
        employment2.save();
    }

    /**
     * Tests the constructor of the class BalanceReportConverter.
     * 
     * @throws AdoHiveException
     */
    @Test
    public void testConstructor() throws AdoHiveException {
        System.out.println("Constructor");
        Course course2 = course.clone();
        course2.setSemester("WS1011");
        course2.save();

        Course course3 = course2.clone();
        course3.setGroup("Test group 2");
        course3.save();

        Course course4 = course2.clone();
        course4.setGroup("Test group 2");
        course4.save();

        Course course5 = course.clone();
        course5.setSemester("WS0001");
        course5.save();

        Course course6 = course.clone();
        course6.setSemester("WS9900");
        course6.save();

        Vector years = new BalanceHelper().getYears();

        File testFile = new File("Test_Report");
        balanceReportConverter = new BalanceReportConverter(testFile, 1, null,
            null);

        File file = new File("Test_Report.pdf");
        assertTrue(file.exists());

        for (int i = 0; i < years.size(); i++) {
            testFile = new File("Test_Report");
            balanceReportConverter = new BalanceReportConverter(testFile, 2,
                years.get(i), null);

            file = new File("Test_Report.pdf");
            assertTrue(file.exists());
        }

        for (int i = 0; i < years.size(); i++) {
            testFile = new File("Test_Report.pdf");
            balanceReportConverter = new BalanceReportConverter(testFile, 2,
                years.get(i), null);

            file = new File("Test_Report.pdf");
            assertTrue(file.exists());
        }

        for (int i = 0; i < years.size(); i++) {
            testFile = new File("Test_Report.test");
            balanceReportConverter = new BalanceReportConverter(testFile, 2,
                years.get(i), null);

            file = new File("Test_Report.test.pdf");
            assertTrue(file.exists());
        }

        testFile = new File("Test_Report.pdf");
        balanceReportConverter = new BalanceReportConverter(testFile, 3, course
            .getSemester(), null);

        file = new File("Test_Report.pdf");
        assertTrue(file.exists());
    }

    /**
     * Cleans up after the test.
     */
    @After
    public void cleanUp() {
        File file = new File("Test_Report.pdf");
        file.delete();
        file = new File("Test_Report.test.pdf");
        file.delete();
    }
}
