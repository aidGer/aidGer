package de.aidger.model.models;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * Tests the Course class.
 *
 * @author aidGer Team
 */
public class CourseTest {
    
    protected Course course = null;

    @Before
    public void setUp() {
        course = new Course();
        course.setId(1);
        course.setAdvisor("Tester");
        course.setDescription("Description");
        course.setFinancialCategoryId(1);
        course.setGroup("2");
        course.setLecturer("Test Tester");
        course.setNumberOfGroups(3);
        course.setPart('a');
        course.setRemark("Remark");
        course.setScope("Sniper Scope");
        course.setSemester("SS 09");
        course.setTargetAudience("Testers");
        course.setUnqualifiedWorkingHours(100);
    }

    /**
     * Test of constructor, of class Contract.
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

}