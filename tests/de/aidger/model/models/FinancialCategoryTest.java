package de.aidger.model.models;

import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Tests the FinancialCategory class.
 *
 * @author aidGer Team
 */
public class FinancialCategoryTest {

    protected FinancialCategory financial = null;

    @Before
    public void setUp() {
        financial = new FinancialCategory();
        financial.setId(1);
        financial.setBudgetCosts(new int[] { 100, 200 });
        financial.setFunds(new int[] { 100, 200 });
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