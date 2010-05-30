package de.aidger.model.models;

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
        financial.setBugdetCosts(new int[] { 100, 200 });
        financial.setFonds(new int[] { 100, 200 });
        financial.setName("Tester");
        financial.setYear((short) 2010);
    }

    /**
     * Test of clone method, of class FinancialCategory.
     */
    @Test
    public void testClone() {
        System.out.println("clone");

        FinancialCategory result = financial.clone();

        assertEquals(financial.getId(), result.getId());
        assertEquals(financial.getBugdetCosts(), result.getBugdetCosts());
        assertEquals(financial.getFonds(), result.getFonds());
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
    }

}