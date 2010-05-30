package de.aidger.model.validators;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the EmailValidator class.
 *
 * @author aidGer Team
 */
public class EmailValidatorTest {

    /**
     * Test of validateVar method, of class EmailValidator.
     */
    @Test
    public void testValidateVar() {
        System.out.println("validateVar");

        EmailValidator val = new EmailValidator(null, null);

        assertFalse(val.validateVar(null));
        assertFalse(val.validateVar(""));
        assertFalse(val.validateVar("a@example.com"));
        assertFalse(val.validateVar("email@example"));
        assertFalse(val.validateVar("email@example.c"));        
        assertTrue(val.validateVar("email@example.com"));
    }

}