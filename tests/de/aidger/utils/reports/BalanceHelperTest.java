/**
 * 
 */
package de.aidger.utils.reports;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import de.aidger.model.models.Assistant;
import de.aidger.model.models.Contract;
import de.aidger.model.models.Course;
import de.aidger.model.models.Employment;
import de.aidger.model.reports.BalanceCourse;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * @author Phil
 * 
 */
public class BalanceHelperTest {

    private Course course = null;

    private Assistant assistant = null;

    private Employment employment1 = null;

    private Employment employment2 = null;

    private Contract contract = null;

    private BalanceHelper balanceHelper = null;

    private BalanceCourse balanceCourse = null;

    public BalanceHelperTest() throws AdoHiveException {
    }

    /**
     * Sets up the Test of the class BalanceHelper.
     * 
     * @throws AdoHiveException
     */
    @Before
    public void setUp() throws AdoHiveException {
        de.aidger.model.Runtime.getInstance().initialize();

        course = new Course();
        course.setAdvisor("Tester");
        course.setDescription("Description");
        course.setFinancialCategoryId(1);
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

        balanceCourse = new BalanceCourse();
        balanceCourse.setTitle("Description");
        balanceCourse.setLecturer("Test Tester");
        balanceCourse.setBasicAWS(course.getNumberOfGroups()
                * course.getUnqualifiedWorkingHours());
        balanceCourse.setPart('a');
        balanceCourse.setPlannedAWS(employment1.getHourCount()
                + employment2.getHourCount());
        balanceCourse
            .setResources((int) (10.0 * employment2.getHourCount() * 1.28));
        balanceCourse
            .setStudentFees((int) (10.0 * employment1.getHourCount() * 1.28));
        balanceCourse.setTargetAudience("Testers");
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
     * Tests the method getBalanceCourse() of class BalanceHelper.
     */
    @Test
    public void testGetBalanceCourse() {
        System.out.println("getBalanceCourse()");

        balanceHelper = new BalanceHelper();

        BalanceCourse result = balanceHelper.getBalanceCourse(course);

        assertNotNull(result);
        assertArrayEquals(balanceCourse.getCourseObject(), result
            .getCourseObject());
    }

    /**
     * Tests the method getYears().
     * 
     * @throws AdoHiveException
     */
    @Test
    public void testGetYears() throws AdoHiveException {
        System.out.println("getYears()");

        balanceHelper = new BalanceHelper();

        Course course2 = course.clone();
        course2.setSemester("2009");
        course2.save();

        Course course3 = course.clone();
        course3.setSemester("WS0910");
        course3.save();

        Vector years = balanceHelper.getYears();

        assertNotNull(years);
        assertEquals(3, years.size());
        assertEquals(2009, years.get(1));
        assertEquals(2010, years.get(2));
    }

    /**
     * Tests the method getSemesters().
     */
    @Test
    public void testGetSemesters() {
        System.out.println("getSemesters()");

        balanceHelper = new BalanceHelper();

        Vector semesters = balanceHelper.getSemesters();

        assertNotNull(semesters);
    }
}
