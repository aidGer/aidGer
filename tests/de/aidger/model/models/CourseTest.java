package de.aidger.model.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * Tests the Course class.
 * 
 * @author aidGer Team
 */
public class CourseTest {

    protected Course course = null;

    @BeforeClass
    public static void beforeClassSetUp() {
        de.aidger.model.Runtime.getInstance().initialize();
    }

    @Before
    public void setUp() throws AdoHiveException {
        FinancialCategory fc = new FinancialCategory();
        fc.setBudgetCosts(new Integer[] { 100 });
        fc.setFunds(new Integer[] { 10001000 });
        fc.setName("name");
        fc.setYear((short) 2010);
        fc.save();

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
    }

    /**
     * Test of constructor, of class Course.
     */
    @Test
    public void testConstructor() throws AdoHiveException {
        System.out.println("Constructor");

        course.setNew(true);
        course.save();

        Course result = new Course(course.getById(course.getId()));

        assertNotNull(result);
        assertEquals(course, result);
    }

    /**
     * Test of validation, of class Course.
     */
    @Test
    public void testValidation() throws AdoHiveException {
        System.out.println("Validation");

        course.setNew(true);
        assertTrue(course.save());

        course.setDescription(null);
        assertFalse(course.save());
        course.resetErrors();
        course.setDescription("Description");

        course.setGroup(null);
        assertFalse(course.save());
        course.resetErrors();
        course.setGroup("2");

        course.setLecturer(null);
        assertFalse(course.save());
        course.resetErrors();
        course.setLecturer("Test Tester");

        course.setNumberOfGroups(-1);
        assertFalse(course.save());
        course.resetErrors();
        course.setNumberOfGroups(3);

        course.setSemester(null);
        assertFalse(course.save());
        course.resetErrors();

        course.setSemester("abc 2000");
        assertFalse(course.save());
        course.resetErrors();
        course.setSemester("SS09");

        course.setUnqualifiedWorkingHours(0.0);
        assertFalse(course.save());
        course.resetErrors();
    }

    /**
     * Test of validateOnRemove, of class Contract.
     */
    @Test
    public void testValidateOnRemove() throws AdoHiveException {
        System.out.println("validateOnRemove");

        assertTrue(course.save());
        assertTrue(course.remove());

        course.save();
        Activity activity = new Activity();
        activity.setAssistantId(null);
        activity.setContent("New assistant");
        activity.setCourseId(course.getId());
        activity.setDate(new java.sql.Date(100));
        activity.setDocumentType("Test Type");
        activity.setProcessor("T");
        activity.setRemark("Remark");
        activity.setSender("Test Sender");
        activity.setType("Test Type");
        activity.save();

        assertFalse(course.remove());
        course.resetErrors();
        activity.remove();

        Assistant assistant = new Assistant();
        assistant.setEmail("test@example.com");
        assistant.setFirstName("Test");
        assistant.setLastName("Tester");
        assistant.setQualification("g");
        assistant.save();

        Contract contract = new Contract();
        contract.setAssistantId(assistant.getId());
        contract.setCompletionDate(new Date(10));
        contract.setConfirmationDate(new Date(100));
        contract.setDelegation(false);
        contract.setEndDate(new Date(1000));
        contract.setStartDate(new Date(20));
        contract.setType("Type");
        contract.save();

        Employment employment = new Employment();
        employment.setAssistantId(assistant.getId());
        employment.setContractId(contract.getId());
        employment.setCourseId(course.getId());
        employment.setFunds("0711");
        employment.setCostUnit(1);
        employment.setHourCount(40.0);
        employment.setMonth((byte) 10);
        employment.setQualification("g");
        employment.setRemark("Remark");
        employment.setYear((short) 2010);
        employment.save();

        assertFalse(course.remove());
        course.resetErrors();
        employment.remove();

        assertTrue(course.remove());
    }

    /**
     * Test of clone method, of class Course.
     */
    @Test
    public void testClone() {
        System.out.println("clone");

        Course result = course.clone();

        assertEquals(course.getId(), result.getId());
        assertEquals(course.getAdvisor(), result.getAdvisor());
        assertEquals(course.getDescription(), result.getDescription());
        assertEquals(course.getFinancialCategoryId(), result
            .getFinancialCategoryId());
        assertEquals(course.getGroup(), result.getGroup());
        assertEquals(course.getLecturer(), result.getLecturer());
        assertEquals(course.getNumberOfGroups(), result.getNumberOfGroups());
        assertEquals(course.getPart(), result.getPart());
        assertEquals(course.getRemark(), result.getRemark());
        assertEquals(course.getScope(), result.getScope());
        assertEquals(course.getSemester(), result.getSemester());
        assertEquals(course.getTargetAudience(), result.getTargetAudience());
        assertEquals(course.getUnqualifiedWorkingHours(), result
            .getUnqualifiedWorkingHours(), 0.001);
    }

    /**
     * Test of equals method, of class Course.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Course result = course.clone();

        assertEquals(course, result);
        assertFalse(course.equals(new Object()));
    }

    /**
     * Test of hashCode method, of class Course.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Course result = course.clone();

        assertEquals(course.hashCode(), result.hashCode());
    }

    /**
     * Test of getCourses method, of class Course.
     */
    @Test
    public void testGetCourses_FinancialCategory() throws AdoHiveException {
        System.out.println("getCourses");

        FinancialCategory fc = new FinancialCategory();
        fc.setBudgetCosts(new Integer[] { 100, 200 });
        fc.setFunds(new Integer[] { 10001000, 20002000 });
        fc.setName("Tester");
        fc.setYear((short) 2010);
        fc.save();

        course.setNew(true);
        course.setFinancialCategoryId(fc.getId());
        course.save();

        List result = course.getCourses(fc);

        assertNotNull(result);
        assertTrue(result.size() >= 1);
        assertEquals(course, result.get(0));
    }

    /**
     * Test of getCoursesBySemester method, of class Course.
     */
    @Test
    public void testGetCoursesBySemester() throws AdoHiveException {
        System.out.println("getCoursesBySemester");

        course.setNew(true);
        course.save();

        List result = course.getCoursesBySemester(course.getSemester());

        assertNotNull(result);
        assertTrue(result.size() > 0);
    }

    /**
     * Test of getCoursesByGroup method, of class Course.
     */
    @Test
    public void testGetCoursesByGroup() throws AdoHiveException {
        System.out.println("getCoursesByGroup");

        course.setNew(true);
        course.save();

        List result = course.getCoursesByGroup(course.getGroup());

        assertNotNull(result);
        assertTrue(result.size() > 0);
    }

    /**
     * Test of getDistinctSemesters method, of class Course.
     */
    @Test
    public void testGetDistinctSemesters() throws AdoHiveException {
        System.out.println("getDistinctSemesters");

        course.setSemester("SS10");
        course.setNew(true);
        course.save();

        List result = course.getDistinctSemesters();

        assertNotNull(result);
        assertTrue(result.contains(course.getSemester()));
    }

    /**
     * Test of getDistinctGroups method, of class Course.
     */
    @Test
    public void testGetDistinctGroups() throws AdoHiveException {
        System.out.println("getDistinctGroups");

        course.setGroup("A");
        course.setNew(true);
        course.save();

        List result = course.getDistinctGroups();

        assertNotNull(result);
        assertTrue(result.contains(course.getGroup()));
    }

}