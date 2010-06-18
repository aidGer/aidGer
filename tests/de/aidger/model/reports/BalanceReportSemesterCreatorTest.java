/**
 * 
 */
package de.aidger.model.reports;

import static org.junit.Assert.assertNotNull;

import java.sql.Date;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import de.aidger.model.models.Assistant;
import de.aidger.model.models.Contract;
import de.aidger.model.models.Course;
import de.aidger.model.models.Employment;
import de.aidger.model.models.FinancialCategory;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * @author Phil
 * 
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
    public void cleanUp() throws AdoHiveException {

        course.remove();

        course2.remove();

        course3.remove();

        course4.remove();

        assistant.remove();

        employment1.remove();

        employment2.remove();

        contract.remove();

        financialCategory.remove();
    }

    /**
     * Prepares this test.
     * 
     * @throws AdoHiveException
     */
    @BeforeClass
    public static void beforeClassSetUp() throws AdoHiveException {
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

        balanceFilter = new BalanceFilter();
    }

    /**
     * Tests the constructor of the class BalanceReportSemesterCreator.
     * 
     * @throws AdoHiveException
     */
    @Test
    public void testConstructor() throws AdoHiveException {
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
