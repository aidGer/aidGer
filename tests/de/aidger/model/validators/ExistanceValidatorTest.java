package de.aidger.model.validators;

import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import org.junit.Test;
import static org.junit.Assert.*;

import de.aidger.model.models.Assistant;
import de.aidger.model.Runtime;

/**
 * Tests the ExistanceValidator class.
 *
 * @author aidGer Team
 */
public class ExistanceValidatorTest {

    public ExistanceValidatorTest() {
        Runtime.getInstance().initialize();
    }

    /**
     * Test of validateVar method, of class ExistanceValidator.
     */
    @Test
    public void testValidateVar() throws AdoHiveException {
        System.out.println("validateVar");

        Assistant a = new Assistant();

        ExistanceValidator val = new ExistanceValidator(null, null, null, a);

        assertFalse(val.validateVar(new Object()));
        assertFalse(val.validateVar(0));

        a.setEmail("test@example.com");
        a.setFirstName("Test");
        a.setLastName("Tester");
        a.setQualification("g");
        a.save();

        assertTrue(val.validateVar(a.getId()));
        assertTrue(val.validateVar(null));
    }

}