package de.aidger.model.models;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import org.junit.BeforeClass;

/**
 * Tests the HourlyWage class.
 *
 * @author aidGer Team
 */
public class HourlyWageTest {

    protected HourlyWage hourly = null;

    @BeforeClass
    public static void beforeClassSetUp() {
        de.aidger.model.Runtime.getInstance().initialize();
    }

    @Before
    public void setUp() {
        hourly = new HourlyWage();
        hourly.setId(1);
        hourly.setMonth((byte) 10);
        hourly.setQualification("g");
        hourly.setWage(new BigDecimal(200));
        hourly.setYear((short) 2010);
    }

    /**
     * Test of constructor, of class HourlyWage.
     */
    @Test
    public void testConstructor() throws AdoHiveException {
        System.out.println("Constructor");

        hourly.clearTable();
        hourly.setNew(true);
        hourly.save();

        HourlyWage result = new HourlyWage(hourly.getByKeys(
                hourly.getQualification(), hourly.getMonth(),
                hourly.getYear()));

        assertNotNull(result);
        assertEquals(hourly, result);
    }

    /**
     * Test of validation methods, of class HourlyWage.
     */
    @Test
    public void testValidation() throws AdoHiveException {
        System.out.println("Validation");

        hourly.clearTable();
        hourly.setNew(true);
        assertTrue(hourly.save());

        hourly.setQualification(null);
        assertFalse(hourly.save());

        hourly.setQualification("Q");
        assertFalse(hourly.save());
        hourly.setQualification("g");

        hourly.setMonth((byte) -1);
        assertFalse(hourly.save());

        hourly.setMonth((byte) 13);
        assertFalse(hourly.save());
        hourly.setMonth((byte) 10);

        hourly.setYear((short) 999);
        assertFalse(hourly.save());

        hourly.setYear((short) 10101);
        assertFalse(hourly.save());
    }


    /**
     * Test of clone method, of class HourlyWage.
     */
    @Test
    public void testClone() {
        System.out.println("clone");

        HourlyWage result = hourly.clone();

        assertEquals(hourly.getId(), result.getId());
        assertEquals(hourly.getMonth(), result.getMonth());
        assertEquals(hourly.getQualification(), result.getQualification());
        assertEquals(hourly.getWage(), result.getWage());
        assertEquals(hourly.getYear(), result.getYear());
    }

    /**
     * Test of equals method, of class HourlyWage.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        HourlyWage result = hourly.clone();

        assertEquals(hourly, result);
        assertFalse(hourly.equals(new Object()));
    }

    /**
     * Test of hashCode method, of class Assistant.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        HourlyWage result = hourly.clone();

        assertEquals(hourly.hashCode(), result.hashCode());
    }

}