package de.aidger.model.models;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * Tests the HourlyWage class.
 *
 * @author aidGer Team
 */
public class HourlyWageTest {

    protected HourlyWage hourly = null;

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
     * Test of constructor, of class Contract.
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