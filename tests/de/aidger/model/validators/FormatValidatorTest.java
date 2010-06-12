package de.aidger.model.validators;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the FormatValidator class.
 *
 * @author aidGer Team
 */
public class FormatValidatorTest {


    /**
     * Test of validateVar method, of class FormatValidator.
     */
    @Test
    public void testValidateVar() {
        System.out.println("validateVar");

        FormatValidator val = new FormatValidator(null, null, "[abc]*");

        assertFalse(val.validateVar(null));
        assertFalse(val.validateVar(new Object()));
        assertFalse(val.validateVar("d"));
        assertTrue(val.validateVar("aabbcc"));
        assertTrue(val.validateVar(""));
    }

}