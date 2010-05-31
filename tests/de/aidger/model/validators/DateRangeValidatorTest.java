package de.aidger.model.validators;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Date;

import de.aidger.model.AbstractModel;

/**
 * Tests the DateRangeValidator class.
 *
 * @author aidGer Team
 */
public class DateRangeValidatorTest {

    /**
     * Test of validate method, of class DateRangeValidator.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");

        ModelImpl model = new ModelImpl();
        DateRangeValidator val = new DateRangeValidator(model, "from", "to");

        assertTrue(val.validate());

        model.from = new Date(2);
        assertFalse(val.validate());

        model.from = new Date(3);
        assertFalse(val.validate());
    }

    /**
     * Test of static validate method, of class DateRangeValidator.
     */
    @Test
    public void testStaticValidate() {
        System.out.println("static validate");

        assertFalse(DateRangeValidator.validate(new Date(100), new Date(1)));
        assertFalse(DateRangeValidator.validate(new Date(1), new Date(1)));
        assertTrue(DateRangeValidator.validate(new Date(1), new Date(100)));
    }

    /**
     * Test of validateVar method, of class DateRangeValidator.
     */
    @Test
    public void testValidateVar() {
        System.out.println("validateVar");

        DateRangeValidator val = new DateRangeValidator(null, "", "");
        assertFalse(val.validateVar(new Object()));
    }

    public class ModelImpl extends AbstractModel<ModelImpl> {

        public Date from = new Date(1);

        public Date to = new Date(2);

        @Override
        public ModelImpl clone() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

}