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
        DateRangeValidator val = new DateRangeValidator(model, "from", "to",
                "from", "to");

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

        DateRangeValidator val = new DateRangeValidator(null, "", "", "", "");
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