package de.aidger.model.models;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the HourlyWage class.
 *
 * @author aidGer Team
 */
public class HourlyWageTest {

    /**
     * Test of clone method, of class HourlyWage.
     */
    @Test
    public void testClone() {
        System.out.println("clone");

        HourlyWage h = new HourlyWage();
        h.setId(1);
        h.setMonth((byte) 10);
        h.setQualification("OverQualified");
        h.setWage(200);
        h.setYear((short) 2010);

        HourlyWage result = h.clone();

        assertEquals(h.getId(), result.getId());
        assertEquals(h.getMonth(), result.getMonth());
        assertEquals(h.getQualification(), result.getQualification());
        assertEquals(h.getWage(), result.getWage(), 0.001);
        assertEquals(h.getYear(), result.getYear());
    }

}