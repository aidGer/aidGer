/**
 * 
 */
package de.aidger.model.reports;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Phil
 * 
 */
public class BalanceFilterTest {
    private BalanceFilter balanceFilter = null;

    public BalanceFilterTest() {

    }

    /**
     * Prepares this test
     */
    @Before
    public void setUp() {

    }

    /**
     * Test the constructor of the class BalanceFilter.
     */
    @Test
    public void testConstructor() {
        System.out.println("Constructor");

        balanceFilter = new BalanceFilter();

        assertNotNull(balanceFilter);
        assertNotNull(balanceFilter.getGroups());
        assertNotNull(balanceFilter.getLecturers());
        assertNotNull(balanceFilter.getTargetAudiences());
    }

    /**
     * Tests the addGroup() method of the class BalanceFilter.
     */
    @Test
    public void testAddGroup() {
        System.out.println("addGroup()");

        balanceFilter = new BalanceFilter();

        balanceFilter.addGroup("Test group");

        assertTrue(balanceFilter.getGroups().contains("Test group"));
    }

    /**
     * Tests the addLecturer() method of the class BalanceFilter.
     */
    @Test
    public void testAddLecturer() {
        System.out.println("addLecturer()");

        balanceFilter = new BalanceFilter();

        balanceFilter.addLecturer("Test lecturer");

        assertTrue(balanceFilter.getLecturers().contains("Test lecturer"));
    }

    /**
     * Tests the addTargetAudience() method of the class BalanceFilter.
     */
    @Test
    public void testAddTargetAudience() {
        System.out.println("addTargetAudience()");

        balanceFilter = new BalanceFilter();

        balanceFilter.addTargetAudience("Test audience");

        assertTrue(balanceFilter.getTargetAudiences().contains("Test audience"));
    }

    /**
     * Tests the removeGroup() method of the class BalanceFilter.
     */
    @Test
    public void testRemoveGroup() {
        System.out.println("removeGroup()");

        balanceFilter = new BalanceFilter();

        balanceFilter.addGroup("Test group");

        assertTrue(balanceFilter.getGroups().contains("Test group"));

        balanceFilter.removeGroup("Test group");

        assertTrue(balanceFilter.getGroups().isEmpty());
    }

    /**
     * Tests the removeLecturer() method of the class BalanceFilter.
     */
    @Test
    public void testRemoveLecturer() {
        System.out.println("removeLecturer()");

        balanceFilter = new BalanceFilter();

        balanceFilter.addLecturer("Test lecturer");

        assertTrue(balanceFilter.getLecturers().contains("Test lecturer"));

        balanceFilter.removeLecturer("Test lecturer");

        assertTrue(balanceFilter.getLecturers().isEmpty());
    }

    /**
     * Tests the method removeTargetAudience of the class BalanceFilter.
     */
    @Test
    public void testRemoveTargetAudience() {
        System.out.println("removeTargetAudience()");

        balanceFilter = new BalanceFilter();

        balanceFilter.addTargetAudience("Test audience");

        assertTrue(balanceFilter.getTargetAudiences().contains("Test audience"));

        balanceFilter.removeTargetAudience("Test audience");

        assertTrue(balanceFilter.getTargetAudiences().isEmpty());
    }
}
