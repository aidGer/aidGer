package de.aidger.model.validators;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Date;

/**
 *
 * @author rmbl
 */
public class DateRangeValidatorTest {

    /**
     * Test of validateVar method, of class DateRangeValidator.
     */
    @Test
    public void testValidateVar() {
        System.out.println("validateVar");

        assertFalse(DateRangeValidator.validate(new Date(100), new Date(1)));
        assertFalse(DateRangeValidator.validate(new Date(1), new Date(1)));
        assertTrue(DateRangeValidator.validate(new Date(1), new Date(100)));
    }

}