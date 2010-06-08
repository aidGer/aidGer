package de.aidger.model.validators;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the InclusionValidator class.
 *
 * @author aidGer Team
 */
public class InclusionValidatorTest {

    /**
     * Test of validateVar method, of class InclusionValidator.
     */
    @Test
    public void testValidateVar() {
        System.out.println("validateVar");

        InclusionValidator val = new InclusionValidator(null, null,
                new String[] { "in", "cl", "us", "ion" });

        assertFalse(val.validateVar(null));
        assertFalse(val.validateVar(""));
        assertFalse(val.validateVar("inclusion"));
        assertTrue(val.validateVar("in"));
    }

}