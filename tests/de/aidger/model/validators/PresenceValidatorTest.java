/*
 * This file is part of the aidGer project.
 *
 * Copyright (C) 2010-2013 The aidGer Team
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

    /**
     * Test of validate method, of class PresenceValidator.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");

        assertFalse(PresenceValidator.validate((Object) null));
        assertTrue(PresenceValidator.validate(this));
    }

}
