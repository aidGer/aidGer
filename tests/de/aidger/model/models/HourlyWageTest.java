package de.aidger.model.models;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

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
        hourly.setQualification("OverQualified");
        hourly.setWage(200);
        hourly.setYear((short) 2010);
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
        assertEquals(hourly.getWage(), result.getWage(), 0.001);
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
    }

}