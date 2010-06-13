package de.aidger.model.models;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import java.util.List;
import org.junit.BeforeClass;

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
        fc.setBudgetCosts(new int[] { 100 });
        fc.setFunds(new int[] { 10001000 });
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
        course.setUnqualifiedWorkingHours(100);
    }

    /**
     * Test of constructor, of class Course.
     */
    @Test
    public void testConstructor() throws AdoHiveException {
        System.out.println("Constructor");

        course.setNew(true);
        course.save();

        Course result = new Course(course.getById(
                course.getId()));

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

        course.setAdvisor(null);
        assertFalse(course.save());
        course.resetErrors();
        course.setAdvisor("Tester");

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

        course.setTargetAudience(null);
        assertFalse(course.save());
        course.resetErrors();
        course.setTargetAudience("Testers");

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
        activity.setAssistantId(-1);
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

        Employment employment = new Employment();
        employment.setAssistantId(-1);
        employment.setContractId(-1);
        employment.setCourseId(course.getId());
        employment.setCostUnit("0711");
        employment.setFunds(1);
        employment.setHourCount(40);
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
        assertEquals(course.getFinancialCategoryId(), result.getFinancialCategoryId());
        assertEquals(course.getGroup(), result.getGroup());
        assertEquals(course.getLecturer(), result.getLecturer());
        assertEquals(course.getNumberOfGroups(), result.getNumberOfGroups());
        assertEquals(course.getPart(), result.getPart());
        assertEquals(course.getRemark(), result.getRemark());
        assertEquals(course.getScope(), result.getScope());
        assertEquals(course.getSemester(), result.getSemester());
        assertEquals(course.getTargetAudience(), result.getTargetAudience());
        assertEquals(course.getUnqualifiedWorkingHours(), result.getUnqualifiedWorkingHours(), 0.001);
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
     * Test of hashCode method, of class Assistant.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Course result = course.clone();

        assertEquals(course.hashCode(), result.hashCode());
    }

     /**
     * Test of getCourses method, of class Assistant.
     */
    @Test
    public void testGetCourses_FinancialCategory() throws AdoHiveException {
        System.out.println("getCourses");

        FinancialCategory fc = new FinancialCategory();
        fc.setBudgetCosts(new int[] { 100, 200 });
        fc.setFunds(new int[] { 10001000, 20002000 });
        fc.setName("Tester");
        fc.setYear((short) 2010);
        fc.save();

        course.clearTable();
        course.setFinancialCategoryId(fc.getId());
        course.save();

        List result = course.getCourses(fc);

        assertNotNull(result);
        assertTrue(result.size() == 1);
        assertEquals(course, result.get(0));
    }

}