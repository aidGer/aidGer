package de.aidger.model.models;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the FinancialCategory class.
 *
 * @author aidGer Team
 */
public class FinancialCategoryTest {

    /**
     * Test of clone method, of class FinancialCategory.
     */
    @Test
    public void testClone() {
        System.out.println("clone");

        FinancialCategory f = new FinancialCategory();
        f.setId(1);
        f.setBugdetCosts(new int[] { 100, 200 });
        f.setFonds(new int[] { 100, 200 });
        f.setName("Tester");
        f.setYear((short) 2010);

        FinancialCategory result = f.clone();

        assertEquals(f.getId(), result.getId());
        assertEquals(f.getBugdetCosts(), result.getBugdetCosts());
        assertEquals(f.getFonds(), result.getFonds());
        assertEquals(f.getName(), result.getName());
        assertEquals(f.getYear(), result.getYear());
    }

}