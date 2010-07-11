/**
 * 
 */
package de.aidger.model.budgets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.aidger.model.budgets.BudgetFilter.Comparison;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * Test the budget filter class.
 * 
 * @author aidGer Team
 */
public class BudgetFilterTest {

    private static BudgetFilter budgetFilter;

    @BeforeClass
    public static void beforeClassSetUp() {
        budgetFilter = new BudgetFilter();
    }

    /**
     * Sets up every test.
     * 
     * @throws AdoHiveException
     */
    @Before
    public void setUp() throws AdoHiveException {
    }

    /**
     * Tests the constructor of the class BudgetFilter.
     */
    @Test
    public void testConstructor() {
        System.out.println("new BudgetFilter()");

        budgetFilter = new BudgetFilter();
        assertNotNull(budgetFilter);
    }

    /**
     * Tests the method addLecturer() of the class BudgetFilter.
     */
    @Test
    public void testAddLecturer() {
        System.out.println("addLecturer()");

        budgetFilter.addLecturer("Test Lecturer");

        assertTrue(budgetFilter.getLecturers().contains("Test Lecturer"));
    }

    /**
     * Tests the method removeLecturer() of the class BudgetFilter.
     */
    @Test
    public void testRemoveLecturer() {
        System.out.println("removeLecturer()");

        budgetFilter.removeLecturer("Test Lecturer");

        assertTrue(!(budgetFilter.getLecturers().contains("Test Lecturer")));
    }

    /**
     * Tests the method addSemester() of the class BudgetFilter.
     */
    @Test
    public void testAddSemester() {
        System.out.println("addSemester()");

        budgetFilter.addSemester("WS1516");

        assertTrue(budgetFilter.getSemesters().contains("WS1516"));
    }

    /**
     * Tests the method removeSemester() of the class BudgetFilter.
     */
    @Test
    public void testRemoveSemester() {
        System.out.println("removeSemester()");

        budgetFilter.removeSemester("WS1516");

        assertTrue(!(budgetFilter.getSemesters().contains("WS1516")));
    }

    /**
     * Tests the comparisons of the class BudgetFilter.
     */
    @Test
    public void testComparison() {
        System.out.println("Comparison");

        assertEquals("=", Comparison.EQUAL.toString());
    }

    /**
     * Tests the setters and getters of the class BudgetFilter.
     */
    @Test
    public void testSettersGetters() {
        System.out.println("Setters and Getters");

        budgetFilter.setAvailableBudget(10);
        assertEquals(10, budgetFilter.getAvailableBudget(), 0);

        budgetFilter.setAvailableComparison(Comparison.NONE);
        assertEquals(Comparison.NONE, budgetFilter.getAvailableComparison());

        budgetFilter.setBookedBudget(10);
        assertEquals(10, budgetFilter.getBookedBudget(), 0);

        budgetFilter.setBookedComparison(Comparison.NONE);
        assertEquals(Comparison.NONE, budgetFilter.getBookedComparison());

        budgetFilter.setTotalBudget(10);
        assertEquals(10, budgetFilter.getTotalBudget(), 0);

        budgetFilter.setTotalComparison(Comparison.NONE);
        assertEquals(Comparison.NONE, budgetFilter.getTotalComparison());

    }

    /**
     * Cleans up after the tests.
     * 
     * @throws AdoHiveException
     */
    @After
    public void cleanUp() throws AdoHiveException {
    }

}
