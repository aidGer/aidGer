/*
 * This file is part of the aidGer project.
 *
 * Copyright (C) 2010-2011 The aidGer Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
        Validator valid = new ValidatorImpl(new String[] { "test", "name" });

        assertTrue(valid.validate(model));

        valid = new ValidatorImpl(new String[] { "notdefined" });

        assertFalse(valid.validate(model));
    }

    /**
     * Test of setMessage method, of class Validator.
     */
    @Test
    public void testSetMessage() {
        System.out.println("setMessage");

        ModelImpl model = new ModelImpl();

        Validator valid = new ValidatorImpl(new String[] { "notdefined" });
        valid.setMessage("Bla");

        assertFalse(valid.validate(model));
        assertEquals(model.getErrors().get(0), "notdefined Bla");
    }

    /**
     * Test of getMessage method, of class Validator.
     */
    @Test
    public void testGetMessage() {
        System.out.println("getMessage");

        Validator valid = new ValidatorImpl(null);
        valid.setMessage("Bla");

        assertEquals("Bla", valid.getMessage());
    }

    public class ValidatorImpl extends Validator {

        public ValidatorImpl(String[] members) {
            super(members, members);
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