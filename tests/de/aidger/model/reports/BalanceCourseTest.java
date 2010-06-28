/**
 * 
 */
package de.aidger.model.reports;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import de.aidger.model.reports.BalanceCourse.BudgetCost;

/**
 * @author Phil
 * 
 */
public class BalanceCourseTest {

    private static BalanceCourse balanceCourse = null;

    public BalanceCourseTest() {
        new BalanceCourse();
    }

    @After
    public void cleanUp() {
    }

    /**
     * Prepares this test.
     */
    @BeforeClass
    public static void beforeClassSetUp() {
        balanceCourse = new BalanceCourse();
        balanceCourse.setTitle("Test title");
        balanceCourse.setLecturer("Test Lecturer");
        balanceCourse.setBasicAWS(100);
        balanceCourse.setPart('t');
        balanceCourse.setPlannedAWS(100);
        balanceCourse.addBudgetCost(11111111, "Test cost", 120);
        balanceCourse.setTargetAudience("Test Audience");
    }

    /**
     * Tests the constructor of the BalanceCourse class.
     */
    @Test
    public void testConstructor() {
        System.out.println("Constructor");

        BalanceCourse result = new BalanceCourse();

        assertNotNull(result);
        assertEquals(result.getTitle(), "");
        assertEquals(result.getBasicAWS(), 0.0, 0);
        assertEquals(result.getLecturer(), "");
        assertEquals(result.getPart(), '-');
        assertEquals(result.getPlannedAWS(), 0.0, 0);
        BudgetCost resultBudgetCost = balanceCourse.getBudgetCosts().get(0);
        assertEquals(11111111, resultBudgetCost.getId());
        assertEquals("Test cost", resultBudgetCost.getName());
        assertEquals(new BigDecimal(120).setScale(2).doubleValue(),
                (double)resultBudgetCost.getValue(), 0.001);
        assertEquals(result.getTargetAudience(), "");
    }

    /**
     * Tests the getCourseObject() method of the BalanceCourse class.
     */
    @Test
    public void testGetCourseObject() {
        System.out.println("getCourseObject()");

        Object[] values = balanceCourse.getCourseObject();

        assertNotNull(values);
        assertEquals(values[0], balanceCourse.getTitle());
        assertEquals(values[1], balanceCourse.getPart());
        assertEquals(values[2], balanceCourse.getLecturer());
        assertEquals(values[3], balanceCourse.getTargetAudience());
        assertEquals(values[4], balanceCourse.getPlannedAWS());
        assertEquals(values[5], balanceCourse.getBasicAWS());
        assertEquals(values[6], balanceCourse.getBudgetCosts());
    }

}
