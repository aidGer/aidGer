package de.aidger.model.models;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the Course class.
 *
 * @author aidGer Team
 */
public class CourseTest {

    /**
     * Test of clone method, of class Course.
     */
    @Test
    public void testClone() {
        System.out.println("clone");

        Course c = new Course();
        c.setId(1);
        c.setDescription("Description");
        c.setFinancialCategoryId(1);
        c.setGroup("2");
        c.setLecturer("Test Tester");
        c.setNumberOfGroups(3);
        c.setPart('a');
        c.setRemark("Remark");
        c.setScope("Sniper Scope");
        c.setSemester("SS 09");
        c.setTargetAudience("Testers");
        c.setUnqualifiedWorkingHours(100);

        Course result = c.clone();

        assertEquals(c.getId(), result.getId());
        assertEquals(c.getDescription(), result.getDescription());
        assertEquals(c.getFinancialCategoryId(), result.getFinancialCategoryId());
        assertEquals(c.getGroup(), result.getGroup());
        assertEquals(c.getLecturer(), result.getLecturer());
        assertEquals(c.getNumberOfGroups(), result.getNumberOfGroups());
        assertEquals(c.getPart(), result.getPart());
        assertEquals(c.getRemark(), result.getRemark());
        assertEquals(c.getScope(), result.getScope());
        assertEquals(c.getSemester(), result.getSemester());
        assertEquals(c.getTargetAudience(), result.getTargetAudience());
        assertEquals(c.getUnqualifiedWorkingHours(), result.getUnqualifiedWorkingHours(), 0.001);
    }

}