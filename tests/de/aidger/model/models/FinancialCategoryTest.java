package de.aidger.model.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import org.junit.BeforeClass;

/**
 * Tests the FinancialCategory class.
 *
 * @author aidGer Team
 */
public class FinancialCategoryTest {

    protected FinancialCategory financial = null;

    @BeforeClass
    public static void beforeClassSetUp() {
        de.aidger.model.Runtime.getInstance().initialize();
    }

    @Before
    public void setUp() {
        financial = new FinancialCategory();
        financial.setId(1);
        financial.setBudgetCosts(new int[] { 100, 200 });
        financial.setFunds(new int[] { 10001000, 20002000 });
        financial.setName("Tester");
        financial.setYear((short) 2010);
    }

    /**
     * Test of constructor, of class FinancialCategory.
     */
    @Test
    public void testConstructor() throws AdoHiveException {
        System.out.println("Constructor");

        financial.setNew(true);
        financial.save();

        FinancialCategory result = new FinancialCategory(financial.getById(
                financial.getId()));

        assertNotNull(result);
        assertEquals(financial, result);
    }

    /**
     * Test of validation methods, of class FinancialCategory.
     */
    @Test
    public void testValidation() throws AdoHiveException {
        System.out.println("Validation");

        financial.setNew(true);
        assertTrue(financial.save());

        financial.setName(null);
        assertFalse(financial.save());
        financial.setName("Tester");

        financial.setYear((short) 999);
        assertFalse(financial.save());

        financial.setYear((short) 10101);
        assertFalse(financial.save());
        financial.setYear((short) 2010);

        financial.setBudgetCosts(new int[] { 0, -1 });
        assertFalse(financial.save());
        financial.setBudgetCosts(new int[] { 100, 200 });

        financial.setFunds(new int[] { 1234567 });
        assertFalse(financial.save());

        financial.setFunds(new int[] { 123456789 });
        assertFalse(financial.save());
    }

    /**
     * Test of validateOnRemove, of class Contract.
     */
    @Test
    public void testValidateOnRemove() throws AdoHiveException {
        System.out.println("validateOnRemove");

        financial.clearTable();
        assertTrue(financial.save());
        assertTrue(financial.remove());

        financial.save();
        Course course = new Course();
        course.setAdvisor("Tester");
        course.setDescription("Description");
        course.setFinancialCategoryId(financial.getId());
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

        assertFalse(financial.remove());
        financial.resetErrors();
        course.remove();

        assertTrue(financial.remove());
    }

    /**
     * Test of clone method, of class FinancialCategory.
     */
    @Test
    public void testClone() {
        System.out.println("clone");

        FinancialCategory result = financial.clone();

        assertEquals(financial.getId(), result.getId());
        assertEquals(financial.getBudgetCosts(), result.getBudgetCosts());
        assertEquals(financial.getFunds(), result.getFunds());
        assertEquals(financial.getName(), result.getName());
        assertEquals(financial.getYear(), result.getYear());
    }

    /**
     * Test of equals method, of class FinancialCategory.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        FinancialCategory result = financial.clone();

        assertEquals(financial, result);
        assertFalse(financial.equals(new Object()));
    }

    /**
     * Test of hashCode method, of class Assistant.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        FinancialCategory result = financial.clone();

        assertEquals(financial.hashCode(), result.hashCode());
    }

}