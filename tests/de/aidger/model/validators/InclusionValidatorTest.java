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

        assertTrue(!val.validateVar(null));
        assertTrue(!val.validateVar(""));
        assertTrue(!val.validateVar("inclusion"));
        assertTrue(val.validateVar("in"));
    }

}