package de.aidger.model.validators;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Date;

/**
 * Tests the PresenceValidator class.
 *
 * @author aidGer Team
 */
public class PresenceValidatorTest {

    /**
     * Test of validateVar method, of class PresenceValidator.
     */
    @Test
    public void testValidateVar() {
        System.out.println("validateVar");

        PresenceValidator val = new PresenceValidator(null, null);

        assertFalse(val.validateVar(null));
        assertFalse(val.validateVar(""));
        assertTrue(val.validateVar(new Date(10)));
        assertTrue(val.validateVar("Test"));
    }

}