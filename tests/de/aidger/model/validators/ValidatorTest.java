package de.aidger.model.validators;

import org.junit.Test;
import static org.junit.Assert.*;

import de.aidger.model.AbstractModel;

/**
 * Tests the abstract Validator class.
 *
 * @author aidGer Team
 */
public class ValidatorTest {

    /**
     * Test of validate method, of class Validator.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");

        ModelImpl model = new ModelImpl();
        Validator valid = new ValidatorImpl(model,
                new String[] { "test", "name" });

        assertTrue(valid.validate());

        valid = new ValidatorImpl(model, new String[] { "notdefined" });

        assertFalse(valid.validate());
    }

    /**
     * Test of setMessage method, of class Validator.
     */
    @Test
    public void testSetMessage() {
        ModelImpl model = new ModelImpl();

        Validator valid = new ValidatorImpl(model, new String[] { "notdefined" });
        valid.setMessage("Bla");

        assertFalse(valid.validate());
        assertEquals(model.getErrors().get(0), "notdefined Bla");
    }

    public class ValidatorImpl extends Validator {

        public ValidatorImpl(AbstractModel model, String[] members) {
            super(model, members);
        }

        public boolean validateVar(Object o) {
            if (o instanceof String && ((String) o).equals("Test")) {
                return true;
            }
            return false;
        }
    }

    public class ModelImpl extends AbstractModel<ModelImpl> {

        public String test = "Test";

        protected String name = "Test";

        @Override
        public ModelImpl clone() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getName() {
            return name;
        }
        
    }

}